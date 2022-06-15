package bms;

public class Book {
	//書籍番号を格納する変数
	private String isbn;
	//タイトルを格納する変数
	private String title;
	//価格を格納する変数
	private int price;
	
	
	//初期化　コンストラクタ
	public Book() {
		this.isbn = "";
		this.title = "";
		this.price = 0;
	}
	
	
	
	
	// Get Set()
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
