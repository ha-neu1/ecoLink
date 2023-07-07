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
                        <h1 class="mt-4">${title}</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"></li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                ${tabletitle}
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>회원 ID</th>
                                            <th>이름</th>
                                            <th>이메일</th>
                                            <th>닉네임</th>
                                            <th>가입일</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>회원 ID</th>
                                            <th>이름</th>
                                            <th>이메일</th>
                                            <th>닉네임</th>
                                            <th>가입일</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<c:forEach items="${memberlist}" var="dto">
                                    		<tr>
                                            	<td><a href="">${dto.memId}</a></td>
                                            	<td>${dto.memName}</td>
                                            	<td>${dto.memEmail}</td>
                                            	<td>${dto.memNick}</td>
                                            	<td>${dto.memRegtime}</td>
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

