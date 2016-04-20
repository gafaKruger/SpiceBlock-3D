

package Model;

import java.io.Serializable;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Ponto implements Serializable {
    private double x, y, z;  
    private long ID;

    public Ponto() {}

    public Ponto(double x, double y, double z, long id) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ID = id;
    }
    
    public Ponto(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Ponto(Ponto p) {
        this.x = p.getX();
        this.y = p.getY();
        this.z = p.getZ();
    }
    
    public Ponto Subtrai (Ponto b, Ponto a) {
        Ponto r = (new Ponto(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ()));
        return r;
    }
    
    public Ponto Norma (Ponto a) {
        double r;
        r = a.getX() * a.getX() + a.getY() * a.getY() + a.getZ() * a.getZ();
        r = Math.sqrt(r);
        Ponto b = new Ponto(a.getX()/r, a.getY()/r, a.getZ()/r);
        return b;
    }
    
    public void normalizar () {
        double r;
        r = this.getX() * this.getX() + this.getY()  *this.getY() + this.getZ() * this.getZ();
        r = Math.sqrt(r);
        setX(getX()/r);
        setY(getY()/r);
        setZ(getZ()/r);        
    }
    
    public void multiplicarValor (double v) {
        x = x * v;
        y = y * v;
        z = z * v;
    }

    public String Imprime () {
        String aux = this.getX() + " " + this.getY() + " " + getZ();
        return aux;
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
