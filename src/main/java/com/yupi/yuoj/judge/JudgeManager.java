package com.yupi.yuoj.judge;

import com.yupi.yuoj.judge.strategy.DefaultJudgeStrategy;
import com.yupi.yuoj.judge.strategy.JudgeContext;
import com.yupi.yuoj.judge.codesandbox.model.JudgeInfo;
import org.springframework.stereotype.Service;

/**
 * @author fanshuaiyao
 * @description: 尽量简化调用方的操作  判题管理
 * @date 2024/11/23 15:11
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        // 拿到语言区分策略
        String language = judgeContext.getQuestionSubmit().getLanguage();
        DefaultJudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new DefaultJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);

    }


}
