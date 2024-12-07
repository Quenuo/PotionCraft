package service;

import model.Comerciante;
import model.InventarioIngrediente;
import model.InventarioPocion;
import model.Pocion;
import repository.GlobalRepository;

import java.util.List;
import java.util.Random;

public class GlobalService {
    private GlobalRepository globalRepository;

    public GlobalService(){
        globalRepository=GlobalRepository.getInstance();
    }

    public void eliminarGuardado(){
        globalRepository.eliminarDatosGuardados();
    }

    public List<InventarioIngrediente> obtenerInventarioIngredientes(){
        return globalRepository.obtenerInventarioIngredientes();
    }

    public List<InventarioPocion> obtenerInventarioPociones(){
        return globalRepository.obtenerInventarioPociones();
    }

    public List<Comerciante> obtenerComerciantes(){
        return globalRepository.obtenerComerciantes();
    }

    public List<Pocion> obtenerPociones(){
        return  globalRepository.obtenerPociones();
    }

    public Comerciante obtenerComercianteAleatorio(){
            return globalRepository.obtenerComercianteAleatorio();
    }

    public void comprarIngredientes(InventarioIngrediente ingrediente){
        globalRepository.comprarIngredientes(ingrediente);
    }

    public void venderPociones(){
        globalRepository.venderPociones();
    }

    public void cerrarConexion(){
        globalRepository.cerrarConexion();
    }
}
