package ru.netology.config;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        try {
            final var baseDir = Files.createTempDirectory("tomcat");    // создание временной директории, куда будут загружены файлы tomcat
            baseDir.toFile().deleteOnExit();    // после завершения директория, если существует, будет удалена
            tomcat.setBaseDir(baseDir.toAbsolutePath().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final var connector = new Connector();
        connector.setPort(9999);
        tomcat.setConnector(connector); //Зачем устанавливать порт через коннетор, а не напрямую? Что делает коннектор?

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }
}