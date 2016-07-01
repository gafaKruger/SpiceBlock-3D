/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class ListaPrimitivas implements Serializable {
    protected ArrayList<Primitiva> listaPrimitivas;
    protected boolean isAgrupado;
    protected double centroX, centroY, centroZ;
    protected int extremoCima, extremoBaixo, extremoEsquerda, extremoDireita, extremoFrente, extremoTras;
    protected float fatorEscalaZ, fatorTraslacaoX, fatorTraslacaoY, fatorTraslacaoZ;
    protected int anguloRotacaoX, anguloRotacaoY, anguloRotacaoZ;
    protected boolean travaFrente, travaTopo, travaLado, controleTelaFrente, controleTelaTopo, controleTelaLado;

    public ListaPrimitivas() {
        listaPrimitivas = new ArrayList<>();
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

    public ListaPrimitivas(ArrayList<Primitiva> listaPrimitivas) {
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
        this.listaPrimitivas = listaPrimitivas;
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void defineFatores() {
        defineAgrupado();
        if (isAgrupado) {
            //fazer os métodos para grudar e distanciar da classe lista
        } else {
            fatorEscalaZ = listaPrimitivas.get(0).getMatriz().getFatorEscalaZ();
            fatorTraslacaoX = listaPrimitivas.get(0).getMatriz().getFatorTraslacaoX();
            fatorTraslacaoY = listaPrimitivas.get(0).getMatriz().getFatorTraslacaoY();
            fatorTraslacaoZ = listaPrimitivas.get(0).getMatriz().getFatorTraslacaoZ();
            anguloRotacaoX = listaPrimitivas.get(0).getMatriz().getAnguloRotacaoX();
            anguloRotacaoY = listaPrimitivas.get(0).getMatriz().getAnguloRotacaoY();
            anguloRotacaoZ = listaPrimitivas.get(0).getMatriz().getAnguloRotacaoZ();
        }
    }
    
     public void calculoCentroGeometrico() {
        defineAgrupado();
        calculoExtremos();
        if (isAgrupado) {
            centroX = (extremoEsquerda + extremoDireita) / 2;
            centroY = (extremoCima + extremoBaixo) / 2;
            centroZ = (extremoTras + extremoFrente) / 2;
        } else {
            centroX = listaPrimitivas.get(0).getMatriz().getMatriz()[0][8];
            centroY = listaPrimitivas.get(0).getMatriz().getMatriz()[1][8];
            centroZ = listaPrimitivas.get(0).getMatriz().getMatriz()[2][8];
        }
     }
     
    public void calculoExtremos() {
        defineAgrupado();
        if (isAgrupado) {
            extremoCima = 999999999;
            extremoBaixo = -999999999;
            extremoEsquerda = 999999999;
            extremoDireita = -999999999;
            extremoTras = 999999999;
            extremoFrente = -999999999;
            for (int i = 0; i < listaPrimitivas.size(); i++) {
                //Extremos ordenada X
                if (listaPrimitivas.get(i).getMatriz().getExtremoEsquerda() < extremoEsquerda) {
                    extremoEsquerda = listaPrimitivas.get(i).getMatriz().getExtremoEsquerda();
                }
                if (listaPrimitivas.get(i).getMatriz().getExtremoDireita() > extremoDireita) {
                    extremoDireita = listaPrimitivas.get(i).getMatriz().getExtremoDireita();
                }
                //Extremos ordenada Y
                if (listaPrimitivas.get(i).getMatriz().getExtremoCima() < extremoCima) {
                    extremoCima = listaPrimitivas.get(i).getMatriz().getExtremoCima();
                }
                if (listaPrimitivas.get(i).getMatriz().getExtremoBaixo() > extremoBaixo) {
                    extremoBaixo = listaPrimitivas.get(i).getMatriz().getExtremoBaixo();
                }
                //Extremos ordenada Z
                if (listaPrimitivas.get(i).getMatriz().getExtremoTras() < extremoTras) {
                    extremoTras = listaPrimitivas.get(i).getMatriz().getExtremoTras();
                }
                if (listaPrimitivas.get(i).getMatriz().getExtremoFrente() > extremoFrente) {
                    extremoFrente = listaPrimitivas.get(i).getMatriz().getExtremoFrente();
                }
            }
        } else {
            extremoCima = listaPrimitivas.get(0).getMatriz().getExtremoCima();
            extremoBaixo = listaPrimitivas.get(0).getMatriz().getExtremoBaixo();
            extremoEsquerda = listaPrimitivas.get(0).getMatriz().getExtremoEsquerda();
            extremoDireita = listaPrimitivas.get(0).getMatriz().getExtremoDireita();
            extremoTras = listaPrimitivas.get(0).getMatriz().getExtremoTras();
            extremoFrente = listaPrimitivas.get(0).getMatriz().getExtremoFrente();
        }
    }
    
    public void transladarPrimitivasXY(int a, int b, int altura, int largura) {
        if (isAgrupado) {
            if (travaFrente && controleTelaFrente) {
                for (int i = 0; i < listaPrimitivas.size(); i++) {
                    listaPrimitivas.get(i).getMatriz().translacaoXY(a, b);
                    travaFrente = listaPrimitivas.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    if (travaFrente) {
                        controleTelaFrente = true;
                    } else {
                        controleTelaFrente = false;
                    }
                }
            } else {
                for (int i = 0; i < listaPrimitivas.size(); i++) {
                    travaFrente = listaPrimitivas.get(i).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    travaTopo = listaPrimitivas.get(i).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    travaLado = listaPrimitivas.get(i).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaFrente) {
                        i = listaPrimitivas.size();
                    }
                    if (travaLado) {
                        controleTelaLado = true;
                    } else {
                        controleTelaLado = false;
                    }
                    if (travaTopo) {
                        controleTelaTopo = true;
                    } else {
                        controleTelaTopo = false;
                    }
                }
                if (!travaFrente) {
                    for (int i = 0; i < listaPrimitivas.size(); i++) {
                        listaPrimitivas.get(i).getMatriz().translacaoXY(a, b);
                    }
                }
            }
        } else {
            if (travaFrente && controleTelaFrente) {
                listaPrimitivas.get(0).getMatriz().translacaoXY(a, b);
                travaFrente = listaPrimitivas.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                if (travaFrente) {
                    controleTelaFrente = true;
                } else {
                    controleTelaFrente = false;
                }
            } else {
                //travaFrente = listaPrimitivas.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                //travaTopo = listaPrimitivas.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                //travaLado = listaPrimitivas.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (!travaFrente) {
                    listaPrimitivas.get(0).getMatriz().translacaoXY(a, b);
                }
                if (travaLado) {
                    controleTelaLado = true;
                } else {
                    controleTelaLado = false;
                }
                if (travaTopo) {
                    controleTelaTopo = true;
                } else {
                    controleTelaTopo = false;
                }
            }
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void transladarPrimitivasXZ(int a, int b, int altura, int largura) {
        if (isAgrupado) {
            if (travaTopo && controleTelaTopo) {
                for (int i = 0; i < listaPrimitivas.size(); i++) {
                    listaPrimitivas.get(i).getMatriz().translacaoXZ(a, b);
                    travaTopo = listaPrimitivas.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    if (travaTopo) {
                        controleTelaTopo = true;
                    } else {
                        controleTelaTopo = false;
                    }
                }
            } else {
                for (int i = 0; i < listaPrimitivas.size(); i++) {
                    travaFrente = listaPrimitivas.get(i).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    travaTopo = listaPrimitivas.get(i).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    travaLado = listaPrimitivas.get(i).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaTopo) {
                        i = listaPrimitivas.size();
                    }
                    if (travaLado) {
                        controleTelaLado = true;
                    } else {
                        controleTelaLado = false;
                    }
                    if (travaFrente) {
                        controleTelaFrente = true;
                    } else {
                        controleTelaFrente = false;
                    }
                }
                if (!travaTopo) {
                    for (int i = 0; i < listaPrimitivas.size(); i++) {
                        listaPrimitivas.get(i).getMatriz().translacaoXZ(a, b);
                    }
                }
            }
        } else {
            if (travaTopo && controleTelaTopo) {
                listaPrimitivas.get(0).getMatriz().translacaoXZ(a, b);
                travaTopo = listaPrimitivas.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                if (travaTopo) {
                    controleTelaTopo = true;
                } else {
                    controleTelaTopo = false;
                }
            } else {
                travaFrente = listaPrimitivas.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                travaTopo = listaPrimitivas.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                travaLado = listaPrimitivas.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (!travaTopo) {
                    listaPrimitivas.get(0).getMatriz().translacaoXZ(a, b);
                }
                if (travaLado) {
                    controleTelaLado = true;
                } else {
                    controleTelaLado = false;
                }
                if (travaFrente) {
                    controleTelaFrente = true;
                } else {
                    controleTelaFrente = false;
                }
            }
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void transladarPrimitivasZY(int a, int b, int altura, int largura) {
        if (isAgrupado) {
            if (travaLado && controleTelaLado) {
                for (int i = 0; i < listaPrimitivas.size(); i++) {
                    listaPrimitivas.get(i).getMatriz().translacaoZY(a, b);
                    travaLado = listaPrimitivas.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaLado) {
                        controleTelaLado = true;
                    } else {
                        controleTelaLado = false;
                    }
                }
            } else {
                for (int i = 0; i < listaPrimitivas.size(); i++) {
                    travaFrente = listaPrimitivas.get(i).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    travaTopo = listaPrimitivas.get(i).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    travaLado = listaPrimitivas.get(i).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaLado) {
                        i = listaPrimitivas.size();
                    }
                    if (travaFrente) {
                        controleTelaFrente = true;
                    } else {
                        controleTelaFrente = false;
                    }
                    if (travaTopo) {
                        controleTelaTopo = true;
                    } else {
                        controleTelaTopo = false;
                    }
                }
                if (!travaLado) {
                    for (int i = 0; i < listaPrimitivas.size(); i++) {
                        listaPrimitivas.get(i).getMatriz().translacaoZY(a, b);
                    }
                }
            }
        } else {
            if (travaLado && controleTelaLado) {
                listaPrimitivas.get(0).getMatriz().translacaoZY(a, b);
                travaLado = listaPrimitivas.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (travaLado) {
                    controleTelaLado = true;
                } else {
                    controleTelaLado = false;
                }
            } else {
                travaFrente = listaPrimitivas.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                travaTopo = listaPrimitivas.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                travaLado = listaPrimitivas.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (!travaLado) {
                    listaPrimitivas.get(0).getMatriz().translacaoZY(a, b);
                }
                if (travaFrente) {
                    controleTelaFrente = true;
                } else {
                    controleTelaFrente = false;
                }
                if (travaTopo) {
                    controleTelaTopo = true;
                } else {
                   controleTelaTopo = false; 
                }
            }
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void transladarPrimitivasXY(int a, int b) {
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            listaPrimitivas.get(i).getMatriz().translacaoXY(a, b);
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void transladarPrimitivasXZ(int a, int b) {
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            listaPrimitivas.get(i).getMatriz().translacaoXZ(a, b);
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void transladarPrimitivasZY(int a, int b) {
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            listaPrimitivas.get(i).getMatriz().translacaoZY(a, b);
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void escalaZ(float escala) {
        this.fatorEscalaZ = fatorEscalaZ + escala;
        if (!isAgrupado) {
            listaPrimitivas.get(0).getMatriz().escalaZ(escala);
            //listaPrimitivas.get(0).getMatriz().escalaZ(escala, centroX, centroY, centroZ);
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void rotacaoZ(int ang, double sen, double cos) {
        this.anguloRotacaoZ = anguloRotacaoZ + ang;
        if (anguloRotacaoZ + ang >= 360) {
            this.anguloRotacaoZ = anguloRotacaoZ - 360;
        } else {
            if (anguloRotacaoZ + ang <= -360) {
                this.anguloRotacaoZ = anguloRotacaoZ + 360;
            }
        }
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            listaPrimitivas.get(i).getMatriz().rotacaoZ(ang, sen, cos, centroX, centroY);
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void rotacaoY(int ang, double sen, double cos) {
        this.anguloRotacaoY = anguloRotacaoY + ang;
        if (anguloRotacaoY + ang >= 360) {
            this.anguloRotacaoY = anguloRotacaoY - 360;
        } else {
            if (anguloRotacaoY + ang <= -360) {
                this.anguloRotacaoY = anguloRotacaoY + 360;
            }
        }
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            listaPrimitivas.get(i).getMatriz().rotacaoY(ang, sen, cos, centroX, centroZ);
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    private void rotacionarX(int ang, double sen, double cos) {
        if (anguloRotacaoX >= 360) {
            this.anguloRotacaoX = anguloRotacaoX - 360;
        } else {
            if (anguloRotacaoX <= -360) {
                this.anguloRotacaoX = anguloRotacaoX + 360;
            }
        }
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            listaPrimitivas.get(i).getMatriz().rotacaoX(ang, sen, cos, centroY, centroZ);
        }
        calculoCentroGeometrico();
        defineFatores();
    }
    
    public void rotacaoX(int ang, double sen, double cos) {
        this.anguloRotacaoX = anguloRotacaoX + ang;
        rotacionarX(ang, sen, cos);
    }
    
    /*public void rotacaoXsetado (int ang, double sen, double cos) {
     this.anguloRotacaoX = ang;
     rotacionarX(ang, sen, cos);
     }*/
    
    public double distanciaCentroPontoXYZ(double c1, double c2, double c3) {
        double x = centroX - c1;
        x = x * x;
        double y = centroY - c2;
        y = y * y;
        double z = centroZ - c3;
        z = z * z;
        double d = x + y + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaCentroPontoXY(double c1, double c2) {
        double x = centroX - c1;
        x = x * x;
        double y = centroY - c2;
        y = y * y;
        double d = x + y;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaCentroPontoXZ(double c1, double c2) {
        double x = centroX - c1;
        x = x * x;
        double z = centroZ - c2;
        z = z * z;
        double d = x + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaCentroPontoYZ(double c1, double c2) {
        double y = centroY - c1;
        y = y * y;
        double z = centroZ - c2;
        z = z * z;
        double d = y + z;
        d = Math.sqrt(d);
        return d;
    }
    
    public void removerTodasPrimitivas() {
        int i = listaPrimitivas.size() - 1;
        while (!listaPrimitivas.isEmpty()) {
            listaPrimitivas.remove(i);
            i--;
        }
    }
    
    public void defineAgrupado() {
        isAgrupado = listaPrimitivas.size() > 1;
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

    public void adicionarPrimitiva(Primitiva c) {
        listaPrimitivas.add(c);
        calculoCentroGeometrico();
        defineFatores();
    }

    public ArrayList<Primitiva> getListaPrimitivas() {
        return listaPrimitivas;
    }

    public Primitiva getPrimitiva() {
        return listaPrimitivas.get(0);
    }

    public void setListaPrimitivas(ArrayList<Primitiva> lista) {
        this.listaPrimitivas = lista;
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
        return listaPrimitivas.size();
    }

    public void setarAgrupamentoPrimitivas() {
        if (isAgrupado()) {
            for (int i = 0; i < listaPrimitivas.size(); i++) {
                listaPrimitivas.get(i).setAgrupado(true);
            }
        } else {
            listaPrimitivas.get(0).setAgrupado(false);
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
