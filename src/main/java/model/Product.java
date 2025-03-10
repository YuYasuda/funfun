package model;

import java.math.BigDecimal;//金額に使用

public class Product {//productsテーブルとCategoriesテーブル
	private int productId;
    private String productName;
    private String description;
    private BigDecimal price; // *金額を扱う際には、BigDecimalが最も適切。floatやdoubleは精度の問題があり、金額には不向き。
    private String imageUrl;
    private int stock;
    private String remarks;
    private int categoryId;//カテゴリテーブルより
    private String categoryName;//カテゴリテーブルより
    private int totalQuantity;
    // productCreatedAt と productUpdatedAt はデータベース側で管理

    
//コンストラクタ
//全てのフィールドを指定する
	public Product(int productId, String productName, String description, BigDecimal price, String imageUrl, int stock, String remarks, int categoryId){
	    this.productId = productId;
	    this.productName = productName;
	    this.description = description;
	    this.price = price;
	    this.imageUrl = imageUrl;
	    this.stock = stock;
	    this.remarks = remarks;	
	    this.categoryId = categoryId;
	    }
		
//一部
	public Product(int productId, String productName, BigDecimal price, int stock, String remarks){
	    this.productId = productId;
		this.productName = productName;
	    this.description = "";//空白
	    this.price = price;
	    this.imageUrl = "";//画像なし
	    this.stock = stock;
	    this.remarks = "";//空白
	    this.categoryId = 0;//要検討
	    }
	
    public Product(int productId, String productName, BigDecimal price, String imageUrl, int totalQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.totalQuantity = totalQuantity;
    }
	
	
	
//必要最低限の情報で初期化
	public Product(String productName, BigDecimal price) {
	    this.productName = productName;
	    this.price = price;
	    this.description = "";  // 空白
	    this.imageUrl = "";     // 空文字
	    this.stock = 0;         // 初期在庫は0
	    this.remarks = "";      // 空白
	    this.categoryId = 0;    // カテゴリIDは0
	}

	
	
	public int getProductId() {return productId;}
    public String getProductName() {return productName;}
    public String getDescription() {return description;}
    public BigDecimal getPrice() {return price;} 
    public String getImageUrl() {return imageUrl;}
    public int getStock() {return stock;}
    public String getRemarks() {return remarks;}
    public int getCategoryId() {return categoryId;}
    public String getCategoryName() {return categoryName;}
    public int getTotalQuantity() {return totalQuantity;}

	

	public void setProductName(String productName) { this.productName = productName; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setStock(int stock) { this.stock = stock; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setTotalQuantity(int totalQuantity) {this.totalQuantity = totalQuantity;}
    

}