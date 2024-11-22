package com.yupi.yuoj.judge;

import cn.hutool.json.JSONUtil;
import com.yupi.yuoj.common.ErrorCode;
import com.yupi.yuoj.exception.BusinessException;
import com.yupi.yuoj.judge.codesandbox.CodeSandBox;
import com.yupi.yuoj.judge.codesandbox.CodeSandFactory;
import com.yupi.yuoj.judge.codesandbox.CodeSandboxProxy;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.yuoj.model.dto.question.JudgeCase;
import com.yupi.yuoj.model.dto.question.JudgeConfig;
import com.yupi.yuoj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.yuoj.model.entity.Question;
import com.yupi.yuoj.model.entity.QuestionSubmit;
import com.yupi.yuoj.model.enums.JudgeInfoMessageEnum;
import com.yupi.yuoj.model.enums.QuestionSubmitLanguageEnum;
import com.yupi.yuoj.model.enums.QuestionSubmitStatusEnum;
import com.yupi.yuoj.model.vo.QuestionVO;
import com.yupi.yuoj.service.QuestionService;
import com.yupi.yuoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fanshuaiyao
 * @description: TODO
 * @date 2024/11/22 19:34
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionVO doJudge(long questionSubmitId) {
        // 1. 根据这个id获取题目提交信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
           throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在！");
        }
        // 2. 从提交记录中拿到题目id
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        // 2.1 题目不存在抛异常
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在！");
        }
        //  题目存在的话 开始判题
        // 3. 先进行题目的状态，看看是否在判题中  如果不为等待中，其他状态全部拒绝
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中！");
        }
        // 4. 更改状态为执行中   更改状态时重新 new 一个 QuestionSubmit 对象，通常是为了数据安全性和性能优化
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        // 4.1 questionSubmitService.updateById() 方法通常是一个部分更新操作  原体数据更加安全
        questionSubmitService.updateById(questionSubmitUpdate);

        // 5. 代码沙箱
        // 5.1 获取一个代码沙箱
        CodeSandBox codeSandBox = CodeSandFactory.newInstance(type);
        // 5.2 对代码沙箱进行代理
        codeSandBox = new CodeSandboxProxy(codeSandBox);

        // 5.3 从题目提交记录和题目中拿到相关信息
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        String judgeCaseStr = question.getJudgeCase();
        // 5.3.1 获取输入用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        // 5.3.2 得到沙箱的执行结果
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);

    //     // 6. 进行结果校验
    //     // 6.1 先默认一个状态
    //     JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;
    //     // 6.2 先判断数量是否相等
    //     List<String> outputList = executeCodeResponse.getOutputList();
    //     // 6.2.1 输出数量和输入数量不相等
    //     if (outputList.size() != inputList.size()) {
    //         judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
    //         return null;
    //     }
    //
    //     // 6.2.2 依次判断输出和预期输出是否相等
    //     for (int i = 0; i < judgeCaseList.size(); i++) {
    //         // 拿到原本的测试输出用例去比对沙箱的输出用例
    //         JudgeCase judgeCase = judgeCaseList.get(i);
    //         if (!judgeCase.getOutput().equals(outputList.get(i))) {
    //             judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
    //             return null;
    //         }
    //     }
    //
    //     // 7. 判断题目的限制条件
    //     // 7.1 获取沙箱执行代码所用的内存和时间   可以判断其他的  todo
    //     JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
    //     Long memory = judgeInfo.getMemory();
    //     Long time = judgeInfo.getTime();
    //
    //     // 7.2 拿到题目中的判题配置
    //     String judgeConfigStr = question.getJudgeConfig();
    //     JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
    //     Long memoryLimit = judgeConfig.getMemoryLimit();
    //     Long timeLimit = judgeConfig.getTimeLimit();
    //     if (memory > memoryLimit) {
    //         judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
    //         return null;
    //     }
    //     if (time > timeLimit) {
    //         judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
    //         return null;
    //     }
        return null;
    }
}
