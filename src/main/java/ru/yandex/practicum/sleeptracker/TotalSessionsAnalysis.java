package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class TotalSessionsAnalysis implements SleepAnalyzer {


    @Override
    public SleepAnalysisResult<?> analyze(List<SleepingSession> sessions) {

        int amount = sessions.size();
        String description = "Общее количество сессий: ";

        return new SleepAnalysisResult<>(description, amount);
    }
}
