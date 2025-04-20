package Session27.bt6;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnect.getConnection();

        if (conn != null) {
            System.out.println("Kết nối thành công");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập department_id để tính tổng lương: ");
            int deptId = scanner.nextInt();

            try {
                CallableStatement cstmt = conn.prepareCall("{CALL calculateTotalSalaryByDept(?)}");
                cstmt.setInt(1, deptId);

                ResultSet rs = cstmt.executeQuery();
                if (rs.next()) {
                    double totalSalary = rs.getDouble("total_salary");
                    System.out.println("Tổng lương phòng ban có ID = " + deptId + " là: " + totalSalary);
                } else {
                    System.out.println("Không tìm thấy dữ liệu cho phòng ban này.");
                }

                rs.close();
                cstmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
