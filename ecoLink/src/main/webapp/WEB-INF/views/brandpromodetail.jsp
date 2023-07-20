<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브랜드 홍보 상세</title>
<script src="../js/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="../css/brandpromodetail.css" rel="stylesheet" />
<%@ include file="header.jsp" %>
</head>
<body>
<div class="container">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 align-items-start my-5">
            <div class="col-lg-7">
                <img class="img-fluid rounded mb-4 mb-lg-0" src="https://dummyimage.com/900x400/dee2e6/6c757d.jpg" alt="..." />
            </div>
            <div class="col-lg-5">
                <h1 class="font-weight-light">국립백두대간수목원</h1>
                <hr>
                <span class="material-symbols-outlined" id="ent_bookmark">favorite</span>
                <hr>
                <p>회사간략소개-----------------</p>
                
                <a class="btn btn-success" href="#!">홈페이지로 가기</a>
            </div>
        </div>

        <div class="row-ecolinkcustom gx-1 gx-lg-5">
            <div class="col-md-auto-ecolinktab mb-5">
                <div class="card-ecolink-tab text-white bg-ecolinkcustom text-center">
                    <div class="card-body"><p class="text-white m-0">상세 내용</p></div>
                </div>
            </div>
            <div class="col-md-auto-ecolinktab mb-5">
                <div class="card-ecolink-tab text-white bg-ecolinkcustomgrey text-center inactive">
                    <div class="card-body"><p class="text-white m-0">후기</p></div>
                </div>
            </div>
        </div>
        
        
            <div class="card-ecolink-tab text-black bg-ecolinkcustomwhite">
                <div class="card-body">
                	<p class="text-black m-0">
                	상세 내용 일때</br>
                	상세내용</br>
                	</br>
                	제품사진
                	</p>
                </div>
            </div>
            
            <div class="card-ecolink-tab text-black bg-ecolinkcustomwhite">
                <div class="card-body">
                	
                </div>
            </div>

        
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/brandpromodetail.js"></script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>