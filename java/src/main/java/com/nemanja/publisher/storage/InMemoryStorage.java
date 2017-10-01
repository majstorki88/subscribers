package com.nemanja.publisher.storage;

import com.nemanja.publisher.model.Subscriber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InMemoryStorage {

    private Map<String, List<Subscriber>> topics = new HashMap<>();

    public InMemoryStorage(){}

    public boolean subscribe(String topic, Subscriber subscriber){
        try {
            if (topics.containsKey(topic)) {
                List<Subscriber> subscribers = topics.get(topic);
                subscribers.add(subscriber);
            } else {
                List<Subscriber> subscribers = new ArrayList<>();
                subscribers.add(subscriber);
                topics.put(topic, subscribers);
            }

            return  true;
        } catch (Exception e){
            return false;
        }

    }

    public List<Subscriber> getSubscribers(String topic){
        return this.topics.get(topic);
    }

    public void publish(String topic, String message){

        for (Subscriber s : getSubscribers(topic)) {
            s.publish(message);
        }
    }
}
