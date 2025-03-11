<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>お問い合わせ完了</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>



    <!-- 成功メッセージを表示 -->
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>

    <!-- エラーメッセージを表示 -->
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- お問い合わせ完了のメッセージ -->
    <c:if test="${not empty success}">
        <p>後ほど担当者からご連絡させていただきます。</p>
    </c:if>

    <!-- トップページへ戻るボタン -->
    <form action="${pageContext.request.contextPath}/index" method="get">
    <button type="submit">トップへ戻る</button>
    </form>


</body>
</html>
