package com.aspose.cloud.sdk.storage.model;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement dateStr, Type typeOfSrc, JsonDeserializationContext context) throws JsonParseException {
		
		//Convert JSON Date to Java Date where server is returning Date in this format /Date(1415106133000+0000)/
		String jsonDate = dateStr.getAsString();
		if(jsonDate.startsWith("/Date(")) {
			jsonDate = jsonDate.replace("/Date(", "");
		}
		if(jsonDate.endsWith(")/")) {
			jsonDate = jsonDate.replace(")/", "");
		}
		//Separate time zone from actual date
		if(jsonDate.contains("+")) {
			String[] splitedDateParts = jsonDate.split(Pattern.quote("+"));
			if(splitedDateParts.length >= 1) {
				jsonDate = splitedDateParts[0];
			}
		}
		
		long datetimestamp = Long.parseLong(jsonDate);
	    Date date = new Date(datetimestamp);
		
		return date;
	}

}
