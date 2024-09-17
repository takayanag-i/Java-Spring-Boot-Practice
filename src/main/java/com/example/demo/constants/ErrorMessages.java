package com.example.demo.constants;

/**
 * エラーメッセージの定数クラス
 */
public class ErrorMessages {

    /*
     * バリデーション
     */

    /** 出席番号の必須チェック違反 */
    public static final String REQUIRED_STUDENT_ID = "出席番号を入力してください。";

    /** 出席番号の形式違反 */
    public static final String FORMAT_STUDENT_ID = "出席番号は4桁の半角数字で入力してください。";

    /** パスワードの必須チェック違反 */
    public static final String REQUIRED_PASSWORD = "パスワードを入力してください。";

    /** パスワードの形式違反 */
    public static final String FORMAT_PASSWORD = "パスワードは63文字以内の半角英数および記号(!@#$%^&*())で入力してください。";

    /** ユーティリティクラスをインスタンス化しようとした場合のエラー */
    public static final String UTIL_NEW_ERROR = "ユーティリティクラスをnewしないでください。";


    /*
     * 業務エラー
     */

    /** 登録しようとする出席番号が重複した場合のエラー */
    public static final String DUPLICATE_STUDENT_ID = "重複する出席番号です。";

    /** 登録しようとするメールアドレスが重複した場合のエラー */
    public static final String DUPLICATE_EMAIL = "重複するメールアドレスです。";

    /** ログインの失敗 */
    public static final String LOGIN_FAILED = "ログインに失敗しました。";

    /** 講座が存在しない場合のエラー */
    public static final String DRIVER_NO_SUCH_COURSE = "講座が存在しません。";

    /** 重複する曜日・時限の履修を登録しようとした場合のエラー */
    public static final String DUPLICATE_ENROLLMENT = "曜日・時限が重複しています。";

    /*
     * サービスクラス
     */

    /** 履修の抹消における予期しないエラー */
    public static final String DRIVER_DELETE_ERROR = "履修の抹消における予期しないエラーが発生しました。";

    /** 履修登録状況の表示に関する予期しないエラー */
    public static final String DRIVER_DISPLAY_ERROR = "履修登録状況の表示に関する予期しないエラーが発生しました。";

    /** 履修登録における予期しないエラー */
    public static final String DRIVER_ENROLLMENT_ERROR = "履修登録における予期しないエラーが発生しました。";

    /** ログインにおける予期しないエラー */
    public static final String DRIVER_LOGIN_ERROR = "ログインにおける予期しないエラーが発生しました。";

    /** 検索実行における予期しないエラー */
    public static final String DRIVER_SEARCH_ERROR = "検索実行における予期しないエラーが発生しました。";

    /** 新規学生登録における予期しないエラー */
    public static final String DRIVER_SIGNUP_ERROR = "新規学生登録における予期しないエラーが発生しました。";

    /**
     * このクラスのインスタンス生成を防ぐためのプライベートコンストラクタ
     */
    private ErrorMessages() {
        throw new IllegalStateException(UTIL_NEW_ERROR);
    }
}
