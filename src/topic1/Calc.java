package topic1;

public class Calc {
    public static void main(String[] args) {
	    int i = 13;
	    float a = 21;
	    double rez;

	    rez = (a + 5) / i * 2;
	    System.out.println(rez);

	    rez *= 10;
	    System.out.println(rez);

	    System.out.println("Result = " + (rez + 10));

	    rez += 10;
		System.out.println(rez + " " + (rez == 50));
    }
}
