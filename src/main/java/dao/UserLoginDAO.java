package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class UserLoginDAO {
    private final String URL = "jdbc:mysql://localhost:3306/funfun";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBCドライバーをロード
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // パスワードをハッシュ化する（BCryptを使用）
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // コスト12（推奨）
    }

    // ユーザー認証（BCryptは比較時にハッシュを生成）
    public boolean authenticateUser(String userId, String password) {
        String sql = "SELECT password FROM customers WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedHashedPass = rs.getString("password");
                System.out.println("デバッグ: userId=" + userId);
                System.out.println("デバッグ: 入力パスワード=" + password);
                System.out.println("デバッグ: DBのハッシュ=" + storedHashedPass);
                boolean isMatch = BCrypt.checkpw(password, storedHashedPass);
                System.out.println("デバッグ: パスワード一致=" + isMatch);
                return isMatch;
            } else {
                System.out.println("デバッグ: userId が見つかりません - " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // パスワード更新(ハッシュ化して保存)
    public boolean updatePassword(String userId, String newPassword) {
        String sql = "UPDATE customers SET password = ? WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hashPassword(newPassword)); // ハッシュ化して保存
            pstmt.setString(2, userId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
