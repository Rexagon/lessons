package topic6;

import topic5.Problem;
import topic5.Town;
import topic5.hero.Hero;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class City extends Town {
    private int dayNumber;
    private Set<HeroInformation> heroesInformation = new HashSet<HeroInformation>();
    private Deque<LongTermProblem> newProblems = new ArrayDeque<LongTermProblem>();

    public City(String name) {
        super(name);
    }

    @Override
    public void addHero(Hero hero) {
        super.addHero(hero);
        heroesInformation.add(new HeroInformation(hero));
    }

    public void causeProblem(LongTermProblem problem) {
        newProblems.push(problem);
    }

    public void printNews() {
        System.out.println("[" + getName() + "]");

        boolean somethingHappened = false;
        for (HeroInformation heroInformation : heroesInformation) {
            somethingHappened |= heroInformation.updateInformation();
        }

        while (!newProblems.isEmpty()) {
            LongTermProblem problem = newProblems.getFirst();
            newProblems.pop();

            System.out.println("A problem of class \"" + problem.getDifficulty() + "\" occured. " +
                    problem.getDescription()+ ".");

            boolean problemAssigned = false;
            for (HeroInformation heroInformation : heroesInformation) {
                Hero hero = heroInformation.getHero();

                if (!problemAssigned && !heroInformation.isBusy() && hero.getSpecialization() == problem.getDifficulty()) {
                    System.out.println("Problem assigned to " + hero.getName() + ".");
                    heroInformation.setCurrentProblem(problem);
                    System.out.println();
                    problemAssigned = true;
                }
            }

            if (!problemAssigned) {
                System.out.println("Nobody had managed with that problem.\n");
            }

            somethingHappened = true;
        }

        if (!somethingHappened) {
            System.out.println();
        }
    }
}
