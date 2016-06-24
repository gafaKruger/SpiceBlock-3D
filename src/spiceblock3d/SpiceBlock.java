
package spiceblock3d;

import Controller.Controller;
import View.PainelPrincipal;
import java.awt.Frame;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class SpiceBlock {

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
        }catch(Exception ex){
            JOptionPane.showMessageDialog
                    (null, ex.getMessage(), "Erro ao carregar configurações de aparência (javax.swing.UIManager).", 
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }   
        Controller C = new Controller(); 
        PainelPrincipal P = new PainelPrincipal(C);
        //C.setPainel(P);
        P.setExtendedState(Frame.MAXIMIZED_BOTH);
        //P.setResizable(false);
        P.setVisible(true);
    }
    
}
