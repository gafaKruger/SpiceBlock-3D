
package Model;

import java.awt.Color;
import java.io.Serializable;

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
    private Face F1, F2, F3, F4, F5, F6;
    //Cubo
    private Cubo C;
    //Identificadores
    private long countArestas, countFaces, countCubos;
    
    public ConfigCubo () {
        countArestas = countCubos = countFaces = 0;
    }
    
    public Cubo criaCubo (int centroX, int centroY, int centroZ, int altura, int largura) {
        Matriz M = new Matriz (centroX, centroY, centroZ, countCubos, altura, largura);
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
        //Faces
        //Frente
        F1 = new Face(A1, A2, A3, A4, countFaces);
        countFaces++;
        //Esquerda
        F2 = new Face(A5, A4, A8, A12, countFaces);
        countFaces++;
        //Trás
        F3 = new Face(A9, A10, A11, A12, countFaces);
        countFaces++;
        //Direita
        F4 = new Face(A6, A10, A7, A2, countFaces);
        countFaces++;
        //Cima
        F5 = new Face(A5, A9, A6, A1, countFaces);
        countFaces++;
        //Baixo
        F6 = new Face(A3, A7, A11, A8, countFaces);
        countFaces++;
        //Cubo
        C = new Cubo(F1, F2, F3, F4, F5, F6, countCubos);
        countCubos++;
        C.setMatriz(M);
        return C;
    }
    
    public Cubo criaCubo (int centroX, int centroY, int centroZ, Color borda, int altura, int largura) {
        Matriz M = new Matriz (centroX, centroY, centroZ, countCubos, altura, largura);
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
        //Faces
        F1 = new Face(A1, A2, A3, A4, countFaces);
        countFaces++;
        F2 = new Face(A5, A4, A8, A12, countFaces);
        countFaces++;
        F3 = new Face(A9, A10, A11, A12, countFaces);
        countFaces++;
        F4 = new Face(A6, A10, A7, A2, countFaces);
        countFaces++;
        F5 = new Face(A5, A9, A6, A1, countFaces);
        countFaces++;
        F6 = new Face(A3, A7, A11, A8, countFaces);
        countFaces++;
        //Cubo
        C = new Cubo(F1, F2, F3, F4, F5, F6, countCubos, borda);
        countCubos++;
        C.setMatriz(M);
        return C;
    }
    
    public Cubo criaCubo (int centroX, int centroY, int centroZ, Color borda, Color preenc, int altura, int largura) {
        Matriz M = new Matriz (centroX, centroY, centroZ, countCubos, altura, largura);
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
        //Faces
        F1 = new Face(A1, A2, A3, A4, countFaces);
        countFaces++;
        F2 = new Face(A5, A4, A8, A12, countFaces);
        countFaces++;
        F3 = new Face(A9, A10, A11, A12, countFaces);
        countFaces++;
        F4 = new Face(A6, A10, A7, A2, countFaces);
        countFaces++;
        F5 = new Face(A5, A9, A6, A1, countFaces);
        countFaces++;
        F6 = new Face(A3, A7, A11, A8, countFaces);
        countFaces++;
        //Cubo
        C = new Cubo(F1, F2, F3, F4, F5, F6, countCubos, borda, preenc);
        countCubos++;
        C.setMatriz(M);
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
