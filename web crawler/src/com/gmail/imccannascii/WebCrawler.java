package com.gmail.imccannascii;

import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

	private Address address;
	private ArrayList<String> successes = new ArrayList<String>();
	private ArrayList<String> skipped = new ArrayList<String>();
	private ArrayList<String> errors = new ArrayList<String>();

	public void CrawlTheWeb(List<Address> addresses) {

		String currentAddress = "";
		String linkToNextPage = "";
		String nextPageAddress = "";
		ArrayList<String> link;
		int i = 0;
		int k = 0;
		boolean firstCycle = true;
		boolean done = false;

		while (true) {

			currentAddress = addresses.get(i).getAddress();

			if (firstCycle == true) {
				successes.add(currentAddress); // first address is always
												// successful.
				firstCycle = false;
			}

			if (addresses.get(i).getLinks().isEmpty() == false && addresses.get(i).getLinks().get(k) != null) {

				linkToNextPage = addresses.get(i).getLinks().get(k);

				if (checkForSuccess(linkToNextPage)) { // Check if the link is
														// already in the
														// success array list.
					if (checkForSkipped(linkToNextPage)) {
						k++;
					} else {
						skipped.add(linkToNextPage);
						k++;
					}
				} else {
					for (int j = i + 1; j < addresses.size();) {

						if (linkToNextPage.equals(addresses.get(j).getAddress())) {
							successes.add(addresses.get(j).getAddress());
							i = j;
							k = 0;
							if (i == (addresses.size() - 1)) {
								done = true;
								break;
							} else {
								break;
							}
						} else {
							if (!checkForError(linkToNextPage)) {
								errors.add(linkToNextPage);
							}
							if (j >= addresses.get(i).getLinks().size()) {
								i++;
								k = 0;
								break;
							} else {
								j++;
								linkToNextPage = addresses.get(i).getLinks().get(j); // Breaks
																						// here.
							}
						}
					}
				}
				if(i >= addresses.size() -1){
					successes.add(addresses.get(i).getAddress());
					done = true;
				}
			}else{
				i++;
			}

			if (done == true) {
				break;
			}
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

	// TODO
	public void getSuccesses() {
		System.out.println("Successes:");
		for (int i = 0; i < successes.size(); i++) {
			System.out.println(successes.get(i));
		}
	}

	// TODO
	public ArrayList<String> getSkipped() {
		return null;
	}

	// TODO
	public ArrayList<String> getErrors() {
		return null;
	}
}
