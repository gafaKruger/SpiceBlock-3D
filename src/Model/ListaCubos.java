package Model;

import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class ListaCubos extends ListaPrimitivas {
    //REVER SE OS METODOS ESTAO OK COM A SUPERCLASSE LISTAPRIMITIVAS OU NECESSITAM MODIFICAÇÃO ESPECIALIZADA
    private ArrayList<Cubo> listaCubos;
    
    public ListaCubos() {
        super();
        listaCubos = new ArrayList<>(); 
    }

    public ListaCubos(ArrayList<Cubo> listaCubos) {
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
        this.listaCubos = listaCubos;
        calculoCentroGeometrico();
        defineFatores();
    }

    public void defineFatores() {
        defineAgrupado();
        if (isAgrupado) {
            //fazer os métodos para grudar e distanciar da classe lista
        } else {
            fatorEscalaZ = listaCubos.get(0).getMatriz().getFatorEscalaZ();
            fatorTraslacaoX = listaCubos.get(0).getMatriz().getFatorTraslacaoX();
            fatorTraslacaoY = listaCubos.get(0).getMatriz().getFatorTraslacaoY();
            fatorTraslacaoZ = listaCubos.get(0).getMatriz().getFatorTraslacaoZ();
            anguloRotacaoX = listaCubos.get(0).getMatriz().getAnguloRotacaoX();
            anguloRotacaoY = listaCubos.get(0).getMatriz().getAnguloRotacaoY();
            anguloRotacaoZ = listaCubos.get(0).getMatriz().getAnguloRotacaoZ();
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
            centroX = listaCubos.get(0).getMatriz().getMatriz()[0][8];
            centroY = listaCubos.get(0).getMatriz().getMatriz()[1][8];
            centroZ = listaCubos.get(0).getMatriz().getMatriz()[2][8];
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
            for (int i = 0; i < listaCubos.size(); i++) {
                //Extremos ordenada X
                if (listaCubos.get(i).getMatriz().getExtremoEsquerda() < extremoEsquerda) {
                    extremoEsquerda = listaCubos.get(i).getMatriz().getExtremoEsquerda();
                }
                if (listaCubos.get(i).getMatriz().getExtremoDireita() > extremoDireita) {
                    extremoDireita = listaCubos.get(i).getMatriz().getExtremoDireita();
                }
                //Extremos ordenada Y
                if (listaCubos.get(i).getMatriz().getExtremoCima() < extremoCima) {
                    extremoCima = listaCubos.get(i).getMatriz().getExtremoCima();
                }
                if (listaCubos.get(i).getMatriz().getExtremoBaixo() > extremoBaixo) {
                    extremoBaixo = listaCubos.get(i).getMatriz().getExtremoBaixo();
                }
                //Extremos ordenada Z
                if (listaCubos.get(i).getMatriz().getExtremoTras() < extremoTras) {
                    extremoTras = listaCubos.get(i).getMatriz().getExtremoTras();
                }
                if (listaCubos.get(i).getMatriz().getExtremoFrente() > extremoFrente) {
                    extremoFrente = listaCubos.get(i).getMatriz().getExtremoFrente();
                }
            }
        } else {
            extremoCima = listaCubos.get(0).getMatriz().getExtremoCima();
            extremoBaixo = listaCubos.get(0).getMatriz().getExtremoBaixo();
            extremoEsquerda = listaCubos.get(0).getMatriz().getExtremoEsquerda();
            extremoDireita = listaCubos.get(0).getMatriz().getExtremoDireita();
            extremoTras = listaCubos.get(0).getMatriz().getExtremoTras();
            extremoFrente = listaCubos.get(0).getMatriz().getExtremoFrente();
        }
    }

    public void transladarCubosXY(int a, int b, int altura, int largura) {
        if (isAgrupado) {
            if (travaFrente && controleTelaFrente) {
                for (int i = 0; i < listaCubos.size(); i++) {
                    listaCubos.get(i).getMatriz().translacaoXY(a, b);
                    travaFrente = listaCubos.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    if (travaFrente) {
                        controleTelaFrente = true;
                    } else {
                        controleTelaFrente = false;
                    }
                }
            } else {
                for (int i = 0; i < listaCubos.size(); i++) {
                    travaFrente = listaCubos.get(i).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    travaTopo = listaCubos.get(i).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    travaLado = listaCubos.get(i).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaFrente) {
                        i = listaCubos.size();
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
                    for (int i = 0; i < listaCubos.size(); i++) {
                        listaCubos.get(i).getMatriz().translacaoXY(a, b);
                    }
                }
            }
        } else {
            if (travaFrente && controleTelaFrente) {
                listaCubos.get(0).getMatriz().translacaoXY(a, b);
                travaFrente = listaCubos.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                if (travaFrente) {
                    controleTelaFrente = true;
                } else {
                    controleTelaFrente = false;
                }
            } else {
                travaFrente = listaCubos.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                travaTopo = listaCubos.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                travaLado = listaCubos.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (!travaFrente) {
                    listaCubos.get(0).getMatriz().translacaoXY(a, b);
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

    public void transladarCubosXZ(int a, int b, int altura, int largura) {
        if (isAgrupado) {
            if (travaTopo && controleTelaTopo) {
                for (int i = 0; i < listaCubos.size(); i++) {
                    listaCubos.get(i).getMatriz().translacaoXZ(a, b);
                    travaTopo = listaCubos.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    if (travaTopo) {
                        controleTelaTopo = true;
                    } else {
                        controleTelaTopo = false;
                    }
                }
            } else {
                for (int i = 0; i < listaCubos.size(); i++) {
                    travaFrente = listaCubos.get(i).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    travaTopo = listaCubos.get(i).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    travaLado = listaCubos.get(i).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaTopo) {
                        i = listaCubos.size();
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
                    for (int i = 0; i < listaCubos.size(); i++) {
                        listaCubos.get(i).getMatriz().translacaoXZ(a, b);
                    }
                }
            }
        } else {
            if (travaTopo && controleTelaTopo) {
                listaCubos.get(0).getMatriz().translacaoXZ(a, b);
                travaTopo = listaCubos.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                if (travaTopo) {
                    controleTelaTopo = true;
                } else {
                    controleTelaTopo = false;
                }
            } else {
                travaFrente = listaCubos.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                travaTopo = listaCubos.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                travaLado = listaCubos.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (!travaTopo) {
                    listaCubos.get(0).getMatriz().translacaoXZ(a, b);
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

    public void transladarCubosZY(int a, int b, int altura, int largura) {
        if (isAgrupado) {
            if (travaLado && controleTelaLado) {
                for (int i = 0; i < listaCubos.size(); i++) {
                    listaCubos.get(i).getMatriz().translacaoZY(a, b);
                    travaLado = listaCubos.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaLado) {
                        controleTelaLado = true;
                    } else {
                        controleTelaLado = false;
                    }
                }
            } else {
                for (int i = 0; i < listaCubos.size(); i++) {
                    travaFrente = listaCubos.get(i).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                    travaTopo = listaCubos.get(i).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                    travaLado = listaCubos.get(i).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                    if (travaLado) {
                        i = listaCubos.size();
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
                    for (int i = 0; i < listaCubos.size(); i++) {
                        listaCubos.get(i).getMatriz().translacaoZY(a, b);
                    }
                }
            }
        } else {
            if (travaLado && controleTelaLado) {
                listaCubos.get(0).getMatriz().translacaoZY(a, b);
                travaLado = listaCubos.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (travaLado) {
                    controleTelaLado = true;
                } else {
                    controleTelaLado = false;
                }
            } else {
                travaFrente = listaCubos.get(0).getMatriz().travaTranslacaoXY(a, b, altura, largura);
                travaTopo = listaCubos.get(0).getMatriz().travaTranslacaoXZ(a, b, altura, largura);
                travaLado = listaCubos.get(0).getMatriz().travaTranslacaoZY(a, b, altura, largura);
                if (!travaLado) {
                    listaCubos.get(0).getMatriz().translacaoZY(a, b);
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

    public void transladarCubosXY(int a, int b) {
        for (int i = 0; i < listaCubos.size(); i++) {
            listaCubos.get(i).getMatriz().translacaoXY(a, b);
        }
        calculoCentroGeometrico();
        defineFatores();
    }

    public void transladarCubosXZ(int a, int b) {
        for (int i = 0; i < listaCubos.size(); i++) {
            listaCubos.get(i).getMatriz().translacaoXZ(a, b);
        }
        calculoCentroGeometrico();
        defineFatores();
    }

    public void transladarCubosZY(int a, int b) {
        for (int i = 0; i < listaCubos.size(); i++) {
            listaCubos.get(i).getMatriz().translacaoZY(a, b);
        }
        calculoCentroGeometrico();
        defineFatores();
    }

    public void escalaZ(float escala) {
        this.fatorEscalaZ = fatorEscalaZ + escala;
        if (!isAgrupado) {
            listaCubos.get(0).getMatriz().escalaZ(escala);
            //listaCubos.get(0).getMatriz().escalaZ(escala, centroX, centroY, centroZ);
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
        for (int i = 0; i < listaCubos.size(); i++) {
            listaCubos.get(i).getMatriz().rotacaoZ(ang, sen, cos, centroX, centroY);
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
        for (int i = 0; i < listaCubos.size(); i++) {
            listaCubos.get(i).getMatriz().rotacaoY(ang, sen, cos, centroX, centroZ);
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
        for (int i = 0; i < listaCubos.size(); i++) {
            listaCubos.get(i).getMatriz().rotacaoX(ang, sen, cos, centroY, centroZ);
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
    
    public void adicionarCubo(Cubo c) {
        listaCubos.add(c);
        calculoCentroGeometrico();
        defineFatores();
    }

    public ArrayList<Cubo> getListaCubos() {
        return listaCubos;
    }

    public Cubo getCubo() {
        return listaCubos.get(0);
    }

    public void setListaCubos(ArrayList<Cubo> lista) {
        this.listaCubos = lista;
    }
    
    public void removerTodosCubos() {
        int i = listaCubos.size() - 1;
        while (!listaCubos.isEmpty()) {
            listaCubos.remove(i);
            i--;
        }
    }

    public void setarAgrupamentoCubos() {
        if (isAgrupado()) {
            for (int i = 0; i < listaCubos.size(); i++) {
                listaCubos.get(i).setAgrupado(true);
            }
        } else {
            listaCubos.get(0).setAgrupado(false);
        }
    }
}
