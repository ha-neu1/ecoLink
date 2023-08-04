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
</head>
<body>
	<div class="board_area">
		<div class="board_title">
			<div class="page_name">
				<strong>브랜드 홍보 게시판</strong>
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
				<input type="text" id="searchtext" placeholder="검색어를 입력하세요">
				<button onclick="search()">검색</button>
			</div>
		</div>

		<div class="boardMain">
			<c:forEach items="${list}" var="dto">
				<div class="boardList">
					<h3 class="memId">
						<a href="/brandpromodetail?entCrn=${dto.entCrn}">${dto.memNick}</a>
					</h3>
					<h4 class="boardTitle">
						<span class="fa fa-star star-active"></span> ${dto.avgRate}
					</h4>
					<a href="/brandpromodetail?entCrn=${dto.entCrn}"><img
						class="boardImage" src="${dto.entdMainPic}"
						onerror="this.onerror=null; this.src='https://buntingmagnetics.com/wp-content/uploads/2015/04/400x300.gif';"></a>
					<div class="entdShort">
						<p class="boardCont">${dto.entdShort}</p>
					</div>
				</div>
			</c:forEach>

		</div>

		<br> <br>
		<div class="page_number">
			<c:choose>
				<c:when test="${startpage == 1}">
					<a class="prev disabled"
						href="/brandpromolist?page=${startpage - 1}" tabindex="-1"
						aria-disabled="true">이전</a>
				</c:when>
				<c:otherwise>
					<a class="prev" href="/brandpromolist?page=${startpage - 1}">이전</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startpage}" end="${endpage}">
				<c:choose>
					<c:when test="${i == currentCpage}">
						<a class="page active" href="/brandpromolist?page=${i}">${i}</a>
					</c:when>
					<c:otherwise>
						<a class="page" href="/brandpromolist?page=${i}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${totalPage != endpage}">
					<a class="next" href="/brandpromolist?page=${endpage + 1}">다음</a>
				</c:when>
				<c:otherwise>
					<a class="next disabled" href="/brandpromolist?page=${endpage + 1}">다음</a>
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
			location.href = "/brandpromolist?page=1&order=" + order + "&search=" + value;
		}
	}
</script>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>