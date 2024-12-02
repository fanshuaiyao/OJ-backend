package com.yupi.yuoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.yupi.yuoj.model.dto.question.JudgeCase;
import com.yupi.yuoj.model.dto.question.JudgeConfig;
import com.yupi.yuoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.yuoj.model.entity.Question;
import com.yupi.yuoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * @author fanshuaiyao
 * @description: 默认策略
 * @date 2024/11/22 20:35
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        // 获取沙箱执行代码所用的内存和时间   可以判断其他的  todo
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();

        // 拿到题目中的判题配置
        Question question = judgeContext.getQuestion();
        String judgeConfigStr = question.getJudgeConfig();

        // 拿到原本的测试输出用例去比对沙箱的输出用例
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();

        //  先默认一个状态
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();


        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);



        // 1 先判断数量是否相等
        // 1.1 输出数量和输入数量不相等
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        // 1.2 依次判断输出和预期输出是否相等
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }

        // 2. 判断题目的限制条件
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long memoryLimit = judgeConfig.getMemoryLimit();
        Long timeLimit = judgeConfig.getTimeLimit();
        // 2.1 内存超出限制
        if (memory > memoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // 2.2 运行时间超出限制
        if (time > timeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        // 3. 判题通过 使用默认状态
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
        
    }
}
