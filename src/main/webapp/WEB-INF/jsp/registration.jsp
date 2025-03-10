<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun | 新規会員登録</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
<!-- ヘッダーここから -->
<jsp:include page="top.jsp"></jsp:include>
<!-- ヘッダーここまで -->

<!-- メインここから -->
    <main>
       <div class="register-container">
            <h1>新規会員登録</h1>
            
            <c:if test="${not empty errorMessage}">
		        <p style="color: red;">${errorMessage}</p>
		    </c:if>
            
            <form action="${pageContext.request.contextPath}/UserRegistrationServlet" method="POST" class="register-form">
                <div class="form-group">
                    <label for="userId">ユーザーID</label>
                    <input type="text" id="userId" name="userId" required>
                </div>
                <div class="form-group">
                    <label for="lastName">苗字</label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="firstName">名前</label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
                <div class="form-group">
                    <label for="phone">電話番号</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="email">メールアドレス</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="birth">生年月日</label>
                    <input type="date" id="birth" name="birth" required>
                </div>
                <div class="form-group">
                    <label for="gender">性別</label>
                    <select id="gender" name="gender" required>
                        <option value="男性">男性</option>
                        <option value="女性">女性</option>
                        <option value="その他">その他</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="password">パスワード</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="shippingPostCode">郵便番号</label>
                    <input type="text" id="shippingPostCode" name="shippingPostCode" required>
                </div>
                <div class="form-group">
			        <label for="state">都道府県:</label>
			        <input type="text" id="state" name="state" required>
                </div>
                <div class="form-group">
			        <label for="city">市区町村:</label>
			        <input type="text" id="city" name="city" required>
                </div>
                <div class="form-group">
			        <label for="shippingAddress1">住所1（番地など）:</label>
			        <input type="text" id="shippingAddress1" name="shippingAddress1" required>
                </div>
                <div class="form-group">
			        <label for="shippingAddress2">住所2（建物名・部屋番号）:</label>
			        <input type="text" id="shippingAddress2" name="shippingAddress2">
                </div>
                <button type="submit" class="register-btn">登録</button>
            </form>
            <p>既にアカウントをお持ちですか？ <a href="login.jsp">ログインはこちら</a></p>
        </div>
          
   
    <!-- メインここまで -->

</body>
</html>