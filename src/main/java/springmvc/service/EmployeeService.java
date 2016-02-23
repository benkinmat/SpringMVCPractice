package springmvc.service;

import java.util.List;

import springmvc.model.Employee;

public interface EmployeeService {

	public Employee getEmployeeById(int id);

	public void saveEmployee(Employee emp);

	public void updateEmployee(Employee emp);

	public void deleteEmployee(String ssn);

	List<Employee> getAllEmployees();

	public Employee getEmployeeBySsn(String ssn);

	public boolean isEmployeeUnique(Integer id, String ssn);

}
