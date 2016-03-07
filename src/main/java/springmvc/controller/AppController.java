package springmvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import springmvc.model.Employee;
import springmvc.model.FileBucker;
import springmvc.model.UserDocument;
import springmvc.model.UserRole;
import springmvc.service.EmployeeService;
import springmvc.service.UserDocumentService;
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
	UserDocumentService userDocumentService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucker")
	protected void initBinder(WebDataBinder webDataBinder) {

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

	@RequestMapping(value = { "/upload-document-{employeeId}" }, method = RequestMethod.GET)
	public String addDocument(ModelMap model, @PathVariable int employeeId) {

		Employee employee = employeeService.getEmployeeById(employeeId);
		model.addAttribute("employee", employee);

		List<UserDocument> documents = userDocumentService
				.findAllDocumentByUserId(employeeId);
		model.addAttribute("documents", documents);

		FileBucker fileBucker = new FileBucker();
		model.addAttribute("fileBucker", fileBucker);

		return "userdocuments";

	}

	@RequestMapping(value = { "/upload-document-{employeeId}" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid FileBucker fileBucker,
			BindingResult result, ModelMap model, @PathVariable int employeeId)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("The input file has error.");

			Employee employee = employeeService.getEmployeeById(employeeId);
			model.addAttribute("employee", employee);

			List<UserDocument> documents = userDocumentService
					.findAllDocumentByUserId(employeeId);
			model.addAttribute("documents", documents);

			return "userdocuments";

		} else {
			System.out.println("Fetching file.");

			Employee employee = employeeService.getEmployeeById(employeeId);
			model.addAttribute("employee", employee);

			saveDocument(fileBucker, employee);

			return "redirect:/upload-document-" + employeeId;
		}

	}

	@RequestMapping(value = { "/download-document-{employeeId}-{documentId}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int employeeId,
			@PathVariable int documentId, HttpServletResponse response)
			throws IOException {

		UserDocument userDocument = userDocumentService.findById(documentId);
		response.setContentType(userDocument.getType());
		response.setContentLength(userDocument.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ userDocument.getName() + "\"");

		FileCopyUtils.copy(userDocument.getContent(),
				response.getOutputStream());

		return "redirect:/upload-document-" + employeeId;

	}

	@RequestMapping(value = { "/delete-document-{employeeId}-{documentId}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int employeeId,
			@PathVariable int documentId) {

		userDocumentService.deleteById(documentId);

		return "redirect:/upload-document-" + employeeId;

	}

	public void saveDocument(FileBucker fileBucker, Employee employee)
			throws IOException {

		UserDocument document = new UserDocument();

		MultipartFile multipartFile = fileBucker.getFile();

		document.setName(multipartFile.getOriginalFilename());
		document.setType(multipartFile.getContentType());
		document.setContent(multipartFile.getBytes());
		document.setDescription(fileBucker.getDescription());
		document.setEmployee(employee);

		userDocumentService.save(document);

	}

	@ModelAttribute("roles")
	public List<UserRole> initializeRoles() {
		return userRoleService.getAllUserRoles();
	}

}
