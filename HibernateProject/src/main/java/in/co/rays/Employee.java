package in.co.rays;

public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private Address Empadd;

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getEmpadd() {
		return Empadd;
	}

	public void setEmpadd(Address empadd) {
		Empadd = empadd;
	}

}
