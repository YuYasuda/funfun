package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductLogic;


//検索窓のサーブレット

@WebServlet("/ProductSearchByQueryServlet")
public class ProductSearchByQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private ProductLogic productLogic;

    @Override
    public void init() throws ServletException {
        productLogic = new ProductLogic();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("query");
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            request.setAttribute("errorMessage", "検索クエリが指定されていません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        List<Product> products = productLogic.searchProducts(searchQuery);
        request.setAttribute("productList", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
    @Override
    public void destroy() {
        productLogic = null;
    }
}
