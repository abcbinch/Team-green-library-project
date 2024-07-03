<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>신청도서</title>

<link rel="stylesheet" type="text/css" href="/css/wishbook.css">
<link rel="stylesheet" type="text/css" href="/css/public/nav.css">

</head>


<body>

<jsp:include page="../index/header.jsp" />

<div class="bannerBgr">
    <div class="Banner">
        <div class="pageTitle">신청 도서 정보</div> 
        <div class="pageRoute">홈 > 마이 페이지 > 신청 도서</div>
    </div>
</div>

<div class="title_container">
	<h1>희망도서 신청 내역</h1>
</div>
	<hr class="hr1">
	

<jsp:include page="../public/navBarMypage.jsp"/>

<main>

<!-- 심사결과에 따라 이모티콘이 달라집니다. -->
<!-- 이모티콘 넣을거면 bookcontainer booktable margin auto로 해야 함 -->
<div class="bigDiv">
<c:choose>
		<c:when test="${wishs.complete == '심사대기'}">
		<div class="book_image">
			<img src="/images/wait.png">
		</div>
		</c:when>
		<c:when test="${wishs.complete == '심사탈락'}">
		<div class="book_image">
			<img src="/images/unacceptable.png">
		</div>
		</c:when>
		<c:when test="${wishs.complete == '심사통과'}">
		<div class="book_image">
			<img src="/images/okay.png">
		</div>
		</c:when>
	</c:choose>
	<div class="book_container">
	
		<div class="book_table">
			<table>
				<tr>
					<th>현재 상태</th>
					<c:choose>
					<c:when test="${wishs.complete == '심사대기'}">
					<td>&nbsp;&nbsp;<span style="color: blue;">${wishs.complete}</span></td>
					</c:when>
					<c:when test="${wishs.complete == '심사탈락'}">
					<td>&nbsp;&nbsp;<span style="color:red;">${wishs.complete}</span></td>
					</c:when>
					<c:when test="${wishs.complete == '심사통과'}">
					<td>&nbsp;&nbsp;<span style="color: green;">${wishs.complete}</span></td>
					</c:when>
					</c:choose>
					
					<th>신청일</th>
					<td>&nbsp;&nbsp;${wishs.wishDate}</td>
				</tr>
				<tr>
					<th>도서명</th>
					<td>&nbsp;&nbsp;${wishs.wishTitle}</td>
					<th>등록 번호</th>
					<td>&nbsp;&nbsp;${wishs.wishIsbn}</td>
				</tr>
				<tr>
					<th>저자</th>
					<td>&nbsp;&nbsp;${wishs.wishAuthor}</td>
					<th>가격</th>
					<td>&nbsp;&nbsp;${wishs.wishPrice}</td>				
				</tr>
				<tr>
					<th>출판사</th>
					<td>&nbsp;&nbsp;${wishs.wishPublisher}</td>
					<th>출판일</th>
					<td>&nbsp;&nbsp;${wishs.wishPublication}</td>
				</tr>
				
				<!-- 여기 밑에 이제  수정하기 삭제하기 목록으로 (수정은 complete = "W" 일때만 보이게)-->
				<!-- 수정은 jsp하나 만들어서 hopebookapply 그대로 복붙해서 value에 원래값 넣고, input은 포스트 submit -->
					
			</table>	
		</div>
		
		<div class="hopeBookNotice">
		<ul>
			<li>심사는 1주에서 2주 사이에 이루어집니다.</li>
			<li>심사에 통과하지 못하셨다면, '자주 묻는 질문' 페이지에서 
			도서 기증 제한 서적을 참고한 뒤<br> 다시 신청해주세요.</li>
			
		</ul>
		</div>
		
	</div>
	
</div>

<div class="butns">
<div class="butns2">
<c:if test="${wishs.complete == '심사대기'}">
			<form action="rewriteWishBook" method="get" class="wishform">
				<input type="hidden" name="userId" value="${userId}">
				<input type="hidden" name="wishId" value="${wishs.wishId}">
				<input type = "submit" value="수정하기">
			</form>	
		</c:if>
			<form action="deleteWish" method="post" class="wishform">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="userId" value="${userId}">
				<input type="hidden" name="wishlistId" value="${wishs.wishId}">
				<input type = "submit" value="삭제하기">
			</form>
			<a href="myWritten"><input type="button" value="목록으로"></a>
</div>
</div>

		

</main>

 <jsp:include page="../index/footer.jsp" />





</body>
</html>