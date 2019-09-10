import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet(name = "Servlet1", urlPatterns="/dataService")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn=null;
        Statement stmt = null;
        ResultSet rset=null;

        try {

            // Load the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // Obtain a connection with the database's path
            String absPath=getServletContext().getRealPath("/WEB-INF/lib/dbBLuniverse");
            conn = DriverManager.getConnection("jdbc:derby:"+absPath, "mhughes", "mhughes");

            // Build the SQL
            StringBuilder sql=new StringBuilder("SELECT p.*, b.title, pb.role ");
            sql.append("FROM persona p ");
            sql.append("JOIN persona_book pb ");
            sql.append("ON p.persona_id=pb.persona_id ");
            sql.append("JOIN book b ");
            sql.append("ON pb.book_id=b.book_id ");
            // Create a statement and execute the SQL
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql.toString());

            // Create an output string from the return set
            StringBuilder output=new StringBuilder("<html><body><ul>");
            while (rset.next()) {
                // Column numbers start at 1. can use column name, but it's slower.
                // get the persona id, name, age, height, eye_color and notes from this result row
                int persona=rset.getInt(1);
                String nm=rset.getString(2);
                // could do this but slower String nm=rset.getString("name");
                int age = rset.getInt(3);
                String ht=rset.getString(4);
                // String eye=rset.getString(5);
                String notes=rset.getString(9);
                String title=rset.getString(10);
                String role=rset.getString(11);

                output.append("<li>#").append(persona);
                output.append(", ").append(nm);
                output.append(", ").append(age);
                output.append(", ").append(ht);
                output.append(", ").append(notes);
                output.append(", ").append(title);
                output.append(", ").append(role);
                output.append("</li>");
            }
            output.append("</ul></body></html>");

            response.getWriter().print(output.toString());

        } catch (SQLException | ClassNotFoundException e) {
            // If there's a problem locating the driver, make the error the response
            response.getWriter().print(e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeAll(conn, stmt, rset);
            // Shut down Derby documentation shows it this way:
//            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        }


    }
}
