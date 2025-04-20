package Session27.bt4;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DatabaseConnect.getConnection();
            if (con != null) {
                System.out.println("Kết nối thành công!");

                Scanner sc = new Scanner(System.in);
                System.out.print("Nhập ID nhân viên cần tìm: ");
                int id = sc.nextInt();

                CallableStatement cs = con.prepareCall("{CALL getEmployeeDetails(?)}");
                cs.setInt(1, id);

                ResultSet rs = cs.executeQuery();
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    int empId = rs.getInt("id");
                    String name = rs.getString("name");
                    double salary = rs.getDouble("salary");

                    System.out.println("Thông tin nhân viên:");
                    System.out.println("ID: " + empId);
                    System.out.println("Tên: " + name);
                    System.out.println("Lương: " + salary);
                }

                if (!found) {
                    System.out.println("Không tìm thấy nhân viên với ID: " + id);
                }

                rs.close();
                cs.close();
                con.close();
            } else {
                System.out.println("Kết nối thất bại.");
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi:");
            e.printStackTrace();
        }
    }
}
