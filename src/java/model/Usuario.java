package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
    
    private int idUsuario;
    private String nome;
    private String login;
    private String senha;
    private int status;
    private Perfil perfil;
    
}
