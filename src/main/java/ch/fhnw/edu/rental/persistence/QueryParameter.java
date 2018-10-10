package ch.fhnw.edu.rental.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * Fluent builder pattern to build maps.
 * 
 * @author Adam Bien
 */
public class QueryParameter {

    private final Map<String, Object> parameters;

    private QueryParameter(String name,Object value){
        this.parameters = new HashMap<>();
        this.parameters.put(name, value);
    }
    public static QueryParameter with(String name,Object value){
        return new QueryParameter(name, value);
    }
    public QueryParameter and(String name,Object value){
        this.parameters.put(name, value);
        return this;
    }
    public Map<String, Object> params(){
        return this.parameters;
    }
}