package ServletCode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BookAppointmentFormSubmitServlet")
public class BookAppointmentFormSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BookAppointmentFormSubmitServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("user_id");

		System.out.println("appointment date and time: " + request.getParameter("date"));

		Timestamp datetime = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			java.util.Date parsedDate = dateFormat.parse(request.getParameter("date"));
			datetime = new Timestamp(parsedDate.getTime());

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/care_connect", "root", "");
			Statement s1 = con.createStatement();

			String sql = "INSERT INTO appointments (patient_id, doctor_id, dept_id, date_time_appointment, status_flag) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st1 = con.prepareStatement(sql);
			st1.setInt(1, user_id);
			st1.setInt(2, Integer.parseInt(request.getParameter("doctor")));
			st1.setInt(3, Integer.parseInt(request.getParameter("department")));
			st1.setTimestamp(4, datetime);
			st1.setString(5, "Not Accepted Yet");

			int rowsInserted = st1.executeUpdate();
			if (rowsInserted > 0) {
				request.setAttribute("successMessage", "Appointment request sent successfully!");
				RequestDispatcher disp1 = request.getRequestDispatcher("/PatientServlet?action=requested_appointments");
				disp1.forward(request, response);
			}
			else {
				System.out.println("Insertion failed, no rows affected.");
				List<String> errors = new ArrayList<>();
				errors.add("Unable to request appointment");
				request.setAttribute("errors", errors);
				RequestDispatcher disp2 = request.getRequestDispatcher("/PatientServlet?action=book_appointment");
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
			RequestDispatcher disp3 = request.getRequestDispatcher("/PatientServlet?action=book_appointment");
			disp3.forward(request, response);
		}
	}
}