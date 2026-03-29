package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult<T> {
    private final String description;
    private final T amount;

    public SleepAnalysisResult(String description, T amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public T getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        if (amount instanceof Double doubleValue) {
            return description + String.format("%.2f", doubleValue);
        }
        return description + amount;
    }
}
