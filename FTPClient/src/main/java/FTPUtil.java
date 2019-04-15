import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created by Pan on 2019/4/15.
 */
public class FTPUtil {

    public static FTPClient getFTPClient(String host,String username,String password,int port){
        FTPClient client = new FTPClient();
        try{
            //连接ftp服务器
            client.connect(host,port);
            //登录ftp服务器
            client.login(username,password);
            //中文支持
            client.setControlEncoding("UTF-8");

            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                System.out.print("未连接到FTP，用户名或密码错误。");
                client.disconnect();
            } else {
                System.out.print("FTP连接成功。");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return client;
    }

    public static void main(String[] args){
        String host = "192.168.2.7";
        String username = "";
        String password = "";
        int port = 21;
        String path = "/test";
        String filename = "hello.txt";
        try{
//            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Pan\\Desktop\\"+filename));
//            FTPUtil.uploadFile(host,username,password,port,path,filename,inputStream);
            FTPUtil.downloadFile(host,username,password,port,path,"D:\\",filename,"h.txt");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void downloadFile(String host,String username,String password,int port,String path,String localPath,String filename,String targetFilename){
        FTPClient client = null;
        try{
            client = getFTPClient(host,username,password,port);
            client.changeWorkingDirectory(path);
            String f_ame = new String(filename.getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);	//编码文件格式,解决中文文件名

            File localFile = new File(localPath + File.separatorChar + targetFilename);
            OutputStream os = new FileOutputStream(localFile);
            client.retrieveFile(f_ame, os);
            os.close();
            client.logout();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean uploadFile(String host, String username, String password, int port, String basePath, String filename, InputStream inputStream){
        boolean result = false;
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(host, username, password, port);
            ftpClient.changeWorkingDirectory(basePath);

            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return result;
            }

            filename = new String(filename.getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);//编码文件名，支持中文文件名
            //上传文件
            if (!ftpClient.storeFile(filename, inputStream)) {
                return result;
            }
            inputStream.close();
            ftpClient.logout();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
