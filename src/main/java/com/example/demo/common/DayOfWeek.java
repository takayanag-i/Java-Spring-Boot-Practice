package com.example.demo.common;

/**
 * 曜日の列挙子
 */
public enum DayOfWeek {
    MON("1", "月"), TUE("2", "火"), WED("3", "水"), THU("4", "木"), FRI("5", "金"), UNSET("",
            "");

    /** 曜日番号 */
    private final String num;
    /** 漢字 */
    private final String japanese;

    /**
     * コンストラクタ
     * 
     * @param num 曜日番号
     * @param japanese 漢字
     */
    private DayOfWeek(String num, String japanese) {
        this.num = num;
        this.japanese = japanese;
    }

    /*
     * ゲッタ
     */
    public String getNum() {
        return num;
    }

    public String getJapanese() {
        return japanese;
    }

    /**
     * 曜日番号をint型で返却する
     * 
     * @return int 曜日番号
     */
    public int getInt() {
        return Integer.parseInt(num);
    }

    /**
     * 曜日番号（String型）から曜日型に変換する
     * 
     * @param str String 曜日番号
     * @return 曜日型のインスタンス
     */
    public static DayOfWeek fromNum(String str) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getNum().equals(str)) {
                return day;
            }
        }

        throw new IllegalArgumentException(str + " は曜日型にできません。");
    }

    /**
     * 曜日番号（int型）から曜日型に変換する
     * 
     * @param num int 曜日番号
     * @return 曜日型のインスタンス
     */
    public static DayOfWeek fromNum(int num) {
        String str = String.valueOf(num);

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getNum().equals(str)) {
                return day;
            }
        }

        throw new IllegalArgumentException(num + " は曜日型にできません。");
    }

    /**
     * 漢字から曜日型に変換する
     * 
     * @param japanese 曜日の漢字
     * @return 曜日型のインスタンス
     */
    public static DayOfWeek fromJapanese(String japanese) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getJapanese().equals(japanese)) {
                return day;
            }
        }
        throw new IllegalArgumentException(japanese + " は曜日型にできません。");
    }
}
