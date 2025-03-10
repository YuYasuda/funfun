package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import dao.CartDAO;
import dao.UserAccountDAO;
import model.Cart;
import model.CartItem;
import model.UserAccount;

@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserAccountDAO userAccountDAO = new UserAccountDAO();

    // GETリクエストで会員登録ページ (registration.jsp) を表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
    }

    // POSTリクエストで新規ユーザー登録処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String shippingPostCode = request.getParameter("shippingPostCode");
        String state = request.getParameter("state");
        String city = request.getParameter("city");
        String shippingAddress1 = request.getParameter("shippingAddress1");
        String shippingAddress2 = request.getParameter("shippingAddress2");

        // パスワードを `BCrypt` でハッシュ化
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        // 配送先の電話番号を"phone"と同じにする。
        String shippingPhoneNumber = phone;
        
        UserAccount newUser = new UserAccount(
                userId, lastName, firstName, phone, email, birth, gender, hashedPassword, "", 
                shippingPostCode, shippingAddress1, shippingAddress2,
                lastName, firstName, state, city, shippingPhoneNumber
            );

        boolean isCreated = userAccountDAO.createUserWithShipping(newUser);
        
        if (isCreated) {
            // 自動ログイン処理
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);

            // customer_id を取得してセッションに保存
            int customerId = userAccountDAO.getCustomerIdByUserId(userId);
            session.setAttribute("customerId", customerId);

            // カートのセッション情報をデータベースに統合
            CartDAO cartDAO = new CartDAO();
            Object sessionCartObj = session.getAttribute("sessionCart");
            List<Cart> sessionCart = new ArrayList<>();

            // セッションから取り出すとobject型なのでinstanceofでチェックする。instanceofでは型消去されるためList<Cart>ではなく、Listの情報しか残らない。
            if (sessionCartObj instanceof List<?>) {
                List<?> rawList = (List<?>) sessionCartObj;
                for (Object obj : rawList) {
                    if (obj instanceof Cart) {
                        sessionCart.add((Cart) obj);
                    }
                }
            }

            if (!sessionCart.isEmpty()) {
                for (Cart cart : sessionCart) {
                    // Cart オブジェクトから CartItem を生成する
                    CartItem cartItem = new CartItem(
                        cart.getCartId(),
                        cart.getProductId(),
                        cart.getUnitPrice(),
                        cart.getQuantity(),
                        cart.getSubtotal(),
                        cart.getProductName(),
                        cart.getImageURL()
                    );
                    cartDAO.mergeSessionCartToDB(customerId, cartItem);
                }
                session.removeAttribute("sessionCart"); // セッションのカートを削除
            }

            // カートの中身をチェックしてリダイレクト先を決定
            boolean hasItems = cartDAO.hasItemsInCart(customerId);
            String redirectPage = hasItems ? "checkout" : "mypage";
            
            response.sendRedirect(request.getContextPath() + "/" + redirectPage);
        } else {
            request.setAttribute("errorMessage", "会員登録に失敗しました。");
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
        }
    }
}
