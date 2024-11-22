package com.yupi.yuoj.judge.codesandbox;


import com.yupi.yuoj.judge.codesandbox.Impl.ExampleCodeSand;
import com.yupi.yuoj.judge.codesandbox.Impl.RemoteCodeSand;
import com.yupi.yuoj.judge.codesandbox.Impl.ThirdPartyCodeSand;

/**
 * @author fanshuaiyao
 * @description: 代码沙箱工厂 根据字符串创建指定的沙箱实例  静态工厂
 * @date 2024/11/22 17:28
 */
public class CodeSandFactory {

    /**
     * 创建代码沙箱实例
     * @param type 沙箱类型
     */
    public static CodeSandBox newInstance(String type) {
        switch (type){
            case "example":
                return new ExampleCodeSand();
            case "remote":
                return new RemoteCodeSand();
            case "thirdParty":
                return new ThirdPartyCodeSand();
            default:
                return new ExampleCodeSand();
        }
    }
}
