package com.yupi.yuoj.model.dto.question;

import lombok.Data;

/**
 * @author fanshuaiyao
 * @description: 题目用例
 * @date 2024/11/17 00:21
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}

