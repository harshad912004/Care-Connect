package ServletCode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PatientRegistrationServlet")
public class PatientRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PatientRegistrationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String susername = request.getParameter("username");
		String semail = request.getParameter("email");
		String sphone = request.getParameter("phone");
		String spassword = request.getParameter("password");
		String saddress = request.getParameter("address");
		String sdob = request.getParameter("dob");
		String sgender = request.getParameter("gender");
		String sblood_group = request.getParameter("blood_group");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/care_connect", "root", "");
			Statement s1 = con.createStatement();

			String query1 = "select id from account_type where type = 'Patient'";
			ResultSet rs1 = s1.executeQuery(query1);
			if (rs1.next()) {
				int account_type_id = rs1.getInt("id");

				String query2 = "INSERT INTO user (name, email_id, phone_number, password, account_type) VALUES ('" + susername + "', '" + semail + "', '" + sphone + "', '" + spassword + "', '" + account_type_id + "')";
				int affectedRows = s1.executeUpdate(query2, Statement.RETURN_GENERATED_KEYS);

				if (affectedRows > 0) {
					try (ResultSet rs2 = s1.getGeneratedKeys()) {
						if (rs2.next()) {
							int new_record_id = rs2.getInt(1);
							String query3 = "INSERT INTO patients (date_of_birth, address, gender, blood_group, user_id) VALUES ('" + sdob + "', '" + saddress + "', '" + sgender + "', '" + sblood_group + "', '" + new_record_id + "')";
							int affectedRows2 = s1.executeUpdate(query3, Statement.RETURN_GENERATED_KEYS);

							if (affectedRows2 > 0) {
								try (ResultSet rs3 = s1.getGeneratedKeys()) {
									if (rs3.next()) {
										request.setAttribute("successMessage", "Registered successfully!");
										RequestDispatcher disp1 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
										disp1.forward(request, response);
									}
								}
							}
							else {
								String query4 = "DELETE From user where id=" + new_record_id;
								s1.executeUpdate(query4);

								List<String> errors = new ArrayList<>();
								errors.add("Unable to register");
								request.setAttribute("errors", errors);
								RequestDispatcher disp2 = request.getRequestDispatcher("/LoginServlet?action=patient_register");
								disp2.forward(request, response);
							}
						}
					}
				}
				else {
					List<String> errors = new ArrayList<>();
					errors.add("Unable to register");
					request.setAttribute("errors", errors);
					RequestDispatcher disp3 = request.getRequestDispatcher("/LoginServlet?action=patient_register");
					disp3.forward(request, response);
				}
			}
			else {
				List<String> errors = new ArrayList<>();
				errors.add("Unable to register");
				request.setAttribute("errors", errors);
				RequestDispatcher disp4 = request.getRequestDispatcher("/LoginServlet?action=patient_register");
				disp4.forward(request, response);
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
			RequestDispatcher disp5 = request.getRequestDispatcher("/LoginServlet?action=patient_register");
			disp5.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}