package model;

import jakarta.servlet.http.HttpSession;

public class SessionHelper {


    // セッションにオブジェクトを保存
    public static void saveToSession(HttpSession session, String attributeName, Object attributeValue) {
        session.setAttribute(attributeName, attributeValue);
    }

    // セッションからオブジェクトを取得
    public static Object getFromSession(HttpSession session, String attributeName) {
        return session.getAttribute(attributeName);
    }

    // セッションからオブジェクトを削除
    public static void clearSession(HttpSession session, String attributeName) {
        session.removeAttribute(attributeName);
    }
    
    // カートセッション専用のキー
    private static final String CART_SESSION_KEY = "cartSession";

    // カートセッションを保存
    public static void saveCartSession(HttpSession session, CartSession cartSession) {
        session.setAttribute(CART_SESSION_KEY, cartSession);
    }

    // カートセッションを取得
    public static CartSession getCartSession(HttpSession session) {
        Object obj = session.getAttribute(CART_SESSION_KEY);
        if (obj instanceof CartSession) {
            return (CartSession) obj;
        }
        return null;
    }

    // カートセッションをクリア
    public static void clearCartSession(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
}
