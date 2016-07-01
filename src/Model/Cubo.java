
package Model;

import java.awt.Color;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Cubo extends Primitiva {
    private final int QTDFACES = 6;
    
    public Cubo () {
        super();
        faces = new Face[6];
    }
    
    public Cubo (Face f[]) {
        super();
        setFaces(f, QTDFACES);
    }
    
    public Cubo (Face f[], long id) {
        super();
        setFaces(f, QTDFACES);
        this.ID = id;
    }
    
    public Cubo (Face f[], long id, Color b) {
        super();
        setFaces(f, QTDFACES);
        this.ID = id;
        this.bordas = b;
    }

    public Cubo (Face f[], long id, Color b, Color p) {
        super();
        setFaces(f, QTDFACES);
        this.ID = id;
        this.bordas = b;
        this.preenchimento = p;
    }
    
    /*public void geraMatriz (int centroX, int centroY, int centroZ) {
    //M = new Matriz(this, getID());
    M = new Matriz (centroX, centroY, centroZ);
    }*/
    
    /*
    public Face getF1() {
        return F1;
    }

    public void setF1(Face F1) {
        this.F1 = F1;
    }

    public Face getF2() {
        return F2;
    }

    public void setF2(Face F2) {
        this.F2 = F2;
    }

    public Face getF3() {
        return F3;
    }

    public void setF3(Face F3) {
        this.F3 = F3;
    }

    public Face getF4() {
        return F4;
    }

    public void setF4(Face F4) {
        this.F4 = F4;
    }

    public Face getF5() {
        return F5;
    }

    public void setF5(Face F5) {
        this.F5 = F5;
    }

    public Face getF6() {
        return F6;
    }

    public void setF6(Face F6) {
        this.F6 = F6;
    }
    */
}
