package edu.nbcc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Salary {
	private int id;
	private String firstName;
	private String lastName;
	private double currentSalary;
	private double nextYearSalary;
	
	public Salary() {
		
	}
	
	public Salary(int id, String firstName, String lastName, double currentSalary, double nextYearSalary) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentSalary = currentSalary;
		this.nextYearSalary = nextYearSalary;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the currentSalary
	 */
	public double getCurrentSalary() {
		return currentSalary;
	}

	/**
	 * @param currentSalary the currentSalary to set
	 */
	public void setCurrentSalary(double currentSalary) {
		this.currentSalary = currentSalary;
	}

	/**
	 * @return the nextYearSalary
	 */
	public double getNextYearSalary() {
		return nextYearSalary;
	}

	/**
	 * @param nextYearSalary the nextYearSalary to set
	 */
	public void setNextYearSalary(double nextYearSalary) {
		this.nextYearSalary = nextYearSalary;
	}

	private List<Salary> mockData = new ArrayList<Salary>();
	
	private void buildMockData() {
		this.mockData.add(new Salary(1, "Fname 1", "Lname 1", 19.99, 29.99));
		this.mockData.add(new Salary(2, "Fname 2", "Lname 2", 29.99, 39.99));
		this.mockData.add(new Salary(3, "Fname 3", "Lname 3", 39.99, 49.99));

	}
	
	/**
	 * 
	 * @return
	 */
	public List<Salary> getSalaries(){
		buildMockData();
		return this.mockData;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Salary getSalary(int id) {
		buildMockData();
		List<Salary> query = mockData.stream().filter(b->b.getId()==id).collect(Collectors.toList());
		
		if(query.size() > 0) {
			return query.get(0);
		}
		return null;
	} 
	
	/**
	 * 
	 * @return
	 */
	public Salary create() {
		this.id = this.mockData.size() + 1;
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public int saveSalary() {
		if(getSalary(this.id) != null) {
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int deleteSalary() {
		if(getSalary(this.id) != null) {
			return 1;
		}
		return 0;
	}
}
