<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
    
    <header>
        <div class="top">
            <h1 class="logo"><a href="${pageContext.request.contextPath}/CategoryServlet">funfun</a></h1>
            <div class="menu">
                <button class="button-container">
                    <div class="menu-button-text">おきにいり</div>
                </button>
                
                <c:choose>
	                <c:when test="${not empty sessionScope.userId}">
		                <!-- ログイン済みの場合 -->
		                <button class="button-container">
		                    <div class="menu-button-text"><a href="${pageContext.request.contextPath}/mypage">マイページ</a></div>
		                </button>
		                <button class="button-container">
		                    <div class="menu-button-text"><a href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a></div>
		                </button>
	                </c:when>
	                <c:otherwise>
		                <!-- 未ログインの場合 -->
		                <button class="button-container">
		                    <div class="menu-button-text"><a href="${pageContext.request.contextPath}/UserLoginServlet">ログイン</a></div>
		                </button>
		                <button class="button-container">
		                    <div class="menu-button-text"><a href="${pageContext.request.contextPath}/UserRegistrationServlet">会員登録</a></div>
		                </button>
	                </c:otherwise>
                </c:choose>

                <div class="icon-container">
                	<a href="${pageContext.request.contextPath}/CartServlet">
                    	<img src="${pageContext.request.contextPath}/images/IMG/cart_img.png" alt="お買い物かご">
                    	<p>カート</p>
                   	</a>
                </div>

                <div class="icon-container">
                    <img src="${pageContext.request.contextPath}/images/IMG/mail_img.png" alt="お問い合わせ">
                    <p>問い合わせ</p>
                </div>
            </div>
            <button class="search-btn">
                <i class="search-icon"></i>
                <span><a href="ProductSearchByQueryServlet">検索></span></a>
            </button>
        </div>

            </div>
                                <!-- モバイル用ハンバーガーメニュー デスクトップでは非表示-->
                                 <div class="menu-nav">
        
                                     <div class="hamburger-menu-container">
                                        <div class="menu-toggle" id="menu-toggle">
                                        <span class="bar"></span>
                                        <span class="bar"></span>
                                        <span class="bar"></span>
                                        </div>
                                    </div>
                                    <nav class="mobile-nav">
                                        <ul>
                                        <li><a href="#">ログイン・会員登録</a></li>
                                        <li><a href="#">買い物かご</a></li>
                                        <li><a href="#">売上ランキング</a></li>
                                        <li><a href="#">魚・水産</a></li>
                                        <li><a href="#">肉</a></li>
                                        <li><a href="#">果物・野菜</a></li>
                                        <li><a href="#">米・麺</a></li>
                                        <li><a href="#">加工品</a></li>
                                        <li><a href="#">スイーツ</a></li>
                                        <li><a href="#">飲料</a></li>
                                        <li><a href="#">おきにいり</a></li>
                                        <li><a href="#">マイページ</a></li>
                                        <li><a href="#">お問い合わせ</a></li>
                                        </ul>
                                    </nav>
                                </div>
                          
                                <!-- モバイル用ハンバーガーメニュー ここまで-->   

        </div>




        <nav class="navbar">
            <ul>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/CategoryServlet"><img src="${pageContext.request.contextPath}/images/IMG/ranking.png" alt="ランキング" class="nav-icon"></a>
                    <div class="sub-menu">
                        <a href="${pageContext.request.contextPath}/CategoryServlet" class="ranking-button"><p>売上ランキング</p></a>
                    </div>
                </li>
                <li class="nav-item"><a href=""><img src="${pageContext.request.contextPath}/images/IMG/seafood.png" alt="魚介・水産" class="nav-icon"></a>
                    <div class="sub-menu">
                        <strong class="sub-title"><a href="">魚介・水産</a></strong>
                        <a href="CategoryServlet?type=魚">魚</a>
                        <a href="CategoryServlet?type=甲殻類">甲殻類</a>
                        <a href="CategoryServlet?type=貝">貝類</a>
                        <a href="CategoryServlet?type=その他海産物">その他</a>
                    </div>
                </li>
                <li class="nav-item"><a href=""><img src="${pageContext.request.contextPath}/images/IMG/meat.png" alt="肉" class="nav-icon"></a>
                    <div class="sub-menu">
                        <strong class="sub-title"><a href="">肉</a></strong>
                        <a href="CategoryServlet?type=牛肉">牛肉</a>
                        <a href="CategoryServlet?type=豚肉">豚肉</a>
                        <a href="CategoryServlet?type=鶏肉">鶏肉</a>
                        <a href="CategoryServlet?type=加工品　ソーセージ、ハム">加工品・ソーセージ・ハム</a>
                    </div>
                </li>
                <li class="nav-item"><a href=""><img src="${pageContext.request.contextPath}/images/IMG/vegetbles.png" alt="果物・野菜" class="nav-icon"></a>
                    <div class="sub-menu">
                        <strong class="sub-title"><a href="">果物・野菜</a></strong>
                        <a href="CategoryServlet?type=果物">果物</a>
                        <a href="CategoryServlet?type=果物加工品">果物加工品</a>
                        <a href="CategoryServlet?type=野菜">野菜</a>
                        <a href="CategoryServlet?type=その他果物・野菜">その他</a>
                    </div>
                </li>
                <li class="nav-item"><a href=""><img src="${pageContext.request.contextPath}/images/IMG/rice_noodle.png" alt="米・麺" class="nav-icon"></a>
                    <div class="sub-menu">
                        <strong class="sub-title"><a href="">米・麺</a></strong>
                        <a href="CategoryServlet?type=米">米</a>
                        <a href="CategoryServlet?type=麺類">麺</a>
                        <a href="CategoryServlet?type=その他米・麺類">その他</a>
                    </div>
                </li>
                <li class="nav-item"><a href=""><img src="${pageContext.request.contextPath}/images/IMG/processfood.png" alt="加工品" class="nav-icon"></a>
                    <div class="sub-menu">
                        <strong class="sub-title"><a href="">加工品</a></strong>
                        <a href="CategoryServlet?type=缶詰・瓶詰">缶詰・瓶詰</a>
                        <a href="CategoryServlet?type=冷凍食品">冷凍食品</a>
                        <a href="CategoryServlet?type=惣菜">惣菜</a>
                        <a href="CategoryServlet?type=その他加工品">その他</a>
                    </div>
                </li>
                <li class="nav-item"><a href=""><img src="${pageContext.request.contextPath}/images/IMG/sweets.png" alt="スイーツ" class="nav-icon"></a>
                    <div class="sub-menu">
                        <strong class="sub-title">スイーツ</strong>
                        <a href="CategoryServlet?type=洋菓子">洋菓子</a>
                        <a href="CategoryServlet?type=和菓子">和菓子</a>
                        <a href="CategoryServlet?type=アイスクリーム・氷菓子">アイスクリーム・氷菓</a>
                        <a href="CategoryServlet?type=その他スイーツ">その他</a>
                    </div>
                </li>
                <li class="nav-item"><a href=""><img src="${pageContext.request.contextPath}/images/IMG/drink.png" alt="飲料" class="nav-icon"></a>
                    <div class="sub-menu">
                        <strong class="sub-title">飲料</strong>
                        <a href="CategoryServlet?type=水・ミネラルウォーター">水・ミネラルウォーター</a>
                        <a href="CategoryServlet?type=コーヒー">コーヒー</a>
                        <a href="CategoryServlet?type=果物・野菜ジュース">果物・野菜ジュース</a>
                        <a href="CategoryServlet?type=その他飲み物">その他</a>
                    </div>
                </li>
            </ul>
        </nav>
       
    </header>