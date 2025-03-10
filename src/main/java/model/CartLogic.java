package model;
import java.util.ArrayList;
import java.util.List;

import dao.CartDAO;

public class CartLogic extends Cart {
    private List<Cart> carts;
    private String userName;

    public String getUserName() {
		return userName;
	}


	// コンストラクタ
    public CartLogic() {
        this.carts = new ArrayList<>();
    }

    // カートを追加するメソッド
    public void addCart(Cart cart) {
        carts.add(cart);
    }

    // カートを削除するメソッド
    public void removeCart(Cart cart) {
        carts.remove(cart);
    }

    // customerIDからカートを抽出するメソッド
    public List<Cart> getCartsByCustomerId(int customerId) {
        List<Cart> result = new ArrayList<>();
        for (Cart cart : carts) {
            if (cart.getCustomerId() == customerId) {
                result.add(cart);
                this.setUserId(cart.getUserId());
            }
        }
        return result;
    }
  

    // データベースから全データを取得してcartsに入れるメソッド
    public void loadAllCartsFromDatabase() {
        CartDAO cartDAO = new CartDAO();
        this.carts = cartDAO.getAllCarts();
    }

    // cartsフィールドのゲッター
    public List<Cart> getCarts() {
        return carts;
    }

    // cartsフィールドのセッター
    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
    
    
}
