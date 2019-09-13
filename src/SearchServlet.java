import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "SearchServlet",
urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            // Search term
            String searchTerm=request.getParameter("searchTerm");

            // Load the driver
            Class.forName(DatabaseUtils.getDriverName());

            // Obtain a connection with the database's path
            // or is the jdbc:derby the driver_name??
            conn = DriverManager.getConnection("jdbc:derby:"+getServletContext().getRealPath(DatabaseUtils.getDatabasePath()), DatabaseUtils.getUserName(), DatabaseUtils.getPassword());

            // Build the SQL
            StringBuilder sql=new StringBuilder("SELECT *");
            sql.append("FROM persona ");
            sql.append("WHERE UPPER(name) LIKE UPPER(?)");

            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, "%"+searchTerm+"%");
            rset = pstmt.executeQuery();

            // Create an output string from the return set
            String[] fieldLabels = {"Persona #", "Name", "Age Year 0", "Height", "Eye Color", "Hair Color", "Body Type", "Picture Link", "Notes"};
            StringBuilder html =new StringBuilder("<html><body>");
            while (rset.next()) {
                for (int fieldNum=1; fieldNum<10; fieldNum++) {
                    if (fieldNum==1 || fieldNum==3) {
                        int fieldValue=rset.getInt(fieldNum);
                        html.append("<p>").append(fieldLabels[fieldNum-1]).append(": ").append(fieldValue).append("</p>");

                    } else {
                        String fieldValue=rset.getString(fieldNum);
                        html.append("<p>").append(fieldLabels[fieldNum-1]).append(": ").append(fieldValue).append("</p>");
                    }
                }
                html.append("<br>");

            }
            html.append("</body></html>");

            // Show the user the data
            response.getWriter().print(html.toString());

        } catch (SQLException | ClassNotFoundException e) {
            response.getWriter().print(e.getMessage());
        } finally {
            // Shut down Derby: connection, statement, resultset
            DatabaseUtils.closeAll(conn, pstmt, rset);
        }

    }
}
