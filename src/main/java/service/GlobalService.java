package service;

import repository.GlobalRepository;

public class GlobalService {
    GlobalRepository globalRepository;

    public GlobalService(){
        globalRepository=GlobalRepository.getInstance();
    }

    public void eliminarGuardado(){
        globalRepository.eliminarDatosGuardados();
    }
}
