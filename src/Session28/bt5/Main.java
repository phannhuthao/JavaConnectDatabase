package Session28.bt5;

import Session28.bt2.DatabaseConnect;

import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnect.getConnection();

        if (conn != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = conn.createStatement();

                String sqlCreateOrders = "CREATE TABLE IF NOT EXISTS orders (" +
                        "order_id INT PRIMARY KEY, " +
                        "customer_name VARCHAR(255), " +
                        "order_date DATE)";
                st.executeUpdate(sqlCreateOrders);

                String sqlCreateDetails = "CREATE TABLE IF NOT EXISTS orderDetails (" +
                        "detail_id INT PRIMARY KEY, " +
                        "order_id INT, " +
                        "product_name VARCHAR(255), " +
                        "quantity INT, " +
                        "FOREIGN KEY (order_id) REFERENCES orders(order_id))";
                st.executeUpdate(sqlCreateDetails);

                conn.setAutoCommit(false);

                String sqlInsertOrder = "INSERT INTO orders(order_id, customer_name, order_date) VALUES (?, ?, ?)";
                PreparedStatement psOrder = conn.prepareStatement(sqlInsertOrder);
                psOrder.setInt(1, 1001);
                psOrder.setString(2, "Phan Nhựt Hào");
                psOrder.setDate(3, Date.valueOf(LocalDate.now()));
                psOrder.executeUpdate();

                String sqlInsertDetail = "INSERT INTO orderDetails(detail_id, order_id, product_name, quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement psDetail = conn.prepareStatement(sqlInsertDetail);

                psDetail.setInt(1, 2001);
                psDetail.setInt(2, 1001);
                psDetail.setString(3, "Iphone 15");
                psDetail.setInt(4, 2);
                psDetail.executeUpdate();

                psDetail.setInt(1, 2002);
                psDetail.setInt(2, 1001);
                psDetail.setString(3, "Samsung Galaxy");
                psDetail.setInt(4, -3); // Gây lỗi
                psDetail.executeUpdate();

                conn.commit();
                System.out.println("Giao dịch thành công!");

                psOrder.close();
                psDetail.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Giao dịch thất bại: " + e.getMessage());
                conn.rollback();
                System.out.println("Rollback toàn bộ giao dịch thành công.");
            }
        }
    }
}
