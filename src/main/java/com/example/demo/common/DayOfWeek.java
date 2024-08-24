package com.example.demo.common;

/**
 * Enum representing the days of the week.
 */
public enum DayOfWeek {
    MON("1", "Mon"), TUE("2", "Tue"), WED("3", "Wed"), THU("4", "Thu"), FRI("5", "Fri"), UNSET("",
            "");

    /** Numeric abbreviation */
    private final String num;
    /** Kanji representation */
    private final String japanese;

    /**
     * Constructor
     * 
     * @param num Numeric abbreviation
     * @param japanese Kanji representation
     */
    private DayOfWeek(String num, String japanese) {
        this.num = num;
        this.japanese = japanese;
    }

    /*
     * Getters
     */
    public String getNum() {
        return num;
    }

    public String getJapanese() {
        return japanese;
    }

    /**
     * Returns the numeric abbreviation as an integer.
     * 
     * @return Numeric abbreviation as an int
     */
    public int getInt() {
        return Integer.parseInt(num);
    }

    /**
     * Converts a string to the corresponding DayOfWeek instance.
     * 
     * @param str Numeric abbreviation as a string
     * @return Corresponding DayOfWeek instance
     */
    public static DayOfWeek fromNum(String str) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getNum().equals(str)) {
                return day;
            }
        }

        throw new IllegalArgumentException(str + " cannot be converted to DayOfWeek.");
    }

    /**
     * Converts an integer to the corresponding DayOfWeek instance.
     * 
     * @param num Numeric abbreviation as an int
     * @return Corresponding DayOfWeek instance
     */
    public static DayOfWeek fromNum(int num) {
        String str = String.valueOf(num);

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getNum().equals(str)) {
                return day;
            }
        }

        throw new IllegalArgumentException(num + " cannot be converted to DayOfWeek.");
    }

    /**
     * Converts a Kanji string to the corresponding DayOfWeek instance.
     * 
     * @param japanese Kanji representation
     * @return Corresponding DayOfWeek instance
     */
    public static DayOfWeek fromJapanese(String japanese) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getJapanese().equals(japanese)) {
                return day;
            }
        }
        throw new IllegalArgumentException(japanese + " cannot be converted to DayOfWeek.");
    }
}
