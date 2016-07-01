
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
    /*private final ArrayList<ListaCubos> listaCub;
    private final ArrayList<ListaPiramides> listaPiram;
    private final ArrayList<ListaPrismas> listaPri;
    private final ArrayList<ListaEsferas> listaEsf;
    private final ArrayList<ListaCones> listaCone;
    private final ArrayList<ListaCilindros> listaCil;*/
    private final ArrayList<ListaPrimitivas> listaPrimitivas;
    
    public Listas () {
        //listaCubos = new ArrayList<>();
        //listaCubosAgrupados = new ArrayList<>();
        //listaAgrupados = new ArrayList<>();
        /*listaCub = new ArrayList<>();
        listaCil = new ArrayList<>();
        listaCone = new ArrayList<>();
        listaEsf = new ArrayList<>();
        listaPri = new ArrayList<>();
        listaPiram = new ArrayList<>();*/
        listaPrimitivas = new ArrayList<>();
    }

    //*Focar nos seguintes métodos
    public ArrayList<ListaPrimitivas> getPrimitivas() {
        return listaPrimitivas;
    }
    
    public void adicionarPrimitiva (ListaPrimitivas listaP) {
        listaPrimitivas.add(listaP);
    }
    
    public void agruparPrimitivas () {
        
    }
    
    public void desagruparPrimitivas () {
        
    }
    //*Até aqui    
    
    /*
    public void adicionarCuboLista (ListaCubos listaC) {
        listaCub.add(listaC);
    }
    
    public void adicionarPiramideLista (ListaPiramides listaPi) {
        listaPiram.add(listaPi);
    }
    
    public void adicionarPrismaLista (ListaPrismas listaPr) {
        listaPri.add(listaPr);
    }
    
    public void adicionarEsferaLista (ListaEsferas listaE) {
        listaEsf.add(listaE);
    }
    
    public void adicionarConeLista (ListaCones listaCo) {
        listaCone.add(listaCo);
    }
    
    public void adicionarCilindroLista (ListaCilindros listaCi) {
        listaCil.add(listaCi);
    }
    
    public ArrayList<ListaCubos> getCubos () {
        return listaCub;
    }
    
    public ArrayList<ListaPrismas> getPrismas () {
        return listaPri;
    }
    
    public ArrayList<ListaCilindros> getCilindros () {
        return listaCil;
    }
    
    public ArrayList<ListaEsferas> getEsferas () {
        return listaEsf;
    }
    
    public ArrayList<ListaCones> getCones () {
        return listaCone;
    }
    
    public ArrayList<ListaPiramides> getPiramides () {
        return listaPiram;
    }
    */
    
    /*
    public void agruparDoisCubos (int index1, int index2) {
        //fazer algo para "grudar" os cubos ao agrupar
        Cubo c;
        Angulos angulos = new Angulos();
        ListaCubos a;
        a = listaCub.get(index1);
        for (int i = a.getListaCubos().size()-1; i >= 0; i--) {
            c = a.getListaCubos().remove(i);
            c.setAgrupado(true);
            double x = 0, y = 0, z = 0;
            x = listaCub.get(index2).getCentroX();
            y = listaCub.get(index2).getCentroY();
            z = listaCub.get(index2).getCentroZ();
            int erro1 = 60, erro2 = 30;
            if (c.getMatriz().getExtremoDireita() > listaCub.get(index2).getExtremoEsquerda()+erro2) {
                x = x - erro1;
            } else {
                if (c.getMatriz().getExtremoEsquerda() > listaCub.get(index2).getExtremoEsquerda()+erro2) {
                    x = x + erro1;
                }
            }
            if (c.getMatriz().getExtremoCima() < listaCub.get(index2).getExtremoCima()-erro2) {
                y = y - erro1;
            } else {
                if (c.getMatriz().getExtremoCima() > listaCub.get(index2).getExtremoCima()+erro2) {
                    y = y + erro1;
                }
            }
            if (c.getMatriz().getExtremoFrente() > listaCub.get(index2).getExtremoTras()+erro2) {
                z = z + erro1;
            } else {
                if (c.getMatriz().getExtremoTras() > listaCub.get(index2).getExtremoTras()+erro2) {
                    z = z - erro1;
                }
            }
            c.getMatriz().refazerMatriz(x, y, z);
            if (c.getMatriz().getAnguloRotacaoX() != listaCub.get(index2).getAnguloRotacaoX()) {
                int ang = listaCub.get(index2).getAnguloRotacaoX();
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
            if (c.getMatriz().getAnguloRotacaoY() != listaCub.get(index2).getAnguloRotacaoY()) {
                int ang = listaCub.get(index2).getAnguloRotacaoY();
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
            if (c.getMatriz().getAnguloRotacaoZ() != listaCub.get(index2).getAnguloRotacaoZ()) {
                int ang = listaCub.get(index2).getAnguloRotacaoZ();
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
            listaCub.get(index2).getListaCubos().add(c);
        }
        listaCub.get(index2).calculoCentroGeometrico();
        listaCub.get(index2).setarAgrupamentoCubos();
        a = listaCub.remove(index1);
    }
    */
    
    public void agruparDoisCubos (int index1, int index2) {
        
    }
    
    public void agruparDoisCilindros () {
        
    }
    
    public void agruparDoisCones () {
        
    }
    
    public void agruparDuasEsferas () {
        
    }
    
    public void agruparDuasPiramides () {
        
    }
    
    public void agruparDoisPrismas () {
        
    }
    
    /*
    public void desagruparCubos (int index) {
        Cubo c;
        ListaCubos l;
        ArrayList<Cubo> a;
        double x, y, z;
        for (int i = listaCub.get(index).getListaCubos().size()-1; i >= 0; i--) {
            x = listaCub.get(index).getCentroX();
            y = listaCub.get(index).getCentroY();
            z = listaCub.get(index).getCentroZ();
            c = listaCub.get(index).getListaCubos().remove(i);
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
            listaCub.add(l);            
            listaCub.get(listaCub.size()-1).calculoCentroGeometrico();
            listaCub.get(listaCub.size()-1).setarAgrupamentoCubos();
        }
        if (listaCub.get(index).getListaCubos().isEmpty()) {
            listaCub.remove(index);
        } 
    }
    */
    
    public void desagruparCubos (int index) {
        
    }
    
    public void desagruparCilindros () {
        
    }
    
    public void desagruparCones () {
        
    }
    
    public void desagruparEsferas () {
        
    }
    
    public void desagruparPiramides () {
        
    }
    
    public void desagruparPrismas () {
        
    }
    
    public void limparListas () {
        int i;
        //Primitivas
        i = listaPrimitivas.size()-1;
        while (!listaPrimitivas.isEmpty()) {
            //listaPrimitivas.get(i).removerTodasPrimitivas();
            listaPrimitivas.remove(i);
            i--;
        }
        /*
        //Cubos
        i = listaCub.size()-1;
        while (!listaCub.isEmpty()) {
            listaCub.get(i).removerTodosCubos();
            listaCub.remove(i);
            i--;
        }
        //Piramides
        i = listaPiram.size()-1;
        while (!listaPiram.isEmpty()) {
            listaPiram.get(i).removerTodasPiramides();
            listaPiram.remove(i);
            i--;
        }
        //Prismas
        i = listaPri.size()-1;
        while (!listaPri.isEmpty()) {
            listaPri.get(i).removerTodosPrismas();
            listaPri.remove(i);
            i--;
        }
        //Esferas
        i = listaEsf.size()-1;
        while (!listaEsf.isEmpty()) {
            listaEsf.get(i).removerTodasEsferas();
            listaEsf.remove(i);
            i--;
        }
        //Cones
        i = listaCone.size()-1;
        while (!listaCone.isEmpty()) {
            listaCone.get(i).removerTodosCones();
            listaCone.remove(i);
            i--;
        }
        //Cilindros
        i = listaCil.size()-1;
        while (!listaCil.isEmpty()) {
            listaCil.get(i).removerTodosCilindros();
            listaCil.remove(i);
            i--;
        }
        */
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
        }
        */
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
