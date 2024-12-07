package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class InventarioPocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pocion_id",nullable = false,unique = true)
    private Pocion pocion;

    public InventarioPocion(){

    }

    public InventarioPocion(Pocion pocion){
        this.pocion=pocion;
        //como solo se creara una aunauq se tengan ingredientes necesarios para crear más establezco la cantidad a una
        cantidad=1L;
    }



    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Pocion getPocion() {
        return pocion;
    }

    public double venderPociones(){
        return pocion.obtenerCostoIngrediente()*1.1;

    }

    public Long getIdPocion(){
        return  pocion.getId();
    }


    public List<Ingrediente> getIngredientes(){
        return pocion.getIngredientes();
    }



    @Override
    public String toString(){
        return "-Poción: "+pocion.getNombre()+" - Cantidad: "+cantidad;
    }
}
