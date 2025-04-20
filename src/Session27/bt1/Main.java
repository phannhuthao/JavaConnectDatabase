package Session27.bt1;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = DatabaseConnect.getConnection();
            if (con != null) {
                System.out.println("Kết nối thành công");

                Statement st = con.createStatement();

                String createTableSql = "CREATE TABLE IF NOT EXISTS employees (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "salary DOUBLE)";
                st.executeUpdate(createTableSql);

                String checkDataSql = "SELECT COUNT(*) FROM employees";
                ResultSet rsCheck = st.executeQuery(checkDataSql);
                if (rsCheck.next() && rsCheck.getInt(1) == 0) {
                    String insertSql = "INSERT INTO employees (id, name, salary) VALUES " +
                            "(1, 'Phan Nhựt Hào', 1000.0), " +
                            "(2, 'Nguyễn Thanh Hạ', 1500.0), " +
                            "(3, 'Lê Văn C', 1200.0), " +
                            "(4, 'Phạm Thị D', 900.0), " +
                            "(5, 'Vũ Văn E', 1300.0)";
                    st.executeUpdate(insertSql);
                }

                Scanner scanner = new Scanner(System.in);
                System.out.print("Nhập id nhân viên cần tìm: ");
                int id = scanner.nextInt();

                String sql = "SELECT * FROM employees WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    int empId = rs.getInt("id");
                    String name = rs.getString("name");
                    double salary = rs.getDouble("salary");
                    System.out.println("ID: " + empId + ", Tên: " + name + ", Lương: " + salary);
                }

                if (!found) {
                    System.out.println("Không tìm thấy nhân viên với id: " + id);
                }

                rs.close();
                ps.close();
                st.close();
                con.close();
            } else {
                System.out.println("Kết nối thất bại");
            }
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra:");
            e.printStackTrace();
        }
    }
}
