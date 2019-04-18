import util.FTPClient;

/**
 * Created by Pan on 2019/4/17.
 */
public class Main {
    public static void main(String[] args){
        FTPClient client = new FTPClient();
        String host = "192.168.2.7";
        int port = 21;
        String username = "810650217@qq.com";
        String password = "pyy19980118";
        try{
            client.connect(host,port,username,password);
//            client.upload("C:\\Users\\Pan\\Desktop\\hello.txt","test\\hello.txt");
            client.list("/");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            client.close();
        }

    }
}
