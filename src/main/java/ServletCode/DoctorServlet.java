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
import ServletCode.DataClasses.LoggedInUserData;
import ServletCode.DataClasses.MedicinesData;
import ServletCode.DataClasses.PatientListData;
import ServletCode.DataClasses.PrescriptionData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DoctorServlet() {
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
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/care_connect", "root", "");

					if ("dashboard".equals(action)) {
						int totalPatients = 0, totalAppointmentRequests = 0, totalPastAppointments = 0, totalUpcomingAppointments = 0, totalPrescriptions = 0;

						String query1 = "SELECT COUNT(DISTINCT user.id) AS total_records FROM user" + " JOIN patients ON user.id = patients.user_id"
										+ " JOIN appointments ON patients.user_id = appointments.patient_id" + " WHERE user.account_type = 3 AND appointments.doctor_id = " + user_id;

						PreparedStatement st1 = con.prepareStatement(query1);
						ResultSet rs1 = st1.executeQuery();
						if (rs1.next()) {
							totalPatients = rs1.getInt("total_records");
						}

						String query2 = "SELECT COUNT(*) AS total_records FROM appointments" + " WHERE appointments.doctor_id = " + user_id
										+ " AND appointments.status_flag = 'Not Accepted Yet'" + " AND appointments.date_time_appointment > CURDATE();";
						PreparedStatement st2 = con.prepareStatement(query2);
						ResultSet rs2 = st2.executeQuery();
						if (rs2.next()) {
							totalAppointmentRequests = rs2.getInt("total_records");
						}

						String query3 = "SELECT COUNT(*) AS total_records FROM appointments WHERE appointments.doctor_id = " + user_id
										+ " AND appointments.status_flag = 'Accepted'" + " AND appointments.date_time_appointment > CURDATE();";
						PreparedStatement st3 = con.prepareStatement(query3);
						ResultSet rs3 = st3.executeQuery();
						if (rs3.next()) {
							totalUpcomingAppointments = rs3.getInt("total_records");
						}

						String query4 = "SELECT COUNT(*) AS total_records FROM appointments WHERE appointments.doctor_id = " + user_id
										+ " AND appointments.date_time_appointment <= CURDATE() AND appointments.date_time_appointment <= CURDATE() + INTERVAL 1 DAY - INTERVAL 1 SECOND;";
						PreparedStatement st4 = con.prepareStatement(query4);
						ResultSet rs4 = st4.executeQuery();
						if (rs4.next()) {
							totalPastAppointments = rs4.getInt("total_records");
						}

						String query5 = "SELECT COUNT(*) AS total_records FROM prescriptions WHERE prescriptions.doctor_id = " + user_id;
						PreparedStatement st5 = con.prepareStatement(query5);
						ResultSet rs5 = st5.executeQuery();
						if (rs5.next()) {
							totalPrescriptions = rs5.getInt("total_records");
						}

						request.setAttribute("totalPatients", totalPatients);
						request.setAttribute("totalAppointmentRequests", totalAppointmentRequests);
						request.setAttribute("totalUpcomingAppointments", totalUpcomingAppointments);
						request.setAttribute("totalPastAppointments", totalPastAppointments);
						request.setAttribute("totalPrescriptions", totalPrescriptions);

						RequestDispatcher disp1 = request.getRequestDispatcher("Doctor/DoctorDashboard.jsp");
						disp1.forward(request, response);
					}
					else if ("appointment_requests".equals(action)) {
						List<AppointmentData> dataList = new ArrayList<>();
						String query6 = "SELECT appointments.*, user.name FROM appointments" + " JOIN user ON user.id = appointments.patient_id " + " WHERE appointments.doctor_id = "
										+ user_id + " AND appointments.status_flag = 'Not Accepted Yet'" + " AND appointments.date_time_appointment > CURDATE();";
						PreparedStatement st6 = con.prepareStatement(query6);
						ResultSet rs6 = st6.executeQuery();

						while (rs6.next()) {
							AppointmentData data = new AppointmentData();
							data.setId(rs6.getInt("id"));
							data.setPatient_name(rs6.getString("name"));
							data.setDate_time_appointment(rs6.getString("date_time_appointment"));
							data.setStatus_flag(rs6.getString("status_flag"));
							data.setRequested_flag(rs6.getInt("requested_flag"));
							dataList.add(data);
						}
						rs6.close();
						st6.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Doctor/AppointmentRequests.jsp").forward(request, response);
					}
					else if ("upcoming_appointments".equals(action)) {
						List<AppointmentData> dataList = new ArrayList<>();
						String query7 = "SELECT appointments.*, user.name FROM appointments" + " JOIN user ON user.id = appointments.patient_id" + " WHERE appointments.doctor_id = "
										+ user_id + " AND appointments.status_flag = 'Accepted'" + " AND appointments.date_time_appointment > CURDATE();";
						PreparedStatement st7 = con.prepareStatement(query7);
						ResultSet rs7 = st7.executeQuery();

						while (rs7.next()) {
							AppointmentData data = new AppointmentData();
							data.setId(rs7.getInt("id"));
							data.setPatient_name(rs7.getString("name"));
							data.setDate_time_appointment(rs7.getString("date_time_appointment"));
							data.setStatus_flag(rs7.getString("status_flag"));
							data.setRequested_flag(rs7.getInt("requested_flag"));
							dataList.add(data);
						}

						rs7.close();
						st7.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Doctor/UpcomingAppointments.jsp").forward(request, response);
					}
					else if ("past_appointments".equals(action)) {
						List<AppointmentData> dataList = new ArrayList<>();
						String query8 = "SELECT appointments.*, user.name FROM appointments" + " JOIN user ON user.id = appointments.patient_id" + " WHERE appointments.doctor_id = "
										+ user_id + " AND appointments.date_time_appointment <= CURDATE() AND appointments.date_time_appointment <= CURDATE() + INTERVAL 1 DAY - INTERVAL 1 SECOND;";
						PreparedStatement st8 = con.prepareStatement(query8);
						ResultSet rs8 = st8.executeQuery();

						while (rs8.next()) {
							AppointmentData data = new AppointmentData();
							data.setId(rs8.getInt("id"));
							data.setPatient_name(rs8.getString("name"));
							data.setDate_time_appointment(rs8.getString("date_time_appointment"));
							data.setStatus_flag(rs8.getString("status_flag"));
							data.setRequested_flag(rs8.getInt("requested_flag"));
							dataList.add(data);
						}

						rs8.close();
						st8.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Doctor/PastAppointments.jsp").forward(request, response);
					}
					else if ("patients_list".equals(action)) {
						List<PatientListData> dataList = new ArrayList<>();

						String query9 = "SELECT user.id, user.name, user.email_id, user.phone_number, patients.* FROM user" + " JOIN patients ON user.id = patients.user_id"
										+ " JOIN appointments ON patients.user_id = appointments.patient_id" + " WHERE user.account_type = 3 AND appointments.doctor_id = " + user_id + " GROUP BY user.id";

						PreparedStatement st9 = con.prepareStatement(query9);
						ResultSet rs9 = st9.executeQuery();

						while (rs9.next()) {
							PatientListData data = new PatientListData();
							data.setId(rs9.getInt("id"));
							data.setName(rs9.getString("name"));
							data.setEmailID(rs9.getString("email_id"));
							data.setPhone_number(rs9.getString("phone_number"));
							data.setGender(rs9.getString("gender"));
							data.setDate_of_birth(rs9.getString("date_of_birth"));
							data.setAddress(rs9.getString("address"));
							data.setBlood_group(rs9.getString("blood_group"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Doctor/PatientsList.jsp").forward(request, response);
					}
					else if ("update_profile".equals(action)) {
						String query10 = "SELECT user.id as main_record_id, user.name, user.email_id, user.phone_number, doctors.* FROM user"
										+ " JOIN doctors ON user.id = doctors.user_id WHERE user.id = " + user_id;
						Statement st1 = con.createStatement();
						ResultSet rs10 = st1.executeQuery(query10);

						LoggedInUserData data = new LoggedInUserData();
						while (rs10.next()) {
							data.setId(rs10.getInt("main_record_id")); // here main_record_id is from user table
							data.setInfo_record_id(rs10.getInt("id")); // here id is from doctors table
							data.setName(rs10.getString("name"));
							data.setEmailID(rs10.getString("email_id"));
							data.setPhone_number(rs10.getString("phone_number"));
							data.setGender(rs10.getString("gender"));
							data.setDate_of_birth(rs10.getString("date_of_birth"));
							data.setAddress(rs10.getString("address"));
							data.setYear_of_experience(rs10.getInt("year_of_experience"));
						}

						List<String> genders = new ArrayList<>();
						genders.add("Male");
						genders.add("Female");

						request.setAttribute("user_data", data);
						request.setAttribute("genders", genders);

						request.getRequestDispatcher("Doctor/UpdateProfile.jsp").forward(request, response);
					}
					else if ("change_password".equals(action)) {
						RequestDispatcher disp2 = request.getRequestDispatcher("Doctor/ChangePassword.jsp");
						disp2.forward(request, response);
					}
					else if ("save_new_password".equals(action)) {
						String old_password = request.getParameter("old_password").trim();
						String new_password = request.getParameter("new_password").trim();
						String cnew_password = request.getParameter("cnew_password").trim();

						List<String> errors = new ArrayList<>();
						if (!old_password.equals(user_password)) {
							errors.add("Old password is wrong");
						}

						if (!errors.isEmpty()) {
							request.setAttribute("errors", errors);
							RequestDispatcher disp3 = request.getRequestDispatcher("/DoctorServlet?action=change_password");
							disp3.forward(request, response);
							return;
						}

						String query11 = "UPDATE user SET password = ? WHERE id = " + user_id;
						PreparedStatement st10 = con.prepareStatement(query11);
						st10.setString(1, request.getParameter("new_password"));
						int rowsAffected = st10.executeUpdate();

						if (rowsAffected > 0) {
							session.setAttribute("user_password", new_password);
							System.out.println("new updated password " + (String) session.getAttribute("user_password"));
							System.out.println("Update successful! " + rowsAffected + " rows affected.");
							request.setAttribute("successMessage", "Password changed successfully!");
							RequestDispatcher disp4 = request.getRequestDispatcher("/DoctorServlet?action=dashboard");
							disp4.forward(request, response);
						}
						else {
							errors.add("Unable to change password");
							request.setAttribute("errors", errors);
							RequestDispatcher disp5 = request.getRequestDispatcher("/DoctorServlet?action=change_password");
							disp5.forward(request, response);
						}
					}
					else if ("save_profile".equals(action)) {
						List<String> errors = new ArrayList<>();

						String query12 = "UPDATE user SET name = ?, email_id = ?, phone_number = ? WHERE id = " + user_id;
						PreparedStatement st11 = con.prepareStatement(query12);
						st11.setString(1, request.getParameter("user_name"));
						st11.setString(2, request.getParameter("user_email"));
						st11.setString(3, request.getParameter("user_phone"));
						int rowsAffected = st11.executeUpdate();

						if (rowsAffected > 0) {
							int info_record_id = Integer.parseInt(request.getParameter("info_record_id"));

							String query13 = "UPDATE doctors SET address = ?, date_of_birth = ?, gender = ?, year_of_experience = ? WHERE id = " + info_record_id;
							PreparedStatement st12 = con.prepareStatement(query13);
							st12.setString(1, request.getParameter("user_address"));
							st12.setString(2, request.getParameter("user_dob"));
							st12.setString(3, request.getParameter("user_gender"));
							st12.setString(4, request.getParameter("user_year_of_experience"));
							int rowsAffected1 = st12.executeUpdate();

							if (rowsAffected1 > 0) {
								System.out.println("Both records updated");
								request.setAttribute("successMessage", "Profile updated successfully!");
								RequestDispatcher disp6 = request.getRequestDispatcher("/DoctorServlet?action=dashboard");
								disp6.forward(request, response);
							}
							else {
								System.out.println("user records updated but unable to update patient record");
								errors.add("Unable to update profile");
								request.setAttribute("errors", errors);
								RequestDispatcher disp7 = request.getRequestDispatcher("/DoctorServlet?action=update_profile");
								disp7.forward(request, response);
							}
						}
						else {
							System.out.println("user record not updated");
							errors.add("Unable to update profile");
							request.setAttribute("errors", errors);
							RequestDispatcher disp8 = request.getRequestDispatcher("/DoctorServlet?action=update_profile");
							disp8.forward(request, response);
						}
					}
					else if ("appointment_acceptance".equals(action)) {
						String data_id = request.getParameter("id");
						String flag = request.getParameter("flag");

						String query14 = "UPDATE appointments SET status_flag = ? WHERE id = " + data_id;
						PreparedStatement st13 = con.prepareStatement(query14);
						st13.setString(1, flag);
						int rowsAffected = st13.executeUpdate();

						if (rowsAffected > 0) {
							request.setAttribute("successMessage", "Appointment " + flag);
							RequestDispatcher disp9 = request .getRequestDispatcher("/DoctorServlet?action=appointment_requests");
							disp9.forward(request, response);
						}
						else {
							List<String> errors = new ArrayList<>();
							errors.add("Unable to change appointment status");
							request.setAttribute("errors", errors);
							RequestDispatcher disp10 = request.getRequestDispatcher("/DoctorServlet?action=appointment_requests");
							disp10.forward(request, response);
						}
					}
					else if ("add_new_prescription".equals(action)) {
						List<PatientListData> dataList = new ArrayList<>();

						Statement stmt = con.createStatement();
						ResultSet rs11 = stmt.executeQuery("SELECT id, name FROM user WHERE account_type = 3");

						while (rs11.next()) {
							PatientListData data = new PatientListData();
							data.setId(rs11.getInt("id"));
							data.setName(rs11.getString("name"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Doctor/AddNewPrescriptions.jsp").forward(request, response);
					}
					else if ("prescriptions_list".equals(action)) {
						List<PrescriptionData> dataList = new ArrayList<>();
						String query14 = "SELECT prescriptions.id, prescriptions.patient_id, prescriptions.date, prescriptions.diagnosis, prescriptions.fees, prescriptions.follow_up, user.name FROM prescriptions"
										+ " JOIN user ON user.id = prescriptions.patient_id" + " WHERE prescriptions.doctor_id = " + user_id;
						PreparedStatement st14 = con.prepareStatement(query14);
						ResultSet rs12 = st14.executeQuery();

						while (rs12.next()) {
							PrescriptionData data = new PrescriptionData();
							data.setId(rs12.getInt("id"));
							data.setPatient_name(rs12.getString("name"));
							data.setDate(rs12.getString("date"));
							data.setDiagnosis(rs12.getString("diagnosis"));
							data.setFees(rs12.getInt("fees"));
							data.setFollow_up(rs12.getString("follow_up"));
							dataList.add(data);
						}

						rs12.close();
						st14.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Doctor/PrescriptionsList.jsp").forward(request, response);
					}
					else if ("view_prescription".equals(action)) {
						String data_id = request.getParameter("id");

						String query15 = "SELECT prescriptions.*, user.name FROM prescriptions" + " JOIN user ON user.id = prescriptions.patient_id" + " WHERE prescriptions.id = " + data_id;
						PreparedStatement st15 = con.prepareStatement(query15);
						ResultSet rs13 = st15.executeQuery();

						if (rs13.next()) {
							PrescriptionData data = new PrescriptionData();
							data.setId(rs13.getInt("id"));
							data.setPatient_name(rs13.getString("name"));
							data.setDate(rs13.getString("date"));
							data.setWeight(rs13.getInt("weight"));
							data.setDiagnosis(rs13.getString("diagnosis"));
							data.setTests(rs13.getString("tests"));
							data.setPayment_type(rs13.getString("payment_type"));
							data.setFees(rs13.getInt("fees"));
							data.setRemarks(rs13.getString("remarks"));
							data.setFollow_up(rs13.getString("follow_up"));

							List<MedicinesData> dataList = new ArrayList<>();
							String query16 = "SELECT * FROM medicines WHERE prescription_id = " + data_id;
							PreparedStatement st16 = con.prepareStatement(query16);
							ResultSet rs14 = st16.executeQuery();

							while (rs14.next()) {
								MedicinesData medData = new MedicinesData();
								medData.setId(rs14.getInt("id"));
								medData.setMedicine_name(rs14.getString("medicine_name"));
								medData.setMed_type(rs14.getString("med_type"));
								medData.setMeasure(rs14.getString("measure"));
								medData.setDosage(rs14.getString("dosage"));
								medData.setInstruction(rs14.getString("instruction"));
								medData.setDays(rs14.getInt("days"));
								dataList.add(medData);
							}

							rs13.close();
							rs14.close();
							st15.close();
							st16.close();

							request.setAttribute("prescriptionData", data);
							request.setAttribute("dataList", dataList);
							request.getRequestDispatcher("Doctor/PrescriptionPage.jsp").forward(request, response);
						}
						else {
							List<String> errors = new ArrayList<>();
							errors.add("Unable to fetch record");
							request.setAttribute("errors", errors);
							RequestDispatcher disp11 = request.getRequestDispatcher("/DoctorServlet?action=prescriptions_list");
							disp11.forward(request, response);
						}
					}
					else if ("print_prescription".equals(action)) {
						String data_id = request.getParameter("id");

						String query17 = "SELECT prescriptions.*, user.name FROM prescriptions" + " JOIN user ON user.id = prescriptions.patient_id" + " WHERE prescriptions.id = " + data_id;
						PreparedStatement st17 = con.prepareStatement(query17);
						ResultSet rs15 = st17.executeQuery();

						if (rs15.next()) {
							PrescriptionData data = new PrescriptionData();
							data.setId(rs15.getInt("id"));
							data.setPatient_name(rs15.getString("name"));
							data.setDate(rs15.getString("date"));
							data.setWeight(rs15.getInt("weight"));
							data.setDiagnosis(rs15.getString("diagnosis"));
							data.setTests(rs15.getString("tests"));
							data.setPayment_type(rs15.getString("payment_type"));
							data.setFees(rs15.getInt("fees"));
							data.setRemarks(rs15.getString("remarks"));
							data.setFollow_up(rs15.getString("follow_up"));

							List<MedicinesData> dataList = new ArrayList<>();
							String query18 = "SELECT * FROM medicines WHERE prescription_id = " + data_id;
							PreparedStatement st18 = con.prepareStatement(query18);
							ResultSet rs16 = st18.executeQuery();

							while (rs16.next()) {
								MedicinesData medData = new MedicinesData();
								medData.setId(rs16.getInt("id"));
								medData.setMedicine_name(rs16.getString("medicine_name"));
								medData.setMed_type(rs16.getString("med_type"));
								medData.setMeasure(rs16.getString("measure"));
								medData.setDosage(rs16.getString("dosage"));
								medData.setInstruction(rs16.getString("instruction"));
								medData.setDays(rs16.getInt("days"));
								dataList.add(medData);
							}

							rs15.close();
							rs16.close();
							st17.close();
							st18.close();

							request.setAttribute("prescriptionData", data);
							request.setAttribute("dataList", dataList);
							request.getRequestDispatcher("Doctor/PrescriptionPrint.jsp").forward(request, response);
						}
						else {
							List<String> errors = new ArrayList<>();
							errors.add("Unable to fetch record");
							request.setAttribute("errors", errors);
							RequestDispatcher disp12 = request.getRequestDispatcher("/DoctorServlet?action=prescriptions_list");
							disp12.forward(request, response);
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
					RequestDispatcher disp13 = request.getRequestDispatcher("/DoctorServlet?action=dashboard");
					disp13.forward(request, response);
				}
			}
			else {
				request.getSession().invalidate();
				List<String> errors = new ArrayList<>();
				errors.add("You have logged out!!!");
				request.setAttribute("errors", errors);
				RequestDispatcher disp14 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
				disp14.forward(request, response);
			}
		}
		else {
			request.getSession().invalidate();
			List<String> errors = new ArrayList<>();
			errors.add("You have logged out!!!");
			request.setAttribute("errors", errors);
			RequestDispatcher disp15 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
			disp15.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}