package com.gmail.imccannascii;

import com.google.*;
import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {

		Gson gson = new Gson();

		JsonDataExtractor extractor = new JsonDataExtractor(
				"C:\\Users\\Ian\\Desktop\\Eclipse Projects\\web crawler\\src\\com\\gmail\\imccannascii\\Internet 1.json");
		extractor.readData();

		for (int i = 0; i < extractor.getAddresses().size(); i++) {
			System.out.println(extractor.getAddresses().get(i).getAddress());
		}
		
		System.out.println();
		
		extractor.getLinks();
	}

}
