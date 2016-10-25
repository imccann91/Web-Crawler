package com.gmail.imccannascii;

import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

	private ArrayList<String> successes = new ArrayList<String>();
	private ArrayList<String> skipped = new ArrayList<String>();
	private ArrayList<String> errors = new ArrayList<String>();
	private ArrayList <String> addressContainer = new ArrayList <String> ();
	
	public void CrawlTheWeb(List<Address> addresses) {

		String linkToNextPage = "";
		int i = 0;
		int k = 0;
		boolean firstCycle = true;
		boolean done = false;
		
		obtainAddresses(addresses);
		
		while (true) {

			k = 0;
			if (firstCycle == true) {
				successes.add(addresses.get(0).getAddress()); // first address
																// is always
				// successful.
				firstCycle = false;
			}

			if (addresses.get(i).getLinks().isEmpty() == false && addresses.get(i).getLinks().get(k) != null) {

				for (k = 0; k < addresses.get(i).getLinks().size(); k++) {

					linkToNextPage = addresses.get(i).getLinks().get(k);

					for (int j = 0; j < addresses.size(); j++) {
						if (addressContainer.contains(linkToNextPage)) {
							if (checkForSuccess(linkToNextPage)) {
								if (checkForSkipped(linkToNextPage)) {

								} else {
									skipped.add(linkToNextPage);
								}
							} else {
								successes.add(linkToNextPage);

							}
						} else {
							if (checkForError(linkToNextPage)) {

							} else {
								errors.add(linkToNextPage);
							}
						}
					}
				}
				if (i >= addresses.size() - 1) {
					done = true;
				} else {
					i++;
				}
				if (done == true) {
					break;
				}
			} else {
				if (i >= addresses.size() - 1) {
					done = true;
				} else {
					i++;
				}
			}
		}
	}

	private void obtainAddresses(List <Address> inputAddresses){
		for(int m = 0; m < inputAddresses.size(); m++){
			addressContainer.add(inputAddresses.get(m).getAddress());
		}
	}
	
	private boolean checkForSuccess(String link) {
		for (int i = 0; i < successes.size(); i++) {
			if (link.equals(successes.get(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForSkipped(String link) {
		for (int i = 0; i < skipped.size(); i++) {
			if (link.equals(skipped.get(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForError(String link) {
		for (int i = 0; i < errors.size(); i++) {
			if (link.equals(errors.get(i))) {
				return true;
			}
		}
		return false;
	}

	public void getSuccesses() {
		System.out.println("Successes:");
		if (successes.isEmpty()) {
			System.out.println("[]");
		} else {
			for (int i = 0; i < successes.size(); i++) {
				System.out.println(successes.get(i));
			}
		}
	}

	
	public void getSkipped() {
		System.out.println("Skipped:");
		if (skipped.isEmpty()) {
			System.out.println("[]");
		} else {
			for (int i = 0; i < skipped.size(); i++) {
				System.out.println(skipped.get(i));
			}
		}
	}

	
	public void getErrors() {
		System.out.println("Errors:");
		if (errors.isEmpty()) {
			System.out.println("[]");
		} else {
			for (int i = 0; i < errors.size(); i++) {
				System.out.println(errors.get(i));
			}
		}
	}
}
