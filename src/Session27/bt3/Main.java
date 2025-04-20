package Session27.bt3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnect.getConnection();
            if (conn != null) {
                System.out.println("Kết nối thành công");

                Statement st = conn.createStatement();

                String createTableSql = "CREATE TABLE IF NOT EXISTS employees (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "salary DOUBLE)";
                st.executeUpdate(createTableSql);

                ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM employees");
                if (rs.next() && rs.getInt(1) == 0) {
                    String insertSql = "INSERT INTO employees (id, name, salary) VALUES " +
                            "(1, 'Phan Nhựt Hào', 1000.0), " +
                            "(2, 'Nguyễn Thanh Hạ', 1200.0), " +
                            "(3, 'Nguyễn Ngọc Diệp', 1500.0)";
                    st.executeUpdate(insertSql);
                }

                Scanner sc = new Scanner(System.in);
                System.out.print("Nhập ID nhân viên muốn cập nhật lương: ");
                int id = sc.nextInt();

                System.out.print("Nhập mức lương mới: ");
                double salary = sc.nextDouble();

                String updateSql = "UPDATE employees SET salary = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(updateSql);
                ps.setDouble(1, salary);
                ps.setInt(2, id);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Cập nhật lương thành công.");
                } else {
                    System.out.println("Không tìm thấy nhân viên với ID: " + id);
                }

                ps.close();
                st.close();
                conn.close();
            } else {
                System.out.println("Kết nối thất bại.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra:");
            e.printStackTrace();
        }
    }
}
