/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Primitiva implements Serializable {
    protected boolean agrupado;
    protected long ID;
    protected Matriz M, Mperspectiva, Misometrica;
    protected Color bordas, preenchimento;
    protected float ka, kd, ks;
    protected int n, tipo, qtdFaces;
    protected Face faces[];
    
    /*
    TIPOS
    1 = CUBO
    2 = CILINDRO
    3 = CONE
    4 = ESFERA
    5 = PIRAMIDE
    6 = PRISMA
    7 = TOROIDE
    -1 = NAO DEFINIDO AINDA
    */
    
    public Primitiva () {
        agrupado = false;
        ka = kd = ks = 0.5f;
        n = 1;
        tipo = -1;
        faces = null;
        qtdFaces = 0;
    }
    
    public Face[] getFaces () {
        return faces;
    }
    
    public void setFaces (Face f[], int qf) {
        if (faces == null) {
            faces = new Face[qf];
            qtdFaces = qf;
        }
        for (int i = 0; i < qf; i++) {
            faces[i] = f[i];
        }
    }
    
    public int getQTDFaces () {
        return qtdFaces;
    }
    
    public void setQTDFaces (int qf) {
        qtdFaces = qf;
    }
    
    public void setTipo (int t) {
        tipo = t;
    }
    
    public int getTipo () {
        return tipo;
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
    
    public boolean isAgrupado() {
        return agrupado;
    }

    public void setAgrupado(boolean agrupado) {
        this.agrupado = agrupado;
    }  
}
