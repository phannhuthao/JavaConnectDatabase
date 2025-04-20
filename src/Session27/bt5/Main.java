package Session27.bt5;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnect.getConnection();
        if (con != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = con.createStatement();

                String sqlDepartment = "CREATE TABLE IF NOT EXISTS department (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(50))";
                st.executeUpdate(sqlDepartment);

                String sqlEmployee = "CREATE TABLE IF NOT EXISTS employee (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(50), " +
                        "salary DOUBLE, " +
                        "department_id INT, " +
                        "FOREIGN KEY (department_id) REFERENCES department(id))";
                st.executeUpdate(sqlEmployee);

                Scanner sc = new Scanner(System.in);
                StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
                boolean hasName = false, hasDept = false, hasSalary = false;

                System.out.print("Nhập tên (Enter bỏ qua): ");
                String name = sc.nextLine();
                if (!name.isEmpty()) {
                    sql.append(" AND name LIKE ?");
                    hasName = true;
                }

                System.out.print("Nhập department_id (Enter bỏ qua): ");
                String deptInput = sc.nextLine();
                int deptId = 0;
                if (!deptInput.isEmpty()) {
                    deptId = Integer.parseInt(deptInput);
                    sql.append(" AND department_id = ?");
                    hasDept = true;
                }

                System.out.print("Nhập mức lương tối thiểu (Enter bỏ qua): ");
                String salaryInput = sc.nextLine();
                double salary = 0;
                if (!salaryInput.isEmpty()) {
                    salary = Double.parseDouble(salaryInput);
                    sql.append(" AND salary > ?");
                    hasSalary = true;
                }

                PreparedStatement ps = con.prepareStatement(sql.toString());

                int index = 1;
                if (hasName) ps.setString(index++, "%" + name + "%");
                if (hasDept) ps.setInt(index++, deptId);
                if (hasSalary) ps.setDouble(index, salary);

                ResultSet rs = ps.executeQuery();
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println("ID: " + rs.getInt("id") +
                            ", Tên: " + rs.getString("name") +
                            ", Lương: " + rs.getDouble("salary") +
                            ", Department ID: " + rs.getInt("department_id"));
                }

                if (!found) {
                    System.out.println("Không tìm thấy nhân viên phù hợp.");
                }

                rs.close();
                ps.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Kết nối thất bại.");
        }
    }
}
