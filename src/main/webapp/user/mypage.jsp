<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<div class="max-w-xl mx-auto my-10 bg-white p-8 rounded-lg shadow">
    <h2 class="text-2xl font-bold mb-6">마이페이지</h2>

    <c:if test="${not empty success}">
        <div class="mb-4 p-3 rounded bg-green-100 text-green-800">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="mb-4 p-3 rounded bg-red-100 text-red-800">${error}</div>
    </c:if>

    <form action="front?command=mypageUpdate" method="post" class="space-y-4">
        <div>
            <label class="block text-sm font-medium text-gray-700">아이디</label>
            <input type="text" class="mt-1 block w-full border rounded p-2 bg-gray-100"
                   value="${me.username}" readonly />
        </div>

        <div>
            <label class="block text-sm font-medium text-gray-700">이름</label>
            <input name="name" type="text" class="mt-1 block w-full border rounded p-2" required
                   value="${me.name}"/>
        </div>

        <div>
            <label class="block text-sm font-medium text-gray-700">이메일</label>
            <input name="email" type="email" class="mt-1 block w-full border rounded p-2"
                   value="${me.email}"/>
        </div>

        <div>
            <label class="block text-sm font-medium text-gray-700">새 비밀번호 (변경 시에만 입력)</label>
            <input name="password" type="password" class="mt-1 block w-full border rounded p-2"
                   placeholder="비워두면 기존 비밀번호 유지"/>
        </div>

        <button type="submit"
                class="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded">
            정보 수정
        </button>
    </form>

    <div class="text-center mt-6">
        <a class="text-blue-600 hover:underline" href="front?command=boardList">게시판으로 돌아가기</a>
    </div>
</div>
</body>
</html>
