package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CartSummary {
//	一応作ってみたが、合計と消費税を計算するときにしか用いない。
	private List<Cart> cartList;
    private String userId;
    private String password;
    private BigDecimal total;
    private BigDecimal fee;
    private BigDecimal tax;
    
    public CartSummary() {}
    public CartSummary(List<Cart> cartList) {
    	setCartList(cartList);
    }
	public List<Cart> getCartList() {
		return cartList;
	}
	public void setCartList(List<Cart> cartList) {

		this.cartList = cartList;
		
		    //リストが入った時点で計を計算する
	        BigDecimal total = BigDecimal.ZERO;

	        for (Cart item : this.cartList) {
	            total = total.add(item.getSubtotal());
	        }
	        setTotal(total.add(getFee()));
//	      消費税込み価格から内税を求める
	        BigDecimal divisor = new BigDecimal("1.08");
	        setTax(total.subtract(total.divide(divisor, 2, RoundingMode.HALF_UP)));

		
		
		
		
		
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getFee() {
	     return new BigDecimal("600.00");
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public void addCart(Cart cart) {
		// TODO 自動生成されたメソッド・スタブ
		this.cartList.add(cart);
	}
	

}
