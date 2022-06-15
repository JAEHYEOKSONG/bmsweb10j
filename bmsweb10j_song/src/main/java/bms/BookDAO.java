package bms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BookDAO {
	private static final String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/mybookdb";
	private static final String USER = "root";
	private static final String PASSWD = "root123";

	/**
	 * コネクションを取得する
	 *
	 * @return connection
	 */
	private Connection getConnection() {
		Connection con = null; // コネクション
		try {
			Class.forName(RDB_DRIVE);
			// DBに接続
			con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * @メソッド名 ：selectAll
	 * @説明 
	 * @args ：なし
	 * @return ：List型の配列
	 */
	public ArrayList<Book> selectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bookinfo ORDER BY isbn";
		ArrayList<Book> booksArrayList = new ArrayList<Book>();

		try {
			// DB接続
			con = getConnection();
			// SQL文をデータベースに送るための準備
			stmt = con.createStatement();
			// SQL文を送信
			rs = stmt.executeQuery(sql);

			// 取得した結果を配列にBooksオブジェクトで格納
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));

				booksArrayList.add(book);
			}
			// 戻り値
			return booksArrayList;

		} catch (Exception e) {
			throw new IllegalStateException("selectallのエラー" + e);
		} finally {
			// リソースの開放、接続を解除
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
	
	
	public ArrayList<Book> search(String _isbn, String _title, String _price){
		Connection con =null;
		Statement stmt =null;
		ResultSet rs = null;
		ArrayList<Book> booksArrayList = new ArrayList<Book>();
		String sql = "SELECT isbn,title,price FROM bookinfo " +
				"WHERE isbn LIKE '%" + _isbn + "%' AND title LIKE '%" + _title + "%' AND price LIKE '%" + _price + "%'";
		
		try {
			// DB接続
			con = getConnection();
			// SQL文をデータベースに送るための準備
			stmt = con.createStatement();
			// SQL文を送信
			rs = stmt.executeQuery(sql);

			// 取得した結果を配列にBooksオブジェクトで格納
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));

				booksArrayList.add(book);
			}
			// 戻り値
			return booksArrayList;

		} catch (Exception e) {
			throw new IllegalStateException("selectallのエラー" + e);
		} finally {
			// リソースの開放、接続を解除
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
	/**
	 * @メソッド名 ：insert
	 * @説明 ：引数情報(ISBN,TITLE,PRICE)のデータをDBに登録
	 * @param book ：Book型オブジェクト(ISBN,TITLE,PRICE)
	 * @author ：宋宰赫
	 */
	public void insert(Book book) {
		Connection con = null;
		Statement stmt = null;
		// sql文
		String sql = "INSERT INTO bookinfo VALUES('" + book.getIsbn() + "','" + book.getTitle() + "'," + book.getPrice()
				+ ")";

		try {
			// DB接続
			con = getConnection();
			// SQL文をデータベースに送るための準備
			stmt = con.createStatement();
			// SQL文を送信
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException("insertのエラー" + e);
		} finally {
			// リソースの開放、接続を解除
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
	/**
	 * @メソッド名	 ：selectByIsbn
	 * @説明		 ：ISBNを引数に該当テーブルから検索結果を受取る
	 * @param 	isbn ：ISBN
	 * @return	book ：Book型のオブジェクト
	 * @author ：宋宰赫
	 */
	public Book selectByIsbn(String isbn) {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// SQL文
		String sql = "SELECT * FROM bookinfo WHERE isbn = '" + isbn + "'";

		// オブジェクト化
		Book book = new Book();

		try {
			// DB接続
			con = getConnection();
			// SQL文をデータベースに送るための準備
			stmt = con.createStatement();
			// SQL文を送信
			rs = stmt.executeQuery(sql);

			// 取得した結果を配列にBooksオブジェクトで格納する
			while (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}

			return book;

		} catch (Exception e) {
			throw new IllegalStateException("selectByIsbnのエラー" + e);

		} finally {
			// リソースの開放、接続を解除
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}
	
	/**
	 * @メソッド名：update
	 * @説明：変更対象のISBNと変更内容情報で、DBのbookinfoテーブルの
	 * 		該当書籍情報の更新を行う
	 * @param book：Book型オブジェクト(ISBN,TITLE,PRICE)
	 * @param oldIsbn：String型変更対象ISBN
	 * @return：なし
	 * @author ：宋宰赫
	 */
	public void update(Book book, String oldIsbn) {
		Connection con = null;
		Statement stmt = null;
		// sql文
		String sql = "UPDATE bookinfo SET" + " isbn  = '" + book.getIsbn() + "',title = '" + book.getTitle()
				+ "',price =  " + book.getPrice() + " WHERE isbn = '" + oldIsbn + "'";

		try {
			// DB接続
			con = getConnection();
			// SQL文をデータベースに送るための準備
			stmt = con.createStatement();
			// SQL文を送信
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException("updateのエラー" + e);
		} finally {
			// リソースの開放、接続を解除
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
	
	/**
	 * @メソッド名：delete
	 * @説明	：該当ISBNデータをDBファイルから削除する
	 * @param book：Book型オブジェクト(ISBN,TITLE,PRICE)
	 * @return：なし
	 * @author ：宋宰赫
	 */
	public void delete(Book book) {
		Connection con = null;
		Statement stmt = null;
		// sql文
		String sql = "DELETE FROM bookinfo WHERE isbn = '" + book.getIsbn() + "'";

		try {
			// DB接続
			con = getConnection();
			// SQL文をデータベースに送るための準備
			stmt = con.createStatement();
			// SQL文を送信
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException("deleteのエラー" + e);
		} finally {
			// リソースの開放、接続を解除
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
}
