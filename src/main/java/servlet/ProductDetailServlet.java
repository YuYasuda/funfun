package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");

        if (productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);

            // 商品データを取得
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.findProductById(productId);

            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/jsp/product.jsp").forward(request, response);
                return;
            }
        }

        // 商品が見つからない場合はエラーページへ
        response.sendRedirect("error.jsp");
    }
}
