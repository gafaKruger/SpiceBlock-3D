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
public class BotaoPrisma extends BotaoPrimitivaExtendido {
    
    public BotaoPrisma () {
        super ();
        this.setText("");
        this.setName("");
        this.setSelected(false);
        this.setSize(32, 32);
        this.setToolTipText("Desenhar Prismas");
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/prisma2.png")));
        //addActionListener();
    }
}
