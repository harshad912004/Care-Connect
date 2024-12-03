package ServletCode.DataClasses;

public class AppointmentData {
	private int id, requested_flag;
	private String doctor_name, patient_name, date_time_appointment, department, status_flag;

	public AppointmentData() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getDate_time_appointment() {
		return date_time_appointment;
	}
	public void setDate_time_appointment(String date_time_appointment) {
		this.date_time_appointment = date_time_appointment;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getStatus_flag() {
		return status_flag;
	}
	public void setStatus_flag(String status_flag) {
		this.status_flag = status_flag;
	}
	public int getRequested_flag() {
		return requested_flag;
	}
	public void setRequested_flag(int requested_flag) {
		this.requested_flag = requested_flag;
	}
}