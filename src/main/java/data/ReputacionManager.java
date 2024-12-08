package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//esta clase es la que se encarga de gestionar el fichero de acceso aleatorio
public class ReputacionManager {
    private static ReputacionManager reputacionManager;
    private final static String rutaFicheroReputacion= "src"+File.separator+"main"+File.separator+"resources"+File.separator+"utils"+File.separator+"reputacion.dat";
    private final static  Path path=Paths.get(rutaFicheroReputacion);
    private static RandomAccessFile randomAccessFile;




    private ReputacionManager(){


    }
    //uso el patron Singlenton para garantizar una única instancia de la clase y la clase Random Acces File
    public static ReputacionManager getInstance(){
        if(reputacionManager==null){
            reputacionManager=new ReputacionManager();
        }
        return reputacionManager;
    }


    //para iniciar la conexion con el fichero de acceso aleatorio , al no iniciarla
    //en el constructor
    private  void abrirGuardado(){
        if (randomAccessFile==null || !randomAccessFile.getChannel().isOpen()){
            try {
                randomAccessFile=new RandomAccessFile(rutaFicheroReputacion,"rw");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //para borrar el fichero de
    public void borrarFicheroReputacion(){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //para cerrar la conexion con el fichero .dat
    public void cerrarFicheroReputacion(){
        if(randomAccessFile!=null){
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    //En caso de cargar la partida con este metodo se acerde a la reputacion, y se guarda en una variable estatica
    public int obtenerReputacionInicial(){
        int reputacion;
        try {
            abrirGuardado();
            randomAccessFile.seek(0);
            reputacion=randomAccessFile.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reputacion;
    }

    //para guardar la reputacion
    public void guardarReputacion(int reputacion){
        try {
            abrirGuardado();
            randomAccessFile.seek(0);
            randomAccessFile.writeInt(reputacion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean existeFicheroReputacion(){
        //el problema es que siempre existira el fichero al tener que la instancia de reputación manager
        //por eso he decidido no meter el new Random acces en el constructor privado(aunque otra alternativa era hacer este metodo estático)
        return Files.exists(path);
    }

}
