package ServletCode.DataClasses;

public class DoctorListData {
	
	private int id, year_of_experience;
	private String name, email_id, phone_number, gender, date_of_birth, address, dept_name, degree;

	public DoctorListData() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailID() {
		return email_id;
	}
	public void setEmailID(String email_id) {
		this.email_id = email_id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public int getYear_of_experience() {
		return year_of_experience;
	}
	public void setYear_of_experience(int year_of_experience) {
		this.year_of_experience = year_of_experience;
	}
}