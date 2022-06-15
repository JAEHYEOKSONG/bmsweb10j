<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bms.Book, util.MyFormat"%>

<%
	Book book = (Book) request.getAttribute("book");
	MyFormat format = new MyFormat();
%>

<html>
	<head>
		<title>書籍詳細情報</title>
		<link rel ="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	</head>
	<body>
		<!-- ブラウザ全体 -->
		<div id="wrap">
			<!-- ヘッダー部分 -->
			<%@ include file="/common/header.jsp" %>

			<!-- メニュー部分 -->
			<div id="menu">
				<div class="container">
					<!-- ナビゲーション  -->
					<div id="nav">
						<ul>
							<li><a href ="<%=request.getContextPath()%>/view/menu.jsp" >[メニュー]</a></li>
							<li><a href ="<%=request.getContextPath()%>/view/insert.jsp">[書籍登録]</a></li>
							<li><a href ="<%=request.getContextPath()%>/list">[書籍一覧]</a></li>
						</ul>
					</div>

					<!-- ページタイトル -->
					<div id="page_title">
						<h2>書籍詳細情報</h2>
					</div>
				</div>
			</div>

			<!-- 書籍詳細情報コンテンツ部分 -->
			<div id="main" class="container">

				<!-- 書籍変更入力フォーム -->
				<div class="detail-button">
					<form action="<%=request.getContextPath()%>/detail" class="inline-block">
						<input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
						<input type="hidden" name="cmd" value="update"></input>
						<input type="submit" value="変更">
					</form>

					<form action="<%=request.getContextPath()%>/delete" class="inline-block">
						<input type="hidden" name="isbn" value="<%=book.getIsbn() %>">
						<input type="submit" value="削除">
					</form>
				</div>

				<table class="detail-table">
					<tr>
						<th>ISBN</th>
						<td><%=book.getIsbn() %></td>
					</tr>
					<tr>
						<th>TITLE</th>
						<td><%=book.getTitle() %></td>
					</tr>
					<tr>
						<th>価格</th>
						<td><%=format.moneyFormat(book.getPrice())%></td>
					</tr>
				</table>

			</div>

			<!-- フッター部分 -->
			<%@ include file="/common/footer.jsp" %>

		</div>
	</body>
</html>