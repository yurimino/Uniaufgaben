public class Addition implements Rechenoperation {

    private final double summand;

    public Addition(double summand) {
        this.summand = summand;
    }

    @Override
    public double berechne(double x) {
        return x + this.summand;
    }
}
