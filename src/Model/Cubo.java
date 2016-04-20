
package Model;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Cubo implements Serializable {
    private Face F1, F2, F3, F4, F5, F6;
    private boolean agrupado;
    private long ID;
    private Matriz M, Mperspectiva, Misometrica;
    private Color bordas, preenchimento;
    private float ka, kd, ks;
    private int n;

    public Cubo () {
        agrupado = false;
        ka = kd = ks = 0.5f;
        n = 1;
    }
    
    public Cubo (Face f1, Face f2, Face f3, Face f4, Face f5, Face f6) {
        this.F1 = f1;
        this.F2 = f2;
        this.F3 = f3;
        this.F4 = f4;
        this.F5 = f5;
        this.F6 = f6;
        agrupado = false;
        ka = kd = ks = 0.5f;
        n = 1;
    }
    
    public Cubo (Face f1, Face f2, Face f3, Face f4, Face f5, Face f6, long id) {
        this.F1 = f1;
        this.F2 = f2;
        this.F3 = f3;
        this.F4 = f4;
        this.F5 = f5;
        this.F6 = f6;
        this.ID = id;
        agrupado = false;
        ka = kd = ks = 0.5f;
        n = 1;
    }
    
    public Cubo (Face f1, Face f2, Face f3, Face f4, Face f5, Face f6, long id, Color b) {
        this.F1 = f1;
        this.F2 = f2;
        this.F3 = f3;
        this.F4 = f4;
        this.F5 = f5;
        this.F6 = f6;
        this.ID = id;
        this.bordas = b;
        agrupado = false;
        ka = kd = ks = 0.5f;
        n = 1;
    }

    public Cubo (Face f1, Face f2, Face f3, Face f4, Face f5, Face f6, long id, Color b, Color p) {
        this.F1 = f1;
        this.F2 = f2;
        this.F3 = f3;
        this.F4 = f4;
        this.F5 = f5;
        this.F6 = f6;
        this.ID = id;
        this.bordas = b;
        this.preenchimento = p;
        agrupado = false;
        ka = kd = ks = 0.5f;
        n = 1;
    }

    public Matriz getMatrizPerspectiva() {
        return Mperspectiva;
    }

    public void setMatrizPerspectiva(Matriz Mperspectiva) {
        this.Mperspectiva = Mperspectiva;
    }

    public Matriz getMatrizIsometrica() {
        return Misometrica;
    }

    public void setMatrizIsometrica(Matriz Misometrica) {
        this.Misometrica = Misometrica;
    }

    public Color getBordas() {
        return bordas;
    }

    public void setBordas(Color bordas) {
        this.bordas = bordas;
    }

    public Color getPreenchimento() {
        return preenchimento;
    }

    public void setPreenchimento(Color preenchimento) {
        this.preenchimento = preenchimento;
    }

    public float getKa() {
        return ka;
    }

    public void setKa(float ka) {
        this.ka = ka;
    }

    public float getKd() {
        return kd;
    }

    public void setKd(float kd) {
        this.kd = kd;
    }

    public float getKs() {
        return ks;
    }

    public void setKs(float ks) {
        this.ks = ks;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    
    public Color getCorBordas() {
        return bordas;
    }

    public void setCorBordas(Color bordas) {
        this.bordas = bordas;
    }

    public Color getCorPreenchimento() {
        return preenchimento;
    }

    public void setCorPreenchimento(Color preenchimento) {
        this.preenchimento = preenchimento;
    }
    
    /*public void geraMatriz (int centroX, int centroY, int centroZ) {
    //M = new Matriz(this, getID());
    M = new Matriz (centroX, centroY, centroZ);
    }*/
    
    public void setMatriz (Matriz m) {
        this.M = m;
    }
    
    public Matriz getMatriz () {
        return M;
    }

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

    public boolean isAgrupado() {
        return agrupado;
    }

    public void setAgrupado(boolean agrupado) {
        this.agrupado = agrupado;
    }  
}
