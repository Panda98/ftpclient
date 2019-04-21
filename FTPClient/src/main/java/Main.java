import util.FTPClient;

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
            String username = "810650217@qq.com";
            String password = "pyy19980118";
            try {
                client.connect(host, port, username, password);
                client2.connect(host, port, username, password);
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
//            client.upload("C:\\Users\\Pan\\Desktop\\hello.txt","test\\hello.txt");
//            client.list("/");
                client.download(localpath,serverpath,c);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                client.close();
            }
        }
    }
    public static void main(String[] args){
        Thread thread = new Thread(new Child());
        thread.start();
        try{
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        Thread thread1 = new Thread(new Child2("C:\\Users\\Pan\\Desktop\\hello.txt","test\\hello.txt",client,c));
        thread1.start();

        Thread thread2 = new Thread(new Child2("C:\\Users\\Pan\\Desktop\\2.txt","test\\2.txt",client2,c2));
        thread2.start();


        while (thread1.isAlive() || thread2.isAlive()){
            double i = client.getProgress("C:\\Users\\Pan\\Desktop\\hello.txt",c);
            double j = client2.getProgress("C:\\Users\\Pan\\Desktop\\2.txt",c2);
            System.out.println("hello.txt: "+i);
            System.out.println("2.txt: "+j);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
