package org.karthik.example.spring.web.config;

import javax.servlet.Filter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.karthik.example.spring.config.AppConfig;

/**
 * @author Karthik Gopalapuram
 *
 */
public class SpringWebAppInitializer extends
	AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class<?>[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return new Class<?>[] { WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
	return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
	return new Filter[] {
		new DelegatingFilterProxy("springSecurityFilterChain"),
		new OpenEntityManagerInViewFilter() };
    }
}
