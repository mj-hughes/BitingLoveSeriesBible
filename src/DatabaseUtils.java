import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseUtils {
    private static final String USER_NAME="mhughes";
    private static final String PASSWORD="mhughes";
    private static final String DRIVER_NAME="org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DATABASE_PATH="/WEB-INF/lib/dbBLuniverse";

    /**
     * closeAll closes connection, statement/preparedstatement, and result set
     *  individually (each enclosed in a try-catch)
     * will also work with prepared statement
     * closeAll from Stacy Read's Database Utilities (permission given to use Sep 11 2019).
     * @param conn Connection
     * @param stmt Statement or PreparedStatement
     * @param rset ResultSet
     */
    public static void closeAll(Connection conn, Statement stmt, ResultSet rset) {
        if (rset != null) {
            try {
                rset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * getUserName
     * @return string constant USER_NAME for database
     */
    public static final String getUserName() {
        return USER_NAME;
    }

    /**
     * getPassword
     * @return string constant PASSWORD for database
     */
    public static final String getPassword() {
        return PASSWORD;
    }

    /**
     * getDriverName
     * @return string constant DRIVER_NAME
     */
    public static final String getDriverName() {
        return DRIVER_NAME;
    }

    /**
     * getDatabasePath
     * @return string constant DATABASE_PATH
     */
    public static final String getDatabasePath () {
        return DATABASE_PATH;
    }
}