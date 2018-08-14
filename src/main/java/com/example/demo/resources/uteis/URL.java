package com.example.demo.resources.uteis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static List<Integer> DecodeIntList(String s) {
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		// Pode ser usado lambida:
		// return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		return list;
	}

	public static String DecodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
}
