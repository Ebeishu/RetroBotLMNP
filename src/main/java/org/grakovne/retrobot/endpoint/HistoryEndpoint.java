package org.grakovne.retrobot.endpoint;

import org.grakovne.retrobot.converter.CoreMessageConverter;
import org.grakovne.retrobot.response.CoreMessageResponse;
import org.grakovne.retrobot.service.DataPresentationService;
import org.grakovne.retrobot.service.core.CoreMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * History endpoint.
 */
@RestController
@RequestMapping("api/retrobot")
public class HistoryEndpoint {

    private final CoreMessageService coreMessageService;
    private final DataPresentationService presentationService;
    private final CoreMessageConverter converter;

    public HistoryEndpoint(CoreMessageService coreMessageService,
                           DataPresentationService presentationService,
                           CoreMessageConverter converter) {

        this.coreMessageService = coreMessageService;
        this.presentationService = presentationService;
        this.converter = converter;
    }

    @GetMapping("history")
    public List<CoreMessageResponse> findHistoryWithDates(
            @RequestParam(name = "from", required = false, defaultValue = "1970-01-01") String from,
            @RequestParam(name = "to", required = false, defaultValue = "2099-12-31") String to) {

        return coreMessageService
                .findForDates(presentationService.fromRawDate(from), presentationService.fromRawDate(to))
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
