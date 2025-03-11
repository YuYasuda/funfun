<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>お問い合わせ - funfun</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
<!-- ヘッダーここから -->
<jsp:include page="top.jsp"></jsp:include>
<!-- ヘッダーここまで -->


    <style>
        
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input, textarea, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>お問い合わせ</h1>
    <form action="SendEmailServlet" method="post">
        <!-- 問い合わせカテゴリ -->
        <label for="category">件名（問い合わせカテゴリ）:</label>
        <select id="category" name="category" required>
            <option value="商品に関する質問">商品に関する質問</option>
            <option value="注文・配送に関する質問">注文・配送に関する質問</option>
            <option value="返品・交換に関する質問">返品・交換に関する質問</option>
            <option value="支払い方法に関する質問">支払い方法に関する質問</option>
            <option value="会員登録・ログインに関する質問">会員登録・ログインに関する質問</option>
        </select>

        <!-- 問い合わせ内容 -->
        <label for="message">問い合わせ内容（本文）:</label>
        <textarea id="message" name="message" rows="5" required></textarea>

        <!-- 返信用メールアドレス -->
        <label for="email">返信用メールアドレス:</label>
        <input type="email" id="email" name="email" required>

        <!-- 返信用メールアドレス確認 -->
        <label for="confirmEmail">返信用メールアドレス（確認用）:</label>
        <input type="email" id="confirmEmail" name="confirmEmail" required>

        <button type="submit">送信</button>
    </form>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
    </c:if>
</div>

<!-- メインここまで -->
<!-- フッター -->

<p class="copyright">&copy; 6fun</p>



    <script src="js/script.js"></script>
</body>
</html>