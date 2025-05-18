package com.springboot3.mircoservice.springboot3;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration//Tells Spring to create this class object as bean
@ConfigurationProperties("db")//LOOKS APPLICATION.PROPERTIES AND WHOEVER MATCHES WITH db INJECTS IT TO CORRESPONDING varibales in DBSETTING
public class DbSetting {

    private String connection;
    private String host;
    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
