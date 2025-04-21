package Session28.bt4;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connA = DatabaseConnect.getConnection();
        Connection connB = DatabaseConnect.getConnection();

        if (connA != null && connB != null) {
            System.out.println("Kết nối thành công tới hai ngân hàng");

            try {
                Statement stA = connA.createStatement();
                Statement stB = connB.createStatement();

                String sqlCreate = "CREATE TABLE IF NOT EXISTS bank_accounts (" +
                        "account_id INT PRIMARY KEY, " +
                        "account_name VARCHAR(255), " +
                        "balance INT NOT NULL, " +
                        "bank_name VARCHAR(255))";
                stA.executeUpdate(sqlCreate);

                stA.executeUpdate("DELETE FROM bank_accounts");
                stA.executeUpdate("INSERT INTO bank_accounts VALUES (1, 'Ngân hàng A - Hào', 5000, 'BankA')");
                stB.executeUpdate("INSERT INTO bank_accounts VALUES (2, 'Ngân hàng B - Hạ', 2000, 'BankB')");

                connA.setAutoCommit(false);
                connB.setAutoCommit(false);

                int amount = 3000;

                PreparedStatement psCheck = connA.prepareStatement("SELECT balance FROM bank_accounts WHERE account_id = ?");
                psCheck.setInt(1, 1);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next()) {
                    int balance = rs.getInt("balance");
                    if (balance < amount) {
                        throw new Exception("Ngân hàng A không đủ tiền để chuyển!");
                    }
                } else {
                    throw new Exception("Không tìm thấy tài khoản ở ngân hàng A!");
                }

                PreparedStatement psDeduct = connA.prepareStatement("UPDATE bank_accounts SET balance = balance - ? WHERE account_id = ?");
                psDeduct.setInt(1, amount);
                psDeduct.setInt(2, 1);
                psDeduct.executeUpdate();

                PreparedStatement psAdd = connB.prepareStatement("UPDATE bank_accounts SET balance = balance + ? WHERE account_id = ?");
                psAdd.setInt(1, amount);
                psAdd.setInt(2, 2);
                psAdd.executeUpdate();

                connA.commit();
                connB.commit();
                System.out.println("Chuyển khoản thành công!");

                ResultSet rs1 = connA.createStatement().executeQuery("SELECT * FROM bank_accounts WHERE account_id = 1");
                while (rs1.next()) {
                    System.out.println("Tài khoản A - Số dư: " + rs1.getInt("balance"));
                }

                ResultSet rs2 = connB.createStatement().executeQuery("SELECT * FROM bank_accounts WHERE account_id = 2");
                while (rs2.next()) {
                    System.out.println("Tài khoản B - Số dư: " + rs2.getInt("balance"));
                }

                psCheck.close(); psDeduct.close(); psAdd.close();
                rs.close(); rs1.close(); rs2.close();
                connA.close(); connB.close();

            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
                if (connA != null) connA.rollback();
                if (connB != null) connB.rollback();
                System.out.println("Rollback cả hai ngân hàng thành công.");
            }
        }
    }
}
