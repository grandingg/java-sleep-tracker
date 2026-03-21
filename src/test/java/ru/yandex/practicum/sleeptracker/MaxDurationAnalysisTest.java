package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class MaxDurationAnalysisTest {

    private SleepingSession session(String start, String end) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        return new SleepingSession(
                LocalDateTime.parse(start, formatter),
                LocalDateTime.parse(end, formatter),
                SleepingQuality.GOOD
        );
    }

    @Test
    void shouldReturnMaximumDuration() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 22:00", "02.10.25 06:00"),
                session("02.10.25 22:00", "03.10.25 08:00")
        );

        MaxDurationAnalysis analysis = new MaxDurationAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(600L, result.getAmount());
    }

    @Test
    void shouldReturnZeroWhenNoSessionsForMax() {
        List<SleepingSession> sessions = List.of();

        MaxDurationAnalysis analysis = new MaxDurationAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(0L, result.getAmount());
    }

}
