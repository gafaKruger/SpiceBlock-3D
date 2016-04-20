
package Model;

import java.io.Serializable;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Aresta implements Serializable {
    //private Ponto a, b;
    private int P1, P2;
    private long ID;

    public Aresta() {}

   public Aresta(int a, int b) {
        this.P1 = a;
        this.P2 = b;
    }
    
    public Aresta(int a, int b, long id) {
        this.P1 = a;
        this.P2 = b;
        this.ID = id;
    } 
    
     public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    
    public int getP1() {
        return P1;
    }

    public void setP1(int P1) {
        this.P1 = P1;
    }

    public int getP2() {
        return P2;
    }

    public void setP2(int P2) {
        this.P2 = P2;
    }
    
    /*public Aresta(Ponto a, Ponto b) {
        this.a = a;
        this.b = b;
    }
    
    public Aresta(Ponto a, Ponto b, long id) {
        this.a = a;
        this.b = b;
        this.ID = id;
    }


    public double Distancia () {
        Ponto r = (new Ponto(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ()));
        double d;
        d = r.getX()*r.getX()+r.getY()*r.getY()+r.getZ()*r.getZ();
        d = Math.sqrt(d);
        return d;
    }
    
    public Ponto getA() {
        return a;
    }
    
    public void setA(Ponto a) {
        this.a = a;
    }

    public Ponto getB() {
        return b;
    }

    public void setB(Ponto b) {
        this.b = b;
    }*/    
}
