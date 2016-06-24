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
public class ListaCones {
    
    private ArrayList<Cone> listaCones;

    void removerTodosCones() {
        int i = listaCones.size() - 1;
        while (!listaCones.isEmpty()) {
            listaCones.remove(i);
            i--;
        }
    }

}
