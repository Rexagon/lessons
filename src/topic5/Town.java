package topic5;

import topic5.hero.Hero;

import java.util.HashSet;
import java.util.Set;

public class Town {
    private String name;
    private Set<Hero> heroes = new HashSet<Hero>();

    public Town(String name) {
        this.name = name;
        System.out.println("Somewhere exists a place called " + name + ".");
    }

    public String getName() {
        return name;
    }

    public void addHero(Hero hero) {
        heroes.add(hero);
        System.out.println("There is a hero " + hero.getName() + ", who lives in " + name +
                ". His specialization are problems with class \"" + hero.getSpecialization() + "\".");
    }

    public void causeProblem(Problem problem) {
        System.out.println("A problem of class \"" + problem.getDifficulty() + "\" occured. " +
                problem.getDescription() + ".");

        System.out.println("The location of this problem is " + name + ".");

        for (Hero hero : heroes) {
            if (hero.getSpecialization() == problem.getDifficulty()) {
                System.out.println("Problem assigned to " + hero.getName() + ".");
                hero.solveProblem(problem);
                System.out.println();
                return;
            }
        }

        System.out.println("Nobody had managed with that problem.\n");
    }
}
