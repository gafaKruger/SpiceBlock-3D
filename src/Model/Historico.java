
package Model;

import java.util.ArrayList;

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Historico {
    private ArrayList<String> nomeOperacao;
    private ArrayList<Double> valorOperacao;
    
    public Historico () {
        nomeOperacao = new ArrayList<>();
        valorOperacao = new ArrayList<>();
    }

    public ArrayList<String> getNomeOperacao() {
        return nomeOperacao;
    }

    public void setNomeOperacao(ArrayList<String> nomeOperacao) {
        this.nomeOperacao = nomeOperacao;
    }

    public ArrayList<Double> getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(ArrayList<Double> valorOperacao) {
        this.valorOperacao = valorOperacao;
    }
    
    
}
