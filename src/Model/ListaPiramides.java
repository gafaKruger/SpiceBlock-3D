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
public class ListaPiramides {

    private ArrayList<Piramide> listaPiramides;

    void removerTodasPiramides() {
        int i = listaPiramides.size() - 1;
        while (!listaPiramides.isEmpty()) {
            listaPiramides.remove(i);
            i--;
        }
    }
    
}
