package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.CartItem;
import model.CartSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/funfun";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // 受け取った全パラメータを出力（デバッグ用）
        System.out.println("【OrderServlet】受け取ったリクエストパラメータ:");
        request.getParameterMap().forEach((key, value) -> System.out.println(key + " = " + String.join(",", value)));

        // action パラメータを取得（デバッグ用）
        String action = request.getParameter("action");
        System.out.println("【OrderServlet】action: " + action);

        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // **① 注文内容の確認 (order.jsp に遷移)**
        if ("confirm".equals(action)) {
            CartSession cartSession = (CartSession) session.getAttribute("cartSession");

            if (cartSession == null || cartSession.getItems().isEmpty()) {
                System.out.println("【OrderServlet】カートが空なので cart.jsp に戻ります。");// デバッグ用
                request.setAttribute("errorMessage", "カートに商品がありません。");
                request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
                return;
            }

            // 注文内容を order.jsp へ渡す
            System.out.println("【OrderServlet】注文内容を order.jsp に渡します。カート内商品数: " + cartSession.getItems().size()); // デバッグ用

            request.setAttribute("orderCart", cartSession);
            request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
            return;
        }
        
        // **② 注文確定 (orderDone.jsp に遷移)**
        else if ("finalize".equals(action)) {
            System.out.println("【OrderServlet】注文確定処理を開始します。");// デバッグ用

            // ユーザーの配送情報・支払い情報を取得
            String shippingLastName = request.getParameter("shippingLastName");
            String shippingFirstName = request.getParameter("shippingFirstName");
            String shippingPostCode = request.getParameter("shippingPostCode");
            String shippingAddress1 = request.getParameter("shippingAddress1");
            String shippingAddress2 = request.getParameter("shippingAddress2");
            String phoneNumber = request.getParameter("phoneNumber");
            String paymentMethod = request.getParameter("paymentMethod");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                conn.setAutoCommit(false); // トランザクション開始

                // **1. 配送先情報を登録**
                String sqlShipping = "INSERT INTO shipping_address (customer_id, shipping_last_name, shipping_first_name, shipping_post_code, shipping_address_1, shipping_address_2, phone_number, shipping_created_at) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlShipping)) {
                    pstmt.setInt(1, customerId);
                    pstmt.setString(2, shippingLastName);
                    pstmt.setString(3, shippingFirstName);
                    pstmt.setString(4, shippingPostCode);
                    pstmt.setString(5, shippingAddress1);
                    pstmt.setString(6, shippingAddress2);
                    pstmt.setString(7, phoneNumber);
                    pstmt.executeUpdate();
                }

                // **2. 注文情報を登録**
                System.out.println("【OrderServlet】注文情報を登録します。");

                String sqlOrder = "INSERT INTO orders_summary (customer_id, total_amount, total_items, payment_id, shipping_address_id,"
                		+ " delivery_date, order_status) "
                		+ "VALUES (?, ?, ?, ?, ?, DATE_ADD(NOW(), INTERVAL 5 DAY), ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    pstmt.setInt(1, customerId);
                    pstmt.setBigDecimal(2, cartSession.getTotal());
                    pstmt.setInt(3, cartSession.getItemsSize());
                 // payment_id については、1:クレジット, 2:銀行振込, 3:代引き
                    pstmt.setString(4, paymentMethod);
                 // shipping_address_id は、配送先情報のINSERT後に取得するか、別途処理
                    pstmt.setInt(5, shippingAddressId);
                    pstmt.setString(6, "受付済み");
                    pstmt.executeUpdate();

                    // **注文IDを取得**
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            orderId = rs.getInt(1);
                        }
                    }
                }

                // **3. カートの商品を order_details に登録**
                System.out.println("【OrderServlet】カートの商品を order_details に登録します。");

                CartSession cartSession = (CartSession) session.getAttribute("cartSession");
                if (cartSession != null && orderId > 0) {
                    String sqlOrderDetails = "INSERT INTO order_details (order_id, product_id, unit_price, quantity, subtotal) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlOrderDetails)) {
                        for (CartItem item : cartSession.getItems()) {
                            pstmt.setInt(1, orderId);
                            pstmt.setInt(2, item.getProductId());
                            pstmt.setBigDecimal(3, item.getUnitPrice());
                            pstmt.setInt(4, item.getQuantity());
                            pstmt.setBigDecimal(5, item.getSubtotal());
                            pstmt.addBatch();
                        }
                        pstmt.executeBatch();
                    }
                }

                // **4. カートをクリア**
                System.out.println("【OrderServlet】カートをクリアします。");

                session.removeAttribute("cartSession");

                conn.commit(); // トランザクション確定
                
                System.out.println("【OrderServlet】注文確定完了。orderDone.jsp に遷移します。");// デバッグ用


            } catch (SQLException e) {
                e.printStackTrace();
            }
         // セッションからカートを取得
            CartSession cartSession = (CartSession) session.getAttribute("cartSession");

         // `cartSession` が `null` の場合の対策
            if (cartSession == null) {
                System.out.println("【OrderServlet】cartSession が null です。カート情報なし。");
                request.setAttribute("errorMessage", "カートのデータがありません。");
                request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
                return;
            }

            request.setAttribute("orderCart", cartSession);
            request.setAttribute("subtotal", cartSession.getSubtotal());
            request.setAttribute("tax", cartSession.getTax());
            request.setAttribute("shippingFee", cartSession.getShippingFee());
            request.setAttribute("total", cartSession.getTotal());

            // **注文確定ページ (orderDone.jsp) に遷移**
            request.getRequestDispatcher("/WEB-INF/jsp/orderDone.jsp").forward(request, response);
            return; // これを追加して、二重フォワードを防ぐ
        }
    }

    // GETリクエストでも POST の処理を実行する
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("【OrderServlet】GETリクエストを受け取りました。order.jsp にフォワードします。");
        
        HttpSession session = request.getSession();
        CartSession cartSession = (CartSession) session.getAttribute("cartSession");

        if (cartSession == null || cartSession.getItems().isEmpty()) {
            System.out.println("【OrderServlet】カートが空なので cart.jsp に戻ります。");
            request.setAttribute("errorMessage", "カートに商品がありません。");
            request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
            return;
        }

        // order.jsp に渡す金額情報をセット
        request.setAttribute("orderCart", cartSession);
        request.setAttribute("subtotal", cartSession.getSubtotal());
        request.setAttribute("tax", cartSession.getTax());
        request.setAttribute("shippingFee", cartSession.getShippingFee());
        request.setAttribute("total", cartSession.getTotal());

        request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
    }
    
}
