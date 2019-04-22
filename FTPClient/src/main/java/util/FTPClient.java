package util;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Created by Pan on 2019/4/17.
 */
public class FTPClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private BufferedOutputStream outputStream;

    private Socket dataSocket;
    private String dataHost;
    private int dataPort;

    private double progress;


    /**
     *  连接ftp服务器
     * @param host 服务器ip
     * @param port 端口号
     * @param username 登录用户名
     * @param password 登录密码
     * @throws Exception 连接失败
     */
    public void connect(String host,int port,String username,String password) throws Exception{
        if(socket!= null)
            throw new Exception("连接已经建立！");

        socket = new Socket(host,port);
        reader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        String msg = reader.readLine();
        System.out.println(msg);

        sendMsg("USER "+username);
        String response = reader.readLine();
        System.out.println(response);

        if(!response.startsWith("331 ")){
            socket = null;
            throw new Exception("用户名不正确！");
        }
        sendMsg("PASS "+password);
        response = reader.readLine();
        System.out.println(response);
        if(!response.startsWith("230 ")){
            socket = null;
            throw new Exception("密码错误！");
        }

        System.out.println("connect succeed. ");



    }


    /**
     *  列出目录下文件及文件夹
     * @param parentPath 目录名，若为根目录，则传入"/"
     * @return key-文件名/文件夹名，value-类型（文件夹为DIR，其余为FILE）
     * @throws Exception 读取错误
     */
    public LinkedHashMap<String,String> list(String parentPath) throws Exception{
        LinkedHashMap<String,String> contents = new LinkedHashMap<String, String>();
        calDataHostPort();

        String response;

//        if(!parentPath.equals("/")){
//            sendMsg("CWD "+parentPath);
//
//            if(!(response = reader.readLine()).startsWith("250 "))
//                throw new Exception("没有该文件夹");
//            System.out.println(response);
//        }
//        sendMsg("LIST");
        sendMsg("NLST "+parentPath);
        response = reader.readLine();
        System.out.println(response);

        dataSocket = new Socket(dataHost,dataPort);
        response = reader.readLine();
        System.out.println(response);

        BufferedReader dataReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream(),"GB2312"));
        String line;
        while ((line = dataReader.readLine())!= null){
            int index = line.lastIndexOf(' ');
            String s = new String(line.substring(index+1,line.length()).getBytes());
            String type = "FILE";
            if(line.contains("DIR"))
                type = "DIR";
            contents.put(s,type);
            System.out.println(line);
        }


        return contents;
    }

    /**
     * 断开FTP连接
     */
    public void close(){
        try{
            if(socket != null)
                socket.close();
            if(reader!= null)
                reader.close();
            if(writer != null)
                writer.close();
            if(outputStream != null)
                outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void calDataHostPort(){
        sendMsg("PASV");

        try{
            String response = reader.readLine();
            System.out.println(response);
            if (!response.startsWith("227 ")) {
                System.out.println("error");
            }
            int opening = response.indexOf('(');
            int closing = response.indexOf(')', opening + 1);
            if (closing > 0) {
                String datalink = response.substring(opening + 1, closing);
                StringTokenizer tokenizer = new StringTokenizer(datalink, ",");
                dataHost = tokenizer.nextToken() + "." + tokenizer.nextToken() + "." + tokenizer.nextToken() + "." + tokenizer.nextToken();
                dataPort = Integer.parseInt(tokenizer.nextToken()) * 256 + Integer.parseInt(tokenizer.nextToken());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendMsg(String msg){
        writer.write(msg+"\r\n");
        writer.flush();
    }

    /**
     * 下载文件
     * @param localpath 文件下载后的存储地址
     * @param serverpath 服务器上的地址
     * @throws Exception 读写错误
     */
    public void download(String localpath,String serverpath,Object lock) throws Exception{

        calDataHostPort();

        long length = 0;
        sendMsg("SIZE "+serverpath);
        String response = reader.readLine();
        System.out.println(response);
        length = Integer.parseInt(response.substring(4,response.length()));
        System.out.println("文件总长："+length);

        File file = new File(localpath);
        int startIndex = 0;
        if(file.exists())
            startIndex = (int) file.length();

        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
        randomAccessFile.seek(startIndex);

        sendMsg("REST "+startIndex);

        response = reader.readLine();
        System.out.println(response);

        sendMsg("RETR "+serverpath);

        response = reader.readLine();
        System.out.println(response);




        dataSocket = new Socket(dataHost, dataPort);
        BufferedInputStream inputStream = new BufferedInputStream(dataSocket.getInputStream());



        byte[] buffer = new byte[4096];
        int bytesRead = 0;


        int already = startIndex;
        while ((bytesRead = inputStream.read(buffer))!=-1){
            randomAccessFile.write(buffer,0,bytesRead);
            already += bytesRead;
            synchronized (lock) {
                progress = (double) already / length;
            }
        }
        randomAccessFile.close();
        inputStream.close();
        dataSocket.close();
    }

    static boolean isFirst = true;
    /**
     * 上传文件
     * @param localpath 文件的本地地址
     * @param serverpath 服务器上的地址
     * @throws Exception 读写错误
     */
    public void upload(String localpath,String serverpath,Object lock) throws Exception{


        calDataHostPort();

        sendMsg("SIZE "+serverpath);

        int size= 0;

        String response = reader.readLine();
        System.out.println(response);
        if(!response.startsWith("550")){
            size = Integer.parseInt(response.substring(4,response.length()));
        }

        sendMsg("APPE "+serverpath);
        response = reader.readLine();
        System.out.println(response);

        dataSocket = new Socket(dataHost,dataPort);

        File file = new File(localpath);
        long length = file.length();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
        randomAccessFile.seek(size);

        outputStream = new BufferedOutputStream(dataSocket.getOutputStream());

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        int already = size;
        while ((bytesRead = randomAccessFile.read(buffer))!=-1){
            outputStream.write(buffer,0,bytesRead);
            already += bytesRead;
            synchronized (lock) {
                progress = (double) already / length;
            }
            outputStream.flush();

        }
        randomAccessFile.close();
//        outputStream.close();
//        inputStream.close();
        dataSocket.close();
    }

    /**
     * 获得当前文件下载进度
     * @param lock
     * @return
     */



    public double getProgress(Object lock){
        double i = 0;
        synchronized (lock) {
            i = this.progress;
        }
        return i;

    }
}
