<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.BoardCommentDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/boardDetail.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>

<script src="js/jquery-3.6.4.min.js"></script>
<%@ include file="header.jsp"%>
<%@ include file="chatbot.jsp"%>
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
					<span id="countLike">${countLike}</span>
				</button>
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
	
<div class="post_tit" name="boardTitle"><p>${detaildto.boardTitle }</p> </div>
	<div class="post_contents" name="boardContents"><p>${detaildto.boardContents }</p></div>
	<div class="post_edit_delete">
		<c:choose>
			<c:when test="${logininfo.memId eq detaildto.memId || logininfo.memId eq 'admin'}">
				<form id="shareeditform" action="shareeditform"> 
				<input type="hidden" name="boardId" value="${detaildto.boardId}" />
				<button type="submit" class="postEditBtn">수정</button>
				</form>
				<button class="postDeleteBtn"
					onclick="deleteBoard(${detaildto.boardId }, '${detaildto.memId}');">삭제</button>
					</c:when>
		</c:choose>
	</div>
	<div class="post_comment_header">댓글</div>
	<div class="post_comment">
		<form id="boardComment">
			<input type="text" placeholder="댓글을 입력해 주세요." class="comment"
				name="comment" id="newComment">
			<button class="commentbtn" >등록</button>
			<input type="hidden" name="boardId" value="${detaildto.boardId}" />
		</form>
	</div>
		<div id="commentsSection">
		<c:forEach items="${clist }" var="dto">
			<c:choose>
				<c:when test="${dto.bcReLevel == 0 }">
					<div class="commentContentWrap">
						<div class="commentContentProfile">
							<i class="fa-regular fa-user"></i>
						</div>
						<div class="commentContentRight">
							<div class="commentNickName">${dto.memNick}
								<c:choose>
									<c:when
										test="${not dto.deleted && (logininfo.memId eq dto.memId || logininfo.memId eq 'admin')}">
										<button class="deleteCommentBtn"
											onclick="deleteComment(${dto.bcId },${dto.boardId }, '${dto.memId}');">
											<i class="fa-solid fa-xmark"></i>
										</button>
									</c:when>
								</c:choose>
							</div>
							<div class="commentContents">
								<c:choose>
									<c:when test="${dto.deleted}">
                                    삭제된 댓글입니다.
                                </c:when>
									<c:otherwise>
                                    ${dto.bcContents}
                                </c:otherwise>
								</c:choose>
							</div>
							<div class="commentContentFooter">
								<div class="dateReplyWrap">
									<div class="commentDate">
										<c:choose>
											<c:when test="${not dto.deleted}">
                                            ${dto.bcRegtime}
                                        </c:when>
										</c:choose>
									</div>
									<form id="replyComment">
										<c:choose>
											<c:when test="${not dto.deleted}">
												<div class="commentReply">
													<button type="button" class="replyWrite">답글쓰기</button>
												</div>
												<input type="hidden" name="boardId"
													value="${detaildto.boardId}" />
												<input type="hidden" name="bcRef" value="${dto.bcRef}" />
											</c:when>
										</c:choose>
								</div>
								<div class="replyContents" style="display: none;">
									<input type="text" placeholder="답글을 입력해 주세요." class="reply"
										name="reply">
									<button class="replyBtn">등록</button>
								</div>
								</form>
								
								
							</div>
						</div>
					</div>
					  <c:set var="hasReplies" value="false" />
					<c:forEach items="${replyList}" var="replyDto">
						<c:choose>
							<c:when test="${replyDto.bcRef == dto.bcRef}">
							 <c:set var="hasReplies" value="true" />
								<div class="replyWrap" data-bcRef="${replyDto.bcRef}"style="display: none;">
									<div class="commentContentProfile">
										<i class="fa-regular fa-user"></i>
									</div>
									<div class="commentContentRight">
										<div class="commentNickName">${replyDto.memNick}
											<c:choose>
												<c:when
													test="${logininfo.memId eq replyDto.memId || logininfo.memId eq 'admin'}">
													<button class="deleteReplyBtn"
														onclick="deleteReply(${replyDto.bcId },${replyDto.boardId }, '${replyDto.memId}');">
														<i class="fa-solid fa-xmark"></i>
													</button>
												</c:when>
											</c:choose>
										</div>
										<div class="commentContents">${replyDto.bcContents}</div>
										<div class="commentContentFooter">
											<div class="commentDate">${replyDto.bcRegtime}</div>
										</div>
									</div>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>
					 <c:if test="${hasReplies}">
					  <div class="replyOnDisplay">
                	<button class="onDisplay"onclick="onDisplay(${dto.bcRef})">답글 더 보기</button>
               		 </div>
                	</c:if>
                	<div class="replyOffDisplay">
					<button class="offDisplay"onclick="offDisplay(${dto.bcRef})" style="display: none;">답글 접기</button>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
	
	
	
		<ul class="pagination">
			<c:choose>
				<c:when test="${currentCpage == 1}">
					<li class="page-item disabled"><a class="page-link"
						href="/sharepostdetail?boardId=${bId}&page=${currentCpage - 1}"
						tabindex="-1" aria-disabled="true">이전</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link" id="nextPageLink"
						href="/sharepostdetail?boardId=${bId}&page=${currentCpage - 1}">이전</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${startpage}" end="${endpage}">
				<c:choose>
					<c:when test="${i == currentCpage}">
						<li class="page-item activeNumber"><a class="page-link" id="nextPageLink"
							href="/sharepostdetail?boardId=${bId}&page=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" id="nextPageLink"
							href="/sharepostdetail?boardId=${bId}&page=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${totalPage == currentCpage}">
						<li class="page-item disabled" id="endNextPage"><a class="page-link" id="nextPageLink"
						href="/sharepostdetail?boardId=${bId}&page=${currentCpage + 1}">다음</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link"
						href="/sharepostdetail?boardId=${bId}&page=${currentCpage + 1}">다음</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	
	<script>
