/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ConfigCubo;
import Model.Cubo;
import java.awt.Color;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class ControlCubo {
    
    private ConfigCubo Ccubo;
    
    public ControlCubo () {
        Ccubo = new ConfigCubo();
    }
    
    public Cubo criarCubo (int a, int b, int op, int altura, int largura) {
        Cubo c = null;
        if (op == 1) {
            c = Ccubo.criaCubo(a, b, 100, altura, largura);
            return c;
        } 
        if (op == 2) {
            c = Ccubo.criaCubo(a, 100, b, altura, largura);
            return c;
        } 
        if (op == 3) {
            //A ordenada Y é invertida no painel do Java, obrigando a trocar a ordem das ordenadas
            c = Ccubo.criaCubo(100, b, a, altura, largura);
            return c;
        } 
        return c;
    }
    
    public Cubo criarCubo (int a, int b, int op, Color borda, int altura, int largura) {
        Cubo c = null;
        if (op == 1) {
            c = Ccubo.criaCubo(a, b, 100, borda, altura, largura);
            return c;
        } 
        if (op == 2) {
            c = Ccubo.criaCubo(a, 100, b, borda, altura, largura);
            return c;
        } 
        if (op == 3) {
            //A ordenada Y é invertida no painel do Java, obrigando a trocar a ordem das ordenadas
            c = Ccubo.criaCubo(100, b, a, borda, altura, largura);
            return c;
        } 
        return c;
    }
    
    public Cubo criarCubo (int a, int b, int op, Color borda, Color preenc, int altura, int largura) {
        Cubo c = null;
        if (op == 1) {
            c = Ccubo.criaCubo(a, b, 100, borda, preenc, altura, largura);
            return c;
        } 
        if (op == 2) {
            c = Ccubo.criaCubo(a, 100, b, borda, preenc, altura, largura);
            return c;
        } 
        if (op == 3) {
            //A ordenada Y é invertida no painel do Java, obrigando a trocar a ordem das ordenadas
            c = Ccubo.criaCubo(100, b, a, borda, preenc, altura, largura);
            return c;
        } 
        return c;
    }
    
    public void setConfiguradorCubo (ConfigCubo c) {
        this.Ccubo = c;
    }        
}
