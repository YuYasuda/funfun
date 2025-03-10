package model;

import java.math.BigDecimal;
import java.util.List;

import dao.ProductDAO;

public class ProductLogic {

    private ProductDAO productDAO;

    public ProductLogic() {
        productDAO = new ProductDAO();  // DAOをインスタンス化
    }

    // 商品一覧を取得するメソッド
    public List<Product> getAllProducts() {
        return productDAO.findAllProducts();  // DAOのメソッドを呼び出す
    }

    // 商品IDで商品情報を取得
    public Product getProductById(int productId) {
        return productDAO.findProductById(productId);  // 商品IDで検索
    }

    // 中分類IDに基づく商品検索
    public List<Product> getProductsBySubCategory(int subCategoryId) {
        return productDAO.findProductsBySubCategory(subCategoryId);  // 中分類IDで検索
    }

    // 検索窓からのクエリによる商品検索
    public List<Product> searchProducts(String searchQuery) {
        return productDAO.searchProductsByNDR(searchQuery);  // 商品名、説明、備考を検索
    }

    // 商品の追加
    public Product addProduct(Product product) throws Exception {
        // 追加前にバリデーションを実行することも可能
        validateProduct(product);

        return productDAO.addProduct(product);  // DAOのaddProductメソッドを呼び出し
    }

    // 商品の更新
    public void updateProduct(Product product) throws Exception {
        // 更新前にバリデーションを実行
        validateProduct(product);

        productDAO.updateProduct(product);  // DAOのupdateProductメソッドを呼び出し
    }

    // 商品の削除
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);  // DAOのdeleteProductメソッドを呼び出し
    }

    // 商品のバリデーション（ビジネスルールに基づく検証）
    private void validateProduct(Product product) throws Exception {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new Exception("商品名は必須です。");
        }

        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("価格は正の数でなければなりません。");
        }

        if (product.getStock() < 0) {
            throw new Exception("在庫数は0以上でなければなりません。");
        }

        // その他のビジネスロジックをここに追加することができます
    }

    // 検索条件による商品検索（管理者用）
    public List<Product> searchProductsByCriteria(String productName, String description, String remarks,
                                                  BigDecimal minPrice, BigDecimal maxPrice, int minStock, int maxStock,
                                                  String categoryName) {
        return productDAO.searchProductsByCriteria(productName, description, remarks, minPrice, maxPrice, minStock, maxStock, categoryName);
    }
}
