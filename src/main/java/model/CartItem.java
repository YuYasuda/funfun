package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartItem {
	private int cartId;
    private int productId;
    private BigDecimal unitPrice;
    private int quantity;
    private LocalDateTime addedAt; // 追加日時
    private BigDecimal subtotal;
    private String productName;
    private String imageURL;


    public CartItem(int productId, BigDecimal unitPrice, int quantity, LocalDateTime addedAt) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.addedAt = addedAt;

    }
    
    public CartItem(int cartId, int productId, BigDecimal unitPrice, int quantity, 
            BigDecimal subtotal, String productName, String imageURL) {
		this.cartId = cartId;
		this.productId = productId;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.productName = productName;
		this.imageURL = imageURL;
		// addedAt を現在時刻に設定するか、null にしておく
		this.addedAt = LocalDateTime.now();
	}
    
    

    public int getCartId() {
        return cartId;
    }


    public int getProductId() {
        return productId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public LocalDateTime getAddedAt() {
        return addedAt;
    }
    
    public BigDecimal getSubtotal() {
        // もし subtotal が設定されていればそれを返し、なければ計算する
        if (subtotal != null) {
            return subtotal;
        }
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    public String getProductName() {
        return productName;
    }

    public String getImageURL() {
        return imageURL;
    }

}
