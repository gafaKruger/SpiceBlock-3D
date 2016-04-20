

package Model;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Angulos {
    private final double senos[];
    private final double cossenos[];
    private double pi;

    public Angulos () {
        pi = Math.acos(-1);
        senos = new double[360];
        cossenos = new double[360];
        for (int i = 0; i < 360; i++) {
            //Java usa radianos para calculo dos angulos, então é necessário converter para graus
            senos[i] = Math.sin(i*pi/180);
            cossenos[i] = Math.cos(i*pi/180);
            //System.out.println(i + " seno " + senos[i] + " cosseno " + cossenos[i]);
        }
    }
    
    public double[] getSenos() {
        return senos;
    }

    public double[] getCossenos() {
        return cossenos;
    }    
}
