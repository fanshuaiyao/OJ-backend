package com.yupi.yuoj.judge;

import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.yuoj.model.vo.QuestionVO;

/**
 * @author fanshuaiyao
 * @description: 判题服务
 * @date 2024/11/22 19:28
 */
public interface JudgeService {

    /**
     * 根据传入的提交id，获取题目，代码等，进行判题
     */
    QuestionVO doJudge(long questionSubmitId);
}
