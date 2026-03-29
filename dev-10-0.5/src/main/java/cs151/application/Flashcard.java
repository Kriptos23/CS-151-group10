package cs151.application;

public class Flashcard {
    private int id;
    private int deckId;
    private String front;
    private String back;
    private String createdAt;


    public Flashcard(int id, int deckId, String front, String back, String createdAt) {
        this.id = id;
        this.deckId = deckId;
        this.front = front;
        this.back = back;
        this.createdAt = createdAt;
    }


    public int getId() {
        return id;
    }


    public int getDeckId() {
        return deckId;
    }


    public String getFront() {
        return front;
    }


    public String getBack() {
        return back;
    }


    public String getCreatedAt() {
        return createdAt;
    }
}

