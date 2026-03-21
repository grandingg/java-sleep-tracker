package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class BadSleepSessionsAnalysisTest {

    private SleepingSession sessionWithQuality(String start, String end, SleepingQuality quality) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        return new SleepingSession(
                LocalDateTime.parse(start, formatter),
                LocalDateTime.parse(end, formatter),
                quality
        );
    }

    @Test
    void shouldCountBadSessions() {
        List<SleepingSession> sessions = List.of(
                sessionWithQuality("01.10.25 22:00", "02.10.25 06:00", SleepingQuality.BAD),
                sessionWithQuality("02.10.25 22:00", "03.10.25 06:00", SleepingQuality.GOOD)
        );

        BadSleepSessionsAnalysis analysis = new BadSleepSessionsAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(1L, result.getAmount());
    }

    @Test
    void shouldReturnZeroWhenNoBadSessions() {
        List<SleepingSession> sessions = List.of(
                sessionWithQuality("01.10.25 22:00", "02.10.25 06:00", SleepingQuality.GOOD)
        );

        BadSleepSessionsAnalysis analysis = new BadSleepSessionsAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(0L, result.getAmount());
    }

}
