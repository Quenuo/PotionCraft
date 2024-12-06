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



    public Long getCantidad() {
        return cantidad;
    }

    public Pocion getPocion() {
        return pocion;
    }

    public double venderPociones(){
        return pocion.obtenerCostoIngrediente()*1.1;

    }



    @Override
    public String toString(){
        return "-Poción: "+pocion.getNombre()+" - Cantidad: "+cantidad;
    }
}
