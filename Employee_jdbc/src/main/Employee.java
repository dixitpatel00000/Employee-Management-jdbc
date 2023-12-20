package main;

public class Employee {
	private String name;
	private int id;
	private int salary;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee(String name, int id, int salary, String email) {
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.email = email;
	}

	public Employee() {

	}

	public String toString() {
		return "Name-" + this.name + " id-" + this.id + " salary-" + this.salary + " email-" + this.email + "\n";
	}

}
