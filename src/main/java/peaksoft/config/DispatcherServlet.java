package peaksoft.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebApp.class
        };
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);

    }

    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForServletNames(null, true, "/*");

    }
}
