package model;

public class Inquiry {
    private String category; // 問い合わせカテゴリ
    private String message;  // 問い合わせ内容
    private String email;    // 返信用メールアドレス
    private String confirmEmail; // 確認用メールアドレス

    // コンストラクタ
    public Inquiry(String category, String message, String email, String confirmEmail) {
        this.category = category;
        this.message = message;
        this.email = email;
        this.confirmEmail = confirmEmail;
    }

    // ゲッターとセッター
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    // 返信用メールアドレスが一致するか確認
    public boolean isEmailValid() {
        if (this.email == null || this.confirmEmail == null) {
            return false; // どちらかがnullの場合は不正
        }
        return this.email.equals(this.confirmEmail);
    }

}
