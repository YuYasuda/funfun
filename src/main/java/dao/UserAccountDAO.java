package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.UserAccount;

public class UserAccountDAO {
    private final String URL = "jdbc:mysql://localhost:3306/funfun";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ユーザー情報の取得
    public UserAccount findUserById(int customerId) {
        String sql = "SELECT c.customer_id, c.user_id, c.customer_last_name, c.customer_first_name, c.phone_number, c.email, c.birth, c.gender, c.password, c.remarks, "
                   + "s.shipping_post_code, s.shipping_address_1, s.shipping_address_2 "
                   + "FROM customers c "
                   + "LEFT JOIN shipping_address s ON c.customer_id = s.customer_id "
                   + "WHERE c.customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new UserAccount(
                    rs.getInt("customer_id"),
                    rs.getString("customer_last_name"),
                    rs.getString("customer_first_name"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("birth"),
                    rs.getString("gender"),
                    rs.getString("password"),
                    rs.getString("remarks"),
                    rs.getString("shipping_post_code"),  // 追加
                    rs.getString("shipping_address_1"),  // 追加
                    rs.getString("shipping_address_2")   // 追加
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ユーザー登録
    public boolean createUser(UserAccount user) {
        String sql = "INSERT INTO customers (user_id, customer_last_name, customer_first_name, phone_number, email, birth, gender, password, remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getCustomerLastName());
            pstmt.setString(3, user.getCustomerFirstName());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getBirth());
            pstmt.setString(7, user.getGender());
            pstmt.setString(8, user.getPassword());
            pstmt.setString(9, user.getRemarks());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // shipping_addressに登録
public boolean createUserWithShipping(UserAccount user) {
    String customerSql = "INSERT INTO customers (user_id, customer_last_name, customer_first_name, phone_number, email, birth, gender, password, remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String shippingSql = "INSERT INTO shipping_address (customer_id, shipping_last_name, shipping_first_name, state, city, phone_number, shipping_post_code, shipping_address_1, shipping_address_2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = getConnection()) {
        conn.setAutoCommit(false);

        // Statementをインポートしておく。AUTO_INCREMENTで作られるcustomer_idを取得する。
        try (PreparedStatement pstmt = conn.prepareStatement(customerSql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getCustomerLastName());
            pstmt.setString(3, user.getCustomerFirstName());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getBirth());
            pstmt.setString(7, user.getGender());
            pstmt.setString(8, user.getPassword());
            pstmt.setString(9, user.getRemarks());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int customerId = rs.getInt(1); 

                try (PreparedStatement shippingStmt = conn.prepareStatement(shippingSql)) {
                    shippingStmt.setInt(1, customerId);
                    shippingStmt.setString(2, user.getCustomerLastName());
                    shippingStmt.setString(3, user.getCustomerFirstName());
                    shippingStmt.setString(4, user.getState());
                    shippingStmt.setString(5, user.getCity());
                    shippingStmt.setString(6, user.getShippingPhoneNumber());
                    shippingStmt.setString(7, user.getShippingPostCode());
                    shippingStmt.setString(8, user.getShippingAddress1());
                    shippingStmt.setString(9, user.getShippingAddress2());
                    shippingStmt.executeUpdate();
                }
            }
        }
        conn.commit();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    // user_idを元にcustomer_idを取得する
    public int getCustomerIdByUserId(String userId) {
        String sql = "SELECT customer_id FROM customers WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // エラー時は -1 を返す
    }
    
 // user_id を元にハッシュ化されたパスワードを取得する
    public String getHashedPassword(String userId) {
        String sql = "SELECT password FROM customers WHERE user_id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("password");  // ハッシュ化されたパスワードを返す
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // ユーザーが見つからない場合
    }


}
