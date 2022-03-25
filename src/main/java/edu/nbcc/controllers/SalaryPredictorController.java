package edu.nbcc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import edu.nbcc.dao.*;
import edu.nbcc.model.*;
import edu.nbcc.util.ValidationUtil;
import java.util.regex.Pattern;

/**
 * Servlet implementation class test
 */
public class SalaryPredictorController extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SALARY_VIEW = "/listSalaries.jsp";
	private static final String CREATE_SALARY = "/salaryPredictor.jsp";
	private static final String SALARY_SUMMARY = "/salaryPredictorResult.jsp";
	private static final String SALARY_FIND = "/viewSalary.jsp";
	private RequestDispatcher view;
	
	public RequestDispatcher getView() {
		return view;
	}
	
	public void setView(HttpServletRequest request, String viewPath) {
		view = request.getRequestDispatcher(viewPath);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryPredictorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test");
		
		Salary salary = new Salary();
		String path = request.getPathInfo();
		DAO dao = new DAOImpl();
		String[] parts = null;
		
		if (path==null) {
			//request.setAttribute("vm", book.getBooks());
				
			List<Salary> list = dao.findAll();
			request.setAttribute("vm", list);
			setView(request, SALARY_VIEW);
		}else{
			 parts = path.split("/");
			 if(parts[1].equalsIgnoreCase("find")) {
				setView(request, SALARY_FIND);
				
				if (request.getParameter("lastName") != null){
					System.out.println("it works");

					String lastName = request.getParameter("lastName");					
					Salary sal = dao.findByLastName(lastName);
					request.setAttribute("vm", sal);
					setView(request, SALARY_FIND);
				}
			}else {			
				if (parts[1].equalsIgnoreCase("create") || ValidationUtil.isNumeric(parts[1])) {
					int id = ValidationUtil.getInteger(parts[1]);
					if (id != 0) {
						//Salary sal = salary.getSalary(id);
						Salary sal = dao.findById(id);
						if (sal != null) {
							SalaryModel vm = new SalaryModel();
							vm.setSalary(sal);
							request.setAttribute("vm", vm);
						}else {
							request.setAttribute("error", new ErrorModel("Book not found"));
						}
					}else {
						//create salary
						request.setAttribute("vm", new SalaryModel());
					}
				}else {
					//edit salary
					request.setAttribute("vm", new SalaryModel());
				}
				setView(request,CREATE_SALARY);
			}
		}
		getView().forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List <String> errors = new ArrayList<String>();
		DAO dao = new DAOImpl();
		Salary salary = new Salary();
		setView(request, SALARY_SUMMARY);
		
		try {
			if (request.getParameter("firstName") == null || (request.getParameter("firstName").trim().length() == 0)) {
				errors.add("First name is null");
			}
			
			if (request.getParameter("lastName") == null || (request.getParameter("lastName").trim().length() == 0)) {
				errors.add("Last name is null");
			}
			
			if (request.getParameter("currentSalary") == null || (request.getParameter("currentSalary").trim().length() == 0)) {
				errors.add("Current salary is null");
			}
			
			int id = ValidationUtil.getInteger(request,"id");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			double currentSalary = ValidationUtil.getDouble(request,"currentSalary",errors);
			
			if (currentSalary < 10000) {
				errors.add("Salary cannot be less than 10000");
			}
			
			if (firstName != "" && !Pattern.matches("[a-zA-Z]+",firstName)) {
				errors.add("First name should only contain letters");
			}
			
			if (lastName !="" && !Pattern.matches("[a-zA-Z]+",lastName)) {
				errors.add("Last name should only contain letters");
			}
						
			double nextYearSalary;
			if (currentSalary < 40000)
				nextYearSalary = 1.5 * currentSalary;
			else
				nextYearSalary = 2000 + ((currentSalary - 40000) * 0.02) + currentSalary;

			if (errors.size() == 0) {
				salary.setId(id);
				salary.setFirstName(firstName);
				salary.setLastName(lastName);
				salary.setCurrentSalary(currentSalary);
				salary.setNextYearSalary(nextYearSalary);
				
				String action = request.getParameter("action").toLowerCase();
				
				switch(action) {
				case "create":
					dao.insert(salary);
					request.setAttribute("createdSalary", salary);
					break;
				
				case "save":
					int saveId = dao.update(salary);
					if (saveId > 0) {
						request.setAttribute("savedSalary", salary);
					}else {
						SalaryModel vm = new SalaryModel();
						vm.setSalary(salary);
						request.setAttribute("vm",vm);
						request.setAttribute("error",new ErrorModel("Salary does not exist to create"));
						setView(request,CREATE_SALARY);
					}
					break;
					
				case "delete":
					int deleteId = dao.delete(id);
					if (deleteId > 0) {
						request.setAttribute("deletedSalary", salary);
					}else {
						SalaryModel vm = new SalaryModel();
						vm.setSalary(salary);
						request.setAttribute("vm",vm);
						request.setAttribute("error",new ErrorModel("Salary does not exist to delete"));
						setView(request,CREATE_SALARY);
					}
					break;
				}
			}else {
				setView(request,CREATE_SALARY);
				ErrorModel errorModel = new ErrorModel();
				errorModel.setErrors(errors);
				request.setAttribute("error", errorModel);
				request.setAttribute("vm", new SalaryModel());
			}
		}catch(Exception ex) {
			setView(request,CREATE_SALARY);
			request.setAttribute("error", new ErrorModel("An error occurred, please try again"));
		}
		getView().forward(request, response);
	}
}
