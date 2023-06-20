package ru.netology.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final var context = new AnnotationConfigWebApplicationContext();
        //context.scan("ru.netology") - метод для конфигурирования бинов с помощью аннотаций (см. презентацию)
        context.register(JavaConfig.class);
        context.refresh();

        final var servlet = new DispatcherServlet(context);
        final var registration = servletContext.addServlet(
                "app", servlet  // app - имя сервлета
        );
        registration.setLoadOnStartup(1);   // порядок запуска сервлета
        registration.addMapping("/");   // путь сервлета
    }
}
