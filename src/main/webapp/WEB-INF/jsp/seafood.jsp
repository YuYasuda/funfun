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
    <title>funfun | 魚介水産ランキング</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>
    <!-- ヘッダーここから -->
   <jsp:include page="top.jsp"></jsp:include>
    <!-- ヘッダーここまで -->

    <h3 class="ranking-title">魚介水産ランキング</h3>


    <div class="all-item-ranking">
        <figure>
        <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank1.png" alt="Icon">
          <a href="item.html" target="_blank">
               <img src="${pageContext.request.contextPath}/images/IMG/1024.jpg" class="ranking-img" alt="Image 1">
          </a>
            <figcaption>
                <div class="item-info">
                    <span class="item-name">小イカ　サイズ大小混ざり</span>
                    <span class="item-price">1000円(税込)</span>
                </div>
            </figcaption>
        </figure>

        <figure>
            <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank2.png" alt="Icon">
            <img src="${pageContext.request.contextPath}/images/IMG/1025.jpg" class="ranking-img" alt="Image 2">
              <figcaption>
                  <div class="item-info">
                      <span class="item-name">数の子　500g</span>
                      <span class="item-price">6000円(税込)</span>
                  </div>
              </figcaption>
          </figure>

        <figure>
            <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank3.png" alt="Icon">
            <img src="${pageContext.request.contextPath}/images/IMG/1026.jpg" class="ranking-img" alt="Image 3">
              <figcaption>
                  <div class="item-info">
                      <span class="item-name">生イイダコ　1㎏</span>
                      <span class="item-price">2000円(税込)</span>
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/1027.jpg" class="ranking-img" alt="Image 4">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank4.png" alt="Icon">
                  <div class="item-info">
                      <span class="item-name">焼き海苔　Ｍ缶</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>     
                 
        <figure>
            
            <img src="${pageContext.request.contextPath}/images/IMG/7101.jpeg" class="ranking-img" alt="Image 5">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank5.png" alt="Icon">
                  <div class="item-info">
                      <span class="item-name">いろはす</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>
      
        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/7102.jpeg" class="ranking-img" alt="Image 6">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank6.png" alt="Icon">
                  <div class="item-info">
                      <span class="item-name">天然水</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>
            
        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/7103.jpeg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank7.png" alt="Icon">
                  <div class="item-info">
                      <span class="item-name">熊野古道水</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/7201.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank8.png" alt="Icon">
                  <div class="item-info">
                      <span class="item-name">ネスカフェゴールド</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/2020.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank9.png" alt="Icon">
                  <div class="item-info">
                    <a href="product.html" target="_blank"><span class="item-name">チキンナゲット</span></a>
                      <span class="item-price">980円(税込)</span>
                
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/2021.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank10.png" alt="Icon">
                  <div class="item-info">
                      <span class="item-name">生ハムスライス</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/2022.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank11.png" alt="11位">
                  <div class="item-info">
                      <span class="item-name">生ハム原木</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/2023.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank12.png" alt="12位">
                  <div class="item-info">
                      <span class="item-name">サラミ</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>
        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/2023.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank13.png" alt="13位">
                  <div class="item-info">
                      <span class="item-name">サラミ</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/2023.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank14.png" alt="14位">
                  <div class="item-info">
                      <span class="item-name">サラミ</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>

        <figure>
            <img src="${pageContext.request.contextPath}/images/IMG/2023.jpg" class="ranking-img" alt="Image 7">
              <figcaption>
                <img class="overlay-image" src="${pageContext.request.contextPath}/images/IMG/rank15.png" alt="15位">
                  <div class="item-info">
                      <span class="item-name">サラミ</span>
                      <span class="item-price">3000円(税込)</span>
                  </div>
              </figcaption>
        </figure>
     </main>
    <!-- メインここまで -->
    
    <!-- JavaScriptファイルをリンク -->
    <script src="js/script.js"></script>    
    
</body>
</html>