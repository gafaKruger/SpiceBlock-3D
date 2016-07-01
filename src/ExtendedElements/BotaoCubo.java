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
public class BotaoCubo extends BotaoPrimitivaExtendido {
    
    public BotaoCubo () {
        super ();
        this.setText("");
        this.setName("");
        this.setSelected(false);
        this.setSize(32, 32);
        this.setToolTipText("Desenhar Cubos");
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cubo2.png")));
        //addActionListener();
    }
}
