package ui;

import data.ReputacionManager;
import service.GlobalService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private static int reputacion;
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
                    //globalService.eliminarGuardado();
                    generarAccionesMenu(scanner,reputacionManager);
                }else if(opcionUsuario==2){
                    reputacionManager.abrirGuardado();
                    reputacion=reputacionManager.obtenerReputacionInicial();
                    generarAccionesMenu(scanner,reputacionManager);

                }else{
                    System.err.println("Elige una opcion correcta");
                    elegioOpcion=false;
                }
            }else{
                int opcionUsuario=arreglarint("",scanner);
                if(opcionUsuario==1){
                    generarAccionesMenu(scanner,reputacionManager);


                }else{
                    System.err.println("Elige una opcion correcta");
                    elegioOpcion=false;
                }
            }
        }while (!elegioOpcion);
    }

    private  static int arreglarint(String pregunta, Scanner scanner){
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

    private static void generarMenu(){
        System.out.println("1. Crear pociones");
        System.out.println("2. Vender pociones");
        System.out.println("3. Comprar ingredientes");
        System.out.println("4. Mostrar estadísticas del juego ");
        System.out.println("5. Mostrar comerciantes");
        System.out.println("6. Mostrar pociones");
        System.out.println("7. Salir");
    }

    private static void generarAccionesMenu(Scanner scanner,ReputacionManager reputacionManager){
        boolean elegioOpcion=false;
        do{
            generarMenu();
            int accionEscogida=arreglarint("",scanner);
            switch (accionEscogida){
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:
                    System.out.println("REPUTACIÓN: "+reputacion);
                    System.out.println("\n");
                    System.out.println("INVENTARIO INGREDIENTES: ");
                    recorrerLista(globalService.obtenerInventarioIngredientes());
                    System.out.println("\n");
                    System.out.println("INVENTARIO POCIONES: ");
                    recorrerLista(globalService.obtenerInventarioPociones());
                    break;

                case 5:
                    System.out.println("Opción seleccionada: MOSTRAR COMERCIANTES");
                    recorrerLista(globalService.obtenerComerciantes());
                    break;

                case 6:
                    System.out.println("Opción seleccionada: MOSTRAR POCIONES");
                    recorrerLista(globalService.obtenerPociones());
                    break;

                case 7:
                    scanner.close();
                    reputacionManager.abrirGuardado();
                    reputacionManager.guardarReputacion(reputacion);
                    globalService.cerrarConexion();
                    elegioOpcion=true;
                    break;
                default:
                    System.err.println("Elige una opción valida!!!");

            }


        }while (!elegioOpcion);
    }

    private static <G> void recorrerLista(List<G> list){
        if(!list.isEmpty()){
            list.forEach(System.out::println);
        }

    }



}
