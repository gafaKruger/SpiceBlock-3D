package View;

import Controller.Controlador;
import Model.Angulos;
import Model.ListaCubos;
import Model.Ponto;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
/*import java.beans.PropertyChangeEvent;
 import javax.swing.SpinnerModel;
 import javax.swing.SpinnerNumberModel;
 import javax.swing.event.ChangeEvent;
 import javax.swing.event.ChangeListener;
 import javax.swing.filechooser.FileNameExtensionFilter;*/

/**
 *
 * @author Rafael Fiori Kruger
 */
public class Painel extends javax.swing.JFrame {

    private Controlador control;
    private boolean alternaFrente, alternaTopo, alternaLado, alternaProjecao, plotDesenho, selecionaCubo;
    private boolean selecLado, selecFrente, selecTopo;
    private int cuboSelecIndice, cuboParaAgrupar, last;
    private boolean cuboIsSelected, alteracoesRealizadas, permiteAgrupar;
    private Color corSelecao;
    private int localClicadoX, localClicadoY, localClicadoZ;
    //private Color corBorda, corPreenchimento;
    private SeletorCores selecionaCorBorda, selecionaCorPreenchimento;
    private Angulos angulos;
    private Carregando carregaStatus;
    private Ajuda menuAjuda;
    /*private int valorAntigoRotacao, proximoRotacao, anteriorRotacao;
     private int valorAntigoEscala, proximoEscala, anteriorEscala;*/
    //private long nanoSegundosPressao;
    //teste
    //private ChangeListener PropertyChangeEvent;

    public Painel() {
        initialize();
    }

    public Painel(Controlador c) {
        this.control = c;
        initialize();
    }

