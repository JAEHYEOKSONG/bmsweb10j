package bms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
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
			// BookDAOのオブジェクト作成
			BookDAO bookDao = new BookDAO();
			
			
			// ③Bookのオブジェクトを生成し、各setterメソッドを利用し、isbn、title、priceを設定する
			Book book = new Book();
			book.setIsbn(request.getParameter("isbn"));
			book.setTitle(request.getParameter("title"));
			int price = Integer.parseInt(request.getParameter("price"));
			book.setPrice(price);
			// ④③で生成したBookのオブジェクトを引数として、関連メソッドを呼び出す
			bookDao.insert(book);
			
			request.getRequestDispatcher("/list").forward(request, response);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
