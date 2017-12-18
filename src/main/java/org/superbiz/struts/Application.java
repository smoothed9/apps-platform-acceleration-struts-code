package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main (String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean struts2(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        org.apache.struts2.dispatcher.FilterDispatcher dispatcher = new FilterDispatcher();
        registrationBean.addInitParameter("actionPackages", "com.lq");
        registrationBean.setFilter(dispatcher);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean struts_cleanup(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        org.apache.struts2.dispatcher.ActionContextCleanUp contextCleanUp = new ActionContextCleanUp();
        registrationBean.setFilter(contextCleanUp);
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean sitemesh(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        com.opensymphony.module.sitemesh.filter.PageFilter pageFilter = new PageFilter();
        registrationBean.setFilter(pageFilter);
        registrationBean.setOrder(3);
        return registrationBean;
    }
}
