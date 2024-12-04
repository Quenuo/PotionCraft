package service;

import model.InventarioIngrediente;
import model.InventarioPocion;
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

    public void cerrarConexion(){
        globalRepository.cerrarConexion();
    }
}
