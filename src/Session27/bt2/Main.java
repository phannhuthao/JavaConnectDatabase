package Session27.bt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnect.getConnection();
        if (con != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = con.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS students (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "age INT NOT NULL, " +
                        "major VARCHAR(255))";
                st.executeUpdate(sql);

                Scanner sc = new Scanner(System.in);

                System.out.print("Nhập id: ");
                int id = sc.nextInt();
                sc.nextLine(); // Clear buffer

                System.out.print("Nhập name: ");
                String name = sc.nextLine();

                System.out.print("Nhập age: ");
                int age = sc.nextInt();
                sc.nextLine(); // Clear buffer

                System.out.print("Nhập major: ");
                String major = sc.nextLine();

                String insertSql = "INSERT INTO students (id, name, age, major) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(insertSql);
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setInt(3, age);
                ps.setString(4, major);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Thêm sinh viên thành công.");
                } else {
                    System.out.println("Không thể thêm sinh viên.");
                }

                ps.close();
                st.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi:");
                e.printStackTrace();
            }
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
}
