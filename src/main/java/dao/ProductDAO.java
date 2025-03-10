package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO extends BaseDAO {

    // 全体ランキングTOP15取得
    public List<Product> findTopSellingProducts(int limit) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, SUM(od.quantity) AS total_sold " +
                     "FROM products p " +
                     "JOIN order_details od ON p.product_id = od.product_id " +
                     "GROUP BY p.product_id " +
                     "ORDER BY total_sold DESC " +
                     "LIMIT ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getString("image_url"),
                    rs.getInt("stock"),
                    "",
                    rs.getInt("total_sold")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // ユーザーの購入履歴からTOP15取得
    public List<Product> findUserTopSellingProducts(int customerId, int limit) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, SUM(od.quantity) AS total_sold " +
                     "FROM products p " +
                     "JOIN order_details od ON p.product_id = od.product_id " +
                     "JOIN orders o ON od.order_id = o.order_id " +
                     "WHERE o.customer_id = ? " +
                     "GROUP BY p.product_id " +
                     "ORDER BY total_sold DESC " +
                     "LIMIT ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, limit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getString("image_url"),
                    rs.getInt("stock"),
                    "",
                    rs.getInt("total_sold")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

	
    /**
     * カテゴリー名で商品を検索する
     * @param categoryName カテゴリー名 (例: "魚", "牛肉", "野菜")
     * @return 指定したカテゴリーの商品リスト
     */
    public List<Product> findProductsByCategoryName(String categoryName) {
        String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, p.remarks, "
                   + "c.category_id, c.category_name "
                   + "FROM products p "
                   + "LEFT JOIN product_categories pc ON p.product_id = pc.product_id "
                   + "LEFT JOIN categories c ON pc.category_id = c.category_id "
                   + "WHERE c.category_name = ? "//category_idではなくcategory_nameで検索する
                   + "ORDER BY p.product_id"; // 商品ID順で並べ替え

        List<Product> productList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryName);  // カテゴリー名で検索

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productList.add(mapResultSetToProduct(rs));  // 商品情報をリストに追加
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    
//    中分類検索用のメソッド
    public List<Product> findProductsBySubCategory(int subCategoryId) {
        // 中分類IDが指定された場合、そのIDに基づいて商品を検索
        String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, p.remarks, "
                   + "c.category_id, c.category_name "
                   + "FROM products p "
                   + "LEFT JOIN product_categories pc ON p.product_id = pc.product_id "
                   + "LEFT JOIN categories c ON pc.category_id = c.category_id "
                   + "WHERE c.category_id = ? "
                   + "ORDER BY p.product_id";  // 商品ID順で並べ替え

        List<Product> productList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, subCategoryId);  // 中分類IDをそのままセット

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                productList.add(mapResultSetToProduct(rs));  // 商品情報をリストに追加
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


//    検索窓の検索メソッド
    public List<Product> searchProductsByNDR(String searchQuery) {
        // 商品名Name、説明Description、備考Remarksによる検索（頭文字をとってNDRです）

        // ひらがな→カタカナ変換
        String normalizedQuery = convertHiraganaToKatakana(searchQuery);  // 正規化処理を追加

        String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, p.remarks, "
                   + "c.category_id, c.category_name "
                   + "FROM products p "
                   + "LEFT JOIN product_categories pc ON p.product_id = pc.product_id "
                   + "LEFT JOIN categories c ON pc.category_id = c.category_id "
                   + "WHERE p.product_name LIKE ? OR p.description LIKE ? OR p.remarks LIKE ?";

        List<Product> productList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 正規化された検索クエリに部分一致を適用
            String searchPattern = "%" + normalizedQuery + "%";  // 検索クエリに部分一致を適用

            pstmt.setString(1, searchPattern); // 商品名に部分一致
            pstmt.setString(2, searchPattern); // 説明に部分一致
            pstmt.setString(3, searchPattern); // 備考に部分一致

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                productList.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    // ひらがな → カタカナに変換するメソッド
    private String convertHiraganaToKatakana(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            // ひらがなをカタカナに変換
            if (c >= 'あ' && c <= 'ん') {
                sb.setCharAt(i, (char)(c - 'あ' + 'ア'));
            }
        }
        return sb.toString();
    }

 // 商品IDで商品情報とカテゴリ情報を取得（管理者用商品ID検索）
    public Product findProductById(int productId) {
        String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, p.remarks,"
                   + "c.category_id, c.category_name "
                   + "FROM products p "
                   + "LEFT JOIN product_categories pc ON p.product_id = pc.product_id "
                   + "LEFT JOIN categories c ON pc.category_id = c.category_id "
                   + "WHERE p.product_id = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId); // 商品IDを設定
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
// 商品一覧を取得
    public List<Product> findAllProducts() {
        String sql = "SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, p.remarks, "
                   + "c.category_id, c.category_name "
                   + "FROM products p "
                   + "LEFT JOIN product_categories pc ON p.product_id = pc.product_id "
                   + "LEFT JOIN categories c ON pc.category_id = c.category_id";

        List<Product> productList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                productList.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

//管理者用検索
    public List<Product> searchProductsByCriteria(String productName, String description, String remarks, 
            BigDecimal minPrice, BigDecimal maxPrice, int minStock, int maxStock, 
            String categoryName) {
				StringBuilder sql = new StringBuilder("SELECT p.product_id, p.product_name, p.description, p.price, p.image_url, p.stock, p.remarks, "
				     + "c.category_id, c.category_name FROM products p "
				     + "LEFT JOIN product_categories pc ON p.product_id = pc.product_id "
				     + "LEFT JOIN categories c ON pc.category_id = c.category_id WHERE 1=1 ");
		
		// 検索条件を動的に追加
		if (productName != null && !productName.isEmpty()) {sql.append("AND p.product_name LIKE ? ");}
		if (description != null && !description.isEmpty()) {sql.append("AND p.description LIKE ? ");}
		if (remarks != null && !remarks.isEmpty()) {sql.append("AND p.remarks LIKE ? ");}
		if (minPrice != null) {sql.append("AND p.price >= ? ");}
		if (maxPrice != null) {sql.append("AND p.price <= ? ");}
		if (minStock >= 0) {sql.append("AND p.stock >= ? ");}
		if (maxStock >= 0) {sql.append("AND p.stock <= ? ");}
		if (categoryName != null && !categoryName.isEmpty()) {sql.append("AND c.category_name LIKE ? ");}
		
		sql.append("ORDER BY p.product_id");  // 結果をID順でソート
		
		List<Product> productList = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
		int paramIndex = 1;
		
		// パラメータを順番に設定
		if (productName != null && !productName.isEmpty()) {pstmt.setString(paramIndex++, "%" + productName + "%");}
		if (description != null && !description.isEmpty()) {pstmt.setString(paramIndex++, "%" + description + "%");}
		if (remarks != null && !remarks.isEmpty()) {pstmt.setString(paramIndex++, "%" + remarks + "%");}
		if (minPrice != null) {pstmt.setBigDecimal(paramIndex++, minPrice);}
		if (maxPrice != null) {pstmt.setBigDecimal(paramIndex++, maxPrice);}
		if (minStock >= 0) {pstmt.setInt(paramIndex++, minStock);}
		if (maxStock >= 0) {pstmt.setInt(paramIndex++, maxStock);}
		if (categoryName != null && !categoryName.isEmpty()) {pstmt.setString(paramIndex++, "%" + categoryName + "%");}
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
		productList.add(mapResultSetToProduct(rs));  // 商品情報をリストに追加
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return productList;
		}
		
// 商品登録（カテゴリは既存のを使う）
    public Product addProduct(Product product) {
        String query = "INSERT INTO products (product_name, description, price, image_url, stock, remarks) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // トランザクション管理

            // 商品の基本情報を挿入
            try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setBigDecimal(3, product.getPrice());
                preparedStatement.setString(4, product.getImageUrl());
                preparedStatement.setInt(5, product.getStock());
                preparedStatement.setString(6, product.getRemarks());
                preparedStatement.executeUpdate();

                // 生成された商品IDを取得
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                int generatedProductId = 0;
                if (generatedKeys.next()) {
                    generatedProductId = generatedKeys.getInt(1);
                }

                // カテゴリIDの確認
                String categoryCheckQuery = "SELECT COUNT(*) FROM categories WHERE category_id = ?";
                try (PreparedStatement categoryCheckPstmt = conn.prepareStatement(categoryCheckQuery)) {
                    categoryCheckPstmt.setInt(1, product.getCategoryId());  // 入力されたカテゴリIDを設定
                    ResultSet rs = categoryCheckPstmt.executeQuery();

                    if (rs.next() && rs.getInt(1) > 0) {
                        // カテゴリIDが存在する場合、商品カテゴリテーブルに挿入
                        String categoryQuery = "INSERT INTO product_categories (product_id, category_id) VALUES (?, ?)";
                        try (PreparedStatement categoryPstmt = conn.prepareStatement(categoryQuery)) {
                            categoryPstmt.setInt(1, generatedProductId);  // 新しく作成した商品のID
                            categoryPstmt.setInt(2, product.getCategoryId());  // 商品に関連付けるカテゴリID
                            categoryPstmt.executeUpdate();
                        }
                    } else {
                        throw new SQLException("指定されたカテゴリIDは存在しません。");
                    }
                }
                
                // コミットして変更を保存
                conn.commit();

                // 新しく作成した商品を返す
                return new Product(
                    generatedProductId,  // 生成されたIDをコンストラクタでセット
                    product.getProductName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getImageUrl(),
                    product.getStock(),
                    product.getRemarks(),
                    product.getCategoryId()
                );

            } catch (SQLException e) {
                e.printStackTrace();
                if (conn != null) {
                    conn.rollback();  // エラーが発生した場合はロールバック
                }
                throw new SQLException("商品追加処理でエラーが発生しました。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("データベース接続に失敗しました。");
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // 自動コミットに戻す
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // 商品を更新
    public void updateProduct(Product product) {
        String query = "UPDATE products SET product_name = ?, description = ?, price = ?, image_url = ?, stock = ?, remarks = ? WHERE product_id = ?";

        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setString(4, product.getImageUrl());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setString(6, product.getRemarks());
            preparedStatement.setInt(7, product.getProductId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 商品を削除
    public void deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE product_id = ?";

        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ResultSetをProductオブジェクトにマッピングするヘルパーメソッド
    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        String imageUrl = resultSet.getString("image_url");
        int stock = resultSet.getInt("stock");
        String remarks = resultSet.getString("remarks");
        int categoryId = resultSet.getInt("category_id");

        return new Product(productId, productName, description, price, imageUrl, stock, remarks, categoryId);
    }
    
    
}
