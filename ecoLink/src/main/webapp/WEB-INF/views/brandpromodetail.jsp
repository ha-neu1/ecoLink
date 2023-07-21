<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브랜드 홍보 상세</title>
<script src="../js/jquery-3.6.4.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="../css/brandpromodetail.css" rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
<script type="text/javascript">
	function bookmark(e, f) {
		location.href="brandpromodetail/bookmark?entCrn=" + e + "&bookmarked=" + f;
	}
	
	function insertBrandComment(e) {
		$('#startextarea').val($('#commentarea').val());
		alert($('#startextarea').val());
		$('#brandComment').submit();
	}
</script>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 align-items-start my-5">
				<div class="col-lg-7">
					<img class="img-fluid rounded mb-4 mb-lg-0"
						src="${bpd.entdMainPic}" onerror="this.onerror=null; this.src='https://dummyimage.com/900x400/dee2e6/6c757d.jpg';" />
				</div>
				<div class="col-lg-5">
					<h1 class="font-weight-light">${bpd.memNick}</h1>
					<hr>
					<c:choose>
						<c:when test="${bookmarked == 0}">
							<span class="material-symbols-outlined" id="ent_bookmark" 
							style="font-variation-settings: 'FILL' 0,'wght' 700,'GRAD' 0,'opsz' 48;" onclick="bookmark('${bpd.entCrn}', ${bookmarked})">favorite</span>
						</c:when>
						<c:otherwise>
							<span class="material-symbols-outlined" id="ent_bookmark" 
							style="font-variation-settings: 'FILL' 1,'wght' 700,'GRAD' 0,'opsz' 48;" onclick="bookmark('${bpd.entCrn}', ${bookmarked})">favorite</span>
						</c:otherwise>
					</c:choose>
					
					<hr>
					<p>${bpd.entdShort}</p>

					<a class="btn btn-success" href="${bpd.entdURL}">홈페이지로 가기</a>
				</div>
			</div>

			<div class="row-ecolinkcustom gx-1 gx-lg-5">
				<div class="col-md-auto-ecolinktab mb-5">
					<div
						class="card-ecolink-tab text-white bg-ecolinkcustom text-center">
						<div class="card-body">
							<p class="text-white m-0">상세 내용</p>
						</div>
					</div>
				</div>
				<div class="col-md-auto-ecolinktab mb-5">
					<div
						class="card-ecolink-tab text-white bg-ecolinkcustomgrey text-center inactive">
						<div class="card-body">
							<p class="text-white m-0">후기</p>
						</div>
					</div>
				</div>
			</div>


			<div class="card-ecolink-tab text-black bg-ecolinkcustomwhite">
				<div class="card-body">
					<p class="text-black m-0">${bpd.entdIntro}</p>
				</div>
			</div>

			<div class="card-ecolink-tab text-black bg-ecolinkcustomwhite">
				<div class="card-body">
					<div class="first text-center">
						<h3 class="mt-2">브랜드 평점 / 후기</h3>
						<div class="">
							<div class="rating-box" style="background-color:${rateColor}; color:${rateTextColor};">
								<h1 class="pt-4">${rate}</h1>
								<p class="">/ 5 점</p>
							</div>
						</div>

						<div class="container d-flex justify-content-center mt-200">
							<div class="row">
								<div class="col-md-12">
									<div class="stars">
										<form action="/insertBrandComment" id="brandComment" method="post">
											<input class="star star-5" id="star-5" type="radio"
												name="star" /> <label class="star star-5" for="star-5"></label>
											<input class="star star-4" id="star-4" type="radio"
												name="star" /> <label class="star star-4" for="star-4"></label>
											<input class="star star-3" id="star-3" type="radio"
												name="star" /> <label class="star star-3" for="star-3"></label>
											<input class="star star-2" id="star-2" type="radio"
												name="star" /> <label class="star star-2" for="star-2"></label>
											<input class="star star-1" id="star-1" type="radio"
												name="star" /> <label class="star star-1" for="star-1"></label>
											<textarea maxlength="255" id="startextarea" hidden="hidden" name="comment"></textarea>
											<input type="hidden" name="entCrn" value="${bpd.entCrn}" />
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="border p-3 rounded">
						<div class="row">
							<div class="col-md-3"></div>
						</div>

						<div class="mt-4">
							<textarea class="textarea form-control" rows="7"
								placeholder="후기를 남겨주세요" maxlength="255" id="commentarea"
								style="resize: none;"></textarea>
						</div>
						<div class="col-1">
							<p class="textTotal">0/255자</p>
						</div>
						<div class="button mt-4 text-left">
							<button class="btn btn-success submit-button" onclick="insertBrandComment('${bpd.entCrn}')">제출</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../js/brandpromodetail.js"></script>
	<script type="text/javascript">
		$('#commentarea').keyup(function(e) {
			let content = $(this).val();

			if (content.length == 0 || content == '') {
				$('.textTotal').text('0/255자');
			} else {
				$('.textTotal').text(content.length + '/255자');
			}

			if (content.length > 255) {
				$('.textTotal').text(content.length - 1 + '/255자');
				$(this).val($(this).val().substring(0, 255));

				alert('글자수는 255자까지 입력 가능합니다.');
			}
			;
		});
	</script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>