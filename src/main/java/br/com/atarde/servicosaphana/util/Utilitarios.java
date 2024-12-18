package br.com.atarde.servicosaphana.util;

import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import br.com.topsys.util.TSUtil;

public final class Utilitarios {

	private Utilitarios() {

	}

	public static String tratarString(String valor) {
		if (!TSUtil.isEmpty(valor)) {
			valor = valor.trim().replaceAll("  ", " ");
		}

		return valor;
	}

	public static Long tratarLong(Long valor) {
		if ((!TSUtil.isEmpty(valor)) && (valor.equals(Long.valueOf(0L)))) {
			valor = null;
		}

		return valor;
	}

	public static String removerAcentos(String palavra) {
		if (TSUtil.isEmpty(palavra)) {
			return null;
		}

		return Normalizer.normalize(new StringBuilder(palavra), Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static Client obterClientBuilder() {

		ClientBuilder clientBuilder = ClientBuilder.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS);

		return clientBuilder.build();

	}
	
    public static Client createClient() {
    	
        try {
        	
            // Configura um TrustManager que não valida cadeias de certificados
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
            };

            // Inicializa o contexto SSL com os TrustManagers configurados
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Configura um verificador de hostname que aceita qualquer hostname
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Cria o cliente HTTP com o contexto SSL e o verificador de hostname configurados
            return ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .hostnameVerifier(allHostsValid)
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

	public static String gerarSenha() {
		Calendar rightNow = Calendar.getInstance();

		int diaAtual = rightNow.get(5);
		int mesAtual = rightNow.get(2) + 1;
		int anoAtual = rightNow.get(1);
		int horaAtual = rightNow.get(11);
		int minutoAtual = rightNow.get(12);
		int segAtual = rightNow.get(13);
		int miliAtual = rightNow.get(14);

		String senha = anoAtual + mesAtual + diaAtual + horaAtual + minutoAtual + segAtual + miliAtual + "";

		return senha;
	}

	public static String getSituacao(Boolean flagAtivo) {
		if ((!TSUtil.isEmpty(flagAtivo)) && (flagAtivo.booleanValue())) {
			return "Ativo";
		}

		return "Inativo";
	}

	public static String getDiaMesAno(Date data) {

		if (!TSUtil.isEmpty(data)) {
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			calendar.setTime(data);
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			int mes = calendar.get(Calendar.MONTH);
			mes = mes + 1;
			String diaMesAno = "" + dia;
			if (mes < 10) {
				diaMesAno = diaMesAno + "0";
			}
			diaMesAno = diaMesAno + "_" + mes + "_" + calendar.get(Calendar.YEAR) + "_";
			return diaMesAno;

		}
		return null;
	}
	
	public static boolean isBetweenDateTime(Date dataReferencia, Date dataInicial, Date dataFinal) {

		Calendar dataInicialCalendar = Calendar.getInstance();

		dataInicialCalendar.setTime(dataInicial);
		dataInicialCalendar.set(Calendar.SECOND, 0);
		dataInicialCalendar.set(Calendar.MILLISECOND, 0);

		Calendar dataFinalCalendar = Calendar.getInstance();

		dataFinalCalendar.setTime(dataFinal);
		dataFinalCalendar.set(Calendar.SECOND, 0);
		dataFinalCalendar.set(Calendar.MILLISECOND, 0);

		Calendar dataCalendar = Calendar.getInstance();

		dataCalendar.setTime(dataReferencia);
		dataCalendar.set(Calendar.SECOND, 0);
		dataCalendar.set(Calendar.MILLISECOND, 0);

		return dataReferencia.after(dataInicialCalendar.getTime()) && dataReferencia.before(dataFinalCalendar.getTime()) || dataCalendar.equals(dataInicialCalendar) || dataCalendar.equals(dataFinalCalendar);

	}
	
	public static String getUrlAcesso(String url) {

		if (url.substring(url.length() - 1).contains("/")) {

			return url.substring(0, url.length() - 1);
		}

		return url;
	}

	public static String getTextoUTF8(String valor) {

		if (!TSUtil.isEmpty(TSUtil.tratarString(valor))) {
			valor = valor.replace("\u0000", "");
			valor = valor.replace("\u001e", "");
			return new String(valor.getBytes(), StandardCharsets.UTF_8);
		}

		return null;
	}

}