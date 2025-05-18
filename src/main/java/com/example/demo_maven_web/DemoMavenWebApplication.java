package com.example.demo_maven_web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
public class DemoMavenWebApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DemoMavenWebApplication.class, args);

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8888);
        tomcat.setBaseDir("tomcat-temp");



        try {
            Context context = tomcat.addContext("/", new File(".").getAbsolutePath());
            //Context context = tomcat.addContext("", null);

            HttpServlet servlet = new HttpServlet() {
                @Override
                protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    //super.doGet(req, resp);
                    resp.setContentType("text/html");
                    PrintWriter writer = resp.getWriter();
                    writer.println("<html><head><title>");
                    writer.println("Hey, Tomcat");
                    writer.println("</title></head>");
                    writer.println("<body><h1>Hello Tomcat</h1></body>");
                    writer.println("</html>");
                }
            };

            String servletName = "helloServlet";
            tomcat.addServlet("/",servletName, servlet);
            context.addServletMappingDecoded("/hello", servletName);

            tomcat.start();
            System.out.println("Tomcat started on port: " + tomcat.getConnector().getPort());
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            e.printStackTrace(); // 추가
            throw new RuntimeException(e);
        }


	}

}
