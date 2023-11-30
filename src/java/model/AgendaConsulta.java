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
public class AgendaConsulta {
    
    private int idConsulta;
    private Date diaHora;
    private String observacoes;
    private String confirmacao;
    private Cliente cliente;
            
}
