package servlet;

import java.io.IOException;
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
import model.CartItem;
import model.CartSession;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // GETリクエストでログインページ (login.jsp) を表示できるようにする
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // すでにログインしている場合、マイページへリダイレクト
        if (session != null && session.getAttribute("userId") != null) {
            response.sendRedirect(request.getContextPath() + "/mypage");
            return;
        }
        
        // 未ログインの場合はログインページを表示
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        System.out.println("【Servletデバッグ】ログイン試行: userId=" + userId);
//        System.out.println("【Servletデバッグ】入力パスワード=" + password);

        // データベースからユーザー情報を取得
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        String hashedPasswordFromDB = userAccountDAO.getHashedPassword(userId);

        boolean isAuthenticated = hashedPasswordFromDB != null && BCrypt.checkpw(password, hashedPasswordFromDB);
        System.out.println("【Servletデバッグ】ログイン結果: " + (isAuthenticated ? "成功" : "失敗"));

        if (isAuthenticated) {
//            System.out.println("【Servletデバッグ】セッションを開始します。");
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);

            // customer_id を取得
            int customerId = userAccountDAO.getCustomerIdByUserId(userId);
            session.setAttribute("customerId", customerId);

         // セッションに保存されている CartSession を取得（キーを "cartSession" と統一）
            CartSession cartSession = (CartSession) session.getAttribute("cartSession");
            if (cartSession != null) {
                // セッション内のカートアイテム（List<CartItem>）を取得
                List<CartItem> sessionCartItems = cartSession.getItems();
                
                if (!sessionCartItems.isEmpty()) {
                    CartDAO cartDAO = new CartDAO();
                    // 各 CartItem から Cart を生成して、データベースに統合する
                    for (CartItem item : sessionCartItems) {
                        
                        cartDAO.mergeSessionCartToDB(customerId, item);
                    }
                    // 統合後、セッション内のカート情報を削除
                    session.removeAttribute("cartSession");
                }
            }

            // カートの中身をチェックしてリダイレクト先を決定
            CartDAO cartDAO = new CartDAO();
            boolean hasItems = cartDAO.hasItemsInCart(customerId);

            String redirectPage = hasItems ? "mypage" : "mypage";
            System.out.println("【Servletデバッグ】リダイレクト: " + redirectPage);
            response.sendRedirect(request.getContextPath() + "/" + redirectPage);
        } else {
            System.out.println("【Servletデバッグ】ログイン失敗、エラーメッセージをセット");
            request.setAttribute("errorMessage", "ユーザーIDまたはパスワードが間違っています。");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
