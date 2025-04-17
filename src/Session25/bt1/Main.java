package Session25.bt1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = ConnectDatabase.getConnection();

        if (con != null) {
            System.out.println("Kết nối thành công!");
            try {
                Statement st = con.createStatement();
                String sql = "CREATE TABLE users (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(255), " +
                        "email VARCHAR(255))";
                st.executeUpdate(sql);
                System.out.println("Tạo bảng thành công hoặc bảng đã tồn tại.");
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Lỗi khi tạo bảng:");
            }
        } else {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
        }
    }
}
