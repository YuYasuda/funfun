package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class OrderDetailDAO {

    // データベース接続を取得
    private final String URL = "jdbc:mysql://localhost:3306/funfun";
    private final String USER = "root";
    private final String PASSWORD = "";

    
    private Connection getConnection() throws SQLException {
        try {
            // MySQL JDBCドライバを手動でロード
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 販売数ランキングを取得するメソッド（カテゴリを問わない）
    public List<Product> getSalesRanking() {
        List<Product> productList = new ArrayList<>();
        
        // 注文がキャンセルされていないものを対象にしたSQLクエリ
        String sql = "SELECT p.product_id, p.product_name, p.price, p.image_url, SUM(od.quantity) AS total_quantity "
                   +  "FROM order_detail od "
                   +  "JOIN products p ON od.product_id = p.product_id "
                   +  "JOIN orders_summary os ON od.order_id = os.order_id "  // ordersテーブルと結合
                   +  "WHERE os.order_status != 'キャンセル' " // オーダーステータスがキャンセルでないものを対象
                   +  "GROUP BY od.product_id "  // 商品ごとにグループ化
                   +  "ORDER BY total_quantity DESC"; // 販売数で降順ソート

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // 商品ごとの販売数ランキングを取得
                Product product = new Product(
                    rs.getInt("product_id"),             // 商品ID
                    rs.getString("product_name"),        // 商品名
                    rs.getBigDecimal("price"),           // 価格
                    rs.getString("image_url"),
                    rs.getInt("total_quantity")         // 合計販売数
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // ログ記録等を考慮すると良い
        }

        return productList; // ランキング結果を返す
    }
    
// 大分類カテゴリごとの販売数ランキングを取得するメソッド
    public List<Product> getSalesRankingByCategory(int categoryStart, int categoryEnd) {
        List<Product> productList = new ArrayList<>();
        
        // 大分類カテゴリごとの販売数ランキングを取得
        String sql = "SELECT c.category_id, c.category_name, p.product_id, p.product_name, p.price, p.image_url, "
                   + "SUM(od.quantity) AS total_quantity "
                   + "FROM order_detail od "
                   + "JOIN products p ON od.product_id = p.product_id "
                   + "JOIN product_categories pc ON p.product_id = pc.product_id "
                   + "JOIN categories c ON pc.category_id = c.category_id "
                   + "JOIN orders_summary os ON od.order_id = os.order_id "
                   + "WHERE os.order_status != 'キャンセル' "
                   + "AND c.category_id BETWEEN ? AND ? "
                   + "GROUP BY c.category_id, p.product_id "
                   + "ORDER BY total_quantity DESC";  // 販売数順にソート

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryStart);
            stmt.setInt(2, categoryEnd);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getBigDecimal("price"),
                        rs.getString("image_url"),
                        rs.getInt("total_quantity") // 合計販売数
                    );
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
