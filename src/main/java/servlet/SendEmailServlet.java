package servlet;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Inquiry;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションからsuccessやerrorメッセージを削除
        request.getSession().removeAttribute("success");
        request.getSession().removeAttribute("error");

        // GET処理（フォームの表示など）
        request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームデータを取得
        String category = request.getParameter("category");         //問い合わせカテゴリ
        String messageContent = request.getParameter("message");    //問い合わせ内容
        String email = request.getParameter("email");               //返信用メールアドレス
        String confirmEmail = request.getParameter("confirmEmail"); //確認用メールアドレス

        // デバッグ用
        System.out.println("Category: " + category);
        System.out.println("Message: " + messageContent);
        System.out.println("Email: " + email);
        System.out.println("Confirm Email: " + confirmEmail);

        // Inquiryオブジェクトを作成
        Inquiry inquiry = new Inquiry(category, messageContent, email, confirmEmail);

        // メールアドレスの確認
        if (!inquiry.isEmailValid()) {
            request.setAttribute("error", "返信用メールアドレスが一致しません。");
            request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp").forward(request, response);
            return;
        }

        // メール送信処理
        sendEmail(inquiry, request, response);
    }

    private void sendEmail(Inquiry inquiry, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // メール送信のための設定
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "funfuninquiry@gmail.com";
        String password = "mvpk seem cqqw keer";

        String subject = "【funfun】" + inquiry.getCategory();
        String body = "以下のお問い合わせ内容を受け取りました。\n\n"
                    + "カテゴリ: " + inquiry.getCategory() + "\n"
                    + "問い合わせ内容: " + inquiry.getMessage() + "\n\n"
                    + "返信先: " + inquiry.getEmail();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // メールメッセージの作成
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("funfuninquiry@gmail.com"));
            message.setSubject(subject);
            message.setText(body);

            // メール送信成功時
            request.getSession().setAttribute("success", "お問い合わせ内容は正常に送信されました。");

            // メール送信後、エラーメッセージを削除
            request.getSession().removeAttribute("error");

            // inquiryResult.jspにフォワード
            request.getRequestDispatcher("/WEB-INF/jsp/inquiryResult.jsp").forward(request, response);

        } catch (MessagingException e) {
            e.printStackTrace();
            // メール送信失敗時
            request.getSession().setAttribute("error", "メール送信に失敗しました。もう一度お試しください。");

            // メール送信失敗時、successメッセージを削除
            request.getSession().removeAttribute("success");

            // contact.jspにフォワード
            request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp").forward(request, response);
        }
    }
}