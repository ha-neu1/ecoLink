<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.BoardCommentDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infopostdetail.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>

<script src="js/jquery-3.6.4.min.js"></script>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="head_wrap">
		<div class="left_wrap">
			<div class="user_image">
				<i class="fa-regular fa-user"></i>
			</div>
			<div class="nickname_date_views">
				<div class="nickname">닉네임</div>
				<div class="date_views">
					<div class="date" name="boardRegtime">${detaildto.boardRegtime}</div>
					<div class="views">
						<i class="fa-regular fa-eye"></i>${detaildto.boardViewCount}</div>
				</div>
			</div>
		</div>
		<div class="right_wrap">
			<div class="like">
				<i class="fa-regular fa-heart"></i>2
			</div>
		</div>
	</div>
	<div class="post_image">
		<ul class="slides">
			<c:forEach var="imageUrl" items="${imageUrls}">
				<li><img src="${imageUrl}" alt=""></li>
			</c:forEach>
		</ul>
		<p class="controller">

			<!-- &lang: 왼쪽 방향 화살표
      &rang: 오른쪽 방향 화살표 -->
			<span class="prev">&lang;</span> <span class="next">&rang;</span>
		</p>
	</div>
	<div class="post_tit" name="boardTitle">${detaildto.boardTitle }</div>
	<div class="post_contents" name="boardContents">${detaildto.boardContents }</div>
	<div class="post_comment_header">댓글</div>
	<div class="post_comment">
		<form id="boardComment">
			<input type="text" placeholder="댓글을 입력해 주세요." class="comment"
				name="comment">
			<button class="commentbtn">등록</button>
			<input type="hidden" name="boardId" value="${detaildto.boardId}" />
		</form>
	</div>


	<div class="comment_list" id="commentList">
		<div class="bcWriterAndRegtime">
			<div class="bcMemNick"></div>
			<div class="bcRegtime"></div>
		</div>
		<div class="bcContentsAndBtn">
			<div class="bcContents"></div>
			<c:if test="${dto.memId eq logininfo.memId}">
				<div class="bcBtn">
					<input type="button" value="수정"> <input type="button"
						value="삭제">
				</div>
			</c:if>
		</div>
		<ul class="bcPagination ">
			<c:choose>
				<c:when test="${startpage == 1}">
					<li class="page-item disabled"><a class="page-link"
						href="/infopostdetail?boardId=${boardId}&page=${startpage - 1}&focus=true"
						tabindex="-1" aria-disabled="true">이전</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/infopostdetail?boardId=${boardId}&page=${startpage - 1}&focus=true">이전</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startpage}" end="${endpage}">
				<c:choose>
					<c:when test="${i == currentCpage}">
						<li class="page-item active"><a class="page-link"
							href="/infopostdetail?boardId=${boardId}&page=${i}&focus=true">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/infopostdetail?boardId=${boardId}&page=${i}&focus=true">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${totalPage != endpage}">
					<li class="page-item"><a class="page-link"
						href="/infopostdetail?boardId=${boardId}&page=${endpage + 1}&focus=true">다음</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled"><a class="page-link"
						href="/infopostdetail?boardId=${boardId}&page=${endpage + 1}&focus=true">다음</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>





	<script>
	$(document).ready(function () {
	    var page = 1; // Set the initial page value

	    $('#boardComment').submit(function (e) {
	        e.preventDefault();
	        var formData = $(this).serialize();
	        var data = {
	                "comments": [
	                    {
	                        "bcId": 1,
	                        "boardId": 55,
	                        "bcContents": "Sample Comment 1"
	                    },
	                    {
	                        "bcId": 2,
	                        "boardId": 55,
	                        "bcContents": "Sample Comment 2"
	                    }
	                ]
	            };
	        $.ajax({
	            url: '/insertBoardComment',
	            type: 'POST',
	            data: formData,
	            success: function(response) {
	                alert("댓글이 작성되었습니다.")
	                // After successful comment submission, fetch the comments using AJAX
	                $.ajax({
	                    url: '/getBoardComments',
	                    type: 'GET',
	                    data: { boardId: "${detaildto.boardId}", page: page }, // Pass the boardId and page as parameters
	                    success: function (response) {
	                        console.log("Response:", response);
	                        var data = JSON.parse(response);
	                        console.log("Data:", data);
	                        
	                        // Check if "comments" exists in the data object
	                        if (data.hasOwnProperty("comments")) {
	                            var comments = data.comments;
	                            console.log("Comments:", comments);
	
	                            // Clear existing comments (optional, depending on your requirement)
	                            $("#commentList").empty();

	                            // Append each comment to the container
	                            comments.forEach(function (comment) {
	                            	
	                                var commentDiv = $("<div>").addClass("comment");
	                                var memNick = $("<div>").addClass("bcMemNick").text(comment.memNick);
	                                var bcRegtime = $("<div>").addClass("bcRegtime").text(comment.bcRegtime);
	                                var bcContents = $("<div>").addClass("bcContents").text(comment.bcContents);

	                                // Append the elements to the comment container
	                                commentDiv.append(memNick, bcRegtime, bcContents);
	                                $("#commentList").append(commentDiv);
	                            });
	                        } else {
	                            console.log("No comments found in the response.");
	                        }
	                    }
	                });
	            }
	        });
	    });
	});
	
	    </script>
	<script src="js/infopostdetail.js"></script>

</body>
</html>