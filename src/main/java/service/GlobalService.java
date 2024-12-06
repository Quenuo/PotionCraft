package service;

import model.Comerciante;
import model.InventarioIngrediente;
import model.InventarioPocion;
import model.Pocion;
import repository.GlobalRepository;

import java.util.List;

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

    public void cerrarConexion(){
        globalRepository.cerrarConexion();
    }
}
