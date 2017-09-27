package topic5;

import topic5.hero.Hero;
import topic5.hero.SimpleHero;
import topic5.hero.StrongHero;
import topic5.hero.WeakHero;

public class Topic5 {
    public static void main(String[] args) {
        Town acity = new Town("A-City");
        Town zcity = new Town("Z-City");

        Hero onePunchMan = new StrongHero("Saitama", 25);
        zcity.addHero(onePunchMan);

        Hero genos = new StrongHero("Genos", 19);
        zcity.addHero(genos);

        Hero mumenRider = new SimpleHero("Mumen Rider", 25);
        zcity.addHero(mumenRider);

        Hero fubuki = new WeakHero("Fubuki", 23);
        acity.addHero(fubuki);

        Hero tatsumaki = new WeakHero("Tatsumaki", 28);
        acity.addHero(tatsumaki);

        System.out.println("\nNews:\n");

        zcity.causeProblem(new Problem(Problem.Difficulty.HIGH,
                "The scientist created a giant that destroys cities"));

        zcity.causeProblem(new Problem(Problem.Difficulty.LOW,
                "Robbers began robbery"));

        zcity.causeProblem(new Problem(Problem.Difficulty.REGULAR,
                "The traffic light stopped working"));

        acity.causeProblem(new Problem(Problem.Difficulty.LOW,
                "Robbers from Z-City moved to A-City and started lawlessness here"));
    }
}
