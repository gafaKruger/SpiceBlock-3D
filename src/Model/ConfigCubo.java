
package Model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class ConfigCubo implements Serializable {
    //Pontos Frente
    //private Ponto P1, P2, P3, P4;
    //Pontos Tras
    //private Ponto P5, P6, P7, P8;
    //Arestas
    private Aresta A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12;
    //Faces
    private Face F[];
    //Cubo
    private Cubo C;
    //Identificadores
    private long countArestas, countFaces, countCubos;
    
    public ConfigCubo () {
        countArestas = countCubos = countFaces = 0;
        F = new Face[6];
    }
    
    public void criaArestas () {
        //Arestas
        A1 = new Aresta(0, 1, countArestas);
        countArestas++;
        A2 = new Aresta(1, 2, countArestas);
        countArestas++;
        A3 = new Aresta(2, 3, countArestas);
        countArestas++;
        A4 = new Aresta(3, 0, countArestas);
        countArestas++;
        A5 = new Aresta(0, 4, countArestas);
        countArestas++;
        A6 = new Aresta(1, 5, countArestas);
        countArestas++;
        A7 = new Aresta(2, 6, countArestas);
        countArestas++;
        A8 = new Aresta(3, 7, countArestas);
        countArestas++;
        A9 = new Aresta(4, 5, countArestas);
        countArestas++;
        A10 = new Aresta(5, 6, countArestas);
        countArestas++;
        A11 = new Aresta(6, 7, countArestas);
        countArestas++;
        A12 = new Aresta(7, 4, countArestas);
        countArestas++;
    }
    
    public void criaFaces (int d) {
        //d = numero de arestas na face
        ArrayList<Aresta> aux = new ArrayList<>();
        //Faces
        //Frente 
        aux.clear();
        aux.add(A1);
        aux.add(A2);
        aux.add(A3);
        aux.add(A4);
        F[0] = new Face(d, countFaces);
        F[0].definirArestas(aux);
        countFaces++;

        //F1 = new Face(A1, A2, A3, A4, countFaces);
        //countFaces++;
        
        //Esquerda
        aux.clear();
        aux.add(A5);
        aux.add(A4);
        aux.add(A8);
        aux.add(A12);
        F[1] = new Face(d, countFaces);
        F[1].definirArestas(aux);
        countFaces++;
        
        //F2 = new Face(A5, A4, A8, A12, countFaces);
        //countFaces++;
        
        //Trás
        aux.clear();
        aux.add(A9);
        aux.add(A10);
        aux.add(A11);
        aux.add(A12);
        F[2] = new Face(d, countFaces);
        F[2].definirArestas(aux);
        countFaces++;
        
        //F3 = new Face(A9, A10, A11, A12, countFaces);
        //countFaces++;
        
        //Direita
        aux.clear();
        aux.add(A6);
        aux.add(A10);
        aux.add(A7);
        aux.add(A2);
        F[3] = new Face(d, countFaces);
        F[3].definirArestas(aux);
        countFaces++;
        
        //F4 = new Face(A6, A10, A7, A2, countFaces);
        //countFaces++;
        
        //Cima
        aux.clear();
        aux.add(A5);
        aux.add(A9);
        aux.add(A6);
        aux.add(A1);
        F[4] = new Face(d, countFaces);
        F[4].definirArestas(aux);
        countFaces++;
        
        //F5 = new Face(A5, A9, A6, A1, countFaces);
        //countFaces++;
        
        //Baixo
        aux.clear();
        aux.add(A3);
        aux.add(A7);
        aux.add(A11);
        aux.add(A8);
        F[5] = new Face(d, countFaces);
        F[5].definirArestas(aux);
        countFaces++;
        
        //F6 = new Face(A3, A7, A11, A8, countFaces);
        //countFaces++;
    }
    
    public Cubo criaCubo (int centroX, int centroY, int centroZ, int altura, int largura) {
        Matriz M = new Matriz (centroX, centroY, centroZ, countCubos, altura, largura);
        criaArestas();
        criaFaces(4);
        //Cubo
        C = new Cubo(F, countCubos);
        countCubos++;
        C.setMatriz(M);
        C.setTipo(1);
        return C;
    }
    
    public Cubo criaCubo (int centroX, int centroY, int centroZ, Color borda, int altura, int largura) {
        Matriz M = new Matriz (centroX, centroY, centroZ, countCubos, altura, largura);
        criaArestas();
        criaFaces(4);
        //Cubo
        C = new Cubo(F, countCubos, borda);
        countCubos++;
        C.setMatriz(M);
        C.setTipo(1);
        return C;
    }
    
    public Cubo criaCubo (int centroX, int centroY, int centroZ, Color borda, Color preenc, int altura, int largura) {
        Matriz M = new Matriz (centroX, centroY, centroZ, countCubos, altura, largura);
        criaArestas();
        criaFaces(4);
        //Cubo
        C = new Cubo(F, countCubos, borda, preenc);
        countCubos++;
        C.setMatriz(M);
        C.setTipo(1);
        return C;
    }
    
    public Cubo getCubo () {
        return C;
    }
    
    /*public Cubo criaCubo () {
        //Pontos Frente
        P1 = new Ponto(100, 80, 120, countPontos);
        countPontos++;
        P2 = new Ponto(150, 80, 120, countPontos);
        countPontos++;
        P3 = new Ponto(150, 130, 120, countPontos);
        countPontos++;
        P4 = new Ponto(100, 130, 120, countPontos);
        countPontos++;
        //Pontos Tras
        P5 = new Ponto(120, 64, 70, countPontos);
        countPontos++;
        P6 = new Ponto(170, 64, 70, countPontos);
        countPontos++;
        P7 = new Ponto(170, 114, 70, countPontos);
        countPontos++;
        P8 = new Ponto(120, 114, 70, countPontos);
        countPontos++;
        //Arestas
        A1 = new Aresta(P1, P2, countArestas);
        countArestas++;
        A2 = new Aresta(P2, P3, countArestas);
        countArestas++;
        A3 = new Aresta(P3, P4, countArestas);
        countArestas++;
        A4 = new Aresta(P4, P1, countArestas);
        countArestas++;
        A5 = new Aresta(P1, P5, countArestas);
        countArestas++;
        A6 = new Aresta(P2, P6, countArestas);
        countArestas++;
        A7 = new Aresta(P3, P7, countArestas);
        countArestas++;
        A8 = new Aresta(P4, P8, countArestas);
        countArestas++;
        A9 = new Aresta(P5, P6, countArestas);
        countArestas++;
        A10 = new Aresta(P6, P7, countArestas);
        countArestas++;
        A11 = new Aresta(P7, P8, countArestas);
        countArestas++;
        A12 = new Aresta(P8, P5, countArestas);
        countArestas++;
        //Faces
        F1 = new Face(A1, A2, A3, A4, countFaces);
        countFaces++;
        F2 = new Face(A5, A4, A8, A12, countFaces);
        countFaces++;
        F3 = new Face(A9, A12, A11, A10, countFaces);
        countFaces++;
        F4 = new Face(A6, A10, A7, A2, countFaces);
        countFaces++;
        F5 = new Face(A5, A9, A6, A1, countFaces);
        countFaces++;
        F6 = new Face(A3, A7, A11, A8, countFaces);
        countFaces++;
        //Cubo
        C = new Cubo(F1, F2, F3, F4, F5, F6, countCubos);
        countCubos++;
        return C;
    }
    
    public Matriz gerarMatriz () {
        Matriz m = new Matriz(C, C.getID());
        return m;
    }*/
}
