<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브랜드 홍보 리스트</title>
<script src="../js/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="../css/brandpromolist.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
<%@ include file="header.jsp"%>
<%@ include file="chatbot.jsp"%>
</head>
<body>
	<div class="board_area">
		<div class="board_title">
			<div class="page_name">
				<strong>브랜드 소개</strong>
			</div>
		</div>
		<hr class="hr_bold">

		<div class="search_container">
			<div class="boardSort">
				<c:if test="${order eq 'latest'}">
					<select id="sortSelect">
						<option value="latest" selected>최신순</option>
						<option value="rate">평점순</option>
					</select>
				</c:if>
				<c:if test="${order eq 'rate'}">
					<select id="sortSelect">
						<option value="latest">최신순</option>
						<option value="rate" selected>평점순</option>
					</select>
				</c:if>

			</div>
			<div class="searchBox">
				<input type="text" id="searchtext" placeholder="브랜드명으로 검색">
				<button onclick="search()">검색</button>
			</div>
		</div>

		<div class="boardMain">
			<c:forEach items="${list}" var="dto">
				<div class="boardList">
					<div class="titlerate">
						<div class="titlenamediv">
							<a class="titlename"
								href="/brandpromodetail?entCrn=${dto.entCrn}">${dto.memNick}</a>
						</div>
						<div class="ratediv">
							<span class="fa fa-star star-active"></span> ${dto.avgRate}
						</div>
					</div>
					<hr style="margin-top: 3px; margin-bottom: 5px;">
					<a href="/brandpromodetail?entCrn=${dto.entCrn}"><img
						class="boardImage" src="${dto.entdMainPic}"
						onerror="this.onerror=null; this.src='https://buntingmagnetics.com/wp-content/uploads/2015/04/400x300.gif';"></a>
					<hr style="margin-bottom: 5px;">
					<div class="entdShort">
						<p class="boardCont" onclick="opendetail('${dto.entCrn}')">${dto.entdShort}</p>
					</div>
				</div>
			</c:forEach>

		</div>

		<br> <br>
		<div class="page_number">
			<c:choose>
				<c:when test="${startpage == 1}">
					<a class="prev disabled"
						<c:choose>
					<c:when test="${not empty order && not empty search}">href="/brandpromolist?page=${startpage - 1}&order=${order}&search=${search}"</c:when>
					<c:when test="${not empty order && empty search}">href="/brandpromolist?page=${startpage - 1}&order=${order}"</c:when>
					<c:when test="${empty order && not empty search}">href="/brandpromolist?page=${startpage - 1}&order=latest&search=${search}"</c:when>
					<c:otherwise>
					href="/brandpromolist?page=${startpage - 1}"
					</c:otherwise>
					</c:choose>
						tabindex="-1" aria-disabled="true">이전</a>
				</c:when>
				<c:otherwise>
					<a class="prev"
						<c:choose>
					<c:when test="${not empty order && not empty search}">href="/brandpromolist?page=${startpage - 1}&order=${order}&search=${search}"</c:when>
					<c:when test="${not empty order && empty search}">href="/brandpromolist?page=${startpage - 1}&order=${order}"</c:when>
					<c:when test="${empty order && not empty search}">href="/brandpromolist?page=${startpage - 1}&order=latest&search=${search}"</c:when>
					<c:otherwise>
					href="/brandpromolist?page=${startpage - 1}"
					</c:otherwise>
					</c:choose>>이전</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startpage}" end="${endpage}">
				<c:choose>
					<c:when test="${i == currentCpage}">
						<a class="page active"
							<c:choose>
					<c:when test="${not empty order && not empty search}">href="/brandpromolist?page=${i}&order=${order}&search=${search}"</c:when>
					<c:when test="${not empty order && empty search}">href="/brandpromolist?page=${i}&order=${order}"</c:when>
					<c:when test="${empty order && not empty search}">href="/brandpromolist?page=${i}&order=latest&search=${search}"</c:when>
					<c:otherwise>
					href="/brandpromolist?page=${i}"
					</c:otherwise>
					</c:choose>>${i}</a>
					</c:when>
					<c:otherwise>
						<a class="page"
							<c:choose>
					<c:when test="${not empty order && not empty search}">href="/brandpromolist?page=${i}&order=${order}&search=${search}"</c:when>
					<c:when test="${not empty order && empty search}">href="/brandpromolist?page=${i}&order=${order}"</c:when>
					<c:when test="${empty order && not empty search}">href="/brandpromolist?page=${i}&order=latest&search=${search}"</c:when>
					<c:otherwise>
					href="/brandpromolist?page=${i}"
					</c:otherwise>
					</c:choose>>${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${totalPage != endpage}">
					<a class="next"
						<c:choose>
					<c:when test="${not empty order && not empty search}">href="/brandpromolist?page=${endpage + 1}&order=${order}&search=${search}"</c:when>
					<c:when test="${not empty order && empty search}">href="/brandpromolist?page=${endpage + 1}&order=${order}"</c:when>
					<c:when test="${empty order && not empty search}">href="/brandpromolist?page=${endpage + 1}&order=latest&search=${search}"</c:when>
					<c:otherwise>
					href="/brandpromolist?page=${endpage + 1}"
					</c:otherwise>
					</c:choose>>다음</a>
				</c:when>
				<c:otherwise>
					<a class="next disabled"
						<c:choose>
					<c:when test="${not empty order && not empty search}">href="/brandpromolist?page=${endpage + 1}&order=${order}&search=${search}"</c:when>
					<c:when test="${not empty order && empty search}">href="/brandpromolist?page=${endpage + 1}&order=${order}"</c:when>
					<c:when test="${empty order && not empty search}">href="/brandpromolist?page=${endpage + 1}&order=latest&search=${search}"</c:when>
					<c:otherwise>
					href="/brandpromolist?page=${endpage + 1}"
					</c:otherwise>
					</c:choose>>다음</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
<script type="text/javascript">
	$("#sortSelect").on("change", function() {
		location.href = "/brandpromolist?page=1&order=" + $(this).val();

	});

	function search() {
		var value = $("#searchtext").val();
		var order = $("#sortSelect").val();
		if (!value) {
			alert("검색어를 입력해주세요.");
		} else {
			location.href = "/brandpromolist?page=1&order=" + order
					+ "&search=" + value;
		}
	}

	function opendetail(e) {
		location.href = "/brandpromodetail?entCrn=" + e;
	}
</script>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>