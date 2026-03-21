package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TotalSessionsAnalysisTest {

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
    void shouldReturnCorrectTotalSessions() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 22:00", "02.10.25 06:00"),
                session("02.10.25 23:00", "03.10.25 07:00")
        );

        TotalSessionsAnalysis analysis = new TotalSessionsAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(2, result.getAmount());
    }


    @Test
    void shouldReturnZeroWhenNoSessions() {
        List<SleepingSession> sessions = List.of();

        TotalSessionsAnalysis analysis = new TotalSessionsAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(0, result.getAmount());
    }
}
