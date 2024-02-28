public class Scheduler {
    private DbSql dbSql;

    public Scheduler(DbSql dbSql) {
        this.dbSql = dbSql;
    }

    public boolean scheduleAppointment(Tidsbestilling tidsbestilling) {
        return dbSql.createAppointment(tidsbestilling);
    }
}
