<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장서관리</title>
    <meta name="csrf-token" content="${_csrf.token}">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/reset.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminHeader.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/adminFooter.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/public/style.css">
    <link rel="stylesheet" type="text/css" href="/admin/css/bookManage.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%-- Include header --%>
<jsp:include page="../../public/adminHeader.jsp"></jsp:include>

<main>
    <section class="banner">
        <h3>장서관리</h3>
        <p>home > book > manage</p>
    </section>

    <section class="totalContainer">
        <div class="searchContainer">
            <div>
                <select id="searchSelectType">
                    <option value="title">제목</option>
                    <option value="author">저자</option>
                    <option value="genre">십진분류</option>
                    <option value="all" selected>제목 + 저자</option>
                </select>
                <div class="inputBox">
                    <input type="text" id="inputText" class="inputText" name="searchKeyword" maxlength="20"
                           placeholder="검색어를 입력하세요" value="" />
                    <button type="button" id="searchBtn" class="searchBtn">검색</button>
                </div>
            </div>
        </div>

        <!-- Board -->
        <div class="outputBoard">
            <div class="results">
                <p id="total">result : </p>
                <select id="resultSelect">
                    <option value="5">5개씩</option>
                    <option value="10">10개씩</option>
                    <option value="15" selected>15개씩</option>
                </select>
                <div class="btnWrap">
                    <input class="deleteBtn" type="button" value="일괄 반납" id="returnBooks" onclick="returnBooks()">
                    <input class="writeBtn" type="button" value="등록" id="createBook" onclick="location.href='/admin/Book/WriteBook'">
                    <input class="deleteBtn" type="button" value="삭제" id="deleteBook" onclick="deleteBooks()">
                </div>
            </div>

            <div class="dashBoard">
                <table>
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="selectAllCheckbox"></th>
                        <th>관리번호</th>
                        <th>제목</th>
                        <th>저자</th>
                        <th>출판사</th>
                        <th>출판년도</th>
                        <th>십진분류</th>
                        <th>대여유무</th>
                        <th colspan="2"></th>
                    </tr>
                    </thead>
                    <tbody id="bookListTBody">
                    </tbody>
                </table>
            </div>
        </div>

        <div class="paging">
            <input class="back" type="button">
            <p id="totalPage"></p>
            <input class="next" type="button">
        </div>
    </section>
</main>

<%-- Include footer --%>
<jsp:include page="../../public/adminFooter.jsp"></jsp:include>

