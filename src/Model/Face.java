
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Face implements Serializable {
    private Aresta A1, A2, A3, A4;
    //modificando para obter faces com quantidade variável de arestas
    private Aresta arestas[];
    private int dimensao;
    private long ID;
    
    public Face () {
        dimensao = 0;
        ID = -1;
    }
    
    public Face (int t, long id) {
        dimensao = t;
        ID = id;
        arestas = new Aresta[t];
    }
    
    //Método específico para faces de 4 lados
    public Face (Aresta a1, Aresta a2, Aresta a3, Aresta a4) {
        this.A1 = a1;
        this.A2 = a2;
        this.A3 = a3;
        this.A4 = a4;
    }
    
    //Método específico para faces de 4 lados
    public Face (Aresta a1, Aresta a2, Aresta a3, Aresta a4, long id) {
        this.A1 = a1;
        this.A2 = a2;
        this.A3 = a3;
        this.A4 = a4;
        this.ID = id;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    
    public Aresta getAresta (int p) {
        return arestas[p];
    }
    
    public void setAresta (int p, Aresta a) {
        arestas[p] = a;
    }
    
    public Aresta[] getTodasArestas () {
        return arestas;
    }
    
    public void definirArestas (ArrayList<Aresta> a) {
        if (a.size() == dimensao) {
            for (int i = 0; i < dimensao; i++) {
                arestas[i] = a.get(i);
            }
        } else {
            System.out.println("Erro: configurações de dimensão de arestas incompatíveis");
        }
    }

    //Método específico para faces de 4 lados
    public Aresta getA1() {
        return A1;
    }

    //Método específico para faces de 4 lados
    public void setA1(Aresta A1) {
        this.A1 = A1;
    }

    //Método específico para faces de 4 lados
    public Aresta getA2() {
        return A2;
    }

    //Método específico para faces de 4 lados
    public void setA2(Aresta A2) {
        this.A2 = A2;
    }

    //Método específico para faces de 4 lados
    public Aresta getA3() {
        return A3;
    }

    //Método específico para faces de 4 lados
    public void setA3(Aresta A3) {
        this.A3 = A3;
    }

    //Método específico para faces de 4 lados
    public Aresta getA4() {
        return A4;
    }

    //Método específico para faces de 4 lados
    public void setA4(Aresta A4) {
        this.A4 = A4;
    }
}
