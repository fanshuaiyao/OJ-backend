package com.yupi.yuoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.yuoj.common.BaseResponse;
import com.yupi.yuoj.common.ErrorCode;
import com.yupi.yuoj.common.ResultUtils;
import com.yupi.yuoj.exception.BusinessException;
import com.yupi.yuoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.yuoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.yuoj.model.entity.QuestionSubmit;
import com.yupi.yuoj.model.entity.User;
import com.yupi.yuoj.model.vo.QuestionSubmitVO;
import com.yupi.yuoj.service.QuestionSubmitService;
import com.yupi.yuoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
// @RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QusetionSubmitController {

    // @Resource
    // private QuestionSubmitService questionSubmitService;
    //
    // @Resource
    // private UserService userService;
    //
    // /**
    //  * 提交题目
    //  *
    //  * @param QuestionSubmitAddRequest
    //  * @param request
    //  * @return 提交记录的id
    //  */
    // @PostMapping("/")
    // public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest QuestionSubmitAddRequest,
    //         HttpServletRequest request) {
    //     if (QuestionSubmitAddRequest == null || QuestionSubmitAddRequest.getQuestionId() <= 0) {
    //         throw new BusinessException(ErrorCode.PARAMS_ERROR);
    //     }
    //     // 登录才能提交题目
    //     final User loginUser = userService.getLoginUser(request);
    //     long questionSubmitId = questionSubmitService.doQuestionSubmit(QuestionSubmitAddRequest, loginUser);
    //     return ResultUtils.success(questionSubmitId);
    // }
    //
    // /**
    //  * 分页获取题目提交列表（仅管理员和自己能看到）
    //  *
    //  * @param questionSubmitQueryRequest
    //  * @param request
    //  * @return
    //  */
    // @PostMapping("/list/page")
    // public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
    //                                                                         HttpServletRequest request) {
    //     long current = questionSubmitQueryRequest.getCurrent();
    //     long size = questionSubmitQueryRequest.getPageSize();
    //     // 先从数据库中查出来所有的数据
    //     Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
    //             questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
    //     final User loginUser = userService.getLoginUser(request);
    //     // 在getQuestionSubmitVOPage() 中进行脱敏处理
    //     return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    // }


}
