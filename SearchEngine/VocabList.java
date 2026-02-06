package SearchEngine;

public class VocabList {

	private LinkedList<String> vocabList;
	private LinkedList<LinkedList<Integer>> docsLists;

	public VocabList() {
		vocabList = new LinkedList<>();
		docsLists = new LinkedList<>();
	}

	public void AddVocab(String word, int docID) {
		if (vocabList.empty()) {
			vocabList.insert(word);
			LinkedList<Integer> newDocList = new LinkedList<>();
			newDocList.insert(docID);
			docsLists.insert(newDocList);
			return;
		}

		vocabList.findFirst();
		docsLists.findFirst();

		boolean wordExists = false;

		while (true) {
			if (vocabList.retrieve().equals(word)) {
				wordExists = true;
				break;
			}

			if (!vocabList.hasNext()) {
				break;
			}

			vocabList.findNext();
			docsLists.findNext();
		}

		if (wordExists) {
			LinkedList<Integer> currentDocsList = docsLists.retrieve();
			if (!currentDocsList.find(docID)) {
				currentDocsList.insert(docID);
			}
		} else {
			vocabList.insert(word);
			LinkedList<Integer> newDocList = new LinkedList<>();
			newDocList.insert(docID);
			docsLists.insert(newDocList);
		}
	}

	public LinkedList<Integer> FindDocList(String word) {

		if (vocabList.find(word)) {

			int index = 0;
			vocabList.findFirst();
			while (vocabList.current()) {
				if (vocabList.retrieve().equals(word)) {
					break;
				}
				vocabList.findNext();
				index++;
			}

			docsLists.findFirst();

			for (int i = 0; i < index; i++) {
				docsLists.findNext();
			}

			return docsLists.retrieve();
		}

		return null;
	}

	public Boolean FindWord(String word) {

		if (vocabList.find(word)) {
			return true;
		}

		return false;
	}
	
	public void printVocab() {
		if (vocabList.empty()) {
			System.out.println("No vocabulary found.");
			return;
		}

		vocabList.findFirst();
		docsLists.findFirst();
		while (vocabList.current()) {
			String word = vocabList.retrieve();
			LinkedList<Integer> docList = docsLists.retrieve();
			int docCount = (docList == null) ? 0 : docList.size();

			System.out.println("Word: '" + word + "' appears in " + docCount + " document(s).");

			vocabList.findNext();
			if (docsLists.hasNext()) {
				docsLists.findNext();
			}
		}

	}
}
