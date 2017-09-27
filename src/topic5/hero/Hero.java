package topic5.hero;

import topic5.Problem;

public abstract class Hero {
    private String name;
    private int age;
    private String country;
    private Problem.Difficulty specialization;

    public Hero(String name, int age, Problem.Difficulty specialization) {
        this.name = name;
        this.age = age;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public Problem.Difficulty getSpecialization() {
        return specialization;
    }

    public abstract boolean solveProblem(Problem problem);
}
