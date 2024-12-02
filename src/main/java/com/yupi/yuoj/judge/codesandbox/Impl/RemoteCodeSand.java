package com.yupi.yuoj.judge.codesandbox.Impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.yuoj.common.ErrorCode;
import com.yupi.yuoj.exception.BusinessException;
import com.yupi.yuoj.judge.codesandbox.CodeSandBox;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fanshuaiyao
 * @description: 实际调用的沙箱
 * @date 2024/11/22 17:14
 */
public class RemoteCodeSand implements CodeSandBox {

    // 定义鉴权请求头和密钥
    public static final String AUTH_REQUEST_HEADER = "auth";
    public static final String AUTH_REQUEST_SECRET  = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://121.194.93.24:8090/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                // .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"execute remoteCodeSandbox error message" + responseStr);
        }

        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);

    }
}
