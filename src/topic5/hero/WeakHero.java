package topic5.hero;

import topic5.Problem;

public class WeakHero extends Hero {
    public WeakHero(String name, int age) {
        super(name, age, Problem.Difficulty.LOW);
    }

    @Override
    public boolean solveProblem(Problem problem) {
        if (getSpecialization() != problem.getDifficulty()) {
            System.out.println("This problem didn't match the specialization of " + getName() +
                    " and he didn't want to solve it.");
            return false;
        }
        else {
            System.out.println("The problem was heroically solved by " + getName() + ".");
            return true;
        }
    }
}
