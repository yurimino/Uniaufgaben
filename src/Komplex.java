/**
 * class to represent a complex number in its parametric form
 */
public class Komplex {

    /**
     * real part of complex number
     */
    private double re;

    /**
     * imaginary part of complex number
     */
    private double im;


    /**
     * Constructor
     * @param re real part of complex number as double value
     * @param im imaginary part of complex number as double value
     */
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
     * adds a second complex number onto the current complex number
     * 
     * @param z number to be added
     */
    public void addiere(Komplex z) {
        this.re += z.re;
        this.im += z.im;
    }

    /**
     * changes the current complex number to the result of its multiplication with a second complex number
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

    /**
     * calculates the absolute value of the complex number
     * @return double containing absolute value of the complex number
     */
    public double getBetrag() {
        return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
    }

    /**
     * override of the toString method to make the returned String fit the format "[re] + [im]i"
     * @return String of the complex number in the specified format
     */
    @Override
    public String toString() {
        if(this.re == 0) {
            return this.im + "i";
        } else if(this.im == 0) {
            return "" + this.re;
        } else if (this.im < 0) {
            return this.re + " - " + Math.abs(this.im) + "i";
        }
        return this.re + " + " + this.im + "i";
    }

    /**
     * calculates the two square roots of a complex number
     * @return array of two Komplex Objects that each represent one result for the square root
     */
    public Komplex[] getWurzel() {
        Komplex[] wurzeln = new Komplex[2];
        double betrag = getBetrag();
        double arg = Math.atan2(this.im, this.re);
        for(int i = 0; i < 2; i++) { //use Moivre
            wurzeln[i] = new Komplex(Math.sqrt(betrag) * Math.cos((arg+(2*i*Math.PI))/2), Math.sqrt(betrag) * Math.sin((arg+(2*i*Math.PI))/2));
        }
        return wurzeln;

    }

    /**
     * adds two complex numbers
     * @param z number to be added
     * @return new Komplex object containing the sum of the two numbers
     */
    public Komplex getSumme(Komplex z) {
        return new Komplex(this.re + z.re, this.im + z.im);
    }


    /**
     * multiplies two complex numbers
     * @param z number being multiplied by
     * @return new Komplex object containing the result of the multiplication
     */
    public Komplex getProdukt(Komplex z) {
        double betrag = this.getBetrag() * z.getBetrag();
        return new Komplex(betrag * Math.cos(this.getArg() + z.getArg()), betrag * Math.sin(this.getArg() + z.getArg()));
    }


}