<script>
    let currentPage = 1;
    let totalPage = currentPage;

    $(document).ready(function () {
        searchBtnEvt();

        // 검색 버튼 클릭 시
        $('#searchBtn').click(function () {
            currentPage = 1;
            searchBtnEvt();
        });

        // 전체 선택 & 해제
        $('#selectAllCheckbox').on('change', function () {
            $('input[name="selectedBooks"]').prop('checked', this.checked);
        });

        // select 값 변경 시
        $('#resultSelect').on('change', function () {
            currentPage = 1;
            searchBtnEvt();
        });

        // 다음 버튼 클릭 시
        $('.next').click(function () {
            if(currentPage < totalPage){
                currentPage++;
                searchBtnEvt();
            }
        });

        // 이전 버튼 클릭 시
        $('.back').click(function () {
            if (currentPage > 1) {
                currentPage--;
                searchBtnEvt();
            }
        });
    });

    function clearCheckboxes() {
        $('input[name="selectedBooks"]').prop('checked', false);
        $('#selectAllCheckbox').prop('checked', false);
    }

    function searchBtnEvt() {
        const inputText = $('#inputText').val();
        const searchType = $('#searchSelectType').val();
        const bookListTBody = $('#bookListTBody');
        const totalPageElem = $('#totalPage');
        const selectValue = $('#resultSelect').val();
        const total = document.getElementById('total');

        $.ajax({
            url: '/admin/Book/search',
            type: 'GET',
            data: { "searchType": searchType, "searchKeyword": inputText, "pageSize": selectValue },
            success: function (response) {
                if (response) {
                    let responseText = '';
                    let len = response.length;
                    totalPage = Math.ceil(len / selectValue);
                    let startPrint = (currentPage - 1) * selectValue;
                    let endPrint = currentPage * selectValue;
                    if (endPrint > len) {
                        endPrint = len;
                    }
                    for (let i = startPrint; i < endPrint; i++) {
                        responseText += "<tr>";
                        responseText += "<td><input type='checkbox' name='selectedBooks' value='" + response[i].bookId + "'></td>";
                        responseText += "<td>" + response[i].bookId + "</td>";
                        responseText += "<td>" + response[i].title + "</td>";
                        responseText += "<td>" + response[i].author.authorName + "</td>";
                        responseText += "<td>" + response[i].publisher.publisherName + "</td>";
                        responseText += "<td>" + response[i].publicationDate + "</td>";
                        responseText += "<td>" + response[i].genreFullname + "</td>";
                        responseText += "<td><input type='checkbox' class='borrowTF' disabled " + (response[i].availability == 1 ? "checked" : "") + "></td>";
                        responseText += "<td><input type='button' class='see' onclick='showBook(" + response[i].bookId + ")'>";
                        responseText += "<input type='button' class='correction' onclick='modifyBook(" + response[i].bookId + ")'>";
                        responseText += "<input type='button' class='return' onclick='returnBook(" + response[i].bookId + ")'></td></tr>";
                    }
                    bookListTBody.html(responseText);
                    total.innerHTML = "result : " + len + "개";
                    totalPageElem.html(currentPage + " of " + totalPage);
                }
            }
        });
    }

    // 조회
    function showBook(bookId) {
        $.ajax({
            url: '/admin/Book/details/' + bookId,  // 수정된 부분
            type: 'POST',
            beforeSend: function (xhr) {
                const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
            },
            success: function (response) {
                window.location.href = '/admin/Book/DetailBook?bookId=' + encodeURIComponent(bookId);
            },
            error: function (xhr, status, error) {
                alert("접속에 실패했습니다. 다시 시도해주세요.");
            }
        });
    }


    // 수정
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
    function deleteBooks() {
        let bookIds = [];
        let maxNum = $('input[name="selectedBooks"]');
        $('input[name="selectedBooks"]:checked').each(function () {
            let idx = $(this).closest('tr').find('td:eq(1)').text().trim();
            bookIds.push(idx);
        });

        if (bookIds.length === 0) {
            alert('삭제할 책을 선택해주세요.');
            return;
        }

        if (confirm('선택한 책을 영구 삭제하시겠습니까?')) {
            $.ajax({
                url: '/admin/Book/deleteBooks',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(bookIds),
                beforeSend: function (xhr) {
                    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                    xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
                },
                success: function (response) {
                    alert("선택한 책들이 삭제 되었습니다. ");
                    searchBtnEvt();
                    clearCheckboxes();
                    if(currentPage == totalPage && announceIds.length >= maxNum.length)
                        currentPage--;
                },
                error: function (xhr, status, error) {
                    alert("책들 삭제 중 오류가 발생했습니다: ");
                }
            });
        }
    }

    // 일괄 반납
    function returnBooks() {
        let bookIds = [];
        $('input[name="selectedBooks"]:checked').each(function () {
            let idx = $(this).closest('tr').find('td:eq(1)').text().trim();
            bookIds.push(idx);
        });

        if (bookIds.length === 0) {
            alert('반납할 책을 선택해주세요.');
            return;
        }

        if (confirm('선택한 책을 반납 처리 하시겠습니까?')) {
            $.ajax({
                url: '/admin/Book/returnMultiBook',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(bookIds),
                beforeSend: function (xhr) {
                    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                    xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
                },
                success: function (response) {
                    alert("선택한 책들이 반납 되었습니다. ");
                    searchBtnEvt();
                },
                error: function (xhr, status, error) {
                    alert("책 반납 중 오류가 발생했습니다: ");
                }
            });
        }
    }
//     개별 반납
    function returnBook(bookId) {
        if (confirm('선택한 책을 반납 처리 하시겠습니까?')) {
            $.ajax({
                url: '/admin/Book/returnBook',
                type: 'POST',
                data: { "bookId": bookId },
                beforeSend: function(xhr) {
                    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
                    xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
                },
                success: function(response) {
                    alert("책이 반납되었습니다.");
                    searchBtnEvt();
                },
                error: function(xhr, status, error) {
                    alert("책 반납 중 오류가 발생했습니다.");
                }
            });
        }
    }
</script>
</body>
</html>
