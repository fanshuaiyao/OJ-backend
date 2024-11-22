package com.yupi.yuoj.judge.codesandbox.Impl;

import com.yupi.yuoj.judge.codesandbox.CodeSandBox;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author fanshuaiyao
 * @description: 实际调用的沙箱
 * @date 2024/11/22 17:14
 */
public class RemoteCodeSand implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("实际代码沙箱");
        return null;
    }
}
