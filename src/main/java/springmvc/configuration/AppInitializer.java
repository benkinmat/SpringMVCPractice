package springmvc.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String LOCATION = "C:/mytemp/";

	private static final long MAX_FILE_SIZE = 1024 * 1024 * 25;// 25MB

	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;// 30MB

	private static final int FILE_SIZE_THRESHOLD = 0;

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] { AppConfig.class };

	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;

	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };

	}

	@Override
	protected void customizeRegistration(Dynamic registration) {

		registration.setMultipartConfig(getMultipartConfigElement());

	}

	public MultipartConfigElement getMultipartConfigElement() {

		return new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
				MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);

	}

}
