package edu.nbcc.model;

public class SalaryModel {
	private Salary salary = new Salary();
	
	
	private final int[] terms = new int[] {1,2,3,4,5,6};
	
	
	
	/**
	 * @return the book
	 */
	public Salary getSalary() {
		return salary;
	}



	/**
	 * @param salary the book to set
	 */
	public void setSalary(Salary salary) {
		this.salary = salary;
	}



	/**
	 * @return the terms
	 */
	public int[] getTerms() {
		return terms;
	}



	public SalaryModel(){
		
		
	}

}
