package SportManiaGUI;

import Comunicacao.Vendas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UsuarioGUI extends JFrame{
    private JPanel mainPanel;
    private JScrollPane displayPanel;
    private JTextField idProduto;
    private JTextField quantProduto;
    private JTextField cpfCliente;
    private JButton processarButton;
    private JButton finalizarButton;
    private JLabel displayCompras;
    private JTextField textField1;
    private JLabel laberEncontrarID;
    private JButton procurarButton;
    private JLabel resultIDProduto;

    public UsuarioGUI(String appNome){
        super(appNome);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1080, 720);

        displayPanel.setSize(1080, 80);
        displayPanel.setBackground(Color.WHITE);
        displayCompras.setText("Compra tal ... 18181");



        this.pack();

        processarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // TODO chamar o método de Vendas
            }
        });
        finalizarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // TODO criar método para finalizar compra (exibe o total & reinicia o processo)
            }
        });

        procurarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // TODO procurar a ID do produto, salva numa variável e "seta" o resultIDProduto
                //resultIDProduto.setText();
            }
        });
    }
}
