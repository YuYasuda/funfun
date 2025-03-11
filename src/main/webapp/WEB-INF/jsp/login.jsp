<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun | ログイン</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
<!-- ヘッダーここから -->
<jsp:include page="top.jsp"></jsp:include>
<!-- ヘッダーここまで -->
  
     
    <!-- メインここから -->
    <main>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
	
    <div class="login-container">
            <form action="${pageContext.request.contextPath}/UserLoginServlet" method="POST" class="login-form">
            <div class="form-group">
                <label for="userId">ユーザーID</label>
                <input type="text" id="userId" name="userId" required placeholder="ユーザーIDを入力">
            </div>
            <div class="form-group">
                <label for="password">パスワード</label>
                <input type="password" id="password" name="password" required placeholder="パスワードを入力">
            </div>
            <button type="submit" class="login-btn">ログイン</button>
            <div class="footer-links">
                <a href="${pageContext.request.contextPath}/UserRegistrationServlet">新規会員登録</a>
            </div>
        </form>
    </div>

    </main>
    <!-- メインここまで -->
    
  
</body>
</html>