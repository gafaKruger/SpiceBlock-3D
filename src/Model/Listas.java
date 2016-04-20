
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Listas implements Serializable {
    //private final ArrayList<Cubo> listaCubos;
    //private final ArrayList<Cubo> listaCubosAgrupados;
    //private final ArrayList<ArrayList> listaAgrupados;
    private final ArrayList<ListaCubos> lista;
    
    public Listas () {
        //listaCubos = new ArrayList<>();
        //listaCubosAgrupados = new ArrayList<>();
        //listaAgrupados = new ArrayList<>();
        lista = new ArrayList<>();
    }
    
    public void adicionarLista (ListaCubos listaC) {
        lista.add(listaC);
        
    }
    
    public ArrayList<ListaCubos> getCubos () {
        return lista;
    }
    
    public void agruparDoisCubos (int index1, int index2) {
        //fazer algo para "grudar" os cubos ao agrupar
        Cubo c;
        Angulos angulos = new Angulos();
        ListaCubos a;
        a = lista.get(index1);
        for (int i = a.getListaCubos().size()-1; i >= 0; i--) {
            c = a.getListaCubos().remove(i);
            c.setAgrupado(true);
            double x = 0, y = 0, z = 0;
            x = lista.get(index2).getCentroX();
            y = lista.get(index2).getCentroY();
            z = lista.get(index2).getCentroZ();
            int erro1 = 60, erro2 = 30;
            if (c.getMatriz().getExtremoDireita() > lista.get(index2).getExtremoEsquerda()+erro2) {
                x = x - erro1;
            } else {
                if (c.getMatriz().getExtremoEsquerda() > lista.get(index2).getExtremoEsquerda()+erro2) {
                    x = x + erro1;
                }
            }
            if (c.getMatriz().getExtremoCima() < lista.get(index2).getExtremoCima()-erro2) {
                y = y - erro1;
            } else {
                if (c.getMatriz().getExtremoCima() > lista.get(index2).getExtremoCima()+erro2) {
                    y = y + erro1;
                }
            }
            if (c.getMatriz().getExtremoFrente() > lista.get(index2).getExtremoTras()+erro2) {
                z = z + erro1;
            } else {
                if (c.getMatriz().getExtremoTras() > lista.get(index2).getExtremoTras()+erro2) {
                    z = z - erro1;
                }
            }
            c.getMatriz().refazerMatriz(x, y, z);
            if (c.getMatriz().getAnguloRotacaoX() != lista.get(index2).getAnguloRotacaoX()) {
                int ang = lista.get(index2).getAnguloRotacaoX();
                double sen, cos;
                if (ang >= 0) {
                    sen = angulos.getSenos()[360 - ang];
                    cos = angulos.getCossenos()[360 - ang];
                    //sen = angulos.getSenos()[ang];
                    //cos = angulos.getCossenos()[ang];
                } else {
                    int aux = Math.abs(ang);
                    sen = angulos.getSenos()[aux];
                    cos = angulos.getCossenos()[aux];
                    //sen = angulos.getSenos()[360 - aux];
                    //cos = angulos.getCossenos()[360 - aux];
                }
                c.getMatriz().rotacaoX(ang, sen, cos);
            }
            if (c.getMatriz().getAnguloRotacaoY() != lista.get(index2).getAnguloRotacaoY()) {
                int ang = lista.get(index2).getAnguloRotacaoY();
                double sen, cos;
                if (ang >= 0) {
                    sen = angulos.getSenos()[360 - ang];
                    cos = angulos.getCossenos()[360 - ang];
                    //sen = angulos.getSenos()[ang];
                    //cos = angulos.getCossenos()[ang];
                } else {
                    int aux = Math.abs(ang);
                    sen = angulos.getSenos()[aux];
                    cos = angulos.getCossenos()[aux];
                    //sen = angulos.getSenos()[360 - aux];
                    //cos = angulos.getCossenos()[360 - aux];
                }
                c.getMatriz().rotacaoY(ang, sen, cos);
            }
            if (c.getMatriz().getAnguloRotacaoZ() != lista.get(index2).getAnguloRotacaoZ()) {
                int ang = lista.get(index2).getAnguloRotacaoZ();
                double sen, cos;
                if (ang >= 0) {
                    sen = angulos.getSenos()[360 - ang];
                    cos = angulos.getCossenos()[360 - ang];
                    //sen = angulos.getSenos()[ang];
                    //cos = angulos.getCossenos()[ang];
                } else {
                    int aux = Math.abs(ang);
                    sen = angulos.getSenos()[aux];
                    cos = angulos.getCossenos()[aux];
                    //sen = angulos.getSenos()[360 - aux];
                    //cos = angulos.getCossenos()[360 - aux];
                }
                c.getMatriz().rotacaoZ(ang, sen, cos);
            }
            lista.get(index2).getListaCubos().add(c);
        }
        lista.get(index2).calculoCentroGeometrico();
        lista.get(index2).setarAgrupamentoCubos();
        a = lista.remove(index1);
    }
    
    public void desagruparCubos (int index) {
        Cubo c;
        ListaCubos l;
        ArrayList<Cubo> a;
        double x, y, z;
        for (int i = lista.get(index).getListaCubos().size()-1; i >= 0; i--) {
            x = lista.get(index).getCentroX();
            y = lista.get(index).getCentroY();
            z = lista.get(index).getCentroZ();
            c = lista.get(index).getListaCubos().remove(i);
            c.setAgrupado(false);
            //Distanciando os cubos
            //Ordenada X
            if (c.getMatriz().getExtremoDireita() < x) {
                for (int j = 0; j < 9; j++) {
                    c.getMatriz().getMatriz()[0][j] = c.getMatriz().getMatriz()[0][j] - 15;
                }
            } else {
                if (c.getMatriz().getExtremoEsquerda() > x) {
                    for (int j = 0; j < 9; j++) {
                        c.getMatriz().getMatriz()[0][j] = c.getMatriz().getMatriz()[0][j] + 15;
                    }
                }
            }
            //Ordenada Y
            if (c.getMatriz().getExtremoBaixo() < y) {
                for (int j = 0; j < 9; j++) {
                    c.getMatriz().getMatriz()[1][j] = c.getMatriz().getMatriz()[1][j] - 15;
                }
            } else {
                if (c.getMatriz().getExtremoCima() > y) {
                    for (int j = 0; j < 9; j++) {
                        c.getMatriz().getMatriz()[1][j] = c.getMatriz().getMatriz()[1][j] + 15;
                    }
                }
            }
            //Ordenada Z
            if (c.getMatriz().getExtremoFrente() < z) {
                for (int j = 0; j < 9; j++) {
                    c.getMatriz().getMatriz()[2][j] = c.getMatriz().getMatriz()[2][j] - 15;
                }
            } else {
                if (c.getMatriz().getExtremoTras() > z) {
                    for (int j = 0; j < 9; j++) {
                        c.getMatriz().getMatriz()[2][j] = c.getMatriz().getMatriz()[2][j] + 15;
                    }
                }
            }
            c.getMatriz().calculoExtremos();
            a = new ArrayList<>();
            a.add(c);
            l = new ListaCubos(a);
            lista.add(l);            
            lista.get(lista.size()-1).calculoCentroGeometrico();
            lista.get(lista.size()-1).setarAgrupamentoCubos();
        }
        if (lista.get(index).getListaCubos().isEmpty()) {
            lista.remove(index);
        } 
    }
    
    public void limparListas () {
        int i = lista.size()-1;
        while (!lista.isEmpty()) {
            lista.get(i).removerTodosCubos();
            lista.remove(i);
            i--;
        }
        /*int i = listaAgrupados.size()-1;
        while (!listaAgrupados.isEmpty()) {
            listaAgrupados.remove(i);
            i--;
        }
        
        i = listaCubosAgrupados.size()-1;
        while (!listaCubosAgrupados.isEmpty()) {
            listaCubosAgrupados.remove(i);
            i--;
        }
        
        i = listaCubos.size()-1;
        while (!listaCubos.isEmpty()) {
            listaCubos.remove(i);
            i--;
        }*/
    }

    /*public ArrayList<Cubo> getListaCubosAgrupados() {
        return listaCubosAgrupados;
    }

    public ArrayList<ArrayList> getListaAgrupados() {
        return listaAgrupados;
    }
    
    public void adcionarCuboAgrupado () {
        
    }*/
}
