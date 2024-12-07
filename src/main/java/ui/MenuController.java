package ui;

import data.ReputacionManager;
import model.*;
import service.GlobalService;

import java.util.*;
import java.util.stream.Collectors;

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
                    globalService.eliminarInventario();
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

    private static int arreglarCantidad(String pregunta,Scanner scanner) {
        boolean opcionCorrecta=false;
        int cantidad;
        do {
            cantidad=arreglarint(pregunta,scanner);
            if(cantidad>0){
                opcionCorrecta=true;
            }else{
                System.err.println("Elige una cantidad correcta!!!");
            }

        }while (!opcionCorrecta);
        return cantidad;

    }

    private  static int arreglarint(String pregunta, Scanner scanner){
        boolean opcionCorrecta=false;
        int respuesta = 0;
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

    private static Map<Pocion,List<InventarioIngrediente>>  generarMenu1(){
        System.out.println("========Pociones disponibles para fabricar==============");
        //Map pocion,lista ingrediente , lo busco
        List<Pocion> pociones=globalService.obtenerPociones();
        List<InventarioIngrediente> ingredientes=globalService.obtenerInventarioIngredientes();
        Map<Pocion,List<InventarioIngrediente>> pocionesFabricables=new HashMap<>();
        if(!ingredientes.isEmpty()){
            Set<Ingrediente> ingredientesAlmacenados = ingredientes.stream()
                    .map(InventarioIngrediente::getIngrediente)
                    .collect(Collectors.toSet());
            int indice=0;
            for(Pocion pocion:pociones){
                if (ingredientesAlmacenados.containsAll(pocion.getIngredientes())){
                    indice++;
                    System.out.println(indice+". "+pocion);
                    List<InventarioIngrediente> inventarioIngredientes=ingredientes.stream().filter(ingrediente -> pocion.getIngredientes().contains(ingrediente.getIngrediente())).toList();
                    pocionesFabricables.put(pocion,inventarioIngredientes);
                }
            }
        }

        return pocionesFabricables;
    }

    private static void generarAccionMenu1(Scanner scanner){
       Map<Pocion,List<InventarioIngrediente>> pocionListMap= generarMenu1();
       List<Pocion> pocionesCrafteable=pocionListMap.keySet().stream().toList();
       if(pocionesCrafteable.isEmpty()){
           System.out.println("Te faltan ingredientes necesarios para fabricar alguna poción");
       }else{
           boolean eligioAccion=true;
           do {
               int pocionElegida=arreglarint("Seleccione el número de la pocion que desee craftear",scanner);
               if(pocionElegida > 0 && pocionElegida <= pocionesCrafteable.size()){
                   Pocion pocionFabricable=pocionesCrafteable.get(pocionElegida-1);
                   System.out.println("Has seleccionado fabricar: "+pocionFabricable.getNombre());
                   calcularReputacion(pocionFabricable.getIngredientes());
                   InventarioPocion pocion=new InventarioPocion(pocionFabricable);
                   List<InventarioIngrediente> ingredientes=pocionListMap.get(pocionFabricable);
                   globalService.agregarPocion(pocion,ingredientes);
                   System.out.println("Poción fabricada con éxito "+pocionFabricable.getNombre());

               }else if(pocionElegida==0){
                   System.out.println("Cancelando !!!");
               }else{
                   System.err.println("Elige una poción correcta!!!!");
                   eligioAccion=false;
               }

           }while (!eligioAccion);


           System.out.println("O.Cancelar");
       }

    }

    private static void calcularReputacion(List<Ingrediente> ingredientes){
        int reputacionNegativa=0;
        int reputacionPositiva=0;
        int reputacionCambiada=0;

        for(Ingrediente ingrediente:ingredientes){
            if(ingrediente.getEfectoNegativo()!=null){
                reputacionNegativa++;

            }else if(ingrediente.getEfectoPositivo()!=null){
                reputacionPositiva++;
            }

        }
        if(reputacionNegativa<reputacionPositiva){
            reputacion++;
            reputacionCambiada++;
        }else if(reputacionPositiva<reputacionNegativa){
            reputacion--;
            reputacionCambiada--;
        }
        System.out.println("Reputación actualizada: "+reputacionCambiada+" (Positivos: "+reputacionPositiva+", Negativos: "+reputacionNegativa+")");
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
                    generarAccionMenu1(scanner);
                    break;

                case 2:
                    globalService.venderPociones();
                    break;

                case 3:
                    Comerciante comerciante= globalService.obtenerComercianteAleatorio();
                    generarAccionMenu3(comerciante,scanner);
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
                    globalService.cerrarConexionBaseDatos();
                    elegioOpcion=true;
                    break;
                default:
                    System.err.println("Elige una opción valida!!!");

            }


        }while (!elegioOpcion);
    }

    private static void generarMenu3(Comerciante comerciante){
        System.out.println("Visita: "+comerciante.getNombre()+" ("+comerciante.getTipoComerciante()+")");
        System.out.println("========Menú de compra =====");
        System.out.println("Ingredientes disponibles:");
    }



    private static void generarAccionMenu3(Comerciante comerciante, Scanner scanner) {
        generarMenu3(comerciante);
        List<Ingrediente> ingredientes = obtenerIngredientesAleatroios(comerciante.getIngredientes());
        boolean elegioOpcion = false;
        double gastoTotal = 0;

        do {
            mostrarIngredientesTienda(ingredientes);
            int ingredienteEscogido = arreglarint("Seleccione el número del ingrediente que desea comprar:", scanner);

            if (ingredienteEscogido == 0) {
                elegioOpcion = true;
                System.out.println("Fin de la visita de " + comerciante.getNombre() + "\n" + "ORO TOTAL INVERTIDO: " + gastoTotal + " oros");
            } else if (ingredienteEscogido > 0 && ingredienteEscogido <= ingredientes.size()) {
                Ingrediente ingredienteSeleccionado = ingredientes.get(ingredienteEscogido - 1);
                System.out.println("Has seleccionado: " + ingredienteSeleccionado.getNombre());

                long cantidad = arreglarCantidad("¿Cuántas unidades desea comprar?", scanner);
                InventarioIngrediente inventarioIngrediente = new InventarioIngrediente(cantidad, ingredienteSeleccionado);
                globalService.comprarIngredientes(inventarioIngrediente);
                System.out.println("Has comprado "+inventarioIngrediente.getCantidad()+" unidades de "+inventarioIngrediente.getNombre()+" por "+inventarioIngrediente.calcularGasto());

                gastoTotal += inventarioIngrediente.calcularGasto();
            } else {
                System.err.println("Elige un ingrediente correcto!!!");
            }
        } while (!elegioOpcion);
    }



    private static List<Ingrediente> obtenerIngredientesAleatroios(List<Ingrediente> ingredienteList){
        Random random=new Random();
        List<Ingrediente> ingredientes=new ArrayList<>();
        for(int i=1;i<6;i++){
            int ingredienteEscogido=random.nextInt(0,ingredienteList.size());
            ingredientes.add(ingredienteList.get(ingredienteEscogido));
        }
        return ingredientes;
    }

    private static void mostrarIngredientesTienda(List<Ingrediente> ingredientes){
        for(int i=1;i<6;i++){
            System.out.println(i+". "+ingredientes.get(i-1));
        }
        System.out.println(0+". Salir");

    }

    private static <G> void recorrerLista(List<G> list){
        if(!list.isEmpty()){
            list.forEach(System.out::println);
        }

    }



}
