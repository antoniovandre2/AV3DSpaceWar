/*
 * Proprietário: Antonio Vandré Pedrosa Furtunato Gomes.
 * 
 * Launcher do Game AVSpaceWar.
 * 
 * Dependências: AntonioVandre.
 * 
 * Sugestões ou comunicar erros: "a.vandre.g@gmail.com".
 * 
 * Licença de uso: Atribuição-NãoComercial-CompartilhaIgual (CC BY-NC-SA).
 * 
 * Última atualização: 18-09-2025.
 */

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.lang.ProcessBuilder;

public class AV3DSpaceWarLauncher
    {
    public static String VersaoLauncher = "15-11-2023";

    public static String URLAVBlockVersao = "https://github.com/antoniovandre2/AV3DSpaceWar/raw/main/AV3DSpaceWarVersao.txt";

    public static String ArquivoAVBlockVersao = "AV3DSpaceWarVersao.txt";

    public static String URLAV3DSpaceWar = "https://github.com/antoniovandre2/AV3DSpaceWar/raw/main/AV3DSpaceWar.jar";

    public static String ArquivoAV3DSpaceWar = "AV3DSpaceWar.jar";

    public static String MensagemErroAtualizar = "Erro ao atualizar o game AV3DSpaceWar.";

    public static String MensagemErroExecutar = "Erro ao executar o game AV3DSpaceWar.";

    public static void main(String[] args)
        {
        int FlagSucessoDownloadNet = 1;

        try
            {
            downloadUsingStream(URLAVBlockVersao, ArquivoAVBlockVersao + ".tmp");
            } catch (IOException e) {FlagSucessoDownloadNet = 0;}

        if (FlagSucessoDownloadNet == 1)
            {
            File file = new File(ArquivoAVBlockVersao);
            int FlagSucessoVersaoLocal = 1;
            String VersaoLocal = "";

            try
                {
                BufferedReader br = new BufferedReader(new FileReader(file));
                VersaoLocal = br.readLine();
                } catch (IOException e) {FlagSucessoVersaoLocal = 0;}

            File fileNet = new File(ArquivoAVBlockVersao + ".tmp");
            int FlagSucessoVersaoNet = 1;
            String VersaoNet = "";

            try
                {
                BufferedReader brNet = new BufferedReader(new FileReader(fileNet));
                VersaoNet = brNet.readLine();
                } catch (IOException e) {FlagSucessoVersaoNet = 0;}

            if ((FlagSucessoVersaoLocal == 1) && (FlagSucessoVersaoNet == 1))
                {
                if (! (VersaoNet.equals(VersaoLocal)))
                    try
                        {
                        downloadUsingStream(URLAV3DSpaceWar, ArquivoAV3DSpaceWar);
                        downloadUsingStream(URLAVBlockVersao, ArquivoAVBlockVersao);
                        } catch (IOException e) {}
                }
            else
                try
                    {
                    downloadUsingStream(URLAV3DSpaceWar, ArquivoAV3DSpaceWar);
                    downloadUsingStream(URLAVBlockVersao, ArquivoAVBlockVersao);
                    } catch (IOException e) {}
            }

        try
            {
            String ArgumentoEstatisticas = "";

            if (args.length != 0) ArgumentoEstatisticas = args[0];

            ProcessBuilder pb = new ProcessBuilder("java", "-jar", ArquivoAV3DSpaceWar, ArgumentoEstatisticas);
            Process p = pb.start();
            } catch (IOException e) {System.out.println(MensagemErroExecutar);}
        }

    private static void downloadUsingStream(String urlStr, String file) throws IOException
        {
		try
			{
			URL url = new URI(urlStr).toURL();
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			FileOutputStream fis = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int count=0;
			while((count = bis.read(buffer,0,1024)) != -1)
			{
				fis.write(buffer, 0, count);
			}
			fis.close();
			bis.close();
			} catch (URISyntaxException e) {}
        }
}
