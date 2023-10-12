package org.trananh.shoppingappbackend.ultilities;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Constants {
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");
	
	public static GsonBuilder gsonBuilder;
	static {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new Constants.LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Constants.LocalDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new Constants.LocalDateDeserializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Constants.LocalDateTimeDeserializer());
	}
	public static Gson gson = gsonBuilder.setPrettyPrinting().create();
	
	public static class LocalDateSerializer implements JsonSerializer<LocalDate> {
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

	    @Override
	    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
	        return new JsonPrimitive(formatter.format(localDate));
	    }

	}

	public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

	    @Override
	    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
	        return new JsonPrimitive(formatter.format(localDateTime));
	    }
	}

	public static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
		@Override
		public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return LocalDate.parse(json.getAsString(),
		            DateTimeFormatter.ofPattern("d-MMM-yyyy").withLocale(Locale.ENGLISH));
		}
	}

	public static class LocalDateTimeDeserializer implements JsonDeserializer < LocalDateTime > {
	    @Override
	    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	    throws JsonParseException {
	        return LocalDateTime.parse(json.getAsString(),
	            DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
	    }
	}
}
