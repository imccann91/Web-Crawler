package com.gmail.imccannascii;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {


		JsonDataExtractor extractor = new JsonDataExtractor(
				"C:\\Users\\Ian\\Desktop\\Eclipse Projects\\web crawler\\src\\com\\gmail\\imccannascii\\Internet 1.json");
		extractor.readData();
/*
		for (int i = 0; i < extractor.getAddresses().size(); i++) {
			System.out.println(extractor.getAddresses().get(i).getAddress());
		}
		
		System.out.println();
		
		extractor.getLinks();
		*/
		
		WebCrawler crawler = new WebCrawler();
		crawler.CrawlTheWeb(extractor.getAddresses());
		crawler.getSuccesses();
	}

}
