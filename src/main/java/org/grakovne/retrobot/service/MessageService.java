package org.grakovne.retrobot.service;

import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private static final String HASH_TAG_MARKER = "#";
    private static final String NEW_WORD_MARKER = " ";

    public List<String> getKeys(RequestedMessageForm form) {
        return List
                .of(form.getText().split(NEW_WORD_MARKER))
                .stream()
                .filter(e -> e.startsWith(HASH_TAG_MARKER))
                .collect(Collectors.toList());
    }
}
