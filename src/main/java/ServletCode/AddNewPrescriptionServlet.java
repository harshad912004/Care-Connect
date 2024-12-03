package ServletCode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddNewPrescriptionServlet")
public class AddNewPrescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int new_record_id = 0;

    public AddNewPrescriptionServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		
		int user_id = (int) session.getAttribute("user_id"); // doctor_id
		String date = request.getParameter("date_of_prescription");
		int patient_id = Integer.parseInt(request.getParameter("patient_name"));
		int weight = Integer.parseInt(request.getParameter("weight_of_patient"));
		String diagnosis = request.getParameter("diagnosis");
		String tests = request.getParameter("tests");
		String payment_type = request.getParameter("payment_type");
		int fees = Integer.parseInt(request.getParameter("fees"));
		String remarks = request.getParameter("remarks");
		String follow_up = request.getParameter("follow_up");

		// Use a Map to store medicine names with index as key
		Map<Integer, String> medicines = new HashMap<>();
		Map<Integer, String> medTypes = new HashMap<>();
		Map<Integer, String> measures = new HashMap<>();
		Map<Integer, String> dosages = new HashMap<>();
		Map<Integer, String> instructions = new HashMap<>();
		Map<Integer, Integer> days = new HashMap<>();
		
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			if (parameterName.startsWith("medicine[")) {
				int index = Integer.parseInt(parameterName.substring(parameterName.indexOf("[") + 1, parameterName.indexOf("]")));
				medicines.put(index, request.getParameter(parameterName));
			}
			else if (parameterName.startsWith("type[")) {
				int index = Integer.parseInt(parameterName.substring(parameterName.indexOf("[") + 1, parameterName.indexOf("]")));
				medTypes.put(index, request.getParameter(parameterName));
			}
			else if (parameterName.startsWith("measure[")) {
				int index = Integer.parseInt(parameterName.substring(parameterName.indexOf("[") + 1, parameterName.indexOf("]")));
				measures.put(index, request.getParameter(parameterName));
			}
			else if (parameterName.startsWith("dosage[")) {
				int index = Integer.parseInt(parameterName.substring(parameterName.indexOf("[") + 1, parameterName.indexOf("]")));
				dosages.put(index, String.join(", ", request.getParameterValues(parameterName)));
			}
			else if (parameterName.startsWith("instruction[")) {
				int index = Integer.parseInt(parameterName.substring(parameterName.indexOf("[") + 1, parameterName.indexOf("]")));
				instructions.put(index, request.getParameter(parameterName));
			}
			else if (parameterName.startsWith("days[")) {
				int index = Integer.parseInt(parameterName.substring(parameterName.indexOf("[") + 1, parameterName.indexOf("]")));
				days.put(index, Integer.parseInt(request.getParameter(parameterName)));
			}
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/care_connect", "root", "");
			Statement st1 = con.createStatement();

			String query1 = "INSERT INTO prescriptions (patient_id, doctor_id, date, weight, diagnosis, tests, payment_type, fees, remarks, follow_up) VALUES "
					+ "('" + patient_id + "', '" + user_id + "', '" + date + "', '" + weight + "', '" + diagnosis
					+ "', '" + tests + "', '" + payment_type + "', '" + fees + "', '" + remarks + "', '" + follow_up + "')";
			int affectedRows = st1.executeUpdate(query1, Statement.RETURN_GENERATED_KEYS);
			if (affectedRows > 0) {
				try (ResultSet generatedKeys = st1.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						new_record_id = generatedKeys.getInt(1);

						int index = 0;
						for (index = 0; index < medicines.size(); index++) {
							String query2 = "INSERT INTO medicines (prescription_id, medicine_name, med_type, measure, dosage, instruction, days) VALUES " + "('" + new_record_id + "', '"
									+ medicines.get(index) + "', '" + medTypes.get(index) + "', '" + measures.get(index) + "', '" + dosages.get(index) + "', '" + instructions.get(index) + "', '" + days.get(index) + "')";
							int affectedRows2 = st1.executeUpdate(query2, Statement.RETURN_GENERATED_KEYS);
						}
					}
				}
				request.setAttribute("successMessage", "Prescription added successfully");
				RequestDispatcher disp1 = request.getRequestDispatcher("/DoctorServlet?action=view_prescription&id=" + new_record_id);
				disp1.forward(request, response);
			}
			else {
				System.out.println("Insertion failed, no rows affected.");
				List<String> errors = new ArrayList<>();
				errors.add("Unable to add new record");
				request.setAttribute("errors", errors);
				RequestDispatcher disp2 = request.getRequestDispatcher("/DoctorServlet?action=add_new_prescription");
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
			RequestDispatcher disp3 = request.getRequestDispatcher("/AdminServlet?action=add_new_doctor");
			disp3.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}