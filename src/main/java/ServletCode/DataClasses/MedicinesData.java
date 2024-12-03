package ServletCode.DataClasses;

public class MedicinesData {

	private int id, days;
	private String medicine_name, med_type, measure, dosage, instruction;

	public MedicinesData() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getMedicine_name() {
		return medicine_name;
	}
	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}
	public String getMed_type() {
		return med_type;
	}
	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}