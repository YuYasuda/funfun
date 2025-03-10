package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Integer customerId = (Integer) session.getAttribute("customerId");

        // 未ログインならlogin.jspにリダイレクトする
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("userId", userId);
        request.setAttribute("customerId", (customerId != null) ? customerId : "N/A");

        request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp").forward(request, response);
    }
}
