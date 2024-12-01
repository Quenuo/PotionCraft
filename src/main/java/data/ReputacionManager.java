package data;
/*TODO el fichero aleatorio tendra el nombre de reputacion.dat en resources/utils puesto que puede existir
 * existir o no se encarga de la creacion esta clase
 * TODO preguntar si el reputacion manager debe de crear la carpeta donde esta el fichero en caso de que no existiera
 *  o por el contrario ya esta crado de antemano
 */

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
        try {

            randomAccessFile=new RandomAccessFile(rutaFicheroReputacion,"rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static ReputacionManager getInstance(){
        if(reputacionManager==null){
            reputacionManager=new ReputacionManager();
        }
        return reputacionManager;
    }


    //me peta al borrar ya que se necesita cerrarse para pòder borrar
    //por lo que solo usare este metodo al crear nueva partida cuando

    public void abrirHuerto(){
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void cerrarFicheroReputacion(){
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        return Files.exists(path);
    }




}
