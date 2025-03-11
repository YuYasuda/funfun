<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
    <!-- ヘッダーここから -->
    <jsp:include page="top.jsp"></jsp:include>
    <!-- ヘッダーここまで -->
    <main>
        <h3 class="mypage-title">マイページ</h3>

        <p class="mypage-name">こんにちは ${userId} さん</p>



        <div class="mypage-icon">
            <a href="RedirectToRegiChangeServlet"><img src="${pageContext.request.contextPath}/images/IMG/myinformation_icon.png" alt="登録情報確認・変更・退会"></a>
            <a><img src="${pageContext.request.contextPath}/images/IMG/history_icon.png" alt="購入履歴"></a>
            <a><img src="${pageContext.request.contextPath}/images/IMG/favourite_icon.png" alt="お気に入り一覧"></a>
        </div>


    </main>
<!-- メインここまで -->
<!-- フッター -->

<p class="copyright">&copy; 6fun</p>
<!-- フッターここまで -->

      
    </body>
</html>