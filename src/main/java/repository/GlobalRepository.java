package repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.*;

import java.util.List;
import java.util.Random;

//Esta clase la uso para manejar la conexión de java y la base de datos
public class GlobalRepository {
    private static GlobalRepository globalRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;




    private GlobalRepository(){
        entityManagerFactory = Persistence.createEntityManagerFactory("potion-craft");
        entityManager=entityManagerFactory.createEntityManager();
    }

    public static GlobalRepository getInstance(){
        if(globalRepository==null){
            globalRepository=new GlobalRepository();
        }
        return globalRepository;
    }

    //para obtener una lista de las pociones que tiene el usuario fabricadas
    public List<InventarioPocion> obtenerInventarioPociones(){
        String consultaSQL="SELECT i FROM InventarioPocion i";
        Query query=entityManager.createQuery(consultaSQL);
        List<InventarioPocion> inventarioPociones=query.getResultList();
        return inventarioPociones;
    }
    //para obtener la lista de los ingredientes que tiene el usuario
    public List<InventarioIngrediente> obtenerInventarioIngredientes(){
        String consultaSQL="SELECT i FROM InventarioIngrediente i";
        Query query=entityManager.createQuery(consultaSQL);
        List<InventarioIngrediente> inventarioIngredientes=query.getResultList();
        return inventarioIngredientes;
    }
    //para obtener una lista de todos los comerciantes que pueden visitar al usuario
    public List<Comerciante> obtenerComerciantes(){
        String consultaSQL="SELECT c FROM Comerciante c";
        Query query=entityManager.createQuery(consultaSQL);
        List<Comerciante> comerciantes=query.getResultList();
        return  comerciantes;
    }
    //para obtener una lsita de todas las pociones de la base de datos
    public List<Pocion> obtenerPociones(){
        String consultaSQL="SELECT p FROM Pocion p";
        Query query=entityManager.createQuery(consultaSQL);
        List<Pocion> pociones=query.getResultList();
        return  pociones;
    }
    //metodo para fabricar una pocion usando ingredientes del usuario
    public void agregarPocion(InventarioPocion pocion,List<InventarioIngrediente> ingredientes){
        try {
            entityManager.getTransaction().begin();
            InventarioPocion pocionExistente=obtenerPocionFabricada(pocion.getIdPocion());
            agregarElemento(pocion,pocionExistente);
            entityManager.getTransaction().commit();
            //lo he hecho dependiente del metodo agregar pocion por pura logica
            //si se ha podido añadir la pocion puedo quitaler los ingredientes
            //si ha fallado , no se han usado los ingredientes
            actualizarIngredientes(ingredientes);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    //metodo para actualizar los ingredientes restantes , al haberlos usado para hacer pociones
    private void actualizarIngredientes(List<InventarioIngrediente> ingredientesUsados){
        try {
            entityManager.getTransaction().begin();
            for(InventarioIngrediente ingrediente:ingredientesUsados){
                long cantidadRestante= ingrediente.getCantidad()-1;
                if(cantidadRestante==0){
                    entityManager.remove(ingrediente);
                }else{
                    ingrediente.setCantidad(cantidadRestante);
                    entityManager.merge(ingrediente);
                }
            }
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    //metodo para vender todas  pociones hechas por el usuario
    public void venderPociones(){
        try {
            entityManager.getTransaction().begin();
            calcularBeneficios();
            entityManager.createQuery("DELETE FROM InventarioPocion").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }
    //metodo para obtener un comerciante aleatorio
    public Comerciante obtenerComercianteAleatorio(){
        Random random=new Random();
        int comercianteEscogido=random.nextInt(0,obtenerComerciantes().size());
        return obtenerComerciantes().get(comercianteEscogido);
    }
    //he creado este metodo debido a que puede producirse la ocasio en la que se pueda comprar un ingrediente
    //que ya tenga el usuario comprado, y al tratar de hace un persist(un INSERT) ,a una tabla donde id ya esista(es decir
    //donde el usuario ya tenga ese ingrediente), produce error for Duplicate entry
    private InventarioIngrediente obtenerIngredienteComprado(Long id){
        String consultaSQL="SELECT i FROM  InventarioIngrediente i WHERE i.ingrediente.id  = :id";
        Query query=entityManager.createQuery(consultaSQL,InventarioIngrediente.class).setParameter("id",id);
        return (InventarioIngrediente) query.getSingleResultOrNull();
    }

    private InventarioPocion obtenerPocionFabricada(Long id){
        String consultaSQL="SELECT i FROM  InventarioPocion i WHERE i.pocion.id  = :id";
        Query query=entityManager.createQuery(consultaSQL,InventarioPocion.class).setParameter("id",id);
        return (InventarioPocion) query.getSingleResultOrNull();
    }


    //metodo para calcular el dinero ganado por vender cada pocion
    //aunque realmente no importa aqui el dinero es infinito
    private void calcularBeneficios(){
        List<InventarioPocion> inventarioPociones=obtenerInventarioPociones();
        if(!inventarioPociones.isEmpty()){
            double gananciasTotales=0;
            System.out.println("=========VENDIENDO TODAS LAS POCIONES=============");
            for(InventarioPocion inventarioPocion:inventarioPociones){
                long numeroPociones=inventarioPocion.getCantidad();
                double gananciaPocion=inventarioPocion.venderPociones()*numeroPociones;
                System.out.println("Has vendido "+numeroPociones+" unidades de "+inventarioPocion.getPocion().getNombre()+" por "+gananciaPocion+" monedas de oro");
                gananciasTotales+=gananciaPocion;
            }
            System.out.println("TOTAL de ganancias: "+gananciasTotales+" monedas de oro");
        }else{
            System.out.println("Sin pociones que vender!!!");
        }

    }
    //metodo para agregar un nuevo elemento(pocion,ingrediente), al inventario del usuario
    //o aumentar su cantidad en el caso de que ya exista
    private void agregarElemento(InventarioPocion pocion, InventarioPocion pocionExistente) {
        if (pocionExistente == null) {
            entityManager.persist(pocion);
        } else {
            long cantidadNueva = pocionExistente.getCantidad() + pocion.getCantidad();
            pocionExistente.setCantidad(cantidadNueva);
        }
    }

    private void agregarElemento(InventarioIngrediente ingrediente, InventarioIngrediente ingredienteExistente) {
        if (ingredienteExistente == null) {
            entityManager.persist(ingrediente);
        } else {
            long cantidadNueva = ingredienteExistente.getCantidad() + ingrediente.getCantidad();
            ingredienteExistente.setCantidad(cantidadNueva);
        }
    }
    //metodo para compar ingredientes al comerciante
    public void comprarIngredientes(InventarioIngrediente ingrediente){
        try {
            entityManager.getTransaction().begin();
            InventarioIngrediente ingredienteExistente=obtenerIngredienteComprado(ingrediente.getIdIngrediente());
            agregarElemento(ingrediente,ingredienteExistente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    public void eliminarDatosGuardados(){
        try {
            entityManager.getTransaction().begin();

            entityManager.createQuery("DELETE FROM InventarioPocion").executeUpdate();
            entityManager.createQuery("DELETE FROM InventarioIngrediente ").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void cerrarConexion(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
