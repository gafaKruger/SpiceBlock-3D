/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExtendedElements;

/*import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.Component.LEFT_ALIGNMENT;
import javax.swing.Icon;*/
import javax.swing.JToggleButton;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class BotaoPrimitivaExtendido extends JToggleButton {
    
    public BotaoPrimitivaExtendido () {
        super("", false);
        this.setText("");
        this.setName("");
        this.setSelected(false);
        this.setSize(32, 32);
    }
    
    /*@Override
    protected void init(String text, Icon icon) {
        if(text != null) {
            setText(text);
        }

        if(icon != null) {
            setIcon(icon);
        }

        // Set the UI
        updateUI();

        setAlignmentX(LEFT_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }*/
}
