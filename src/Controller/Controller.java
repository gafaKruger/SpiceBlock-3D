
package Controller;

import Model.ConfigCubo;
import Model.Cubo;
import Model.ListaCubos;
import Model.Listas;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Controller implements Serializable {
    private final Listas listas;
    private final ListaCubos listaCubos;
    private ConfigCubo Ccubo;
    //private Cubo auxCubo;
    //private Color corBorda, corPreenchimento;
    //private Painel painel;

    public Controller() {
        Ccubo = new ConfigCubo();
        listas = new Listas();
        listaCubos = new ListaCubos();
        //corBorda = Color.BLACK;
        //corPreenchimento = Color.BLACK;
    }
    
    public boolean criarCubo (int a, int b, int op, int altura, int largura) {
        if (op == 1) {
            Cubo c = Ccubo.criaCubo(a, b, 100, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        if (op == 2) {
            Cubo c = Ccubo.criaCubo(a, 100, b, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        if (op == 3) {
            //A ordenada Y é invertida no painel do Java, obrigando a trocar a ordem das ordenadas
            Cubo c = Ccubo.criaCubo(100, b, a, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        return true;
    }
    
    public boolean criarCubo (int a, int b, int op, Color borda, int altura, int largura) {
        if (op == 1) {
            Cubo c = Ccubo.criaCubo(a, b, 100, borda, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        if (op == 2) {
            Cubo c = Ccubo.criaCubo(a, 100, b, borda, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        if (op == 3) {
            //A ordenada Y é invertida no painel do Java, obrigando a trocar a ordem das ordenadas
            Cubo c = Ccubo.criaCubo(100, b, a, borda, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        return true;
    }
    
    public boolean criarCubo (int a, int b, int op, Color borda, Color preenc, int altura, int largura) {
        if (op == 1) {
            Cubo c = Ccubo.criaCubo(a, b, 100, borda, preenc, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        if (op == 2) {
            Cubo c = Ccubo.criaCubo(a, 100, b, borda, preenc, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        if (op == 3) {
            //A ordenada Y é invertida no painel do Java, obrigando a trocar a ordem das ordenadas
            Cubo c = Ccubo.criaCubo(100, b, a, borda, preenc, altura, largura);
            if (c != null) {
                adicionarCubo(c);
                return true;
            } else {
                return false;
            }
        } 
        return true;
    }
    
    public void setConfiguradorCubo (ConfigCubo c) {
        this.Ccubo = c;
    }
    
    public void adicionarCubo (Cubo c) {
        ListaCubos listaAux = new ListaCubos();
        listaAux.adicionarCubo(c);
        listas.getCubos().add(listaAux);
    }
    
    public void limparListas () {
        listas.limparListas();
    }
    
    public ArrayList<ListaCubos> getListaCubos () {
        return listas.getCubos();
    }
    
    public ListaCubos getCubo (int i) {
        return listas.getCubos().get(i);
    }
    
    public ListaCubos excluirCubo (int i) {
        return listas.getCubos().remove(i);
    }
    
    public void desagrupar (int i) {
        listas.desagruparCubos(i);
    }
    
    public void agruparDoisCubos (int i, int j) {
        listas.agruparDoisCubos(i, j);
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
