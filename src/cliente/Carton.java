/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
/**
 *
 * @author jorge
 */
public class Carton extends JFrame{
    private Container contenedor;
    private GridLayout cuadricula;
    private JButton botones[]=new JButton[16];
    
    public Carton(String opc[][],ObjectOutputStream oos,ObjectInputStream ois){
        JPanel titulo=new JPanel();
        JPanel tab=new JPanel();
        JPanel ficha=new JPanel();
        JLabel f=new JLabel();
        int i=0,k=0,h=0;
        JLabel eti=new JLabel("   B   -   I   -   N   -   G   -   O");
        eti.setFont(new Font("Monospaced",Font.BOLD,30));
        cuadricula=new GridLayout(8,8);
        contenedor=getContentPane();
        titulo.setLayout(new BorderLayout());
        ficha.setLayout(new GridLayout());
        f.setText("Esperando a mas jugadores");
              
        tab.setLayout(cuadricula);
        setLayout(new BorderLayout());
        ficha.add(f);
        
        for(int indice=0;indice<32;indice++){
            if(indice%8==0){
                for(int j=0;j<8;j++){
                    JButton vacio=new JButton();
                    vacio.setBorder(null);
                    vacio.setBackground(java.awt.Color.white);
                    tab.add(vacio);
                }
            }
            if((indice%2)==0 && indice<32){
                //char l=(char)(65+numAlea.nextInt(26));
                String l=opc[h][k];
                //System.out.println("Letra insertada: "+l+" h:"+h+" k:"+k+" i:"+i);
                botones[i]=new JButton(""+l);
                botones[i].setBorder(null);
                botones[i].setBackground(java.awt.Color.WHITE);
                tab.add(botones[i]);
                if((h%3)==0 && h!=0){
                    k++;
                    h=0;
                }else{
                    h++;
                }
                i++;
            }else{
                JButton vacio=new JButton();
                vacio.setBorder(null);
                vacio.setBackground(java.awt.Color.white);
                tab.add(vacio);
            }
        }
        
        titulo.add(eti,BorderLayout.CENTER);
        titulo.setBackground(java.awt.Color.yellow);
        add(titulo,BorderLayout.NORTH);
        add(tab,BorderLayout.CENTER);
        add(ficha,BorderLayout.SOUTH);
        this.setSize(720,720);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        k=0;
        try{
            for(;;){
                String bola=ois.readUTF();
                k++;
                f.setText("Nombre ficha: "+bola+"    Numero de ficha: "+k);
                for(i=0;i<16;i++){
                    if(bola.equals(botones[i].getText())){
                        botones[i].setBackground(Color.yellow);
                    }
                }
                Thread.sleep(1200);
                if(bingo()){
                    oos.writeInt(1);
                    oos.flush();
                    JOptionPane.showMessageDialog(this, "Usted a ganado");
                    System.exit(0);
                }else{
                    oos.writeInt(0);
                    oos.flush();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private boolean bingo(){
        boolean ban=true,ban2=false;
        /*for(int i=0;i<16;i++){
            if(botones[i].getBackground()!=Color.yellow){
                ban=false;
                //JOptionPane.showMessageDialog(this, "Aun no por: "+botones[i].getText());
                break;
            }
        }*/
        for(int i=0;i<16;i+=4){
            ban=true;
            for(int j=0;j<4;j++){
                if(botones[i+j].getBackground()!=Color.yellow){
                    ban=false;
                }
            }
            if(ban){
                ban2=true;
            }
        }
        for(int i=0;i<4;i++){
            ban=true;
            for(int j=0;j<16;j+=4){
                if(botones[i+j].getBackground()!=Color.yellow){
                    ban=false;
                }
            }
            if(ban){
                ban2=true;
            }
        }
        ban=true;
        for(int i=0;i<=15;i+=5){
            if(botones[i].getBackground()!=Color.yellow){
                ban=false;
            }
        }
        if(ban){
            ban2=true;
        }
        ban=true;
        for(int i=0;i<=12;i+=3){
            if(botones[i].getBackground()!=Color.yellow){
                ban=false;
            }
        }
        if(ban){
            ban2=true;
        }
        return ban2;
    }
}
