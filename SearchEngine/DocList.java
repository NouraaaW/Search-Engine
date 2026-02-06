package SearchEngine;

import java.io.File;
import java.util.Scanner;

public class DocList {

	private ArrayList<Document> docList = new ArrayList<>(50);
	private int TotT;

	public DocList(String stops, String docs, VocabList vocabList, VocabAVL vocabAVL, VocabBST vocabBST) {
		TotT = 0;

		String stopwordsString = "";

		try {
			File stopfile = new File(stops);
			Scanner reader = new Scanner(stopfile);
			while (reader.hasNext()) {
				String stopword = reader.next().toLowerCase();
				stopwordsString += stopword + " ";
			}
			reader.close();

			File docsfile = new File(docs);
			Scanner reader1 = new Scanner(docsfile);
			String line = reader1.nextLine();
			while (reader1.hasNextLine()) {
				line = reader1.nextLine().toLowerCase();
				int pos = line.indexOf(',');

				if (pos == -1 || line.substring(0, pos).trim().isEmpty())
					continue;
				try {
					int docID = Integer.parseInt(line.substring(0, pos).trim());
				} catch (NumberFormatException e) {
					System.out.println("Invalid document ID: " + line.substring(0, pos).trim());
					continue;
				}

				int docID = Integer.parseInt(line.substring(0, pos));
				String content = line.substring(pos + 1).trim();
				content = content.replaceAll("-", " ").trim();
				content = content.replaceAll("[^a-zA-Z0-9 ]", "").trim();

				Document doc = new Document(content, docID, stopwordsString, vocabList, vocabAVL, vocabBST);
				if (!docList.isFull())
					docList.add(doc);
				TotT += doc.getTokens();
			}

			reader1.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public int getCount() {
		return docList.size();
	}

	public int getTokens() {
		return TotT;
	}

	public Document getDoc(int index) {
		return docList.get(index);
	}

	public LinkedList<Integer> find(String word) {
		LinkedList<Integer> docIDs = new LinkedList<>();
		docList.findFirst();

		while (!docList.isCurrentNull()) {
			Document doc = docList.get(docList.getDocID());
			if (doc != null && doc.searchWord(word)) {
				docIDs.insert(docList.getDocID());
			}
			docList.findNext();
		}

		return docIDs;
	}

	public void printAllDocuments() {
		if (docList.isEmpty()) {
			System.out.println("No documents available.");
			return;
		}

		docList.findFirst();
		System.out.println("Documents in the list:");

		while (!docList.isCurrentNull()) {
			Document currentDoc = docList.get(docList.getDocID());
			System.out.println("Document ID: " + currentDoc.getDocID() + ", Tokens: " + currentDoc.getTokens());
			docList.findNext();
		}
	}

	public ArrayList<Document> getDocList() {
		return docList;
	}
}
