package com.yupi.yuoj.model.dto.question;

import lombok.Data;

/**
 * @author fanshuaiyao
 * @description: 题目配置
 * @date 2024/11/17 00:21
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;
}

