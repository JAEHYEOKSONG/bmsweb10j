package bms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetaillServlet
 */
@WebServlet("/DetaillServlet")
public class DetaillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetaillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {

			// 入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");

			// ①isbnとcmd(フォワード先を区別するパラメータ)を取得
			String isbn = request.getParameter("isbn");
			cmd = request.getParameter("cmd");

			// ②BookDAOをインスタンス化
			BookDAO objDao = new BookDAO();

			// ③書籍情報を検索し、戻り値としてBookオブジェクトを取得する
			Book book = objDao.selectByIsbn(isbn);

			// 詳細情報のエラーチェック
			if (book.getIsbn() == null) {
				if (cmd.equals("detail")) {
					error = "表示対象の書籍が存在しない為、詳細情報は表示できませんでした。";
				} else if (cmd.equals("update")) {
					error = "更新対象の書籍が存在しない為、変更画面は表示できませんでした。";
				}
					cmd = "list";
				return;
			}

			// ④③で取得したbookをリクエストスコープに"book"という名前で格納する
			request.setAttribute("book", book);

		} catch (IllegalStateException e) {
			if (cmd.equals("detail")) {
				error = "DB接続エラーの為、書籍詳細は表示できませんでした。";
			} else if (cmd.equals("update")) {
				error = "DB接続エラーの為、変更画面は表示できませんでした。";
			}
			cmd = "menu";
		} finally {
			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// ⑤ cmdの値でフォワード先を呼び分ける
				if (cmd.equals("detail")) {
					request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
				} else if (cmd.equals("update")) {
					request.getRequestDispatcher("/view/update.jsp").forward(request, response);
				}
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
	}
	}

}
