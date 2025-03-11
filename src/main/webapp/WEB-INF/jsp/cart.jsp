<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="model.Cart" %>	
<%@ page import="model.CartSession" %>
<%@ page import="model.CartSummary" %>
<%@ page import="model.CartItem" %>
	

<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun 買い物かご</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .right-align {
            text-align: right;
        }
    </style>
</head>
<body>
    <!-- ヘッダーここから -->

    <jsp:include page="top.jsp" />
    <!-- ヘッダーここまで -->

    <!-- メインここから -->
     
<h2>${userId != null ? userId : "ゲスト."}様</h2>

     <table border="0">
        <tr>

            <th>商品名</th>

            <th>単価</th>
            <th>個数</th>
            <th>計</th>
            <th>　</th>
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

    <div class="cart-actions">
        <a href="${pageContext.request.contextPath}/OrderServlet"><button class="continue-shopping-btn">注文する</button>
        </a>
    </div>
    <div class="cart-actions">
            <a href="<%= request.getContextPath() %>/ClearCartServlet"><button class="continue-shopping-btn">注文をキャンセル</button>
            </a>
    </div>
    <!-- メインここまで -->
    <!-- JavaScriptファイルをリンク -->
    <script src="js/script.js"></script> 


</body>
</html>
