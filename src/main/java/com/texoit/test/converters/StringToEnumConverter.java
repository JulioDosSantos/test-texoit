package com.texoit.test.converters;

import org.springframework.core.convert.converter.Converter;

import com.texoit.test.enums.Ranges;

public class StringToEnumConverter implements Converter<String, Ranges> {

	@Override
	public Ranges convert(String source) {
		return Ranges.valueOf(source.toUpperCase());
	}
}
