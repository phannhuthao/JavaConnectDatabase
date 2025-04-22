package Session29.bt1.presentation;

import Session29.bt1.business.BookBusiness;
import Session29.bt1.business.BookTypeBusiness;

import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookTypeBusiness bookTypeBusiness = new BookTypeBusiness();
        BookBusiness bookBusiness = new BookBusiness();

        int choice;
        while (true) {
            System.out.println("***************** BOOK-MANAGEMENT *****************");
            System.out.println("|1. Quản lý loại sách(BOOKTYPE-MENU)              |");
            System.out.println("|2. Quản lý sách(BOOK-MENU)                       |");
            System.out.println("|3. Thoát                                         |");
            System.out.println("***************************************************");
            System.out.print("Nhập lựa chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    bookTypeMenu(scanner, bookTypeBusiness);
                    break;
                case 2:
                    bookMenu(scanner, bookBusiness);
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void bookTypeMenu(Scanner scanner, BookTypeBusiness bookTypeBusiness) {
        int choice;
        do {
            System.out.println("****************** BOOKTYPE-MENU ******************");
            System.out.println("1. Danh sách loại sách");
            System.out.println("2. Tạo mới loại sách");
            System.out.println("3. Cập nhật thông tin loại sách (Chưa làm)");
            System.out.println("4. Xóa loại sách");
            System.out.println("5. Thống kê số lượng sách theo mã loại (Chưa làm)");
            System.out.println("6. Quay lại");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    bookTypeBusiness.displayAll();
                    break;
                case 2:
                    bookTypeBusiness.addBookType(scanner);
                    break;
                case 4:
                    System.out.print("Nhập ID cần xóa: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    bookTypeBusiness.deleteById(id);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Chức năng chưa hỗ trợ hoặc không hợp lệ.");
            }
        } while (choice != 6);
    }

    private static void bookMenu(Scanner scanner, BookBusiness bookBusiness) {
        int choice;
        do {
            System.out.println("******************** BOOK-MENU ********************");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Tạo mới sách");
            System.out.println("3. Cập nhật thông tin sách (Chưa làm)");
            System.out.println("4. Xóa sách");
            System.out.println("5. Hiển thị sách theo giá giảm dần");
            System.out.println("6. Tìm kiếm sách theo tên/nội dung");
            System.out.println("7. Thống kê sách theo nhóm (Chưa làm)");
            System.out.println("8. Quay lại");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    bookBusiness.displayAllBooks();
                    break;
                case 2:
                    bookBusiness.addBook(scanner);
                    break;
                case 4:
                    System.out.print("Nhập ID sách cần xóa: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    bookBusiness.deleteBook(id);
                    break;
                case 5:
                    bookBusiness.sortBooksByPriceDesc();
                    break;
                case 6:
                    System.out.print("Nhập từ khóa tìm kiếm: ");
                    String keyword = scanner.nextLine();
                    bookBusiness.searchBook(keyword);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Chức năng chưa hỗ trợ hoặc không hợp lệ.");
            }
        } while (choice != 8);
    }
}
