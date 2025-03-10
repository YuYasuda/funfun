<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

    <jsp:include page="top.jsp" />
    <!-- ヘッダーここまで -->

    <!-- メインここから -->
 
    <main>
        <div class="hero-img">
        	<img src="${pageContext.request.contextPath}/images/IMG/hero_1.jpg" alt="hero">
        </div>

    <strong>今週のおすすめ</strong>
   
        <div class="recommend">
	        <c:forEach var="product" items="${products}">
	            <figure>
	            
	           	<!-- 商品詳細ページへのリンク -->
	                <a href="ProductDetailServlet?productId=${product.productId}">
	                    <img class="recommendation" src="${pageContext.request.contextPath}/images/Photo/${product.imageUrl}" 
	                    alt="${product.productName}" 
	                    onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/no-image.jpeg';">
		                <figcaption>
		                    <div class="item-info">
		                        <span class="item-name">${product.productName}<br></span>
								<!-- <span class="item-price">${product.price}</span>-->
								<span class="item-price">
								<fmt:formatNumber value="${product.price}" maxFractionDigits="0" />円(税込)</span>
							</div>
		                </figcaption>
	                </a>
	            </figure>
	       	</c:forEach>                      
        </div>
          
    </main>
    <!-- メインここまで -->
    
</body>
</html>