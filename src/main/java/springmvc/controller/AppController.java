package springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import springmvc.model.Employee;
import springmvc.model.UserRole;
import springmvc.service.EmployeeService;
import springmvc.service.UserRoleService;
import springmvc.utils.FileValidator;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	UserRoleService userRoleService;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	FileValidator fileValidator;
	
	@InitBinder("fileBucker")
	protected void initBinder(WebDataBinder webDataBinder){
		
		webDataBinder.setValidator(fileValidator);
		
	}

	@RequestMapping(value = { "/", "list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";

	}

	@RequestMapping(value = { "new" }, method = RequestMethod.GET)
	public String addEmployee(ModelMap model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "registration";

	}

	@RequestMapping(value = { "new" }, method = RequestMethod.POST)
	public String addEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors())
			return "registration";

		if (!employeeService.isEmployeeUnique(employee.getId(),
				employee.getSsn())) {
			FieldError ssnError = new FieldError("employee", "ssn",
					messageSource.getMessage("non.unique.ssn",
							new String[] { employee.getSsn() },
							Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}

		employeeService.saveEmployee(employee);
		model.addAttribute("success", "Employee " + employee.getName()
				+ " registered successfully");

		return "success";

	}

	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model) {

		Employee employee = employeeService.getEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";

	}

	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee,
			BindingResult result, @PathVariable String ssn, ModelMap model) {

		if (result.hasErrors())
			return "registration";

		if (!employeeService.isEmployeeUnique(employee.getId(),
				employee.getSsn())) {
			FieldError ssnError = new FieldError("employee", "ssn",
					messageSource.getMessage("non.unique.ssn",
							new String[] { employee.getSsn() },
							Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}

		employeeService.updateEmployee(employee);
		model.addAttribute("success", "Employee " + employee.getName()
				+ " updated successfully");

		return "success";

	}

	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {

		employeeService.deleteEmployee(ssn);
		return "redirect:/";

	}
	
	@ModelAttribute("roles")
	public List<UserRole> initializeRoles(){
		return userRoleService.getAllUserRoles();
	}

}
