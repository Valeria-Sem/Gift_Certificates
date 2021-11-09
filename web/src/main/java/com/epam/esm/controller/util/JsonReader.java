package com.epam.esm.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

/**
 * @author Valeria
 * This is JsonReader.class for converting JSON into Map
 */
public class JsonReader {

    /**
     * Method get data from JSON and map to HashMap
     * @param json string with objects
     * @return HashMap with json objects
     */
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
