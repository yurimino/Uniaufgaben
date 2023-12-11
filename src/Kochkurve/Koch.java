package Kochkurve;

import javax.sound.midi.Soundbank;
import java.net.StandardSocketOptions;

public class Koch {

    public static void main(String[] args) {
        //Test

        System.out.println("Rekursionstiefe 0: ");
        kochKurve(0, 500, 500, 500, 0);
        System.out.println("(500/500)");
        System.out.println("Rekursionstiefe 1: ");
        kochKurve(0, 500, 500, 500, 1);
        System.out.println("(500/500)");
        System.out.println("Rekursionstiefe 2: ");
        kochKurve(0, 500, 500, 500, 2);
        System.out.println("(500/500)");
    }

    /**
     * takes two initial points (ax,ay) and (bx,by) and calculates the Koch curve based on the line between the two points, up to a certain depth of recursion "rek"
     * @param ax x value of starting point
     * @param ay y value of starting point
     * @param bx x value of end point
     * @param by y value of end point
     * @param rek desired depth of recursion
     */
    public static void kochKurve(double ax, double ay, double bx, double by, int rek) {

        //check if depth is 0 -> return start point

        if(rek == 0) {
            System.out.printf("(%.1f/%.1f) \n", ax, ay);
            return;
        }
        //otherwise calculate 3 points and 4 recursive calls (depth-1)
        double cx = (2 * ax + bx) / 3;
        double cy = (2 * ay + by) / 3;
        double dx = (ax + 2 * bx) / 3;
        double dy = (ay + 2 * by) / 3;
        double ex = (cx + dx) / 2;
        double ey = (cy + dy) / 2;
        //recursion
        kochKurve(ax, ay, cx, cy, rek - 1);
        kochKurve(cx, cy, ex, ey, rek - 1);
        kochKurve(ex, ey, dx, dy, rek - 1);
        kochKurve(dx, dy, bx, by, rek - 1);






    }
}



