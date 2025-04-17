package Session25.bt3;

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
                        "(3, 'Nguyễn Tiến Linh', 'Việt Nam')";
                st.executeUpdate(sqlInsert);

                System.out.println("Thêm nhân viên thành công!");

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
