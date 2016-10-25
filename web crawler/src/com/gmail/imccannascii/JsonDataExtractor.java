package com.gmail.imccannascii;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class JsonDataExtractor {

	private FileInputStream inputFile;
	private JsonReader reader;
	private ArrayList<Address> addresses;
	private Gson gson = new Gson();

	public JsonDataExtractor(String fileName) throws IOException {

		inputFile = new FileInputStream(fileName);

	}
	
	public void newFileToProcess(String filename) throws IOException{
		inputFile.close();
		inputFile = new FileInputStream(filename);
	}

	public void readData() {
		String temp = "";
		try {
			reader = new JsonReader(new InputStreamReader(inputFile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.exit(0);
		}

		addresses = new ArrayList<Address>();

		try {
			reader.beginObject();
			int index = 0;
			while (reader.hasNext()) {
				if (reader.peek() == JsonToken.NAME) {
					temp = reader.nextName();

				} else if (reader.peek() == JsonToken.BEGIN_ARRAY) {
					reader.beginArray();
				} else if (reader.peek() == JsonToken.BEGIN_OBJECT) {
					reader.beginObject();
				} else {

					if (temp.equals("address")) {
						String address = gson.fromJson(reader, String.class);
						addresses.add(new Address(address));
					} else if (temp.equals("links")) {
						String link = gson.fromJson(reader, String.class);
						addresses.get(index).addLinkToArrayList(link);
						;
					}
				}

				if (reader.peek() == JsonToken.END_ARRAY) {
					reader.endArray();
					index++;
					// readData();
				}
				if (reader.peek() == JsonToken.END_OBJECT) {
					reader.endObject();

				}
			}

		} catch (IOException e) {
			System.exit(0);
		}
	}

	public void closeFiles() throws IOException {
		inputFile.close();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void getLinks() {
		for (int i = 0; i < addresses.size(); i++) {
			for (int j = 0; j < addresses.get(i).getLinks().size(); j++) {
				System.out.println(addresses.get(i).getLinks().get(j));
			}
		}
	}
}
