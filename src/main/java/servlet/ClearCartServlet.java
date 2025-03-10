package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.CartSession;
import model.SessionHelper;

/**
 * Servlet implementation class ClearCartServlet
 */
@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  HttpSession session = request.getSession();
	        // レスポンスのエンコーディングを設定
	        response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
//	         SessionHelperを使用してセッションをクリア
	        SessionHelper.clearSession(session, "cartSession");
	        
	        CartSession clearedCartSession = (CartSession) SessionHelper.getFromSession(session, "cartSession");
	        if (clearedCartSession == null) {
//	            response.getWriter().println("CartSessionがセッションから正常にクリアされました。");
	        	
	            request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	        		        } else {
//	            response.getWriter().println("CartSessionのクリアに失敗しました。");
	            request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	        		        }	
	     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
