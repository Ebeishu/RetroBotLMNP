package org.grakovne.retrobot.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * w
 * Service for data presentation between string representation and POJO.
 */
@Service
public class DataPresentationService {

    /**
     * Makes ZonedDateTime By date.
     */
    public ZonedDateTime fromRawDate(String rawDate) {
        return LocalDate.parse(rawDate, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneOffset.UTC);
    }
}
