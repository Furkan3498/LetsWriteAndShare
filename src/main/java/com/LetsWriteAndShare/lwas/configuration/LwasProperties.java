package com.LetsWriteAndShare.lwas.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "lwas")
@Configuration
public class LwasProperties {



    private Email email;

    private Client client;

    private Storage storage = new Storage();

    private String tokenType;



    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public static record Email(
            String username,
            String password,
            String host,
            int port,
            String from
    ){}

    public static record Client(
            String host
    ){}

    public static class Storage {
        String root = "uploads";
        String profile = "profile";

        public String getRoot() {
            return root;
        }
        public void setRoot(String root) {
            this.root = root;
        }

        public String getProfile() {
            return profile;
        }
        public void setProfile(String profile) {
            this.profile = profile;
        }
    }
}