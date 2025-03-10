<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="subtotalWithoutTax" value="${subtotal - tax}" />


<%@ page import="model.Cart" %>	
<%@ page import="model.CartSession" %>
<%@ page import="model.CartSummary" %>
<%@ page import="model.CartItem" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<!--//オリジナルは今野-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun | 注文内容確認</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
<!--//渡邊追記スタイル-->
    <style>
        .right-align {
            text-align: right;
        }
    </style>

</head>
<body class="order">
<!-- ヘッダーここから -->
<jsp:include page="top.jsp"></jsp:include>
<!-- ヘッダーここまで -->


    <main>
        <div class="order-container">
            <h1>注文内容の確認</h1>
            <!-- 注文内容 渡邊書き換える　tableないだけ-->
            <section class="order-details">
                <h2>ご注文の商品</h2>
                <c:if test="${not empty orderCart.items}">
                	<table border="0">
				        <tr>
				
				            <th>商品名</th>
				            <th>単価</th>
				            <th>個数</th>
				            <th>計</th>
				            <th>　</th>
				        </tr>
        
				        <c:forEach var="item" items="${orderCart.items}">
				          <tr>
				                <td>${item.productName}</td>
				                <td class="right-align"><fmt:formatNumber value="${item.unitPrice}" maxFractionDigits="0" />円　
				                </td>
				                <td class="right-align">${item.quantity}</td>
				                <td class="right-align"><fmt:formatNumber value="${item.subtotal}" maxFractionDigits="0" />円　
				                </td>
				                <td>
				                <img src="${pageContext.request.contextPath}/images/Photo/${item.imageURL}" width = 100 alt="Product Image" />
				                </td>
				
				
				            </tr>
				            
				        </c:forEach>
      
       
				    </table>
			    </c:if>
			    <c:if test="${empty orderCart.items}">
				    <p>カートに商品がありません。</p>
				</c:if>
            </section>
    
            <!-- 送付先情報 -->
            <section class="shipping-info">
                <h2>送付先情報</h2>
                <div class="info-item">
                    <label for="last-name">苗字:</label>
                    <input type="text" id="last-name" name="last-name" placeholder="田中">
                </div>
                <div class="info-item">
                    <label for="first-name">名前:</label>
                    <input type="text" id="first-name" name="first-name" placeholder="太郎">
                </div>
                <div class="info-item">
                    <label for="address">住所:</label>
                    <input type="text" id="address" name="address" placeholder="東京都新宿区西新宿1-1-1">
                </div>
                <div class="info-item">
                    <label for="phone">電話番号:</label>
                    <input type="tel" id="phone" name="phone" placeholder="090-1234-5678">
                </div>
            </section>
    
            <!-- 決済方法 -->
            <section class="payment-method">
                <h2>決済方法</h2>
                <div class="payment-options">
                    <div class="payment-option">
                        <input type="radio" id="credit-card" name="payment-method" value="credit-card">
                        <label for="credit-card">クレジットカード</label>
                    </div>
                    <div class="payment-option">
                        <input type="radio" id="bank-transfer" name="payment-method" value="bank-transfer">
                        <label for="bank-transfer">銀行振込</label>
                    </div>
                    <div class="payment-option">
                        <input type="radio" id="cash-on-delivery" name="payment-method" value="cash-on-delivery">
                        <label for="cash-on-delivery">代金引換</label>
                    </div>
                </div>
    
                <!-- クレジットカード番号入力フィールド -->
<!--                <div class="credit-card-info" id="credit-card-info">-->
<!--                    <label for="card-number">カード番号:</label>-->
<!--                    <input type="text" id="card-number" name="card-number" placeholder="XXXX-XXXX-XXXX-XXXX">-->
<!--                </div>-->
            </section>
    
            <!-- 合計金額 -->
            <section class="order-summary">
                
                <div class="summary-item">
                    <span>小計（税抜き）:</span>
                    <span><fmt:formatNumber value="${subtotalWithoutTax}" maxFractionDigits="0" />円</span>
                </div>
                <div class="summary-item">
                    <span>（内税:</span>
                    <span><fmt:formatNumber value="${tax}" maxFractionDigits="0" />円）</span>
                </div>
                
                <div class="summary-item">
                    <span>送料:</span>
                    <span><fmt:formatNumber value="${shippingFee}" maxFractionDigits="0" />円</span>
                </div>
                <div class="summary-item">
                    <span><strong>合計:</strong></span>
                    <span><strong><fmt:formatNumber value="${total}" maxFractionDigits="0" />円</strong></span>
                </div>
            </section>
    
            <!-- 注文確定ボタン -->
            <div class="order-actions">
            	<form action="${pageContext.request.contextPath}/OrderServlet" method="post">
				    <input type="hidden" name="action" value="finalize">
				    <button type="submit" class="confirm-order-btn">注文を確定する</button>
				</form>
            </div>
        </div>
    </main>

       <!-- メインここまで -->
        
       <!-- フッター -->

    <p class="copyright">&copy; 6fun</p>
    <!-- フッターここまで -->
   

<!-- JavaScriptファイルをリンク -->
<script src="js/script.js"></script>

</body>
</html>