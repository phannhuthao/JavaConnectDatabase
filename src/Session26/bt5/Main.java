package Session26.bt5;

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
                        "(2, 'Hạ', 25, 150000), " +
                        "(3, 'Diệp', 25, 300000), " +
                        "(4, 'Nhi', 25, 500000), " +
                        "(5, 'Ly', 25, 1000), " +
                        "(6, 'Tuấn', 30, 200000), " +
                        "(7, 'Lan', 28, 400000)";
                st.executeUpdate(sqlInsert);

                String sqlSelect = "SELECT * FROM employees ORDER BY salary DESC LIMIT 5";
                ResultSet rs = st.executeQuery(sqlSelect);

                System.out.println("\nDanh sách top 5 nhân viên có lương cao nhất:");
                while (rs.next()) {
                    System.out.println(
                            "ID: " + rs.getInt("id") +
                                    " | Name: " + rs.getString("name") +
                                    " | Age: " + rs.getInt("age") +
                                    " | Salary: " + rs.getDouble("salary")
                    );
                }

                rs.close();
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
