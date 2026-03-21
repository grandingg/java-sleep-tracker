package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SleeplessNightsAnalysisTest {

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
    void shouldReturnZeroWhenEveryNightHasSleep() {

        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:00", "02.10.25 06:00"),
                session("02.10.25 23:00", "03.10.25 06:00"),
                session("03.10.25 23:00", "04.10.25 06:00")
        );

        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(0L, result.getAmount());
    }

    @Test
    void shouldCountOneSleeplessNight() {

        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:00", "02.10.25 06:00"),
                session("03.10.25 23:00", "04.10.25 06:00")
        );

        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals(1L, result.getAmount());
    }
}

