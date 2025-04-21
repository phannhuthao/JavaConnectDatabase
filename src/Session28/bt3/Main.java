package Session28.bt3;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnect.getConnection();

        if (conn != null) {
            System.out.println("Kết nối thành công");

            try {
                Statement st = conn.createStatement();

                String sqlCreate = "CREATE TABLE IF NOT EXISTS accounts (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "balance INT NOT NULL)";
                st.executeUpdate(sqlCreate);

                st.executeUpdate("DELETE FROM accounts");

                st.executeUpdate("INSERT INTO accounts (id, name, balance) VALUES (1, 'Phan Nhựt Hào', 5000)");
                st.executeUpdate("INSERT INTO accounts (id, name, balance) VALUES (2, 'Nguyễn Thanh Hạ', 2000)");

                conn.setAutoCommit(false);

                int amount = 3000;

                // Kiểm tra số dư tài khoản A
                PreparedStatement checkBalance = conn.prepareStatement("SELECT balance FROM accounts WHERE id = ?");
                checkBalance.setInt(1, 1);
                ResultSet rs = checkBalance.executeQuery();
                if (rs.next()) {
                    int balanceA = rs.getInt("balance");

                    if (balanceA < amount) {
                        throw new Exception("Không đủ số dư để chuyển tiền!");
                    }

                    // Trừ tiền tài khoản A
                    PreparedStatement deduct = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
                    deduct.setInt(1, amount);
                    deduct.setInt(2, 1);
                    deduct.executeUpdate();

                    // Cộng tiền tài khoản B
                    PreparedStatement add = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
                    add.setInt(1, amount);
                    add.setInt(2, 2);
                    add.executeUpdate();

                    conn.commit();
                    System.out.println("Chuyển tiền thành công!");

                    deduct.close();
                    add.close();
                }

                rs.close();
                checkBalance.close();
                st.close();

                Statement show = conn.createStatement();
                ResultSet result = show.executeQuery("SELECT * FROM accounts");
                while (result.next()) {
                    System.out.println("ID: " + result.getInt("id") +
                            ", Name: " + result.getString("name") +
                            ", Balance: " + result.getInt("balance"));
                }
                result.close();
                show.close();

                conn.close();

            } catch (Exception e) {
                System.out.println("Có lỗi xảy ra: " + e.getMessage());
                conn.rollback();
                System.out.println("Rollback hoàn tất.");
            }
        }
    }
}
