package servidor;

import java.net.ServerSocket;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Jorge Ortega Hernandez
 */
public class servidor_bingo {
    public static void main(String[]args){
        try{
            ServerSocket s=new ServerSocket(1234);
            JOptionPane.showMessageDialog(null,"Servicio iniciado");
            opciones opc=new opciones();
            for(int i=0;i<2;i++){
                Socket cl=s.accept();
                Manejador m=new Manejador(cl,opc);
                m.start();
            }
            for(int i=0;i<100;i++){
                opc.dato=opc.getFicha();
                System.out.println(opc.dato+"  "+i);
                Thread.sleep(2000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class Manejador extends Thread{
    Socket cl;
    opciones opc;
    public Manejador(Socket c,opciones opc){
        cl=c;
        this.opc=opc;
    }
    public void run(){
        try{
            String anterior="aun no";
            ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
            oos.writeObject(opc);
            oos.flush();
            
            for(int i=0;i<100;){
                //System.out.println("Dato= "+anterior);
                Thread.sleep(100);
                if(!opc.dato.equals(anterior)){
                    anterior=opc.dato;
                    System.out.println("Hijo escribio: "+anterior);
                    oos.writeUTF(opc.dato);
                    oos.flush();
                    if(ois.readInt()==1){
                        System.out.println("Jugador ganador con la direccion "+cl.getLocalAddress());
                        opc.dato="Jugador ganador con la direccion "+cl.getLocalAddress();
                        System.exit(0);
                    }
                    i++;
                }
            }
            System.exit(-1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
