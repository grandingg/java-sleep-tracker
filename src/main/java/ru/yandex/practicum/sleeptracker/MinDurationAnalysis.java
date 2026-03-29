package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class MinDurationAnalysis implements SleepAnalyzer<Long> {

    @Override
    public SleepAnalysisResult<?> analyze(List<SleepingSession> sessions) {

        String description = "Минимальная продолжительность сессии: ";

        long amount = sessions.stream()
                .map(session ->
                        Duration.between(session.getStart(), session.getEnd())
                                .toMinutes())
                .min(Long::compare)
                .orElse(0L);

        return new SleepAnalysisResult<>(description, amount);
    }
}
