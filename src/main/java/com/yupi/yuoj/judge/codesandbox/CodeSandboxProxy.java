package com.yupi.yuoj.judge.codesandbox;

import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fanshuaiyao
 * @description:  实现代理，在原有的代码沙箱上进新房给增强
 * @date 2024/11/22 19:00
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandBox{

    // 在这里final变量允许通过构造函数赋值，且只能被改变一次，如果没有进行构造函数传参将报错。且不存在默认值的情况
    private final CodeSandBox codeSandBox;

    public CodeSandboxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("Execute code request: {}", executeCodeRequest);
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("Execute code response: {}", executeCodeResponse);
        return executeCodeResponse;
    }
}
