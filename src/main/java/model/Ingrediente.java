package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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


    public String getNombre() {
        return nombre;
    }

    public TipoIngrediente getTipoIngrediente() {
        return tipoIngrediente;
    }









}
