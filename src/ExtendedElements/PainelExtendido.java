package ExtendedElements;

import Model.Primitiva;
import Model.Face;
import Model.ListaPrimitivas;
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
    private ArrayList<ListaPrimitivas> primitivasPossiveis = new ArrayList<>();

    public PainelExtendido() {
        setBackground(Color.white);
        //setBorder(TitledBorder.);
    }

    public void desenharVisaoTopo(ArrayList<ListaPrimitivas> listaPrimitivas, boolean ocultarFaces) {
        g = getGraphics();
        if (ocultarFaces) {
            visibilidadeFacesTopo(listaPrimitivas);
            /*int tam = listaPrimitivas.size();
             int pos, count;
             double valor[][] = new double[6][8];
             double aux;
             boolean F1, F2, F3, F4, F5, F6;
             F1 = F2 = F3 = F4 = F5 = F6 = true;
             for (int i = 0; i < tam; i++) {
             aux = -1;

             }*/
        } else {
            int numFaces;
            for (ListaPrimitivas primitiva : listaPrimitivas) {
                for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                    g.setColor(primitiva.getListaPrimitivas().get(i).getCorBordas());
                    numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                    for (int j = 0; j < numFaces; j++) {
                        desenhoFaceTopo(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatriz());
                    }
                }
            }
        }
    }

    public void desenharVisaoLadoEsquerdo(ArrayList<ListaPrimitivas> listaPrimitivas, boolean ocultarFaces) {
        g = getGraphics();
        if (ocultarFaces) {
            visibilidadeFacesLado(listaPrimitivas);
        } else {
            int numFaces;
            for (ListaPrimitivas primitiva : listaPrimitivas) {  
                for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                    g.setColor(primitiva.getListaPrimitivas().get(i).getCorBordas());
                    numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                    for (int j = 0; j < numFaces; j++) {
                        desenhoFaceLado(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatriz());
                    }
                }
            }
        }
    }

    public void desenharVisaoFrente(ArrayList<ListaPrimitivas> listaPrimitivas, boolean ocultarFaces) {
        g = getGraphics();
        if (ocultarFaces) {
            visibilidadeFacesFrente(listaPrimitivas);
        } else {
            int numFaces;
            for (ListaPrimitivas primitiva : listaPrimitivas) {
                for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                    g.setColor(primitiva.getListaPrimitivas().get(i).getCorBordas());
                    numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                    for (int j = 0; j < numFaces; j++) {
                        desenhoFaceFrente(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatriz());
                    }
                }
            }
        }
    }

    public void desenharVisaoPerspectiva(ArrayList<ListaPrimitivas> listaPrimitivas, boolean ocultarFaces, Ponto VRP, Ponto P, int dp) {
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
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                m = multiplicaMatrizes(srusrc, primitiva.getListaPrimitivas().get(i).getMatriz().getMatriz(), 4, 9);
                m = multiplicaMatrizes(p, m, 4, 9);
                Matriz mpers = new Matriz(m, 1);
                mpers.homogeneizar();
                primitiva.getListaPrimitivas().get(i).setMatrizPerspectiva(mpers);
            }
        }
        if (ocultarFaces) {
            visibilidadesFacesPerspectiva(listaPrimitivas);
        } else {
            int numFaces;
            for (ListaPrimitivas primitiva : listaPrimitivas) {
                for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                    g.setColor(primitiva.getListaPrimitivas().get(i).getCorBordas());
                    numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                    for (int j = 0; j < numFaces; j++) {
                        desenhoFacePerspectiva(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatrizPerspectiva());
                    }
                }
            }
        }
    }

    public void desenharVisaoIsometrica(ArrayList<ListaPrimitivas> listaPrimitivas, boolean ocultarFaces, Ponto VRP, Ponto P) {
        g = getGraphics();
        double[][] srusrc = new double[4][4];
        double[][] m = new double[4][4];
        srusrc = SRUSRC(VRP, P);
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                m = multiplicaMatrizes(srusrc, primitiva.getListaPrimitivas().get(i).getMatriz().getMatriz(), 4, 9);
                Matriz mIso = new Matriz(m, 1);
                mIso.adaptar();
                primitiva.getListaPrimitivas().get(i).setMatrizIsometrica(mIso);
            }
        }
        if (ocultarFaces) {
            visibilidadeFacesIsometrica(listaPrimitivas);
        } else {
            int numFaces;
            for (ListaPrimitivas primitiva : listaPrimitivas) { 
                for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                    g.setColor(primitiva.getListaPrimitivas().get(i).getCorBordas());
                    numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                    for (int j = 0; j < numFaces; j++) {
                        desenhoFaceIsometrica(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatrizIsometrica());
                    }
                }
            }
        }
    }

    public double[][] SRUSRC(Ponto VRP, Ponto P) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
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
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
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
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        g.drawLine((int) m.getMatriz()[0][f.getAresta(0).getP1()], (int) m.getMatriz()[1][f.getAresta(0).getP1()],
                (int) m.getMatriz()[0][f.getAresta(0).getP2()], (int) m.getMatriz()[1][f.getAresta(0).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(1).getP1()], (int) m.getMatriz()[1][f.getAresta(1).getP1()],
                (int) m.getMatriz()[0][f.getAresta(1).getP2()], (int) m.getMatriz()[1][f.getAresta(1).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(2).getP1()], (int) m.getMatriz()[1][f.getAresta(2).getP1()],
                (int) m.getMatriz()[0][f.getAresta(2).getP2()], (int) m.getMatriz()[1][f.getAresta(2).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(3).getP1()], (int) m.getMatriz()[1][f.getAresta(3).getP1()],
                (int) m.getMatriz()[0][f.getAresta(3).getP2()], (int) m.getMatriz()[1][f.getAresta(3).getP2()]);
    }

    public void desenhoFaceTopo(Face f, Matriz m) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        g.drawLine((int) m.getMatriz()[0][f.getAresta(0).getP1()], (int) m.getMatriz()[2][f.getAresta(0).getP1()],
                (int) m.getMatriz()[0][f.getAresta(0).getP2()], (int) m.getMatriz()[2][f.getAresta(0).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(1).getP1()], (int) m.getMatriz()[2][f.getAresta(1).getP1()],
                (int) m.getMatriz()[0][f.getAresta(1).getP2()], (int) m.getMatriz()[2][f.getAresta(1).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(2).getP1()], (int) m.getMatriz()[2][f.getAresta(2).getP1()],
                (int) m.getMatriz()[0][f.getAresta(2).getP2()], (int) m.getMatriz()[2][f.getAresta(2).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(3).getP1()], (int) m.getMatriz()[2][f.getAresta(3).getP1()],
                (int) m.getMatriz()[0][f.getAresta(3).getP2()], (int) m.getMatriz()[2][f.getAresta(3).getP2()]);
    }

    public void desenhoFaceLado(Face f, Matriz m) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        g.drawLine((int) m.getMatriz()[2][f.getAresta(0).getP1()], (int) m.getMatriz()[1][f.getAresta(0).getP1()],
                (int) m.getMatriz()[2][f.getAresta(0).getP2()], (int) m.getMatriz()[1][f.getAresta(0).getP2()]);

        g.drawLine((int) m.getMatriz()[2][f.getAresta(1).getP1()], (int) m.getMatriz()[1][f.getAresta(1).getP1()],
                (int) m.getMatriz()[2][f.getAresta(1).getP2()], (int) m.getMatriz()[1][f.getAresta(1).getP2()]);

        g.drawLine((int) m.getMatriz()[2][f.getAresta(2).getP1()], (int) m.getMatriz()[1][f.getAresta(2).getP1()],
                (int) m.getMatriz()[2][f.getAresta(2).getP2()], (int) m.getMatriz()[1][f.getAresta(2).getP2()]);

        g.drawLine((int) m.getMatriz()[2][f.getAresta(3).getP1()], (int) m.getMatriz()[1][f.getAresta(3).getP1()],
                (int) m.getMatriz()[2][f.getAresta(3).getP2()], (int) m.getMatriz()[1][f.getAresta(3).getP2()]);
    }

    private void valoresPontosFaces(Face f, Matriz m, int a, int b) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        System.out.println((int) m.getMatriz()[a][f.getAresta(0).getP1()] + " " + (int) m.getMatriz()[b][f.getAresta(0).getP1()]
                + " " + (int) m.getMatriz()[a][f.getAresta(0).getP2()] + " " + (int) m.getMatriz()[b][f.getAresta(0).getP2()]);

        System.out.println((int) m.getMatriz()[a][f.getAresta(1).getP1()] + " " + (int) m.getMatriz()[b][f.getAresta(1).getP1()]
                + " " + (int) m.getMatriz()[a][f.getAresta(1).getP2()] + " " + (int) m.getMatriz()[b][f.getAresta(1).getP2()]);

        System.out.println((int) m.getMatriz()[a][f.getAresta(2).getP1()] + " " + (int) m.getMatriz()[b][f.getAresta(2).getP1()]
                + " " + (int) m.getMatriz()[a][f.getAresta(2).getP2()] + " " + (int) m.getMatriz()[b][f.getAresta(2).getP2()]);

        System.out.println((int) m.getMatriz()[a][f.getAresta(3).getP1()] + " " + (int) m.getMatriz()[b][f.getAresta(3).getP1()]
                + " " + (int) m.getMatriz()[a][f.getAresta(3).getP2()] + " " + (int) m.getMatriz()[b][f.getAresta(3).getP2()]);
    }

    public void desenhoFacePerspectiva(Face f, Matriz m) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        g.drawLine((int) m.getMatriz()[0][f.getAresta(0).getP1()], (int) m.getMatriz()[1][f.getAresta(0).getP1()],
                (int) m.getMatriz()[0][f.getAresta(0).getP2()], (int) m.getMatriz()[1][f.getAresta(0).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(1).getP1()], (int) m.getMatriz()[1][f.getAresta(1).getP1()],
                (int) m.getMatriz()[0][f.getAresta(1).getP2()], (int) m.getMatriz()[1][f.getAresta(1).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(2).getP1()], (int) m.getMatriz()[1][f.getAresta(2).getP1()],
                (int) m.getMatriz()[0][f.getAresta(2).getP2()], (int) m.getMatriz()[1][f.getAresta(2).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(3).getP1()], (int) m.getMatriz()[1][f.getAresta(3).getP1()],
                (int) m.getMatriz()[0][f.getAresta(3).getP2()], (int) m.getMatriz()[1][f.getAresta(3).getP2()]);
    }

    public void desenhoFaceIsometrica(Face f, Matriz m) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        g.drawLine((int) m.getMatriz()[0][f.getAresta(0).getP1()], (int) m.getMatriz()[1][f.getAresta(0).getP1()],
                (int) m.getMatriz()[0][f.getAresta(0).getP2()], (int) m.getMatriz()[1][f.getAresta(0).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(1).getP1()], (int) m.getMatriz()[1][f.getAresta(1).getP1()],
                (int) m.getMatriz()[0][f.getAresta(1).getP2()], (int) m.getMatriz()[1][f.getAresta(1).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(2).getP1()], (int) m.getMatriz()[1][f.getAresta(2).getP1()],
                (int) m.getMatriz()[0][f.getAresta(2).getP2()], (int) m.getMatriz()[1][f.getAresta(2).getP2()]);

        g.drawLine((int) m.getMatriz()[0][f.getAresta(3).getP1()], (int) m.getMatriz()[1][f.getAresta(3).getP1()],
                (int) m.getMatriz()[0][f.getAresta(3).getP2()], (int) m.getMatriz()[1][f.getAresta(3).getP2()]);
    }

    public void apagarTodosPrimitivasPerspectiva(ArrayList<ListaPrimitivas> listaPrimitivas) {
        g = getGraphics();
        g.setColor(Color.WHITE);
        int numFaces;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                for (int j = 0; j < numFaces; j++) {
                    desenhoFacePerspectiva(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatrizPerspectiva());
                }
            }
        }
    }

    public void apagarTodosPrimitivasIsometrica(ArrayList<ListaPrimitivas> listaPrimitivas) {
        g = getGraphics();
        g.setColor(Color.WHITE);
        int numFaces;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                for (int j = 0; j < numFaces; j++) {
                    desenhoFaceIsometrica(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatrizIsometrica());
                }
            }
        }
    }

    public void apagarTodosPrimitivasFrente(ArrayList<ListaPrimitivas> listaPrimitivas) {
        g = getGraphics();
        g.setColor(Color.WHITE);
        int numFaces;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                for (int j = 0; j < numFaces; j++) {
                    desenhoFaceFrente(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatriz());
                }
            }
        }
    }

    public void apagarTodosPrimitivasTopo(ArrayList<ListaPrimitivas> listaPrimitivas) {
        g = getGraphics();
        g.setColor(Color.WHITE);
        int numFaces;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                for (int j = 0; j < numFaces; j++) {
                    desenhoFaceTopo(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatriz());
                }
            }
        }
    }

    public void apagarTodosPrimitivasLado(ArrayList<ListaPrimitivas> listaPrimitivas) {
        g = getGraphics();
        g.setColor(Color.WHITE);
        int numFaces;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                numFaces = primitiva.getListaPrimitivas().get(i).getQTDFaces();
                for (int j = 0; j < numFaces; j++) {
                    desenhoFaceLado(primitiva.getListaPrimitivas().get(i).getFaces()[j], primitiva.getListaPrimitivas().get(i).getMatriz());
                }
            }
        }
    }

    public void visibilidadeFacesFrente(ArrayList<ListaPrimitivas> listaPrimitivas) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        double a, b, c, raiz;
        int p1, p2, p3, ang;
        Primitiva aux;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                aux = primitiva.getListaPrimitivas().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatriz().getAnguloRotacaoZ());
                //Face que está na frente
                if (ang < 180) {
                    p1 = aux.getFaces()[0].getAresta(2).getP2();
                    p2 = aux.getFaces()[0].getAresta(1).getP2();
                    p3 = aux.getFaces()[0].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[0].getAresta(2).getP2();
                    p2 = aux.getFaces()[0].getAresta(1).getP2();
                    p1 = aux.getFaces()[0].getAresta(1).getP1();
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
                        desenhoFaceFrente(aux.getFaces()[0], aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getFaces()[0], aux.getMatriz());
                    }
                }

                //Face que está na esquerda
                if (ang < 180) {
                    p1 = aux.getFaces()[1].getAresta(2).getP2();
                    p2 = aux.getFaces()[1].getAresta(1).getP1();
                    p3 = aux.getFaces()[1].getAresta(1).getP2();
                } else {
                    p3 = aux.getFaces()[1].getAresta(2).getP2();
                    p2 = aux.getFaces()[1].getAresta(1).getP1();
                    p1 = aux.getFaces()[1].getAresta(1).getP2();
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
                        desenhoFaceFrente(aux.getFaces()[1], aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getFaces()[1], aux.getMatriz());
                    }
                }

                //Face que está atrás
                if (ang < 180) {
                    p1 = aux.getFaces()[2].getAresta(3).getP1();
                    p2 = aux.getFaces()[2].getAresta(0).getP1();
                    p3 = aux.getFaces()[2].getAresta(0).getP2();
                } else {
                    p3 = aux.getFaces()[2].getAresta(3).getP1();
                    p2 = aux.getFaces()[2].getAresta(0).getP1();
                    p1 = aux.getFaces()[2].getAresta(0).getP2();
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
                        desenhoFaceFrente(aux.getFaces()[2], aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getFaces()[2], aux.getMatriz());
                    }
                }

                //Face que está na direita
                if (ang < 180) {
                    p1 = aux.getFaces()[3].getAresta(2).getP1();
                    p2 = aux.getFaces()[3].getAresta(1).getP2();
                    p3 = aux.getFaces()[3].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[3].getAresta(2).getP1();
                    p2 = aux.getFaces()[3].getAresta(1).getP2();
                    p1 = aux.getFaces()[3].getAresta(1).getP1();
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
                        desenhoFaceFrente(aux.getFaces()[3], aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getFaces()[3], aux.getMatriz());
                    }
                }

                //Face que está no topo
                if (ang < 180) {
                    p1 = aux.getFaces()[4].getAresta(2).getP1();
                    p2 = aux.getFaces()[4].getAresta(1).getP2();
                    p3 = aux.getFaces()[4].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[4].getAresta(2).getP1();
                    p2 = aux.getFaces()[4].getAresta(1).getP2();
                    p1 = aux.getFaces()[4].getAresta(1).getP1();
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
                        desenhoFaceFrente(aux.getFaces()[4], aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getFaces()[4], aux.getMatriz());
                    }
                }

                //Face que está abaixo
                if (ang < 180) {
                    p1 = aux.getFaces()[5].getAresta(2).getP2();
                    p2 = aux.getFaces()[5].getAresta(1).getP2();
                    p3 = aux.getFaces()[5].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[5].getAresta(2).getP2();
                    p2 = aux.getFaces()[5].getAresta(1).getP2();
                    p1 = aux.getFaces()[5].getAresta(1).getP1();
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
                        desenhoFaceFrente(aux.getFaces()[5], aux.getMatriz());
                    }
                } else {
                    if (c >= 0) {
                        desenhoFaceFrente(aux.getFaces()[5], aux.getMatriz());
                    }
                }
            }
        }
    }

    public void visibilidadeFacesTopo(ArrayList<ListaPrimitivas> listaPrimitivas) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        double a, b, c, raiz;
        int p1, p2, p3, ang;
        Primitiva aux;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                aux = primitiva.getListaPrimitivas().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatriz().getAnguloRotacaoY());
                //Face que está na frente
                p1 = aux.getFaces()[0].getAresta(2).getP2();
                p2 = aux.getFaces()[0].getAresta(1).getP2();
                p3 = aux.getFaces()[0].getAresta(1).getP1();

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
                    desenhoFaceTopo(aux.getFaces()[0], aux.getMatriz());
                }

                //Face que está na esquerda
                p1 = aux.getFaces()[1].getAresta(2).getP2();
                p2 = aux.getFaces()[1].getAresta(1).getP1();
                p3 = aux.getFaces()[1].getAresta(1).getP2();

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
                    desenhoFaceTopo(aux.getFaces()[1], aux.getMatriz());
                }

                //Face que está atrás
                p1 = aux.getFaces()[2].getAresta(3).getP1();
                p2 = aux.getFaces()[2].getAresta(0).getP1();
                p3 = aux.getFaces()[2].getAresta(0).getP2();

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
                    desenhoFaceTopo(aux.getFaces()[2], aux.getMatriz());
                }

                //Face que está na direita
                p1 = aux.getFaces()[3].getAresta(2).getP1();
                p2 = aux.getFaces()[3].getAresta(1).getP2();
                p3 = aux.getFaces()[3].getAresta(1).getP1();

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
                    desenhoFaceTopo(aux.getFaces()[3], aux.getMatriz());
                }

                //Face que está no topo
                p1 = aux.getFaces()[4].getAresta(2).getP1();
                p2 = aux.getFaces()[4].getAresta(1).getP2();
                p3 = aux.getFaces()[4].getAresta(1).getP1();

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
                    desenhoFaceTopo(aux.getFaces()[4], aux.getMatriz());
                }

                //Face que está abaixo
                p1 = aux.getFaces()[5].getAresta(2).getP2();
                p2 = aux.getFaces()[5].getAresta(1).getP2();
                p3 = aux.getFaces()[5].getAresta(1).getP1();

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
                    desenhoFaceTopo(aux.getFaces()[5], aux.getMatriz());
                }
            }
        }
    }

    public void visibilidadeFacesLado(ArrayList<ListaPrimitivas> listaPrimitivas) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        double a, b, c, raiz;
        int p1, p2, p3, ang;
        Primitiva aux;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                aux = primitiva.getListaPrimitivas().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatriz().getAnguloRotacaoX());
                //Face que está na frente
                p1 = aux.getFaces()[0].getAresta(2).getP2();
                p2 = aux.getFaces()[0].getAresta(1).getP2();
                p3 = aux.getFaces()[0].getAresta(1).getP1();

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
                    desenhoFaceLado(aux.getFaces()[0], aux.getMatriz());
                }

                //Face que está na esquerda
                p1 = aux.getFaces()[1].getAresta(2).getP2();
                p2 = aux.getFaces()[1].getAresta(1).getP1();
                p3 = aux.getFaces()[1].getAresta(1).getP2();

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
                    desenhoFaceLado(aux.getFaces()[1], aux.getMatriz());
                }

                //Face que está atrás
                p1 = aux.getFaces()[2].getAresta(3).getP1();
                p2 = aux.getFaces()[2].getAresta(0).getP1();
                p3 = aux.getFaces()[2].getAresta(0).getP2();

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
                    desenhoFaceLado(aux.getFaces()[2], aux.getMatriz());
                }

                //Face que está na direita
                p1 = aux.getFaces()[3].getAresta(2).getP1();
                p2 = aux.getFaces()[3].getAresta(1).getP2();
                p3 = aux.getFaces()[3].getAresta(1).getP1();

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
                    desenhoFaceLado(aux.getFaces()[3], aux.getMatriz());
                }

                //Face que está no topo
                p1 = aux.getFaces()[4].getAresta(2).getP1();
                p2 = aux.getFaces()[4].getAresta(1).getP2();
                p3 = aux.getFaces()[4].getAresta(1).getP1();

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
                    desenhoFaceLado(aux.getFaces()[4], aux.getMatriz());
                }

                //Face que está abaixo
                p1 = aux.getFaces()[5].getAresta(2).getP2();
                p2 = aux.getFaces()[5].getAresta(1).getP2();
                p3 = aux.getFaces()[5].getAresta(1).getP1();

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
                    desenhoFaceLado(aux.getFaces()[5], aux.getMatriz());
                }
            }
        }
    }

    public void visibilidadesFacesPerspectiva(ArrayList<ListaPrimitivas> listaPrimitivas) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        double a, b, c, raiz, R;
        int p1, p2, p3, ang;
        Primitiva aux;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                aux = primitiva.getListaPrimitivas().get(i);
                g.setColor(aux.getCorBordas());
                ang = Math.abs(aux.getMatrizPerspectiva().getAnguloRotacaoZ());
                //Face que está na frente
                if (ang < 180) {
                    p1 = aux.getFaces()[0].getAresta(2).getP2();
                    p2 = aux.getFaces()[0].getAresta(1).getP2();
                    p3 = aux.getFaces()[0].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[0].getAresta(2).getP2();
                    p2 = aux.getFaces()[0].getAresta(1).getP2();
                    p1 = aux.getFaces()[0].getAresta(1).getP1();
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
                        desenhoFacePerspectiva(aux.getFaces()[0], aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getFaces()[0], aux.getMatrizPerspectiva());
                    }
                }

                //Face que está na esquerda
                if (ang < 180) {
                    p1 = aux.getFaces()[1].getAresta(2).getP2();
                    p2 = aux.getFaces()[1].getAresta(1).getP1();
                    p3 = aux.getFaces()[1].getAresta(1).getP2();
                } else {
                    p3 = aux.getFaces()[1].getAresta(2).getP2();
                    p2 = aux.getFaces()[1].getAresta(1).getP1();
                    p1 = aux.getFaces()[1].getAresta(1).getP2();
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
                        desenhoFacePerspectiva(aux.getFaces()[1], aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getFaces()[1], aux.getMatrizPerspectiva());
                    }
                }

                //Face que está atrás
                if (ang < 180) {
                    p1 = aux.getFaces()[2].getAresta(3).getP1();
                    p2 = aux.getFaces()[2].getAresta(0).getP1();
                    p3 = aux.getFaces()[2].getAresta(0).getP2();
                } else {
                    p3 = aux.getFaces()[2].getAresta(3).getP1();
                    p2 = aux.getFaces()[2].getAresta(0).getP1();
                    p1 = aux.getFaces()[2].getAresta(0).getP2();
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
                        desenhoFacePerspectiva(aux.getFaces()[2], aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getFaces()[2], aux.getMatrizPerspectiva());
                    }
                }

                //Face que está na direita
                if (ang < 180) {
                    p1 = aux.getFaces()[3].getAresta(2).getP1();
                    p2 = aux.getFaces()[3].getAresta(1).getP2();
                    p3 = aux.getFaces()[3].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[3].getAresta(2).getP1();
                    p2 = aux.getFaces()[3].getAresta(1).getP2();
                    p1 = aux.getFaces()[3].getAresta(1).getP1();
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
                        desenhoFacePerspectiva(aux.getFaces()[3], aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getFaces()[3], aux.getMatrizPerspectiva());
                    }
                }

                //Face que está no topo
                if (ang < 180) {
                    p1 = aux.getFaces()[4].getAresta(2).getP1();
                    p2 = aux.getFaces()[4].getAresta(1).getP2();
                    p3 = aux.getFaces()[4].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[4].getAresta(2).getP1();
                    p2 = aux.getFaces()[4].getAresta(1).getP2();
                    p1 = aux.getFaces()[4].getAresta(1).getP1();
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
                        desenhoFacePerspectiva(aux.getFaces()[4], aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getFaces()[4], aux.getMatrizPerspectiva());
                    }
                }

                //Face que está abaixo
                if (ang < 180) {
                    p1 = aux.getFaces()[5].getAresta(2).getP2();
                    p2 = aux.getFaces()[5].getAresta(1).getP2();
                    p3 = aux.getFaces()[5].getAresta(1).getP1();
                } else {
                    p3 = aux.getFaces()[5].getAresta(2).getP2();
                    p2 = aux.getFaces()[5].getAresta(1).getP2();
                    p1 = aux.getFaces()[5].getAresta(1).getP1();
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
                        desenhoFacePerspectiva(aux.getFaces()[5], aux.getMatrizPerspectiva());
                    }
                } else {
                    if (R >= 0) {
                        desenhoFacePerspectiva(aux.getFaces()[5], aux.getMatrizPerspectiva());
                    }
                }
            }
        }
    }

    public void visibilidadeFacesIsometrica(ArrayList<ListaPrimitivas> listaPrimitivas) {
        //****************MODIFICAR METODO PARA USAR NUMERO VARIAVEL DE FACES
        double a, b, c, raiz, R;
        int p1, p2, p3;
        Primitiva aux;
        for (ListaPrimitivas primitiva : listaPrimitivas) {
            for (int i = 0; i < primitiva.getListaPrimitivas().size(); i++) {
                aux = primitiva.getListaPrimitivas().get(i);
                g.setColor(aux.getCorBordas());
                //Face que está na frente
                p1 = aux.getFaces()[0].getAresta(2).getP2();
                p2 = aux.getFaces()[0].getAresta(1).getP2();
                p3 = aux.getFaces()[0].getAresta(1).getP1();

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
                    desenhoFaceIsometrica(aux.getFaces()[0], aux.getMatrizIsometrica());
                }

                //Face que está na esquerda
                p1 = aux.getFaces()[1].getAresta(2).getP2();
                p2 = aux.getFaces()[1].getAresta(1).getP1();
                p3 = aux.getFaces()[1].getAresta(1).getP2();

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
                    desenhoFaceIsometrica(aux.getFaces()[1], aux.getMatrizIsometrica());
                }

                //Face que está atrás
                p1 = aux.getFaces()[2].getAresta(3).getP1();
                p2 = aux.getFaces()[2].getAresta(0).getP1();
                p3 = aux.getFaces()[2].getAresta(0).getP2();

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
                    desenhoFaceIsometrica(aux.getFaces()[2], aux.getMatrizIsometrica());
                }

                //Face que está na direita
                p1 = aux.getFaces()[3].getAresta(2).getP1();
                p2 = aux.getFaces()[3].getAresta(1).getP2();
                p3 = aux.getFaces()[3].getAresta(1).getP1();

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
                    desenhoFaceIsometrica(aux.getFaces()[3], aux.getMatrizIsometrica());
                }

                //Face que está no topo
                p1 = aux.getFaces()[4].getAresta(2).getP1();
                p2 = aux.getFaces()[4].getAresta(1).getP2();
                p3 = aux.getFaces()[4].getAresta(1).getP1();

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
                    desenhoFaceIsometrica(aux.getFaces()[4], aux.getMatrizIsometrica());
                }

                //Face que está abaixo
                p1 = aux.getFaces()[5].getAresta(2).getP2();
                p2 = aux.getFaces()[5].getAresta(1).getP2();
                p3 = aux.getFaces()[5].getAresta(1).getP1();

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
                    desenhoFaceIsometrica(aux.getFaces()[5], aux.getMatrizIsometrica());
                }
            }
        }
    }

    public void apagarPrimitiva(ListaPrimitivas listaPrimitivas) {
        g.setColor(Color.WHITE);
        Primitiva primitiva;
        int numFaces;
        for (int i = 0; i < listaPrimitivas.getListaPrimitivas().size(); i++) {
            primitiva = listaPrimitivas.getListaPrimitivas().get(i);
            numFaces = primitiva.getQTDFaces();
            for (int j = 0; j < numFaces; j++) {
                desenhoFaceFrente(primitiva.getFaces()[j], primitiva.getMatriz());
                desenhoFaceTopo(primitiva.getFaces()[j], primitiva.getMatriz());
                desenhoFaceLado(primitiva.getFaces()[j], primitiva.getMatriz());
            }
        }
    }

    public void apagarPrimitivaProjecao(ListaPrimitivas listaPrimitivas, int n) {
        g.setColor(Color.WHITE);
        Primitiva primitiva;
        int numFaces;
        if (n == 1) {
            for (int i = 0; i < listaPrimitivas.getListaPrimitivas().size(); i++) {
                primitiva = listaPrimitivas.getListaPrimitivas().get(i);
                numFaces = primitiva.getQTDFaces();
                for (int j = 0; j < numFaces; j++) {
                    desenhoFacePerspectiva(primitiva.getFaces()[j], primitiva.getMatrizPerspectiva());
                }
            }
        } else {
            if (n == 2) {
                for (int i = 0; i < listaPrimitivas.getListaPrimitivas().size(); i++) {
                    primitiva = listaPrimitivas.getListaPrimitivas().get(i);
                    numFaces = primitiva.getQTDFaces();
                    for (int j = 0; j < numFaces; j++) {
                        desenhoFaceIsometrica(primitiva.getFaces()[j], primitiva.getMatrizIsometrica());
                    }
                }
            }
        }
    }

    public boolean existemPrimitivasPossiveisFrente(ArrayList<ListaPrimitivas> cb, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        primitivasPossiveis.clear();
        primitivasPossiveis = new ArrayList<>();
        for (int i = 0; i < cb.size(); i++) {
            if (clickDentroPrimitivaFrente(cb.get(i), cord1, cord2)) {
                primitivasPossiveis.add(cb.get(i));
                //System.out.println("possivel " + i);
            }
        }
        return !primitivasPossiveis.isEmpty();
    }

    public boolean existemPrimitivasPossiveisTopo(ArrayList<ListaPrimitivas> cb, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        primitivasPossiveis.clear();
        primitivasPossiveis = new ArrayList<>();
        for (int i = 0; i < cb.size(); i++) {
            if (clickDentroPrimitivaTopo(cb.get(i), cord1, cord2)) {
                primitivasPossiveis.add(cb.get(i));
            }
        }
        return !primitivasPossiveis.isEmpty();
    }

    public boolean existemPrimitivasPossiveisLado(ArrayList<ListaPrimitivas> cb, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        primitivasPossiveis.clear();
        primitivasPossiveis = new ArrayList<>();
        for (int i = 0; i < cb.size(); i++) {
            //Invertido por conta da ordenada y invertida no painel do java
            if (clickDentroPrimitivaLado(cb.get(i), cord1, cord2)) {
                primitivasPossiveis.add(cb.get(i));
            }
        }
        return !primitivasPossiveis.isEmpty();
    }

    public boolean clickDentroPrimitivaFrente(ListaPrimitivas c, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        if (cord1 >= c.getExtremoEsquerda() && cord1 <= c.getExtremoDireita()) {
            if (cord2 >= c.getExtremoCima() && cord2 <= c.getExtremoBaixo()) {
                return true;
            }
        }
        return false;
        //return (clickDentroPrimitiva(c, cord1, cord2, 0, 1));
    }

    public boolean clickDentroPrimitivaTopo(ListaPrimitivas c, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        if (cord1 >= c.getExtremoEsquerda() && cord1 <= c.getExtremoDireita()) {
            if (cord2 >= c.getExtremoTras() && cord2 <= c.getExtremoFrente()) {
                return true;
            }
        }
        return false;
        //return (clickDentroPrimitiva(c, cord1, cord2, 0, 2));
    }

    public boolean clickDentroPrimitivaLado(ListaPrimitivas c, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        //Invertido por conta da ordenada y invertida no painel do java
        if (cord1 >= c.getExtremoTras() && cord1 <= c.getExtremoFrente()) {
            if (cord2 >= c.getExtremoCima() && cord2 <= c.getExtremoBaixo()) {
                return true;
            }
        }
        return false;
        //return (clickDentroPrimitiva(c, cord1, cord2, 2, 1));
    }

    public int selecionarPrimitivaFrente(ArrayList<ListaPrimitivas> listaPrimitivas, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        double menorDist = 999999999, dist;
        int selec = 0;
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            dist = listaPrimitivas.get(i).distanciaCentroPontoXY(cord1, cord2);
            if (dist < menorDist) {
                menorDist = dist;
                selec = i;
            }
            //System.out.println("distancia " + menorDist);
        }
        return selec;
    }

    public int selecionarPrimitivaTopo(ArrayList<ListaPrimitivas> listaPrimitivas, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        double menorDist = 999999999, dist;
        int selec = 0;
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            dist = listaPrimitivas.get(i).distanciaCentroPontoXZ(cord1, cord2);
            if (dist < menorDist) {
                menorDist = dist;
                selec = i;
            }
        }
        return selec;
    }

    public int selecionarPrimitivaLado(ArrayList<ListaPrimitivas> listaPrimitivas, int cord1, int cord2) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        double menorDist = 999999999, dist;
        int selec = 0;
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            //Invertido por causa do painel do java
            dist = listaPrimitivas.get(i).distanciaCentroPontoYZ(cord2, cord1);
            if (dist < menorDist) {
                menorDist = dist;
                selec = i;
            }
        }
        return selec;
    }

    public void pintarSelecaoFrente(ListaPrimitivas c, Color cor) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        //****************MODIFICAR A PINTURA DA SELECAO PARA CONTORNAR AS BORDAS DO OBJETO
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

    public void pintarSelecaoTopo(ListaPrimitivas c, Color cor) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        //****************MODIFICAR A PINTURA DA SELECAO PARA CONTORNAR AS BORDAS DO OBJETO
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

    public void pintarSelecaoLado(ListaPrimitivas c, Color cor) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        //****************MODIFICAR A PINTURA DA SELECAO PARA CONTORNAR AS BORDAS DO OBJETO
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

    public int getPrimitivaMaisProximoFrente(ArrayList<ListaPrimitivas> listaPrimitivas, int sel) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        double menorDist = 999999999, dist;
        double cX, cY;
        cX = listaPrimitivas.get(sel).getCentroX();
        cY = listaPrimitivas.get(sel).getCentroY();
        int index = -1;
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            if (i != sel) {
                dist = listaPrimitivas.get(i).distanciaCentroPontoXY(cX, cY);
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

    public int getPrimitivaMaisProximoTopo(ArrayList<ListaPrimitivas> listaPrimitivas, int sel) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        double menorDist = 999999999, dist;
        double cX, cZ;
        cX = listaPrimitivas.get(sel).getCentroX();
        cZ = listaPrimitivas.get(sel).getCentroZ();
        int index = -1;
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            if (i != sel) {
                dist = listaPrimitivas.get(i).distanciaCentroPontoXZ(cX, cZ);
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

    public int getPrimitivaMaisProximoLado(ArrayList<ListaPrimitivas> listaPrimitivas, int sel) {
        //****************MODIFICAR METODO PARA USAR COM QUALQUER TIPO DE PRIMITIVA
        double menorDist = 999999999, dist;
        double cY, cZ;
        cY = listaPrimitivas.get(sel).getCentroY();
        cZ = listaPrimitivas.get(sel).getCentroZ();
        int index = -1;
        for (int i = 0; i < listaPrimitivas.size(); i++) {
            if (i != sel) {
                dist = listaPrimitivas.get(i).distanciaCentroPontoYZ(cY, cZ);
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
