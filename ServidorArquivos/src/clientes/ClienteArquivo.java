package clientes;

import java.io.*;
import java.net.*;

public class ClienteArquivo {
    public static void main(String[] args) {
        String servidorNome = "localhost"; // Nome do servidor (localhost para servidor local)
        
        int porta = 12345; // Porta em que o servidor está ouvindo
        
        String caminhoArquivo = "caminho/do/seu/arquivo.txt"; // Caminho do arquivo que será enviado

        try (Socket socket = new Socket(servidorNome, porta);
            FileInputStream fis = new FileInputStream(caminhoArquivo);
        		
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Conectado ao servidor em " + servidorNome + " na porta " + porta);

            // Obtém o tamanho do arquivo
            File arquivo = new File(caminhoArquivo);
            
            long tamanhoArquivo = arquivo.length();

            // Envia o tamanho do arquivo para o servidor
            dos.writeLong(tamanhoArquivo);
            dos.flush();

            // Lê o arquivo e envia os dados para o servidor
            byte[] buffer = new byte[4096];
            int bytesLidos;
            
            while ((bytesLidos = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesLidos);
                
            }

            System.out.println("Arquivo enviado com sucesso.");
            
        } catch (UnknownHostException e) {
            System.out.println("Host desconhecido: " + servidorNome);
            
        } catch (IOException e) {
            System.out.println("Erro ao comunicar com o servidor: " + e.getMessage());
            
        }
    }
}
