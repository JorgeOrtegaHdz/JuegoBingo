package cliente;

import java.awt.GridLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import servidor.opciones;

/**
 *
 * @author Jorge Ortega Hernandez
 */
public class cliente_bingo {
    public static void main(String[]args){
        /*String prueba[][]={{"A32","D65","G78","E76"},{"A32","D65","G78","E76"},
            {"A32","D65","G78","E76"},{"A32","D65","G78","E76"}};
        Carton carton= new Carton(prueba);
        carton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        carton.setSize(720,720);
        carton.setVisible(true);*/
        try{
            String host=JOptionPane.showInputDialog(null,"Escribe la direccion del servidor: ","localhost");
            int pto=Integer.parseInt(JOptionPane.showInputDialog(null, "Escribe el puerto: ","1234"));
            Socket cl=new Socket(host,pto);
            JOptionPane.showMessageDialog(null, "Conexion Establecida");
            ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
            opciones opc=(opciones)ois.readObject();
            String datos[][]=opc.GetOpc();
            Carton carton= new Carton(datos,oos,ois);
            carton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            carton.setSize(720,720);
            carton.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
