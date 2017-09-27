package topic5;

public class Problem {
    public enum Difficulty {
        HIGH,
        LOW,
        REGULAR
    }

    private Difficulty difficulty;
    private String description;

    public Problem(Difficulty difficulty, String description) {
        this.difficulty = difficulty;
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }
}
