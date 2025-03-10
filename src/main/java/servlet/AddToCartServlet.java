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

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productId = request.getParameter("productId");
		System.out.println("GetしたProductId" + productId);
		int productID=0;
//		if(productId.isEmpty()) {
//		       request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
//		}else {
			productID = Integer.parseInt(productId);

//		}
		System.out.println("productId (string): " + productId);
		System.out.println("productID(int) : " + productID);
		
		
		HttpSession session = request.getSession(false);
//  	 ServletContext session = getServletContext();
       response.setContentType("text/html; charset=UTF-8");
       response.setCharacterEncoding("UTF-8");
       
       String userId =null;
       int customerId = 0;
       // セッションからCartSessionを取得
       CartSession cartSession = (CartSession) session.getAttribute("cartSession");
       if (cartSession == null) {
           // CartSessionがセッションに存在しない場合、新しいインスタンスを作成
           cartSession = new CartSession();
           cartSession.setItems(new ArrayList<CartItem>());
           userId = "guest";
           System.out.println("セッションは空でした。");
           
       }else {
       	userId = cartSession.getUserId();
       	customerId = cartSession.getCustomerId();
       	System.out.println(cartSession.getItemsSize() + "　：セッションはありました。");
       }

       // 新しいCartItemを作成
       ProductDAO productDao = new ProductDAO();
       CartItem newItem = null;
      Product addProduct = productDao.findProductById(productID);
      if (addProduct != null) {   
       newItem = new CartItem(cartSession.getItemsSize()+1, productID, addProduct.getPrice(), 3, BigDecimal.valueOf(7500.00), addProduct.getProductName(), addProduct.getImageUrl());
       	System.out.println("addProduct があります。");
      }else {
       newItem = new CartItem(cartSession.getItemsSize()+1, 101, BigDecimal.valueOf(2500.00), 3, BigDecimal.valueOf(7500.00), "追加でも", "1011.jpg");
      	System.out.println("addProduct がNULLです。");
                
            }
       // CartSessionに新しいアイテムを追加
       cartSession.getItems().add(newItem);
       
       // CartSessionをセッションに保存
       session.setAttribute("cartSession", cartSession);
       
           	// JSPに変数を渡す
   	request.setAttribute("userId", userId);
   	//商品名プラスパスワードユーザー名など　使わないかも
   	request.setAttribute("cartSession", cartSession);
   	//商品名など JSPに送る時だけに使用他に対い道がない。後で消す必要があるかもしれない
   	request.setAttribute("cartSessionItem", cartSession.getItems());
// 	    request.setAttribute("cartList", cartList);   
       request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	
	}	
	

	/**
p.	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String productId = request.getParameter("productId");
//		int productID=0;
////		if(productId.isEmpty()) {
////		       request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
////		}else {
//			productID = Integer.parseInt(productId);
//
////		}
//		System.out.println("productId (string): " + productId);
//		System.out.println("productID(int) : " + productID);
//		
//		
//		HttpSession session = request.getSession(false);
////  	 ServletContext session = getServletContext();
//       response.setContentType("text/html; charset=UTF-8");
//       response.setCharacterEncoding("UTF-8");
//       
//       String userId =null;
//       int customerId = 0;
//       // セッションからCartSessionを取得
//       CartSession cartSession = (CartSession) session.getAttribute("cartSession");
//       if (cartSession == null) {
//           // CartSessionがセッションに存在しない場合、新しいインスタンスを作成
//           cartSession = new CartSession();
//           cartSession.setItems(new ArrayList<CartItem>());
//           userId = "gest";
//           System.out.println("セッションは空でした。");
//           
//       }else {
//       	userId = cartSession.getUserId();
//       	customerId = cartSession.getCustomerId();
//       	System.out.println(cartSession.getItemsSize() + "　：セッションはありました。");
//       }
//
//       // 新しいCartItemを作成
//       ProductDAO productDao = new ProductDAO();
//       CartItem newItem = null;
//      Product addProduct = productDao.findProductById(productID);
//      if (addProduct != null) {   
//       newItem = new CartItem(cartSession.getItemsSize()+1, productID, addProduct.getPrice(), 3, BigDecimal.valueOf(7500.00), addProduct.getProductName(), addProduct.getImageUrl());
//       	System.out.println("addProduct があります。");
//      }else {
//       newItem = new CartItem(cartSession.getItemsSize()+1, 101, BigDecimal.valueOf(2500.00), 3, BigDecimal.valueOf(7500.00), "追加でも", "1011.jpg");
//      	System.out.println("addProduct がNULLです。");
//                
//            }
//       // CartSessionに新しいアイテムを追加
//       cartSession.getItems().add(newItem);
//       
//       // CartSessionをセッションに保存
//       session.setAttribute("cartSession", cartSession);
//       
//           	// JSPに変数を渡す
//   	request.setAttribute("userId", userId);
//   	//商品名プラスパスワードユーザー名など　使わないかも
//   	request.setAttribute("cartSession", cartSession);
//   	//商品名など JSPに送る時だけに使用他に対い道がない。後で消す必要があるかもしれない
//   	request.setAttribute("cartSessionItem", cartSession.getItems());
//// 	    request.setAttribute("cartList", cartList);   
//       request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	
	}

}
