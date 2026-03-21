package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTypeAnalysisTest {

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
    void shouldReturnOwlWhenMostNightsAreOwls() {

        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:30", "02.10.25 09:30"),
                session("02.10.25 23:40", "03.10.25 09:20"),
                session("03.10.25 21:00", "04.10.25 06:00")
        );

        SleepTypeAnalysis analysis = new SleepTypeAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals("Сова", result.getAmount());
    }

    @Test
    void shouldReturnOwlWhenOnlyOwlsExist() {

        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:30", "02.10.25 09:30"),
                session("02.10.25 23:40", "03.10.25 09:20")
        );

        SleepTypeAnalysis analysis = new SleepTypeAnalysis();
        SleepAnalysisResult<?> result = analysis.analyze(sessions);

        assertEquals("Сова", result.getAmount());
    }
}
