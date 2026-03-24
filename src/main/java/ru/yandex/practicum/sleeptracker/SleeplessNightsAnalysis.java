package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SleeplessNightsAnalysis implements SleepAnalyzer<Long> {

    @Override
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {

        String description = "Количество бессонных ночей: ";

        if (sessions == null || sessions.isEmpty()) {
            return new SleepAnalysisResult<>(description, 0L);
        }

        SleepingSession firstSession = sessions.getFirst();
        SleepingSession lastSession = sessions.getLast();

        LocalDate firstDate = firstSession.getStart().toLocalDate();
        LocalDate lastDate = lastSession.getEnd().toLocalDate();

        if (firstSession.getStart().getHour() >= 12) {
            firstDate = firstDate.plusDays(1);
        }

        long sleeplessNights = 0;
        LocalDate currentDate = firstDate;

        //Я тут, к сожалению, уже не смогла заменить цикл(((
        while (currentDate.isBefore(lastDate)) {
            LocalDateTime nightStart = currentDate.atTime(0, 0);
            LocalDateTime nightEnd = currentDate.atTime(6, 0);

            boolean hasSleep = sessions.stream()
                    .anyMatch(session -> session.getEnd().isAfter(nightStart) &&
                            session.getStart().isBefore(nightEnd)
                    );
            if (!hasSleep) {
                sleeplessNights++;
            }

            currentDate = currentDate.plusDays(1);
        }

        //.filter(session -> session.getEnd().isAfter(nightStart) &&
        //      session.getStart().isBefore(nightEnd))
        //    .findAny()
        //  .isPresent();
        //Я сначала написала, так, но idea исправила на anyMatch

        return new SleepAnalysisResult<>(description, sleeplessNights);
    }
}
