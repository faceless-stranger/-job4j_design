package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private  static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.\r\n\r\n".getBytes());
                    String value = in.readLine().split("\\?")[1].split(" ")[0];
                    if (value.equals("msg=Exit")) {
                        out.write("End job".getBytes());
                        break;
                    }
                    if (value.equals("msg=Hello")) {
                        out.write("Hello".getBytes());
                    }
                    if (!value.isEmpty() && !value.equals("msg=Hello")) {
                        out.write(value.split("=")[1].getBytes());
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Exception in log example", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}