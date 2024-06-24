package servidor;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int porta = 12345; // Porta em que o servidor estará ouvindo

        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta " + porta);

            while (true) {
                try (Socket clienteSocket = servidorSocket.accept();
                    PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))) {

                    System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());

                    String inputLine;
                    
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Recebido do cliente: " + inputLine);
                        
                        out.println("Mensagem recebida: " + inputLine);
                        
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao comunicar com o cliente: " + e.getMessage());
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
            
        }
    }
}
