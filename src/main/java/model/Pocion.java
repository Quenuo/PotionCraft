package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Pocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,length = 100,unique = true)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "Ingredientes_Pociones",
            joinColumns = @JoinColumn(name = "pocion_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes;

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(){

        return "Poción: "+nombre+"\n"
                +"Ingredientes necesarios: \n"
                +mostrarIngredientesNecesarios()
                +"\n";
    }

    private String mostrarIngredientesNecesarios(){
        StringBuilder listaIngredientes= new StringBuilder();
        for(int i=0; i<ingredientes.size();i++){
            listaIngredientes.append("- ").append(ingredientes.get(i).getNombre()).append(" (Tipo ").append(ingredientes.get(i).getTipoIngrediente()).append(")\n");

        }
        return listaIngredientes.toString();
    }


}
