package com.epam.esm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class JsonReader {
    public HashMap getObjectsFromJSON(String json){
        ObjectMapper mapper = new ObjectMapper();
        HashMap objects = new HashMap<>();
        try{
            objects = mapper.readValue(json, HashMap.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return objects;
    }
}
