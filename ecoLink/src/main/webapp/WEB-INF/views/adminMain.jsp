<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>ECO LINK ADMIN</title>
        <link href="/css/admin_dist_style.min.css" rel="stylesheet" />
        <link href="/css/admin.css" rel="stylesheet" />
        <script src="/js/admin_fontawesome_all.js"></script>
        <link rel="shortcut icon" href="#">
    </head>
    <body class="sb-nav-fixed">
        <%@ include file="/WEB-INF/views/adminNav.jsp" %>
        
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">ADMIN 페이지 메인</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">대시보드</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body">기업 회원가입 요청</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="/adminEnterReg">${noConfirmedEnter} 개의 미승인된 요청이 있습니다.</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">회원 관리 바로가기</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="/adminMember">${allUserCount} 명의 회원들이 활동중입니다.</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                최신 글 목록
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>글번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>조회수</th>
                                            <th>작성일</th>
                                            <th>게시판</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>글번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>조회수</th>
                                            <th>작성일</th>
                                            <th>게시판</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<c:forEach items="${boardlist}" var="dto">
                                    		<tr>
                                            	<td>${dto.boardId}</td>
                                            	<td><a href="">${dto.boardTitle}</a></td>
                                            	<td>${dto.memId}</td>
                                            	<td>${dto.boardViewCount}</td>
                                            	<td>${dto.boardRegtime}</td>
                                            	<td>${dto.boardType}</td>
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
                            <div>
                              
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/admin_scripts.js"></script>
        <script src="js/admin_dist_umd_simple_datatables.js"></script>
        <script src="js/admin_datatables_simple.js"></script>
    </body>
</html>

