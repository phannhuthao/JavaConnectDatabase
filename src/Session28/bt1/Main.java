package Session28.bt1;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnect.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công");

            System.out.println("Auto-commit ban đầu: " + conn.getAutoCommit());

            try {
                Statement st = conn.createStatement();

                String sql = "CREATE TABLE IF NOT EXISTS user (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "email VARCHAR(225))";
                st.executeUpdate(sql);

                conn.setAutoCommit(false);
                System.out.println("Auto-commit sau khi tắt: " + conn.getAutoCommit());

                String insertSql = "INSERT INTO user (id, name, email) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insertSql);
                ps.setInt(1, 1);
                ps.setString(2, "Phan Nhựt Hào");
                ps.setString(3, "hao@gmail.com");
                ps.executeUpdate();

                conn.commit();
                System.out.println("Dữ liệu đã được commit.");

                ResultSet rs = st.executeQuery("SELECT * FROM user");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Tên: " + rs.getString("name"));
                    System.out.println("Email: " + rs.getString("email"));
                }

                rs.close();
                ps.close();
                st.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                if (conn != null) {
                    System.out.println("Rollback do lỗi...");
                    conn.rollback();
                }
            }
        }
    }
}
