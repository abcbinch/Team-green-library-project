<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="csrf-token" content="${_csrf.token}">
    <title>희망 도서 수정</title>
    <link rel="stylesheet" type="text/css" href="/admin/css/public/reset.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminHeader.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminFooter.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/style.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/bookUpdate.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<jsp:include page="../../public/adminHeader.jsp"></jsp:include>
<main>
    <section class="banner">
        <h3>희망 도서 수정</h3>
        <p>home > book > registration > modify</p>
    </section>
    <section class="viewContainer">
        <table class="bookInfo">
            <tr>
                <th>번호</th>
                <td id="wishId"></td>
                <th>ISBN<span>*</span></th>
                <td><input type="text" id="isbn" name="isbn" value=""></td>
            </tr>
            <tr>
                <th>제목<span>*</span></th>
                <td><input type="text" id="title" name="title" value=""></td>
                <th>저자<span>*</span></th>
                <td><input type="text" id="author" name="author" value=""></td>
            </tr>
            <tr>
                <th>출판사<span>*</span></th>
                <td><input type="text" id="publisher" name="publisher" value=""></td>
                <th>발간일자<span>*</span></th>
                <td><input type="text" id="publicationDate" name="publicationDate" value=""></td>
            </tr>
            <tr>
                <th>이미지</th>
                <td>
                    <input type="file" id="fileInput" name="file" onchange="readURL(this)">
                    <img id="preview" class="previewImg"/>
                </td>
                <th>기타 작성사항<span></span></th>
                <td><textarea id="content" name="content"></textarea></td>
            </tr>
        </table>
        <div class="btnWrap">
            <input class="modiBtn" type="button" value="목록" onclick="goToList()">
            <input class="deleteBtn" type="button" value="수정" onclick="updateWishBook()">
        </div>
    </section>
</main>
<jsp:include page="../../public/adminFooter.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        const urlParams = new URLSearchParams(window.location.search);
        const wishlistId = urlParams.get('wishlistId');
        if (wishlistId) {
            fetchWishDetails(wishlistId);
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

    function fetchWishDetails(wishlistId) {
        $.ajax({
            url: '/admin/BuyBook/updateWishInfo/' + wishlistId,
            type: 'GET',
            success: function (response) {
                console.log(response)
                if (response) {
                    $('#wishId').text(response.wishlistId);
                    $('#isbn').val(response.wishIsbn);
                    $('#title').val(response.wishTitle);
                    $('#author').val(response.wishAuthor);
                    $('#publisher').val(response.wishPublisher);
                    $('#publicationDate').val(response.wishPublication);
                    $('#content').val(response.contents);

                    // 이미지 URL이 있으면 섬네일 표시
                    if (response.imageUrl) {
                        $('#previewImg').html(`<img src="${response.imageUrl}" alt="Preview">`);
                    }
                }
            },
            error: function (xhr, status, error) {
                console.error("오류 발생: " + error);
                alert('데이터를 불러오는데 실패했습니다. 다시 시도해주세요.');
            }
        });
    }

    function goToList() {
        window.location.href = '/admin/BuyBook';
    }

    function updateWishBook() {
        const wishlistId = $('#wishId').text();
        const isbn = $('#isbn').val();
        const title = $('#title').val();
        const author = $('#author').val();
        const publisher = $('#publisher').val();
        const publicationDate = $('#publicationDate').val();
        const content = $('#content').val();

        if (!isbn || !title || !author || !publisher || !publicationDate) {
            alert('모든 필수 입력 항목을 입력해주세요.');
            return;
        }

        if (confirm('수정하시겠습니까?')) {
            const formData = new FormData();
            formData.append('wishlistId', wishlistId);
            formData.append('isbn', isbn);
            formData.append('title', title);
            formData.append('author', author);
            formData.append('publisher', publisher);
            formData.append('publicationDate', publicationDate);
            formData.append('content', content);

            // 파일이 선택된 경우에만 FormData에 추가
            const fileInput = document.getElementById('fileInput');
            if (fileInput.files.length > 0) {
                formData.append('file', fileInput.files[0]);
            }

            $.ajax({
                url: '/admin/BuyBook/uploadWishInfo',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function (xhr) {
                    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                    xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
                },
                success: function (data) {
                    alert('수정이 완료되었습니다.');
                    window.location.href = '/admin/BuyBook';
                },
                error: function (xhr, status, error) {
                    console.error("오류 발생: " + error);
                    alert('수정에 실패했습니다. 다시 시도해주세요.');
                }
            });
        }
    }
</script>


</body>

</html>
