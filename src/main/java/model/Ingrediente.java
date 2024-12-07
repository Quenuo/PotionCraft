package model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoIngrediente tipoIngrediente;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Double precioCompra;

    @Column
    private Double dureza;

    @Column
    private String efectoPositivo;

    @Column
    private String efectoNegativo;

    @Column
    private Long nivelToxicidad;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecioCompra(){
        return precioCompra;
    }

    public TipoIngrediente getTipoIngrediente() {
        return tipoIngrediente;
    }

    public String getEfectoNegativo() {
        return efectoNegativo;
    }

    public String getEfectoPositivo() {
        return efectoPositivo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString(){
        return nombre+" (Tipo: "+getTipoIngrediente()+ ") - Precio:"+precioCompra;
    }

}
