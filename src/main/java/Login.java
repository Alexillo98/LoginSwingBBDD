import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Provider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Login extends JFrame implements KeyListener {

    java.sql.Connection con;
    JPanel panel = new JPanel();
    JLabel usuario = new JLabel("USUARIO");
    JLabel password = new JLabel("CONTRASEÑA");
    JTextField usuarioText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    public Login() throws SQLException{
        super("LOGIN");
        Servicio s = new Servicio();
         con = s.getConnection();
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        panel.setBackground(Color.CYAN);
        add(panel);
        panel.setLayout(null);
        usuario.setBounds(10,30,100,20);
        password.setBounds(10,60,100,20);

        usuarioText.setBounds(115,30,100,20);
        passwordText.setBounds(115,60,100,20);
        panel.add(usuario);
        panel.add(password);
        panel.add(usuarioText);
        panel.add(passwordText);
        usuarioText.addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    int a = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                if(comprobarLogin()){
                    JOptionPane.showMessageDialog(null,"SE HA CONFIRMADO LA SESIÓN");
                } else {
                    JOptionPane.showMessageDialog(null,"NO SE HA CONFIRMADO LA SESIÓN");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    int a = 0;
    }

    public boolean comprobarLogin() throws SQLException {
        String query = "SELECT name FROM usuario WHERE name = ? AND password = ?";
        PreparedStatement pst =  con.prepareStatement(query);

        String usuario = usuarioText.getText();
        String password = Arrays.toString(passwordText.getPassword());
        pst.setString(1,usuario);
        pst.setString(2,password);

        ResultSet rs = pst.executeQuery();
        return (rs.next());
    }
}
