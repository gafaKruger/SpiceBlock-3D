
package Controller;

import Model.Cilindro;
import Model.Cone;
import Model.Cubo;
import Model.Esfera;
import Model.Listas;
import Model.Piramide;
import Model.Primitiva;
import Model.Prisma;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import Model.ListaPrimitivas;
/*import Model.ListaCilindros;
import Model.ListaCones;
import Model.ListaCubos;
import Model.ListaEsferas;
import Model.ListaPiramides;
import Model.ListaPrismas;*/

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Controller implements Serializable {
    private final Listas listas;
    private ControlEsfera cEsf;
    private ControlCilindro cCil;
    private ControlCone cCone;
    private ControlCubo cCubo;
    private ControlPiramide cPir;
    private ControlPrisma cPris;
    //private Cubo auxCubo;
    //private Color corBorda, corPreenchimento;
    //private Painel painel;

    public Controller() {
        listas = new Listas();
        cEsf = new ControlEsfera();
        cCil = new ControlCilindro();
        cCone = new ControlCone();
        cCubo = new ControlCubo();
        cPir = new ControlPiramide();
        cPris = new ControlPrisma();
        //corBorda = Color.BLACK;
        //corPreenchimento = Color.BLACK;
    }
    
    public void limparListas () {
        listas.limparListas();
    }
    
    //*Focar nos seguintes metodos
    public void adicionarPrimitiva(Primitiva p) {
        ListaPrimitivas listaAux = new ListaPrimitivas();
        listaAux.adicionarPrimitiva(p);
        listas.getPrimitivas().add(listaAux);
    }
    
    public boolean criarCubo (int a, int b, int op, int altura, int largura) {
        Cubo c;
        c = cCubo.criarCubo(a, b, op, altura, largura);
        if (c != null) {
            adicionarPrimitiva(c);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean criarCubo (int a, int b, int op, Color borda, int altura, int largura) {
        Cubo c;
        c = cCubo.criarCubo(a, b, op, borda, altura, largura);
        if (c != null) {
            adicionarPrimitiva(c);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean criarCubo (int a, int b, int op, Color borda, Color preenc, int altura, int largura) {
        Cubo c;
        c = cCubo.criarCubo(a, b, op, borda, preenc, altura, largura);
        if (c != null) {
            adicionarPrimitiva(c);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean criarEsfera () {
        Esfera e;
        e = cEsf.criarEsfera();
        if (e != null) {
            adicionarPrimitiva(e);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean criarPrisma () {
        Prisma p;
        p = cPris.criarPrisma();
        if (p != null) {
            adicionarPrimitiva(p);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean criarCilindro () {
        Cilindro c;
        c = cCil.criarCilindro();
        if (c != null) {
            adicionarPrimitiva(c);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean criarPiramide () {
        Piramide p;
        p = cPir.criarPiramide();
        if (p != null) {
            adicionarPrimitiva(p);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean criarCone () {
        Cone c;
        c = cCone.criarCone();
        if (c != null) {
            adicionarPrimitiva(c);
            return true;
        } else {
            return false;
        }
    }
    
    public ArrayList<ListaPrimitivas> getListaPrimitivas () {
        return listas.getPrimitivas();
    }
    
    public ListaPrimitivas getPrimitiva (int i) {
        return listas.getPrimitivas().get(i);
    }
            
    public ListaPrimitivas excluirPrimitiva (int i) {
        return listas.getPrimitivas().remove(i);
    }
    //*Ate aqui
    
    /*
    public void adicionarCubo (Cubo c) {
        ListaCubos listaAux = new ListaCubos();
        listaAux.adicionarCubo(c);
        listas.getCubos().add(listaAux);
    }
    
    public void adicionarEsfera (Esfera e) {
        ListaEsferas listaAux = new ListaEsferas();
        listaAux.adicionarEsfera(e);
        listas.getEsferas().add(listaAux);
    }
    
    public void adicionarCilindro (Cilindro c) {
        ListaCilindros listaAux = new ListaCilindros();
        listaAux.adicionarCilindro(c);
        listas.getCilindros().add(listaAux);
    }
    
    public void adicionarCone (Cone c) {
        ListaCones listaAux = new ListaCones();
        listaAux.adicionarCone(c);
        listas.getCones().add(listaAux);
    }
    
    public void adicionarPiramide (Piramide p) {
        ListaPiramides listaAux = new ListaPiramides();
        listaAux.adicionarPiramide(p);
        listas.getPiramides().add(listaAux);
    }
    
    public void adicionarPrisma (Prisma p) {
        ListaPrismas listaAux = new ListaPrismas();
        listaAux.adicionarPrisma(p);
        listas.getPrismas().add(listaAux);
    }
    
    public ArrayList<ListaCubos> getListaCubos () {
        return listas.getCubos();
    }
    
    public ArrayList<ListaEsferas> getListaEsferas () {
        return listas.getEsferas();
    }
    
    public ArrayList<ListaCilindros> getListaCilindros () {
        return listas.getCilindros();
    }
    
    public ArrayList<ListaCones> getListaCones () {
        return listas.getCones();
    }
    
    public ArrayList<ListaPiramides> getListaPiramides () {
        return listas.getPiramides();
    }
    
    public ArrayList<ListaPrismas> getListaPrismas () {
        return listas.getPrismas();
    }
    
    public ListaCubos getCubo (int i) {
        return listas.getCubos().get(i);
    }
    
    public ListaEsferas getEsfera (int i) {
        return listas.getEsferas().get(i);
    }
    
    public ListaCilindros getCilindro (int i) {
        return listas.getCilindros().get(i);
    }
    
    public ListaCones getCone (int i) {
        return listas.getCones().get(i);
    }
    
    public ListaPiramides getPiramide (int i) {
        return listas.getPiramides().get(i);
    }
    
    public ListaPrismas getPrisma (int i) {
        return listas.getPrismas().get(i);
    }
    
    public ListaCubos excluirCubo (int i) {
        return listas.getCubos().remove(i);
    }
    
    public ListaCilindros excluirCilindro (int i) {
        return listas.getCilindros().remove(i);
    }
    
    public ListaCones excluirCone (int i) {
        return listas.getCones().remove(i);
    }
    
    public ListaEsferas excluirEsfera (int i) {
        return listas.getEsferas().remove(i);
    }
    
    public ListaPiramides excluirPiramide (int i) {
        return listas.getPiramides().remove(i);
    }
    
    public ListaPrismas excluirPrisma (int i) {
        return listas.getPrismas().remove(i);
    }
    */
    
    //*Verificar os seguintes metodos para fazer combinações entre as primitivas
    //agrupar duasPiramides, agruparPiramideCubo
    public void desagrupar (int i) {
        listas.desagruparCubos(i);
    }
    
    public void agruparDoisCubos (int i, int j) {
        listas.agruparDoisCubos(i, j);
    }
    //*Mudar até este método
    
    public ControlEsfera getcEsf() {
        return cEsf;
    }

    public void setcEsf(ControlEsfera cEsf) {
        this.cEsf = cEsf;
    }

    public ControlCilindro getcCil() {
        return cCil;
    }

    public void setcCil(ControlCilindro cCil) {
        this.cCil = cCil;
    }

    public ControlCone getcCone() {
        return cCone;
    }

    public void setcCone(ControlCone cCone) {
        this.cCone = cCone;
    }

    public ControlCubo getcCubo() {
        return cCubo;
    }

    public void setcCubo(ControlCubo cCubo) {
        this.cCubo = cCubo;
    }

    public ControlPiramide getcPir() {
        return cPir;
    }

    public void setcPir(ControlPiramide cPir) {
        this.cPir = cPir;
    }

    public ControlPrisma getcPris() {
        return cPris;
    }

    public void setcPris(ControlPrisma cPris) {
        this.cPris = cPris;
    }
    
    /*public Painel getPainel() {
        return painel;
    }

    public void setPainel(Painel painel) {
        this.painel = painel;
    }*/
    
    /*public void setCorBordaPainel () {
        painel.setCorBorda(corBorda);
    }
    
    public void setCorPreenchimentoPainel () {
        painel.setCorPreenchimento(corPreenchimento);
    }
    
    public Color getCorBordaPainel () {
        return painel.getCorBorda();
    }
    
    public Color getCorPreenchimentoPainel () {
        return painel.getCorPreenchimento();
    }*/
    
    /*public Color getCorBorda() {
        return corBorda;
    }

    public void setCorBorda(Color corB) {
        this.corBorda = corB;
    }

    public Color getCorPreenchimento() {
        return corPreenchimento;
    }
    
    public void setCorPreenchimento(Color corP) {
        this.corPreenchimento = corP;
    }*/
    
}
