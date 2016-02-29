package springmvc.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import springmvc.model.FileBucker;

@Component
public class FileValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return FileBucker.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		FileBucker file = (FileBucker) target;

		if (file.getFile() != null) {
			if (file.getFile() == null)
				errors.rejectValue("file", "missing.file");
		}

	}
	
}
