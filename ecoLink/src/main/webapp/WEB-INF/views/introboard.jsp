<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/intro.css">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp"%>
<%@ include file="chatbot.jsp"%>
    <section id="intro_contents">
        <div class="intro_title">
            <h3>업사이클링이란?</h3>
        </div>
        <div class="intro_title_text">
            <p class="title_text">
                폐기물 같이 더 이상 쓸모가 없어져 버려진 물건을 다시 새로운 디자인으로 재해석해 환경적이면서도 예술적인 가치를 높이는 재활용 방식을 말합니다.
            </p>
        </div>
        <div class="intro_detail">
            <p class="img">
                <img src="images/upcycle.jpg">
            </p>
            <div class="intro_detail_text">
                <p class="detail_text">
                    일반적으로 리사이클링은 소비된 제품이나 재료를 분해하여 비슷한 종류의 제품이나 재료로 재생산하는 과정을 의미합니다. 하지만 업사이클링은 기존 제품이나 재료를 그대로 유지하면서 새로운 디자인이나 기능을 부여하여 창의적인 제품을 만들어냅니다. 즉, 원래 제품의 가치를 높이는 방식으로 재활용을 수행하는 것입니다.
                </p>
            </div>
        </div>
        <div class="intro_goodpoint">         
            <div class="intro_goodpoint_text">
                <p class="goodpoint_title">업사이클링의 좋은점은?</p>
                <p class="goodpoint_text">
                    기존 제품이나 재료를 활용하여 새로운 제품을 만들기 때문에  추가적인 자원 투입이 적습니다. 즉, 새로운 자원을 채굴하거나 가공하는 과정을 줄여줌으로써 자원을 절약할 수 있고 땅에 버려지는 쓰레기량을 감소시켜 환경을 보호할 수 있습니다. 
                    또한  업사이클링은 환경 문제에 대한 인식을 높이고, 개인과 기업의 환경 책임감을 촉진합니다. 재활용과 업사이클링을 통해 자원의 유용성을 극대화함으로써 자연 환경의 보호와 지속 가능한 개발에 대한 중요성을 알리는 기회를 제공합니다.
                </p>
            </div>
                <p class="img">
                    <img src="images/upcycleex.jpg">
                </p>
            
        </div>
    </section>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>
