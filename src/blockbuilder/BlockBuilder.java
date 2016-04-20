
package blockbuilder;

import Controller.Controlador;
import View.Painel;
import java.awt.Frame;
import javax.swing.UIManager;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class BlockBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try{
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception ex){}   
        Controlador C = new Controlador(); 
        Painel P = new Painel(C);
        //C.setPainel(P);
        P.setExtendedState(Frame.MAXIMIZED_BOTH);
        //P.setResizable(false);
        P.setVisible(true);
    }
    
}
