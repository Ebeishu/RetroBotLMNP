package org.grakovne.retrobot.controller;

import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.service.question.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessQuestionController extends RequestedMessageController {
    private final QuestionService businessQuestionService;

    public BusinessQuestionController(QuestionService questionService) {
        this.businessQuestionService = questionService;
    }


    @Override
    public List<String> getKeys() {
        return List.of("#вопросэксперту", "#бизнесподскажи", "#какэтоработает");
    }

    @Override
    public String getReplyMessage() {
        return "Ваш вопрос очень важен для нас!\nВ ближайшее время мы с вами свяжемся и все расскажем \uD83D\uDE42";
    }

    @Override
    protected void processMessage(RequestedMessageForm form) {
        businessQuestionService.swallowMessage(form);
    }
}
