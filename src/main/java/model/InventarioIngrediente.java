package model;

import jakarta.persistence.*;

@Entity
@Table
public class InventarioIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id",nullable = false,unique = true )
    private Ingrediente ingrediente;

    public InventarioIngrediente(){

    }

    public InventarioIngrediente(Long cantidad,Ingrediente ingrediente){
        this.cantidad=cantidad;
        this.ingrediente=ingrediente;
    }


    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre(){//me suena feo ingrediente.getIngrediente.getNombre(),
        //creando este metodo soluciono ese problema
        return ingrediente.getNombre();
    }

    public Long getIdIngrediente(){
        return ingrediente.getId();
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public Double calcularGasto(){
        return cantidad*ingrediente.getPrecioCompra();
    }

    @Override
    public String toString(){
        return "-Ingrediente: "+ingrediente.getNombre()+" (Tipo: "+ingrediente.getTipoIngrediente()+") - Cantidad: "+cantidad;
    }




}
