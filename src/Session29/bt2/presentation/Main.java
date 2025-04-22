package Session29.bt2.presentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("******************* SHOP MANAGEMENT *******************");
            System.out.println("1. Quản lý danh mục ");
            System.out.println("2. Quản lý sản phẩm ");
            System.out.println("3. Báo cáo thống kê ");
            System.out.println("4. Thoát");
            System.out.println("******************* --------------- *******************");
            System.out.println(" ");
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Lựa chọn không lệ");
            }
        }
    }

    private static void categoryMenu(Scanner scanner) {
        int choice = 0;
        do {
            System.out.println("******************* CATEGORIES MANAGEMENT *******************");
            System.out.println("1. Hiển thị các danh mục  ");
            System.out.println("2. Hiển thị danh mục sắp xếp theo tên tăng dần  ");
            System.out.println("3. Thêm mới danh mục  ");
            System.out.println("4. Cập nhật danh mục  ");
            System.out.println("5. Xóa danh mục  ");
            System.out.println("6. Tìm kiếm danh mục theo tên  ");
            System.out.println("7. Cập nhật trạng thái danh mục  ");
            System.out.println("8. Thoát ");
            System.out.println("******************* -------------------- ******************* ");
        } while (choice != 8);
    }

    private static void productMenu(Scanner scanner) {
        int choice = 0;
        do {
            System.out.println("*******************  PRODUCT MANAGEMENT  *******************");
            System.out.println("1. Hiển thị sản phẩm  ");
            System.out.println("2. Hiển thị sản phẩm theo giá giảm dần  ");
            System.out.println("3. Thêm mới sản phẩm  ");
            System.out.println("4. Cập nhật sản phẩm  ");
            System.out.println("5. Cập nhật trạng thái sản phẩm  ");
            System.out.println("6. Xóa sản phẩm  ");
            System.out.println("7. Tìm kiếm sản phẩm theo tên sản phẩm  ");
            System.out.println("8. Tìm kiếm sản phẩm trong khoảng giá a-b ");
            System.out.println("9. Thoát ");
            System.out.println("******************* -------------------- ******************* ");
        } while (choice != 9);
    }


 }
