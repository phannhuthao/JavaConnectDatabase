package Session29.bt1.entity;

import java.util.Scanner;

public class BookType implements  IbaseEntity {
    private int id;
    private String typeName;
    private String description;
    private boolean isDelete;

    public BookType() {
    }

    public BookType(int id, String typeName, String description, boolean isDeleted) {
        this.id = id;
        this.typeName = typeName;
        this.description = description;
        this.isDelete = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean deleted) {
        isDelete = deleted;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên loại sách: ");
        this.typeName = scanner.nextLine();
        System.out.print("Nhập mô tả: ");
        this.description = scanner.nextLine();
        this.isDelete = false;
    }

    @Override
    public void displayData() {
        System.out.println("ID loại sách: " + id);
        System.out.println("Loại : " + typeName);
        System.out.println("Mô tả: " + description);
        System.out.println("Trạng thái: " + (isDelete ? "Xóa" : "Hoạt động"));
    }
}
