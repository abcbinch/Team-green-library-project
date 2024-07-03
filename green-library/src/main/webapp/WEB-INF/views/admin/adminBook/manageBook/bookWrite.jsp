<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서 등록</title>
    <meta name="csrf-token" content="${_csrf.token}">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/reset.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminHeader.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminFooter.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/style.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/bookWrite.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<jsp:include page="../../public/adminHeader.jsp"></jsp:include>
<main>
    <section class="banner">
        <h3>도서 등록</h3>
        <p>home > book > registration</p>
    </section>
    <section class="viewContainer">
        <table class="bookInfo">
            <tr>
                <th>번호</th>
                <td id="bookIdx">${nextId}</td>
                <th>십진분류<span>*</span></th>
                <td><input type="text" name="bookGroup" id="bookGroup"></td>
            </tr>
            <tr>
                <th>제목<span>*</span></th>
                <td><input type="text" name="bookTitle" id="bookTitle"></td>
                <th>저자<span>*</span></th>
                <td><input type="text" name="bookWriter" id="bookWriter"></td>
            </tr>
            <tr>
                <th>출판사<span>*</span></th>
                <td><input type="text" name="bookPublisher" id="bookPublisher"></td>
                <th>발간일자<span>*</span></th>
                <td><input type="text" name="publicationDate" id="publicationDate"></td>
            </tr>
            <tr>
                <th>ISBN<span>*</span></th>
                <td><input type="text" name="bookISBN" id="bookISBN"></td>
                <th>위치<span>*</span></th>
                <td><input type="text" name="bookLocation" id="bookLocation"></td>
            </tr>
            <tr>
                <th>이미지</th>
                <td id="fileRow">
                    <div class="fileDiv">
                        <input type="file" name="fileInput" id="fileInput" onchange="readURL(this)">
                        <img id="preview" class="previewImg"/>
                    </div>
                </td>

                <th>내용<span>*</span></th>
                <td><textarea name="bookSummary" id="bookSummary"></textarea></td>
            </tr>
        </table>
        <div class="btnWrap">
            <input type="button" value="목록" onclick="goToList()">
            <input class="deleteBtn" type="button" value="등록" onclick="createBook()">
        </div>
    </section>
</main>
<jsp:include page="../../public/adminFooter.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        const urlParams = new URLSearchParams(window.location.search);
        const bookId = urlParams.get('bookId');
        if (bookId) {
            fetchBookDetails(bookId);
        }
    });
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#preview').attr('src', e.target.result);
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            $('#preview').attr('src', '');
        }
    }

    function fetchBookDetails(bookId) {
        $.ajax({
            url: '/admin/Book/getBookById/' + bookId,
            type: 'GET',
            success: function (response) {
                if (response) {
                    $('#bookIdx').text(response.bookId);
                    $('#bookTitle').val(response.title);

                    if (response.author && response.author.authorName) {
                        $('#bookWriter').val(response.author.authorName);
                    } else {
                        $('#bookWriter').val('');
                    }
                    $('#bookPublisher').val(response.publisher.publisherName);
                    $('#bookGroup').val(response.genreFullname);
                    $('#publicationDate').val(response.publicationDate);
                    $('#bookISBN').val(response.isbn);
                    $('#bookLocation').val(response.location);
                    $('#bookSummary').val(response.summary);
                    if (response.imagePath) {
                        $('#fileRow').append('<a href="/resources/static/documents/"' + response.imagePath + '">' + response.imagePath + '</a>');
                    }
                    $('.deleteBtn').val('수정').attr('onclick', 'updateBook(' + response.bookId + ')');
                }
            },
            error: function (xhr, status, error) {
                console.error("오류 발생: " + error);
                alert('도서 정보를 불러오는데 실패했습니다. 다시 시도해주세요.');
            }
        });
    }


    function goToList() {
        window.location.href = '/admin/Book';
    }

    function isValidDate(dateString) {
        // YYYY-MM-DD 형식의 정규표현식
        const datePattern = /^\d{4}-\d{2}-\d{2}$/;
        return datePattern.test(dateString);
    }
    function createBook() {
        const title = $('#bookTitle').val().trim();
        const group = $('#bookGroup').val().trim();
        const authorName = $('#bookWriter').val().trim();
        const publisherName = $('#bookPublisher').val().trim();
        const publicationDate = $('#publicationDate').val().trim(); // 문자열로 처리
        const isbn = $('#bookISBN').val().trim();
        const location = $('#bookLocation').val().trim();
        const summary = $('#bookSummary').val().trim();

        if (!title || !authorName || !group || !publisherName || !publicationDate || !isbn || !location || !summary) {
            alert('모든 필수 항목을 입력해 주세요.');
            return;
        }
        if (!isValidDate(publicationDate)) {
            alert('발간일자는 YYYY-MM-DD 형식으로 입력해 주세요.');
            return;
        }

        const formData = new FormData();
        formData.append('title', title);
        formData.append('genreFullname', group);
        formData.append('authorName', authorName); // 저자 이름으로 넣기
        formData.append('publisherName', publisherName); // 출판사 이름으로 넣기
        formData.append('publicationDate', publicationDate);
        formData.append('isbn', isbn);
        formData.append('location', location);
        formData.append('summary', summary);

        const fileInput = $('#fileInput')[0].files[0];
        if (fileInput) {
            formData.append('image', fileInput);
        }

        $.ajax({
            url: '/admin/Book/createBook',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function (xhr) {
                const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
            },
            success: function (data) {
                alert('도서 등록이 완료되었습니다.');
                window.location.href = '/admin/Book';
            },
            error: function (xhr, status, error) {
                console.error("오류 발생: " + error);
                alert('도서 등록에 실패했습니다. 다시 시도해주세요.');
            }
        });
    }

    function updateBook(bookId) {
        const title = $('#bookTitle').val().trim();
        const group = $('#bookGroup').val().trim();
        const authorName = $('#bookWriter').val().trim();
        const publisherName = $('#bookPublisher').val().trim();
        const publicationDate = $('#publicationDate').val().trim(); // 문자열로 처리
        const isbn = $('#bookISBN').val().trim();
        const location = $('#bookLocation').val().trim();
        const summary = $('#bookSummary').val().trim();

        if (!title || !authorName || !group || !publisherName || !publicationDate || !isbn || !location || !summary) {
            alert('모든 필수 항목을 입력해 주세요.');
            return;
        }
        if (!isValidDate(publicationDate)) {
            alert('발간일자는 YYYY-MM-DD 형식으로 입력해 주세요.');
            return;
        }

        const formData = new FormData();
        formData.append('bookId', bookId);
        formData.append('title', title);
        formData.append('genreFullname', group);
        formData.append('authorName', authorName); // 저자 이름으로 넣기
        formData.append('publisherName', publisherName); // 출판사 이름으로 넣기
        formData.append('publicationDate', publicationDate);
        formData.append('isbn', isbn);
        formData.append('location', location);
        formData.append('summary', summary);

        const fileInput = $('#fileInput')[0].files[0];
        if (fileInput) {
            formData.append('image', fileInput);
        }

        $.ajax({
            url: '/admin/Book/updateBook',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function (xhr) {
                const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
            },
            success: function (data) {
                alert('도서 정보가 수정되었습니다.');
                window.location.href = '/admin/Book';
            },
            error: function (xhr, status, error) {
                console.error("오류 발생: " + error);
                alert('도서 정보 수정에 실패했습니다. 다시 시도해주세요.');
            }
        });
    }
</script>
</body>
</html>
