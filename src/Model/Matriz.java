package Model;

import java.io.Serializable;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Matriz implements Serializable {

    private double M[][];
    private long ID;
    private final int tam = 30;
    private int extremoCima, extremoBaixo, extremoEsquerda, extremoDireita, extremoFrente, extremoTras;
    private float fatorEscalaZ, fatorTraslacaoX, fatorTraslacaoY, fatorTraslacaoZ;
    private int anguloRotacaoX, anguloRotacaoY, anguloRotacaoZ;

    public Matriz(int centroX, int centroY, int centroZ, long id, int altura, int largura) {
        this.ID = id;
        if ((centroX - tam) <= 0) {
            centroX = centroX + tam;
        }
        if ((centroX + tam) >= largura) {
            centroX = centroX - tam;
        }
        if ((centroY - tam) <= 0) {
            centroY = centroY + tam;
        }
        if ((centroY + tam) >= altura) {
            centroY = centroY - tam;
        }
        if ((centroZ - tam) <= 0) {
            centroZ = centroZ + tam;
        }
        if (((centroZ + tam) >= altura) || ((centroZ + tam) >= largura)) {
            centroZ = centroZ - tam;
        }
        M = new double[4][9];
        //Ponto P1
        M[0][0] = centroX - tam;
        M[1][0] = centroY - tam;
        M[2][0] = centroZ + tam;
        M[3][0] = 1;
        //Ponto P2
        M[0][1] = centroX + tam;
        M[1][1] = centroY - tam;
        M[2][1] = centroZ + tam;
        M[3][1] = 1;
        //Ponto P3
        M[0][2] = centroX + tam;
        M[1][2] = centroY + tam;
        M[2][2] = centroZ + tam;
        M[3][2] = 1;
        //Ponto P4
        M[0][3] = centroX - tam;
        M[1][3] = centroY + tam;
        M[2][3] = centroZ + tam;
        M[3][3] = 1;
        //Ponto P5
        M[0][4] = centroX - tam;
        M[1][4] = centroY - tam;
        M[2][4] = centroZ - tam;
        M[3][4] = 1;
        //Ponto P6
        M[0][5] = centroX + tam;
        M[1][5] = centroY - tam;
        M[2][5] = centroZ - tam;
        M[3][5] = 1;
        //Ponto P7
        M[0][6] = centroX + tam;
        M[1][6] = centroY + tam;
        M[2][6] = centroZ - tam;
        M[3][6] = 1;
        //Ponto P8
        M[0][7] = centroX - tam;
        M[1][7] = centroY + tam;
        M[2][7] = centroZ - tam;
        M[3][7] = 1;
        //Centro Geométrico
        /*M[0][8] = 0;
         M[1][8] = 0;
         M[2][8] = 0;
         for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 8; j++) {
         M[i][8] = M[i][8] + M[i][j];
         }  
         }
         M[0][8] = M[0][8] / 8;
         M[1][8] = M[1][8] / 8;
         M[2][8] = M[2][8] / 8;
         M[3][8] = 1;*/

        M[0][8] = centroX;
        M[1][8] = centroY;
        M[2][8] = centroZ;
        M[3][8] = 1;
        fatorTraslacaoX = fatorTraslacaoY = fatorTraslacaoZ = 0;
        anguloRotacaoX = anguloRotacaoY = anguloRotacaoZ = 0;
        fatorEscalaZ = (float) 1.0;

        extremoCima = 999999999;
        extremoBaixo = -999999999;
        extremoEsquerda = 999999999;
        extremoDireita = -999999999;
        extremoTras = 999999999;
        extremoFrente = -999999999;
        calculoExtremos();
    }

    public void refazerMatriz(double centroX, double centroY, double centroZ) {
        M = new double[4][9];
        //Ponto P1
        M[0][0] = centroX - tam;
        M[1][0] = centroY - tam;
        M[2][0] = centroZ + tam;
        M[3][0] = 1;
        //Ponto P2
        M[0][1] = centroX + tam;
        M[1][1] = centroY - tam;
        M[2][1] = centroZ + tam;
        M[3][1] = 1;
        //Ponto P3
        M[0][2] = centroX + tam;
        M[1][2] = centroY + tam;
        M[2][2] = centroZ + tam;
        M[3][2] = 1;
        //Ponto P4
        M[0][3] = centroX - tam;
        M[1][3] = centroY + tam;
        M[2][3] = centroZ + tam;
        M[3][3] = 1;
        //Ponto P5
        M[0][4] = centroX - tam;
        M[1][4] = centroY - tam;
        M[2][4] = centroZ - tam;
        M[3][4] = 1;
        //Ponto P6
        M[0][5] = centroX + tam;
        M[1][5] = centroY - tam;
        M[2][5] = centroZ - tam;
        M[3][5] = 1;
        //Ponto P7
        M[0][6] = centroX + tam;
        M[1][6] = centroY + tam;
        M[2][6] = centroZ - tam;
        M[3][6] = 1;
        //Ponto P8
        M[0][7] = centroX - tam;
        M[1][7] = centroY + tam;
        M[2][7] = centroZ - tam;
        M[3][7] = 1;
        //Centro Geométrico
        /*M[0][8] = 0;
         M[1][8] = 0;
         M[2][8] = 0;
         for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 8; j++) {
         M[i][8] = M[i][8] + M[i][j];
         }  
         }
         M[0][8] = M[0][8] / 8;
         M[1][8] = M[1][8] / 8;
         M[2][8] = M[2][8] / 8;
         M[3][8] = 1;*/

        M[0][8] = centroX;
        M[1][8] = centroY;
        M[2][8] = centroZ;
        M[3][8] = 1;
        fatorTraslacaoX = fatorTraslacaoY = fatorTraslacaoZ = 0;
        anguloRotacaoX = anguloRotacaoY = anguloRotacaoZ = 0;
        fatorEscalaZ = 1;

        extremoCima = 999999999;
        extremoBaixo = -999999999;
        extremoEsquerda = 999999999;
        extremoDireita = -999999999;
        extremoTras = 999999999;
        extremoFrente = -999999999;
        calculoExtremos();
    }

    public Matriz(double[][] m, int op) {
        M = new double[4][9];
        if (op == 1) { //cria matriz perspectiva
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    M[i][j] = m[i][j];
                }
            }
        }
        if (op == 2) { //cria matriz isometrica
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    M[i][j] = m[i][j];
                }
            }
        }
    }

    public void homogeneizar() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                M[j][i] = M[j][i] / M[3][i];
            }
        }
        //adaptar();
        /*for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");*/
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double[][] getMatriz() {
        return M;
    }

    public void imprimeMatrizPontos() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public double distanciaDoisPontosXYZ(int p0, int p1) {
        double x = M[0][p1] - M[0][p0];
        x = x * x;
        double y = M[1][p1] - M[1][p0];
        y = y * y;
        double z = M[2][p1] - M[2][p0];
        z = z * z;
        double d = x + y + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaDoisPontosXY(int p0, int p1) {
        double x = M[0][p1] - M[0][p0];
        x = x * x;
        double y = M[1][p1] - M[1][p0];
        y = y * y;
        double d = x + y;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaDoisPontosXZ(int p0, int p1) {
        double x = M[0][p1] - M[0][p0];
        x = x * x;
        double z = M[2][p1] - M[2][p0];
        z = z * z;
        double d = x + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaDoisPontosYZ(int p0, int p1) {
        double y = M[1][p1] - M[1][p0];
        y = y * y;
        double z = M[2][p1] - M[2][p0];
        z = z * z;
        double d = y + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaDoisPontosXYZ(int p, int c1, int c2, int c3) {
        double x = M[0][p] - c1;
        x = x * x;
        double y = M[1][p] - c2;
        y = y * y;
        double z = M[2][p] - c3;
        z = z * z;
        double d = x + y + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaDoisPontosXY(int p, int c1, int c2) {
        double x = M[0][p] - c1;
        x = x * x;
        double y = M[1][p] - c2;
        y = y * y;
        double d = x + y;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaDoisPontosXZ(int p, int c1, int c2) {
        double x = M[0][p] - c1;
        x = x * x;
        double z = M[2][p] - c2;
        z = z * z;
        double d = x + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaDoisPontosYZ(int p, int c1, int c2) {
        double y = M[1][p] - c1;
        y = y * y;
        double z = M[2][p] - c2;
        z = z * z;
        double d = y + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaCentroPontoXYZ(int c1, int c2, int c3) {
        double x = M[0][8] - c1;
        x = x * x;
        double y = M[1][8] - c2;
        y = y * y;
        double z = M[2][8] - c3;
        z = z * z;
        double d = x + y + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaCentroPontoXY(int c1, int c2) {
        double x = M[0][8] - c1;
        x = x * x;
        double y = M[1][8] - c2;
        y = y * y;
        double d = x + y;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaCentroPontoXZ(int c1, int c2) {
        double x = M[0][8] - c1;
        x = x * x;
        double z = M[2][8] - c2;
        z = z * z;
        double d = x + z;
        d = Math.sqrt(d);
        return d;
    }

    public double distanciaCentroPontoYZ(int c1, int c2) {
        double y = M[1][8] - c1;
        y = y * y;
        double z = M[2][8] - c2;
        z = z * z;
        double d = y + z;
        d = Math.sqrt(d);
        return d;
    }

    public int getAnguloRotacaoX() {
        return anguloRotacaoX;
    }

    public void setAnguloRotacaoX(int angulo) {
        this.anguloRotacaoX = angulo;
    }

    public int getAnguloRotacaoY() {
        return anguloRotacaoY;
    }

    public void setAnguloRotacaoY(int angulo) {
        this.anguloRotacaoY = angulo;
    }

    public int getAnguloRotacaoZ() {
        return anguloRotacaoZ;
    }

    public void setAnguloRotacaoZ(int angulo) {
        this.anguloRotacaoZ = angulo;
    }

    public float getFatorEscalaZ() {
        return fatorEscalaZ;
    }

    public void setFatorEscalaZ(float fator) {
        this.fatorEscalaZ = fator;
    }

    public float getFatorTraslacaoX() {
        return fatorTraslacaoX;
    }

    public void setFatorTraslacaoX(float fatorX) {
        this.fatorTraslacaoX = fatorX;
    }

    public float getFatorTraslacaoY() {
        return fatorTraslacaoY;
    }

    public void setFatorTraslacaoY(float fatorY) {
        this.fatorTraslacaoY = fatorY;
    }

    public float getFatorTraslacaoZ() {
        return fatorTraslacaoZ;
    }

    public void setFatorTraslacaoZ(float fatorZ) {
        this.fatorTraslacaoZ = fatorZ;
    }

    public int getExtremoCima() {
        return extremoCima;
    }

    public void setExtremoCima(int Cima) {
        this.extremoCima = Cima;
    }

    public int getExtremoBaixo() {
        return extremoBaixo;
    }

    public void setExtremoBaixo(int Baixo) {
        this.extremoBaixo = Baixo;
    }

    public int getExtremoEsquerda() {
        return extremoEsquerda;
    }

    public void setExtremoEsquerda(int Esquerda) {
        this.extremoEsquerda = Esquerda;
    }

    public int getExtremoDireita() {
        return extremoDireita;
    }

    public void setExtremoDireita(int Direita) {
        this.extremoDireita = Direita;
    }

    public int getExtremoFrente() {
        return extremoFrente;
    }

    public void setExtremoFrente(int Frente) {
        this.extremoFrente = Frente;
    }

    public int getExtremoTras() {
        return extremoTras;
    }

    public void setExtremoTras(int Tras) {
        this.extremoTras = Tras;
    }

    public void calculoExtremos() {
        extremoCima = 999999999;
        extremoBaixo = -999999999;
        extremoEsquerda = 999999999;
        extremoDireita = -999999999;
        extremoTras = 999999999;
        extremoFrente = -999999999;
        for (int i = 0; i < 8; i++) {
            //Extremos na ordenada X
            if (M[0][i] > extremoDireita) {
                extremoDireita = (int) M[0][i];
            }
            if (M[0][i] < extremoEsquerda) {
                extremoEsquerda = (int) M[0][i];
            }
            //Extremos na ordenada Y
            if (M[1][i] > extremoBaixo) {
                extremoBaixo = (int) M[1][i];
            }
            if (M[1][i] < extremoCima) {
                extremoCima = (int) M[1][i];
            }
            //Extremos na ordenada Z
            if (M[2][i] > extremoFrente) {
                extremoFrente = (int) M[2][i];
            }
            if (M[2][i] < extremoTras) {
                extremoTras = (int) M[2][i];
            }
        }
        extremoBaixo++;
        extremoCima--;
        extremoDireita++;
        extremoEsquerda--;
        extremoFrente++;
        extremoTras--;
    }

    public void calculoCentro() {
        M[0][8] = (extremoDireita + extremoEsquerda) / 2;
        M[1][8] = (extremoCima + extremoBaixo) / 2;
        M[1][8] = (extremoFrente + extremoTras) / 2;
    }

    /*public boolean calculoMovimentaExtremoMatrizXY (int altura, int largura) {
     if ()
     }
    
     public boolean calculoMovimentaExtremoMatrizXZ (int altura, int largura) {
        
     }
    
     public boolean calculoMovimentaExtremoMatrizZY (int altura, int largura) {
        
     }*/
    public void translacaoXY(double fatorX, double fatorY) {
        for (int i = 0; i < 9; i++) {
            M[0][i] = M[0][i] + fatorX;
            M[1][i] = M[1][i] + fatorY;
        }
        calculoExtremos();
    }

    public boolean travaTranslacaoXY(double fatorX, double fatorY, int altura, int largura) {
        double x, y;
        for (int i = 0; i < 9; i++) {
            x = M[0][i] + fatorX;
            y = M[1][i] + fatorY;
            if (x < 0 || y < 0 || x > largura || y > altura) {
                return true;
            }
        }
        /*for (int i = 0; i < 9; i++) {
         M[0][i] = M[0][i] + fatorX;
         M[1][i] = M[1][i] + fatorY;
         }
         calculoExtremos();*/
        return false;
    }

    public void translacaoXZ(double fatorX, double fatorZ) {
        for (int i = 0; i < 9; i++) {
            M[0][i] = M[0][i] + fatorX;
            M[2][i] = M[2][i] + fatorZ;
        }
        calculoExtremos();
    }

    public boolean travaTranslacaoXZ(double fatorX, double fatorZ, int altura, int largura) {
        double x, z;
        for (int i = 0; i < 9; i++) {
            x = M[0][i] + fatorX;
            z = M[2][i] + fatorZ;
            if (x < 0 || z < 0 || x > largura || z > altura) {
                return true;
            }
        }
        /*for (int i = 0; i < 9; i++) {
         M[0][i] = M[0][i] + fatorX;
         M[2][i] = M[2][i] + fatorZ;
         }
         calculoExtremos();*/
        return false;
    }

    public void translacaoZY(double fatorZ, double fatorY) {
        for (int i = 0; i < 9; i++) {
            M[1][i] = M[1][i] + fatorY;
            M[2][i] = M[2][i] + fatorZ;
        }
        calculoExtremos();
    }

    public boolean travaTranslacaoZY(double fatorZ, double fatorY, int altura, int largura) {
        double z, y;
        for (int i = 0; i < 9; i++) {
            z = M[1][i] + fatorY;
            y = M[2][i] + fatorZ;
            if (z < 0 || y < 0 || z > altura || y > largura) {
                return true;
            }
        }
        /*for (int i = 0; i < 9; i++) {
         M[1][i] = M[1][i] + fatorY;
         M[2][i] = M[2][i] + fatorZ;
         }
         calculoExtremos();*/
        return false;
    }

    public void translacaoXYZ(double fatorX, double fatorY, double fatorZ) {
        for (int i = 0; i < 9; i++) {
            M[0][i] = M[0][i] + fatorX;
            M[1][i] = M[1][i] + fatorY;
            M[2][i] = M[2][i] + fatorZ;
        }
        calculoExtremos();
    }

    public void escalaZ(float fator) {
        this.fatorEscalaZ = fatorEscalaZ + fator;
        double cx, cy, cz;
        cx = M[0][8];
        cy = M[1][8];
        cz = M[2][8];
        translacaoXYZ(-cx, -cy, -cz);
        for (int i = 0; i < 9; i++) {
            M[2][i] = M[2][i] + M[2][i] * fator;
        }
        translacaoXYZ(cx, cy, cz);
        calculoExtremos();
        calculoCentro();
    }

    /*public void escalaZ (float fator, double cx, double cy, double cz) {
     this.fatorEscalaZ = fator;
     translacaoXYZ(-cx, -cy, -cz);
     for (int i = 0; i < 9; i++) {
     M[2][i] = M[2][i] * fator;            
     }
     translacaoXYZ(cx, cy, cz);
     calculoExtremos();
     calculoCentro();
     }*/
    //Rotação em relação a X ao redor do seu centro geométrico
    public void rotacaoX(int angulo, double sen, double cos) {
        double yAnt, zAnt;
        double cy, cz;
        cz = M[1][8];
        cy = M[2][8];
        //System.out.println("cy " + M[1][8] + " cz " + M[2][8]);
        translacaoZY(-cy, -cz);
        //System.out.println("cy " + M[1][8] + " cz " + M[2][8]);
        this.anguloRotacaoX = anguloRotacaoX + angulo;
        if (anguloRotacaoX + angulo >= 360) {
            this.anguloRotacaoX = anguloRotacaoX - 360;
        } else {
            if (anguloRotacaoX + angulo <= -360) {
                this.anguloRotacaoX = anguloRotacaoX + 360;
            }
        }
        for (int i = 0; i < 9; i++) {
            yAnt = M[1][i];
            zAnt = M[2][i];
            //Ordenadas Y e Z invertidas por conta do painel do java
            M[1][i] = (yAnt * cos) + (zAnt * (-sen));
            M[2][i] = (yAnt * sen) + (zAnt * cos);
            //M[1][i] = (yAnt * sen) + (zAnt * cos);
            //M[2][i] = (yAnt * cos) + (zAnt * (-sen));
        }
        translacaoZY(cy, cz);
        calculoExtremos();
        calculoCentro();
    }

    //Rotação em relação a X ao redor do seu centro geométrico
    public void rotacaoX(int angulo, double sen, double cos, double cz, double cy) {
        double yAnt, zAnt;
        //System.out.println("cy " + M[1][8] + " cz " + M[2][8]);
        translacaoZY(-cy, -cz);
        //System.out.println("cy " + M[1][8] + " cz " + M[2][8]);
        this.anguloRotacaoX = anguloRotacaoX + angulo;
        if (anguloRotacaoX + angulo >= 360) {
            this.anguloRotacaoX = anguloRotacaoX - 360;
        } else {
            if (anguloRotacaoX + angulo <= -360) {
                this.anguloRotacaoX = anguloRotacaoX + 360;
            }
        }
        for (int i = 0; i < 9; i++) {
            yAnt = M[1][i];
            zAnt = M[2][i];
            //Ordenadas Y e Z invertidas por conta do painel do java
            M[1][i] = (yAnt * cos) + (zAnt * (-sen));
            M[2][i] = (yAnt * sen) + (zAnt * cos);
            //M[1][i] = (yAnt * sen) + (zAnt * cos);
            //M[2][i] = (yAnt * cos) + (zAnt * (-sen));
        }
        translacaoZY(cy, cz);
        calculoExtremos();
        calculoCentro();
    }

    //Rotação em relação a Y ao redor do seu centro geométrico
    public void rotacaoY(int angulo, double sen, double cos) {
        double xAnt, zAnt;
        double cx, cz;
        cx = M[0][8];
        cz = M[2][8];
        translacaoXZ(-cx, -cz);
        this.anguloRotacaoY = anguloRotacaoY + angulo;
        if (anguloRotacaoY + angulo >= 360) {
            this.anguloRotacaoY = anguloRotacaoY - 360;
        } else {
            if (anguloRotacaoY + angulo <= -360) {
                this.anguloRotacaoY = anguloRotacaoY + 360;
            }
        }
        for (int i = 0; i < 9; i++) {
            xAnt = M[0][i];
            zAnt = M[2][i];
            M[0][i] = (xAnt * cos) + (zAnt * sen);
            M[2][i] = (xAnt * (-sen)) + (zAnt * cos);
        }
        translacaoXZ(cx, cz);
        calculoExtremos();
        calculoCentro();
    }

    //Rotação em relação a Y ao redor do seu centro geométrico
    public void rotacaoY(int angulo, double sen, double cos, double cx, double cz) {
        double xAnt, zAnt;
        translacaoXZ(-cx, -cz);
        this.anguloRotacaoY = anguloRotacaoY + angulo;
        if (anguloRotacaoY + angulo >= 360) {
            this.anguloRotacaoY = anguloRotacaoY - 360;
        } else {
            if (anguloRotacaoY + angulo <= -360) {
                this.anguloRotacaoY = anguloRotacaoY + 360;
            }
        }
        for (int i = 0; i < 9; i++) {
            xAnt = M[0][i];
            zAnt = M[2][i];
            M[0][i] = (xAnt * cos) + (zAnt * sen);
            M[2][i] = (xAnt * (-sen)) + (zAnt * cos);
        }
        translacaoXZ(cx, cz);
        calculoExtremos();
        calculoCentro();
    }

    //Rotação em relação a Z ao redor do seu centro geométrico
    public void rotacaoZ(int angulo, double sen, double cos) {
        double xAnt, yAnt;
        double cx, cy;
        cx = M[0][8];
        cy = M[1][8];
        translacaoXY(-cx, -cy);
        this.anguloRotacaoZ = anguloRotacaoZ + angulo;
        if (anguloRotacaoZ + angulo >= 360) {
            this.anguloRotacaoZ = anguloRotacaoZ - 360;
        } else {
            if (anguloRotacaoZ + angulo <= -360) {
                this.anguloRotacaoZ = anguloRotacaoZ + 360;
            }
        }
        for (int i = 0; i < 9; i++) {
            xAnt = M[0][i];
            yAnt = M[1][i];
            M[0][i] = (xAnt * cos) + (yAnt * (-sen));
            M[1][i] = (xAnt * sen) + (yAnt * cos);
        }
        translacaoXY(cx, cy);
        calculoExtremos();
        calculoCentro();
    }

    //Rotação em relação a Z ao redor do seu centro geométrico
    public void rotacaoZ(int angulo, double sen, double cos, double cx, double cy) {
        double xAnt, yAnt;
        translacaoXY(-cx, -cy);
        this.anguloRotacaoZ = anguloRotacaoZ + angulo;
        if (anguloRotacaoZ + angulo >= 360) {
            this.anguloRotacaoZ = anguloRotacaoZ - 360;
        } else {
            if (anguloRotacaoZ + angulo <= -360) {
                this.anguloRotacaoZ = anguloRotacaoZ + 360;
            }
        }
        for (int i = 0; i < 9; i++) {
            xAnt = M[0][i];
            yAnt = M[1][i];
            M[0][i] = (xAnt * cos) + (yAnt * (-sen));
            M[1][i] = (xAnt * sen) + (yAnt * cos);
        }
        translacaoXY(cx, cy);
        calculoExtremos();
        calculoCentro();
    }

    public void adaptar() {
        double aux;
        double r;
        double a = 99999999;
        double b = 0;
        for (int i = 0; i < 9; i++) {
            if (M[0][i] < a) {
                a = M[0][i];
                b = M[1][i];
            }
        }
        if (a < 0) {
            a = Math.abs(a);
            b = Math.abs(b);
            for (int i = 0; i < 9; i++) {
                M[0][i] = M[0][i] + a;
                M[1][i] = M[1][i] + b;
            }
        }
        a = 999999999;
        b = 0;
        for (int i = 0; i < 9; i++) {
            if (M[1][i] < a) {
                a = M[1][i];
                b = M[0][i];
            }
        }
        if (a < 0) {
            a = Math.abs(a);
            b = Math.abs(b);
            for (int i = 0; i < 9; i++) {
                M[1][i] = M[1][i] + a;
                M[0][i] = M[0][i] + b;
            }
        }
        /*for (int j = 0; j < 9; j++) {
            if (M[0][j] < 0) {
                aux = -M[0][j];
                r = Math.abs(M[0][j] / M[1][j]);
                M[0][j] = aux;
                M[1][j] = aux + M[1][j] * r;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (M[1][j] < 0) {
                aux = -M[1][j];
                r = Math.abs(M[0][j] / M[1][j]);
                M[1][j] = aux;
                M[0][j] = aux + M[0][j] * r;
            }
        }*/
    }

    /*public Matriz (Cubo c, long id) {
     M = new double[4][9];
     ID = id;
     //Ponto P1
     M[0][0] = c.getF1().getA1().getA().getX();
     M[1][0] = c.getF1().getA1().getA().getY();
     M[2][0] = c.getF1().getA1().getA().getZ();
     M[3][0] = 1;
     //Ponto P2
     M[0][1] = c.getF1().getA1().getB().getX();
     M[1][1] = c.getF1().getA1().getB().getY();
     M[2][1] = c.getF1().getA1().getB().getZ();
     M[3][1] = 1;
     //Ponto P3
     M[0][2] = c.getF1().getA2().getB().getX();
     M[1][2] = c.getF1().getA2().getB().getY();
     M[2][2] = c.getF1().getA2().getB().getZ();
     M[3][2] = 1;
     //Ponto P4
     M[0][3] = c.getF1().getA3().getB().getX();
     M[1][3] = c.getF1().getA3().getB().getY();
     M[2][3] = c.getF1().getA3().getB().getZ();
     M[3][3] = 1;
     //Ponto P5
     M[0][4] = c.getF3().getA1().getA().getX();
     M[1][4] = c.getF3().getA1().getA().getY();
     M[2][4] = c.getF3().getA1().getA().getZ();
     M[3][4] = 1;
     //Ponto P6
     M[0][5] = c.getF3().getA1().getB().getX();
     M[1][5] = c.getF3().getA1().getB().getY();
     M[2][5] = c.getF3().getA1().getB().getZ();
     M[3][5] = 1;
     //Ponto P7
     M[0][6] = c.getF3().getA4().getB().getX();
     M[1][6] = c.getF3().getA4().getB().getY();
     M[2][6] = c.getF3().getA4().getB().getZ();
     M[3][6] = 1;
     //Ponto P8
     M[0][7] = c.getF3().getA3().getB().getX();
     M[1][7] = c.getF3().getA3().getB().getY();
     M[2][7] = c.getF3().getA3().getB().getZ();
     M[3][7] = 1;
     //Centro Geométrico
     M[0][7] = 0;
     M[1][7] = 0;
     M[2][7] = 0;
     M[3][7] = 1;
     }*/
}
