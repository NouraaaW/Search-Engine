package SearchEngine;

public class VocabAVL {

	private  AVLTree<String, LinkedList<Integer>> AVLTree;
	private LinkedList<Integer> docsLists;

	public VocabAVL() {

		AVLTree = new AVLTree<>();
		docsLists = new LinkedList<>();
	}

	public void AddWordAVL(String word, int i) {
		LinkedList<Integer> docsList;

		if (!AVLTree.find(word)) {
			docsList = new LinkedList<>();
			docsList.insert(i);
			AVLTree.insert(word, docsList);
		} else {
			docsList = AVLTree.retrieve();
			if (!docsList.find(i)) {
				docsList.insert(i);
				AVLTree.update(docsList);
			}
		}
	}

	public boolean Find(String word) {

		if (AVLTree.find(word))
			return true;

		return false;

	}

	public LinkedList<Integer> FindDocList(String word) {

		return AVLTree.retrieveDataWithWord(word);

	}

	public LinkedList<Integer> getDocsLists() {
		return docsLists;
	}

	public int getvocabsize() {
		return AVLTree.size();
	}

}
