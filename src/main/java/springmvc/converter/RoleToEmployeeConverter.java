package springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import springmvc.model.UserRole;
import springmvc.service.UserRoleService;

@Component
public class RoleToEmployeeConverter implements Converter<Object, UserRole> {

	@Autowired
	UserRoleService userRoleService;

	public UserRole convert(Object source) {

		Integer id = Integer.parseInt((String) source);
		System.out.println(id);
		return userRoleService.getUserRoleById(id);

	}

}
