<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <div class="accountContainer">
        <h1 class="adminLogo">
            <img src="/admin/assets/imgs/adminImg.png" alt="">
        </h1>
        <div>
            <p>관리자</p>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="button" class="btn btn-outline-success" id="editBtn">정보 확인</button>
                <button type="submit" class="btn btn-outline-success" id="logoutBtn">로그아웃</button>
            </form>
        </div>
    </div>
    <nav>
        <ul>
            <li class="navSection">
                <div>
                    <h2>게시판 관리</h2>
                </div>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/Inquiry">문의사항</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/Announcement">공지사항</a></li>
                </ul>
            </li>
            <li class="navSection">
                <div>
                    <h2>도서 관리</h2>
                </div>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/BuyBook">수서</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/Book">장서</a></li>
                </ul>
            </li>
            <li class="navSection">
                <div>
                    <h2>유저 관리</h2>
                </div>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/adminInfo">관리자 목록</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/User">이용자 목록</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</header>