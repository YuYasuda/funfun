package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductLogic;  // Logicクラスをインポート

@WebServlet("/ProductSearchByProductIdServlet")
public class ProductSearchByProductIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // ProductLogicのインスタンス
    private ProductLogic productLogic;

    @Override
    public void init() throws ServletException {
        // ProductLogicのインスタンス化
        productLogic = new ProductLogic();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 商品IDをURLのパラメータから取得
        String productIdStr = request.getParameter("productId");

        if (productIdStr == null || productIdStr.isEmpty()) {
            // 商品IDが指定されていない場合、エラーページに遷移
            request.setAttribute("errorMessage", "商品IDが指定されていません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            // ProductLogic経由で商品情報を取得
            Product product = productLogic.getProductById(productId);

            if (product != null) {
                // 商品が見つかった場合、リクエスト属性に設定
                request.setAttribute("product", product);
                // 商品詳細画面にフォワード
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                // 商品が見つからなかった場合、エラーページに遷移
                request.setAttribute("errorMessage", "指定された商品が見つかりません。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            // 商品IDが数値でない場合のエラー処理
            request.setAttribute("errorMessage", "無効な商品IDです。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // サーブレット終了時にLogicのリソースを解放
        productLogic = null;
    }
}
