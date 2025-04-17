package Session25.bt4;

import Session25.bt3.ConnectDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectDatabase.getConnection();

        if (conn != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = conn.createStatement();

                String sqlCreate = "CREATE TABLE IF NOT EXISTS employees (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "address VARCHAR(255))";
                st.executeUpdate(sqlCreate);

                String sqlInsert = "INSERT INTO employees (id, name, address) VALUES " +
                        "(1, 'Đăng Văn Lâm', 'Đức'), " +
                        "(2, 'Nguyễn Xuân Son', 'Brazil'), " +
                        "(3, 'Nguyễn Tiến Linh', 'Việt Nam') " +
                        "ON DUPLICATE KEY UPDATE name = VALUES(name), address = VALUES(address)";
                st.executeUpdate(sqlInsert);

                String sqlUpdate = "UPDATE employees SET name = 'Bùi Tiến Dũng' WHERE id = 3";
                st.executeUpdate(sqlUpdate);

                System.out.println("Thêm + Cập nhật nhân viên thành công!");

                st.close();
                conn.close();

            } catch (Exception e) {
                System.out.println("Lỗi xử lý SQL:");
                e.printStackTrace();
            }
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
}
