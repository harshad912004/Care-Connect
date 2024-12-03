package ServletCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FetchDoctorsBasedOnDepartments")
public class FetchDoctorsBasedOnDepartments extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public FetchDoctorsBasedOnDepartments() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String department = request.getParameter("dept_id");
		//System.out.println("requested department id " + department);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/care_connect", "root", "");

			String query1 = "SELECT user.id, user.name, doctors.specialization FROM user "
							+ "JOIN doctors ON user.id = doctors.user_id WHERE doctors.specialization = " + department;
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();

			if (!rs1.next()) {
				System.out.println("ResultSet is empty.");
				out.println("<div>Doctors not available</div>");
			}
			else {
				out.println("<label for='doctor'>Select Doctor<span style='color:red;'>*</span></label>");
				out.println("<select id='doctor' name='doctor' required>");
				out.println("<option value='0'>Select Doctor</option>");
				do {
					//System.out.println("doctor name: " + rs1.getString("name"));
					out.println("<option value='" + rs1.getString("id") + "'>" + rs1.getString("name") + "</option>");
				} while (rs1.next());
			}
			rs1.close();
			st1.close();
			con.close();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			out.println("Error: " + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}