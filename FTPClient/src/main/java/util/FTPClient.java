package util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Pan on 2019/4/17.
 */
public class FTPClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private Socket dataSocket;
    private BufferedInputStream inputStream;
    private BufferedOutputStream outputStream;
    private String dataHost;
    private int dataPort;

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
            throw new Exception("socket has already connected!");

        socket = new Socket(host,port);
        reader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        String msg = reader.readLine();
        System.out.println(msg);


        writer.println("USER "+username);
        writer.flush();
        String response = reader.readLine();
        System.out.println(response);

        if(!response.startsWith("331 ")){
            System.out.println("error");
        }
        writer.println("PASS "+password);
        writer.flush();
        response = reader.readLine();
        System.out.println(response);
        if(!response.startsWith("230 ")){
            System.out.println("error");
            return;
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

        if(!parentPath.equals("/")){
            writer.println("CWD "+parentPath);
            writer.flush();

            if(!(response = reader.readLine()).startsWith("250 "))
                throw new Exception("没有该文件夹");
            System.out.println(response);
        }

        writer.println("LIST");
        writer.flush();

        dataSocket = new Socket(dataHost,dataPort);
        response = reader.readLine();
        System.out.println(response);

        BufferedReader dataReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
        String line;
        while ((line = dataReader.readLine())!= null){
            int index = line.lastIndexOf(' ');
            String s = line.substring(index+1,line.length());
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
            socket.close();
            reader.close();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void calDataHostPort(){
        writer.println("PASV");
        writer.flush();
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


    /**
     * 下载文件
     * @param localpath 文件下载后的存储地址
     * @param serverpath 服务器上的地址
     * @throws Exception 读写错误
     */
    public void download(String localpath,String serverpath) throws Exception{
        calDataHostPort();
        writer.println("RETR " + serverpath);
        writer.flush();
        String response = reader.readLine();
        System.out.println(response);
        dataSocket = new Socket(dataHost, dataPort);
        inputStream = new BufferedInputStream(dataSocket.getInputStream());
        outputStream = new BufferedOutputStream(new FileOutputStream(new File(localpath)));
        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,bytesRead);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        dataSocket.close();
    }

    /**
     * 上传文件
     * @param localpath 文件的本地地址
     * @param serverpath 服务器上的地址
     * @throws Exception 读写错误
     */
    public void upload(String localpath,String serverpath) throws Exception{
        calDataHostPort();

        writer.println("STOR "+serverpath);
        writer.flush();

        dataSocket = new Socket(dataHost,dataPort);
        String response = reader.readLine();
        System.out.println(response);

        outputStream = new BufferedOutputStream(dataSocket.getOutputStream());
        inputStream = new BufferedInputStream(new FileInputStream(new File(localpath)));

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,bytesRead);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        dataSocket.close();
    }

}
