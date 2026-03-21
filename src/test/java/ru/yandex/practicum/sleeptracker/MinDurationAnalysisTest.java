package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class MinDurationAnalysisTest {

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
    void shouldReturnMinimumDuration() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 22:00", "02.10.25 06:00"),
                session("02.10.25 22:00", "02.10.25 23:00")
        );

        MinDurationAnalysis analysis = new MinDurationAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(60L, result.getAmount());
    }

    @Test
    void shouldReturnDurationWhenOnlyOneSession() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 22:00", "02.10.25 06:00")
        );

        MinDurationAnalysis analysis = new MinDurationAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(480L, result.getAmount());
    }

}
