package adandroid.v2.appnovovipclientelegal.api;

import java.util.Calendar;

public class AppUtil {

    public static final int TIME_SPLASH = 5 * 1000;
    public static final String PREF_APP = "app_clienteLegal_vip_pref";
   public static final String LOG_APP = "CLIENTE_LOG";

    public static String getDataAtual() {
        String dia, mes, ano;

        String dataAtual = "00/00/0000";
        try {


            Calendar calendar = Calendar.getInstance();
            dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            ano = String.valueOf(calendar.get(Calendar.YEAR));

            dia = (Calendar.DAY_OF_MONTH < 10) ? "0" + dia : dia;
            int mesAtual = (Calendar.MONTH) + 1;
            mes = (Calendar.MONTH > 10) ? "0" + mes : mes;
            dataAtual = dia + "/" + mes + "/" + ano;

            return dataAtual;
        } catch (Exception e) {

        }
        return dataAtual;
    }

    public static String getHoraAtual() {
        String hora, minuto, segundo;

        String horaAtual = "00:00:00";
        try {

            Calendar calendar = Calendar.getInstance();
            int ihora = calendar.get(Calendar.HOUR_OF_DAY);
            int iminuto = calendar.get(Calendar.MINUTE);
            int isegundo = calendar.get(Calendar.SECOND);
            hora = (ihora <= 10) ? "0" + ihora : Integer.toString(ihora);
            minuto = (iminuto <= 10) ? "0" + iminuto : Integer.toString(iminuto);
            segundo = (isegundo <= 10) ? "0" + isegundo : Integer.toString(isegundo);

            horaAtual = hora + ":" + minuto + ":" + segundo;


        } catch (Exception e) {

        }
        return horaAtual;


    }
}