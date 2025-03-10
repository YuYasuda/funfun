package servlet;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ここで情報をデータベースに保存する処理などを行う

        // 現在は情報をコンソールに出力
        System.out.println("ユーザー名: " + username);
        System.out.println("メールアドレス: " + email);
        System.out.println("新しいパスワード: " + password);

        // 更新が完了したら、mypage.jsp にリダイレクトする
        response.sendRedirect("mypage.jsp");
    }
}
