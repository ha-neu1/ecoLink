<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시물 상세보기</title>
<link rel="stylesheet" href="/css/board.css">
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<%@ include file="header.jsp"%>
<%@ include file="chatbot.jsp"%>
</head>
<body>
	<div class="board_area">
		<div class="board_title">
			<div class="page_name">
				<strong>게시물 상세보기</strong>
			</div>
		</div>
		<hr class="hr_bold">

		<div class="boardMain">
			<h3 class="memId">${board.memId}</h3>
			<h4 class="boardTitle">${board.boardTitle}</h4>

			<button id="likeBoard" class="like_button">
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

			<p class="boardDate">
				<fmt:parseDate value="${board.boardRegtime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
				<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
			</p>
			<div class="viewCnt">조회수${board.boardViewCount}</div>
			<c:if test="${board.memId eq user.memId}">
				<button class="button" onclick="boardUpdate(${board.boardId})">수정</button>
				<button class="button"
					onClick="deleteConfirmation(${board.boardId})">삭제</button>
			</c:if>
			<img class="boardImage" src="${board.filePath}"
				onerror="this.onerror=null; this.src='https://source.unsplash.com/300x225/?beach';">
			<p class="boardCont">${board.boardContents}</p>
		</div>

		<div class="post_comment_header">댓글</div>
		<div class="post_comment">
			<form id="boardComment">
				<input type="text" placeholder="댓글을 입력해 주세요." class="comment"
					name="comment" id="newComment">
				<button class="commentbtn">등록</button>
				<input type="hidden" name="boardId" value="${detaildto.boardId}" />
			</form>
		</div>

		<div id="commentsSection">
			<c:forEach items="${clist}" var="dto">
				<c:choose>
					<c:when test="${dto.bcReLevel == 0}">
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
									<div class="replyWrap" data-bcRef="${replyDto.bcRef}"
										style="display: none;">
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
							<button data-show="${dto.bcRef}" class="showReplyBtn">답글
								더 보기</button>
							<button data-hide="${dto.bcRef}" class="hideReplyBtn"
								style="display: none;">답글 접기</button>
						</c:if>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>
	</div>

<script>
    $(document).ready(function () {
        var hasLiked = ${hasLiked};
        var countLike = ${countLike};

        // Initial heart icon setup
        var likeIcon = $('#likeIcon');
        if (hasLiked) {
            likeIcon.addClass('fa-solid').removeClass('fa-regular');
        }

        $('#likeBoard').click(function () {
            if (${not empty user}) {
                var boardId = ${board.boardId};
                var memId = '${user.memId}';

                $.ajax({
                    url: '/insertInfoBoardLike',
                    type: 'POST',
                    data: {
                        boardId: boardId,
                        memId: memId
                    },
                    success: function (updatedLikeCount) {
                        if (hasLiked) {
                            countLike--;
                        } else {
                            countLike++;
                        }
                        hasLiked = !hasLiked;

                        // Update the like count and heart icon dynamically
                        $('#countLike').text(countLike);
                        likeIcon.toggleClass('fa-regular fa-solid');
                        console.log('Board liked!');
                    },
                    error: function (xhr, status, error) {
                        console.error('Failed to like the board: ' + xhr.responseText);
                    }
                });
            } else {
                alert("로그인을 해주세요");
                window.location.href = '/login'; // Redirect to the login page
            }
        });
    });
</script>

	<script src="/js/board.js" defer type="module"></script>
	<script src="/js/boardRead.js" defer type="module"></script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>
