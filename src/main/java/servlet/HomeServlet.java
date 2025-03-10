package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CartDAO;
import dao.UserAccountDAO;
import model.Cart;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // customer_idを取得
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        int customerId = userAccountDAO.getCustomerIdByUserId(userId);
        session.setAttribute("customerId", customerId);

        // customer_idを取得してカートに商品があるかチェック
        CartDAO cartDAO = new CartDAO();
        boolean hasItems = cartDAO.hasItemsInCart(customerId);

        // 画面遷移先を決定
        String redirectPage = hasItems ? "checkout.jsp" : "mypage.jsp";

        // JSP に遷移先を渡す
        request.setAttribute("redirectPage", redirectPage);
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
        
        // cartItemsをセット。nullにならないようにする
        List<Cart> cartItems = cartDAO.getCartByCustomerId(customerId);
        request.setAttribute("cartItems", cartItems);

    }
}
