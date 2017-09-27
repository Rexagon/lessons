package topic6;

import topic5.Problem;
import topic5.hero.Hero;

public class HeroInformation {
    private Hero hero;
    private int solvingTimeLeft;
    private LongTermProblem currentProblem;

    public HeroInformation(Hero hero) {
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }

    public void setCurrentProblem(LongTermProblem currentProblem) {
        if (isBusy()) {
            System.out.println(hero.getName() + " is busy");
        }
        else {
            this.currentProblem = currentProblem;
            this.solvingTimeLeft = currentProblem.getSolvingDuration();
        }
    }

    public boolean isBusy() {
        return currentProblem != null && solvingTimeLeft > 0;
    }

    public boolean updateInformation() {
        if (isBusy()) {
            solvingTimeLeft--;
        }

        if (solvingTimeLeft <= 0 && currentProblem != null) {
            System.out.println("There is some information about \"" + currentProblem.getDescription() + "\"");
            hero.solveProblem(new Problem(currentProblem.getDifficulty(), currentProblem.getDescription()));
            currentProblem = null;
            solvingTimeLeft = 0;
            System.out.println();
            return true;
        }
        else {
            return false;
        }
    }
}
