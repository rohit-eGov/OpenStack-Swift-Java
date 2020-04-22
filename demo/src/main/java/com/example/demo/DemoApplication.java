package com.example.demo;

import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Collection;
import java.util.ResourceBundle;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Hello World!");

		String username = "test:tester";
		String password = "testing";
		String auth_url = "http://192.168.0.108:12345/auth/v1.0";

		//Authecation of account (Logging in)
		AccountConfig config = new AccountConfig();
		config.setUsername(username);
		config.setPassword(password);
		config.setAuthUrl(auth_url);
		config.setAuthenticationMethod(AuthenticationMethod.BASIC);
		Account account = new AccountFactory(config).createAccount();

		//creating container for object
		Container container = account.getContainer("TestContainer2");
		container.create();
		container.makePublic();

		//listing out the container
		Collection<Container> containers = account.list();
		for (Container currentContainer : containers) {
			System.out.println(currentContainer.getName());
		}

		//storing the object into conatiner
		StoredObject object = container.getObject("Nodog.png");
		object.uploadObject(new File("/home/rohit/Pictures/Nodog.png")); // provide the filepath of file to upload
		System.out.println("Public URL: "+object.getPublicURL());

		//Object information
		System.out.println("Last modified:  "+object.getLastModified());
		System.out.println("ETag:           "+object.getEtag());
		System.out.println("Content type:   "+object.getContentType());
		System.out.println("Content length: "+object.getContentLength());

		Collection<StoredObject> objects = container.list();
		for (StoredObject currentObject : objects) {
			System.out.println(currentObject.getName());
		}


		System.out.printf("Account summary: containers containing "+account.getObjectCount()+" objects with a total of "+account.getBytesUsed()+" bytes%n");
	}

}
