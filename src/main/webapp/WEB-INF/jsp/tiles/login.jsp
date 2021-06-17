<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row d-flex justify-content-center align-items-center">
	<div class="card align-middle"
		style="width: 20rem; border-radius: 20px;">
		<div class="card-body">
			<form action='<c:url value="/login"/>' class="form-signin"
				method="POST" onSubmit="logincall();return false">
				<div class="card-title" style="margin-top: 30px;">
					<h2 class="card-title text-center" style="color: #113366;">로그인</h2>
					<br>
				</div>
				<label for="inputEmail" class="sr-only">ID</label> <input
					type="text" name="id" class="form-control" placeholder="ID"
					required autofocus><BR> <label for="inputPassword"
					class="sr-only">Password</label> <input type="password" name="pw"
					class="form-control" placeholder="Password" required><br>

				<p class="card-title text-center" style="color: #ff0000;">
					<c:out value="${message}" />
				</p>

				<button id="btn-Yes" class="btn btn-success btn-block" type="submit">로
					그 인</button></form>
				<button id="btn-Yes" class="btn btn-outline-success btn-block"
					onClick="location.href='<c:url value='/signUp' />'">회 원 가
					입</button>
			

		</div>
	</div>
</div>
</html>