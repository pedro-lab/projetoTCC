package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private Date dataVenda;
    private Date dataEntrega;
    private Date dataVencimento;
    private String statusEntrega;
    private Date dataOS;
    private Usuario usuario;
    private Laboratorio laboratorio;
    private Lente lente;
    private Cliente cliente;
    private int status;

    //Esse atribruto nao vai para o banco de dados
    public static Date dataAtual = new Date();
    private String statusVencimento;
    private Date vencimento;

    public String verificaVencimento(Date data) {
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String mensagem = "";
        String data1 = df.format(data);

        Calendar calendarioDataAtual = DataParaCalendario(dataAtual, true);

        try {
            vencimento = df.parse(data1);
        } catch (ParseException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        Calendar calendarioDataVencimento = DataParaCalendario(vencimento, true);


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
    
    public ArrayList<String> meses (){
        
        ArrayList<String> meses = new ArrayList<>();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");
        
        return meses;
    }
}
