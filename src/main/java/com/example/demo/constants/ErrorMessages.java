package com.example.demo.constants;

/**
 * A utility class that holds error message constants used throughout the application. The content
 * is in Japanese. This class should not be instantiated.
 */
public class ErrorMessages {

    /** Error message for when a DELETE query fails. */
    public static final String DAO_DELETE_ERROR = "DELETEクエリの実行中にエラーが発生しました。";

    /** Error message for when an INSERT query fails. */
    public static final String DAO_INSERT_ERROR = "INSERTクエリの実行中にエラーが発生しました。";

    /** Error message for when a SELECT query fails. */
    public static final String DAO_SELECT_ERROR = "SELECTクエリの実行中にエラーが発生しました。";

    /** Error message for an unexpected error during enrollment deletion. */
    public static final String DRIVER_DELETE_ERROR = "履修の抹消における予期しないエラーが発生しました。";

    /** Error message for an unexpected error during enrollment status display. */
    public static final String DRIVER_DISPLAY_ERROR = "履修登録状況の表示に関して予期しないエラーが発生しました。";

    /** Error message for an unexpected error during enrollment registration. */
    public static final String DRIVER_ENROLLMENT_ERROR = "履修登録における予期しないエラーが発生しました。";

    /** Error message for an unexpected error during login. */
    public static final String DRIVER_LOGIN_ERROR = "ログインにおける予期しないエラーが発生しました。";

    /** Error message for when a specified course does not exist. */
    public static final String DRIVER_NO_SUCH_COURSE = "講座が存在しません。";

    /** Error message for an unexpected error during a search operation. */
    public static final String DRIVER_SEARCH_ERROR = "検索実行における予期しないエラーが発生しました。";

    /** Error message for an unexpected error during student signup. */
    public static final String DRIVER_SIGNUP_ERROR = "新規学生登録における予期しないエラーが発生しました。";

    /** Error message for when an enrollment period overlaps with another. */
    public static final String DUPLICATE_ENROLLMENT = "曜日・時限が重複しています。";

    /** Error message for when a student ID is duplicated. */
    public static final String DUPLICATE_STUDENT_ID = "重複する出席番号です。";

    /** Error message for when an email address is duplicated. */
    public static final String DUPLICATE_EMAIL = "重複するメールアドレスです。";

    /** Error message for when login fails. */
    public static final String LOGIN_FAILED = "ログインに失敗しました。";

    /** Error message for when the utility class is instantiated. */
    public static final String UTIL_NEW_ERROR = "ユーティリティクラスをnewしないでください。";

    /**
     * Private constructor to prevent instantiation of this utility class. Throws an
     * IllegalStateException if an attempt to instantiate is made.
     */
    private ErrorMessages() {
        throw new IllegalStateException(UTIL_NEW_ERROR);
    }
}
