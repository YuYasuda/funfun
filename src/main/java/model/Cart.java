package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Cart implements Serializable {
    private int cartId;
    private int customerId;
    private int productId;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal subtotal;
    private Timestamp addedAt;
    private String remarks;

    private String productName;
    private String userId;
    private String customerLastName;
    private String customerFirstName;
    private String imageURL;

    public Cart() {}

    // すべてのフィールドを引数に取るコンストラクタ
    public Cart(int cartId, int customerId, int productId, BigDecimal unitPrice, int quantity, BigDecimal subtotal, Timestamp addedAt, String remarks, String productName, String userID, String customerLastName, String customerFirstName,String imageURL) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.addedAt = addedAt;
        this.remarks = remarks;
        this.productName = productName;
        this.userId = userID;
        this.customerLastName = customerLastName;
        this.customerFirstName = customerFirstName;
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
//        return "Cart{" +
//                "cartId=" + cartId +
//                ", customerId=" + customerId +
//                ", productId=" + productId +
               
                //                ", addedAt=" + addedAt +
//                ", remarks='" + remarks + '\'' +
          return "ユーザー名：'" + userId +
        		  "商品名：'" + productName +
                 "単価：=" + unitPrice +
                "個数：" + quantity + 
                "計：" + subtotal +
                "画像" + imageURL;
          
//                ", userId='" + userId + '\'' +
//                "お名前：" + customerLastName +
//                "　" + customerFirstName + '\'' +
//                '}';
    }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCustomerLastName() { return customerLastName; }
    public void setCustomerLastName(String customerLastName) { this.customerLastName = customerLastName; }

    public String getCustomerFirstName() { return customerFirstName; }
    public void setCustomerFirstName(String customerFirstName) { this.customerFirstName = customerFirstName; }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public Timestamp getAddedAt() { return addedAt; }
    public void setAddedAt(Timestamp addedAt) { this.addedAt = addedAt; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
}
