package com.nemanja.publisher.controller;

import com.nemanja.publisher.model.ConsoleSubscriber;
import com.nemanja.publisher.model.Message;
import com.nemanja.publisher.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    @Autowired
    InMemoryStorage inMemoryStorage;

    @RequestMapping(value = "/{topic}", method = RequestMethod.POST)
    @ResponseBody
    public Message subscribe(@PathVariable String topic, @RequestBody ConsoleSubscriber subscriber){

        if(inMemoryStorage.subscribe(topic, subscriber)){
            return new Message("Uspesno pretplacen.");
        } else {
            return new Message("Neuspesno pretplacen.");
        }
    }

    @RequestMapping("/publish/{topic}")
    public void publish(@PathVariable String topic){
        inMemoryStorage.publish(topic, "Caoooo");
    }
}
