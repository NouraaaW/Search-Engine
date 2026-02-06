package SearchEngine;

public class VocabBST {

	private BSTree<String, LinkedList<Integer>> BSTree;

	private LinkedList<Integer> docsLists;

	public VocabBST() {

		BSTree = new BSTree<>();
		docsLists = new LinkedList<>();
	}

	public void AddWordBST(String word, int i) {
		LinkedList<Integer> docsList;

		if (!BSTree.find(word)) {
			docsList = new LinkedList<>();
			docsList.insert(i);
			BSTree.insert(word, docsList);
		} else {
			docsList = BSTree.retrieve();
			if (!docsList.find(i)) {
				docsList.insert(i);
				BSTree.update(docsList);
			}
		}
	}

	public boolean Find(String word) {

		if (BSTree.find(word))
			return true;

		return false;

	}

	public LinkedList<Integer> FindDocList(String word) {

		return BSTree.retrieveDataWithWord(word);

	}
}
