/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExtendedElements;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class BotaoCone extends BotaoPrimitivaExtendido {
    
    public BotaoCone () {
        super ();
        this.setText("");
        this.setName("");
        this.setSelected(false);
        this.setSize(20, 20);
        this.setToolTipText("Desenhar Cones");
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cone2.png")));
        //addActionListener();
    }
}
