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
import ServletCode.DataClasses.MedicinesData;
import ServletCode.DataClasses.PrescriptionData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PatientServlet() {
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
						int totalPastAppointments = 0, totalRequestedAppointments = 0;

						String query1 = "SELECT COUNT(*) AS total_records FROM appointments WHERE appointments.patient_id = " + user_id + " AND appointments.date_time_appointment > CURDATE();";
						PreparedStatement ps1 = con.prepareStatement(query1);
						ResultSet rs1 = ps1.executeQuery();
						if (rs1.next()) {
							totalRequestedAppointments = rs1.getInt("total_records");
						}

						String query2 = "SELECT COUNT(*) AS total_records FROM appointments WHERE appointments.patient_id = " + user_id + " AND appointments.date_time_appointment <= CURDATE() AND appointments.date_time_appointment <= CURDATE() + INTERVAL 1 DAY - INTERVAL 1 SECOND;";
						PreparedStatement ps2 = con.prepareStatement(query2);
						ResultSet rs2 = ps2.executeQuery();
						if (rs2.next()) {
							totalPastAppointments = rs2.getInt("total_records");
						}

						request.setAttribute("totalRequestedAppointments", totalRequestedAppointments);
						request.setAttribute("totalPastAppointments", totalPastAppointments);

						RequestDispatcher disp1 = request.getRequestDispatcher("Patient/PatientDashboard.jsp");
						disp1.forward(request, response);
					}
					else if ("past_appointments".equals(action)) {
						List<AppointmentData> dataList = new ArrayList<>();
						String query3 = "SELECT appointments.*, user.name, departments.dept_name FROM appointments JOIN user ON user.id = appointments.doctor_id"
										+ " JOIN departments ON departments.id = appointments.dept_id WHERE appointments.patient_id = " + user_id
										+ " AND appointments.date_time_appointment <= CURDATE() AND appointments.date_time_appointment <= CURDATE() + INTERVAL 1 DAY - INTERVAL 1 SECOND;";
						PreparedStatement ps3 = con.prepareStatement(query3);
						ResultSet rs3 = ps3.executeQuery();

						while (rs3.next()) {
							AppointmentData data = new AppointmentData();
							data.setId(rs3.getInt("id"));
							data.setDoctor_name(rs3.getString("name"));
							data.setPatient_name(user_name);
							data.setDepartment(rs3.getString("dept_name"));
							data.setDate_time_appointment(rs3.getString("date_time_appointment"));
							data.setStatus_flag(rs3.getString("status_flag"));
							data.setRequested_flag(rs3.getInt("requested_flag"));
							dataList.add(data);
						}
						rs3.close();
						ps3.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Patient/PastAppointments.jsp").forward(request, response);
					}
					else if ("requested_appointments".equals(action)) {
						List<AppointmentData> dataList = new ArrayList<>();
						String query4 = "SELECT appointments.*, user.name, departments.dept_name FROM appointments JOIN user ON user.id = appointments.doctor_id"
										+ " JOIN departments ON departments.id = appointments.dept_id WHERE appointments.patient_id = " + user_id + " AND appointments.date_time_appointment > CURDATE();";
						PreparedStatement ps4 = con.prepareStatement(query4);
						ResultSet rs4 = ps4.executeQuery();

						while (rs4.next()) {
							AppointmentData data = new AppointmentData();
							data.setId(rs4.getInt("id"));
							data.setDoctor_name(rs4.getString("name"));
							data.setPatient_name(user_name);
							data.setDepartment(rs4.getString("dept_name"));
							data.setDate_time_appointment(rs4.getString("date_time_appointment"));
							data.setStatus_flag(rs4.getString("status_flag"));
							data.setRequested_flag(rs4.getInt("requested_flag"));
							dataList.add(data);
						}

						rs4.close();
						ps4.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Patient/NewlyRequestedAppointments.jsp").forward(request, response);
					}
					else if ("book_appointment".equals(action)) {
						List<DepartmentData> dataList = new ArrayList<>();

						Statement st1 = con.createStatement();
						ResultSet rs5 = st1.executeQuery("SELECT id, dept_name FROM departments");

						while (rs5.next()) {
							DepartmentData data = new DepartmentData();
							data.setId(rs5.getInt("id"));
							data.setName(rs5.getString("dept_name"));
							data.setTotal_doctors(0);
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Patient/BookAppointment.jsp").forward(request, response);
					}
					else if ("update_profile".equals(action)) {
						String query5 = "SELECT user.id as main_record_id, user.name, user.email_id, user.phone_number, patients.* FROM user"
										+ " JOIN patients ON user.id = patients.user_id WHERE user.id = " + user_id;
						Statement st2 = con.createStatement();
						ResultSet rs6 = st2.executeQuery(query5);

						LoggedInUserData data = new LoggedInUserData();
						while (rs6.next()) {
							data.setId(rs6.getInt("main_record_id")); // here main_record_id is from user table
							data.setInfo_record_id(rs6.getInt("id")); // here id is from patients table
							data.setName(rs6.getString("name"));
							data.setEmailID(rs6.getString("email_id"));
							data.setPhone_number(rs6.getString("phone_number"));
							data.setGender(rs6.getString("gender"));
							data.setDate_of_birth(rs6.getString("date_of_birth"));
							data.setAddress(rs6.getString("address"));
							data.setBlood_group(rs6.getString("blood_group"));
						}

						List<String> genders = new ArrayList<>();
						genders.add("Male");
						genders.add("Female");

						List<String> bloodgroups = new ArrayList<>();
						bloodgroups.add("A+");
						bloodgroups.add("A-");
						bloodgroups.add("B+");
						bloodgroups.add("B-");
						bloodgroups.add("AB+");
						bloodgroups.add("AB-");
						bloodgroups.add("O+");
						bloodgroups.add("O-");

						request.setAttribute("user_data", data);
						request.setAttribute("genders", genders);
						request.setAttribute("bloodgroups", bloodgroups);
						
						request.getRequestDispatcher("Patient/UpdateProfile.jsp").forward(request, response);
					}
					else if ("change_password".equals(action)) {
						RequestDispatcher disp2 = request.getRequestDispatcher("Patient/ChangePassword.jsp");
						disp2.forward(request, response);
					}
					else if ("save_new_password".equals(action)) {
//						System.out.println("password " + user_password);
						String old_password = request.getParameter("old_password").trim();
//						System.out.println("old_password " + old_password);
						String new_password = request.getParameter("new_password").trim();
						String cnew_password = request.getParameter("cnew_password").trim();

						List<String> errors = new ArrayList<>();
						if (!old_password.equals(user_password)) {
							errors.add("Old password is wrong");
						}
						if (!errors.isEmpty()) {
							request.setAttribute("errors", errors);
							RequestDispatcher disp3 = request .getRequestDispatcher("/PatientServlet?action=change_password");
							disp3.forward(request, response);
							return;
						}

						String query6 = "UPDATE user SET password = ? WHERE id = " + user_id;
						PreparedStatement ps5 = con.prepareStatement(query6);
						ps5.setString(1, request.getParameter("new_password"));
						int rowsAffected = ps5.executeUpdate();

						if (rowsAffected > 0) {
							session.setAttribute("user_password", new_password);
							System.out.println("new updated password " + (String) session.getAttribute("user_password"));
							System.out.println("Update successful! " + rowsAffected + " rows affected.");
							request.setAttribute("successMessage", "Password changed successfully!");
							RequestDispatcher disp4 = request.getRequestDispatcher("/PatientServlet?action=dashboard");
							disp4.forward(request, response);
						}
						else {
							errors.add("Unable to change password");
							request.setAttribute("errors", errors);
							RequestDispatcher disp5 = request.getRequestDispatcher("/PatientServlet?action=change_password");
							disp5.forward(request, response);
						}
					}
					else if ("save_profile".equals(action)) {
						List<String> errors = new ArrayList<>();

						String query7 = "UPDATE user SET name = ?, email_id = ?, phone_number = ? WHERE id = " + user_id;
						PreparedStatement ps6 = con.prepareStatement(query7);
						ps6.setString(1, request.getParameter("user_name"));
						ps6.setString(2, request.getParameter("user_email"));
						ps6.setString(3, request.getParameter("user_phone"));
						int rowsAffected = ps6.executeUpdate();

						if (rowsAffected > 0) {
							int info_record_id = Integer.parseInt(request.getParameter("info_record_id"));

							String query8 = "UPDATE patients SET date_of_birth = ?, address = ?, gender = ?, blood_group = ? WHERE id = " + info_record_id;
							PreparedStatement ps7 = con.prepareStatement(query8);
							ps7.setString(1, request.getParameter("user_dob"));
							ps7.setString(2, request.getParameter("user_address"));
							ps7.setString(3, request.getParameter("user_gender"));
							ps7.setString(4, request.getParameter("user_blood_group"));
							int rowsAffected1 = ps7.executeUpdate();

							if (rowsAffected1 > 0) {
								System.out.println("Both records updated");
								request.setAttribute("successMessage", "Profile updated successfully!");
								RequestDispatcher disp6 = request.getRequestDispatcher("/PatientServlet?action=dashboard");
								disp6.forward(request, response);
							}
							else {
								System.out.println("user records updated but unable to update patient record");
								errors.add("Unable to update profile");
								request.setAttribute("errors", errors);
								RequestDispatcher disp7 = request.getRequestDispatcher("/PatientServlet?action=update_profile");
								disp7.forward(request, response);
							}
						}
						else {
							System.out.println("user record not updated");
							errors.add("Unable to update profile");
							request.setAttribute("errors", errors);
							RequestDispatcher disp8 = request.getRequestDispatcher("/PatientServlet?action=update_profile");
							disp8.forward(request, response);
						}
					}
					else if ("cancel_appointment".equals(action)) {
						String data_id = request.getParameter("id");
						String query9 = "DELETE From appointments where id=" + data_id;
						Statement st1 = con.createStatement();
						st1.executeUpdate(query9);

						request.setAttribute("successMessage", "Appointment cancelled");
						RequestDispatcher disp9 = request.getRequestDispatcher("/PatientServlet?action=requested_appointments");
						disp9.forward(request, response);
					}
					else if ("prescriptions_list".equals(action)) {
						List<PrescriptionData> dataList = new ArrayList<>();
						String query10 = "SELECT prescriptions.id, prescriptions.doctor_id, prescriptions.date, prescriptions.diagnosis, prescriptions.fees, prescriptions.follow_up, user.name FROM prescriptions"
										+ " JOIN user ON user.id = prescriptions.doctor_id WHERE prescriptions.patient_id = " + user_id;
						PreparedStatement ps8 = con.prepareStatement(query10);
						ResultSet rs7 = ps8.executeQuery();

						while (rs7.next()) {
							PrescriptionData data = new PrescriptionData();
							data.setId(rs7.getInt("id"));
							data.setDoctor_name(rs7.getString("name"));
							data.setDate(rs7.getString("date"));
							data.setDiagnosis(rs7.getString("diagnosis"));
							data.setFees(rs7.getInt("fees"));
							data.setFollow_up(rs7.getString("follow_up"));
							dataList.add(data);
						}
						rs7.close();
						ps8.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Patient/PrescriptionsList.jsp").forward(request, response);
					}
					else if ("view_prescription".equals(action)) {
						String data_id = request.getParameter("id");

						String query11 = "SELECT prescriptions.*, user.name FROM prescriptions JOIN user ON user.id = prescriptions.patient_id" + " WHERE prescriptions.id = " + data_id;
						PreparedStatement ps9 = con.prepareStatement(query11);
						ResultSet rs8 = ps9.executeQuery();

						if (rs8.next()) {
							PrescriptionData data = new PrescriptionData();
							data.setId(rs8.getInt("id"));
							data.setPatient_name(rs8.getString("name"));
							data.setDate(rs8.getString("date"));
							data.setWeight(rs8.getInt("weight"));
							data.setDiagnosis(rs8.getString("diagnosis"));
							data.setTests(rs8.getString("tests"));
							data.setPayment_type(rs8.getString("payment_type"));
							data.setFees(rs8.getInt("fees"));
							data.setRemarks(rs8.getString("remarks"));
							data.setFollow_up(rs8.getString("follow_up"));

							List<MedicinesData> dataList = new ArrayList<>();
							String query12 = "SELECT * FROM medicines WHERE prescription_id = " + data_id;
							PreparedStatement ps10 = con.prepareStatement(query12);
							ResultSet rs9 = ps10.executeQuery();

							while (rs9.next()) {
								MedicinesData medData = new MedicinesData();
								medData.setId(rs9.getInt("id"));
								medData.setMedicine_name(rs9.getString("medicine_name"));
								medData.setMed_type(rs9.getString("med_type"));
								medData.setMeasure(rs9.getString("measure"));
								medData.setDosage(rs9.getString("dosage"));
								medData.setInstruction(rs9.getString("instruction"));
								medData.setDays(rs9.getInt("days"));
								dataList.add(medData);
							}

							rs8.close();
							rs9.close();
							ps10.close();
							ps10.close();

							request.setAttribute("prescriptionData", data);
							request.setAttribute("dataList", dataList);
							request.getRequestDispatcher("Patient/PrescriptionPage.jsp").forward(request, response);
						}
						else {
							List<String> errors = new ArrayList<>();
							errors.add("Unable to fetch record");
							request.setAttribute("errors", errors);
							RequestDispatcher disp10 = request.getRequestDispatcher("/PatientServlet?action=prescriptions_list");
							disp10.forward(request, response);
						}
					}
					else if ("print_prescription".equals(action)) {
						String data_id = request.getParameter("id");
						DoctorListData doctorData = new DoctorListData();

						String query13 = "SELECT prescriptions.*, user.name FROM prescriptions JOIN user ON user.id = prescriptions.patient_id" + " WHERE prescriptions.id = " + data_id;
						PreparedStatement ps11 = con.prepareStatement(query13);
						ResultSet rs10 = ps11.executeQuery();

						if (rs10.next()) {
							PrescriptionData data = new PrescriptionData();
							data.setId(rs10.getInt("id"));
							data.setPatient_name(rs10.getString("name"));
							data.setDate(rs10.getString("date"));
							data.setWeight(rs10.getInt("weight"));
							data.setDiagnosis(rs10.getString("diagnosis"));
							data.setTests(rs10.getString("tests"));
							data.setPayment_type(rs10.getString("payment_type"));
							data.setFees(rs10.getInt("fees"));
							data.setRemarks(rs10.getString("remarks"));
							data.setFollow_up(rs10.getString("follow_up"));

							int doctor_id = rs10.getInt("doctor_id");

							String query14 = "SELECT user.id, user.name, user.email_id, user.phone_number, doctors.degree, departments.dept_name FROM user"
											+ " JOIN doctors ON user.id = doctors.user_id JOIN departments ON doctors.specialization = departments.id WHERE user.id = " + doctor_id;
							PreparedStatement ps12 = con.prepareStatement(query14);
							ResultSet rs11 = ps12.executeQuery();

							while (rs11.next()) {
								doctorData.setName(rs11.getString("name"));
								doctorData.setEmailID(rs11.getString("email_id"));
								doctorData.setPhone_number(rs11.getString("phone_number"));
								doctorData.setDept_name(rs11.getString("dept_name"));
								doctorData.setDegree(rs11.getString("degree"));
							}

							List<MedicinesData> dataList = new ArrayList<>();
							String query15 = "SELECT * FROM medicines WHERE prescription_id = " + data_id;
							PreparedStatement ps13 = con.prepareStatement(query15);
							ResultSet rs12 = ps13.executeQuery();

							while (rs12.next()) {
								MedicinesData medData = new MedicinesData();
								medData.setId(rs12.getInt("id"));
								medData.setMedicine_name(rs12.getString("medicine_name"));
								medData.setMed_type(rs12.getString("med_type"));
								medData.setMeasure(rs12.getString("measure"));
								medData.setDosage(rs12.getString("dosage"));
								medData.setInstruction(rs12.getString("instruction"));
								medData.setDays(rs12.getInt("days"));
								dataList.add(medData);
							}

							rs11.close();
							rs12.close();
							ps12.close();
							ps13.close();

							request.setAttribute("prescriptionData", data);
							request.setAttribute("doctorData", doctorData);
							request.setAttribute("dataList", dataList);
							request.getRequestDispatcher("Patient/PrescriptionPrint.jsp").forward(request, response);
						}
						else {
							List<String> errors = new ArrayList<>();
							errors.add("Unable to fetch record");
							request.setAttribute("errors", errors);
							RequestDispatcher disp11 = request.getRequestDispatcher("/PatientServlet?action=prescriptions_list");
							disp11.forward(request, response);
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
					RequestDispatcher disp12 = request.getRequestDispatcher("/PatientServlet?action=dashboard");
					disp12.forward(request, response);
				}
			}
			else {
				request.getSession().invalidate();
				List<String> errors = new ArrayList<>();
				errors.add("You have logged out!!!");
				request.setAttribute("errors", errors);
				RequestDispatcher disp13 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}