package bms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch13.AccountDao6;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

		try {
			// 文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			// パラメータの取得
			String isbn = request.getParameter("isbn");

			// DAOオブジェクト宣言
			BookDAO bookDao = new BookDAO();

			// 削除メソッドを呼び出し
			int count = bookDao.delete(isbn);

			// 削除件数をリクエストスコープに登録
			request.setAttribute("count", count);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、登録できませんでした。";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/view/ch13/deleteReceipt.jsp").forward(request, response);
		}
	}

	

}
