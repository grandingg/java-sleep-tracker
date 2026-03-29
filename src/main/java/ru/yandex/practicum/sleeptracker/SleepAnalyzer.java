package ru.yandex.practicum.sleeptracker;

import java.util.List;

public interface SleepAnalyzer<T> {
    SleepAnalysisResult<?> analyze(List<SleepingSession> sessions);

}
