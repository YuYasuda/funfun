package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ProductDAO;
import model.CartItem;
import model.CartSession;
import model.Product;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        super.init();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new ServletException("データベースドライバのロードに失敗しました", e);
//        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    	
   	    HttpSession session = request.getSession();
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String userId =null;
        
        // セッションからCartSessionを取得
        CartSession cartSession = (CartSession) session.getAttribute("cartSession");
        if (cartSession == null) {
            // CartSessionがセッションに存在しない場合、新しいインスタンスを作成
            cartSession = new CartSession();
            cartSession.setItems(new ArrayList<CartItem>());
            userId = "ゲスト";
            System.out.println("セッションはありません。");
        }else {
        	userId = cartSession.getUserId();
        	System.out.println("セッション　リストは以下の数あります。" + cartSession.getItemsSize());
        }


        // CartSessionをセッションに保存
        session.setAttribute("cartSession", cartSession);
   	// JSPに変数を渡す
    	request.setAttribute("userId", userId);
    	//商品名プラスパスワードユーザー名など　使わないかも
    	request.setAttribute("cartSession", cartSession);
    	//商品名など JSPに送る時だけに使用他に対い道がない。後で消す必要があるかもしれない
    	request.setAttribute("cartSessionItem", cartSession.getItems());
//  	    request.setAttribute("cartList", cartList);   
        request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
    }
 
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // ログイン状態の確認（userId がセッションにあるかどうか）
        String userId = (String) session.getAttribute("userId");

        int customerId = 0;

        try {
            // セッションからCartSessionを取得
            CartSession cartSession = (CartSession) session.getAttribute("cartSession");
            if (cartSession == null) {
                System.out.println("【processRequest】CartSession is null. Creating new instance.");
                cartSession = new CartSession();
                cartSession.setItems(new ArrayList<CartItem>());
            } else {
                userId = cartSession.getUserId();
                customerId = cartSession.getCustomerId();
                System.out.println("【processRequest】Found CartSession. Items count: " + cartSession.getItemsSize());
            }
            
            // リクエストパラメータから productId と quantity を取得する
            String productIdStr = request.getParameter("productId");
            String quantityStr = request.getParameter("quantity");
            System.out.println("【processRequest】Received parameters: productId=" + productIdStr + ", quantity=" + quantityStr);
            
            if (productIdStr != null && quantityStr != null) {
                int productId = Integer.parseInt(productIdStr);
                int quantity = Integer.parseInt(quantityStr);
                System.out.println("【processRequest】Parsed productId=" + productId + ", quantity=" + quantity);
                
                // 商品情報を取得（DAOを利用）
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.findProductById(productId);
                if (product != null) {
                    BigDecimal unitPrice = product.getPrice();
                    BigDecimal subtotal = unitPrice.multiply(new BigDecimal(quantity));
                    System.out.println("【processRequest】Product found: " + product.getProductName() 
                            + ", unitPrice=" + unitPrice + ", subtotal=" + subtotal);
                    
                    // 新しい CartItem を作成
                    CartItem newItem = new CartItem(
                        cartSession.getItemsSize() + 1, // 一意の cartId (仮の例)
                        productId, 
                        unitPrice, 
                        quantity, 
                        subtotal, 
                        product.getProductName(), 
                        product.getImageUrl()
                    );
                    cartSession.getItems().add(newItem);
                    System.out.println("【processRequest】New CartItem added: " + newItem.toString());
                } else {
                    System.out.println("【processRequest】Product with ID " + productId + " not found.");
                }
            } else {
                System.out.println("【processRequest】No product parameters provided.");
            }
            
            // CartSessionをセッションに保存
            session.setAttribute("cartSession", cartSession);
            request.setAttribute("userId", userId);
            request.setAttribute("cartSession", cartSession);
            request.setAttribute("cartSessionItem", cartSession.getItems());
            
            System.out.println("【processRequest】Forwarding to cart.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
            
        } catch (Exception e) {
            System.err.println("【processRequest】Exception occurred:");
            e.printStackTrace();
            throw new ServletException("Error in processing cart request", e);
        }
    }
}
