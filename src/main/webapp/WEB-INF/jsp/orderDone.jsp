<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="model.Cart" %>	
<%@ page import="model.CartSession" %>
<%@ page import="model.CartSummary" %>
<%@ page import="model.CartItem" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun | ご注文ありがとうございました</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
<!-- ヘッダーここから -->
<jsp:include page="top.jsp"></jsp:include>
<!-- ヘッダーここまで -->

<!-- メインここから -->
<main>  
<c:if test="${not empty orderCart.items}">
    <div class="order-success-container">
      <h1>注文が完了しました。</h1>
      <p>注文番号: 123456789</p>
      <p>配送先: 東京都新宿区1-1-1</p>
      <p>配送先氏名：</p>
      <p>到着予定日：</p>


      <p>ご注文内容</p>
      
   <div class="order-container">
      <!-- 注文内容 渡邊書き換える　tableないだけ-->
          <section class="order-details">
              <h2>ご注文の商品</h2>
                 <!-- CartSession内の各アイテム情報をループして表示 -->
                <table border="0">
        <tr>
			<c:forEach var="item" items="${orderCart.items}">
	            <th>商品名: ${item.productName}</th>
	            <th>単価: ${item.unitPrice}</th>
	            <th>個数: ${item.quantity}</th>
	            <th>計: ${item.subtotal}</th>
	            <th>商品ID: ${item.subtotal}</th>
            </c:forEach>
        </tr>
        
        <c:forEach var="cart" items="${cartSessionItem}">
          <tr>
                <td>${cart.productName}</td>
                <td class="right-align"><fmt:formatNumber value="${cart.unitPrice}" maxFractionDigits="0" />円　
                </td>
                <td class="right-align">${cart.quantity}</td>
                <td class="right-align"><fmt:formatNumber value="${cart.subtotal}" maxFractionDigits="0" />円　
                </td>
                <td>
                <img src="${pageContext.request.contextPath}/images/Photo/${cart.imageURL}" width = 100 alt="Product Image" />
                </td>


            </tr>
            
        </c:forEach>
      
       
    </table>
    </c:if>
            </section>
    
            <!-- 送付先情報 -->
    
            <!-- 合計金額 -->
            <section class="order-summary">
                
                <div class="summary-item">
                    <span>送料:</span>
                    <span><fmt:formatNumber value="${fee}" maxFractionDigits="0" />円</span>
                </div>
                <div class="summary-item">
                    <span><strong>合計:</strong></span>
                    <span><strong><fmt:formatNumber value="${total}" maxFractionDigits="0" />円</strong></span>
                </div>
                <div class="summary-item">
                    <span>（内税:</span>
                    <span><fmt:formatNumber value="${tax}" maxFractionDigits="0" />円）</span>
                </div>
            </section>
        <a href="${pageContext.request.contextPath}/CategoryServlet">トップページへ戻る</a>
    
  </main>
  <!-- メインここまで -->
        <!-- フッター -->

    <p class="copyright">&copy; 6fun</p>
    <!-- フッターここまで -->
   

<!-- JavaScriptファイルをリンク -->
<script src="js/script.js"></script>

</body>
</html>