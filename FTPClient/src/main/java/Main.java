import util.FTPClient;

import java.util.HashMap;

/**
 * Created by Pan on 2019/4/17.
 */
public class Main {
    static FTPClient client;
    static FTPClient client2;
    static char[] c = new char[0];
    static char[] c2 = new char[0];
    private static class Child implements Runnable{
        @Override
        public void run() {
            connect();

        }
        private void connect(){
            client = new FTPClient();
            client2 = new FTPClient();
            String host = "192.168.2.7";
            int port = 21;
            String username = "Experiment";
            String password = "123456";
            try {
                client.connect(host, port, username, password);
//                client2.connect(host, port, username, password);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static class Child2 implements Runnable{
        String localpath;
        String serverpath;
        FTPClient client;
        char[] c;

        public Child2(String localpath, String serverpath,FTPClient client,char[] c) {
            this.localpath = localpath;
            this.serverpath = serverpath;
            this.client = client;
            this.c = c;
        }

        @Override
        public void run() {
            try{
            client.upload("C:\\Users\\Pan\\Desktop\\chapter3.pdf","",c);
//            client.list("/");
//                client.download(localpath,serverpath,c);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                client.close();
            }
        }
    }
    public static void main(String[] args){
        test();

    }
    public static void test(){
        String host = "192.168.2.7";
        int port = 21;
        String username = "Experiment";
        String password = "123456";

        client = new FTPClient();
        try{
            client.connect(host,port,username,password);
            client.upload("C:\\Users\\Pan\\Desktop\\chapter3.pdf","chapter3.pdf",c);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void thread(){
        Thread thread = new Thread(new Child());
        thread.start();
        try{
            thread.join();
            HashMap hashMap = client.list("/test/1/新建文件夹");
            System.out.println(client.getDirSize("/test"));
            System.out.println("LIST: "+hashMap.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        String path = "";
        try {
            path = new String("test\\2.txt");
        }catch (Exception e){
            e.printStackTrace();
        }

        Thread thread1 = new Thread(new Child2("C:\\Users\\Pan\\Desktop\\1.txt",path,client,c));
        thread1.start();

//        Thread thread2 = new Thread(new Child2("C:\\Users\\Pan\\Desktop\\2.txt","test\\2.txt",client2,c2));
//        thread2.start();


        while (thread1.isAlive()){
            double i = client.getProgress(c);
//            double j = client2.getProgress("C:\\Users\\Pan\\Desktop\\2.txt",c2);
            System.out.println("1.txt: "+i);
//            System.out.println("2.txt: "+j);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
