/**
 * 
 */
package edu.nbcc.dao;

import java.util.List;

import edu.nbcc.model.Salary;

/**
 * @author Maria Ocampo
 *
 */
public interface DAO {
	
	/**
	 * delete a salary
	 * @param id
	 * @return
	 */
	public int delete(int id);
	
	/**
	 * insert a salary
	 * @param salary
	 * @return
	 */
	public int insert (Salary salary);
	
	/**
	 * update a salary
	 * @param salary
	 * @return
	 */
	public int update (Salary salary);
	
	/**
	 * find all books
	 * @return
	 */
	public List<Salary> findAll();
	
	/**
	 * get book by name
	 * @param name
	 * @return
	 */
	public Salary findByLastName(String name);
	
	public Salary findById(int id);
}
