package Session29.bt1.business;

import Session29.bt1.entity.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookBusiness {
    private List<Book> bookList = new ArrayList<>();
    private static int AUTO_ID = 1;

    public void addBook(Scanner scanner) {
        Book book = new Book(AUTO_ID++, "", "", "", 0, "", 0.0);
        book.inputData(scanner);
        bookList.add(book);
    }

    public void displayAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("Danh sách sách trống.");
            return;
        }
        for (Book b : bookList) {
            if (!b.isDeleted()) {
                b.displayData();
                System.out.println("----------------------");
            }
        }
    }

    public void sortBooksByPriceDesc() {
        bookList.sort(Comparator.comparingDouble(Book::getPrice).reversed());
        displayAllBooks();
    }

    public void searchBook(String keyword) {
        for (Book b : bookList) {
            if ((b.getBookName().contains(keyword) || b.getContent().contains(keyword)) && !b.isDeleted()) {
                b.displayData();
                System.out.println("----------------------");
            }
        }
    }

    public void deleteBook(int id) {
        for (Book b : bookList) {
            if (b.getId() == id && !b.isDeleted()) {
                b.setDeleted(true);
                System.out.println("Đã xóa sách thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy sách.");
    }
}
