package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import jakarta.servlet.http.HttpSession;


public class CartSession {
    private List<CartItem> items;
    private int customerId;
    private String userId;
    private String password;
    private BigDecimal total;
    private BigDecimal fee;
    private BigDecimal tax;
    
    private static final BigDecimal TAX_RATE = new BigDecimal("0.08"); // 8%消費税
    private static final BigDecimal SHIPPING_FEE = new BigDecimal("600.00"); // 送料600円
    
        
    public int getItemsSize() {
    	return items.size();
    }
    public int getCustomerId() {
		return customerId;
	}

	public BigDecimal getTax() {
		return getSubtotal().multiply(TAX_RATE);
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotal() {
		return getSubtotal().add(getTax()).add(SHIPPING_FEE);
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	static public BigDecimal getFee() {
		//配送料は６００円で固定消費税込み
        return new BigDecimal("600.00");
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	// リストCartItemのゲッター
	public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
        //アイテムリストが設定されたときに合計金額、送料、税額などを再計算する
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : this.items) {
            total = total.add(item.getSubtotal());
        }
        

        //送料は６００円固定
//        setFee();
//        this.total = total.add(getFee());    
//        this.total = total.add(getFee());  
        setTotal(total.add(getFee()));
//      消費税込み価格から内税を求める
        BigDecimal divisor = new BigDecimal("1.08");
        setTax(total.subtract(total.divide(divisor, 2, RoundingMode.HALF_UP)));
        
    }
    // Save the cart items in the session
    public void saveToSession(HttpSession session) {
        session.setAttribute("cartSession", this);
    }

    // Retrieve the cart items from the session
    public static CartSession getFromSession(HttpSession session) {
        return (CartSession) session.getAttribute("cartSession");
    }

    // Clear the cart items from the session
    public void clearSession(HttpSession session) {
        session.removeAttribute("cartSession");
    }
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

 // toStringメソッドの追加
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<BR>ユーザーID<BR>" + this.getUserId() +"<BR>");
        if (items != null) {
            for (CartItem item : items) {
                sb.append(item.toString()).append("<BR>");
            }
        }
//        sb.append("}<BR>\n");
        return sb.toString();
    }
    
    
    public static CartSession copyFromCart2(CartSummary cartSummary) {
        CartSession cartSession = new CartSession();
        for (Cart cart : cartSummary.getCartList()) {
            CartItem item = new CartItem(
                cart.getCartId(),
                cart.getProductId(),
                cart.getUnitPrice(),
                cart.getQuantity(),
                cart.getSubtotal(),
                cart.getProductName(),
                cart.getImageURL()
            );
            cartSession.addItem(item);
        }
        return cartSession;
    }

    private void addItem(CartItem item) {
		// TODO 自動生成されたメソッド・スタブ
    	this.items.add(item);
    	// アイテム追加後、合計金額など再計算する場合はここで処理を行う
	}

	public static CartSummary copyToCart2(CartSession cartSession) {
        CartSummary cartSummary = new CartSummary();
        for (CartItem item : cartSession.getItems()) {
            Cart cart = new Cart();
            cart.setCartId(item.getCartId());
            cart.setProductId(item.getProductId());
            cart.setUnitPrice(item.getUnitPrice());
            cart.setQuantity(item.getQuantity());
            cart.setSubtotal(item.getSubtotal());
            cart.setProductName(item.getProductName());
            cart.setImageURL(item.getImageURL());
            cartSummary.addCart(cart);
        }
        return cartSummary;
	}
	
    // カート内の小計（税抜き）の合計を計算
    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        if (items == null || items.isEmpty()) {
            System.out.println("【CartSession】カート内に商品がありません。subtotal=0");
            return subtotal;
        }

        for (CartItem item : items) {
            subtotal = subtotal.add(item.getSubtotal());
        }
        return subtotal;
    }
	
    // 送料を取得
    public BigDecimal getShippingFee() {
        return SHIPPING_FEE;
    }

}