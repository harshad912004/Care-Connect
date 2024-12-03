package ServletCode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ServletCode.DataClasses.AppointmentData;
import ServletCode.DataClasses.DepartmentData;
import ServletCode.DataClasses.DoctorListData;
import ServletCode.DataClasses.LoggedInUserData;
import ServletCode.DataClasses.PatientListData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if (session != null) {
			String user_name = (String) session.getAttribute("user_name");
			int user_id = (int) session.getAttribute("user_id");
			String user_password = ((String) session.getAttribute("user_password")).trim();

			if (user_name != null && user_id != 0 && user_password != null) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/care_connect", "root", "");

					if ("dashboard".equals(action)) {
						int totalDoctors = 0, totalPatients = 0, totalAppointments = 0, totalDepartments = 0;

						String query1 = "SELECT COUNT(*) AS total_records FROM user where account_type = 2";
						PreparedStatement s1 = c.prepareStatement(query1);
						ResultSet rs1 = s1.executeQuery();
						if (rs1.next()) {
							totalDoctors = rs1.getInt("total_records");
						}

						String query2 = "SELECT COUNT(*) AS total_records FROM user where account_type = 3";
						PreparedStatement s2 = c.prepareStatement(query2);
						ResultSet rs2 = s2.executeQuery();
						if (rs2.next()) {
							totalPatients = rs2.getInt("total_records");
						}

						String query3 = "SELECT COUNT(*) AS total_records FROM appointments";
						PreparedStatement s3 = c.prepareStatement(query3);
						ResultSet rs3 = s3.executeQuery();
						if (rs3.next()) {
							totalAppointments = rs3.getInt("total_records");
						}

						String query4 = "SELECT COUNT(*) AS total_records FROM departments";
						PreparedStatement s4 = c.prepareStatement(query4);
						ResultSet rs4 = s4.executeQuery();
						if (rs4.next()) {
							totalDepartments = rs4.getInt("total_records");
						}

						request.setAttribute("totalDoctors", totalDoctors);
						request.setAttribute("totalPatients", totalPatients);
						request.setAttribute("totalAppointments", totalAppointments);
						request.setAttribute("totalDepartments", totalDepartments);

						RequestDispatcher disp = request.getRequestDispatcher("Admin/AdminDashboard.jsp");
						disp.forward(request, response);
					}
					else if ("appointments".equals(action))
					{
						List<AppointmentData> dataList = new ArrayList<>();

						String query5 = "SELECT appointments.*, u1.name AS patient_name, u2.name AS doctor_name, departments.dept_name" + " FROM appointments "
										+ "JOIN departments ON departments.id = appointments.dept_id" + " INNER JOIN user u1 ON appointments.patient_id = u1.id INNER JOIN user u2 ON appointments.doctor_id = u2.id";
						PreparedStatement s5 = c.prepareStatement(query5);
						ResultSet rs5 = s5.executeQuery();

						while (rs5.next()) {
							AppointmentData data = new AppointmentData();
							data.setId(rs5.getInt("id"));
							data.setDoctor_name(rs5.getString("doctor_name"));
							data.setPatient_name(rs5.getString("patient_name"));
							data.setDepartment(rs5.getString("dept_name"));
							data.setDate_time_appointment(rs5.getString("date_time_appointment"));
							data.setStatus_flag(rs5.getString("status_flag"));
							data.setRequested_flag(rs5.getInt("requested_flag"));
							dataList.add(data);
						}
						rs5.close();
						s5.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/Appointments.jsp").forward(request, response);
					}
					else if ("doctors_list".equals(action)) {
						List<DoctorListData> dataList = new ArrayList<>();

						String query6 = "SELECT user.id, user.name, user.email_id, user.phone_number, doctors.*, departments.dept_name FROM user" + " JOIN doctors ON user.id = doctors.user_id"
										+ " JOIN departments ON doctors.specialization = departments.id WHERE user.account_type = 2";
						PreparedStatement s6 = c.prepareStatement(query6);
						ResultSet rs6 = s6.executeQuery();

						while (rs6.next()) {

							DoctorListData data = new DoctorListData();
							data.setId(rs6.getInt("id"));
							data.setName(rs6.getString("name"));
							data.setEmailID(rs6.getString("email_id"));
							data.setPhone_number(rs6.getString("phone_number"));
							data.setGender(rs6.getString("gender"));
							data.setDate_of_birth(rs6.getString("date_of_birth"));
							data.setAddress(rs6.getString("address"));
							data.setDept_name(rs6.getString("dept_name"));
							data.setDegree(rs6.getString("degree"));
							data.setYear_of_experience(rs6.getInt("year_of_experience"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);

						RequestDispatcher disp1 = request.getRequestDispatcher("Admin/DoctorsList.jsp");
						disp1.forward(request, response);
					}
					else if ("patients_list".equals(action)) {
						List<PatientListData> dataList = new ArrayList<>();

						String query7 = "SELECT user.id, user.name, user.email_id, user.phone_number, patients.* FROM user" + " JOIN patients ON user.id = patients.user_id WHERE user.account_type = 3";
						PreparedStatement s7 = c.prepareStatement(query7);
						ResultSet rs7 = s7.executeQuery();

						while (rs7.next()) {

							PatientListData data = new PatientListData();
							data.setId(rs7.getInt("id"));
							data.setName(rs7.getString("name"));
							data.setEmailID(rs7.getString("email_id"));
							data.setPhone_number(rs7.getString("phone_number"));
							data.setGender(rs7.getString("gender"));
							data.setDate_of_birth(rs7.getString("date_of_birth"));
							data.setAddress(rs7.getString("address"));
							data.setBlood_group(rs7.getString("blood_group"));

							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/PatientsList.jsp").forward(request, response);
					}
					else if ("departments".equals(action)) {
						List<DepartmentData> dataList = new ArrayList<>();

						String query8 = "SELECT dp.id AS dept_id, dp.dept_name, COUNT(d.specialization) AS total_doctors"
										+ " FROM departments dp " + "LEFT JOIN doctors d ON dp.id = d.specialization" + " GROUP BY dp.id, dp.dept_name";
						PreparedStatement s8 = c.prepareStatement(query8);
						ResultSet rs8 = s8.executeQuery();

						while (rs8.next()) {
							DepartmentData data = new DepartmentData();
							data.setId(rs8.getInt("dept_id"));
							data.setName(rs8.getString("dept_name"));
							data.setTotal_doctors(rs8.getInt("total_doctors"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/DepartmentsList.jsp").forward(request, response);
					}
					else if ("add_new_doctor".equals(action)) {
						List<DepartmentData> dataList = new ArrayList<>();

						Statement st1 = c.createStatement();
						ResultSet rs9 = st1.executeQuery("SELECT id, dept_name FROM departments");

						while (rs9.next()) {
							DepartmentData data = new DepartmentData();
							data.setId(rs9.getInt("id"));
							data.setName(rs9.getString("dept_name"));
							data.setTotal_doctors(0);
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/AddNewDoctor.jsp").forward(request, response);
					}
					else if ("update_profile".equals(action)) {
						Statement st2 = c.createStatement();
						ResultSet rs10 = st2.executeQuery("SELECT * FROM user where id=" + user_id);

						LoggedInUserData data = new LoggedInUserData();
						while (rs10.next()) {
							data.setId(rs10.getInt("id"));
							data.setName(rs10.getString("name"));
							data.setEmailID(rs10.getString("email_id"));
							data.setPhone_number(rs10.getString("phone_number"));
						}

						request.setAttribute("user_data", data);
						request.getRequestDispatcher("Admin/UpdateProfile.jsp").forward(request, response);
					}
					else if ("change_password".equals(action)) {
						RequestDispatcher disp2 = request.getRequestDispatcher("Admin/ChangePassword.jsp");
						disp2.forward(request, response);
					}
					else if ("save_new_password".equals(action)) {
						System.out.println("password " + user_password);
						String old_password = request.getParameter("old_password").trim();
						System.out.println("old_password " + old_password);
						String new_password = request.getParameter("new_password").trim();
						String cnew_password = request.getParameter("cnew_password").trim();

						List<String> errors = new ArrayList<>();
						if (!old_password.equals(user_password)) {
							errors.add("Old password is wrong");
						}
						if (!errors.isEmpty()) {
							request.setAttribute("errors", errors);
							RequestDispatcher disp3 = request.getRequestDispatcher("/AdminServlet?action=change_password");
							disp3.forward(request, response);
							return;
						}
						String query9 = "UPDATE user SET password = ? WHERE id = " + user_id;
						PreparedStatement s9 = c.prepareStatement(query9);
						s9.setString(1, request.getParameter("new_password"));
						int rowsAffected = s9.executeUpdate();

						if (rowsAffected > 0) {
							session.setAttribute("user_password", new_password);
							System.out.println("new updated password " + (String) session.getAttribute("user_password"));
							System.out.println("Update successful! " + rowsAffected + " rows affected.");
							request.setAttribute("successMessage", "Password changed successfully!");
							RequestDispatcher disp4 = request.getRequestDispatcher("/AdminServlet?action=dashboard");
							disp4.forward(request, response);
						}
						else {
							errors.add("Unable to change password");
							request.setAttribute("errors", errors);
							RequestDispatcher disp5 = request.getRequestDispatcher("/AdminServlet?action=change_password");
							disp5.forward(request, response);
						}
					}
					else if ("save_profile".equals(action)) {
						List<String> errors = new ArrayList<>();

						String query10 = "UPDATE user SET name = ?, email_id = ?, phone_number = ? WHERE id = " + user_id;
						PreparedStatement s10 = c.prepareStatement(query10);
						s10.setString(1, request.getParameter("user_name"));
						s10.setString(2, request.getParameter("user_email"));
						s10.setString(3, request.getParameter("user_phone"));
						int rowsAffected = s10.executeUpdate();

						if (rowsAffected > 0) {
							request.setAttribute("successMessage", "Profile updated successfully!");
							RequestDispatcher disp6 = request.getRequestDispatcher("/AdminServlet?action=dashboard");
							disp6.forward(request, response);
						}
						else {
							errors.add("Unable to update profile");
							request.setAttribute("errors", errors);
							RequestDispatcher disp7 = request.getRequestDispatcher("/AdminServlet?action=update_profile");
							disp7.forward(request, response);
						}
					}
				}
				catch (ClassNotFoundException | SQLException e) {
					// e.printStackTrace();
					System.out.println("Error: " + e.getMessage());

					request.getSession().invalidate();
					List<String> errors = new ArrayList<>();
					errors.add(e.getMessage());
					request.setAttribute("errors", errors);
					RequestDispatcher disp8 = request.getRequestDispatcher("/AdminServlet?action=dashboard");
					disp8.forward(request, response);
				}
			}
			else {
				request.getSession().invalidate();
				List<String> errors = new ArrayList<>();
				errors.add("You have logged out!!!");
				request.setAttribute("errors", errors);
				RequestDispatcher disp9 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
				disp9.forward(request, response);
			}
		}
		else {
			request.getSession().invalidate();
			List<String> errors = new ArrayList<>();
			errors.add("You have logged out!!!");
			request.setAttribute("errors", errors);
			RequestDispatcher disp10 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
			disp10.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}