package servidor;

import java.io.*;
import java.net.*;

public class ServidorArquivo {
    public static void main(String[] args) {
        int porta = 12345; // Porta em que o servidor estará ouvindo

        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado e ouvindo na porta " + porta);

            while (true) {
                try (Socket clienteSocket = servidorSocket.accept();
                    DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
                		
                    FileOutputStream fos = new FileOutputStream("arquivo_recebido.txt")) {

                    System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());

                    // Lê o tamanho do arquivo
                    long tamanhoArquivo = dis.readLong();
                    byte[] buffer = new byte[4096];
                    int bytesLidos;
                    long bytesRecebidos = 0;

                    // Lê o arquivo do input stream e escreve no arquivo de saída
                    while (bytesRecebidos < tamanhoArquivo) {
                        bytesLidos = dis.read(buffer, 0, buffer.length);
                        
                        fos.write(buffer, 0, bytesLidos);
                        
                        bytesRecebidos += bytesLidos;
                        
                    }

                    System.out.println("Arquivo recebido com sucesso.");
                    
                } catch (IOException e) {
                    System.out.println("Erro ao comunicar com o cliente: " + e.getMessage());
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
            
        }
    }
}
