public class Komplex {
    private double re;
    private double im;

    public Komplex(double re, double im) {
        this.re = re;
        this.im = im;
        // every value valid, no restictions necessary
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    /**
     * adds one complex number to another
     * 
     * @param z second addent
     */
    public void addiere(Komplex z) {
        this.re += z.re;
        this.im += z.im;
    }

    /**
     * multiplies two complex numbers with each other
     * 
     * @param z the complex number that the number is being multiplied by
     */
    public void multipliziere(Komplex z) {
        double betrag = this.getBetrag() * z.getBetrag();
        this.re = betrag * Math.cos(this.getArg() + z.getArg());
        this.im = betrag * Math.sin(this.getArg() + z.getArg());

    }

    /**
     * private method that calculates the argument of a complex number for
     * calculations based on z = r*e^(arg*PI)
     * 
     * @return the argument of a complex number
     */
    private double getArg() {

        if (re > 0) {
            if (im < 0) {
                return Math.atan(im / re) + (Math.PI * 2);
            } else if (im == 0) {
                return 0;
            }
            return Math.atan(im / re);
        } else if (re < 0) {
            if (im == 0) {
                return Math.PI;
            }
            return Math.atan(im / re + Math.PI);
        } else { // re == 0
            if (im > 0) {
                return Math.PI / 2;
            }

            return (3 * Math.PI) / 2;
        }
    }

    public double getBetrag() {
        return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
    }

    @Override
    public String toString() {
        if (im < 0) {
            return this.re + " - " + Math.abs(im) + "i";
        }
        return this.re + " + " + im + "i"; // test later
    }

    public Komplex[] getWurzel() {
        Komplex[] wurzeln = new Komplex[2];
        double betrag = getBetrag();
        double arg = getArg();
        for(int i = 0; i < 2; i++) { //use Moivre
            wurzeln[i] = new Komplex(Math.sqrt(betrag) * Math.cos((arg+(2*i*Math.PI))/2), Math.sqrt(betrag) * Math.sin((arg+(2*i*Math.PI))/2));
        }
        return wurzeln;

    }

    public Komplex getSumme(Komplex z) {
        return new Komplex(this.re + z.re, this.im + z.im);
    }

    public Komplex getProdukt(Komplex z) {
        double betrag = this.getBetrag() * z.getBetrag();
        return new Komplex(betrag * Math.cos(this.getArg() + z.getArg()), betrag * Math.sin(this.getArg() + z.getArg()));
    }


}
