/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class ListaEsferas {
    
    private ArrayList<Esfera> listaEsferas;

    void removerTodasEsferas() {
        int i = listaEsferas.size() - 1;
        while (!listaEsferas.isEmpty()) {
            listaEsferas.remove(i);
            i--;
        }
    }

}
