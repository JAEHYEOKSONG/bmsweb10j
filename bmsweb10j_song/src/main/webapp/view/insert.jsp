<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>書籍登録</title>
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/style.css">
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
							<li><a href ="<%=request.getContextPath()%>/list">[書籍一覧]</a></li>
						</ul>
					</div>

					<!-- ページタイトル -->
					<div id="page_title">
						<h2>書籍登録</h2>
					</div>
				</div>
			</div>

			<!-- 書籍登録コンテンツ部分 -->
			<div id="main" class="container">
				<!--  入力フォーム -->
				<form action="<%=request.getContextPath()%>/insert">
					<table class="input-table" align="center">
					<tr>
						<th>ISBN</th>
						<td><input type="text" name="isbn"></td>
					</tr>
					<tr>
						<th>TITLE</th>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<th>価格</th>
						<td><input type="text" name="price"></td>
					</tr>
					</table>
					<input type="submit" value="登録">
				</form>
			</div>

			<!-- フッター部分 -->
			<%@ include file="/common/footer.jsp" %>

		</div>
	</body>
</html>