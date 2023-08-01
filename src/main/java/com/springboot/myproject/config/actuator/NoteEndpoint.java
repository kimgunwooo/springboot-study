package com.springboot.myproject.config.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id="note") //JmxEndpoint, WebEndpoint
public class NoteEndpoint {
    private Map<String,Object> noteContent = new HashMap<>();

    @ReadOperation //HTTP GET 요청에 반응
    public Map<String,Object> getNote(){
        return noteContent;
    }
    @WriteOperation
    public Map<String,Object> writeNote(String key, Object value){
        noteContent.put(key,value);
        return noteContent;
    }
    @DeleteOperation
    public Map<String,Object> deleteNote(String key){
        noteContent.remove(key);
        return noteContent;
    }
}
