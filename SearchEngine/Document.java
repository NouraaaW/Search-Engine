package SearchEngine;

public class Document {

	private LinkedList<String> DocData;
	private int tokens;
	private int docID;

	public Document(String data, int i, String stopwords, VocabList vocabList, VocabAVL VocabAVL, VocabBST vocabBST) {
		DocData = new LinkedList<>();
		tokens = 0;
		docID = i;

		String[] words = data.split(" ");

		for (int count = 0; count < words.length; count++) {
			String word = words[count];
			tokens++;

			if (!stopwords.contains(word + " ")) {
				DocData.insert(word);
				vocabList.AddVocab(word, i);
				VocabAVL.AddWordAVL(word, i);
				vocabBST.AddWordBST(word, i);
			}
		}
	}

	public int getTokens() {
		return tokens;
	}

	public boolean searchWord(String word) {
		return DocData.find(word);
	}

	public int getDocID() {
		return docID;
	}

	public int calculateTermFrequency(String term) {
		int frequency = 0;

		if (DocData != null) { 
			DocData.findFirst();
			while (DocData.current()) {
				String currentTerm = DocData.retrieve();
				//System.out.println("DEBUG: Checking term '" + currentTerm + "' in DocID " + docID);
				if (currentTerm.equals(term)) {
					frequency++;
				}
				DocData.findNext();
			}
		}

		//System.out.println("DEBUG: Frequency of term '" + term + "' in DocID " + docID + ": " + frequency);
		return frequency;
	}

}
