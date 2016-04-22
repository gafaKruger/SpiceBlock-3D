package View;

import Model.Cubo;
import Model.Face;
import Model.ListaCubos;
import Model.Matriz;
import Model.Ponto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class PainelExtendido extends JPanel {

    private Graphics g;
    private ArrayList<ListaCubos> cubosPossiveis = new ArrayList<>();

    public PainelExtendido() {
    }

    public void desenharVisaoTopo(ArrayList<ListaCubos> listaCubos, boolean ocultarFaces) {
        g = getGraphics();
        if (ocultarFaces) {
            visibilidadeFacesTopo(listaCubos);
            /*int tam = listaCubos.size();
             int pos, count;
             double valor[][] = new double[6][8];
             double aux;
             boolean F1, F2, F3, F4, F5, F6;
             F1 = F2 = F3 = F4 = F5 = F6 = true;
             for (int i = 0; i < tam; i++) {
             aux = -1;

             }*/
        } else {
            for (ListaCubos cubo : listaCubos) {
                for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                    g.setColor(cubo.getListaCubos().get(i).getCorBordas());
                    desenhoFaceTopo(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceTopo(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceTopo(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceTopo(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceTopo(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceTopo(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatriz());
                }
            }
        }
    }

    public void desenharVisaoLadoEsquerdo(ArrayList<ListaCubos> listaCubos, boolean ocultarFaces) {
        g = getGraphics();
        if (ocultarFaces) {
            visibilidadeFacesLado(listaCubos);
        } else {
            for (ListaCubos cubo : listaCubos) {
                for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                    g.setColor(cubo.getListaCubos().get(i).getCorBordas());
                    desenhoFaceLado(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceLado(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceLado(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceLado(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceLado(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceLado(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatriz());
                }
            }
        }
    }

    public void desenharVisaoFrente(ArrayList<ListaCubos> listaCubos, boolean ocultarFaces) {
        g = getGraphics();
        if (ocultarFaces) {
            visibilidadeFacesFrente(listaCubos);
        } else {
            for (ListaCubos cubo : listaCubos) {
                for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                    g.setColor(cubo.getListaCubos().get(i).getCorBordas());
                    desenhoFaceFrente(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceFrente(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceFrente(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceFrente(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceFrente(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatriz());
                    desenhoFaceFrente(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatriz());
                }
            }
        }
    }

    public void desenharVisaoPerspectiva(ArrayList<ListaCubos> listaCubos, boolean ocultarFaces, Ponto VRP, Ponto P, int dp) {
        g = getGraphics();
        //Assumindo que Zprp = 0, então -Zvp = dp;
        double[][] srusrc = new double[4][4];
        double[][] p = new double[4][4];
        double[][] m = new double[4][4];
        p[0][0] = 1;
        p[0][1] = 0;
        p[0][2] = 0;
        p[0][3] = 0;

        p[1][0] = 0;
        p[1][1] = 1;
        p[1][2] = 0;
        p[1][3] = 0;

        p[2][0] = 0;
        p[2][1] = 0;
        p[2][2] = 1;
        p[2][3] = 0;

        p[3][0] = 0;
        p[3][1] = 0;
        p[3][2] = (double) -1 / dp;
        //System.out.println(p[3][2]);
        //System.out.println("");
        p[3][3] = 0;
        srusrc = SRUSRC(VRP, P);
        //transformar para coordenadas de tela SRC->SRT
        //trocar a variavel srusrc por srusrt no trecho de codigo abaixo
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                m = multiplicaMatrizes(srusrc, cubo.getListaCubos().get(i).getMatriz().getMatriz(), 4, 9);
                m = multiplicaMatrizes(p, m, 4, 9);
                Matriz mpers = new Matriz(m, 1);
                mpers.homogeneizar();
                cubo.getListaCubos().get(i).setMatrizPerspectiva(mpers);
            }
        }
        if (ocultarFaces) {
            visibilidadesFacesPerspectiva(listaCubos);
        } else {
            for (ListaCubos cubo : listaCubos) {
                for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                    g.setColor(cubo.getListaCubos().get(i).getCorBordas());
                    desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                    desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                    desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                    desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                    desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                    desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                }
            }
        }
    }

    public void desenharVisaoIsometrica(ArrayList<ListaCubos> listaCubos, boolean ocultarFaces, Ponto VRP, Ponto P) {
        g = getGraphics();
        double[][] srusrc = new double[4][4];
        double[][] m = new double[4][4];
        srusrc = SRUSRC(VRP, P);
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                m = multiplicaMatrizes(srusrc, cubo.getListaCubos().get(i).getMatriz().getMatriz(), 4, 9);
                Matriz mIso = new Matriz(m, 1);
                mIso.adaptar();
                cubo.getListaCubos().get(i).setMatrizIsometrica(mIso);
            }
        }
        if (ocultarFaces) {
            visibilidadeFacesIsometrica(listaCubos);
        } else {
            for (ListaCubos cubo : listaCubos) {
                for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                    g.setColor(cubo.getListaCubos().get(i).getCorBordas());
                    desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                }
            }
        }
    }

    public double[][] SRUSRC(Ponto VRP, Ponto P) {
        //Adotando Y = (0, 1, 0)
        Ponto N = new Ponto();
        Ponto V;
        Ponto U;
        double[][] srusrc = new double[4][4];
        double aux, i, j, k;

        N = N.Subtrai(VRP, P);
        N.normalizar();

        aux = N.getY();
        V = new Ponto(-(aux * N.getX()), 1 - (aux * N.getY()), -(aux * N.getZ()));
        V.normalizar();

        i = (V.getY() * N.getZ()) - (V.getZ() * N.getY());
        j = (V.getZ() * N.getX()) - (V.getX() * N.getZ());
        k = (V.getX() * N.getY()) - (V.getY() * N.getX());
        U = new Ponto(i, j, k);

        srusrc[0][0] = U.getX();
        srusrc[0][1] = U.getY();
        srusrc[0][2] = U.getZ();
        //srusrc[0][3] = (U.getX()*(-VRP.getX())) + (U.getY()*(-VRP.getY())) + (U.getZ()*(-VRP.getZ()));
        srusrc[0][3] = 0;

        srusrc[1][0] = V.getX();
        srusrc[1][1] = V.getY();
        srusrc[1][2] = V.getZ();
        //srusrc[1][3] = (V.getX()*(-VRP.getX())) + (V.getY()*(-VRP.getY())) + (V.getZ()*(-VRP.getZ()));
        srusrc[1][3] = 0;

        srusrc[2][0] = N.getX();
        srusrc[2][1] = N.getY();
        srusrc[2][2] = N.getZ();
        srusrc[2][3] = (N.getX() * (-VRP.getX())) + (N.getY() * (-VRP.getY())) + (N.getZ() * (-VRP.getZ()));

        srusrc[3][0] = 0;
        srusrc[3][1] = 0;
        srusrc[3][2] = 0;
        srusrc[3][3] = 1;

        /*srusrc[0][0] = 1;
        srusrc[0][1] = 0;
        srusrc[0][2] = 0;
        //srusrc[0][3] = (U.getX()*(-VRP.getX())) + (U.getY()*(-VRP.getY())) + (U.getZ()*(-VRP.getZ()));
        srusrc[0][3] = 0;

        srusrc[1][0] = 0;
        srusrc[1][1] = 1;
        srusrc[1][2] = 0;
        //srusrc[1][3] = (V.getX()*(-VRP.getX())) + (V.getY()*(-VRP.getY())) + (V.getZ()*(-VRP.getZ()));
        srusrc[1][3] = 0;

        srusrc[2][0] = 0;
        srusrc[2][1] = 0;
        srusrc[2][2] = 1;
        srusrc[2][3] = (-VRP.getX()) + (-VRP.getY()) + (-VRP.getZ());

        srusrc[3][0] = 0;
        srusrc[3][1] = 0;
        srusrc[3][2] = 0;
        srusrc[3][3] = 1;*/

        /*for (int l = 0; l < 4; l++) {
         for (int m = 0; m < 4; m++) {
         System.out.print(srusrc[l][m] + " ");
         }
         System.out.println("");
         }
         System.out.println("");  */
        return srusrc;
    }

    public double[][] multiplicaMatrizes(double[][] m1, double[][] m2, int linhas, int colunas) {
        double[][] mAux = new double[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                mAux[i][j] = 0;
            }
        }
        for (int i = 0; i < colunas; i++) {
            for (int j = 0; j < linhas; j++) {
                for (int k = 0; k < linhas; k++) {
                    mAux[j][i] = mAux[j][i] + m1[j][k] * m2[k][i];
                    //System.out.println("m1 l: " + j + " m1 c: " + k + " m2 l: " + k + " m2 c: " + i);
                }
                //System.out.println("");
            }
        }
        /*for (int i = 0; i < linhas; i++) {
         for (int j = 0; j < colunas; j++) {
         System.out.print(mAux[i][j] + " ");
         }
         System.out.println("");
         }
         System.out.println("");*/
        return mAux;
    }

    public void desenhoFaceFrente(Face f, Matriz m) {
        g.drawLine((int) m.getMatriz()[0][f.getA1().getP1()], (int) m.getMatriz()[1][f.getA1().getP1()],
                (int) m.getMatriz()[0][f.getA1().getP2()], (int) m.getMatriz()[1][f.getA1().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA2().getP1()], (int) m.getMatriz()[1][f.getA2().getP1()],
                (int) m.getMatriz()[0][f.getA2().getP2()], (int) m.getMatriz()[1][f.getA2().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA3().getP1()], (int) m.getMatriz()[1][f.getA3().getP1()],
                (int) m.getMatriz()[0][f.getA3().getP2()], (int) m.getMatriz()[1][f.getA3().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA4().getP1()], (int) m.getMatriz()[1][f.getA4().getP1()],
                (int) m.getMatriz()[0][f.getA4().getP2()], (int) m.getMatriz()[1][f.getA4().getP2()]);
    }

    public void desenhoFaceTopo(Face f, Matriz m) {
        g.drawLine((int) m.getMatriz()[0][f.getA1().getP1()], (int) m.getMatriz()[2][f.getA1().getP1()],
                (int) m.getMatriz()[0][f.getA1().getP2()], (int) m.getMatriz()[2][f.getA1().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA2().getP1()], (int) m.getMatriz()[2][f.getA2().getP1()],
                (int) m.getMatriz()[0][f.getA2().getP2()], (int) m.getMatriz()[2][f.getA2().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA3().getP1()], (int) m.getMatriz()[2][f.getA3().getP1()],
                (int) m.getMatriz()[0][f.getA3().getP2()], (int) m.getMatriz()[2][f.getA3().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA4().getP1()], (int) m.getMatriz()[2][f.getA4().getP1()],
                (int) m.getMatriz()[0][f.getA4().getP2()], (int) m.getMatriz()[2][f.getA4().getP2()]);
    }

    public void desenhoFaceLado(Face f, Matriz m) {
        g.drawLine((int) m.getMatriz()[2][f.getA1().getP1()], (int) m.getMatriz()[1][f.getA1().getP1()],
                (int) m.getMatriz()[2][f.getA1().getP2()], (int) m.getMatriz()[1][f.getA1().getP2()]);

        g.drawLine((int) m.getMatriz()[2][f.getA2().getP1()], (int) m.getMatriz()[1][f.getA2().getP1()],
                (int) m.getMatriz()[2][f.getA2().getP2()], (int) m.getMatriz()[1][f.getA2().getP2()]);

        g.drawLine((int) m.getMatriz()[2][f.getA3().getP1()], (int) m.getMatriz()[1][f.getA3().getP1()],
                (int) m.getMatriz()[2][f.getA3().getP2()], (int) m.getMatriz()[1][f.getA3().getP2()]);

        g.drawLine((int) m.getMatriz()[2][f.getA4().getP1()], (int) m.getMatriz()[1][f.getA4().getP1()],
                (int) m.getMatriz()[2][f.getA4().getP2()], (int) m.getMatriz()[1][f.getA4().getP2()]);
    }

    private void valoresPontosFaces(Face f, Matriz m, int a, int b) {
        System.out.println((int) m.getMatriz()[a][f.getA1().getP1()] + " " + (int) m.getMatriz()[b][f.getA1().getP1()]
                + " " + (int) m.getMatriz()[a][f.getA1().getP2()] + " " + (int) m.getMatriz()[b][f.getA1().getP2()]);

        System.out.println((int) m.getMatriz()[a][f.getA2().getP1()] + " " + (int) m.getMatriz()[b][f.getA2().getP1()]
                + " " + (int) m.getMatriz()[a][f.getA2().getP2()] + " " + (int) m.getMatriz()[b][f.getA2().getP2()]);

        System.out.println((int) m.getMatriz()[a][f.getA3().getP1()] + " " + (int) m.getMatriz()[b][f.getA3().getP1()]
                + " " + (int) m.getMatriz()[a][f.getA3().getP2()] + " " + (int) m.getMatriz()[b][f.getA3().getP2()]);

        System.out.println((int) m.getMatriz()[a][f.getA4().getP1()] + " " + (int) m.getMatriz()[b][f.getA4().getP1()]
                + " " + (int) m.getMatriz()[a][f.getA4().getP2()] + " " + (int) m.getMatriz()[b][f.getA4().getP2()]);
    }

    public void desenhoFacePerspectiva(Face f, Matriz m) {
        g.drawLine((int) m.getMatriz()[0][f.getA1().getP1()], (int) m.getMatriz()[1][f.getA1().getP1()],
                (int) m.getMatriz()[0][f.getA1().getP2()], (int) m.getMatriz()[1][f.getA1().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA2().getP1()], (int) m.getMatriz()[1][f.getA2().getP1()],
                (int) m.getMatriz()[0][f.getA2().getP2()], (int) m.getMatriz()[1][f.getA2().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA3().getP1()], (int) m.getMatriz()[1][f.getA3().getP1()],
                (int) m.getMatriz()[0][f.getA3().getP2()], (int) m.getMatriz()[1][f.getA3().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA4().getP1()], (int) m.getMatriz()[1][f.getA4().getP1()],
                (int) m.getMatriz()[0][f.getA4().getP2()], (int) m.getMatriz()[1][f.getA4().getP2()]);
    }

    public void desenhoFaceIsometrica(Face f, Matriz m) {
        g.drawLine((int) m.getMatriz()[0][f.getA1().getP1()], (int) m.getMatriz()[1][f.getA1().getP1()],
                (int) m.getMatriz()[0][f.getA1().getP2()], (int) m.getMatriz()[1][f.getA1().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA2().getP1()], (int) m.getMatriz()[1][f.getA2().getP1()],
                (int) m.getMatriz()[0][f.getA2().getP2()], (int) m.getMatriz()[1][f.getA2().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA3().getP1()], (int) m.getMatriz()[1][f.getA3().getP1()],
                (int) m.getMatriz()[0][f.getA3().getP2()], (int) m.getMatriz()[1][f.getA3().getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getA4().getP1()], (int) m.getMatriz()[1][f.getA4().getP1()],
                (int) m.getMatriz()[0][f.getA4().getP2()], (int) m.getMatriz()[1][f.getA4().getP2()]);
    }

    public void apagarTodosCubosPerspectiva(ArrayList<ListaCubos> listaCubos) {
        g = getGraphics();
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                g.setColor(Color.WHITE);
                desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatrizPerspectiva());
            }
        }
    }

    public void apagarTodosCubosIsometrica(ArrayList<ListaCubos> listaCubos) {
        g = getGraphics();
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                g.setColor(Color.WHITE);
                desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatrizIsometrica());
                desenhoFaceIsometrica(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatrizIsometrica());
            }
        }
    }

    public void apagarTodosCubosFrente(ArrayList<ListaCubos> listaCubos) {
        g = getGraphics();
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                g.setColor(Color.WHITE);
                desenhoFaceFrente(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceFrente(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceFrente(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceFrente(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceFrente(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceFrente(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatriz());
            }
        }
    }

    public void apagarTodosCubosTopo(ArrayList<ListaCubos> listaCubos) {
        g = getGraphics();
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                g.setColor(Color.WHITE);
                desenhoFaceTopo(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceTopo(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceTopo(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceTopo(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceTopo(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceTopo(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatriz());
            }
        }
    }

    public void apagarTodosCubosLado(ArrayList<ListaCubos> listaCubos) {
        g = getGraphics();
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                g.setColor(Color.WHITE);
                desenhoFaceLado(cubo.getListaCubos().get(i).getF1(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceLado(cubo.getListaCubos().get(i).getF2(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceLado(cubo.getListaCubos().get(i).getF3(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceLado(cubo.getListaCubos().get(i).getF4(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceLado(cubo.getListaCubos().get(i).getF5(), cubo.getListaCubos().get(i).getMatriz());
                desenhoFaceLado(cubo.getListaCubos().get(i).getF6(), cubo.getListaCubos().get(i).getMatriz());
            }
        }
    }

    public void visibilidadeFacesFrente(ArrayList<ListaCubos> listaCubos) {
        double a, b, c, raiz;
        int p1, p2, p3, ang;
        Cubo aux;
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                aux = cubo.getListaCubos().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatriz().getAnguloRotacaoZ());
                //Face que está na frente
                if (ang < 180) {
                    p1 = aux.getF1().getA3().getP2();
                    p2 = aux.getF1().getA2().getP2();
                    p3 = aux.getF1().getA2().getP1();
                } else {
                    p3 = aux.getF1().getA3().getP2();
                    p2 = aux.getF1().getA2().getP2();
                    p1 = aux.getF1().getA2().getP1();
                }
                //System.out.println("p1 " + p1);
                //System.out.println("p2 " + p2);
                //System.out.println("p3 " + p3);

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                c = c / raiz;

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]); 
                 R = (a*vet[v][0]) + (b*vet[v][1]) + (c*vet[v][2]) + d; */
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                //a = a / raiz;
                //b = b / raiz;
                //c = c / raiz; 
                //R = c;
                //System.out.println(a);
                //System.out.println(b);
                //System.out.println("1 " + c);
                //System.out.println(d);
                //System.out.println(R);
                //System.out.println("1 " + c);
                //System.out.println("raiz 1 " + raiz);
                if (ang < 180) {
                    if (c <= 0) {
                        desenhoFaceFrente(aux.getF1(), aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getF1(), aux.getMatriz());
                    }
                }

                //Face que está na esquerda
                if (ang < 180) {
                    p1 = aux.getF2().getA3().getP2();
                    p2 = aux.getF2().getA2().getP1();
                    p3 = aux.getF2().getA2().getP2();
                } else {
                    p3 = aux.getF2().getA3().getP2();
                    p2 = aux.getF2().getA2().getP1();
                    p1 = aux.getF2().getA2().getP2();
                }
                //System.out.println("p1 " + p1);
                //System.out.println("p2 " + p2);
                //System.out.println("p3 " + p3);

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                c = c / raiz;

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]); 
                 R = (a*vet[v][0]) + (b*vet[v][1]) + (c*vet[v][2]) + d; */
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                //a = a / raiz;
                //b = b / raiz;
                //System.out.println("raiz 2 " + raiz);
                //c = c / raiz; 
                //R = c;
                //R = a + b + c + d;
                //System.out.println("2 " + c);
                if (ang < 180) {
                    if (c <= 0) {
                        desenhoFaceFrente(aux.getF2(), aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getF2(), aux.getMatriz());
                    }
                }

                //Face que está atrás
                if (ang < 180) {
                    p1 = aux.getF3().getA4().getP1();
                    p2 = aux.getF3().getA1().getP1();
                    p3 = aux.getF3().getA1().getP2();
                } else {
                    p3 = aux.getF3().getA4().getP1();
                    p2 = aux.getF3().getA1().getP1();
                    p1 = aux.getF3().getA1().getP2();
                }
                //System.out.println("p1 " + p1);
                //System.out.println("p2 " + p2);
                //System.out.println("p3 " + p3);

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                c = c / raiz;

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]); 
                 R = (a*vet[v][0]) + (b*vet[v][1]) + (c*vet[v][2]) + d; */
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                //a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                //System.out.println("raiz 3 " + raiz);
                //System.out.println("3 " + c);
                if (ang < 180) {
                    if (c <= 0) {
                        desenhoFaceFrente(aux.getF3(), aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getF3(), aux.getMatriz());
                    }
                }

                //Face que está na direita
                if (ang < 180) {
                    p1 = aux.getF4().getA3().getP1();
                    p2 = aux.getF4().getA2().getP2();
                    p3 = aux.getF4().getA2().getP1();
                } else {
                    p3 = aux.getF4().getA3().getP1();
                    p2 = aux.getF4().getA2().getP2();
                    p1 = aux.getF4().getA2().getP1();
                }
                //System.out.println("p1 " + p1);
                //System.out.println("p2 " + p2);
                //System.out.println("p3 " + p3);

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                c = c / raiz;

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]); 
                 R = (a*vet[v][0]) + (b*vet[v][1]) + (c*vet[v][2]) + d; */
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                //a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                //System.out.println("raiz 4 " + raiz);
                //System.out.println("4 " + c);
                if (ang < 180) {
                    if (c <= 0) {
                        desenhoFaceFrente(aux.getF4(), aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getF4(), aux.getMatriz());
                    }
                }

                //Face que está no topo
                if (ang < 180) {
                    p1 = aux.getF5().getA3().getP1();
                    p2 = aux.getF5().getA2().getP2();
                    p3 = aux.getF5().getA2().getP1();
                } else {
                    p3 = aux.getF5().getA3().getP1();
                    p2 = aux.getF5().getA2().getP2();
                    p1 = aux.getF5().getA2().getP1();
                }
                //System.out.println("p1 " + p1);
                //System.out.println("p2 " + p2);
                //System.out.println("p3 " + p3);

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                c = c / raiz;

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]); 
                 R = (a*vet[v][0]) + (b*vet[v][1]) + (c*vet[v][2]) + d; */
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                //a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                //System.out.println("raiz 5 " + raiz);
                //System.out.println("5 " + c);
                if (ang < 180) {
                    if (c <= 0) {
                        desenhoFaceFrente(aux.getF5(), aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getF5(), aux.getMatriz());
                    }
                }

                //Face que está abaixo
                if (ang < 180) {
                    p1 = aux.getF6().getA3().getP2();
                    p2 = aux.getF6().getA2().getP2();
                    p3 = aux.getF6().getA2().getP1();
                } else {
                    p3 = aux.getF6().getA3().getP2();
                    p2 = aux.getF6().getA2().getP2();
                    p1 = aux.getF6().getA2().getP1();
                }
                //System.out.println("p1 " + p1);
                //System.out.println("p2 " + p2);
                //System.out.println("p3 " + p3);

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                c = c / raiz;

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]); 
                 R = (a*vet[v][0]) + (b*vet[v][1]) + (c*vet[v][2]) + d; */
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                //a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                //System.out.println("raiz 6 " + raiz);
                //System.out.println("6 " + c);
                if (ang < 180) {
                    if (c <= 0) {
                        desenhoFaceFrente(aux.getF6(), aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getF6(), aux.getMatriz());
                    }
                }
            }
        }
    }

    public void visibilidadeFacesTopo(ArrayList<ListaCubos> listaCubos) {
        double a, b, c, raiz;
        int p1, p2, p3, ang;
        Cubo aux;
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                aux = cubo.getListaCubos().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatriz().getAnguloRotacaoY());
                //Face que está na frente
                p1 = aux.getF1().getA3().getP2();
                p2 = aux.getF1().getA2().getP2();
                p3 = aux.getF1().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a*aux.getMatriz().getMatriz()[0][p2]) - (b*aux.getMatriz().getMatriz()[1][p2])
                 - (c*aux.getMatriz().getMatriz()[2][p2]);*/
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                //a = a / raiz;
                b = b / raiz;
                //c = c / raiz;
                //R = c;
                //System.out.println(a);
                //System.out.println(b);
                //System.out.println(c);
                //System.out.println(d);
                //System.out.println(R);
                if (b <= 0) {
                    desenhoFaceTopo(aux.getF1(), aux.getMatriz());
                }

                //Face que está na esquerda
                p1 = aux.getF2().getA3().getP2();
                p2 = aux.getF2().getA2().getP1();
                p3 = aux.getF2().getA2().getP2();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a*aux.getMatriz().getMatriz()[0][p2]) - (b*aux.getMatriz().getMatriz()[1][p2])
                 - (c*aux.getMatriz().getMatriz()[2][p2]);*/
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                //a = a / raiz;
                b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (b <= 0) {
                    desenhoFaceTopo(aux.getF2(), aux.getMatriz());
                }

                //Face que está atrás
                p1 = aux.getF3().getA4().getP1();
                p2 = aux.getF3().getA1().getP1();
                p3 = aux.getF3().getA1().getP2();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                //a = a / raiz;
                b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (b <= 0) {
                    desenhoFaceTopo(aux.getF3(), aux.getMatriz());
                }

                //Face que está na direita
                p1 = aux.getF4().getA3().getP1();
                p2 = aux.getF4().getA2().getP2();
                p3 = aux.getF4().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                //a = a / raiz;
                b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (b <= 0) {
                    desenhoFaceTopo(aux.getF4(), aux.getMatriz());
                }

                //Face que está no topo
                p1 = aux.getF5().getA3().getP1();
                p2 = aux.getF5().getA2().getP2();
                p3 = aux.getF5().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                //a = a / raiz;
                b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (b <= 0) {
                    desenhoFaceTopo(aux.getF5(), aux.getMatriz());
                }

                //Face que está abaixo
                p1 = aux.getF6().getA3().getP2();
                p2 = aux.getF6().getA2().getP2();
                p3 = aux.getF6().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                //a = a / raiz;
                b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (b <= 0) {
                    desenhoFaceTopo(aux.getF6(), aux.getMatriz());
                }
            }
        }
    }

    public void visibilidadeFacesLado(ArrayList<ListaCubos> listaCubos) {
        double a, b, c, raiz;
        int p1, p2, p3, ang;
        Cubo aux;
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                aux = cubo.getListaCubos().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatriz().getAnguloRotacaoX());
                //Face que está na frente
                p1 = aux.getF1().getA3().getP2();
                p2 = aux.getF1().getA2().getP2();
                p3 = aux.getF1().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a*aux.getMatriz().getMatriz()[0][p2]) - (b*aux.getMatriz().getMatriz()[1][p2])
                 - (c*aux.getMatriz().getMatriz()[2][p2]);*/
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //System.out.println(a);
                //System.out.println(b);
                //System.out.println(c);
                //System.out.println(d);
                //System.out.println(R);
                if (a <= 0) {
                    desenhoFaceLado(aux.getF1(), aux.getMatriz());
                }

                //Face que está na esquerda
                p1 = aux.getF2().getA3().getP2();
                p2 = aux.getF2().getA2().getP1();
                p3 = aux.getF2().getA2().getP2();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a*aux.getMatriz().getMatriz()[0][p2]) - (b*aux.getMatriz().getMatriz()[1][p2])
                 - (c*aux.getMatriz().getMatriz()[2][p2]);*/
                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (a <= 0) {
                    desenhoFaceLado(aux.getF2(), aux.getMatriz());
                }

                //Face que está atrás
                p1 = aux.getF3().getA4().getP1();
                p2 = aux.getF3().getA1().getP1();
                p3 = aux.getF3().getA1().getP2();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (a <= 0) {
                    desenhoFaceLado(aux.getF3(), aux.getMatriz());
                }

                //Face que está na direita
                p1 = aux.getF4().getA3().getP1();
                p2 = aux.getF4().getA2().getP2();
                p3 = aux.getF4().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (a <= 0) {
                    desenhoFaceLado(aux.getF4(), aux.getMatriz());
                }

                //Face que está no topo
                p1 = aux.getF5().getA3().getP1();
                p2 = aux.getF5().getA2().getP2();
                p3 = aux.getF5().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (a <= 0) {
                    desenhoFaceLado(aux.getF5(), aux.getMatriz());
                }

                //Face que está abaixo
                p1 = aux.getF6().getA3().getP2();
                p2 = aux.getF6().getA2().getP2();
                p3 = aux.getF6().getA2().getP1();

                a = (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        - (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        * (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2]);

                b = (aux.getMatriz().getMatriz()[2][p3] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        - (aux.getMatriz().getMatriz()[2][p1] - aux.getMatriz().getMatriz()[2][p2])
                        * (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2]);

                c = (aux.getMatriz().getMatriz()[0][p3] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p1] - aux.getMatriz().getMatriz()[1][p2])
                        - (aux.getMatriz().getMatriz()[0][p1] - aux.getMatriz().getMatriz()[0][p2])
                        * (aux.getMatriz().getMatriz()[1][p3] - aux.getMatriz().getMatriz()[1][p2]);

                /*d = -(a * aux.getMatriz().getMatriz()[0][p2]) - (b * aux.getMatriz().getMatriz()[1][p2])
                 - (c * aux.getMatriz().getMatriz()[2][p2]);*/

                /*R = (a*aux.getMatriz().getMatriz()[0][8]) + (b*aux.getMatriz().getMatriz()[1][8]) 
                 + (c*aux.getMatriz().getMatriz()[2][8]) + d;*/
                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                //b = b / raiz;
                //c = c / raiz;
                //R = c;
                //R = a + b + c + d;
                if (a <= 0) {
                    desenhoFaceLado(aux.getF6(), aux.getMatriz());
                }
            }
        }
    }

    public void visibilidadesFacesPerspectiva(ArrayList<ListaCubos> listaCubos) {
        double a, b, c, raiz, R;
        int p1, p2, p3, ang;
        Cubo aux;
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                aux = cubo.getListaCubos().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatrizPerspectiva().getAnguloRotacaoZ());
                //Face que está na frente
                if (ang < 180) {
                    p1 = aux.getF1().getA3().getP2();
                    p2 = aux.getF1().getA2().getP2();
                    p3 = aux.getF1().getA2().getP1();
                } else {
                    p3 = aux.getF1().getA3().getP2();
                    p2 = aux.getF1().getA2().getP2();
                    p1 = aux.getF1().getA2().getP1();
                }

                a = (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2]);

                b = (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2]);

                c = (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;

                if (ang < 180) {
                    if (R <= 0) {
                        desenhoFacePerspectiva(aux.getF1(), aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getF1(), aux.getMatrizPerspectiva());
                    }
                }

                //Face que está na esquerda
                if (ang < 180) {
                    p1 = aux.getF2().getA3().getP2();
                    p2 = aux.getF2().getA2().getP1();
                    p3 = aux.getF2().getA2().getP2();
                } else {
                    p3 = aux.getF2().getA3().getP2();
                    p2 = aux.getF2().getA2().getP1();
                    p1 = aux.getF2().getA2().getP2();
                }

                a = (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2]);

                b = (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2]);

                c = (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (ang < 180) {
                    if (R <= 0) {
                        desenhoFacePerspectiva(aux.getF2(), aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getF2(), aux.getMatrizPerspectiva());
                    }
                }

                //Face que está atrás
                if (ang < 180) {
                    p1 = aux.getF3().getA4().getP1();
                    p2 = aux.getF3().getA1().getP1();
                    p3 = aux.getF3().getA1().getP2();
                } else {
                    p3 = aux.getF3().getA4().getP1();
                    p2 = aux.getF3().getA1().getP1();
                    p1 = aux.getF3().getA1().getP2();
                }

                a = (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2]);

                b = (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2]);

                c = (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (ang < 180) {
                    if (R <= 0) {
                        desenhoFacePerspectiva(aux.getF3(), aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getF3(), aux.getMatrizPerspectiva());
                    }
                }

                //Face que está na direita
                if (ang < 180) {
                    p1 = aux.getF4().getA3().getP1();
                    p2 = aux.getF4().getA2().getP2();
                    p3 = aux.getF4().getA2().getP1();
                } else {
                    p3 = aux.getF4().getA3().getP1();
                    p2 = aux.getF4().getA2().getP2();
                    p1 = aux.getF4().getA2().getP1();
                }

                a = (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2]);

                b = (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2]);

                c = (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (ang < 180) {
                    if (R <= 0) {
                        desenhoFacePerspectiva(aux.getF4(), aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getF4(), aux.getMatrizPerspectiva());
                    }
                }

                //Face que está no topo
                if (ang < 180) {
                    p1 = aux.getF5().getA3().getP1();
                    p2 = aux.getF5().getA2().getP2();
                    p3 = aux.getF5().getA2().getP1();
                } else {
                    p3 = aux.getF5().getA3().getP1();
                    p2 = aux.getF5().getA2().getP2();
                    p1 = aux.getF5().getA2().getP1();
                }

                a = (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2]);

                b = (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2]);

                c = (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (ang < 180) {
                    if (R <= 0) {
                        desenhoFacePerspectiva(aux.getF5(), aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getF5(), aux.getMatrizPerspectiva());
                    }
                }

                //Face que está abaixo
                if (ang < 180) {
                    p1 = aux.getF6().getA3().getP2();
                    p2 = aux.getF6().getA2().getP2();
                    p3 = aux.getF6().getA2().getP1();
                } else {
                    p3 = aux.getF6().getA3().getP2();
                    p2 = aux.getF6().getA2().getP2();
                    p1 = aux.getF6().getA2().getP1();
                }

                a = (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2]);

                b = (aux.getMatrizPerspectiva().getMatriz()[2][p3] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[2][p1] - aux.getMatrizPerspectiva().getMatriz()[2][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2]);

                c = (aux.getMatrizPerspectiva().getMatriz()[0][p3] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p1] - aux.getMatrizPerspectiva().getMatriz()[1][p2])
                        - (aux.getMatrizPerspectiva().getMatriz()[0][p1] - aux.getMatrizPerspectiva().getMatriz()[0][p2])
                        * (aux.getMatrizPerspectiva().getMatriz()[1][p3] - aux.getMatrizPerspectiva().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (ang < 180) {
                    if (R <= 0) {
                        desenhoFacePerspectiva(aux.getF6(), aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getF6(), aux.getMatrizPerspectiva());
                    }
                }
            }
        }
    }

    public void visibilidadeFacesIsometrica(ArrayList<ListaCubos> listaCubos) {
        double a, b, c, raiz, R;
        int p1, p2, p3;
        Cubo aux;
        for (ListaCubos cubo : listaCubos) {
            for (int i = 0; i < cubo.getListaCubos().size(); i++) {
                aux = cubo.getListaCubos().get(i);
                g.setColor(aux.getCorBordas());
                //Face que está na frente
                p1 = aux.getF1().getA3().getP2();
                p2 = aux.getF1().getA2().getP2();
                p3 = aux.getF1().getA2().getP1();

                a = (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2]);

                b = (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2]);

                c = (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;

                if (R <= 0) {
                    desenhoFaceIsometrica(aux.getF1(), aux.getMatrizIsometrica());
                }

                //Face que está na esquerda
                p1 = aux.getF2().getA3().getP2();
                p2 = aux.getF2().getA2().getP1();
                p3 = aux.getF2().getA2().getP2();

                a = (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2]);

                b = (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2]);

                c = (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (R <= 0) {
                    desenhoFaceIsometrica(aux.getF2(), aux.getMatrizIsometrica());
                }

                //Face que está atrás
                p1 = aux.getF3().getA4().getP1();
                p2 = aux.getF3().getA1().getP1();
                p3 = aux.getF3().getA1().getP2();

                a = (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2]);

                b = (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2]);

                c = (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (R <= 0) {
                    desenhoFaceIsometrica(aux.getF3(), aux.getMatrizIsometrica());
                }

                //Face que está na direita
                p1 = aux.getF4().getA3().getP1();
                p2 = aux.getF4().getA2().getP2();
                p3 = aux.getF4().getA2().getP1();

                a = (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2]);

                b = (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2]);

                c = (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (R <= 0) {
                    desenhoFaceIsometrica(aux.getF4(), aux.getMatrizIsometrica());
                }

                //Face que está no topo
                p1 = aux.getF5().getA3().getP1();
                p2 = aux.getF5().getA2().getP2();
                p3 = aux.getF5().getA2().getP1();

                a = (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2]);

                b = (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2]);

                c = (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (R <= 0) {
                    desenhoFaceIsometrica(aux.getF5(), aux.getMatrizIsometrica());
                }

                //Face que está abaixo
                p1 = aux.getF6().getA3().getP2();
                p2 = aux.getF6().getA2().getP2();
                p3 = aux.getF6().getA2().getP1();

                a = (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2]);

                b = (aux.getMatrizIsometrica().getMatriz()[2][p3] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[2][p1] - aux.getMatrizIsometrica().getMatriz()[2][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2]);

                c = (aux.getMatrizIsometrica().getMatriz()[0][p3] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p1] - aux.getMatrizIsometrica().getMatriz()[1][p2])
                        - (aux.getMatrizIsometrica().getMatriz()[0][p1] - aux.getMatrizIsometrica().getMatriz()[0][p2])
                        * (aux.getMatrizIsometrica().getMatriz()[1][p3] - aux.getMatrizIsometrica().getMatriz()[1][p2]);

                raiz = Math.sqrt(a * a + b * b + c * c);
                a = a / raiz;
                b = b / raiz;
                c = c / raiz;
                R = a + b + c;
                if (R <= 0) {
                    desenhoFaceIsometrica(aux.getF6(), aux.getMatrizIsometrica());
                }
            }
        }
    }

    public void apagarCubo(ListaCubos listaCubos) {
        g.setColor(Color.WHITE);
        Cubo cubo;
        for (int i = 0; i < listaCubos.getListaCubos().size(); i++) {
            cubo = listaCubos.getListaCubos().get(i);
            //Frente
            desenhoFaceFrente(cubo.getF1(), cubo.getMatriz());
            desenhoFaceFrente(cubo.getF2(), cubo.getMatriz());
            desenhoFaceFrente(cubo.getF3(), cubo.getMatriz());
            desenhoFaceFrente(cubo.getF4(), cubo.getMatriz());
            desenhoFaceFrente(cubo.getF5(), cubo.getMatriz());
            desenhoFaceFrente(cubo.getF6(), cubo.getMatriz());
            //Topo
            desenhoFaceTopo(cubo.getF1(), cubo.getMatriz());
            desenhoFaceTopo(cubo.getF2(), cubo.getMatriz());
            desenhoFaceTopo(cubo.getF3(), cubo.getMatriz());
            desenhoFaceTopo(cubo.getF4(), cubo.getMatriz());
            desenhoFaceTopo(cubo.getF5(), cubo.getMatriz());
            desenhoFaceTopo(cubo.getF6(), cubo.getMatriz());
            //Lado
            desenhoFaceLado(cubo.getF1(), cubo.getMatriz());
            desenhoFaceLado(cubo.getF2(), cubo.getMatriz());
            desenhoFaceLado(cubo.getF3(), cubo.getMatriz());
            desenhoFaceLado(cubo.getF4(), cubo.getMatriz());
            desenhoFaceLado(cubo.getF5(), cubo.getMatriz());
            desenhoFaceLado(cubo.getF6(), cubo.getMatriz());
        }
    }

    public void apagarCuboProjecao(ListaCubos listaCubos, int n) {
        g.setColor(Color.WHITE);
        Cubo cubo;
        if (n == 1) {
            for (int i = 0; i < listaCubos.getListaCubos().size(); i++) {
                cubo = listaCubos.getListaCubos().get(i);
                //Perspectiva
                desenhoFacePerspectiva(cubo.getF1(), cubo.getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getF2(), cubo.getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getF3(), cubo.getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getF4(), cubo.getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getF5(), cubo.getMatrizPerspectiva());
                desenhoFacePerspectiva(cubo.getF6(), cubo.getMatrizPerspectiva());
            }
        } else {
            if (n == 2) {
                for (int i = 0; i < listaCubos.getListaCubos().size(); i++) {
                    cubo = listaCubos.getListaCubos().get(i);
                    //Isometrica
                    desenhoFaceIsometrica(cubo.getF1(), cubo.getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getF2(), cubo.getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getF3(), cubo.getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getF4(), cubo.getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getF5(), cubo.getMatrizIsometrica());
                    desenhoFaceIsometrica(cubo.getF6(), cubo.getMatrizIsometrica());
                }
            }
        }
    }

    public boolean existemCubosPossiveisFrente(ArrayList<ListaCubos> cb, int cord1, int cord2) {
        cubosPossiveis.clear();
        cubosPossiveis = new ArrayList<>();
        for (int i = 0; i < cb.size(); i++) {
            if (clickDentroCuboFrente(cb.get(i), cord1, cord2)) {
                cubosPossiveis.add(cb.get(i));
                //System.out.println("possivel " + i);
            }
        }
        return !cubosPossiveis.isEmpty();
    }

    public boolean existemCubosPossiveisTopo(ArrayList<ListaCubos> cb, int cord1, int cord2) {
        cubosPossiveis.clear();
        cubosPossiveis = new ArrayList<>();
        for (int i = 0; i < cb.size(); i++) {
            if (clickDentroCuboTopo(cb.get(i), cord1, cord2)) {
                cubosPossiveis.add(cb.get(i));
            }
        }
        return !cubosPossiveis.isEmpty();
    }

    public boolean existemCubosPossiveisLado(ArrayList<ListaCubos> cb, int cord1, int cord2) {
        cubosPossiveis.clear();
        cubosPossiveis = new ArrayList<>();
        for (int i = 0; i < cb.size(); i++) {
            //Invertido por conta da ordenada y invertida no painel do java
            if (clickDentroCuboLado(cb.get(i), cord1, cord2)) {
                cubosPossiveis.add(cb.get(i));
            }
        }
        return !cubosPossiveis.isEmpty();
    }

    public boolean clickDentroCuboFrente(ListaCubos c, int cord1, int cord2) {
        if (cord1 >= c.getExtremoEsquerda() && cord1 <= c.getExtremoDireita()) {
            if (cord2 >= c.getExtremoCima() && cord2 <= c.getExtremoBaixo()) {
                return true;
            }
        }
        return false;
        //return (clickDentroCubo(c, cord1, cord2, 0, 1));
    }

    public boolean clickDentroCuboTopo(ListaCubos c, int cord1, int cord2) {
        if (cord1 >= c.getExtremoEsquerda() && cord1 <= c.getExtremoDireita()) {
            if (cord2 >= c.getExtremoTras() && cord2 <= c.getExtremoFrente()) {
                return true;
            }
        }
        return false;
        //return (clickDentroCubo(c, cord1, cord2, 0, 2));
    }

    public boolean clickDentroCuboLado(ListaCubos c, int cord1, int cord2) {
        //Invertido por conta da ordenada y invertida no painel do java
        if (cord1 >= c.getExtremoTras() && cord1 <= c.getExtremoFrente()) {
            if (cord2 >= c.getExtremoCima() && cord2 <= c.getExtremoBaixo()) {
                return true;
            }
        }
        return false;
        //return (clickDentroCubo(c, cord1, cord2, 2, 1));
    }

    public int selecionarCuboFrente(ArrayList<ListaCubos> listaCubos, int cord1, int cord2) {
        double menorDist = 999999999, dist;
        int selec = 0;
        for (int i = 0; i < listaCubos.size(); i++) {
            dist = listaCubos.get(i).distanciaCentroPontoXY(cord1, cord2);
            if (dist < menorDist) {
                menorDist = dist;
                selec = i;
            }
            //System.out.println("distancia " + menorDist);
        }
        return selec;
    }

    public int selecionarCuboTopo(ArrayList<ListaCubos> listaCubos, int cord1, int cord2) {
        double menorDist = 999999999, dist;
        int selec = 0;
        for (int i = 0; i < listaCubos.size(); i++) {
            dist = listaCubos.get(i).distanciaCentroPontoXZ(cord1, cord2);
            if (dist < menorDist) {
                menorDist = dist;
                selec = i;
            }
        }
        return selec;
    }

    public int selecionarCuboLado(ArrayList<ListaCubos> listaCubos, int cord1, int cord2) {
        double menorDist = 999999999, dist;
        int selec = 0;
        for (int i = 0; i < listaCubos.size(); i++) {
            //Invertido por causa do painel do java
            dist = listaCubos.get(i).distanciaCentroPontoYZ(cord2, cord1);
            if (dist < menorDist) {
                menorDist = dist;
                selec = i;
            }
        }
        return selec;
    }

    public void pintarSelecaoFrente(ListaCubos c, Color cor) {
        g = getGraphics();
        g.setColor(cor);
        boolean trocar = true;
        int esq, dir, cima, baixo;
        esq = c.getExtremoEsquerda() - 4;
        dir = c.getExtremoDireita() + 5;
        cima = c.getExtremoCima();
        baixo = c.getExtremoBaixo();
        for (int i = esq; i < dir; i++) {
            if (trocar) {
                g.drawLine(i, c.getExtremoCima(), i, c.getExtremoCima());
                g.drawLine(i, c.getExtremoCima() - 2, i, c.getExtremoCima() - 2);
                g.drawLine(i, c.getExtremoCima() - 4, i, c.getExtremoCima() - 4);
                g.drawLine(i, c.getExtremoBaixo(), i, c.getExtremoBaixo());
                g.drawLine(i, c.getExtremoBaixo() + 2, i, c.getExtremoBaixo() + 2);
                g.drawLine(i, c.getExtremoBaixo() + 4, i, c.getExtremoBaixo() + 4);
                trocar = false;
            } else {
                g.drawLine(i, c.getExtremoCima() - 1, i, c.getExtremoCima() - 1);
                g.drawLine(i, c.getExtremoCima() - 3, i, c.getExtremoCima() - 3);
                g.drawLine(i, c.getExtremoBaixo() + 1, i, c.getExtremoBaixo() + 1);
                g.drawLine(i, c.getExtremoBaixo() + 3, i, c.getExtremoBaixo() + 3);
                trocar = true;
            }
        }
        trocar = false;
        for (int i = cima; i < baixo; i++) {
            if (trocar) {
                g.drawLine(c.getExtremoEsquerda(), i, c.getExtremoEsquerda(), i);
                g.drawLine(c.getExtremoEsquerda() - 2, i, c.getExtremoEsquerda() - 2, i);
                g.drawLine(c.getExtremoEsquerda() - 4, i, c.getExtremoEsquerda() - 4, i);
                g.drawLine(c.getExtremoDireita(), i, c.getExtremoDireita(), i);
                g.drawLine(c.getExtremoDireita() + 2, i, c.getExtremoDireita() + 2, i);
                g.drawLine(c.getExtremoDireita() + 4, i, c.getExtremoDireita() + 4, i);
                trocar = false;
            } else {
                g.drawLine(c.getExtremoEsquerda() - 1, i, c.getExtremoEsquerda() - 1, i);
                g.drawLine(c.getExtremoEsquerda() - 3, i, c.getExtremoEsquerda() - 3, i);
                g.drawLine(c.getExtremoDireita() + 1, i, c.getExtremoDireita() + 1, i);
                g.drawLine(c.getExtremoDireita() + 3, i, c.getExtremoDireita() + 3, i);
                trocar = true;
            }
        }
        //pintarSelecao(c, cor, 0, 1);
    }

    public void pintarSelecaoTopo(ListaCubos c, Color cor) {
        g = getGraphics();
        g.setColor(cor);
        boolean trocar = true;
        int esq, dir, tras, frente;
        esq = c.getExtremoEsquerda() - 4;
        dir = c.getExtremoDireita() + 5;
        tras = c.getExtremoTras();
        frente = c.getExtremoFrente();
        for (int i = esq; i < dir; i++) {
            if (trocar) {
                g.drawLine(i, c.getExtremoTras(), i, c.getExtremoTras());
                g.drawLine(i, c.getExtremoTras() - 2, i, c.getExtremoTras() - 2);
                g.drawLine(i, c.getExtremoTras() - 4, i, c.getExtremoTras() - 4);
                g.drawLine(i, c.getExtremoFrente(), i, c.getExtremoFrente());
                g.drawLine(i, c.getExtremoFrente() + 2, i, c.getExtremoFrente() + 2);
                g.drawLine(i, c.getExtremoFrente() + 4, i, c.getExtremoFrente() + 4);
                trocar = false;
            } else {
                g.drawLine(i, c.getExtremoTras() - 1, i, c.getExtremoTras() - 1);
                g.drawLine(i, c.getExtremoTras() - 3, i, c.getExtremoTras() - 3);
                g.drawLine(i, c.getExtremoFrente() + 1, i, c.getExtremoFrente() + 1);
                g.drawLine(i, c.getExtremoFrente() + 3, i, c.getExtremoFrente() + 3);
                trocar = true;
            }
        }
        trocar = false;
        for (int i = tras; i < frente; i++) {
            if (trocar) {
                g.drawLine(c.getExtremoEsquerda(), i, c.getExtremoEsquerda(), i);
                g.drawLine(c.getExtremoEsquerda() - 2, i, c.getExtremoEsquerda() - 2, i);
                g.drawLine(c.getExtremoEsquerda() - 4, i, c.getExtremoEsquerda() - 4, i);
                g.drawLine(c.getExtremoDireita(), i, c.getExtremoDireita(), i);
                g.drawLine(c.getExtremoDireita() + 2, i, c.getExtremoDireita() + 2, i);
                g.drawLine(c.getExtremoDireita() + 4, i, c.getExtremoDireita() + 4, i);
                trocar = false;
            } else {
                g.drawLine(c.getExtremoEsquerda() - 1, i, c.getExtremoEsquerda() - 1, i);
                g.drawLine(c.getExtremoEsquerda() - 3, i, c.getExtremoEsquerda() - 3, i);
                g.drawLine(c.getExtremoDireita() + 1, i, c.getExtremoDireita() + 1, i);
                g.drawLine(c.getExtremoDireita() + 3, i, c.getExtremoDireita() + 3, i);
                trocar = true;
            }
        }
        //pintarSelecao(c, cor, 0, 2);
    }

    public void pintarSelecaoLado(ListaCubos c, Color cor) {
        //Invertido por conta da ordenada y invertida no painel do java
        g = getGraphics();
        g.setColor(cor);
        boolean trocar = true;
        int tras, frente, cima, baixo;
        tras = c.getExtremoTras() - 4;
        frente = c.getExtremoFrente() + 5;
        cima = c.getExtremoCima();
        baixo = c.getExtremoBaixo();
        for (int i = tras; i < frente; i++) {
            if (trocar) {
                g.drawLine(i, c.getExtremoCima(), i, c.getExtremoCima());
                g.drawLine(i, c.getExtremoCima() - 2, i, c.getExtremoCima() - 2);
                g.drawLine(i, c.getExtremoCima() - 4, i, c.getExtremoCima() - 4);
                g.drawLine(i, c.getExtremoBaixo(), i, c.getExtremoBaixo());
                g.drawLine(i, c.getExtremoBaixo() + 2, i, c.getExtremoBaixo() + 2);
                g.drawLine(i, c.getExtremoBaixo() + 4, i, c.getExtremoBaixo() + 4);
                trocar = false;
            } else {
                g.drawLine(i, c.getExtremoCima() - 1, i, c.getExtremoCima() - 1);
                g.drawLine(i, c.getExtremoCima() - 3, i, c.getExtremoCima() - 3);
                g.drawLine(i, c.getExtremoBaixo() + 1, i, c.getExtremoBaixo() + 1);
                g.drawLine(i, c.getExtremoBaixo() + 3, i, c.getExtremoBaixo() + 3);
                trocar = true;
            }
        }
        trocar = false;
        for (int i = cima; i < baixo; i++) {
            if (trocar) {
                g.drawLine(c.getExtremoTras(), i, c.getExtremoTras(), i);
                g.drawLine(c.getExtremoTras() - 2, i, c.getExtremoTras() - 2, i);
                g.drawLine(c.getExtremoTras() - 4, i, c.getExtremoTras() - 4, i);
                g.drawLine(c.getExtremoFrente(), i, c.getExtremoFrente(), i);
                g.drawLine(c.getExtremoFrente() + 2, i, c.getExtremoFrente() + 2, i);
                g.drawLine(c.getExtremoFrente() + 4, i, c.getExtremoFrente() + 4, i);
                trocar = false;
            } else {
                g.drawLine(c.getExtremoTras() - 1, i, c.getExtremoTras() - 1, i);
                g.drawLine(c.getExtremoTras() - 3, i, c.getExtremoTras() - 3, i);
                g.drawLine(c.getExtremoFrente() + 1, i, c.getExtremoFrente() + 1, i);
                g.drawLine(c.getExtremoFrente() + 3, i, c.getExtremoFrente() + 3, i);
                trocar = true;
            }
        }
        //pintarSelecao(c, cor, 2, 1);
    }

    public int getCuboMaisProximoFrente(ArrayList<ListaCubos> listaCubos, int sel) {
        double menorDist = 999999999, dist;
        double cX, cY;
        cX = listaCubos.get(sel).getCentroX();
        cY = listaCubos.get(sel).getCentroY();
        int index = -1;
        for (int i = 0; i < listaCubos.size(); i++) {
            if (i != sel) {
                dist = listaCubos.get(i).distanciaCentroPontoXY(cX, cY);
                if (dist < menorDist) {
                    menorDist = dist;
                    index = i;
                }
            }
        }
        if (menorDist <= 50) {
            return index;
        } else {
            return -1;
        }
    }

    public int getCuboMaisProximoTopo(ArrayList<ListaCubos> listaCubos, int sel) {
        double menorDist = 999999999, dist;
        double cX, cZ;
        cX = listaCubos.get(sel).getCentroX();
        cZ = listaCubos.get(sel).getCentroZ();
        int index = -1;
        for (int i = 0; i < listaCubos.size(); i++) {
            if (i != sel) {
                dist = listaCubos.get(i).distanciaCentroPontoXZ(cX, cZ);
                if (dist < menorDist) {
                    menorDist = dist;
                    index = i;
                }
            }
        }
        if (menorDist <= 50) {
            return index;
        } else {
            return -1;
        }
    }

    public int getCuboMaisProximoLado(ArrayList<ListaCubos> listaCubos, int sel) {
        double menorDist = 999999999, dist;
        double cY, cZ;
        cY = listaCubos.get(sel).getCentroY();
        cZ = listaCubos.get(sel).getCentroZ();
        int index = -1;
        for (int i = 0; i < listaCubos.size(); i++) {
            if (i != sel) {
                dist = listaCubos.get(i).distanciaCentroPontoYZ(cY, cZ);
                if (dist < menorDist) {
                    menorDist = dist;
                    index = i;
                }
            }
        }
        if (menorDist <= 50) {
            return index;
        } else {
            return -1;
        }
    }

    public void limparTela() {
        //repaint();
        g = getGraphics();
        super.paintComponent(getGraphics());
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }
}
