package ServletCode.DataClasses;

public class LoggedInUserData {
	private int id, info_record_id;
	private String name, email_id, phone_number; // for admin, doctor and patient
	private String gender, date_of_birth, address; // for doctor and patient
	private int year_of_experience; // for doctor
	private String blood_group; // for patient

	public LoggedInUserData() {}

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
	public int getYear_of_experience() {
		return year_of_experience;
	}
	public void setYear_of_experience(int year_of_experience) {
		this.year_of_experience = year_of_experience;
	}
	public String getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}
	public int getInfo_record_id() {
		return info_record_id;
	}
	public void setInfo_record_id(int info_record_id) {
		this.info_record_id = info_record_id;
	}
}