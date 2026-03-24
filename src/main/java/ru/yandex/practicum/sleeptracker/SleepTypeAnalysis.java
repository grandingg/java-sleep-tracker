package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SleepTypeAnalysis implements SleepAnalyzer<SleepType> {

    private boolean isNight(SleepingSession session) {
        LocalDateTime start = session.getStart();
        LocalDateTime end = session.getEnd();

        LocalDateTime nightStart = end.toLocalDate().atTime(0, 0);
        LocalDateTime nightEnd = end.toLocalDate().atTime(6, 0);

        return end.isAfter(nightStart) && start.isBefore(nightEnd);
    }

    private boolean isOwl(SleepingSession session) {
        LocalTime start = session.getStart().toLocalTime();
        LocalTime end = session.getEnd().toLocalTime();

        return start.isAfter(LocalTime.of(23, 0)) &&
                end.isAfter(LocalTime.of(9, 0));

    }

    private boolean isLark(SleepingSession session) {
        LocalTime start = session.getStart().toLocalTime();
        LocalTime end = session.getEnd().toLocalTime();

        return start.isBefore(LocalTime.of(22, 0)) &&
                end.isBefore(LocalTime.of(7, 0));
    }

    @Override
    public SleepAnalysisResult<SleepType> analyze(List<SleepingSession> sessions) {

        String description = "Ваш хронотип: ";

        if (sessions == null || sessions.isEmpty()) {
            return new SleepAnalysisResult<>(description, SleepType.DOVE);
        }

        long owl = sessions.stream()
                .filter(this::isNight)
                .filter(this::isOwl)
                .count();

        long lark = sessions.stream()
                .filter(this::isNight)
                .filter(this::isLark)
                .count();

        long dove = sessions.stream()
                .filter(this::isNight)
                .count() - owl - lark;

        SleepType nightType;

        if (owl > lark && owl > dove) {
            nightType = SleepType.OWL;
        } else if (lark > owl && lark > dove) {
            nightType = SleepType.LARK;
        } else {
            nightType = SleepType.DOVE;
        }

        return new SleepAnalysisResult<>(description, nightType);
    }
}
