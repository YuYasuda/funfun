package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.CartItem;

public class CartDAO {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/funfun";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	// セッションのカートデータをデータベースに統合
	public void mergeSessionCartToDB(int customerId, CartItem item) {
	    String sql = "INSERT INTO CART (CUSTOMER_ID, PRODUCT_ID, UNIT_PRICE, QUANTITY, SUBTOTAL, ADDED_AT) " +
	                 "VALUES (?, ?, ?, ?, ?, NOW()) " +
	                 "ON DUPLICATE KEY UPDATE " +
	                 "QUANTITY = QUANTITY + VALUES(QUANTITY), " +
	                 "SUBTOTAL = SUBTOTAL + VALUES(SUBTOTAL)";

	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, customerId);
	        pstmt.setInt(2, item.getProductId());
	        pstmt.setBigDecimal(3, item.getUnitPrice());
	        pstmt.setInt(4, item.getQuantity());
	        pstmt.setBigDecimal(5, item.getSubtotal());

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	// カートに商品があるかを確認
	public boolean hasItemsInCart(int customerId) {
	    String sql = "SELECT COUNT(*) FROM CART WHERE CUSTOMER_ID = ?";

	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, customerId);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0;  // カート内の商品数が1以上なら true
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	// すべてのカートデータを取得
	public List<Cart> getAllCarts() {
	    List<Cart> cartList = new ArrayList<>();
	    
	    String sql = "SELECT CART.CART_ID, CART.CUSTOMER_ID, CART.PRODUCT_ID, " +
	                 "PRODUCTS.PRODUCT_NAME, CUSTOMERS.USER_ID, CUSTOMERS.CUSTOMER_LAST_NAME, CUSTOMERS.CUSTOMER_FIRST_NAME, " +
	                 "CART.UNIT_PRICE, CART.QUANTITY, CART.SUBTOTAL, PRODUCTS.IMAGE_URL, CART.ADDED_AT " +
	                 "FROM CART " +
	                 "JOIN PRODUCTS ON CART.PRODUCT_ID = PRODUCTS.PRODUCT_ID " +
	                 "JOIN CUSTOMERS ON CART.CUSTOMER_ID = CUSTOMERS.CUSTOMER_ID";

	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        
	        while (rs.next()) {
	            Cart cart = new Cart();
	            cart.setCartId(rs.getInt("CART_ID"));
	            cart.setCustomerId(rs.getInt("CUSTOMER_ID"));
	            cart.setProductId(rs.getInt("PRODUCT_ID"));
	            cart.setProductName(rs.getString("PRODUCT_NAME"));
	            cart.setUserId(rs.getString("USER_ID"));
	            cart.setCustomerLastName(rs.getString("CUSTOMER_LAST_NAME"));
	            cart.setCustomerFirstName(rs.getString("CUSTOMER_FIRST_NAME"));
	            cart.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
	            cart.setQuantity(rs.getInt("QUANTITY"));
	            cart.setSubtotal(rs.getBigDecimal("SUBTOTAL"));
	            cart.setAddedAt(rs.getTimestamp("ADDED_AT"));
	            cart.setImageURL(rs.getString("IMAGE_URL"));

	            cartList.add(cart);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cartList;
	}

	
    public List<Cart> getCartByCustomerId(int customerId) {
        List<Cart> cartList = new ArrayList<>();

        String sql = "SELECT CART.CART_ID, " +
                     "CART.CUSTOMER_ID, " +
                     "CART.PRODUCT_ID, " +
                     "PRODUCTS.PRODUCT_NAME, " +
                     "CUSTOMERS.USER_ID, " +
                     "CUSTOMERS.CUSTOMER_LAST_NAME, " +
                     "CUSTOMERS.CUSTOMER_FIRST_NAME, " +
                     "CART.UNIT_PRICE, " +
                     "CART.QUANTITY, " +
                     "CART.SUBTOTAL, " +
                     "PRODUCTS.IMAGE_URL, " +
                     "CART.ADDED_AT " +
                     "FROM CART " +
                     "JOIN PRODUCTS ON CART.PRODUCT_ID = PRODUCTS.PRODUCT_ID " +
                     "JOIN CUSTOMERS ON CART.CUSTOMER_ID = CUSTOMERS.CUSTOMER_ID " +
                     "WHERE CART.CUSTOMER_ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("CART_ID"));
                cart.setCustomerId(rs.getInt("CUSTOMER_ID"));
                cart.setProductId(rs.getInt("PRODUCT_ID"));
                cart.setProductName(rs.getString("PRODUCT_NAME"));
                cart.setUserId(rs.getString("USER_ID"));
                cart.setCustomerLastName(rs.getString("CUSTOMER_LAST_NAME"));
                cart.setCustomerFirstName(rs.getString("CUSTOMER_FIRST_NAME"));
                cart.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
                cart.setQuantity(rs.getInt("QUANTITY"));
                cart.setSubtotal(rs.getBigDecimal("SUBTOTAL"));
                cart.setAddedAt(rs.getTimestamp("ADDED_AT"));
                cart.setImageURL(rs.getString("IMAGE_URL"));

                cartList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }

    // データベースに新しいカートを追加するメソッド
    public void insertCart(Cart cart) {
        String sql = "INSERT INTO CART (CUSTOMER_ID, PRODUCT_ID, UNIT_PRICE, QUANTITY, SUBTOTAL, REMARKS) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cart.getCustomerId());
            pstmt.setInt(2, cart.getProductId());
            pstmt.setBigDecimal(3, cart.getUnitPrice());
            pstmt.setInt(4, cart.getQuantity());
            pstmt.setBigDecimal(5, cart.getSubtotal());
            pstmt.setString(6, cart.getRemarks());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // データベースの既存カートを更新するメソッド
    public void updateCart(Cart cart) {
        String sql = "UPDATE CART SET CUSTOMER_ID = ?, PRODUCT_ID = ?, UNIT_PRICE = ?, QUANTITY = ?, SUBTOTAL = ?, ADDED_AT = ?, REMARKS = ? WHERE CART_ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cart.getCustomerId());
            pstmt.setInt(2, cart.getProductId());
            pstmt.setBigDecimal(3, cart.getUnitPrice());
            pstmt.setInt(4, cart.getQuantity());
            pstmt.setBigDecimal(5, cart.getSubtotal());
            pstmt.setTimestamp(6, cart.getAddedAt());
            pstmt.setString(7, cart.getRemarks());
            pstmt.setInt(8, cart.getCartId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
