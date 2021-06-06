package com.kmh.spring.common;

public class DateUtils {

	private static final DateUtils instance = new DateUtils();

	private DateUtils() {
	}

	public static DateUtils getInstance() {
		return instance;
	}
}
