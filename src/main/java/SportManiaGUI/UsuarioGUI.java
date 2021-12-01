package SportManiaGUI;

import javax.swing.*;
import java.awt.*;

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

    public UsuarioGUI(String appNome){
        super(appNome);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1080, 720);

        displayPanel.setSize(1080, 80);
        displayPanel.setBackground(Color.WHITE);
        displayCompras.setText("Compra tal ... 18181");

        //DisplayUsuario.getWindows();


        this.pack();
    }
}
