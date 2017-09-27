package topic5.hero;

import topic5.Problem;

public class StrongHero extends Hero {
    public StrongHero(String name, int age) {
        super(name, age, Problem.Difficulty.HIGH);
    }

    @Override
    public boolean solveProblem(Problem problem) {
        if (getSpecialization() != problem.getDifficulty()) {
            System.out.println("This problem was too easy for " + getName() + " and he didn't want to solve it.");
            return false;
        }
        else {
            System.out.println("The problem was easily solved by " + getName() + ".");
            return true;
        }
    }
}
