/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class ListaPiramides {

    private ArrayList<Piramide> listaPiramides;
    private boolean isAgrupado;
    private double centroX, centroY, centroZ;
    private int extremoCima, extremoBaixo, extremoEsquerda, extremoDireita, extremoFrente, extremoTras;
    private float fatorEscalaZ, fatorTraslacaoX, fatorTraslacaoY, fatorTraslacaoZ;
    private int anguloRotacaoX, anguloRotacaoY, anguloRotacaoZ;
    private boolean travaFrente, travaTopo, travaLado, controleTelaFrente, controleTelaTopo, controleTelaLado;

    public ListaPiramides() {
        listaPiramides = new ArrayList<>();
        isAgrupado = false;
        centroX = 0;
        centroY = 0;
        centroZ = 0;
        extremoCima = 999999999;
        extremoBaixo = -999999999;
        extremoEsquerda = 999999999;
        extremoDireita = -999999999;
        extremoTras = 999999999;
        extremoFrente = -999999999;
        fatorTraslacaoX = fatorTraslacaoY = fatorTraslacaoZ = 0;
        anguloRotacaoX = anguloRotacaoY = anguloRotacaoZ = 0;
        fatorEscalaZ = (float) 1.0f;
        travaFrente = travaLado = travaTopo = false;
        controleTelaLado = controleTelaTopo = controleTelaFrente = false;
    }

    public ListaPiramides(ArrayList<Piramide> listaPiramides) {
        isAgrupado = false;
        centroX = 0;
        centroY = 0;
        centroZ = 0;
        extremoCima = 999999999;
        extremoBaixo = -999999999;
        extremoEsquerda = 999999999;
        extremoDireita = -999999999;
        extremoTras = 999999999;
        extremoFrente = -999999999;
        fatorTraslacaoX = fatorTraslacaoY = fatorTraslacaoZ = 0;
        anguloRotacaoX = anguloRotacaoY = anguloRotacaoZ = 0;
        fatorEscalaZ = (float) 1.0f;
        travaFrente = travaLado = travaTopo = false;
        controleTelaLado = controleTelaTopo = controleTelaFrente = false;
        this.listaPiramides = listaPiramides;
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void defineFatores() {
        defineAgrupado();
        if (isAgrupado) {
            //fazer os métodos para grudar e distanciar da classe lista
        } else {
            fatorEscalaZ = listaPiramides.get(0).getMatriz().getFatorEscalaZ();
            fatorTraslacaoX = listaPiramides.get(0).getMatriz().getFatorTraslacaoX();
            fatorTraslacaoY = listaPiramides.get(0).getMatriz().getFatorTraslacaoY();
            fatorTraslacaoZ = listaPiramides.get(0).getMatriz().getFatorTraslacaoZ();
            anguloRotacaoX = listaPiramides.get(0).getMatriz().getAnguloRotacaoX();
            anguloRotacaoY = listaPiramides.get(0).getMatriz().getAnguloRotacaoY();
            anguloRotacaoZ = listaPiramides.get(0).getMatriz().getAnguloRotacaoZ();
        }
    }
    
     public void calculoCentroGeometrico() {
        defineAgrupado();
        calculoExtremos();
     }
     
    public void calculoExtremos() {}
    
    public void transladarPiramidesXY(int a, int b, int altura, int largura) {}
    
    public void transladarPiramidesXZ(int a, int b, int altura, int largura) {}
    
    public void transladarPiramidesZY(int a, int b, int altura, int largura) {}
    
    public void transladarPiramidesXY(int a, int b) {}
    
    public void transladarPiramidesXZ(int a, int b) {}
    
    public void transladarPiramidesZY(int a, int b) {}
    
    public void escalaZ(float escala) {}
    
    public void rotacaoZ(int ang, double sen, double cos) {}
    
    public void rotacaoY(int ang, double sen, double cos) {}
    
    private void rotacionarX(int ang, double sen, double cos) {}
    
    public void rotacaoX(int ang, double sen, double cos) {}
    
    /*
    public double distanciaCentroPontoXYZ(double c1, double c2, double c3) {}
    
    public double distanciaCentroPontoXY(double c1, double c2) {}
    
    public double distanciaCentroPontoXZ(double c1, double c2) {}
    
    public double distanciaCentroPontoYZ(double c1, double c2) {}
    */
    
    void removerTodasPiramides() {
        int i = listaPiramides.size() - 1;
        while (!listaPiramides.isEmpty()) {
            listaPiramides.remove(i);
            i--;
        }
    }
    
    public void defineAgrupado() {
        isAgrupado = listaPiramides.size() > 1;
    }

    public int getExtremoCima() {
        return extremoCima;
    }

    public void setExtremoCima(int extremoCima) {
        this.extremoCima = extremoCima;
    }

    public int getExtremoBaixo() {
        return extremoBaixo;
    }

    public void setExtremoBaixo(int extremoBaixo) {
        this.extremoBaixo = extremoBaixo;
    }

    public int getExtremoEsquerda() {
        return extremoEsquerda;
    }

    public void setExtremoEsquerda(int extremoEsquerda) {
        this.extremoEsquerda = extremoEsquerda;
    }

    public int getExtremoDireita() {
        return extremoDireita;
    }

    public void setExtremoDireita(int extremoDireita) {
        this.extremoDireita = extremoDireita;
    }

    public int getExtremoFrente() {
        return extremoFrente;
    }

    public void setExtremoFrente(int extremoFrente) {
        this.extremoFrente = extremoFrente;
    }

    public int getExtremoTras() {
        return extremoTras;
    }

    public void setExtremoTras(int extremoTras) {
        this.extremoTras = extremoTras;
    }

    public void adicionarPiramide(Piramide c) {
        listaPiramides.add(c);
        calculoCentroGeometrico();
        defineFatores();
    }

    public ArrayList<Piramide> getListaPiramides() {
        return listaPiramides;
    }

    public Piramide getPiramide() {
        return listaPiramides.get(0);
    }

    public void setListaPiramides(ArrayList<Piramide> lista) {
        this.listaPiramides = lista;
    }

    public boolean isAgrupado() {
        defineAgrupado();
        return isAgrupado;
    }

    public void setAgrupado(boolean agrupado) {
        this.isAgrupado = agrupado;
    }

    public double getCentroX() {
        return centroX;
    }

    public double getCentroY() {
        return centroY;
    }

    public double getCentroZ() {
        return centroZ;
    }

    public void setCentroX(double cx) {
        this.centroX = cx;
    }

    public void setCentroY(double cy) {
        this.centroY = cy;
    }

    public void setCentroZ(double cz) {
        this.centroZ = cz;
    }

    public int getTamanhoLista() {
        return listaPiramides.size();
    }

    public void setarAgrupamentoPiramides() {
        if (isAgrupado()) {
            for (int i = 0; i < listaPiramides.size(); i++) {
                listaPiramides.get(i).setAgrupado(true);
            }
        } else {
            listaPiramides.get(0).setAgrupado(false);
        }
    }

    public float getFatorEscalaZ() {
        return fatorEscalaZ;
    }

    public void setFatorEscalaZ(float fatorEscalaZ) {
        this.fatorEscalaZ = fatorEscalaZ;
    }

    public float getFatorTraslacaoX() {
        return fatorTraslacaoX;
    }

    public void setFatorTraslacaoX(float fatorTraslacaoX) {
        this.fatorTraslacaoX = fatorTraslacaoX;
    }

    public float getFatorTraslacaoY() {
        return fatorTraslacaoY;
    }

    public void setFatorTraslacaoY(float fatorTraslacaoY) {
        this.fatorTraslacaoY = fatorTraslacaoY;
    }

    public float getFatorTraslacaoZ() {
        return fatorTraslacaoZ;
    }

    public void setFatorTraslacaoZ(float fatorTraslacaoZ) {
        this.fatorTraslacaoZ = fatorTraslacaoZ;
    }

    public int getAnguloRotacaoX() {
        return anguloRotacaoX;
    }

    public void setAnguloRotacaoX(int anguloRotacaoX) {
        this.anguloRotacaoX = anguloRotacaoX;
    }

    public int getAnguloRotacaoY() {
        return anguloRotacaoY;
    }

    public void setAnguloRotacaoY(int anguloRotacaoY) {
        this.anguloRotacaoY = anguloRotacaoY;
    }

    public int getAnguloRotacaoZ() {
        return anguloRotacaoZ;
    }

    public void setAnguloRotacaoZ(int anguloRotacaoZ) {
        this.anguloRotacaoZ = anguloRotacaoZ;
    }
}
