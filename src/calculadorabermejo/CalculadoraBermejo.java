/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorabermejo;

import java.util.Scanner;

/**
 *
 * @author bermejo
 */
public class CalculadoraBermejo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Acceso acceso = new Acceso();
        int opcion=0;
        System.out.println("Hola, vamos a loguearnos");
        //acceso.creaFichero();
        if (acceso.login()){
            System.out.println("Login correcto");
            while(opcion!=3){
                System.out.println("Que quieres hacer?");
                System.out.println("1. Crear usuario");
                System.out.println("2. Eliminar Usuario");
                System.out.println("3. Salir");
                Scanner scan = new Scanner(System.in);
                opcion = scan.nextInt();
                if(opcion==1){
                    if(acceso.creaUsuarios()){
                        System.out.println("Añadido!");
                    } else{
                        System.out.println("Error");
                    }
                }else if (opcion==2){
                    if(acceso.eliminaUsuarios()){
                        System.out.println("Borrado");
                    } else {
                        System.out.println("Error");
                    }
                }
            }
        } else {
            System.out.println("Error");
        }
        System.out.println("adios");
    }
    
}
