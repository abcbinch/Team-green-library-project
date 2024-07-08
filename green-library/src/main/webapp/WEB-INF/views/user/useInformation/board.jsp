<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useInformationBoard</title>
<link href="/css/board.css" type="text/css" rel="stylesheet">

</head>
<body>
	<div class="center">
    <form method="post" id="form">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <table id="board">
        <caption id="caption" style="margin-bottom: 40px; font-size: 2em; font-weight: bold;"></caption>
        <thead>
            <tr id="table-header"></tr>
        </thead>
        <tbody id="table-body">
            <!-- Data will be dynamically added here -->
        </tbody>
    </table>
    </form>
    </div>
    <div class="center" id="button-box">
    <button class="btn" id="prev-button" onclick="prevPage()">이전</button>
	<button class="btn" id="next-button" onclick="nextPage()">다음</button>
	</div>
    <script src="/js/board.js" type=""></script>
</body>
</html>
