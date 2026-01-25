import java.sql.*;

public class Main2 {
    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/testdb",
            "root",
            "password"
        );

        Statement st = con.createStatement();

        st.executeUpdate(
            "CREATE TABLE IF NOT EXISTS users (" +
            "id INT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(50))"
        );

        st.executeUpdate(
            "INSERT INTO users(name) VALUES ('Alice'), ('Bob'), ('Charlie')"
        );

        System.out.println("Table created and data inserted");

        con.close();
    }
}
