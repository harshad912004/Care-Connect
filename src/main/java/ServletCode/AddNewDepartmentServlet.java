package ServletCode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddNewDepartmentServlet")
public class AddNewDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddNewDepartmentServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/care_connect", "root", "");
			Statement st1 = con.createStatement();

			String query1 = "INSERT INTO departments (dept_name) VALUES (?)";
			PreparedStatement ps1 = con.prepareStatement(query1);
			ps1.setString(1, request.getParameter("dept_name"));

			int rowsInserted = ps1.executeUpdate();
			if (rowsInserted > 0) {
				request.setAttribute("successMessage", "New department added successfully!");
				RequestDispatcher disp1 = request.getRequestDispatcher("/AdminServlet?action=departments");
				disp1.forward(request, response);
			}
			else {
				System.out.println("Insertion failed, no rows affected.");
				List<String> errors = new ArrayList<>();
				errors.add("Unable to add new department");
				request.setAttribute("errors", errors);
				RequestDispatcher disp2 = request.getRequestDispatcher("/AdminServlet?action=departments");
				disp2.forward(request, response);
			}
			con.close();
		}
		catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error: " + e.getMessage());

			request.getSession().invalidate();
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			RequestDispatcher disp3 = request.getRequestDispatcher("/AdminServlet?action=departments");
			disp3.forward(request, response);
		}
	}
}