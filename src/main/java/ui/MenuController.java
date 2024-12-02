package ui;

import data.ReputacionManager;
import service.GlobalService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    private int reputacion;
    private static GlobalService globalService;

    public MenuController(){
        globalService=new GlobalService();
    }


    public void generarInicioAplicacion(){
        System.out.println("====== BIENVENIDO A POTIONCRAFT =======");

        ReputacionManager reputacionManager=ReputacionManager.getInstance();
        Scanner scanner=new Scanner(System.in);
        boolean elegioOpcion=true;
        do {
            System.out.println("1. Nueva partida");
            if(reputacionManager.existeFicheroReputacion()){
                System.out.println("2. Cargar partida");
                int opcionUsuario=arreglarint("",scanner);
                if(opcionUsuario==1){
                    reputacionManager.borrarFicheroReputacion();
                    globalService.eliminarGuardado();
                }else if(opcionUsuario==2){
                    reputacionManager.abrirGuardado();
                    reputacion=reputacionManager.obtenerReputacionInicial();
                }else{
                    System.err.println("Elige una opcion correcta");
                    elegioOpcion=false;
                }
            }else{
                int opcionUsuario=arreglarint("",scanner);
                if(opcionUsuario==1){

                }else{
                    System.err.println("Elige una opcion correcta");
                    elegioOpcion=false;
                }
            }
        }while (!elegioOpcion);
    }

    private static int arreglarint(String pregunta, Scanner scanner){
        boolean opcionCorrecta=false;
        int respuesta=0;
        do {
            try{
                if(!pregunta.isBlank()){
                    System.out.println(pregunta);
                }
                respuesta=scanner.nextInt();
                opcionCorrecta=true;


            } catch (InputMismatchException e) {
                System.err.println("Escribe un numero valido");
                scanner.nextLine();
            }
        }while(!opcionCorrecta);
        return respuesta;
    }
}