$(document).ready(function() {

  // Code for handling board comment form submission
  $('#boardComment').submit(function(e) {
    e.preventDefault();
    var formData = $(this).serialize();
    
   
    $.ajax({
      url: '/insertShareBoardComment',
      type: 'POST',
      data: formData,
      success: function(response) {
        $('.comment').val('');
        alert("댓글이 작성되었습니다.");
        
        location.reload();
      },
      error: function(xhr, status, error) {
        if (xhr.status === 401) {
          alert("로그인을 해주세요.");
          window.location.href = '/login'; // Redirect to the login page
        } else {
          alert("댓글이 작성되지 않았습니다.: " + xhr.responseText);
        }
      }
    });
  });

  // Code for handling reply comment form submission
 $('.replyWrite').click(function() {
  var commentContentRight = $(this).closest('.commentContentRight');
  var replyContents = commentContentRight.find('.replyContents');
  replyContents.toggle();
  var buttonText = replyContents.is(':visible') ? '답글닫기' : '답글쓰기';
  $(this).text(buttonText);
});

$('.replyBtn').click(function(e) {
  e.preventDefault();
  var replyForm = $(this).closest('.commentContentRight').find('form'); // Get the closest form
  var formData = replyForm.serialize(); // Serialize the form data
  $.ajax({
    url: '/insertShareReplyComment',
    type: 'POST',
    data: formData,
    success: function(response) {
      replyForm.find('.reply').val(''); // Clear the reply input field
      alert("답글이 작성되었습니다.");
      location.reload();
    },
    error: function(xhr, status, error) {
      if (xhr.status === 401) {
        alert("로그인을 해주세요");
        window.location.href = '/login'; // Redirect to the login page
      } else {
        alert("답글이 작성되지 않았습니다.: " + xhr.responseText);
      }
    }
  });
});

  // Code for handling like button click using jQuery
  $('#likeBoard').click(function() {
    // Toggle the classes on the <i> element to change its heart style
    var likeIcon = $('#likeIcon');
    var boardId = ${bId};
    var memId = '${user.memId}'; // Use single quotes to ensure proper string representation

    // Use JSTL conditional to check if the user is logged in
    if (${not empty user}) {
      // Make the AJAX request to insertBoardLike only if the user is logged in
      $.ajax({
        url: '/insertShareBoardLike',
        type: 'POST',
        data: {
          boardId: boardId,
          memId: memId
        },
        success: function(updatedLikeCount) {
            // Update the like count and heart icon dynamically
            $('#countLike').text(updatedLikeCount);
           // Toggle the heart icon classes correctly
        likeIcon.toggleClass('fa-regular fa-heart');
        likeIcon.toggleClass('fa-solid fa-heart');
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
      alert("로그인을 해주세요");
      window.location.href = '/login'; // Redirect to the login page
    }
  });
});
function deleteBoard(s, a) {
	if (!confirm("게시물을 삭제하시겠습니까?")) {
        
    } else {
        alert("게시물이 삭제되었습니다.");
        location.href="/sharedeleteBoard?boardId=" + s + "&memId=" + a;
    }
}
function deleteComment(bc,b, m) {
	if (!confirm("댓글을 삭제하시겠습니까?")) {
        
    } else {
        alert("댓글이 삭제되었습니다.");
        location.href="/sharedeleteComment?bcId=" + bc + " &boardId=" + b + "&memId=" + m;
    }
}


function deleteReply(bc,b, m) {
	if (!confirm("답글을 삭제하시겠습니까?")) {
        
    } else {
        alert("답글이 삭제되었습니다.");
        location.href="/sharedeleteReply?bcId=" + bc + " &boardId=" + b + "&memId=" + m;
    }
}
function onDisplay(bcRef) {
    $('.replyWrap[data-bcRef="' + bcRef + '"]').show();
    $('[onclick="onDisplay(' + bcRef + ')"]').hide(); // Hide "More replies" button
    $('[onclick="offDisplay(' + bcRef + ')"]').show(); // Show "Collapse Reply" button
}
function offDisplay(bcRef) {
    $('.replyWrap[data-bcRef="' + bcRef + '"]').hide();
    $('[onclick="onDisplay(' + bcRef + ')"]').show(); // Show "More replies" button
    $('[onclick="offDisplay(' + bcRef + ')"]').hide(); // Hide "Collapse Reply" button
}
</script>
	<script src="js/infopostdetail.js"></script>





</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>