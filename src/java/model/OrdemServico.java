package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    //Esse atribruto nao vai para o banco de dados
    public static Date dataAtual = new Date();
    private String statusVencimento;
    private Date dataVencimento;

    public String verificaVencimento(Date data) {
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String mensagem = "";
        String data1 = df.format(data);

        Calendar calendarioDataAtual = DataParaCalendario(dataAtual, true);

        try {
            dataVencimento = df.parse(data1);
        } catch (ParseException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        Calendar calendarioDataVencimento = DataParaCalendario(dataVencimento, true);


        System.out.println(calendarioDataAtual + " */* " + calendarioDataVencimento);

        if (calendarioDataAtual.after(calendarioDataVencimento)) {
            return "Atrasado";
        } else {
            return "No prazo";
        }
    }

    private static Calendar DataParaCalendario(Date date, boolean setTimeToZero) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        if (setTimeToZero) {
            calendario.set(Calendar.HOUR_OF_DAY, 0);
            calendario.set(Calendar.MINUTE, 0);
            calendario.set(Calendar.SECOND, 0);
            calendario.set(Calendar.MILLISECOND, 0);
        }
        return calendario;

    }
}
