<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서 상세</title>
    <meta name="csrf-token" content="${_csrf.token}">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/reset.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminHeader.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminFooter.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/style.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/bookInfo.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<jsp:include page="../../public/adminHeader.jsp"></jsp:include>
<main>
    <section class="banner">
        <h3>도서 정보</h3>
        <p>home > book > detail</p>
    </section>
    <section class="viewContainer">
        <div class="btnWrap">
            <input class="modify" type="button" value="수정" onclick="modifyBook(${book.bookId})">
            <input class="deleteBtn" type="button" value="삭제" onclick="deleteBook(${book.bookId})">
        </div>
        <table class="announcementInfo">
            <tr>
                <th>번호</th>
                <td>${book.bookId}</td>
                <th>십진분류</th>
                <td>${book.genreFullname}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${book.title}</td>
                <th>저자</th>
                <td>${book.author.authorName}</td>
            </tr>
            <tr>
                <th>출판사</th>
                <td>${book.publisher.publisherName}</td>
                <th>발간일자</th>
                <td>${book.publicationDate}</td>
            </tr>
            <tr>
                <th>ISBN</th>
                <td>${book.isbn}</td>
                <th>위치</th>
                <td>${book.location}</td>
            </tr>
            <tr>
                <th>이미지</th>
                <td>
                    <p>${book.img}</p>
                    <img id="preview" class="previewImg" src="/documents/${book.img}"/>
                </td>
                <th>내용</th>
                <td>${book.summary}</td>
            </tr>
        </table>
        <div class="boardNav">
            <div class="prevNav">
                <span class="tit">이전글</span><a class="con" id="prevCon" href="#"></a>
            </div>
            <div class="nextNav">
                <span class="tit">다음글</span><a class="con" id="nextCon" href="#"></a>
            </div>
        </div>
        <div class="board-btn">
            <a href="/admin/Book" class="listBtn">목록</a>
        </div>
    </section>
</main>
<jsp:include page="../../public/adminFooter.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        const bookId = ${book.bookId};
        prevPage(bookId);
        nextPage(bookId);
        <%--if (${book.img}) {--%>
        <%--    $('#preview').attr('src', '/upload/' + ${book.img});--%>
        <%--}--%>
    });

    function prevPage(bookId) {
        $.ajax({
            url: '/admin/Book/prevBook',
            type: 'GET',
            data: { bookId: bookId },
            beforeSend: function (xhr) {
                const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
            },
            success: function (data) {
                if (data && data.title && data.bookId) {
                    $("#prevCon").text(data.title);
                    $("#prevCon").attr("href", "/admin/Book/DetailBook?bookId=" + data.bookId);
                } else {
                    $("#prevCon").text("이전글이 없습니다.");
                }
            },
            error: function (xhr, status, error) {
                $("#prevCon").text("이전글이 없습니다.");
            }
        });
    }

    function nextPage(bookId) {
        $.ajax({
            url: '/admin/Book/nextBook',
            type: 'GET',
            data: { bookId: bookId },
            beforeSend: function (xhr) {
                const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
            },
            success: function (data) {
                if (data && data.title && data.bookId) {
                    $("#nextCon").text(data.title);
                    $("#nextCon").attr("href", "/admin/Book/DetailBook?bookId=" + data.bookId);
                } else {
                    $("#nextCon").text("다음글이 없습니다.");
                }
            },
            error: function (xhr, status, error) {
                $("#nextCon").text("다음글이 없습니다.");
            }
        });
    }

    function modifyBook(bookId) {
        $.ajax({
            url: '/admin/Book/modifyBtnClick/' + bookId,  // 수정된 부분
            type: 'POST',
            beforeSend: function (xhr) {
                const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
            },
            success: function (response) {
                window.location.href = '/admin/Book/updateBook?bookId=' + encodeURIComponent(bookId);
                searchBtnEvt();
            },
            error: function (xhr, status, error) {
                alert('접속에 실패했습니다. 다시 시도해주세요.');
            }
        });
    }


    // 책 삭제
    function deleteBook(bookId) {
        if (confirm('선택한 책을 영구 삭제하시겠습니까?')) {
            $.ajax({
                url: '/admin/Book/deleteBook',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(bookId),
                beforeSend: function (xhr) {
                    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                    xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
                },
                success: function (response) {
                    alert("선택한 책이 삭제 되었습니다. ");
                    window.location.href='/admin/Book';
                },
                error: function (xhr, status, error) {
                    alert("책들 삭제 중 오류가 발생했습니다: ");
                }
            });
        }
    }

</script>
</body>

</html>