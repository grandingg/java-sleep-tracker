package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class AverageDurationAnalysisTest {

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
    void shouldReturnAverageDuration() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 22:00", "02.10.25 06:00"),
                session("02.10.25 22:00", "03.10.25 06:00")
        );

        AverageDurationAnalysis analysis = new AverageDurationAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(480.0, (Double) result.getAmount());
    }

    @Test
    void shouldReturnCorrectAverageWhenDifferentDurations() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 22:00", "02.10.25 06:00"),
                session("02.10.25 22:00", "02.10.25 23:00")
        );

        AverageDurationAnalysis analysis = new AverageDurationAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(270.0, (Double) result.getAmount());
    }

}
