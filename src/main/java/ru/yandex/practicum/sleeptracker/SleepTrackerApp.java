package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {

    public static void main(String[] args) throws IOException {
        List<SleepingSession> sessions = new ArrayList<>();

        try (FileReader reader = new FileReader("src/main/resources/sleep_log.txt");
             BufferedReader br = new BufferedReader(reader)) {

            sessions = br.lines()
                    .map(SleepTrackerApp::parseSession)
                    .toList();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        List<SleepAnalyzer> analysis = List.of(
                new TotalSessionsAnalysis(),
                new MinDurationAnalysis(),
                new MaxDurationAnalysis(),
                new AverageDurationAnalysis(),
                new BadSleepSessionsAnalysis(),
                new SleeplessNightsAnalysis(),
                new SleepTypeAnalysis()
        );

        List<SleepingSession> finalSessions = sessions;
        //Idea сказала так сделать(создать новый список)

        analysis.forEach(analyser -> {
            SleepAnalysisResult<?> result = analyser.analyze(finalSessions);
            Object value = result.getAmount();

            if (value instanceof Double) {
                System.out.println(
                        result.getDescription() +
                                String.format("%.2f", value)
                );
            } else {
                System.out.println(
                        result.getDescription() + value
                );
            }
        });

    }

    private static SleepingSession parseSession(String line) {
        String[] split = line.split(";");

        String startSplit = split[0];
        String endSplit = split[1];
        String qualitySplit = split[2];

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        LocalDateTime start = LocalDateTime.parse(startSplit, formatter);
        LocalDateTime end = LocalDateTime.parse(endSplit, formatter);
        SleepingQuality quality = SleepingQuality.valueOf(qualitySplit);

        return new SleepingSession(start, end, quality);
    }
}