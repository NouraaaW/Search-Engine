package SearchEngine;

import java.util.Scanner;

public class Main {

	private static final DocList AllDocIDs = null;
	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) {
		String stopwordsFile = "/Users/shoogalawadah/Desktop/data 2/stop.txt";
		String docsFile = "/Users/shoogalawadah/Desktop/data 2/dataset.csv";

		VocabList vocabList = new VocabList();

		VocabAVL vocabAVL = new VocabAVL();

		VocabBST vocabBST = new VocabBST();

		LinkedList<Integer> ALLdocIDs = new LinkedList<>();

		DocList docList = new DocList(stopwordsFile, docsFile, vocabList, vocabAVL, vocabBST);

		System.out.println("Welcome to our simple search engine!");

		String choice;

		do {
			System.out.println("would you like to see our menu of services? (yes/no)");
			choice = read.next();
			read.nextLine();
			if (choice.equalsIgnoreCase("no")) {
				System.out.println("thank you, goodbye! ");

				break;
			} else if (!choice.equalsIgnoreCase("yes")) {
				System.out.println("Invalid input!");
				System.out.println("Please enter yes or no.");
				continue;
			}

			int choice2 = 0;

			do {
				

					System.out.println("Enter your choice of service");
					System.out.println("1- Retrieve a term. ");
					System.out.println("2- Boolean Retrieval.");
					System.out.println("3- Ranked Retrieval.");
					System.out.println("4- Indexed Documents.");
					System.out.println("5- Indexed Tokens.");
					System.out.println("6- Number Of Tokens and vocabulary ");
					System.out.println("7- Exist.");
					if (!read.hasNextInt()) {
						System.out.println("Invalid input! Please enter a number from the menu.");
						read.nextLine();
						continue;
					}
					choice2 = read.nextInt();
					read.nextLine();
					System.out.println();
					switch (choice2) {
					case 1:
						System.out.println("--------- Retrieve a term --------- ");
						System.out.println("1- Retrieval using index ");
						System.out.println("2- Retrieval using inverted index ");
						System.out.println("3- Retrieval using BST ");
						System.out.println("4- Retrieval using AVL ");
						 int type = 0;

				            
				            while (true) {
				              try {
				                type = read.nextInt();
				                read.nextLine();
				                if (type == 1 || type == 2 || type == 3 || type == 4) {
				                  break;
				                } else {
				                  System.out.println("Invalid input! Please choose 1, 2, 3, or 4");
				                }
				              } catch (Exception e) {
				                System.out.println("Invalid input! Please enter a number from the menu.");
				                read.nextLine();
				              }
				            }
						if (type == 1) {
							ALLdocIDs = RetrieveIndex(docList);
						} else if (type == 2) {
							ALLdocIDs = RetrieveInvertedIndexe(vocabList);
						} else if (type == 3) {
							ALLdocIDs = RetrieveBST(vocabBST);
						} else if (type == 4) {
							ALLdocIDs = RetrieveAVL(vocabAVL);
						} else {
							System.out.println("invaled input");
							continue;
						}

						if (!ALLdocIDs.empty()) {
							ALLdocIDs.findFirst();
							System.out.print("{ ");
							while (ALLdocIDs.hasNext()) {
								System.out.print(ALLdocIDs.retrieve() + ", ");
								ALLdocIDs.findNext();
							}
							System.out.print(ALLdocIDs.retrieve() + " ");
							System.out.println("}");

						} else
							System.out.println("sorry, Term not available");

						System.out.println("------------------------------------------------");
						break;
					case 2:
						System.out.println("--------- Boolean Retrieval  --------- ");
						System.out.println("1- Retrieval using index ");
						System.out.println("2- Retrieval using inverted index ");
						System.out.println("3- Retrieval using BST ");
						System.out.println("4- Retrieval using AVL ");

						  int type1 = 0;

				            
				            while (true) {
				              try {
				                type1 = read.nextInt();
				                read.nextLine();
				                if (type1 == 1 || type1 == 2||  type1 == 3 || type1 == 4) {
				                  break;
				                } else {
				                  System.out.println("Invalid input! Please choose 1, 2 , 3 , or 4");
				                }
				              } catch (Exception e) {
				                System.out.println("Invalid input! Please enter a number from the menu.");
				                read.nextLine();
				              }
				            }
						if (type1 == 1) {
							ALLdocIDs = BooleanIndex(docList);
						} else if (type1 == 2) {
							ALLdocIDs = BooleanLinkedList(vocabList);
						} else if (type1 == 3) {
							ALLdocIDs = BooleanBST(vocabBST);
						} else if (type1 == 4) {
							ALLdocIDs = BooleanAVL(vocabAVL);
						} else {
							System.out.println("invaled input");
							continue;
						}

						if (!ALLdocIDs.empty()) {
							ALLdocIDs.findFirst();
							System.out.print("{ ");
							while (ALLdocIDs.hasNext()) {
								System.out.print(ALLdocIDs.retrieve() + ", ");
								ALLdocIDs.findNext();
							}
							System.out.print(ALLdocIDs.retrieve() + " ");
							System.out.println("}");

						} else
							System.out.println("sorry, Query not found  ");
						System.out.println("------------------------------------------------");
						break;

					case 3:

						System.out.println("--------- Ranked Retrieval ---------");
						System.out.println("Do you want to do ranked retrieval with:");
						System.out.println("1- Tokens");
						System.out.println("2- Vocabulary");

						int retrievalChoice = 0;

						while (true) {
							try {
								retrievalChoice = read.nextInt();
								read.nextLine();
								if (retrievalChoice == 1 || retrievalChoice == 2) {
									break;
								} else {
									System.out.println("Invalid input! Please choose 1 or 2.");
								}
							} catch (Exception e) {
								System.out.println("Invalid input! Please enter 1 or 2.");
								read.nextLine();
							}
						}

						if (retrievalChoice == 1) {
							System.out.println("Performing ranked retrieval with tokens...");
							System.out.print("Enter your query: ");
							String query = read.nextLine().trim();

							RankedRetrieval rankedRetrieval = new RankedRetrieval(docsFile);
							rankedRetrieval.retrieveRankedResults(query);

						} else if (retrievalChoice == 2) {
							System.out.println("Performing ranked retrieval with vocabulary...");
							System.out.print("Enter your query: ");
							LinkedList<RankedDocument> rankedResults = rankedRetrieval(docList, vocabList);
							if (rankedResults.empty()) {
								System.out.println("No documents matched your query.");
							}
						}

						break;

					case 4:
						System.out.println("--------- Indexed Documents --------- ");
						docList.printAllDocuments();
						System.out.println("-------------------------------------------");
						break;
						case 5:
							System.out.println("--------- List of vocabulary in the index  ---------");
							vocabList.printVocab();
							System.out.println("------------------------------------------------");

							break;

						case 6 :
							System.out.println("--------- Number of vocabulary and tokens  ---------");

							System.out.println("           Number Of Tokens: " + docList.getTokens());
							System.out.println("           Number Of vocabulary: " + vocabAVL.getvocabsize());
							System.out.println("----------------------------------------------------");

							break;
					case 7:
						System.out.println("thank you, goodbye! ");
						break;

					default:
						System.out.println("Invalid choice. Please select a valid option.");
					}

				
			} while (choice2 != 7);

			break;
		} while (true);

		read.close();

	}


	public static LinkedList<Integer> BooleanIndex(DocList docList) {
		System.out.println("Please enter query to look for:");
		String query = read.nextLine().toLowerCase().trim();
		String[] terms = query.split(" ");
		LinkedList<Integer> ALLdocIDs = new LinkedList<>();
		LinkedList<Integer> docIDs1;
		LinkedList<Integer> docIDs2;

		int Case = 0;

		if (query.contains("or ") && !query.contains("and ")) {
			Case = 1;
		} else if (!query.contains("or ") && query.contains("and ")) {
			Case = 2;
		} else if (query.contains("or ") && query.contains("and ")) {
			Case = 3;
		} else {
			System.out.println("Invalid query. Please try again.");
			return new LinkedList<>();
		}

		switch (Case) {
		case 1:
			for (int i = 0; i < terms.length; i++) {
				if (!terms[i].equals("or")) {
					docIDs1 = docList.find(terms[i]);
					if (docIDs1 != null) {
						docIDs1.findFirst();
						while (docIDs1.current()) {
							int docID = docIDs1.retrieve();
							Document doc = docList.getDoc(docID);

							if (!ALLdocIDs.find(docID) && doc.searchWord(terms[i])) {
								ALLdocIDs.insert(docID);
							}
							docIDs1.findNext();
						}
					}
				}
			}
			break;

		case 2:
			docIDs1 = docList.find(terms[0]);
			if (docIDs1 == null) {
				ALLdocIDs = new LinkedList<>();
				break;
			}

			for (int i = 2; i < terms.length; i += 2) {
				if (terms[i - 1].equals("and")) {
					docIDs2 = docList.find(terms[i]);
					LinkedList<Integer> tempList = new LinkedList<>();

					if (docIDs1 != null && docIDs2 != null) {
						docIDs1.findFirst();
						while (docIDs1.current()) {
							int docID1 = docIDs1.retrieve();
							docIDs2.findFirst();
							while (docIDs2.current()) {
								int docID2 = docIDs2.retrieve();
								if (docID1 == docID2) {
									if (!tempList.find(docID1)) {
										tempList.insert(docID1);
									}
									break;
								}
								docIDs2.findNext();
							}
							docIDs1.findNext();
						}
					}

					docIDs1 = tempList;
				}
			}

			ALLdocIDs = docIDs1;
			break;

		case 3: {

			String[] termswithoutAND = new String[terms.length];
			int j = 0;

			for (int i = 0; i < terms.length - 1; i++) {
				if (terms[i + 1].equals("or") && (terms[i].equals("intersectionDocIDs")
						|| (i != 0 && !terms[i - 1].equals("and")) || i == 0)) {
					termswithoutAND[j] = terms[i];
					j++;
					i++;
					termswithoutAND[j] = terms[i];
					j++;
				}

				if (terms[i + 1].equals("and") && i < terms.length - 1) {
					String word1 = terms[i];
					LinkedList<Integer> docIDs11 = docList.find(word1);
					if (docIDs11 != null && !docIDs11.empty()) {
						String word2 = terms[i + 2];
						LinkedList<Integer> docIDs21 = docList.find(word2);
						if (docIDs21 != null && !docIDs21.empty()) {
							docIDs11.findFirst();
							docIDs21.findFirst();

							while (docIDs11.current()) {
								Integer currentDocID1 = docIDs11.retrieve();
								docIDs21.findFirst();

								while (docIDs21.current()) {
									Integer currentDocID2 = docIDs21.retrieve();
									if (currentDocID1.equals(currentDocID2)) {
										if (!ALLdocIDs.find(currentDocID1)) {
											ALLdocIDs.insert(currentDocID1);
										}
									}
									docIDs21.findNext();
								}

								docIDs11.findNext();
							}
						}
					}
					String intersectionDocIDsS = "intersectionDocIDs";
					termswithoutAND[j] = intersectionDocIDsS;
					j++;
				}
			}

			for (int t = 0; t < termswithoutAND.length; t++) {
				if (termswithoutAND[t] != null) {
					if (!termswithoutAND[t].equals("or") && !termswithoutAND[t].equals("intersectionDocIDs")) {
						LinkedList<Integer> docIDs = docList.find(termswithoutAND[t]);
						if (docIDs != null && !docIDs.empty()) {
							docIDs.findFirst();
							while (docIDs.current()) {
								Integer docID = docIDs.retrieve();
								if (!ALLdocIDs.find(docID)) {
									ALLdocIDs.insert(docID);
								}
								docIDs.findNext();
							}
						}
					}
				}
			}
			break;
		}

		default:
			System.out.println("Unhandled case.");
		}

		return ALLdocIDs;

	}

	public static LinkedList<Integer> BooleanLinkedList(VocabList vocabList) {
		System.out.println("Please enter query to look for:");
		String query = read.nextLine().toLowerCase().trim();
		String[] terms = query.split(" ");
		LinkedList<Integer> ALLdocIDs = new LinkedList<>();
		LinkedList<Integer> docIDs1;
		LinkedList<Integer> docIDs2;

		int Case = 0;

		if (query.contains("or ") && !query.contains("and ")) {
			Case = 1;
		} else if (!query.contains("or ") && query.contains("and ")) {
			Case = 2;
		} else if (query.contains("or ") && query.contains("and ")) {
			Case = 3;
		} else {
			System.out.println("Invalid query. Please try again.");
			return new LinkedList<>();
		}

		switch (Case) {
		case 1:
			for (int i = 0; i < terms.length; i++) {
				if (!terms[i].equals("or")) {
					docIDs1 = vocabList.FindDocList(terms[i]);
					if (docIDs1 != null) {
						docIDs1.findFirst();
						while (docIDs1.current()) {
							int docID = docIDs1.retrieve();
							if (!ALLdocIDs.find(docID)) {
								ALLdocIDs.insert(docID);
							}
							docIDs1.findNext();
						}
					}
				}
			}
			break;

		case 2:
			docIDs1 = vocabList.FindDocList(terms[0]);
			if (docIDs1 == null) {
				ALLdocIDs = new LinkedList<>();
				break;
			}

			for (int i = 2; i < terms.length; i += 2) {
				if (terms[i - 1].equals("and")) {
					docIDs2 = vocabList.FindDocList(terms[i]);
					LinkedList<Integer> tempList = new LinkedList<>();

					if (docIDs1 != null && docIDs2 != null) {
						docIDs1.findFirst();
						while (docIDs1.current()) {
							int docID1 = docIDs1.retrieve();
							docIDs2.findFirst();
							while (docIDs2.current()) {
								int docID2 = docIDs2.retrieve();
								if (docID1 == docID2) {
									if (!tempList.find(docID1)) {
										tempList.insert(docID1);
									}
									break;
								}
								docIDs2.findNext();
							}
							docIDs1.findNext();
						}
					}

					docIDs1 = tempList;
				}
			}

			ALLdocIDs = docIDs1;
			break;

		case 3: {
			String[] termswithoutAND = new String[terms.length];

			int j = 0;

			for (int i = 0; i < terms.length - 1; i++) {

				if (terms[i + 1].equals("and") && i < terms.length - 1) {
					String word1 = terms[i];
					boolean found = vocabList.FindWord(word1);
					if (found) {
						docIDs1 = vocabList.FindDocList(word1);
						String word2 = terms[i + 2];
						boolean found2 = vocabList.FindWord(word2);
						if (found2) {
							docIDs2 = vocabList.FindDocList(word2);

							docIDs1.findFirst();
							docIDs2.findFirst();

							while (docIDs1.current()) {
								Integer currentDocID1 = docIDs1.retrieve();
								docIDs2.findFirst();

								while (docIDs2.current()) {
									Integer currentDocID2 = docIDs2.retrieve();
									if (currentDocID1.equals(currentDocID2)) {
										ALLdocIDs.insert(currentDocID1);
									}
									docIDs2.findNext();
								}

								docIDs1.findNext();
							}

						}

					}
					String intersectionDocIDsS = "intersectionDocIDs";
					termswithoutAND[j] = intersectionDocIDsS;
					j++;

				}

				if (terms[i + 1].equals("or") && (terms[i].equals("intersectionDocIDs")
						|| (i != 0 && !terms[i - 1].equals("and")) || i == 0)) {
					termswithoutAND[j] = terms[i];
					j++;
					i++;
					termswithoutAND[j] = terms[i];
					j++;

				}

			}

			for (int t = 0; t < termswithoutAND.length; t++) {
				if (termswithoutAND[t] != null) {
					if (!termswithoutAND[t].equals("or")) {
						if (!termswithoutAND[t].equals("intersectionDocIDs")) {
							boolean found = vocabList.FindWord(termswithoutAND[t]);
							if (found) {
								LinkedList<Integer> docIDs = vocabList.FindDocList(termswithoutAND[t]);
								if (!docIDs.empty()) {
									docIDs.findFirst();
									while (docIDs.hasNext()) {
										if (!ALLdocIDs.find(docIDs.retrieve())) {
											ALLdocIDs.insert(docIDs.retrieve());
										}
										docIDs.findNext();
									}
									if (!ALLdocIDs.find(docIDs.retrieve()))
										ALLdocIDs.insert(docIDs.retrieve());

								}
							}
						}
					}
				}
			}

			break;
		}

		default:
			System.out.println("Unhandled case.");
		}

		return ALLdocIDs;
	}

	public static LinkedList<Integer> BooleanAVL(VocabAVL vocabAVL) {

		System.out.println("Please enter qurey to look for");

		String query = read.nextLine().toLowerCase().trim();
		String[] terms = query.split(" ");
		int Case = 0;

		if (query.contains("or" + " ") && !query.contains("and" + " ")) {
			Case = 1;
		} else if (!query.contains("or" + " ") && query.contains("and" + " ")) {
			Case = 2;
		} else if (query.contains("or" + " ") && query.contains("and" + " ")) {
			Case = 3;
		} else {
			System.out.println("Try again");
		}

		LinkedList<Integer> ALLdocIDs = new LinkedList<>();
		LinkedList<Integer> docIDs = new LinkedList<Integer>();
		LinkedList<Integer> docIDs1 = new LinkedList<Integer>();
		LinkedList<Integer> docIDs2 = new LinkedList<Integer>();

		switch (Case) {

		case 1:

			for (int i = 0; i < terms.length; i++) {
				if (!terms[i].equals("or")) {
					boolean found = vocabAVL.Find(terms[i]);
					if (found) {
						docIDs = vocabAVL.FindDocList(terms[i]);
						if (!docIDs.empty()) {
							docIDs.findFirst();
							while (docIDs.hasNext()) {
								if (!ALLdocIDs.find(docIDs.retrieve())) {
									ALLdocIDs.insert(docIDs.retrieve());
								}
								docIDs.findNext();

							}
							if (!ALLdocIDs.find(docIDs.retrieve()))
								ALLdocIDs.insert(docIDs.retrieve());
						}
					}
				}
			}

			break;

		case 2: {

			for (int i = 0; i < terms.length - 1; i++) {
				if (!terms[i].equals("and")) {
					String word1 = terms[i];
					boolean found = vocabAVL.Find(word1);
					if (found) {
						docIDs1 = vocabAVL.FindDocList(word1);
						String word2 = terms[i + 2];
						boolean found2 = vocabAVL.Find(word2);
						if (found2) {
							docIDs2 = vocabAVL.FindDocList(word2);

							docIDs1.findFirst();
							docIDs2.findFirst();

							while (docIDs1.current()) {
								Integer currentDocID1 = docIDs1.retrieve();
								docIDs2.findFirst();

								while (docIDs2.current()) {
									Integer currentDocID2 = docIDs2.retrieve();
									if (currentDocID1.equals(currentDocID2)) {
										ALLdocIDs.insert(currentDocID1);
									}
									docIDs2.findNext();
								}

								docIDs1.findNext();
							}

						}

					}
				}
			}

			break;
		}

		case 3: {
			String[] termswithoutAND = new String[terms.length];

			int j = 0;

			for (int i = 0; i < terms.length - 1; i++) {
				if (terms[i + 1].equals("or") && (terms[i].equals("intersectionDocIDs")
						|| (i != 0 && !terms[i - 1].equals("and")) || i == 0)) {
					termswithoutAND[j] = terms[i];
					j++;
					i++;
					termswithoutAND[j] = terms[i];
					j++;

				}

				if (terms[i + 1].equals("and") && i < terms.length - 1) {
					String word1 = terms[i];
					boolean found = vocabAVL.Find(word1);
					if (found) {
						docIDs1 = vocabAVL.FindDocList(word1);
						String word2 = terms[i + 2];
						boolean found2 = vocabAVL.Find(word2);
						if (found2) {
							docIDs2 = vocabAVL.FindDocList(word2);

							docIDs1.findFirst();
							docIDs2.findFirst();

							while (docIDs1.current()) {
								Integer currentDocID1 = docIDs1.retrieve();
								docIDs2.findFirst();

								while (docIDs2.current()) {
									Integer currentDocID2 = docIDs2.retrieve();
									if (currentDocID1.equals(currentDocID2)) {
										ALLdocIDs.insert(currentDocID1);
									}
									docIDs2.findNext();
								}

								docIDs1.findNext();
							}

						}

					}
					String intersectionDocIDsS = "intersectionDocIDs";
					termswithoutAND[j] = intersectionDocIDsS;
					j++;

				}

			}

			for (int t = 0; t < termswithoutAND.length; t++) {
				if (termswithoutAND[t] != null) {
					if (!termswithoutAND[t].equals("or")) {
						if (!termswithoutAND[t].equals("intersectionDocIDs")) {
							boolean found = vocabAVL.Find(termswithoutAND[t]);
							if (found) {
								docIDs = vocabAVL.FindDocList(termswithoutAND[t]);
								if (!docIDs.empty()) {
									docIDs.findFirst();
									while (docIDs.hasNext()) {
										if (!ALLdocIDs.find(docIDs.retrieve())) {
											ALLdocIDs.insert(docIDs.retrieve());
										}
										docIDs.findNext();
									}
									if (!ALLdocIDs.find(docIDs.retrieve()))
										ALLdocIDs.insert(docIDs.retrieve());

								}
							}
						}
					}
				}
			}

			break;
		}
		}
		return ALLdocIDs;

	}

	public static LinkedList<Integer> BooleanBST(VocabBST vocabBST) {

		System.out.println("Please enter query to look for");

		String query = read.nextLine().toLowerCase().trim();
		String[] terms = query.split(" ");
		int Case = 0;

		if (query.contains("or" + " ") && !query.contains("and" + " ")) {
			Case = 1;
		} else if (!query.contains("or" + " ") && query.contains("and" + " ")) {
			Case = 2;
		} else if (query.contains("or" + " ") && query.contains("and" + " ")) {
			Case = 3;
		} else {
			System.out.println("Try again");
		}

		LinkedList<Integer> ALLdocIDs = new LinkedList<>();
		LinkedList<Integer> docIDs = new LinkedList<Integer>();
		LinkedList<Integer> docIDs1 = new LinkedList<Integer>();
		LinkedList<Integer> docIDs2 = new LinkedList<Integer>();

		switch (Case) {

		case 1:

			for (int i = 0; i < terms.length; i++) {
				if (!terms[i].equals("or")) {
					boolean found = vocabBST.Find(terms[i]);
					if (found) {
						docIDs = vocabBST.FindDocList(terms[i]);
						if (!docIDs.empty()) {
							docIDs.findFirst();
							while (docIDs.hasNext()) {
								if (!ALLdocIDs.find(docIDs.retrieve())) {
									ALLdocIDs.insert(docIDs.retrieve());
								}
								docIDs.findNext();

							}
							if (!ALLdocIDs.find(docIDs.retrieve()))
								ALLdocIDs.insert(docIDs.retrieve());
						}
					}
				}
			}

			break;

		case 2: {

			for (int i = 0; i < terms.length - 1; i++) {
				if (!terms[i].equals("and")) {
					String word1 = terms[i];
					boolean found = vocabBST.Find(word1);
					if (found) {
						docIDs1 = vocabBST.FindDocList(word1);
						String word2 = terms[i + 2];
						boolean found2 = vocabBST.Find(word2);
						if (found2) {
							docIDs2 = vocabBST.FindDocList(word2);

							docIDs1.findFirst();
							docIDs2.findFirst();

							while (docIDs1.current()) {
								Integer currentDocID1 = docIDs1.retrieve();
								docIDs2.findFirst();

								while (docIDs2.current()) {
									Integer currentDocID2 = docIDs2.retrieve();
									if (currentDocID1.equals(currentDocID2)) {
										ALLdocIDs.insert(currentDocID1);
									}
									docIDs2.findNext();
								}

								docIDs1.findNext();
							}

						}

					}
				}
			}

			break;
		}

		case 3: {
			String[] termswithoutAND = new String[terms.length];

			int j = 0;

			for (int i = 0; i < terms.length - 1; i++) {
				if (terms[i + 1].equals("or") && (terms[i].equals("intersectionDocIDs")
						|| (i != 0 && !terms[i - 1].equals("and")) || i == 0)) {
					termswithoutAND[j] = terms[i];
					j++;
					i++;
					termswithoutAND[j] = terms[i];
					j++;

				}

				if (terms[i + 1].equals("and") && i < terms.length - 1) {
					String word1 = terms[i];
					boolean found = vocabBST.Find(word1);
					if (found) {
						docIDs1 = vocabBST.FindDocList(word1);
						String word2 = terms[i + 2];
						boolean found2 = vocabBST.Find(word2);
						if (found2) {
							docIDs2 = vocabBST.FindDocList(word2);

							docIDs1.findFirst();
							docIDs2.findFirst();

							while (docIDs1.current()) {
								Integer currentDocID1 = docIDs1.retrieve();
								docIDs2.findFirst();

								while (docIDs2.current()) {
									Integer currentDocID2 = docIDs2.retrieve();
									if (currentDocID1.equals(currentDocID2)) {
										ALLdocIDs.insert(currentDocID1);
									}
									docIDs2.findNext();
								}

								docIDs1.findNext();
							}

						}

					}
					String intersectionDocIDsS = "intersectionDocIDs";
					termswithoutAND[j] = intersectionDocIDsS;
					j++;

				}

			}

			for (int t = 0; t < termswithoutAND.length; t++) {
				if (termswithoutAND[t] != null) {
					if (!termswithoutAND[t].equals("or")) {
						if (!termswithoutAND[t].equals("intersectionDocIDs")) {
							boolean found = vocabBST.Find(termswithoutAND[t]);
							if (found) {
								docIDs = vocabBST.FindDocList(termswithoutAND[t]);
								if (!docIDs.empty()) {
									docIDs.findFirst();
									while (docIDs.hasNext()) {
										if (!ALLdocIDs.find(docIDs.retrieve())) {
											ALLdocIDs.insert(docIDs.retrieve());
										}
										docIDs.findNext();
									}
									if (!ALLdocIDs.find(docIDs.retrieve()))
										ALLdocIDs.insert(docIDs.retrieve());

								}
							}
						}
					}
				}
			}

			break;
		}
		}
		return ALLdocIDs;

	}

	public static LinkedList<Integer> RetrieveBST(VocabBST vocabBST) {
		System.out.println("Enter the word you want to Retrieve: ");

		String word = read.nextLine().toLowerCase().trim();
		if (word.isEmpty() || word.split("\\s+").length > 1) {
			System.out.println("Please enter a single valid word without spaces.");
			return new LinkedList<>();
		}

		if (!word.matches("[a-zA-Z]+")) {
			System.out.println("Please enter a valid word containing only alphabetic characters.");
			return new LinkedList<>();
		}
		LinkedList<Integer> ALLdocIDs = new LinkedList<>();
		LinkedList<Integer> docIDs = new LinkedList<Integer>();

		boolean found = vocabBST.Find(word);
		if (found) {
			docIDs = vocabBST.FindDocList(word);
			if (!docIDs.empty()) {
				docIDs.findFirst();
				while (docIDs.hasNext()) {
					if (!ALLdocIDs.find(docIDs.retrieve())) {
						ALLdocIDs.insert(docIDs.retrieve());
					}
					docIDs.findNext();
				}
				ALLdocIDs.insert(docIDs.retrieve());

			}
		}

		return ALLdocIDs;

	}

	public static LinkedList<Integer> RetrieveAVL(VocabAVL vocabAVL) {
		System.out.println("Enter the word you want to Retrieve: ");

		String word = read.nextLine().toLowerCase().trim();
		if (word.isEmpty() || word.split("\\s+").length > 1) {
			System.out.println("Please enter a single valid word without spaces.");
			return new LinkedList<>();
		}

		if (!word.matches("[a-zA-Z]+")) {
			System.out.println("Please enter a valid word containing only alphabetic characters.");
			return new LinkedList<>();
		}
		LinkedList<Integer> ALLdocIDs = new LinkedList<>();
		LinkedList<Integer> docIDs = new LinkedList<Integer>();

		boolean found = vocabAVL.Find(word);
		if (found) {
			docIDs = vocabAVL.FindDocList(word);
			if (!docIDs.empty()) {
				docIDs.findFirst();
				while (docIDs.hasNext()) {
					if (!ALLdocIDs.find(docIDs.retrieve())) {
						ALLdocIDs.insert(docIDs.retrieve());
					}
					docIDs.findNext();
				}
				ALLdocIDs.insert(docIDs.retrieve());

			}
		}

		return ALLdocIDs;

	}

	public static LinkedList<Integer> RetrieveInvertedIndexe(VocabList vocabList) {
		System.out.print("Enter the word you want to Retrieve: ");
		String word = read.nextLine().toLowerCase().trim();
		if (word.isEmpty() || word.split("\\s+").length > 1) {
			System.out.println("Please enter a single valid word without spaces.");
			return new LinkedList<>();
		}

		if (!word.matches("[a-zA-Z]+")) {
			System.out.println("Please enter a valid word containing only alphabetic characters.");
			return new LinkedList<>();
		}
		LinkedList<Integer> foundDocIDs = vocabList.FindDocList(word);
		return foundDocIDs;

	}

	public static LinkedList<Integer> RetrieveIndex(DocList docList) {
		System.out.print("Enter the word you want to Retrieve: ");
		String word = read.nextLine().toLowerCase().trim();
		if (word.isEmpty() || word.split("\\s+").length > 1) {
			System.out.println("Please enter a single valid word without spaces.");
			return new LinkedList<>();
		}

		if (!word.matches("[a-zA-Z]+")) {
			System.out.println("Please enter a valid word containing only alphabetic characters.");
			return new LinkedList<>();
		}

		LinkedList<Integer> foundDocIDs = docList.find(word);
		return foundDocIDs;
	}

	public static LinkedList<RankedDocument> rankedRetrieval(DocList docList, VocabList vocabList) {
		System.out.print("Enter your query: ");
		String query = read.nextLine().toLowerCase().trim();

		if (query.isEmpty() || !query.matches("^[a-zA-Z\\s]+$")) {
			System.out.println("Invalid query. Please enter alphabetic terms separated by spaces.");
			return new LinkedList<>();
		}

		String[] queryTerms = query.split("\\s+");
		LinkedList<RankedDocument> rankedDocs = new LinkedList<>();

		for (int i = 0; i < docList.getCount(); i++) {
			Document doc = docList.getDoc(i);
			if (doc == null) continue;

			int score = 0;

			for (String term : queryTerms) {
				LinkedList<Integer> docIDs = vocabList.FindDocList(term);
				if (docIDs != null && docIDs.find(doc.getDocID())) {
					int termFrequency = doc.calculateTermFrequency(term);
					score += termFrequency;
				}
			}

			if (score > 0) {
				rankedDocs.insert(new RankedDocument(doc.getDocID(), score));
			}
		}

		rankedDocs = sortRankedDocuments(rankedDocs);


		System.out.println("Ranked Retrieval Results:");
		System.out.printf("%-10s %-10s\n", "DocID", "Score");
		System.out.println("----------------------------");

		rankedDocs.findFirst();
		while (rankedDocs.current()) {
			RankedDocument rd = rankedDocs.retrieve();
			System.out.printf("%-10d %-10d\n", rd.getDocID(), rd.getScore());
			rankedDocs.findNext();
		}

		return rankedDocs;
	}

	public static LinkedList<RankedDocument> sortRankedDocuments(LinkedList<RankedDocument> docs) {
		LinkedList<RankedDocument> sortedList = new LinkedList<>();
		docs.findFirst();

		while (docs.current()) {
			RankedDocument current = docs.retrieve();
			sortedInsert(sortedList, current);
			docs.findNext();
		}
		return sortedList;
	}

	public static void sortedInsert(LinkedList<RankedDocument> sortedList, RankedDocument doc) {
		if (sortedList.empty()) {
			sortedList.insert(doc);
			return;
		}

		LinkedList<RankedDocument> temp = new LinkedList<>();
		boolean inserted = false;

		sortedList.findFirst();
		while (sortedList.current()) {
			RankedDocument current = sortedList.retrieve();

			if (!inserted && doc.score > current.score) {
				temp.insert(doc);
				inserted = true;
			}
			temp.insert(current);
			sortedList.findNext();
		}

		if (!inserted) {
			temp.insert(doc);
		}
		sortedList.setHead(temp.getHead());
		sortedList.setCurrent(temp.getHead());
	}
}