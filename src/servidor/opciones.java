/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author JorgeOrtega
 */
public class opciones implements Serializable{
    public String []opc=new String[100];
    private int tam=opc.length;
    public String dato="aun no";
    public opciones(){
        int num,l;
        for(int i=0;i<100;i++){
            num=dameAleatorio(100);
            l=(dameAleatorio(25))+65;
            String dato=((char)l)+(num+"");
            opc[i]=dato;
        }        
    }
    public String getFicha(){
        int pos=dameAleatorio(tam);
        String ficha=opc[pos];
        --tam;
        for(int i=pos;i<tam;i++){
            opc[i]=opc[i+1];
        }
        return ficha;
    }
    public String[][]GetOpc(){
        String[][]nue=new String[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                nue[i][j]=opc[dameAleatorio(100)];
            }
        }
        return nue;
    }
    public int dameAleatorio(int rango){
        Random numAlea=new Random();
        return numAlea.nextInt(rango);
    }
}
