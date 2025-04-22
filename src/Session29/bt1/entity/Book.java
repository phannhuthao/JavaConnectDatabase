package Session29.bt1.entity;

import java.util.Scanner;

public class Book implements IbaseEntity {
    private int id;
    private String bookName;
    private String author;
    private String content;
    private int totalPages;
    private String publisher;
    private double price;
    private int typeId;
    private boolean isDeleted;

    public Book(int id, String bookName, String author, String content, int totalPages, String publisher, double price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.content = content;
        this.totalPages = totalPages;
        this.publisher = publisher;
        this.price = price;
        this.typeId = 0;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên sách: ");
        this.bookName = scanner.nextLine();
        System.out.print("Nhập tên tác giả: ");
        this.author = scanner.nextLine();
        System.out.print("Nhập nội dung: ");
        this.content = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Nhập số trang: ");
                this.totalPages = Integer.parseInt(scanner.nextLine());
                if (this.totalPages > 0) break;
                System.out.println("Số trang phải lớn hơn 0");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên dương");
            }
        }

        System.out.print("Nhập nhà xuất bản: ");
        this.publisher = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Nhập giá: ");
                this.price = Double.parseDouble(scanner.nextLine());
                if (this.price > 0) break;
                System.out.println("Giá sách phải lớn hơn 0");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại với số thực dương");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập số ID: ");
                this.typeId = Integer.parseInt(scanner.nextLine());
                if (this.typeId > 0) break;
                System.out.println("ID thể loại phải lớn hơn 0");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên dương");
            }
        }

        this.isDeleted = false;
    }



    @Override
    public void displayData() {
        System.out.println("ID: " + id);
        System.out.println("Tên sách: " + bookName);
        System.out.println("Tác giả: " + author);
        System.out.println("Nội dung: " + content);
        System.out.println("Tổng số trang: " + totalPages);
        System.out.println("Nhà xuất bản: " + publisher);
        System.out.println("Giá: " + price);
        System.out.println("Type ID: " + typeId);
        System.out.println("Trạng thái: " + (isDeleted ? "Xóa" : "Hoạt động"));
    }
}
