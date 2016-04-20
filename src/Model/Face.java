
package Model;

import java.io.Serializable;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Face implements Serializable {
    private Aresta A1, A2, A3, A4;
    private long ID;
    
    public Face () {}
    
    public Face (Aresta a1, Aresta a2, Aresta a3, Aresta a4) {
        this.A1 = a1;
        this.A2 = a2;
        this.A3 = a3;
        this.A4 = a4;
    }
    
    public Face (Aresta a1, Aresta a2, Aresta a3, Aresta a4, long id) {
        this.A1 = a1;
        this.A2 = a2;
        this.A3 = a3;
        this.A4 = a4;
        this.ID = id;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Aresta getA1() {
        return A1;
    }

    public void setA1(Aresta A1) {
        this.A1 = A1;
    }

    public Aresta getA2() {
        return A2;
    }

    public void setA2(Aresta A2) {
        this.A2 = A2;
    }

    public Aresta getA3() {
        return A3;
    }

    public void setA3(Aresta A3) {
        this.A3 = A3;
    }

    public Aresta getA4() {
        return A4;
    }

    public void setA4(Aresta A4) {
        this.A4 = A4;
    }
}
