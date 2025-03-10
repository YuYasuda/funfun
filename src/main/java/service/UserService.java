package service;

import dao.UserLoginDAO;

public class UserService {
    private UserLoginDAO userLoginDAO = new UserLoginDAO();

    // ユーザー認証
    public boolean login(String userId, String password) {
        System.out.println("【Serviceデバッグ】受け取った userId=" + userId);
        System.out.println("【Serviceデバッグ】受け取った password=" + password);

        boolean result = userLoginDAO.authenticateUser(userId, password);

        System.out.println("【Serviceデバッグ】DAOの認証結果: " + result);
        return result;
    }
    // パスワード変更
    public boolean changePassword(String userId, String newPassword) {
        return userLoginDAO.updatePassword(userId, newPassword);
    }
}
