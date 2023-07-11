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
        <script>
			function adminadd() {
				if(document.getElementById("inputId").value==''){
					alert("아이디를 입력해주십시오.");
					return false;
				}
				if(document.getElementById("inputName").value==''){
					alert("이름을 입력해주십시오.");
					return false;
				}
				if(document.getElementById("inputEmail").value==''){
					alert("이메일을 입력해주십시오.");
					return false;
				}
				if(document.getElementById("inputPassword").value==''){
					alert("암호를 입력해주십시오.");
					return false;
				}
				if(document.getElementById("inputPasswordConfirm").value==''){
					alert("암호 확인을 입력해주십시오.");
					return false;
				}
				if(document.getElementById("inputPasswordConfirm").value != document.getElementById("inputPassword").value){
					alert("입력한 암호와 암호 확인이 다릅니다. 다시 입력해주십시오.");
					return false;
				}
				document.getElementById('adminadd').submit();
				}
	</script>
    </head>
    <body class="sb-nav-fixed">
    	<%@ include file="/WEB-INF/views/adminNav.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">관리자 계정 생성</h3></div>
                                    <div class="card-body">
                                        <form id="adminadd" action="/adminadd" method="post">
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputId" name="inputId" type="text" placeholder="enter Id" />
                                                        <label for="inputId">아이디</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputName" name="inputName" type="text" placeholder="Enter Name" />
                                                        <label for="inputName">이름</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" type="email" name="inputEmail" placeholder="name@example.com" />
                                                <label for="inputEmail">이메일</label>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPassword" name="inputPassword" type="password" placeholder="Create a password" />
                                                        <label for="inputPassword">암호</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPasswordConfirm" type="password" placeholder="Confirm password" />
                                                        <label for="inputPasswordConfirm">암호 확인</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid"><a class="btn btn-primary btn-block" href="#" onclick="return adminadd()">계정 생성</a></div>
                                                <div>${result}</div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/admin_scripts.js"></script>
    </body>
</html>

