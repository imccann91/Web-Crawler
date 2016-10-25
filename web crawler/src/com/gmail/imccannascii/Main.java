package com.gmail.imccannascii;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {


		JsonDataExtractor extractor = new JsonDataExtractor(
				"C:\\Users\\Ian\\Desktop\\Eclipse Projects\\web crawler\\src\\com\\gmail\\imccannascii\\Internet 1.json");
		extractor.readData();
		extractor.closeFiles();
		
		WebCrawler crawler = new WebCrawler();
		crawler.CrawlTheWeb(extractor.getAddresses());
		crawler.getSuccesses();
		crawler.getSkipped();
		crawler.getErrors();
		
		System.out.println();
		
		extractor.newFileToProcess("C:\\Users\\Ian\\git\\Web-Crawler\\web crawler\\src\\com\\gmail\\imccannascii\\Internet 2.json");
		extractor.readData();
		extractor.closeFiles();
		
		crawler = new WebCrawler();
		crawler.CrawlTheWeb(extractor.getAddresses());
		crawler.getSuccesses();
		crawler.getSkipped();
		crawler.getErrors();
	}

}
