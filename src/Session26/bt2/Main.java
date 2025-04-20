package Session26.bt2;

import Session26.bt1.ConnectDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = ConnectDatabase.getConnection();

        if (con != null) {
            System.out.println("Kết nối thành công!");
            try {
                Statement st = con.createStatement();

                String sql = "CREATE TABLE customers (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "age INT NOT NULL)";
                st.executeUpdate(sql);

                st.executeUpdate("INSERT INTO customers(name, age) VALUES " +
                        "('Hào', 1), " +
                        "('Hạ', 2), " +
                        "('Diệp', 3), " +
                        "('Nhi', 4), " +
                        "('Hương', 5)");

                ResultSet rs = st.executeQuery("SELECT * FROM customers");
                System.out.println("\nDữ liệu trong bảng customers:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.println("ID: " + id + ", Tên: " + name + ", Tuổi: " + age);
                }

                rs.close();
                st.close();
                con.close();

            } catch (Exception e) {
                System.out.println("Lỗi xử lý SQL:");
                e.printStackTrace();
            }
        } else {
            System.out.println("Kết nối thất bại!");
        }
    }
}
