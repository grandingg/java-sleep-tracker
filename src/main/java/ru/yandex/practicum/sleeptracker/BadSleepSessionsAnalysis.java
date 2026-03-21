package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadSleepSessionsAnalysis implements SleepAnalyzer {

    public SleepAnalysisResult<?> analyze(List<SleepingSession> sessions) {

        String description = "Количество сессий с плохим качество сна: ";

        long amount = sessions.stream()
                .filter(session -> session.getQuality() == SleepingQuality.BAD)
                .count();

        return new SleepAnalysisResult<>(description, amount);
    }
}