    public void initialize() {
        initComponents();
        //grupo cores
        buttonGroupCores.add(SelecAmarelo);
        buttonGroupCores.add(SelecAzul);
        buttonGroupCores.add(SelecCiano);
        buttonGroupCores.add(SelecCinza);
        buttonGroupCores.add(SelecCinzaClaro);
        buttonGroupCores.add(SelecCinzaEscuro);
        buttonGroupCores.add(SelecLaranjado);
        buttonGroupCores.add(SelecMagenta);
        buttonGroupCores.add(SelecRosa);
        buttonGroupCores.add(SelecVerde);
        buttonGroupCores.add(SelecVermelho);
        buttonGroupCores.add(SelecPreto);
        SelecPreto.setSelected(true);
        //grupo projecao
        buttonGroupProjecao.add(SelecProjecaoIsometrica);
        buttonGroupProjecao.add(SelecProjecaoPerspectiva);
        SelecProjecaoPerspectiva.setSelected(true);
        //grupo sombreamento
        buttonGroupSombreamento.add(SombreamentoFlat);
        buttonGroupSombreamento.add(SombreamentoGouraud);
        buttonGroupSombreamento.add(SombreamentoNenhum);
        buttonGroupSombreamento.add(SombreamentoPhong);
        SombreamentoNenhum.setSelected(true);
        /*if (SelecProjecaoPerspectiva.isSelected()) {
         ((javax.swing.border.TitledBorder) PainelBaseProjecao.getBorder()).setTitle("Projeção - Perspectiva");
         }
         if (SelecProjecaoIsometrica.isSelected()) {
         ((javax.swing.border.TitledBorder) PainelBaseProjecao.getBorder()).setTitle("Projeção - Isométrica");
         }*/
        //corSelecao = Color.BLACK;
        //corBorda = Color.BLACK;
        selecionaCorBorda = new SeletorCores(Color.BLACK, true);
        selecionaCorPreenchimento = new SeletorCores(Color.RED, false);
        AlternarVisaoFrente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
        AlternarVisaoTopo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
        AlternarVisaoLado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
        AlternarVisaoProjecao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
        DesenharCubos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cubo.png")));
        ExcluirCubo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/lixeira.png")));
        BotaoDesagrupar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cubosDesagrupados.png")));
        alternaFrente = alternaTopo = alternaLado = alternaProjecao = selecionaCubo = true;
        plotDesenho = cuboIsSelected = false;
        selecFrente = selecLado = selecTopo = false;
        alteracoesRealizadas = false;
        ExcluirCubo.setEnabled(false);
        SpinnerEscala.setEnabled(false);
        SpinnerRotacao.setEnabled(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerRotacao.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerEscala.getEditor()).getTextField().setEditable(false);
        BotaoDesagrupar.setEnabled(false);
        SpinnerKA.setEnabled(false);
        SpinnerKD.setEnabled(false);
        SpinnerKS.setEnabled(false);
        SpinnerN.setEnabled(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerKA.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerKD.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerKS.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerN.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerLuzAmbiente.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerLuzPontual.getEditor()).getTextField().setEditable(false);

        ((javax.swing.JSpinner.DefaultEditor) SpinnerVRPX.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerVRPY.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerVRPZ.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerPX.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerPY.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerPZ.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerViewUpX.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerViewUpY.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerViewUpZ.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerDP.getEditor()).getTextField().setEditable(false);
        ((javax.swing.JSpinner.DefaultEditor) SpinnerVRPIsometrica.getEditor()).getTextField().setEditable(false);
        angulos = new Angulos();
        carregaStatus = new Carregando();
        last = -1;
        permiteAgrupar = false;
        menuAjuda = new Ajuda();
        /*BotaoFazerRotacao.setEnabled(false);
         BotaoFazerEscala.setEnabled(false);*/

        /*valorAntigoEscala = valorAntigoRotacao = 0;
         proximoRotacao = proximoEscala = 1;
         anteriorEscala = anteriorRotacao = -1;*/
        //teste
        //SpinnerRotacao.getModel().addChangeListener(PropertyChangeEvent);
        //SpinnerEscala = new SpinnerNumberModel(0, -359, 359, 1);
        //ChangeEvent e = new ChangeEvent(this);
        //PropertyChangeEvent.stateChanged(e);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupCores = new javax.swing.ButtonGroup();
        buttonGroupProjecao = new javax.swing.ButtonGroup();
        buttonGroupSombreamento = new javax.swing.ButtonGroup();
        PainelDesenho = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        OcultaFaces = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        SpinnerRotacao = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        SpinnerEscala = new javax.swing.JSpinner();
        ExcluirCubo = new javax.swing.JButton();
        BotaoDesagrupar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        SelecProjecaoPerspectiva = new javax.swing.JRadioButton();
        SelecProjecaoIsometrica = new javax.swing.JRadioButton();
        SelecionarCubos = new javax.swing.JToggleButton();
        DesenharCubos = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        PainelCor = new javax.swing.JPanel();
        BotaoCorBordas = new javax.swing.JButton();
        BotaoCorPreenchimento = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        SpinnerLuzAmbiente = new javax.swing.JSpinner();
        SpinnerLuzPontual = new javax.swing.JSpinner();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        SpinnerKA = new javax.swing.JSpinner();
        SpinnerKD = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        SpinnerKS = new javax.swing.JSpinner();
        SpinnerN = new javax.swing.JSpinner();
        PainelBaseFrente = new javax.swing.JPanel();
        PainelFrente = new View.PainelExtendido();
        AlternarVisaoFrente = new javax.swing.JButton();
        PainelBaseLado = new javax.swing.JPanel();
        PainelLado = new View.PainelExtendido();
        AlternarVisaoLado = new javax.swing.JButton();
        PainelBaseTopo = new javax.swing.JPanel();
        PainelTopo = new View.PainelExtendido();
        AlternarVisaoTopo = new javax.swing.JButton();
        PainelBaseProjecao = new javax.swing.JPanel();
        PainelProjecao = new View.PainelExtendido();
        AlternarVisaoProjecao = new javax.swing.JButton();
        PainelObservacao = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        SpinnerVRPX = new javax.swing.JSpinner();
        SpinnerPX = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        SpinnerVRPY = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        SpinnerVRPZ = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        SpinnerPY = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        SpinnerPZ = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        SpinnerViewUpX = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        SpinnerViewUpY = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        SpinnerViewUpZ = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        SpinnerDP = new javax.swing.JSpinner();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        SpinnerVRPIsometrica = new javax.swing.JSpinner();
        BarraDeMenu = new javax.swing.JMenuBar();
        Arquivo = new javax.swing.JMenu();
        AbrirProjeto = new javax.swing.JMenuItem();
        SalvarProjeto = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Sair = new javax.swing.JMenuItem();
        Editar = new javax.swing.JMenu();
        Sombreamento = new javax.swing.JMenu();
        SombreamentoNenhum = new javax.swing.JCheckBoxMenuItem();
        SombreamentoFlat = new javax.swing.JCheckBoxMenuItem();
        SombreamentoGouraud = new javax.swing.JCheckBoxMenuItem();
        SombreamentoPhong = new javax.swing.JCheckBoxMenuItem();
        CorSelec = new javax.swing.JMenu();
        SelecPreto = new javax.swing.JRadioButtonMenuItem();
        SelecVerde = new javax.swing.JRadioButtonMenuItem();
        SelecAzul = new javax.swing.JRadioButtonMenuItem();
        SelecVermelho = new javax.swing.JRadioButtonMenuItem();
        SelecAmarelo = new javax.swing.JRadioButtonMenuItem();
        SelecCiano = new javax.swing.JRadioButtonMenuItem();
        SelecLaranjado = new javax.swing.JRadioButtonMenuItem();
        SelecMagenta = new javax.swing.JRadioButtonMenuItem();
        SelecCinza = new javax.swing.JRadioButtonMenuItem();
        SelecCinzaClaro = new javax.swing.JRadioButtonMenuItem();
        SelecCinzaEscuro = new javax.swing.JRadioButtonMenuItem();
        SelecRosa = new javax.swing.JRadioButtonMenuItem();
        MenuAjuda = new javax.swing.JMenu();
        BotaoAjuda = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        BotaoSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BlockBuilder");
        setName("BlockBuilder"); // NOI18N
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                Painel.this.windowStateChanged(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                Painel.this.windowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Painel.this.windowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Painel.this.keyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Painel.this.keyTyped(evt);
            }
        });

        PainelDesenho.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Desenho"));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        OcultaFaces.setText("Ocultar Faces");
        OcultaFaces.setToolTipText("Habilitar ou Desabilitar Representação Wireframe");
        OcultaFaces.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mudaOpcaoOcultarFaces(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Rotacionar");

        SpinnerRotacao.setModel(new javax.swing.SpinnerNumberModel(0, -359, 359, 1));
        SpinnerRotacao.setToolTipText("Aumentar ou Dimininuir Ângulo de Rotação");
        SpinnerRotacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeSpinnerRotacao(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Escala (Z) ");

        SpinnerEscala.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), null, null, Float.valueOf(0.1f)));
        SpinnerEscala.setToolTipText("Aumentar ou Diminuir Razão de Escala (Coordenada Z)");
        SpinnerEscala.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changeSpinnerEscala(evt);
            }
        });

        ExcluirCubo.setToolTipText("Excluir um Cubo");
        ExcluirCubo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirCuboActionPerformed(evt);
            }
        });

        BotaoDesagrupar.setToolTipText("Desagrupar Cubos");
        BotaoDesagrupar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoDesagruparActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Projeção:");

        SelecProjecaoPerspectiva.setText("Perspectiva");
        SelecProjecaoPerspectiva.setToolTipText("Projeção em Perspectiva");
        SelecProjecaoPerspectiva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecPerspectivaMouseClicked(evt);
            }
        });

        SelecProjecaoIsometrica.setText("Isométrica");
        SelecProjecaoIsometrica.setToolTipText("Projeção Isométrica");
        SelecProjecaoIsometrica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecIsometricaMouseClicked(evt);
            }
        });

        SelecionarCubos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/selecao.png"))); // NOI18N
        SelecionarCubos.setToolTipText("Selecionar Cubos");
        SelecionarCubos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecionarCubosActionPerformed(evt);
            }
        });

        DesenharCubos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cubo.png"))); // NOI18N
        DesenharCubos.setToolTipText("Desenhar Cubos");
        DesenharCubos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesenharCubosActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Operações:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Visibilidade:");

        javax.swing.GroupLayout PainelDesenhoLayout = new javax.swing.GroupLayout(PainelDesenho);
        PainelDesenho.setLayout(PainelDesenhoLayout);
        PainelDesenhoLayout.setHorizontalGroup(
            PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDesenhoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(SelecionarCubos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(DesenharCubos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(ExcluirCubo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoDesagrupar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SelecProjecaoPerspectiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OcultaFaces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SelecProjecaoIsometrica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PainelDesenhoLayout.createSequentialGroup()
                        .addGroup(PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SpinnerEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerRotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelDesenhoLayout.setVerticalGroup(
            PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDesenhoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SelecionarCubos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoDesagrupar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExcluirCubo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DesenharCubos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
            .addGroup(PainelDesenhoLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinnerRotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(PainelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinnerEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PainelDesenhoLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(OcultaFaces, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SelecProjecaoPerspectiva, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SelecProjecaoIsometrica, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PainelCor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cor e Sombreamento"));

        BotaoCorBordas.setText("Cor Bordas");
        BotaoCorBordas.setToolTipText("Definir a Cor das Bordas do Cubo");
        BotaoCorBordas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCorBordasActionPerformed(evt);
            }
        });

        BotaoCorPreenchimento.setText("Cor Preenchimento");
        BotaoCorPreenchimento.setToolTipText("Definir a Cor de Preenchimento do Cubo");
        BotaoCorPreenchimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCorPreenchimentoActionPerformed(evt);
            }
        });

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Fontes Luminosas:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ambiente:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pontual:");

        SpinnerLuzAmbiente.setToolTipText("Editar Fonte de Iluminação Ambiente");

        SpinnerLuzPontual.setToolTipText("Editar Fonte de Iluminação Pontual");

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Materiais:");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Ka:");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Kd:");

        SpinnerKA.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.5f), null, null, Float.valueOf(0.1f)));
        SpinnerKA.setToolTipText("Editar Coeficiente de Reflexão Ambiente (Ka)");

        SpinnerKD.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.5f), null, null, Float.valueOf(0.1f)));
        SpinnerKD.setToolTipText("Editar Coeficiente de Reflexão Difusa (Kd)");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ks:");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("N:");

        SpinnerKS.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.5f), null, null, Float.valueOf(0.1f)));
        SpinnerKS.setToolTipText("Editar Coeficiente de Reflexão Especular (Ks)");

        SpinnerN.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
        SpinnerN.setToolTipText("Editar Aproximação da Distribuição Espacial da Luz Refletida Especularmente (N)");

        javax.swing.GroupLayout PainelCorLayout = new javax.swing.GroupLayout(PainelCor);
        PainelCor.setLayout(PainelCorLayout);
        PainelCorLayout.setHorizontalGroup(
            PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCorLayout.createSequentialGroup()
                .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BotaoCorPreenchimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotaoCorBordas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PainelCorLayout.createSequentialGroup()
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SpinnerLuzAmbiente, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(SpinnerLuzPontual)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PainelCorLayout.createSequentialGroup()
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SpinnerKA, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerKD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SpinnerKS, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(SpinnerN)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        PainelCorLayout.setVerticalGroup(
            PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(PainelCorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BotaoCorBordas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoCorPreenchimento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PainelCorLayout.createSequentialGroup()
                .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelCorLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerLuzAmbiente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerLuzPontual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelCorLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerKA, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerKS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerKD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PainelBaseFrente.setBackground(new java.awt.Color(255, 255, 255));
        PainelBaseFrente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Frente - XY"));

        PainelFrente.setBackground(new java.awt.Color(255, 255, 255));
        PainelFrente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        PainelFrente.setPreferredSize(new java.awt.Dimension(200, 200));
        PainelFrente.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moverCuboFrente(evt);
            }
        });
        PainelFrente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                operacoesCuboFrente(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mouseReleasedFrente(evt);
            }
        });
        PainelFrente.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizedFrente(evt);
            }
        });
        PainelFrente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyPressedFrente(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                keyTypedFrente(evt);
            }
        });

        AlternarVisaoFrente.setToolTipText("Alterna a Visão");
        AlternarVisaoFrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlternarVisaoFrenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelFrenteLayout = new javax.swing.GroupLayout(PainelFrente);
        PainelFrente.setLayout(PainelFrenteLayout);
        PainelFrenteLayout.setHorizontalGroup(
            PainelFrenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFrenteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(AlternarVisaoFrente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelFrenteLayout.setVerticalGroup(
            PainelFrenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelFrenteLayout.createSequentialGroup()
                .addComponent(AlternarVisaoFrente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 231, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PainelBaseFrenteLayout = new javax.swing.GroupLayout(PainelBaseFrente);
        PainelBaseFrente.setLayout(PainelBaseFrenteLayout);
        PainelBaseFrenteLayout.setHorizontalGroup(
            PainelBaseFrenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelFrente, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        PainelBaseFrenteLayout.setVerticalGroup(
            PainelBaseFrenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelFrente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        PainelBaseLado.setBackground(new java.awt.Color(255, 255, 255));
        PainelBaseLado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Lado (Esquerda) - ZY"));

        PainelLado.setBackground(new java.awt.Color(255, 255, 255));
        PainelLado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        PainelLado.setPreferredSize(new java.awt.Dimension(200, 200));
        PainelLado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moverCuboLado(evt);
            }
        });
        PainelLado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                operacoesCuboLado(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mouseReleasedLado(evt);
            }
        });
        PainelLado.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizedLado(evt);
            }
        });

        AlternarVisaoLado.setToolTipText("Alterna a Visão");
        AlternarVisaoLado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlternarVisaoLadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelLadoLayout = new javax.swing.GroupLayout(PainelLado);
        PainelLado.setLayout(PainelLadoLayout);
        PainelLadoLayout.setHorizontalGroup(
            PainelLadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelLadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(AlternarVisaoLado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelLadoLayout.setVerticalGroup(
            PainelLadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLadoLayout.createSequentialGroup()
                .addComponent(AlternarVisaoLado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 204, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PainelBaseLadoLayout = new javax.swing.GroupLayout(PainelBaseLado);
        PainelBaseLado.setLayout(PainelBaseLadoLayout);
        PainelBaseLadoLayout.setHorizontalGroup(
            PainelBaseLadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelLado, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        PainelBaseLadoLayout.setVerticalGroup(
            PainelBaseLadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelLado, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        PainelBaseTopo.setBackground(new java.awt.Color(255, 255, 255));
        PainelBaseTopo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Topo - XZ"));

        PainelTopo.setBackground(new java.awt.Color(255, 255, 255));
        PainelTopo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        PainelTopo.setPreferredSize(new java.awt.Dimension(200, 200));
        PainelTopo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moverCuboTopo(evt);
            }
        });
        PainelTopo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                operacoesCuboTopo(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mouseReleasedTopo(evt);
            }
        });
        PainelTopo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizedTopo(evt);
            }
        });

        AlternarVisaoTopo.setToolTipText("Alterna a Visão");
        AlternarVisaoTopo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlternarVisaoTopoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelTopoLayout = new javax.swing.GroupLayout(PainelTopo);
        PainelTopo.setLayout(PainelTopoLayout);
        PainelTopoLayout.setHorizontalGroup(
            PainelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelTopoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(AlternarVisaoTopo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelTopoLayout.setVerticalGroup(
            PainelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTopoLayout.createSequentialGroup()
                .addComponent(AlternarVisaoTopo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 231, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PainelBaseTopoLayout = new javax.swing.GroupLayout(PainelBaseTopo);
        PainelBaseTopo.setLayout(PainelBaseTopoLayout);
        PainelBaseTopoLayout.setHorizontalGroup(
            PainelBaseTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelTopo, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
        );
        PainelBaseTopoLayout.setVerticalGroup(
            PainelBaseTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelTopo, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        PainelBaseProjecao.setBackground(new java.awt.Color(255, 255, 255));
        PainelBaseProjecao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Projeção"));
        PainelBaseProjecao.setName(""); // NOI18N

        PainelProjecao.setBackground(new java.awt.Color(255, 255, 255));
        PainelProjecao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        PainelProjecao.setPreferredSize(new java.awt.Dimension(200, 200));
        PainelProjecao.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizedProjecao(evt);
            }
        });

        AlternarVisaoProjecao.setToolTipText("Alterna a Visão");
        AlternarVisaoProjecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlternarVisaoProjecaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelProjecaoLayout = new javax.swing.GroupLayout(PainelProjecao);
        PainelProjecao.setLayout(PainelProjecaoLayout);
        PainelProjecaoLayout.setHorizontalGroup(
            PainelProjecaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelProjecaoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(AlternarVisaoProjecao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelProjecaoLayout.setVerticalGroup(
            PainelProjecaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelProjecaoLayout.createSequentialGroup()
                .addComponent(AlternarVisaoProjecao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PainelBaseProjecaoLayout = new javax.swing.GroupLayout(PainelBaseProjecao);
        PainelBaseProjecao.setLayout(PainelBaseProjecaoLayout);
        PainelBaseProjecaoLayout.setHorizontalGroup(
            PainelBaseProjecaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelProjecao, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
        );
        PainelBaseProjecaoLayout.setVerticalGroup(
            PainelBaseProjecaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelProjecao, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        PainelObservacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Observação"));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("VRPX:");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("PX:");

        SpinnerVRPX.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(30), null, null, Integer.valueOf(1)));
        SpinnerVRPX.setToolTipText("Mudar o Valor do VRP em X");
        SpinnerVRPX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerVRPXChange(evt);
            }
        });

        SpinnerPX.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(30), null, null, Integer.valueOf(1)));
        SpinnerPX.setToolTipText("Mudar o Valor do Ponto em X ");
        SpinnerPX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerPXChange(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("VRPY:");

        SpinnerVRPY.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(50), null, null, Integer.valueOf(1)));
        SpinnerVRPY.setToolTipText("Mudar o Valor do VRP em Y");
        SpinnerVRPY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerVRPYChange(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("VRPZ:");

        SpinnerVRPZ.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(200), null, null, Integer.valueOf(1)));
        SpinnerVRPZ.setToolTipText("Mudar o Valor do VRP em Z");
        SpinnerVRPZ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinneVRPZChange(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("PY:");

        SpinnerPY.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(20), null, null, Integer.valueOf(1)));
        SpinnerPY.setToolTipText("Mudar o Valor do Ponto em Y");
        SpinnerPY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerPYChange(evt);
            }
        });

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("PZ:");

        SpinnerPZ.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(50), null, null, Integer.valueOf(1)));
        SpinnerPZ.setToolTipText("Mudar o Valor do Ponto em Z");
        SpinnerPZ.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerPZChange(evt);
            }
        });

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("VUpX:");
        jLabel20.setToolTipText("Vetor View Up (X)");

        SpinnerViewUpX.setModel(new javax.swing.SpinnerNumberModel());
        SpinnerViewUpX.setToolTipText("Mudar o Valor do Vetor View Up em X");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("VUpY:");
        jLabel21.setToolTipText("Vetor View Up (Y)");

        SpinnerViewUpY.setModel(new javax.swing.SpinnerNumberModel());
        SpinnerViewUpY.setToolTipText("Mudar o Valor do Vetor View Up em Y");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("VUpZ:");
        jLabel22.setToolTipText("Vetor View Up (Z)");

        SpinnerViewUpZ.setModel(new javax.swing.SpinnerNumberModel());
        SpinnerViewUpZ.setToolTipText("Mudar o Valor do Vetor View Up em Z");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("DP:");
        jLabel23.setToolTipText("Distância ao Plano de Projeção");

        SpinnerDP.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(50), null, null, Integer.valueOf(1)));
        SpinnerDP.setToolTipText("Mudar a Distância ao Plano de Projeção");
        SpinnerDP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerDPChange(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("VRP Iso:");

        SpinnerVRPIsometrica.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(150), null, null, Integer.valueOf(1)));
        SpinnerVRPIsometrica.setToolTipText("Alterar o VRP da Projeção Isométrica");
        SpinnerVRPIsometrica.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerSpinnerVRPIsometricaChange(evt);
            }
        });

        javax.swing.GroupLayout PainelObservacaoLayout = new javax.swing.GroupLayout(PainelObservacao);
        PainelObservacao.setLayout(PainelObservacaoLayout);
        PainelObservacaoLayout.setHorizontalGroup(
            PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelObservacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelObservacaoLayout.createSequentialGroup()
                        .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SpinnerPX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerViewUpX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelObservacaoLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SpinnerVRPX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(jLabel21))
                .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelObservacaoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SpinnerViewUpY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SpinnerViewUpZ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelObservacaoLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelObservacaoLayout.createSequentialGroup()
                                .addComponent(SpinnerVRPY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerVRPZ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelObservacaoLayout.createSequentialGroup()
                                .addComponent(SpinnerPY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerPZ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PainelObservacaoLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(SpinnerDP, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SpinnerVRPIsometrica, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelObservacaoLayout.setVerticalGroup(
            PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(PainelObservacaoLayout.createSequentialGroup()
                .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelObservacaoLayout.createSequentialGroup()
                        .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SpinnerVRPX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerVRPY, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerVRPZ, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerPX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerPY, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerPZ, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerViewUpX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerViewUpY, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerViewUpZ, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PainelObservacaoLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(SpinnerDP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SpinnerVRPIsometrica, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        Arquivo.setText("Arquivo");

        AbrirProjeto.setText("Abrir Projeto...");
        AbrirProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirProjetoActionPerformed(evt);
            }
        });
        Arquivo.add(AbrirProjeto);

        SalvarProjeto.setText("Salvar Projeto...");
        SalvarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarProjetoActionPerformed(evt);
            }
        });
        Arquivo.add(SalvarProjeto);
        Arquivo.add(jSeparator1);

        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });
        Arquivo.add(Sair);

        BarraDeMenu.add(Arquivo);

        Editar.setText("Editar");

        Sombreamento.setText("Sombreamento");

        SombreamentoNenhum.setSelected(true);
        SombreamentoNenhum.setText("Sem Sombreamento");
        Sombreamento.add(SombreamentoNenhum);

        SombreamentoFlat.setSelected(true);
        SombreamentoFlat.setText("Flat Shadding");
        Sombreamento.add(SombreamentoFlat);

        SombreamentoGouraud.setSelected(true);
        SombreamentoGouraud.setText("Gouraud");
        Sombreamento.add(SombreamentoGouraud);

        SombreamentoPhong.setSelected(true);
        SombreamentoPhong.setText("Phong");
        Sombreamento.add(SombreamentoPhong);

        Editar.add(Sombreamento);

        CorSelec.setText("Cor da Seleção");

        SelecPreto.setSelected(true);
        SelecPreto.setText("Preto");
        SelecPreto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecPretoActionPerformed(evt);
            }
        });
        CorSelec.add(SelecPreto);

        SelecVerde.setSelected(true);
        SelecVerde.setText("Verde");
        SelecVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecVerdeActionPerformed(evt);
            }
        });
        CorSelec.add(SelecVerde);

        SelecAzul.setSelected(true);
        SelecAzul.setText("Azul");
        SelecAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecAzulActionPerformed(evt);
            }
        });
        CorSelec.add(SelecAzul);

        SelecVermelho.setSelected(true);
        SelecVermelho.setText("Vermelho");
        SelecVermelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecVermelhoActionPerformed(evt);
            }
        });
        CorSelec.add(SelecVermelho);

        SelecAmarelo.setSelected(true);
        SelecAmarelo.setText("Amarelo");
        SelecAmarelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecAmareloActionPerformed(evt);
            }
        });
        CorSelec.add(SelecAmarelo);

        SelecCiano.setSelected(true);
        SelecCiano.setText("Ciano");
        SelecCiano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecCianoActionPerformed(evt);
            }
        });
        CorSelec.add(SelecCiano);

        SelecLaranjado.setSelected(true);
        SelecLaranjado.setText("Laranjado");
        SelecLaranjado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecLaranjadoActionPerformed(evt);
            }
        });
        CorSelec.add(SelecLaranjado);

        SelecMagenta.setSelected(true);
        SelecMagenta.setText("Magenta");
        SelecMagenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecMagentaActionPerformed(evt);
            }
        });
        CorSelec.add(SelecMagenta);

        SelecCinza.setSelected(true);
        SelecCinza.setText("Cinza");
        SelecCinza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecCinzaActionPerformed(evt);
            }
        });
        CorSelec.add(SelecCinza);

        SelecCinzaClaro.setSelected(true);
        SelecCinzaClaro.setText("Cinza Claro");
        SelecCinzaClaro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecCinzaClaroActionPerformed(evt);
            }
        });
        CorSelec.add(SelecCinzaClaro);

        SelecCinzaEscuro.setSelected(true);
        SelecCinzaEscuro.setText("Cinza Escuro");
        SelecCinzaEscuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecCinzaEscuroActionPerformed(evt);
            }
        });
        CorSelec.add(SelecCinzaEscuro);

        SelecRosa.setSelected(true);
        SelecRosa.setText("Rosa");
        SelecRosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelecRosaActionPerformed(evt);
            }
        });
        CorSelec.add(SelecRosa);

        Editar.add(CorSelec);

        BarraDeMenu.add(Editar);

        MenuAjuda.setText("Ajuda");

        BotaoAjuda.setText("Exibir Ajuda");
        BotaoAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAjudaActionPerformed(evt);
            }
        });
        MenuAjuda.add(BotaoAjuda);
        MenuAjuda.add(jSeparator5);

        BotaoSobre.setText("Sobre");
        BotaoSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSobreActionPerformed(evt);
            }
        });
        MenuAjuda.add(BotaoSobre);

        BarraDeMenu.add(MenuAjuda);

        setJMenuBar(BarraDeMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PainelBaseLado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelBaseFrente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelBaseProjecao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelBaseTopo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelDesenho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelCor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PainelObservacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelCor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelBaseTopo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelBaseFrente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelBaseLado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelBaseProjecao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
        if (!control.getListaCubos().isEmpty() && alteracoesRealizadas) {
            int opt;
            opt = JOptionPane.showConfirmDialog(rootPane, "Deseja salvar as alterações realizadas?", "Salvar Alterações", YES_NO_OPTION, INFORMATION_MESSAGE);
            if (opt == 0) {
                try {
                    salvarArquivo();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível salvar!", "Erro", ERROR_MESSAGE);
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.exit(0);
    }//GEN-LAST:event_SairActionPerformed

    public void setControlador(Controlador c) {
        control = c;
    }

    private void habilitarBotoes(boolean b) {
        if (b) {
            ExcluirCubo.setEnabled(true);
            SpinnerRotacao.setEnabled(true);
            if (!control.getListaCubos().isEmpty()) {
                if (control.getCubo(cuboSelecIndice).isAgrupado()) {
                    BotaoDesagrupar.setEnabled(true);
                    SpinnerEscala.setEnabled(false);
                    SpinnerKA.setEnabled(false);
                    SpinnerKD.setEnabled(false);
                    SpinnerKS.setEnabled(false);
                    SpinnerN.setEnabled(false);
                } else {
                    BotaoDesagrupar.setEnabled(false);
                    SpinnerEscala.setEnabled(true);
                    SpinnerKA.setEnabled(true);
                    SpinnerKD.setEnabled(true);
                    SpinnerKS.setEnabled(true);
                    SpinnerN.setEnabled(true);
                }
            }
        } else {
            ExcluirCubo.setEnabled(false);
            SpinnerRotacao.setEnabled(false);
            SpinnerKA.setEnabled(false);
            SpinnerKD.setEnabled(false);
            SpinnerKS.setEnabled(false);
            SpinnerN.setEnabled(false);
            BotaoDesagrupar.setEnabled(false);
            SpinnerEscala.setEnabled(false);
        }
    }

    private void setarValoresBotoes(int v) {
        SpinnerRotacao.setValue(v);
        if (!control.getCubo(cuboSelecIndice).isAgrupado()) {
            SpinnerEscala.setValue(control.getCubo(cuboSelecIndice).getFatorEscalaZ());
            SpinnerKA.setValue(control.getCubo(cuboSelecIndice).getCubo().getKa());
            SpinnerKD.setValue(control.getCubo(cuboSelecIndice).getCubo().getKd());
            SpinnerKS.setValue(control.getCubo(cuboSelecIndice).getCubo().getKs());
            SpinnerN.setValue(control.getCubo(cuboSelecIndice).getCubo().getN());
        }
    }

    /*public void setCorBorda(Color c) {
     this.corBorda = c;
     }

     public void setCorPreenchimento(Color c) {
     this.corPreenchimento = c;
     }

     public Color getCorBorda() {
     return corBorda;
     }

     public Color getCorPreenchimento() {
     return corPreenchimento;
     }*/
    private void desenharVisoesPaineis() {
        if (!control.getListaCubos().isEmpty()) {
            PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
            PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
            PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
            if (SelecProjecaoPerspectiva.isSelected()) {
                int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
                int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
                int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
                Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
                int px = Integer.parseInt(SpinnerPX.getValue().toString());
                int py = Integer.parseInt(SpinnerPY.getValue().toString());
                int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                Ponto p = new Ponto(px, py, pz);
                int dp = Integer.parseInt(SpinnerDP.getValue().toString());
                PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
                    Ponto vrpIso = new Ponto(vrp, vrp, vrp);
                    int px = Integer.parseInt(SpinnerPX.getValue().toString());
                    int py = Integer.parseInt(SpinnerPY.getValue().toString());
                    int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                    Ponto p = new Ponto(px, py, pz);
                    PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
                }
            }
        }
    }

    private void AlternarVisaoFrenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlternarVisaoFrenteActionPerformed
        if (alternaFrente) {
            AlternarVisaoFrente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaBaixo.jpg")));
            alternaFrente = false;
            PainelBaseProjecao.setVisible(false);
            PainelBaseTopo.setVisible(false);
            PainelBaseLado.setVisible(false);
            PainelTopo.setVisible(false);
            PainelLado.setVisible(false);
            PainelProjecao.setVisible(false);
            if (!control.getListaCubos().isEmpty()) {
                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
            }
        } else {
            AlternarVisaoFrente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
            alternaFrente = true;
            PainelBaseProjecao.setVisible(true);
            PainelBaseTopo.setVisible(true);
            PainelBaseLado.setVisible(true);
            PainelTopo.setVisible(true);
            PainelLado.setVisible(true);
            PainelProjecao.setVisible(true);
            desenharVisoesPaineis();
        }
    }//GEN-LAST:event_AlternarVisaoFrenteActionPerformed

    private void AlternarVisaoTopoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlternarVisaoTopoActionPerformed
        if (alternaTopo) {
            AlternarVisaoTopo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaBaixo.jpg")));
            alternaTopo = false;
            PainelBaseFrente.setVisible(false);
            PainelBaseProjecao.setVisible(false);
            PainelBaseLado.setVisible(false);
            PainelFrente.setVisible(false);
            PainelLado.setVisible(false);
            PainelProjecao.setVisible(false);
            if (!control.getListaCubos().isEmpty()) {
                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
            }
        } else {
            AlternarVisaoTopo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
            alternaTopo = true;
            PainelBaseFrente.setVisible(true);
            PainelBaseProjecao.setVisible(true);
            PainelBaseLado.setVisible(true);
            PainelFrente.setVisible(true);
            PainelLado.setVisible(true);
            PainelProjecao.setVisible(true);
            desenharVisoesPaineis();
        }
    }//GEN-LAST:event_AlternarVisaoTopoActionPerformed

    private void AlternarVisaoLadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlternarVisaoLadoActionPerformed
        if (alternaLado) {
            AlternarVisaoLado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaBaixo.jpg")));
            alternaLado = false;
            PainelBaseFrente.setVisible(false);
            PainelBaseTopo.setVisible(false);
            PainelBaseProjecao.setVisible(false);
            PainelTopo.setVisible(false);
            PainelFrente.setVisible(false);
            PainelProjecao.setVisible(false);
            if (!control.getListaCubos().isEmpty()) {
                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
            }
        } else {
            AlternarVisaoLado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
            alternaLado = true;
            PainelBaseFrente.setVisible(true);
            PainelBaseTopo.setVisible(true);
            PainelBaseProjecao.setVisible(true);
            PainelTopo.setVisible(true);
            PainelFrente.setVisible(true);
            PainelProjecao.setVisible(true);
            desenharVisoesPaineis();
        }
    }//GEN-LAST:event_AlternarVisaoLadoActionPerformed

    private void AlternarVisaoProjecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlternarVisaoProjecaoActionPerformed
        if (alternaProjecao) {
            AlternarVisaoProjecao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaBaixo.jpg")));
            alternaProjecao = false;
            PainelBaseFrente.setVisible(false);
            PainelBaseTopo.setVisible(false);
            PainelBaseLado.setVisible(false);
            PainelTopo.setVisible(false);
            PainelLado.setVisible(false);
            PainelFrente.setVisible(false);
            if (!control.getListaCubos().isEmpty()) {
                if (SelecProjecaoPerspectiva.isSelected()) {
                    int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
                    int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
                    int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
                    Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
                    int px = Integer.parseInt(SpinnerPX.getValue().toString());
                    int py = Integer.parseInt(SpinnerPY.getValue().toString());
                    int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                    Ponto p = new Ponto(px, py, pz);
                    int dp = Integer.parseInt(SpinnerDP.getValue().toString());
                    PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
                } else {
                    if (SelecProjecaoIsometrica.isSelected()) {
                        int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
                        Ponto vrpIso = new Ponto(vrp, vrp, vrp);
                        int px = Integer.parseInt(SpinnerPX.getValue().toString());
                        int py = Integer.parseInt(SpinnerPY.getValue().toString());
                        int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                        Ponto p = new Ponto(px, py, pz);
                        PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
                    }
                }
            }
        } else {
            AlternarVisaoProjecao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/setaCima.jpg")));
            alternaProjecao = true;
            PainelBaseFrente.setVisible(true);
            PainelBaseTopo.setVisible(true);
            PainelBaseLado.setVisible(true);
            PainelTopo.setVisible(true);
            PainelLado.setVisible(true);
            PainelFrente.setVisible(true);
            desenharVisoesPaineis();
        }
    }//GEN-LAST:event_AlternarVisaoProjecaoActionPerformed

    private void operacoesCuboFrente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_operacoesCuboFrente
        if (plotDesenho) {
            //plotDesenho = false;
            if (control.criarCubo(evt.getX(), evt.getY(), 1, selecionaCorBorda.getCorSelecionada(),
                    PainelFrente.getSize().height - 1, PainelFrente.getSize().width - 1)) {
                //double sen, cos;
                //int ang = 30;
                //sen = angulos.getSenos()[360 - ang];
                //cos = angulos.getCossenos()[360 - ang];
                //control.getCubo(control.getListaCubos().size()-1).rotacaoZ(ang, sen, cos);
                //control.getCubo(control.getListaCubos().size()-1).rotacaoY(ang, sen, cos);
                //control.getCubo(control.getListaCubos().size()-1).rotacaoX(ang, sen, cos);
                if (cuboIsSelected) {
                    cuboIsSelected = false;
                    if (selecLado) {
                        PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                        selecLado = false;
                    }
                    if (selecFrente) {
                        PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                        selecFrente = false;
                    }
                    if (selecTopo) {
                        PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                        selecTopo = false;
                    }
                    habilitarBotoes(false);
                }
                desenharVisoesPaineis();
                //selecionaCubo = true;
                alteracoesRealizadas = true;
            } else {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível criar o cubo!", "Erro!", ERROR_MESSAGE);
            }
        } else {
            if (selecionaCubo) {
                //System.out.println("X: " + evt.getX() + " Y: " + evt.getY());
                if (!control.getListaCubos().isEmpty()) {
                    if (PainelFrente.existemCubosPossiveisFrente(control.getListaCubos(), evt.getX(), evt.getY())) {
                        if (cuboIsSelected) {
                            int aux = PainelFrente.selecionarCuboFrente(control.getListaCubos(), evt.getX(), evt.getY());
                            if (selecLado) {
                                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecLado = false;
                            }
                            if (selecTopo) {
                                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecTopo = false;
                            }
                            if (cuboSelecIndice != aux) {
                                if (selecFrente) {
                                    PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                                    PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                                    //selecFrente = false;
                                }
                            }
                            cuboSelecIndice = aux;
                        } else {
                            cuboSelecIndice = PainelFrente.selecionarCuboFrente(control.getListaCubos(), evt.getX(), evt.getY());
                            cuboIsSelected = true;
                            selecLado = selecTopo = false;
                        }
                        PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
                        localClicadoX = evt.getX();
                        localClicadoY = evt.getY();
                        selecFrente = true;
                        SpinnerRotacao.setValue(control.getCubo(cuboSelecIndice).getAnguloRotacaoZ());
                        if (!control.getCubo(cuboSelecIndice).isAgrupado()) {
                            SpinnerKA.setValue(control.getCubo(cuboSelecIndice).getCubo().getKa());
                            SpinnerKD.setValue(control.getCubo(cuboSelecIndice).getCubo().getKd());
                            SpinnerKS.setValue(control.getCubo(cuboSelecIndice).getCubo().getKs());
                            SpinnerN.setValue(control.getCubo(cuboSelecIndice).getCubo().getN());
                            SpinnerEscala.setValue(control.getCubo(cuboSelecIndice).getFatorEscalaZ());
                        }
                        habilitarBotoes(true);
                        setarValoresBotoes(control.getCubo(cuboSelecIndice).getAnguloRotacaoZ());
                    } else {
                        if (cuboIsSelected) {
                            cuboIsSelected = false;
                            if (selecLado) {
                                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecLado = false;
                            }
                            if (selecFrente) {
                                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                                selecFrente = false;
                            }
                            if (selecTopo) {
                                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecTopo = false;
                            }
                            habilitarBotoes(false);
                            if (last != -1 && last < control.getListaCubos().size()) {
                                PainelFrente.pintarSelecaoFrente(control.getCubo(last), Color.WHITE);
                            }
                        }
                    }
                }
            }
        }
        repintarBotoesAlternar();
        //nanoSegundosPressao = System.nanoTime();
    }//GEN-LAST:event_operacoesCuboFrente

    private void moverCuboFrente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverCuboFrente
        //System.out.println(cuboSelecIndice);
        if (cuboIsSelected && PainelFrente.clickDentroCuboFrente(control.getCubo(cuboSelecIndice), evt.getX(), evt.getY())) {
            //nanoSegundosPressao = System.nanoTime();
            ListaCubos c = control.getCubo(cuboSelecIndice);
            PainelFrente.pintarSelecaoFrente(c, Color.WHITE);
            PainelFrente.apagarCubo(c);
            PainelTopo.apagarCubo(c);
            PainelLado.apagarCubo(c);
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarCuboProjecao(c, 1);
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarCuboProjecao(c, 2);
                }
            }

            control.getCubo(cuboSelecIndice).transladarCubosXY(evt.getX() - localClicadoX, evt.getY() - localClicadoY,
                    PainelFrente.getSize().height - 1, PainelFrente.getSize().width - 1);

            //PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
            PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
            desenharVisoesPaineis();

            //System.out.println((evt.getX()-localClicadoX) + " " + (evt.getY()-localClicadoY));
            localClicadoX = evt.getX();
            localClicadoY = evt.getY();
            repintarBotoesAlternar();
            alteracoesRealizadas = true;

            //operaçoes agrupamento
            cuboParaAgrupar = PainelFrente.getCuboMaisProximoFrente(control.getListaCubos(), cuboSelecIndice);
            if (cuboParaAgrupar != cuboSelecIndice && cuboParaAgrupar != -1) {
                last = cuboParaAgrupar;
                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboParaAgrupar), Color.LIGHT_GRAY);
                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
                permiteAgrupar = true;
            } else {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelFrente.pintarSelecaoFrente(control.getCubo(last), Color.WHITE);
                    PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                    PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
                    permiteAgrupar = false;
                }
            }

            /*PainelFrente.pintarSelecaoFrente(control.getCubo(cuboParaAgrupar), Color.WHITE);
             PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
             PainelFrente.apagarTodosCubosFrente(control.getListaCubos(), OcultaFaces.isSelected());
             PainelTopo.apagarTodosCubosTopo(control.getListaCubos(), OcultaFaces.isSelected());
             PainelLado.apagarTodosCubosLado(control.getListaCubos(), OcultaFaces.isSelected());
             PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos(), OcultaFaces.isSelected());
             control.agruparDoisCubos(cuboSelecIndice, cuboParaAgrupar);
             PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
             PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
             PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
             PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected());
             BotaoDesagrupar.setEnabled(false);
             ExcluirCubo.setEnabled(false);
             SpinnerEscala.setEnabled(false);
             SpinnerRotacao.setEnabled(false);
             BotaoFazerRotacao.setEnabled(false);
             BotaoFazerEscala.setEnabled(false);
             cuboIsSelected = false;
             selecFrente = false;
             selecLado = false;
             selecTopo = false;
             repintarBotoesAlternar();*/
        }
    }//GEN-LAST:event_moverCuboFrente

    private void moverCuboTopo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverCuboTopo
        if (cuboIsSelected && PainelTopo.clickDentroCuboTopo(control.getCubo(cuboSelecIndice), evt.getX(), evt.getY())) {
            ListaCubos c = control.getCubo(cuboSelecIndice);
            PainelTopo.pintarSelecaoTopo(c, Color.WHITE);
            PainelFrente.apagarCubo(c);
            PainelTopo.apagarCubo(c);
            PainelLado.apagarCubo(c);
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarCuboProjecao(c, 1);
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarCuboProjecao(c, 2);
                }
            }
            control.getCubo(cuboSelecIndice).transladarCubosXZ(evt.getX() - localClicadoX, evt.getY() - localClicadoZ,
                    PainelTopo.getSize().height - 1, PainelTopo.getSize().width - 1);

            //PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
            PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
            desenharVisoesPaineis();

            //System.out.println((evt.getX()-localClicadoX) + " " + (evt.getY()-localClicadoY));
            localClicadoX = evt.getX();
            localClicadoZ = evt.getY();
            repintarBotoesAlternar();
            alteracoesRealizadas = true;

            //operaçoes agrupamento
            cuboParaAgrupar = PainelTopo.getCuboMaisProximoTopo(control.getListaCubos(), cuboSelecIndice);
            if (cuboParaAgrupar != cuboSelecIndice && cuboParaAgrupar != -1) {
                last = cuboParaAgrupar;
                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboParaAgrupar), Color.LIGHT_GRAY);
                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
                permiteAgrupar = true;
            } else {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelTopo.pintarSelecaoTopo(control.getCubo(last), Color.WHITE);
                    PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                    PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
                    permiteAgrupar = false;
                }
            }
        }
    }//GEN-LAST:event_moverCuboTopo

    private void moverCuboLado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverCuboLado
        if (cuboIsSelected && PainelLado.clickDentroCuboLado(control.getCubo(cuboSelecIndice), evt.getX(), evt.getY())) {
            ListaCubos c = control.getCubo(cuboSelecIndice);
            PainelLado.pintarSelecaoLado(c, Color.WHITE);
            PainelFrente.apagarCubo(c);
            PainelTopo.apagarCubo(c);
            PainelLado.apagarCubo(c);
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarCuboProjecao(c, 1);
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarCuboProjecao(c, 2);
                }
            }
            control.getCubo(cuboSelecIndice).transladarCubosZY(evt.getX() - localClicadoZ, evt.getY() - localClicadoY,
                    PainelLado.getSize().height - 1, PainelLado.getSize().width - 1);

            //PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
            PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
            desenharVisoesPaineis();

            //System.out.println((evt.getX()-localClicadoX) + " " + (evt.getY()-localClicadoY));
            localClicadoZ = evt.getX();
            localClicadoY = evt.getY();
            repintarBotoesAlternar();
            alteracoesRealizadas = true;

            //operaçoes agrupamento
            cuboParaAgrupar = PainelLado.getCuboMaisProximoLado(control.getListaCubos(), cuboSelecIndice);
            if (cuboParaAgrupar != cuboSelecIndice && cuboParaAgrupar != -1) {
                last = cuboParaAgrupar;
                PainelLado.pintarSelecaoLado(control.getCubo(cuboParaAgrupar), Color.LIGHT_GRAY);
                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
                permiteAgrupar = true;
            } else {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelLado.pintarSelecaoLado(control.getCubo(last), Color.WHITE);
                    PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                    PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
                    permiteAgrupar = false;
                }
            }
        }
    }//GEN-LAST:event_moverCuboLado

    private void operacoesCuboTopo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_operacoesCuboTopo
        if (plotDesenho) {
            //plotDesenho = false;
            if (control.criarCubo(evt.getX(), evt.getY(), 2, selecionaCorBorda.getCorSelecionada(),
                    PainelFrente.getSize().height - 1, PainelFrente.getSize().width - 1)) {
                double sen, cos;
                int ang = 30;
                sen = angulos.getSenos()[360 - ang];
                cos = angulos.getCossenos()[360 - ang];
                //control.getCubo(control.getListaCubos().size()-1).rotacaoZ(ang, sen, cos);
                //control.getCubo(control.getListaCubos().size()-1).rotacaoY(ang, sen, cos);
                //control.getCubo(control.getListaCubos().size()-1).rotacaoX(ang, sen, cos);
                if (cuboIsSelected) {
                    cuboIsSelected = false;
                    if (selecLado) {
                        PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                        selecLado = false;
                    }
                    if (selecFrente) {
                        PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                        selecFrente = false;
                    }
                    if (selecTopo) {
                        PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                        selecTopo = false;
                    }
                    habilitarBotoes(false);
                }
                desenharVisoesPaineis();
                //selecionaCubo = true;
                alteracoesRealizadas = true;
            } else {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível criar o cubo!", "Erro!", ERROR_MESSAGE);
            }
        } else {
            if (selecionaCubo) {
                //System.out.println("X: " + evt.getX() + " Y: " + evt.getY());
                if (!control.getListaCubos().isEmpty()) {
                    if (PainelTopo.existemCubosPossiveisTopo(control.getListaCubos(), evt.getX(), evt.getY())) {
                        if (cuboIsSelected) {
                            int aux = PainelTopo.selecionarCuboTopo(control.getListaCubos(), evt.getX(), evt.getY());
                            if (selecLado) {
                                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecLado = false;
                            }
                            if (selecFrente) {
                                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                                selecFrente = false;
                            }
                            if (cuboSelecIndice != aux) {
                                if (selecTopo) {
                                    PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                                    PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                                    //selecTopo = false;
                                }
                            }
                            cuboSelecIndice = aux;
                        } else {
                            cuboSelecIndice = PainelTopo.selecionarCuboTopo(control.getListaCubos(), evt.getX(), evt.getY());
                            cuboIsSelected = true;
                            selecLado = selecFrente = false;
                        }
                        PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
                        localClicadoX = evt.getX();
                        localClicadoZ = evt.getY();
                        selecTopo = true;
                        SpinnerRotacao.setValue(control.getCubo(cuboSelecIndice).getAnguloRotacaoZ());
                        if (!control.getCubo(cuboSelecIndice).isAgrupado()) {
                            SpinnerKA.setValue(control.getCubo(cuboSelecIndice).getCubo().getKa());
                            SpinnerKD.setValue(control.getCubo(cuboSelecIndice).getCubo().getKd());
                            SpinnerKS.setValue(control.getCubo(cuboSelecIndice).getCubo().getKs());
                            SpinnerN.setValue(control.getCubo(cuboSelecIndice).getCubo().getN());
                            SpinnerEscala.setValue(control.getCubo(cuboSelecIndice).getFatorEscalaZ());
                        }
                        habilitarBotoes(true);
                        setarValoresBotoes(control.getCubo(cuboSelecIndice).getAnguloRotacaoY());
                    } else {
                        if (cuboIsSelected) {
                            cuboIsSelected = false;
                            if (selecLado) {
                                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecLado = false;
                            }
                            if (selecFrente) {
                                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                                selecFrente = false;
                            }
                            if (selecTopo) {
                                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecTopo = false;
                            }
                            habilitarBotoes(false);
                            if (last != -1 && last < control.getListaCubos().size()) {
                                PainelTopo.pintarSelecaoTopo(control.getCubo(last), Color.WHITE);
                            }
                        }
                    }
                }
            }
        }
        repintarBotoesAlternar();
    }//GEN-LAST:event_operacoesCuboTopo

    private void operacoesCuboLado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_operacoesCuboLado
        if (plotDesenho) {
            //plotDesenho = false;
            if (control.criarCubo(evt.getX(), evt.getY(), 3, selecionaCorBorda.getCorSelecionada(),
                    PainelFrente.getSize().height - 1, PainelFrente.getSize().width - 1)) {
                double sen, cos;
                int ang = 30;
                sen = angulos.getSenos()[360 - ang];
                cos = angulos.getCossenos()[360 - ang];
                //control.getCubo(control.getListaCubos().size()-1).rotacaoZ(ang, sen, cos);
                //control.getCubo(control.getListaCubos().size()-1).rotacaoY(ang, sen, cos);
                //control.getCubo(control.getListaCubos().size()-1).rotacaoX(ang, sen, cos);
                if (cuboIsSelected) {
                    cuboIsSelected = false;
                    if (selecLado) {
                        PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                        selecLado = false;
                    }
                    if (selecFrente) {
                        PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                        selecFrente = false;
                    }
                    if (selecTopo) {
                        PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                        PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                        selecTopo = false;
                    }
                    habilitarBotoes(false);
                }
                desenharVisoesPaineis();
                //selecionaCubo = true;
                alteracoesRealizadas = true;
            } else {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível criar o cubo!", "Erro!", ERROR_MESSAGE);
            }
        } else {
            if (selecionaCubo) {
                //System.out.println("X: " + evt.getX() + " Y: " + evt.getY());
                if (!control.getListaCubos().isEmpty()) {
                    if (PainelLado.existemCubosPossiveisLado(control.getListaCubos(), evt.getX(), evt.getY())) {
                        if (cuboIsSelected) {
                            int aux = PainelLado.selecionarCuboLado(control.getListaCubos(), evt.getX(), evt.getY());
                            if (selecFrente) {
                                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                                selecFrente = false;
                            }
                            if (selecTopo) {
                                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecTopo = false;
                            }
                            if (cuboSelecIndice != aux) {
                                if (selecLado) {
                                    PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                                    PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                                    //selecLado = false;
                                }
                            }
                            cuboSelecIndice = aux;
                        } else {
                            cuboSelecIndice = PainelLado.selecionarCuboLado(control.getListaCubos(), evt.getX(), evt.getY());
                            cuboIsSelected = true;
                            selecFrente = selecTopo = false;
                        }
                        PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
                        localClicadoZ = evt.getX();
                        localClicadoY = evt.getY();
                        selecLado = true;
                        SpinnerRotacao.setValue(control.getCubo(cuboSelecIndice).getAnguloRotacaoZ());
                        if (!control.getCubo(cuboSelecIndice).isAgrupado()) {
                            SpinnerKA.setValue(control.getCubo(cuboSelecIndice).getCubo().getKa());
                            SpinnerKD.setValue(control.getCubo(cuboSelecIndice).getCubo().getKd());
                            SpinnerKS.setValue(control.getCubo(cuboSelecIndice).getCubo().getKs());
                            SpinnerN.setValue(control.getCubo(cuboSelecIndice).getCubo().getN());
                            SpinnerEscala.setValue(control.getCubo(cuboSelecIndice).getFatorEscalaZ());
                        }
                        habilitarBotoes(true);
                        setarValoresBotoes(control.getCubo(cuboSelecIndice).getAnguloRotacaoX());
                    } else {
                        if (cuboIsSelected) {
                            cuboIsSelected = false;
                            if (selecLado) {
                                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecLado = false;
                            }
                            if (selecFrente) {
                                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                                selecFrente = false;
                            }
                            if (selecTopo) {
                                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                                selecTopo = false;
                            }
                            habilitarBotoes(false);
                            if (last != -1 && last < control.getListaCubos().size()) {
                                PainelLado.pintarSelecaoLado(control.getCubo(last), Color.WHITE);
                            }
                        }
                    }
                }
            }
        }
        repintarBotoesAlternar();
    }//GEN-LAST:event_operacoesCuboLado

    private void pintarCorSelecao() {
        if (cuboIsSelected) {
            if (selecFrente) {
                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
            }
            if (selecLado) {
                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
            }
            if (selecTopo) {
                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
            }
        }
    }

    private void windowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowGainedFocus
        //System.out.println("a");
        if (!control.getListaCubos().isEmpty()) {
            desenharVisoesPaineis();
            pintarCorSelecao();
        }
    }//GEN-LAST:event_windowGainedFocus

    private void SelecPretoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecPretoActionPerformed
        corSelecao = Color.BLACK;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecPretoActionPerformed

    private void SelecVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecVerdeActionPerformed
        corSelecao = Color.GREEN;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecVerdeActionPerformed

    private void SelecAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecAzulActionPerformed
        corSelecao = Color.BLUE;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecAzulActionPerformed

    private void SelecVermelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecVermelhoActionPerformed
        corSelecao = Color.RED;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecVermelhoActionPerformed

    private void SelecAmareloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecAmareloActionPerformed
        corSelecao = Color.YELLOW;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecAmareloActionPerformed

    private void SelecCianoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecCianoActionPerformed
        corSelecao = Color.CYAN;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecCianoActionPerformed

    private void SelecLaranjadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecLaranjadoActionPerformed
        corSelecao = Color.ORANGE;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecLaranjadoActionPerformed

    private void SelecMagentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecMagentaActionPerformed
        corSelecao = Color.MAGENTA;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecMagentaActionPerformed

    private void SelecCinzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecCinzaActionPerformed
        corSelecao = Color.GRAY;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecCinzaActionPerformed

    private void SelecCinzaClaroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecCinzaClaroActionPerformed
        corSelecao = Color.LIGHT_GRAY;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecCinzaClaroActionPerformed

    private void SelecCinzaEscuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecCinzaEscuroActionPerformed
        corSelecao = Color.DARK_GRAY;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecCinzaEscuroActionPerformed

    private void SelecRosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecRosaActionPerformed
        corSelecao = Color.PINK;
        pintarCorSelecao();
    }//GEN-LAST:event_SelecRosaActionPerformed

    private void windowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowStateChanged
        if (!control.getListaCubos().isEmpty()) {
            desenharVisoesPaineis();
            pintarCorSelecao();
        }
    }//GEN-LAST:event_windowStateChanged

    private void BotaoCorBordasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCorBordasActionPerformed
        selecionaCorBorda.setVisible(true);
    }//GEN-LAST:event_BotaoCorBordasActionPerformed

    private void BotaoCorPreenchimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCorPreenchimentoActionPerformed
        selecionaCorPreenchimento.setVisible(true);
    }//GEN-LAST:event_BotaoCorPreenchimentoActionPerformed

    public boolean salvarArquivo() throws FileNotFoundException, IOException {
        if (!control.getListaCubos().isEmpty()) {
            carregaStatus.setarProgressoInicial(0);
            File arquivo;
            int result;
            JFileChooser salvaArq = new JFileChooser();
            //FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos BlockBuilder *.BLBD", "blbd");
            //salvaArq.addChoosableFileFilter(filtro);
            //salvaArq.setFileFilter(filtro);
            result = salvaArq.showSaveDialog(null);
            if (result == JFileChooser.CANCEL_OPTION) {
                salvaArq.cancelSelection();
            } else {
                carregaStatus.setVisible(true);
                carregaStatus.aumentarProgresso(10);
                arquivo = salvaArq.getSelectedFile();
                carregaStatus.aumentarProgresso(5);
                try {
                    try (FileOutputStream f = new FileOutputStream(arquivo);
                            ObjectOutputStream objGravar = new ObjectOutputStream(f)) {
                        objGravar.writeObject(control);
                        carregaStatus.aumentarProgresso(60);
                        objGravar.flush();
                        carregaStatus.aumentarProgresso(15);
                        objGravar.close();
                        carregaStatus.aumentarProgresso(10);
                        carregaStatus.setVisible(false);
                    }
                    return true;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } catch (IOException ex) {
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Não há dados para serem gravados!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    public boolean abrirArquivo() throws FileNotFoundException, IOException, ClassNotFoundException {
        carregaStatus.setarProgressoInicial(0);
        File arquivo;
        int result;
        JFileChooser abreArq = new JFileChooser();
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos BlockBuilder *.BLBD", "blbd");
        //abreArq.addChoosableFileFilter(filtro);
        //abreArq.setFileFilter(filtro);
        result = abreArq.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            abreArq.cancelSelection();
        } else {
            arquivo = abreArq.getSelectedFile();
            try {
                carregaStatus.setVisible(true);
                carregaStatus.aumentarProgresso(5);
                Controlador c;
                try (FileInputStream f = new FileInputStream(arquivo);
                        ObjectInputStream objLeitura = new ObjectInputStream(f)) {
                    c = (Controlador) objLeitura.readObject();
                    carregaStatus.aumentarProgresso(15);
                    objLeitura.close();
                    carregaStatus.aumentarProgresso(5);
                }
                if (selecFrente) {
                    PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                    selecFrente = false;
                }
                if (selecTopo) {
                    PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                    selecTopo = false;
                }
                if (selecLado) {
                    PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                    selecLado = false;
                }
                carregaStatus.aumentarProgresso(10);
                cuboIsSelected = plotDesenho = false;
                PainelFrente.apagarTodosCubosFrente(control.getListaCubos());
                PainelTopo.apagarTodosCubosTopo(control.getListaCubos());
                PainelLado.apagarTodosCubosLado(control.getListaCubos());
                if (SelecProjecaoPerspectiva.isSelected()) {
                    PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
                } else {
                    if (SelecProjecaoIsometrica.isSelected()) {
                        PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                    }
                }
                carregaStatus.aumentarProgresso(10);
                control.limparListas();
                setControlador(c);
                carregaStatus.aumentarProgresso(5);
                desenharVisoesPaineis();
                carregaStatus.aumentarProgresso(40);
                ExcluirCubo.setEnabled(false);
                SpinnerEscala.setValue(1);
                SpinnerRotacao.setValue(0);
                SpinnerEscala.setEnabled(false);
                SpinnerRotacao.setEnabled(false);
                /*BotaoFazerRotacao.setEnabled(false);
                 BotaoFazerEscala.setEnabled(false);*/
                BotaoDesagrupar.setEnabled(false);
                alteracoesRealizadas = false;
                carregaStatus.aumentarProgresso(10);
                carregaStatus.setVisible(false);
                return true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }

    private void ExcluirCuboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirCuboActionPerformed
        excluirCubo();
    }//GEN-LAST:event_ExcluirCuboActionPerformed

    public void excluirCubo() {
        if (cuboIsSelected && !control.getListaCubos().isEmpty()) {
            if (selecLado) {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelLado.pintarSelecaoLado(control.getCubo(last), Color.WHITE);
                }
                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                selecLado = false;
            }
            if (selecFrente) {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelFrente.pintarSelecaoFrente(control.getCubo(last), Color.WHITE);
                }
                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                selecFrente = false;
            }
            if (selecTopo) {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelTopo.pintarSelecaoTopo(control.getCubo(last), Color.WHITE);
                }
                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                selecTopo = false;
            }
            ListaCubos c = control.excluirCubo(cuboSelecIndice);
            ExcluirCubo.setEnabled(false);
            SpinnerEscala.setEnabled(false);
            SpinnerRotacao.setEnabled(false);
            /*BotaoFazerRotacao.setEnabled(false);
             BotaoFazerEscala.setEnabled(false);*/
            BotaoDesagrupar.setEnabled(false);
            cuboIsSelected = false;
            cuboSelecIndice = -1;
            PainelFrente.apagarCubo(c);
            PainelTopo.apagarCubo(c);
            PainelLado.apagarCubo(c);
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarCuboProjecao(c, 1);
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarCuboProjecao(c, 2);
                }
            }
            desenharVisoesPaineis();
            repintarBotoesAlternar();
            alteracoesRealizadas = true;
            permiteAgrupar = false;
        }
        if (control.getListaCubos().isEmpty()) {
            selecionaCubo = false;
            cuboIsSelected = false;
            alteracoesRealizadas = false;
        }
    }

    private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing
        if (!control.getListaCubos().isEmpty() && alteracoesRealizadas) {
            int opt;
            opt = JOptionPane.showConfirmDialog(rootPane, "Deseja salvar as alterações realizadas?", "Salvar Alterações", YES_NO_OPTION, INFORMATION_MESSAGE);
            if (opt == 0) {
                try {
                    salvarArquivo();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível salvar!", "Erro", ERROR_MESSAGE);
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_windowClosing

    private void keyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyPressed
        //Delete
        System.out.println("epa");
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (cuboIsSelected) {
                excluirCubo();
            }
        }
    }//GEN-LAST:event_keyPressed

    private void keyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTyped
        //Delete
        System.out.println("epa2");
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (cuboIsSelected) {
                excluirCubo();
            }
        }
    }//GEN-LAST:event_keyTyped

    private void rotacionar() {
        if (cuboIsSelected) {
            boolean flag = true;
            int valorAtual = Integer.parseInt(SpinnerRotacao.getModel().getValue().toString()); //ang
            int valorAntigoRotacao = 0;
            ListaCubos c = control.getCubo(cuboSelecIndice);
            if (selecFrente) {
                valorAntigoRotacao = control.getCubo(cuboSelecIndice).getAnguloRotacaoZ();
                if ((valorAtual != (valorAntigoRotacao + 1)) && (valorAtual != (valorAntigoRotacao - 1))) {
                    flag = false;
                } else {
                    PainelFrente.pintarSelecaoFrente(c, Color.WHITE);
                }
            }
            if (selecTopo) {
                valorAntigoRotacao = control.getCubo(cuboSelecIndice).getAnguloRotacaoY();
                if ((valorAtual != (valorAntigoRotacao + 1)) && (valorAtual != (valorAntigoRotacao - 1))) {
                    flag = false;
                } else {
                    PainelTopo.pintarSelecaoTopo(c, Color.WHITE);
                }
            }
            if (selecLado) {
                valorAntigoRotacao = control.getCubo(cuboSelecIndice).getAnguloRotacaoX();
                if ((valorAtual != (valorAntigoRotacao + 1)) && (valorAtual != (valorAntigoRotacao - 1))) {
                    flag = false;
                } else {
                    PainelLado.pintarSelecaoLado(c, Color.WHITE);
                }
            }
            if (flag) {
                PainelFrente.apagarCubo(c);
                PainelTopo.apagarCubo(c);
                PainelLado.apagarCubo(c);
                if (SelecProjecaoPerspectiva.isSelected()) {
                    PainelProjecao.apagarCuboProjecao(c, 1);
                } else {
                    if (SelecProjecaoIsometrica.isSelected()) {
                        PainelProjecao.apagarCuboProjecao(c, 2);
                    }
                }
                double sen, cos;
                if ((valorAtual - valorAntigoRotacao) == 1) {
                    //sen = angulos.getSenos()[ang];
                    //cos = angulos.getCossenos()[ang];
                    //sen = angulos.getSenos()[360 - ang];
                    //cos = angulos.getCossenos()[360 - ang];
                    sen = angulos.getSenos()[359];
                    cos = angulos.getCossenos()[359];
                    if (selecFrente) {
                        control.getCubo(cuboSelecIndice).rotacaoZ(1, sen, cos);
                        PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
                    }
                    if (selecLado) {
                        control.getCubo(cuboSelecIndice).rotacaoX(1, sen, cos);
                        PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
                    }
                    if (selecTopo) {
                        control.getCubo(cuboSelecIndice).rotacaoY(1, sen, cos);
                        PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
                    }
                } else {
                    if ((valorAtual - valorAntigoRotacao) == -1) {
                        //int aux = Math.abs(ang);
                        //sen = angulos.getSenos()[360 - aux];
                        //cos = angulos.getCossenos()[360 - aux];
                        //sen = angulos.getSenos()[aux];
                        //cos = angulos.getCossenos()[aux];
                        sen = angulos.getSenos()[1];
                        cos = angulos.getCossenos()[1];
                        if (selecFrente) {
                            control.getCubo(cuboSelecIndice).rotacaoZ(-1, sen, cos);
                            PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
                        }
                        if (selecLado) {
                            control.getCubo(cuboSelecIndice).rotacaoX(-1, sen, cos);
                            PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
                        }
                        if (selecTopo) {
                            control.getCubo(cuboSelecIndice).rotacaoY(-1, sen, cos);
                            PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
                        }
                    }
                }
                desenharVisoesPaineis();
                repintarBotoesAlternar();
                alteracoesRealizadas = true;
            } else {
                SpinnerRotacao.setValue(valorAntigoRotacao);
            }
        }
    }

    /*private void setRotacionar() {
     if (cuboIsSelected) {
     ListaCubos c = control.getCubo(cuboSelecIndice);
     if (selecFrente) {
     PainelFrente.pintarSelecaoFrente(c, Color.WHITE);
     }
     if (selecTopo) {
     PainelTopo.pintarSelecaoTopo(c, Color.WHITE);
     }
     if (selecLado) {
     PainelLado.pintarSelecaoLado(c, Color.WHITE);
     }
     PainelFrente.apagarCubo(c);
     PainelTopo.apagarCubo(c);
     PainelLado.apagarCubo(c);
     PainelProjecao.apagarCubo(c);
     int ang = Integer.parseInt(SpinnerRotacao.getModel().getValue().toString());
     double sen, cos;
     if (ang > 0) {
     //sen = angulos.getSenos()[ang];
     //cos = angulos.getCossenos()[ang];
     sen = angulos.getSenos()[360 - ang];
     cos = angulos.getCossenos()[360 - ang];
     } else {
     int aux = Math.abs(ang);
     //sen = angulos.getSenos()[360 - aux];
     //cos = angulos.getCossenos()[360 - aux];
     sen = angulos.getSenos()[aux];
     cos = angulos.getCossenos()[aux];
     }
     if (selecFrente) {
     control.getCubo(cuboSelecIndice).rotacaoZsetado(ang, sen, cos);
     PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), corSelecao);
     }
     if (selecLado) {
     control.getCubo(cuboSelecIndice).rotacaoXsetado(ang, sen, cos);
     PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), corSelecao);
     }
     if (selecTopo) {
     control.getCubo(cuboSelecIndice).rotacaoYsetado(ang, sen, cos);
     PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), corSelecao);
     }
     desenharVisoesPaineis();
     repintarBotoesAlternar();
     alteracoesRealizadas = true;
     }
     }*/
    private void escala() {
        if (cuboIsSelected) {
            boolean flag = true;
            float valorAtual = (float) (Double.parseDouble(SpinnerEscala.getValue().toString()));
            float valorAntigoEscala = control.getCubo(cuboSelecIndice).getFatorEscalaZ();
            /*System.out.println(valorAtual);
             System.out.println(valorAntigoEscala);
             System.out.println(valorAtual - valorAntigoEscala);
             System.out.println(valorAtual != (valorAntigoEscala + 0.1f));*/
            if ((valorAtual != (valorAntigoEscala + 0.1f)) && (valorAtual != (valorAntigoEscala - 0.1f))) {
                flag = false;
            }
            if (flag) {
                if (!control.getCubo(cuboSelecIndice).isAgrupado()) {
                    ListaCubos c = control.getCubo(cuboSelecIndice);
                    if (selecFrente) {
                        PainelFrente.pintarSelecaoFrente(c, Color.WHITE);
                    }
                    if (selecTopo) {
                        PainelTopo.pintarSelecaoTopo(c, Color.WHITE);
                    }
                    if (selecLado) {
                        PainelLado.pintarSelecaoLado(c, Color.WHITE);
                    }
                    PainelFrente.apagarCubo(c);
                    PainelTopo.apagarCubo(c);
                    PainelLado.apagarCubo(c);
                    if (SelecProjecaoPerspectiva.isSelected()) {
                        PainelProjecao.apagarCuboProjecao(c, 1);
                    } else {
                        if (SelecProjecaoIsometrica.isSelected()) {
                            PainelProjecao.apagarCuboProjecao(c, 2);
                        }
                    }
                    if (valorAtual == (valorAntigoEscala + 0.1f)) {
                        control.getCubo(cuboSelecIndice).escalaZ((float) 0.1f);
                    } else {
                        if (valorAtual == (valorAntigoEscala - 0.1f)) {
                            control.getCubo(cuboSelecIndice).escalaZ((float) -0.1f);
                        }
                    }
                    if (selecFrente) {
                        PainelFrente.pintarSelecaoFrente(c, corSelecao);
                    }
                    if (selecLado) {
                        PainelLado.pintarSelecaoLado(c, corSelecao);
                    }
                    if (selecTopo) {
                        PainelTopo.pintarSelecaoTopo(c, corSelecao);
                    }
                    desenharVisoesPaineis();
                    repintarBotoesAlternar();
                    alteracoesRealizadas = true;
                }
            } else {
                SpinnerEscala.setValue(valorAntigoEscala);
            }
        }
    }

    private void AbrirProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirProjetoActionPerformed
        try {
            abrirArquivo();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Não foi possível abrir o arquivo!", "Erro", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AbrirProjetoActionPerformed

    private void SalvarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarProjetoActionPerformed
        try {
            salvarArquivo();
        } catch (IOException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Não foi possível salvar o arquivo!", "Erro", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SalvarProjetoActionPerformed

    private void BotaoDesagruparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoDesagruparActionPerformed
        if (cuboIsSelected) {
            ListaCubos c = control.getCubo(cuboSelecIndice);
            if (selecFrente) {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelFrente.pintarSelecaoFrente(control.getCubo(last), Color.WHITE);
                }
                PainelFrente.pintarSelecaoFrente(c, Color.WHITE);
            }
            if (selecTopo) {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelTopo.pintarSelecaoTopo(control.getCubo(last), Color.WHITE);
                }
                PainelTopo.pintarSelecaoTopo(c, Color.WHITE);
            }
            if (selecLado) {
                if (last != -1 && last < control.getListaCubos().size()) {
                    PainelLado.pintarSelecaoLado(control.getCubo(last), Color.WHITE);
                }
                PainelLado.pintarSelecaoLado(c, Color.WHITE);
            }
            PainelFrente.apagarCubo(c);
            PainelTopo.apagarCubo(c);
            PainelLado.apagarCubo(c);
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarCuboProjecao(c, 1);
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarCuboProjecao(c, 2);
                }
            }
            control.desagrupar(cuboSelecIndice);
            BotaoDesagrupar.setEnabled(false);
            ExcluirCubo.setEnabled(false);
            SpinnerEscala.setEnabled(false);
            SpinnerRotacao.setEnabled(false);
            /*BotaoFazerRotacao.setEnabled(false);
             BotaoFazerEscala.setEnabled(false);*/
            cuboIsSelected = false;
            selecFrente = false;
            selecLado = false;
            selecTopo = false;
            permiteAgrupar = false;
            desenharVisoesPaineis();
            repintarBotoesAlternar();
            alteracoesRealizadas = true;
        }
    }//GEN-LAST:event_BotaoDesagruparActionPerformed

    private void BotaoSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSobreActionPerformed
        Sobre sb = new Sobre();
        sb.setVisible(true);
    }//GEN-LAST:event_BotaoSobreActionPerformed

    private void mouseReleasedFrente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseReleasedFrente
        //long tempoPressionamento = System.nanoTime() - nanoSegundosPressao;
        //System.out.println(tempoPressionamento);
        //if (tempoPressionamento >= 2000000000) {
        if (permiteAgrupar) {
            PainelFrente.pintarSelecaoFrente(control.getCubo(last), Color.WHITE);
            PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
            PainelFrente.apagarTodosCubosFrente(control.getListaCubos());
            PainelTopo.apagarTodosCubosTopo(control.getListaCubos());
            PainelLado.apagarTodosCubosLado(control.getListaCubos());
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                }
            }
            //System.out.println(cuboSelecIndice);
            control.agruparDoisCubos(cuboSelecIndice, last);
            desenharVisoesPaineis();
            BotaoDesagrupar.setEnabled(false);
            ExcluirCubo.setEnabled(false);
            SpinnerEscala.setEnabled(false);
            SpinnerRotacao.setEnabled(false);
            /*BotaoFazerRotacao.setEnabled(false);
             BotaoFazerEscala.setEnabled(false);*/
            cuboIsSelected = false;
            selecFrente = false;
            selecLado = false;
            selecTopo = false;
            permiteAgrupar = false;
            repintarBotoesAlternar();
        }
    }//GEN-LAST:event_mouseReleasedFrente

    private void mouseReleasedTopo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseReleasedTopo
        if (permiteAgrupar) {
            PainelTopo.pintarSelecaoTopo(control.getCubo(last), Color.WHITE);
            PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
            PainelFrente.apagarTodosCubosFrente(control.getListaCubos());
            PainelTopo.apagarTodosCubosTopo(control.getListaCubos());
            PainelLado.apagarTodosCubosLado(control.getListaCubos());
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                }
            }
            //System.out.println(cuboSelecIndice);
            control.agruparDoisCubos(cuboSelecIndice, last);
            desenharVisoesPaineis();
            BotaoDesagrupar.setEnabled(false);
            ExcluirCubo.setEnabled(false);
            SpinnerEscala.setEnabled(false);
            SpinnerRotacao.setEnabled(false);
            /*BotaoFazerRotacao.setEnabled(false);
             BotaoFazerEscala.setEnabled(false);*/
            cuboIsSelected = false;
            selecFrente = false;
            selecLado = false;
            selecTopo = false;
            permiteAgrupar = false;
            repintarBotoesAlternar();
        }
    }//GEN-LAST:event_mouseReleasedTopo

    private void mouseReleasedLado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseReleasedLado
        if (permiteAgrupar) {
            PainelLado.pintarSelecaoLado(control.getCubo(last), Color.WHITE);
            PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
            PainelFrente.apagarTodosCubosFrente(control.getListaCubos());
            PainelTopo.apagarTodosCubosTopo(control.getListaCubos());
            PainelLado.apagarTodosCubosLado(control.getListaCubos());
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                }
            }
            //System.out.println(cuboSelecIndice);
            control.agruparDoisCubos(cuboSelecIndice, last);
            desenharVisoesPaineis();
            BotaoDesagrupar.setEnabled(false);
            ExcluirCubo.setEnabled(false);
            SpinnerEscala.setEnabled(false);
            SpinnerRotacao.setEnabled(false);
            /*BotaoFazerRotacao.setEnabled(false);
             BotaoFazerEscala.setEnabled(false);*/
            cuboIsSelected = false;
            selecFrente = false;
            selecLado = false;
            selecTopo = false;
            permiteAgrupar = false;
            repintarBotoesAlternar();
        }
    }//GEN-LAST:event_mouseReleasedLado

    private void mudaOpcaoOcultarFaces(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mudaOpcaoOcultarFaces
        if (OcultaFaces.isSelected()) {
            PainelFrente.apagarTodosCubosFrente(control.getListaCubos());
            PainelTopo.apagarTodosCubosTopo(control.getListaCubos());
            PainelLado.apagarTodosCubosLado(control.getListaCubos());
            if (SelecProjecaoPerspectiva.isSelected()) {
                PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                }
            }
        }
        desenharVisoesPaineis();
    }//GEN-LAST:event_mudaOpcaoOcultarFaces

    private void selecPerspectivaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecPerspectivaMouseClicked
        //((javax.swing.border.TitledBorder) PainelBaseProjecao.getBorder()).setTitle("Projeção - Perspectiva");
        //PainelBaseProjecao.repaint();
        PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
        int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
        int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
        int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
        Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
        int px = Integer.parseInt(SpinnerPX.getValue().toString());
        int py = Integer.parseInt(SpinnerPY.getValue().toString());
        int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
        Ponto p = new Ponto(px, py, pz);
        int dp = Integer.parseInt(SpinnerDP.getValue().toString());
        PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        AlternarVisaoProjecao.repaint();
    }//GEN-LAST:event_selecPerspectivaMouseClicked

    private void selecIsometricaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecIsometricaMouseClicked
        //((javax.swing.border.TitledBorder) PainelBaseProjecao.getBorder()).setTitle("Projeção - Isométrica");
        //PainelBaseProjecao.repaint();
        PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
        int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
        Ponto vrpIso = new Ponto(vrp, vrp, vrp);
        int px = Integer.parseInt(SpinnerPX.getValue().toString());
        int py = Integer.parseInt(SpinnerPY.getValue().toString());
        int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
        Ponto p = new Ponto(px, py, pz);
        PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
        AlternarVisaoProjecao.repaint();
    }//GEN-LAST:event_selecIsometricaMouseClicked

    private void BotaoAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAjudaActionPerformed
        menuAjuda.setVisible(true);
    }//GEN-LAST:event_BotaoAjudaActionPerformed

    private void keyTypedFrente(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTypedFrente
        //Delete
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (cuboIsSelected) {
                excluirCubo();
            }
        }
    }//GEN-LAST:event_keyTypedFrente

    private void keyPressedFrente(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyPressedFrente
        //Delete
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (cuboIsSelected) {
                excluirCubo();
            }
        }
    }//GEN-LAST:event_keyPressedFrente

    private void resizedFrente(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resizedFrente
        if (!control.getListaCubos().isEmpty()) {
            PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
            pintarCorSelecao();
        }
    }//GEN-LAST:event_resizedFrente

    private void resizedTopo(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resizedTopo
        if (!control.getListaCubos().isEmpty()) {
            PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
            pintarCorSelecao();
        }
    }//GEN-LAST:event_resizedTopo

    private void resizedProjecao(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resizedProjecao
        if (!control.getListaCubos().isEmpty()) {
            if (SelecProjecaoPerspectiva.isSelected()) {
                int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
                int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
                int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
                Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
                int px = Integer.parseInt(SpinnerPX.getValue().toString());
                int py = Integer.parseInt(SpinnerPY.getValue().toString());
                int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                Ponto p = new Ponto(px, py, pz);
                int dp = Integer.parseInt(SpinnerDP.getValue().toString());
                PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
            } else {
                if (SelecProjecaoIsometrica.isSelected()) {
                    int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
                    Ponto vrpIso = new Ponto(vrp, vrp, vrp);
                    int px = Integer.parseInt(SpinnerPX.getValue().toString());
                    int py = Integer.parseInt(SpinnerPY.getValue().toString());
                    int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                    Ponto p = new Ponto(px, py, pz);
                    PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
                }
            }
            pintarCorSelecao();
        }
    }//GEN-LAST:event_resizedProjecao

    private void resizedLado(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resizedLado
        if (!control.getListaCubos().isEmpty()) {
            PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
            pintarCorSelecao();
        }
    }//GEN-LAST:event_resizedLado

    private void changeSpinnerRotacao(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeSpinnerRotacao
        rotacionar();
    }//GEN-LAST:event_changeSpinnerRotacao

    private void changeSpinnerEscala(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changeSpinnerEscala
        escala();
    }//GEN-LAST:event_changeSpinnerEscala

    private void SelecionarCubosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelecionarCubosActionPerformed
        if (SelecionarCubos.isSelected()) {
            selecionaCubo = true;
            plotDesenho = false;
            DesenharCubos.setSelected(false);
        } else {
            habilitarBotoes(false);
            selecionaCubo = false;
            cuboIsSelected = false;
            if (selecLado) {
                PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                selecLado = false;
            }
            if (selecFrente) {
                PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                selecFrente = false;
            }
            if (selecTopo) {
                PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                selecTopo = false;
            }
        }
    }//GEN-LAST:event_SelecionarCubosActionPerformed

    private void DesenharCubosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesenharCubosActionPerformed
        if (DesenharCubos.isSelected()) {
            habilitarBotoes(false);
            if (cuboIsSelected) {
                cuboIsSelected = false;
                if (selecLado) {
                    PainelLado.pintarSelecaoLado(control.getCubo(cuboSelecIndice), Color.WHITE);
                    PainelLado.desenharVisaoLadoEsquerdo(control.getListaCubos(), OcultaFaces.isSelected());
                    selecLado = false;
                }
                if (selecFrente) {
                    PainelFrente.pintarSelecaoFrente(control.getCubo(cuboSelecIndice), Color.WHITE);
                    PainelFrente.desenharVisaoFrente(control.getListaCubos(), OcultaFaces.isSelected());
                    selecFrente = false;
                }
                if (selecTopo) {
                    PainelTopo.pintarSelecaoTopo(control.getCubo(cuboSelecIndice), Color.WHITE);
                    PainelTopo.desenharVisaoTopo(control.getListaCubos(), OcultaFaces.isSelected());
                    selecTopo = false;
                }
            }
            selecionaCubo = false;
            plotDesenho = true;
            SelecionarCubos.setSelected(false);
        } else {
            plotDesenho = false;
        }
    }//GEN-LAST:event_DesenharCubosActionPerformed

    private void SpinneVRPZChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinneVRPZChange
        if (SelecProjecaoPerspectiva.isSelected()) {
            PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
            int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
            int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
            Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            int dp = Integer.parseInt(SpinnerDP.getValue().toString());
            PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        }
    }//GEN-LAST:event_SpinneVRPZChange

    private void SpinnerVRPXChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerVRPXChange
        if (SelecProjecaoPerspectiva.isSelected()) {
            PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
            int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
            int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
            Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            int dp = Integer.parseInt(SpinnerDP.getValue().toString());
            PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        }
    }//GEN-LAST:event_SpinnerVRPXChange

    private void SpinnerVRPYChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerVRPYChange
        if (SelecProjecaoPerspectiva.isSelected()) {
            PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
            int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
            int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
            Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            int dp = Integer.parseInt(SpinnerDP.getValue().toString());
            PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        }
    }//GEN-LAST:event_SpinnerVRPYChange

    private void SpinnerDPChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerDPChange
        if (SelecProjecaoPerspectiva.isSelected()) {
            PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
            int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
            int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
            Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            int dp = Integer.parseInt(SpinnerDP.getValue().toString());
            PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        }
    }//GEN-LAST:event_SpinnerDPChange

    private void SpinnerPXChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerPXChange
        if (SelecProjecaoPerspectiva.isSelected()) {
            PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
            int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
            int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
            Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            int dp = Integer.parseInt(SpinnerDP.getValue().toString());
            PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        } else {
            if (SelecProjecaoIsometrica.isSelected()) {
                PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
                Ponto vrpIso = new Ponto(vrp, vrp, vrp);
                int px = Integer.parseInt(SpinnerPX.getValue().toString());
                int py = Integer.parseInt(SpinnerPY.getValue().toString());
                int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                Ponto p = new Ponto(px, py, pz);
                PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
            }
        }
    }//GEN-LAST:event_SpinnerPXChange

    private void SpinnerPYChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerPYChange
        if (SelecProjecaoPerspectiva.isSelected()) {
            PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
            int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
            int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
            Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            int dp = Integer.parseInt(SpinnerDP.getValue().toString());
            PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        } else {
            if (SelecProjecaoIsometrica.isSelected()) {
                PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
                Ponto vrpIso = new Ponto(vrp, vrp, vrp);
                int px = Integer.parseInt(SpinnerPX.getValue().toString());
                int py = Integer.parseInt(SpinnerPY.getValue().toString());
                int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                Ponto p = new Ponto(px, py, pz);
                PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
            }
        }
    }//GEN-LAST:event_SpinnerPYChange

    private void SpinnerPZChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerPZChange
        if (SelecProjecaoPerspectiva.isSelected()) {
            PainelProjecao.apagarTodosCubosPerspectiva(control.getListaCubos());
            int vrpx = Integer.parseInt(SpinnerVRPX.getValue().toString());
            int vrpy = Integer.parseInt(SpinnerVRPY.getValue().toString());
            int vrpz = Integer.parseInt(SpinnerVRPZ.getValue().toString());
            Ponto vrp = new Ponto(vrpx, vrpy, vrpz);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            int dp = Integer.parseInt(SpinnerDP.getValue().toString());
            PainelProjecao.desenharVisaoPerspectiva(control.getListaCubos(), OcultaFaces.isSelected(), vrp, p, dp);
        } else {
            if (SelecProjecaoIsometrica.isSelected()) {
                PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
                int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
                Ponto vrpIso = new Ponto(vrp, vrp, vrp);
                int px = Integer.parseInt(SpinnerPX.getValue().toString());
                int py = Integer.parseInt(SpinnerPY.getValue().toString());
                int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
                Ponto p = new Ponto(px, py, pz);
                PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
            }
        }
    }//GEN-LAST:event_SpinnerPZChange

    private void SpinnerSpinnerVRPIsometricaChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerSpinnerVRPIsometricaChange
        if (SelecProjecaoIsometrica.isSelected()) {
            PainelProjecao.apagarTodosCubosIsometrica(control.getListaCubos());
            int vrp = Integer.parseInt(SpinnerVRPIsometrica.getValue().toString());
            Ponto vrpIso = new Ponto(vrp, vrp, vrp);
            int px = Integer.parseInt(SpinnerPX.getValue().toString());
            int py = Integer.parseInt(SpinnerPY.getValue().toString());
            int pz = Integer.parseInt(SpinnerPZ.getValue().toString());
            Ponto p = new Ponto(px, py, pz);
            PainelProjecao.desenharVisaoIsometrica(control.getListaCubos(), OcultaFaces.isSelected(), vrpIso, p);
        }
    }//GEN-LAST:event_SpinnerSpinnerVRPIsometricaChange

    private void repintarBotoesAlternar() {
        AlternarVisaoFrente.repaint();
        AlternarVisaoTopo.repaint();
        AlternarVisaoLado.repaint();
        AlternarVisaoProjecao.repaint();
    }

    /*private void changeValorSpinnerRotacao (javax.swing.event.ChangeListener evt) {
        
     }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Painel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AbrirProjeto;
    private javax.swing.JButton AlternarVisaoFrente;
    private javax.swing.JButton AlternarVisaoLado;
    private javax.swing.JButton AlternarVisaoProjecao;
    private javax.swing.JButton AlternarVisaoTopo;
    private javax.swing.JMenu Arquivo;
    private javax.swing.JMenuBar BarraDeMenu;
    private javax.swing.JMenuItem BotaoAjuda;
    private javax.swing.JButton BotaoCorBordas;
    private javax.swing.JButton BotaoCorPreenchimento;
    private javax.swing.JButton BotaoDesagrupar;
    private javax.swing.JMenuItem BotaoSobre;
    private javax.swing.JMenu CorSelec;
    private javax.swing.JToggleButton DesenharCubos;
    private javax.swing.JMenu Editar;
    private javax.swing.JButton ExcluirCubo;
    private javax.swing.JMenu MenuAjuda;
    private javax.swing.JCheckBox OcultaFaces;
    private javax.swing.JPanel PainelBaseFrente;
    private javax.swing.JPanel PainelBaseLado;
    private javax.swing.JPanel PainelBaseProjecao;
    private javax.swing.JPanel PainelBaseTopo;
    private javax.swing.JPanel PainelCor;
    private javax.swing.JPanel PainelDesenho;
    private View.PainelExtendido PainelFrente;
    private View.PainelExtendido PainelLado;
    private javax.swing.JPanel PainelObservacao;
    private View.PainelExtendido PainelProjecao;
    private View.PainelExtendido PainelTopo;
    private javax.swing.JMenuItem Sair;
    private javax.swing.JMenuItem SalvarProjeto;
    private javax.swing.JRadioButtonMenuItem SelecAmarelo;
    private javax.swing.JRadioButtonMenuItem SelecAzul;
    private javax.swing.JRadioButtonMenuItem SelecCiano;
    private javax.swing.JRadioButtonMenuItem SelecCinza;
    private javax.swing.JRadioButtonMenuItem SelecCinzaClaro;
    private javax.swing.JRadioButtonMenuItem SelecCinzaEscuro;
    private javax.swing.JRadioButtonMenuItem SelecLaranjado;
    private javax.swing.JRadioButtonMenuItem SelecMagenta;
    private javax.swing.JRadioButtonMenuItem SelecPreto;
    private javax.swing.JRadioButton SelecProjecaoIsometrica;
    private javax.swing.JRadioButton SelecProjecaoPerspectiva;
    private javax.swing.JRadioButtonMenuItem SelecRosa;
    private javax.swing.JRadioButtonMenuItem SelecVerde;
    private javax.swing.JRadioButtonMenuItem SelecVermelho;
    private javax.swing.JToggleButton SelecionarCubos;
    private javax.swing.JMenu Sombreamento;
    private javax.swing.JCheckBoxMenuItem SombreamentoFlat;
    private javax.swing.JCheckBoxMenuItem SombreamentoGouraud;
    private javax.swing.JCheckBoxMenuItem SombreamentoNenhum;
    private javax.swing.JCheckBoxMenuItem SombreamentoPhong;
    private javax.swing.JSpinner SpinnerDP;
    private javax.swing.JSpinner SpinnerEscala;
    private javax.swing.JSpinner SpinnerKA;
    private javax.swing.JSpinner SpinnerKD;
    private javax.swing.JSpinner SpinnerKS;
    private javax.swing.JSpinner SpinnerLuzAmbiente;
    private javax.swing.JSpinner SpinnerLuzPontual;
    private javax.swing.JSpinner SpinnerN;
    private javax.swing.JSpinner SpinnerPX;
    private javax.swing.JSpinner SpinnerPY;
    private javax.swing.JSpinner SpinnerPZ;
    private javax.swing.JSpinner SpinnerRotacao;
    private javax.swing.JSpinner SpinnerVRPIsometrica;
    private javax.swing.JSpinner SpinnerVRPX;
    private javax.swing.JSpinner SpinnerVRPY;
    private javax.swing.JSpinner SpinnerVRPZ;
    private javax.swing.JSpinner SpinnerViewUpX;
    private javax.swing.JSpinner SpinnerViewUpY;
    private javax.swing.JSpinner SpinnerViewUpZ;
    private javax.swing.ButtonGroup buttonGroupCores;
    private javax.swing.ButtonGroup buttonGroupProjecao;
    private javax.swing.ButtonGroup buttonGroupSombreamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
