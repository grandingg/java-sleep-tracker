package ru.yandex.practicum.sleeptracker;

public enum SleepType {

    OWL("Сова"),
    LARK("Жаворонок"),
    DOVE("Голубь");

    private final String displayName;

    SleepType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
