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
public class Laboratorio {
    
    private int idLaboratorio;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private  int status; 
    
}