import data.ReputacionManager;

public class Main {
    public static void main(String[] args) {
        ReputacionManager reputacionManager=ReputacionManager.getInstance();
//        int reputacionInicial= reputacionManager.obtenerReputacionInicial();
//        System.out.println("La reputacion inicial es "+reputacionInicial );
        reputacionManager.guardarReputacion(10);
        int reputacion=20;
        reputacionManager.guardarReputacion(reputacion);
        int reputacion2=reputacionManager.obtenerReputacionInicial();
        System.out.println(reputacion2);
        reputacionManager.borrarFicheroReputacion();
        reputacionManager.abrirHuerto();
        reputacionManager.guardarReputacion(40);

    }
}
