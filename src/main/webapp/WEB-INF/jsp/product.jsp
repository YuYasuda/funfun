<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>funfun | 商品詳細</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

<!-- ヘッダーここから -->
<jsp:include page="top.jsp"></jsp:include>
<!-- ヘッダーここまで -->

 <!-- メインコンテンツ -->
 <main>
        <p>top > 加工品</p>
    
        <div class="product-box">
            <img src="${pageContext.request.contextPath}/images/Photo/${product.imageUrl}" class="product-img" alt="${product.productName}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/no-image.jpeg';">
    
            <div class="product-name-info">
                <h3>${product.productName}</h3>
    
                <div class="price-info">
                    <h2><fmt:formatNumber value="${product.price}" maxFractionDigits="0" />円 <span class="tax-included">(税込)</span></h2>
                </div>
    
                        
                    
    
                <!-- カートに追加フォーム -->
            <form action="CartServlet" method="post">
            	<div class="detail">
            	<div class="quantity-label">
                <label for="quantity" class="quantity-label">数量:</label>
                <div class="quantity-selector">
	                <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.stock}" step="1">
	                <input type="hidden" name="productId" value="${product.productId}">
	                </div> 
	                </div>
	                <button type="submit" class="add-to-cart-btn">カートに入れる</button>
               
                </div>
            </form>
    		
                    <p class="favorite">おきにいりに登録</p>
                    </div>
            </div>
        </div>
    
        <p>
        ${product.description}
        </p>
    
        <!-- 同じカテゴリの商品 -->
        <div class="product-recommend">
            <strong>同じカテゴリの商品</strong>
            <div class="recommend">
                <figure>
                    <img src="${pageContext.request.contextPath}/images/IMG/2021.jpg" class="recommendation" alt="Image 7">
                    <figcaption>
                        <div class="product-info">
                            <span class="product-name">生ハムスライス</span>
                            <span class="product-price">3000円(税込)</span>
                        </div>
                    </figcaption>
                </figure>
    
                <figure>
                    <img src="${pageContext.request.contextPath}/images/IMG/2022.jpg" class="recommendation" alt="Image 7">
                    <figcaption>
                        <div class="product-info">
                            <span class="product-name">生ハム原木</span>
                            <span class="product-price">3000円(税込)</span>
                        </div>
                    </figcaption>
                </figure>
    
                <figure>
                    <img src="${pageContext.request.contextPath}/images/IMG/2023.jpg" class="recommendation" alt="Image 7">
                    <figcaption>
                        <div class="product-info">
                            <span class="product-name">サラミ</span>
                            <span class="product-price">3000円(税込)</span>
                        </div>
                    </figcaption>
                </figure>
            </div>
        </div>
    </main>
</main>
<!-- メインここまで -->
<!-- フッター -->

<p class="copyright">&copy; 6fun</p>
<!-- フッターここまで -->

<!-- JavaScriptファイルをリンク -->
    <script src="js/script.js"></script>
</body>
</html>