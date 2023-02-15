package ru.bojark;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

//это инфраструктурный класс, в нём запускаем Tomcat и сервлет из него.
public class Main {
    private static final String TOMCAT = "tomcat";
    private static final int PORT = 9999;


    public static void main(String[] args) throws LifecycleException, IOException {
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory(TOMCAT);
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(PORT);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }
}
