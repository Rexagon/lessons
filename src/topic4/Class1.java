package topic4;

class Class1 implements Class4 {
    protected double par1;
    public int par2;
    private String par3;
    private Class2[] class2s;

    public void Operation1(int par2) {
        this.par2 = par2;
    }

    protected String Operation2() {
        return par3;
    }

    public void Operation3(double par1) {
        this.par1 = par1;
    }
}

class Class2 {
    private int par4;

    protected void Operation5() {}

    public int Operation6() {
        return par4;
    }
}

class Class3 {
    public void Operation14(Class1 class1) {}
}

interface Class4 {
    public void Operation1(int par2);
}