package repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.Comerciante;
import model.InventarioIngrediente;
import model.InventarioPocion;
import model.Pocion;

import java.util.List;


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


    public List<InventarioPocion> obtenerInventarioPociones(){
        String consultaSQL="SELECT i FROM InventarioPocion i";
        Query query=entityManager.createQuery(consultaSQL);
        List<InventarioPocion> inventarioPociones=query.getResultList();
        return inventarioPociones;
    }

    public List<InventarioIngrediente> obtenerInventarioIngredientes(){
        String consultaSQL="SELECT i FROM InventarioIngrediente i";
        Query query=entityManager.createQuery(consultaSQL);
        List<InventarioIngrediente> inventarioIngredientes=query.getResultList();
        return inventarioIngredientes;
    }

    public List<Comerciante> obtenerComerciantes(){
        String consultaSQL="SELECT c FROM Comerciante c";
        Query query=entityManager.createQuery(consultaSQL);
        List<Comerciante> comerciantes=query.getResultList();
        return  comerciantes;
    }

    public List<Pocion> obtenerPociones(){
        String consultaSQL="SELECT p FROM Pocion p";
        Query query=entityManager.createQuery(consultaSQL);
        List<Pocion> pociones=query.getResultList();
        return  pociones;
    }

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
