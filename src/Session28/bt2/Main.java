package Session28.bt2;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnect.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = conn.createStatement();

                String sqlCreate = "CREATE TABLE IF NOT EXISTS newuser (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "email VARCHAR(225))";
                st.executeUpdate(sqlCreate);

                conn.setAutoCommit(false);

                // hợp lệ
                String sqlInsert1 = "INSERT INTO newuser (id, name, email) VALUES (?, ?, ?)";
                PreparedStatement ps1 = conn.prepareStatement(sqlInsert1);
                ps1.setInt(1, 1);
                ps1.setString(2, "Phan Nhựt Hào");
                ps1.setString(3, "hao@gmail.com");
                ps1.executeUpdate();

                // không hợp lệ bị trùng id
                String sqlInsert2 = "INSERT INTO newuser (id, name, email) VALUES (?, ?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sqlInsert2);
                ps2.setInt(1, 1);
                ps2.setString(2, "Nguyễn Thanh Hạ");
                ps2.setString(3, "hanguyen@gmail.com");
                ps2.executeUpdate();

                conn.commit();

                ps1.close();
                ps2.close();
                st.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rollback do lỗi...");
                conn.rollback();
            }

            Statement stCheck = conn.createStatement();
            ResultSet rs = stCheck.executeQuery("SELECT * FROM newuser");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Tên: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
            }
            if (!hasData) {
                System.out.println("Không có dữ liệu nào được thêm do đã rollback.");
            }

            rs.close();
            stCheck.close();
            conn.close();
        }
    }
}
