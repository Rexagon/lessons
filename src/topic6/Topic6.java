package topic6;

import topic5.Problem;
import topic5.hero.Hero;
import topic5.hero.SimpleHero;
import topic5.hero.StrongHero;
import topic5.hero.WeakHero;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class World {
    private static LocalDate date = LocalDate.now();
    private static Set<City> cities = new HashSet<City>();

    public static void addCity(City city) {
        cities.add(city);
    }

    public static void update() {
        date = date.plusDays(1);
        System.out.println("============[" + date + "]============");

        for (City city : cities) {
            city.printNews();
        }
    }
}

public class Topic6 {
    public static void main(String[] args) {
        // Creating cities
        City acity = new City("A-City");
        World.addCity(acity);

        City zcity = new City("Z-City");
        World.addCity(zcity);

        // Creating heroes
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

        // News list
        System.out.println();

        // Day 1
        zcity.causeProblem(new LongTermProblem(Problem.Difficulty.HIGH,
                "The scientist created a giant that destroys cities", 2));

        World.update();

        // Day 2
        zcity.causeProblem(new LongTermProblem(Problem.Difficulty.HIGH,
                "Some angry aliens come to the Earth", 2));

        World.update();

        // Day 3
        zcity.causeProblem(new LongTermProblem(Problem.Difficulty.LOW,
                "Robbers began robbery", 1));

        World.update();

        // Day 4
        zcity.causeProblem(new LongTermProblem(Problem.Difficulty.REGULAR,
                "The traffic light stopped working", 1));

        World.update();

        // Day 5
        acity.causeProblem(new LongTermProblem(Problem.Difficulty.LOW,
                "Robbers from Z-City moved to A-City and started lawlessness here", 1));

        World.update();

        // Day 6
        World.update();
    }
}
