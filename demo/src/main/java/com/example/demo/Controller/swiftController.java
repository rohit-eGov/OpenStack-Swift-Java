package com.example.demo.Controller;

import nl.tweeenveertig.openstack.client.Account;
import nl.tweeenveertig.openstack.client.impl.ClientImpl;
import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;

import java.util.ResourceBundle;

public class swiftController {
    ResourceBundle credentials = ResourceBundle.getBundle("application");
    String username = credentials.getString("username");
    String password = credentials.getString("password");
    String auth_url = credentials.getString("auth_url");
    String tenant = credentials.getString("password");

    Account account = new ClientImpl().authenticate(tenant, username, password, auth_url);


}
