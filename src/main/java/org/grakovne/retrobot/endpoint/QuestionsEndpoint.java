package org.grakovne.retrobot.endpoint;

import org.grakovne.retrobot.converter.QuestionConverter;
import org.grakovne.retrobot.response.CoreMessageResponse;
import org.grakovne.retrobot.service.DataPresentationService;
import org.grakovne.retrobot.service.question.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/retrobot")
public class QuestionsEndpoint {

    private final QuestionService questionService;
    private final DataPresentationService presentationService;
    private final QuestionConverter questionConverter;

    public QuestionsEndpoint(QuestionService questionService,
                             DataPresentationService presentationService,
                             QuestionConverter questionConverter) {
        this.questionService = questionService;
        this.presentationService = presentationService;
        this.questionConverter = questionConverter;
    }

    @GetMapping("questions")
    public List<CoreMessageResponse> findQuestionsWithDates(
            @RequestParam(name = "from", required = false, defaultValue = "1970-01-01") String from,
            @RequestParam(name = "to", required = false, defaultValue = "2099-12-31") String to) {

        return questionService
                .findForDates(presentationService.fromRawDate(from), presentationService.fromRawDate(to))
                .stream()
                .map(questionConverter::convert)
                .collect(Collectors.toList());
    }
}
