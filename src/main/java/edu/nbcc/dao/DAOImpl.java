/**
 * 
 */
package edu.nbcc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.nbcc.model.Salary;

/**
 * @author Maria Ocampo
 * No comments in implementation class
 */
public class DAOImpl implements DAO {

	//help to connect mysql to java code
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/javaee";
	private static final String USER_ID = "dev";
	private static final String PASSWORD = "dev1234";
	
	private static final String DELETE = "DELETE FROM salary WHERE id=?";
	private static final String INSERT = "INSERT INTO salary(firstName, lastName, currentSalary, nextYearSalary) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE salary SET firstName=?, lastName=?, currentSalary=?, nextYearSalary=? WHERE id=?";
	private static final String FIND_ALL = "SELECT * FROM salary ORDER BY id";
	private static final String FIND_BY_ID = "SELECT * FROM salary WHERE id=?";
	private static final String FIND_BY_LASTNAME = "SELECT * FROM salary WHERE lastName=?";
	
	/**
	 * get connection
	 * @return
	 */
	private Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(DB_URL,USER_ID,PASSWORD);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * close the connection
	 * @param conn
	 */
	private static void close(Connection conn) {
		if (conn!=null) {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * close the statement
	 * @param conn
	 */
	private static void close(Statement stmt) {
		if (stmt!=null) {
			try {
				stmt.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	@Override
	public int delete(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		try{
			conn = getConnection();
			statement = conn.prepareStatement(DELETE);
			statement.setInt(1,id);
			return statement.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			close(statement);
			close(conn);
		}	
	}

	@Override
	public int insert(Salary salary) {
		Connection conn = null;
		PreparedStatement statement = null;
		try{
			conn = getConnection();
			statement = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,salary.getFirstName());
			statement.setString(2,salary.getLastName());
			statement.setDouble(3,salary.getCurrentSalary());
			statement.setDouble(4,salary.getNextYearSalary());
			
			int result = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			
			if(rs.next()) {
				salary.setId(rs.getInt(1));
			}
			return result;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			close(statement);
			close(conn);
		}	
	}

	@Override
	public int update(Salary salary) {
		Connection conn = null;
		PreparedStatement statement = null;
		try{
			conn = getConnection();
			statement = conn.prepareStatement(UPDATE);
			statement.setString(1,salary.getFirstName());
			statement.setString(2,salary.getLastName());
			statement.setDouble(3,salary.getCurrentSalary());
			statement.setDouble(4,salary.getNextYearSalary());
			statement.setInt(5,salary.getId());
			
			return statement.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			close(statement);
			close(conn);
		}	
	}

	@Override
	public List<Salary> findAll() {
		Connection conn = null;
		PreparedStatement statement = null;
		List<Salary> list = new ArrayList<Salary>();
		try{
			conn = getConnection();
			System.out.println(conn);
			statement = conn.prepareStatement(FIND_ALL);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Salary salary = new Salary();
				salary.setId(rs.getInt("id"));
				salary.setFirstName(rs.getString("firstName"));
				salary.setLastName(rs.getString("lastName"));
				salary.setCurrentSalary(rs.getDouble("currentSalary"));
				salary.setNextYearSalary(rs.getDouble("nextYearSalary"));
				list.add(salary);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			close(statement);
			close(conn);
		}	
		return list;
	}

	@Override
	public Salary findByLastName(String lastName) {
		Connection conn = null;
		PreparedStatement statement = null;
		try{
			conn = getConnection();
			statement = conn.prepareStatement(FIND_BY_LASTNAME);
			statement.setString(1, lastName);
			ResultSet rs = statement.executeQuery();
			Salary salary = new Salary();
			if (rs.next()) {
				salary.setId(rs.getInt("id"));
				salary.setFirstName(rs.getString("firstName"));
				salary.setLastName(rs.getString("lastName"));
				salary.setCurrentSalary(rs.getDouble("currentSalary"));
				salary.setNextYearSalary(rs.getDouble("nextYearSalary"));
			}
			return salary;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			close(statement);
			close(conn);
		}
	}

	@Override
	public Salary findById(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		try{
			conn = getConnection();
			statement = conn.prepareStatement(FIND_BY_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			Salary salary = new Salary();
			if (rs.next()) {
				salary.setId(rs.getInt("id"));
				salary.setFirstName(rs.getString("firstName"));
				salary.setLastName(rs.getString("lastName"));
				salary.setCurrentSalary(rs.getDouble("currentSalary"));
				salary.setNextYearSalary(rs.getDouble("nextYearSalary"));
			}
			return salary;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			close(statement);
			close(conn);
		}
	}

}
