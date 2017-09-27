package topic6;

import topic5.Problem;

public class LongTermProblem extends Problem {
    private int solvingDuration;

    public LongTermProblem(Problem.Difficulty difficulty, String description, int solvingDuration) {
        super(difficulty, description);
        this.solvingDuration = solvingDuration;
    }

    public int getSolvingDuration() {
        return solvingDuration;
    }
}
