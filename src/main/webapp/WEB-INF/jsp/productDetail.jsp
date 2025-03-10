<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${product.productName} - 商品詳細</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>${product.productName}</h1>
    <div class="product-box">
        <img class="product-img" src="${pageContext.request.contextPath}/images/Photo/${product.imageUrl}" alt="${product.productName}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/no-image.jpg';">
        <div class="product-name-info">
            <h3>${product.productName}</h3>
            <p>${product.description}</p>
            <p class="price-info">
                <h2>￥${product.price}</h2>
                <span class="tax-included">(税込)</span>
            </p>
            <p>在庫: ${product.stock}</p>

            <form action="CartServlet" method="post">
                <label for="quantity" class="quantity-label">数量:</label>
                <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.stock}">
                <input type="hidden" name="productId" value="${product.productId}">
                <button type="submit" class="add-to-cart-btn">カートに追加</button>
            </form>
        </div>
    </div>
</body>
</html>
