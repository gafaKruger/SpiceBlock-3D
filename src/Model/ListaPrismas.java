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
public class ListaPrismas {
    
    private ArrayList<Prisma> listaPrismas;

    void removerTodosPrismas() {
        int i = listaPrismas.size() - 1;
        while (!listaPrismas.isEmpty()) {
            listaPrismas.remove(i);
            i--;
        }
    }

}
