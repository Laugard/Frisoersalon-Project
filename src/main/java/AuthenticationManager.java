public class AuthenticationManager {
    private DbSql dbSql;

    public AuthenticationManager(DbSql dbSql) {
        this.dbSql = dbSql;
    }

    public boolean authenticate(String username, String password) {
        return dbSql.authenticate(username, password);
    }
}
