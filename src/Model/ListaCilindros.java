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
public class ListaCilindros {

    private ArrayList<Cilindro> listaCilindros;

    void removerTodosCilindros() {
        int i = listaCilindros.size() - 1;
        while (!listaCilindros.isEmpty()) {
            listaCilindros.remove(i);
            i--;
        }
    }
}
