package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class AverageDurationAnalysis implements SleepAnalyzer {

    @Override
    public SleepAnalysisResult<?> analyze(List<SleepingSession> sessions) {

        String description = "Средняя продолжительность сессии: ";

        double amount = sessions.stream()
                .mapToLong(session ->
                        Duration.between(session.getStart(), session.getEnd())
                                .toMinutes())
                .average()
                .orElse(0.0);

        return new SleepAnalysisResult<>(description, amount);
    }
}
