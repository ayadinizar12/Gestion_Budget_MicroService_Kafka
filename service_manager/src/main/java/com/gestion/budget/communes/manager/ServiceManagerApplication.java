package com.gestion.budget.communes.manager;

import java.net.MalformedURLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceManagerApplication {

	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(ServiceManagerApplication.class, args);
		
	/*
	    URL url = new URL("http", "localhost", 8081, "/manager/project");

	        try

	    {
	        URLConnection urlConnection = url.openConnection();
	        HttpURLConnection connection = null;
	        if (urlConnection instanceof HttpURLConnection) {
	            connection = (HttpURLConnection) urlConnection;
	            connection.setRequestMethod("POST");
	            connection.connect();
	            int code = connection.getResponseCode();
	            System.out.println(code);
	        } else {
	            System.out.println("Please enter an HTTP URL.");
	            return;
	        }
	    } catch(
	    IOException e)

	    {
	        throw new RuntimeException(e);
	    }*/
	}

}
