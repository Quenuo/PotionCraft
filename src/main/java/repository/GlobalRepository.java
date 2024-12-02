package repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.InventarioIngrediente;
import model.InventarioPocion;

import java.util.List;


public class GlobalRepository {
    private static GlobalRepository globalRepository;
    private  EntityManagerFactory entityManagerFactory;
    private  EntityManager entityManager;




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


    public List<InventarioPocion> obtenerPocionesUsuario(){
        String consultaSQL="SELECT i FROM InventarioPocion i";
        Query query=entityManager.createQuery(consultaSQL);
        List<InventarioPocion> inventarioPociones=query.getResultList();
        return inventarioPociones;
    }

    public List<InventarioIngrediente> obtenerIngredientesUsuario(){
        String consultaSQL="SELECT i FROM InventarioIngrediente i";
        Query query=entityManager.createQuery(consultaSQL);
        List<InventarioIngrediente> inventarioIngredientes=query.getResultList();
        return inventarioIngredientes;
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
