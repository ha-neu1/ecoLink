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
				<div class="nickname">${detaildto.memNick}</div>
				<div class="date_views">
					<div class="date" name="boardRegtime">${detaildto.boardRegtime}</div>
					<div class="views">
						<i class="fa-regular fa-eye"></i>${detaildto.boardViewCount}</div>
				</div>
			</div>
		</div>
		<div class="right_wrap">
			<div class="like">
				<button id="likeBoard" class="likeBoard">
					<c:choose>
						<c:when test="${not hasLiked}">
							<i id="likeIcon" class="fa-regular fa-heart"></i>
						</c:when>
						<c:otherwise>
							<i id="likeIcon" class="fa-solid fa-heart"></i>
						</c:otherwise>
					</c:choose>
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
	<c:forEach items="${clist }" var="dto">
		<c:choose>
			<c:when test="${dto.bcReLevel == 0 }">
				<div class="commentContentWrap">
					<div class="commentContentProfile">
						<i class="fa-regular fa-user"></i>
					</div>
					<div class="commentContentRight">
						<div class="commentNickName">${dto.memNick}</div>
						<div class="commentContents">${dto.bcContents}</div>
						<div class="commentContentFooter">
							<div class="dateReplyWrap">
								<div class="commentDate">${dto.bcRegtime}</div>
								<form id="replyComment">
									<div class="commentReply">
										<button class="replyWrite">답글쓰기</button>
									</div>
									<input type="hidden" name="boardId"
										value="${detaildto.boardId}" /> <input type="hidden"
										name="bcRef" value="${dto.bcRef}" />
							</div>
							<div class="replyContents">
								<input type="text" placeholder="답글을 입력해 주세요." class="reply"
									name="reply">
								<button class="replyBtn">등록</button>
							</div>
							</form>
						</div>
					</div>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${dto.bcReLevel == 1 }">
				<div class="replyWrap">
					<div class="commentContentProfile">
						<i class="fa-regular fa-user"></i>
					</div>
					<div class="commentContentRight">
						<div class="commentNickName">${dto.memNick}</div>
						<div class="commentContents">${dto.bcContents}</div>
						<div class="commentContentFooter">
							<div class="commentDate">${dto.bcRegtime}</div>
						</div>
					</div>
				</div>
			</c:when>
		</c:choose>
	</c:forEach>
	<div class="mt-3">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${startpage == 1}">
					<li class="page-item disabled"><a class="page-link"
						href="/infopostdetail?boardId=${bId}&page=${startpage - 1}&focus=true"
						tabindex="-1" aria-disabled="true">이전</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/infopostdetail?boardId=${bId}&page=${startpage - 1}&focus=true">이전</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startpage}" end="${endpage}">
				<c:choose>
					<c:when test="${i == currentCpage}">
						<li class="page-item active"><a class="page-link"
							href="/infopostdetail?boardId=${bId}&page=${i}&focus=true">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/infopostdetail?boardId=${bId}&page=${i}&focus=true">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${totalPage != endpage}">
					<li class="page-item"><a class="page-link"
						href="/infopostdetail?boardId=${bId}&page=${endpage + 1}&focus=true">다음</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled"><a class="page-link"
						href="/infopostdetail?boardId=${bId}&page=${endpage + 1}&focus=true">다음</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
	<script>
$(document).ready(function() {

  // Code for handling board comment form submission
  $('#boardComment').submit(function(e) {
    e.preventDefault();
    var formData = $(this).serialize();
    $.ajax({
      url: '/insertBoardComment',
      type: 'POST',
      data: formData,
      success: function(response) {
        $('.comment').val('');
        alert("Your comment has been written");
      },
      error: function(xhr, status, error) {
        if (xhr.status === 401) {
          alert("Login is required. You will be taken to the login page.");
          window.location.href = '/login'; // Redirect to the login page
        } else {
          alert("Failed to write comment: " + xhr.responseText);
        }
      }
    });
  });

  // Code for handling reply comment form submission
  $('#replyComment').submit(function(e) {
    e.preventDefault();
    var formData = $(this).serialize();
    $.ajax({
      url: '/insertReplyComment',
      type: 'POST',
      data: formData,
      success: function(response) {
        $('.reply').val('');
        alert("Reply has been written");
      },
      error: function(xhr, status, error) {
        if (xhr.status === 401) {
          alert("Login is required. You will be taken to the login page.");
          window.location.href = '/login'; // Redirect to the login page
        } else {
          alert("Failed to write reply: " + xhr.responseText);
        }
      }
    });
  });

  // Code for handling like button click using jQuery
  $('#likeBoard').click(function() {
    // Toggle the classes on the <i> element to change its heart style
    var likeIcon = $('#likeIcon');
    likeIcon.toggleClass('fa-regular fa-heart');
    likeIcon.toggleClass('fa-solid fa-heart');
    var boardId = ${bId};
    var memId = '${user.memId}'; // Use single quotes to ensure proper string representation

    // Use JSTL conditional to check if the user is logged in
    if (${not empty user}) {
      // Make the AJAX request to insertBoardLike only if the user is logged in
      $.ajax({
        url: '/insertBoardLike',
        type: 'POST',
        data: {
          boardId: boardId,
          memId: memId
        },
        success: function(response) {
          // Handle the success response if needed
          console.log('Board liked!');
        },
        error: function(xhr, status, error) {
          // Handle the error response if needed
          console.error('Failed to like the board: ' + xhr.responseText);
          // If the like fails, revert the heart style to its previous state
          likeIcon.toggleClass('fa-regular fa-heart');
          likeIcon.toggleClass('fa-solid fa-heart');
        }
      });
    } else {
      // Show an alert and redirect to the login page
      alert("Login is required. You will be taken to the login page.");
      window.location.href = '/login'; // Redirect to the login page
    }
  });
});
</script>
	<script src="js/infopostdetail.js"></script>





</body>
</html>