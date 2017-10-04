/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorabermejo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bermejo
 */
class Acceso {
    public boolean login(){
        System.out.println("Introduzca su usuario: ");
        Scanner scan = new Scanner(System.in);
        String usuario = scan.next();
        System.out.println("Introduzca su contraseña: ");
        String contrasena = scan.next();
        if(comparaDatosLogin(usuario,contrasena)){
            return true;
        } else {
            return false;
        }
    }
    
    public void creaFichero(){
        File file = new File("ficheroAcceso");
        
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("admin-123456789\n".getBytes());
            fos.write("javi-123456789\n".getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private boolean comparaDatosLogin(String usuario, String contrasena) {
        File file = new File("ficheroAcceso");
        int contentB;
        String usuarioLeido = "";
        String contrasenaLeida = "";
        String leyendo = "";
        String newline = System.getProperty("line.separator");
        try {
            FileInputStream fis = new FileInputStream(file);
            while ((contentB=fis.read())!=-1){
                String content = String.valueOf((char)contentB);
                if("-".equals(content)){
                    usuarioLeido = leyendo;
                    leyendo = "";
                    content = "";
                }
                if(String.valueOf(content).contains(newline)){
                    contrasenaLeida = leyendo;
                    leyendo = "";
                    content = "";
                    if (usuario.equals(usuarioLeido)&&contrasena.equals(contrasenaLeida)){
                        return true;
                    }
                }
                leyendo = leyendo + content;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    boolean creaUsuarios(){
        File file = new File("ficheroAcceso");
        
        System.out.println("Introduzca el nombre del nuevo usuario: ");
        Scanner scan = new Scanner(System.in);
        String usuario = scan.next();
        System.out.println("Introduzca la contraseña del nuevo usuario: ");
        String contrasena = scan.next();
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(usuario.getBytes());
            fos.write("-".getBytes());
            fos.write(contrasena.getBytes());
            fos.write("\n".getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex){
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    boolean eliminaUsuarios() {
        File file = new File("ficheroAcceso");
        File fTemporal = new File("temporal");
        
        int contentB;
        String leyendo = "";
        String newline = System.getProperty("line.separator");
        
        System.out.println("Introduzca el nombre del usuario a eliminar: ");
        Scanner scan = new Scanner(System.in);
        String usuario = scan.next();
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(fTemporal);
            while ((contentB=fis.read())!=-1){
                String content = String.valueOf((char)contentB);
                leyendo = leyendo + content;
                if(usuario.equals(leyendo)){
                    leyendo = "";
                    content = "";
                    while (!String.valueOf((char)fis.read()).contains(newline)){}
                }else if(String.valueOf(content).contains(newline)){
                    fos.write(leyendo.getBytes());
                    fos.flush();
                    leyendo = "";
                    content = "";
                }
            }
            fos.close();
            fis.close();
            file.delete();
            fTemporal.renameTo(file);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex){
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
