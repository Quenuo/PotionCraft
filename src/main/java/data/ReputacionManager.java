package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReputacionManager {
    private static ReputacionManager reputacionManager;
    private final static String rutaFicheroReputacion= "src"+File.separator+"main"+File.separator+"resources"+File.separator+"utils"+File.separator+"reputacion.dat";
    private final static  Path path=Paths.get(rutaFicheroReputacion);
    private static RandomAccessFile randomAccessFile;




    private ReputacionManager(){


    }

    public static ReputacionManager getInstance(){
        if(reputacionManager==null){
            reputacionManager=new ReputacionManager();
        }
        return reputacionManager;
    }


    //me peta al borrar ya que se necesita cerrarse para pòder borrar
    //por lo que solo usare este metodo al crear nueva partida cuando

    public void abrirGuardado(){
        if (randomAccessFile==null || !randomAccessFile.getChannel().isOpen()){
            try {
                randomAccessFile=new RandomAccessFile(rutaFicheroReputacion,"rw");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void borrarFicheroReputacion(){
        cerrarFicheroReputacion();
        try {
            Files.deleteIfExists(path);
            //despues de borrar el guradado vuelvo a abrir el f
            abrirGuardado();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void cerrarFicheroReputacion(){
        if(randomAccessFile!=null){
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public int obtenerReputacionInicial(){
        int reputacion;
        try {
            randomAccessFile.seek(0);
            reputacion=randomAccessFile.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reputacion;
    }

    public void guardarReputacion(int reputacion){
        try {
            randomAccessFile.seek(0);
            randomAccessFile.writeInt(reputacion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean existeFicheroReputacion(){
        //el problema es que siempre existira el fichero al tener que la instancia de reputacion manager
        //por lo que necesito verificar su tamaño(o que readInt se distinto a nulo)
        return Files.exists(path);
    }

}
