<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun | 総合売上ランキング</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<!-- ヘッダーここから -->
<jsp:include page="top.jsp"></jsp:include>
<!-- ヘッダーここまで -->

<body>
    <h3 class="ranking-title">総合売上ランキング</h3>


    <div class="all-item-ranking">
    	<c:forEach var="product" items="${rankingProducts}" varStatus="status">
    		<!-- 15位までのみ表示 -->
    		<c:if test="${status.index < 15 }">
		        <figure>
		        	<!-- 順位に応じたランキング画像を動的に設定 -->
			        <img class="overlay-image"
			         src="${pageContext.request.contextPath}/images/IMG/rank${status.index + 1}.png" alt="Rank ${status.index + 1}">
			          <a href="ProductDetailServlet?productId=${product.productId}">
			               <img src="${pageContext.request.contextPath}/images/Photo/${product.imageUrl}"  class="ranking-img" alt="${product.productName}" 
			               onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/no-image.jpeg';">
			          </a>
			            <figcaption>
			                <div class="item-info">
			                    <span class="item-name">${product.productName}</span>
			                    <span class="item-price">
			                    <fmt:formatNumber value="${product.price}" maxFractionDigits="0" />円(税込)</span>
			                </div>
			            </figcaption>
		        </figure>
	        </c:if>
        </c:forEach>

     </main>
    <!-- メインここまで -->
    
    <!-- JavaScriptファイルをリンク -->
    <script src="js/script.js"></script>    
    
</body>
</html>