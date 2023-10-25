package model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lente {
    
    private int idLente;
    private String nome;
    private String modelo;
    private Double preco;
    private String fabricante;
    private int status;
    
}
