package model;

public class UserLogin {
    private String userId;  // ログイン用のユーザーID
    private String hashedPass;  // ハッシュ化されたパスワード

    public UserLogin(String userId, String hashedPass) {
        this.userId = userId;
        this.hashedPass = hashedPass;
    }

    public String getUserId() { return userId; }
    public String getHashedPass() { return hashedPass; }
}
