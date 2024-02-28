import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSql {
    private String url = "jdbc:sqlite:path_to_db"; // Opdater med den faktiske database-URL

    public DbSql() {
        try {
            Connection conn = DriverManager.getConnection(url);
            // Initialiser databaseforbindelsen
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returnerer true, hvis brugeren findes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createAppointment(Tidsbestilling tidsbestilling) {
        String sql = "INSERT INTO appointments (medarbejderId, kundeId, serviceId, tidspunkt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tidsbestilling.getMedarbejderId());
            pstmt.setInt(2, tidsbestilling.getKundeId());
            pstmt.setInt(3, tidsbestilling.getServiceId());
            pstmt.setString(4, tidsbestilling.getTidspunkt());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    Hallo jeg hedder lauge

    // Yderligere metoder for databasemanipulation
}
