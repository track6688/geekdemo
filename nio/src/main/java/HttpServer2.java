import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 * 多线程
 * </pre>
 *
 * @author guojz
 * @version 1.00.00
 * @createDate 2020/10/27 16:27
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class HttpServer2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);

        while(true) {
            final Socket socket = serverSocket.accept();

            new Thread(()-> service(socket)).start();
        }
    }

    /**
     * 请求处理
     * @param socket
     */
    private static void service(Socket socket){

        if (socket == null) {
            return;
        }

        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}

