package topic5.hero;

import topic5.Problem;

public class SimpleHero extends Hero {
    public SimpleHero(String name, int age) {
        super(name, age, Problem.Difficulty.REGULAR);
    }

    @Override
    public boolean solveProblem(Problem problem) {
        if (getSpecialization() != problem.getDifficulty()) {
            System.out.println("This problem was too difficult for " + getName() + " and he didn't want to solve it.");
            return false;
        }
        else {
            System.out.println("The simple problem was solved by " + getName() + ".");
            return true;
        }
    }
}
