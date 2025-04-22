package Session29.bt1.business;

import Session29.bt1.entity.BookType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookTypeBusiness {
    private List<BookType> bookTypeList = new ArrayList<>();
    private static int AUTO_ID = 1;

    public void addBookType(Scanner scanner) {
        BookType newType = new BookType();
        newType.setId(AUTO_ID++);
        newType.inputData(scanner);
        bookTypeList.add(newType);
    }

    public void displayAll() {
        if (bookTypeList.isEmpty()) {
            System.out.println("Danh sách loại sách trống.");
            return;
        }
        for (BookType bt : bookTypeList) {
            bt.displayData();
            System.out.println("----------------------");
        }
    }

    public BookType findById(int id) {
        for (BookType bt : bookTypeList) {
            if (bt.getId() == id && !bt.isDelete()) {
                return bt;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        BookType bt = findById(id);
        if (bt != null) {
            bt.setDelete(true);
            System.out.println("Đã xóa thành công.");
        } else {
            System.out.println("Không tìm thấy loại sách.");
        }
    }
}
