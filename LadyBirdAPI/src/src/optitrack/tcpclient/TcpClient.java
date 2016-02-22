package src.optitrack.tcpclient;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.*;
import java.nio.charset.*;

public class TcpClient {

    public SocketChannel client = null;
    public InetSocketAddress isa = null;
    public RecvThread rt = null;
    private OptiTrackClient oTClient = null;
    
    public TcpClient(OptiTrackClient oTClient) {
    	this.oTClient = oTClient;
    }

    public void makeConnection() {
        int result = 0;
        try {
            client = SocketChannel.open();
            isa = new InetSocketAddress("239.255.42.99", 1511);
            client.connect(isa);
            client.configureBlocking(false);
            receiveMessage();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public int sendMessage() {
//        System.out.println("Inside SendMessage");
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String msg = null;
//        ByteBuffer bytebuf = ByteBuffer.allocate(1024);
//        int nBytes = 0;
//        try {
//            msg = in.readLine();
//            System.out.println("msg is " + msg);
//            bytebuf = ByteBuffer.wrap(msg.getBytes());
//            nBytes = client.write(bytebuf);
//            System.out.println("nBytes is " + nBytes);
//            if (msg.equals("quit") || msg.equals("shutdown")) {
//                System.out.println("time to stop the client");
//                interruptThread();
//                try {
//                    Thread.sleep(5000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                client.close();
//                return -1;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Wrote " + nBytes + " bytes to the server");
//        return nBytes;
//    }

    public void receiveMessage() {
        rt = new RecvThread("Receive THread", client);
        rt.start();

    }

    public void interruptThread() {
        rt.val = false;
    }

    public class RecvThread extends Thread {

        public SocketChannel sc = null;
        public boolean val = true;

        public RecvThread(String str, SocketChannel client) {
            super(str);
            sc = client;
        }

        public void run() {

            System.out.println("Inside receivemsg");
            int nBytes = 0;
            ByteBuffer buf = ByteBuffer.allocate(2048);
            try {
                while (val) {
                    while ((nBytes = client.read(buf)) > 0) {
                        buf.flip();
                        oTClient.handleIncommingMessage(buf);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();

            }


        }
    }
}
