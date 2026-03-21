package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class MaxDurationAnalysis implements SleepAnalyzer {

    public SleepAnalysisResult<?> analyze(List<SleepingSession> sessions) {

        String description = "Максимальная продолжительность сессии: ";

        Long amount = sessions.stream()
                .map(session ->
                        Duration.between(session.getStart(), session.getEnd())
                                .toMinutes())
                .max(Long::compare)
                .orElse(0L);

        return new SleepAnalysisResult<>(description, amount);
    }
}
