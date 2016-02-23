package springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springmvc.dao.EmployeeDao;
import springmvc.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	public void saveEmployee(Employee emp) {

		this.employeeDao.saveEmployee(emp);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * springmvc.service.EmployeeService#updateEmployee(springmvc.model.Employee
	 * ) It will update new info when the
	 */
	public void updateEmployee(Employee emp) {

		Employee entity = getEmployeeById(emp.getId());
		if (entity != null) {
			entity.setName(emp.getName());
			entity.setJoiningDate(emp.getJoiningDate());
			entity.setSalary(emp.getSalary());
			entity.setSsn(emp.getSsn());
			entity.setUserRoleSet(emp.getUserRoleSet());
		}				

	}

	public void deleteEmployee(String ssn) {

		this.employeeDao.deleteEmployeeBySsn(ssn);

	}

	public List<Employee> getAllEmployees() {

		return this.employeeDao.findAllEmployees();

	}

	public Employee getEmployeeBySsn(String ssn) {

		return this.employeeDao.findEmployeeBySsn(ssn);

	}

	public boolean isEmployeeUnique(Integer id, String ssn) {

		Employee emp = this.employeeDao.findEmployeeBySsn(ssn);
		return (emp == null) || ((id != null) && (emp.getId() == id));

	}

	public Employee getEmployeeById(int id) {

		return this.employeeDao.findById(id);

	}

}
