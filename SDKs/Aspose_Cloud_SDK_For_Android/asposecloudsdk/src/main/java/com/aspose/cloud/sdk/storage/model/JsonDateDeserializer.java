/**
 * 
 */
package com.aspose.cloud.sdk.storage.model;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public  class JsonDateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  {
    	try{
        String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\+.*)?\\))\\/";
       // String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\-.*)?\\))\\/";
        Pattern pattern = Pattern.compile(JSONDateToMilliseconds);
        Matcher matcher = pattern.matcher(json.getAsJsonPrimitive().getAsString());
        String result = matcher.replaceAll("$2");
        return new Date(new Long(result));
    	}
    	catch(Exception e)
    	{
//            String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\+.*)?\\))\\/";
            String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\-.*)?\\))\\/";
            Pattern pattern = Pattern.compile(JSONDateToMilliseconds);
            Matcher matcher = pattern.matcher(json.getAsJsonPrimitive().getAsString());
            String result = matcher.replaceAll("$2");
            return new Date(new Long(result));
    		
    	}
}

    @Override
    public JsonElement serialize(Date date, Type arg1, JsonSerializationContext arg2) {
        return new JsonPrimitive("/Date(" + date.getTime() + ")/");
    }
}
