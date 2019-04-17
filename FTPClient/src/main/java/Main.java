/**
 * Created by Pan on 2019/4/17.
 */
public class Main {
    public static void main(String[] args){
        FTPClient client = new FTPClient();
        String host = "192.168.2.7";
        int port = 21;
        String username = "";
        String password = "";
        try{
            client.connect(host,port,username,password);
            client.upload("C:\\Users\\Pan\\Desktop\\hello.txt","test\\hello.txt");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            client.close();
        }

    }
}
