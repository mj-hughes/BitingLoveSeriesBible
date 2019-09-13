import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ListServlet",
urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn=null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            // Load the driver
            Class.forName(DatabaseUtils.getDriverName());

            // Obtain a connection with the database's path
            String absPath=getServletContext().getRealPath(DatabaseUtils.getDatabasePath());
            conn = DriverManager.getConnection("jdbc:derby:"+absPath, DatabaseUtils.getUserName(), DatabaseUtils.getPassword());

            // Build the SQL
            StringBuilder sql=new StringBuilder("SELECT p.name ");
            sql.append("FROM persona p ");
            sql.append("ORDER BY p.name ");

            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql.toString());

            // Create an output string from the return set
            StringBuilder output=new StringBuilder("<html><body><h2>Character List</h2><ul>");
            while (rset.next()) {
                // Column numbers start at 1. can use column name, but it's slower (a few milliseconds).
                String nm=rset.getString(1); // could do this but slower String nm=rset.getString("name");
                output.append("<li>").append(nm).append("</li>");
            }
            output.append("</ul></body></html>");

            // Show the user the data
            response.getWriter().print(output.toString());

        } catch (SQLException | ClassNotFoundException e) {
            response.getWriter().print(e.getMessage());
        } finally {
            // Shut down Derby: connection, statement, resultset
            DatabaseUtils.closeAll(conn, stmt, rset);
        }

    }
}
