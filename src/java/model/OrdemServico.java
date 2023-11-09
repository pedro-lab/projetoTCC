package model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdemServico {
    
    private int idOs;
    private Date dataSolicitacao;
    private Date dataEntrega;
    private Date vencimento;
    private String statusEntrega;
    private Usuario usuario;
    private Laboratorio laboratorio;
    private Lente lente;
    private Cliente cliente;
    private int status;
    
}
