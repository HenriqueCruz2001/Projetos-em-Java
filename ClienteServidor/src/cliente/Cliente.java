package cliente;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String servidorNome = "localhost"; // Nome do servidor (localhost para servidor local)
        
        int porta = 12345; // Porta em que o servidor está ouvindo

        try (Socket socket = new Socket(servidorNome, porta);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             
        	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado no servidor em " + servidorNome + " e na porta " + porta);

            String userInput;
            
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                
                System.out.println("Resposta do servidor: " + in.readLine());
                
            }
            
        } catch (UnknownHostException e) {
            System.out.println("Host desconhecido: " + servidorNome);
            
        } catch (IOException e) {
            System.out.println("Erro ao comunicar com o servidor: " + e.getMessage());
            
        }
    }
}
