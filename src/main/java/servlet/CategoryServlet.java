package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserAccountDAO;
import model.Product;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId"); // ログイン情報を取得（未ログインなら null）
        
        // クエリパラメータからカテゴリを取得
        String categoryName = request.getParameter("type"); // カテゴリー名を取得
        String targetPage = "/WEB-INF/jsp/index.jsp"; // デフォルトのページ


        // DAOを使って該当するカテゴリーの商品リストを取得
        ProductDAO productDAO = new ProductDAO();
        // DAOからuserIdを使って購入履歴を検索
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        // DAOからuserIdでcustomerIdを取得
        UserAccountDAO userAccountDAO = new UserAccountDAO(); 

        
        // カテゴリーが選択された場合（通常の処理）
        if (categoryName != null) {
	
	        List<Product> products = productDAO.findProductsByCategoryName(categoryName);
	
	        // JSPに商品リストを渡す
	        request.setAttribute("products", products);
	        request.setAttribute("selectedCategory", categoryName); // 現在のカテゴリーを表示用にセット
	        request.setAttribute("userId", userId); // ログイン状態をJSPに渡す
	        request.getRequestDispatcher(targetPage).forward(request, response);
	        return;
        }

        
        
        List<Product> rankingProducts;
        if (userId != null) {
            // ユーザーの購入履歴のTOP15を取得
            int customerId = userAccountDAO.getCustomerIdByUserId(userId);
            if (customerId != -1) {
	            List<Product> userRanking = productDAO.findUserTopSellingProducts(customerId, 15);
	
	            if (userRanking.size() < 15) {
	                // 足りない分を全体ランキングで補完（OrderDetailDAOを使用）
	                List<Product> globalRanking =  orderDetailDAO.getSalesRanking();
	                for (Product p : globalRanking) {
	                    if (userRanking.size() >= 15) break; // 15品揃ったら終了
	                    if (!userRanking.contains(p)) userRanking.add(p); // 重複しないものだけ追加
	                }
	            }
	            rankingProducts = userRanking;
            } else {
                // ユーザーIDが無効な場合、全体ランキングを取得
                rankingProducts = orderDetailDAO.getSalesRanking();

            }
        } else {
            // ログインしていない場合は全体ランキング取得
            rankingProducts = orderDetailDAO.getSalesRanking();
        }

        // JSPにランキングデータを渡す
        request.setAttribute("rankingProducts", rankingProducts);
        request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp").forward(request, response);

    }
}
