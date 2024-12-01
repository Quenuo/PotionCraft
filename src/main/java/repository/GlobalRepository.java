package repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class GlobalRepository {
    private static GlobalRepository globalRepository;
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;




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



    public void cerrarConexion(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
