package com.nemanja.publisher.model;

public class ConsoleSubscriber implements Subscriber{

    private String name;

    @Override
    public void publish(String message) {
        System.out.println(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
