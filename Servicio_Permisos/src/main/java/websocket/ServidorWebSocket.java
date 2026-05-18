package websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ServidorWebSocket extends WebSocketServer {

    private WebSocket pacienteConectado = null;
    private CountDownLatch esperandoRespuesta = null;
    private boolean pacienteAprobó = false;

    public ServidorWebSocket(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        pacienteConectado = conn;
        System.out.println("App del paciente conectada via WebSocket.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        pacienteConectado = null;
        System.out.println("App del paciente desconectada.");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Respuesta del paciente: " + message);
        if (message.equals("APROBADO")) {
            pacienteAprobó = true;
        } else {
            pacienteAprobó = false;
        }
        if (esperandoRespuesta != null) {
            esperandoRespuesta.countDown();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Error WebSocket: " + ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("--- Servidor WebSocket iniciado ---");
    }

    public boolean notificarYEsperarAprobacion(String cedulaMedico) {
        if (pacienteConectado == null) {
            System.err.println("No hay paciente conectado via WebSocket.");
            return false;
        }

        String notificacion = "Solicitud:" + cedulaMedico;
        pacienteConectado.send(notificacion);
        System.out.println("Notificación enviada al paciente: " + notificacion);

        esperandoRespuesta = new CountDownLatch(1);
        try {
            boolean respondioATiempo = esperandoRespuesta.await(30, TimeUnit.SECONDS);
            if (!respondioATiempo) {
                System.err.println("El paciente no respondió a tiempo.");
                return false;
            }
        } catch (InterruptedException e) {
            System.err.println("Espera interrumpida: " + e.getMessage());
            return false;
        }

        return pacienteAprobó;
    }
}