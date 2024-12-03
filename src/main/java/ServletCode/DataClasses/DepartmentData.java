package ServletCode.DataClasses;

public class DepartmentData {
	private int id, total_doctors;
	private String name;

	public DepartmentData() {}
	public DepartmentData(int id, String name) {
		this.id = id;
		this.name = name;
	}
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
	public int getTotal_doctors() {
		return total_doctors;
	}
	public void setTotal_doctors(int total_doctors) {
		this.total_doctors = total_doctors;
	}
}