package model;

import java.util.Date;
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
public class Cliente {
    
    private int idCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private int idade;
    private Date dataNasc;
    private int status;
    
}
