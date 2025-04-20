package Session26.bt6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = ConnectDatabase.getConnection();

        if (con != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = con.createStatement();

                String sqlCreate = "CREATE TABLE IF NOT EXISTS employees (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "age INT, " +
                        "salary DOUBLE)";
                st.executeUpdate(sqlCreate);

                st.executeUpdate("DELETE FROM employees");

                String sqlInsert = "INSERT INTO employees (id, name, age, salary) VALUES " +
                        "(1, 'Hào', 25, 100000), " +
                        "(2, 'Hạ', 26, 150000), " +
                        "(3, 'Diệp', 24, 300000), " +
                        "(4, 'Linh', 28, 500000), " +
                        "(5, 'Em', 30, 1000), " +
                        "(6, 'Nhi', 27, 200000), " +
                        "(7, 'Trang', 23, 400000), " +
                        "(8, 'Hương', 29, 350000)";
                st.executeUpdate(sqlInsert);

                ResultSet rs1 = st.executeQuery("SELECT COUNT(*) AS total FROM employees");
                if (rs1.next()) {
                    System.out.println("Tổng số lượng nhân viên: " + rs1.getInt("total"));
                }

                ResultSet rs2 = st.executeQuery("SELECT AVG(salary) AS avg_salary FROM employees");
                if (rs2.next()) {
                    System.out.println("Mức lương trung bình: " + rs2.getDouble("avg_salary"));
                }

                ResultSet rs3 = st.executeQuery("SELECT MAX(salary) AS max_salary, MIN(salary) AS min_salary FROM employees");
                if (rs3.next()) {
                    System.out.println("Mức lương cao nhất: " + rs3.getDouble("max_salary"));
                    System.out.println("Mức lương thấp nhất: " + rs3.getDouble("min_salary"));
                }

                rs1.close();
                rs2.close();
                rs3.close();
                st.close();
                con.close();

            } catch (Exception e) {
                System.out.println("Lỗi xử lý SQL:");
                e.printStackTrace();
            }

        } else {
            System.out.println("Kết nối thất bại");
        }
    }
}
