package SearchEngine;

public class RankedDocument {
    public int docID;
    public int score;

    public RankedDocument(int docID, int score) {
        this.docID = docID;
        this.score = score;
    }

    public int getDocID() {
        return docID;
    }

    public int getScore() {
        return score;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String toString() {
        return "RankedDocument [docID=" + docID + ", score=" + score + "]";
    }
}
