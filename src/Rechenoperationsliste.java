import java.util.ArrayList;

public class Rechenoperationsliste {

    private ArrayList<Rechenoperation> operations;

    public Rechenoperationsliste() {
        this.operations = new ArrayList<>();
    }

    public void add(Rechenoperation operation) {
        if(!this.operations.contains(operation)) {
            this.operations.add(operation);
        }
    }

    public double[] transform(double[] feld) {
        double[] result = new double[feld.length];
        for (int i = 0; i < feld.length; i++) {
            result[i] = feld[i];
            for (Rechenoperation operation : operations) {
                result[i] = operation.berechne(result[i]);
            }
        }
        return result;
    }

}
