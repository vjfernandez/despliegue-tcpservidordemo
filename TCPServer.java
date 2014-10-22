
/**
 * Un servidor TCP Sencillo. Solo una demo.
 * 
 */
import java.io.*;
import java.net.*;

class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         String fraseDelCliente;
         String fraseDelClienteMays;
         ServerSocket socketServidor = new ServerSocket(6789);
         Socket conexion;

        do
         {
            System.out.println("Esperando cliente...");
            //esperar a un cliente
            conexion = socketServidor.accept();
            System.out.println("Aceptado transporte desde "+conexion.getInetAddress()+":"+
                conexion.getPort());
            //Un stream para leer
            BufferedReader desdeCliente =
               new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            //otro para escribir
            DataOutputStream haciaCliente = new DataOutputStream(conexion.getOutputStream());
            //leer cadena entera hasta cambio de línea
            fraseDelCliente = desdeCliente.readLine();
            System.out.println("Recibido: " + fraseDelCliente);            
            //La pasamos a mayúsculas y la reenviamos al cliente
            fraseDelClienteMays = fraseDelCliente.toUpperCase() + '\n';
            haciaCliente.writeBytes(fraseDelClienteMays);
         } while (!fraseDelCliente.equals("fin"));
         
         conexion.close();
         System.out.println("Servidor terminado");
      }
}