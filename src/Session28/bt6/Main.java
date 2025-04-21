package Session28.bt6;

import Session28.bt2.DatabaseConnect;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnect.getConnection();

        if (conn != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = conn.createStatement();

                String createDepartments = "CREATE TABLE IF NOT EXISTS departments (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255))";
                st.executeUpdate(createDepartments);

                String createEmployees = "CREATE TABLE IF NOT EXISTS employees (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "department_id INT, " +
                        "FOREIGN KEY (department_id) REFERENCES departments(id))";
                st.executeUpdate(createEmployees);

                conn.setAutoCommit(false);

                String insertDept = "INSERT INTO departments(id, name) VALUES (?, ?)";
                PreparedStatement psDept = conn.prepareStatement(insertDept);
                psDept.setInt(1, 1);
                psDept.setString(2, "Phòng chả biết");
                psDept.executeUpdate();

                String insertEmp = "INSERT INTO employees(id, name, department_id) VALUES (?, ?, ?)";
                PreparedStatement psEmp = conn.prepareStatement(insertEmp);

                psEmp.setInt(1, 101);
                psEmp.setString(2, "Phan Nhựt Hào");
                psEmp.setInt(3, 1);
                psEmp.executeUpdate();

                psEmp.setInt(1, 102);
                psEmp.setString(2, "Nguyễn Thanh Hạ");
                psEmp.setInt(3, 1);
                psEmp.executeUpdate();

                psEmp.setInt(1, 101); // Trùng id → gây lỗi
                psEmp.setString(2, "Nguyễn Ngọc Diệp");
                psEmp.setInt(3, 1);
                psEmp.executeUpdate();

                conn.commit();
                System.out.println("Giao dịch thành công");

                psDept.close();
                psEmp.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Giao dịch thất bại: " + e.getMessage());
                conn.rollback();
                System.out.println("Rollback toàn bộ giao dịch thành công.");
            }
        }
    }
}
