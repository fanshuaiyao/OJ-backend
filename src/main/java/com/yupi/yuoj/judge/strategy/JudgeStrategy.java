package com.yupi.yuoj.judge.strategy;

import com.yupi.yuoj.model.dto.questionsubmit.JudgeInfo;

/**
 * @author fanshuaiyao
 * @description: TODO
 * @date 2024/11/22 20:31
 */
public interface JudgeStrategy {
    /**
     * 执行判题
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
