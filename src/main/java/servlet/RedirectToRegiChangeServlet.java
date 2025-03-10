package servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RedirectToRegiChangeServlet")
public class RedirectToRegiChangeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // regiChange.jsp にリダイレクトする
        //String nextPage = "/regiChange.jsp";
    	//response.sendRedirect("regiChange.jsp");
        //RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        //dispatcher.forward(request, response);
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/regiChange.jsp");
	      dispatcher.forward(request, response);
	      
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // GETとPOSTは同じ処理をする
    }
}
