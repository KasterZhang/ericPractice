import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariDataSource;

import org.mariadb.jdbc.MariaDbDataSource;

public class SmineDataPlatform {

    private String DBtype;

    /**
     * DATABASE:
     * Apache Derby
     * Firebird
     * H2
     * HSQLDB
     * IBM DB2
     * IBM Informix
     * MS SQL Server
     * Mariadb
     * Oracle
     * OrientDB
     * PostgreSQL
     * SAP MaxDB
     * SQLite
     * SyBase
     * @return
     */
    private HikariDataSource getPool() {
        switch (DBtype) {
            case "mariadb":
                MariadbPool();
                break;
            case "postgresql":
                PostgresPool();
                break;
        }
        return null;

    }
    private void MariadbPool() {

    }
    private void PostgresPool() {

    }

    public static void maintest(String[] args) throws SQLException {
        SmineDataSource ds = new SmineDataSource();
        ds.init(null, 10, 50);
        // System.out.println(ds.reader_PW);
        Connection conn1 = ds.getConnection();
        Connection conn2 = ds.getConnection();
        Statement stmt1 = conn1.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select * from processing_code limit 0,1");
        rs1.next();
        System.out.println(rs1.getLong(1)); //4489



        Statement stmt2 = conn2.createStatement();
        ResultSet rs2 = stmt2.executeQuery("SELECT CONNECTION_ID()");
        rs2.next();
        System.out.println(rs2.getLong(1)); //4489 (reused same connection)
        //close connection
        conn1.close();
        conn2.close();
    }
}