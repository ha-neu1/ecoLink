<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>ECO LINK ADMIN</title>
<link href="/css/admin_dist_style.min.css" rel="stylesheet" />
<link href="/css/admin.css" rel="stylesheet" />
<script src="/js/admin_fontawesome_all.js"></script>
<link rel="shortcut icon" href="#">
</head>
<body class="sb-nav-fixed">
	<%@ include file="/WEB-INF/views/adminNav.jsp"%>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">${title}</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active"></li>
				</ol>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i> ${tabletitle}
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>사업자등록번호</th>
									<th>기업명</th>
									<th>대표연락처</th>
									<th>아이디</th>
									<th>이메일</th>
									<th>가입신청일</th>
									<th>선택</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>사업자등록번호</th>
									<th>기업명</th>
									<th>대표연락처</th>
									<th>아이디</th>
									<th>이메일</th>
									<th>가입신청일</th>
									<th>선택</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach items="${boardlist}" var="dto">
									<tr>
										<td>${dto.entCrn}</td>
										<td><a href="/brandpromodetail?entCrn=${dto.entCrn}">${dto.memNick}</a></td>
										<td>${dto.entPhone}</td>
										<td>${dto.memId}</td>
										<td>${dto.memEmail}</td>
										<td>${dto.memRegtime}</td>
										<td><c:choose>
												<c:when test="${dto.entdConfirm == 'false'}">
													<button id="${dto.entCrn}-yes"
														onclick="control('${dto.entCrn}', 'allow');">가입허가</button>&nbsp;
                                            				<button
														id="${dto.entCrn}-no"
														onclick="control('${dto.entCrn}', 'refuse');">가입거부</button>
												</c:when>
												<c:otherwise>
                                            				${dto.entdConfirm}
                                            			</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-muted">Copyright &copy; ECO LINK 2023</div>
					<div></div>
				</div>
			</div>
		</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/admin_scripts.js"></script>
	<script src="js/admin_dist_umd_simple_datatables.js"></script>
	<script src="js/admin_datatables_simple.js"></script>
	<script type="text/javascript">
		function control(s, a) {
			if (a == "allow") {
				if (!confirm("사업자등록번호 " + s + " 님의 가입을 허가하시겠습니까?")) {

				} else {
					alert("가입을 허가합니다.");
					location.href="/controlEnterReg?entCrn=" + s + "&allow=" + a;
				}
			} else {
				if (!confirm("사업자등록번호 " + s + " 님의 가입을 거부하시겠습니까? 저장된 데이터가 전부 삭제됩니다.")) {

				} else {
					alert("가입을 거부합니다.");
					location.href="/controlEnterReg?entCrn=" + s + "&allow=" + a;
				}
			}

		}
	</script>
</body>
</html>

