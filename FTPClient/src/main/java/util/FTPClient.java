package util;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Created by Pan on 2019/4/17.
 */
public class FTPClient {
    private String host;
    private int port;
    private String username;
    private String password;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private BufferedOutputStream outputStream;

    private Socket dataSocket;
    private String dataHost;
    private int dataPort;

    private double progress;

    private final String localCoding = "GB2312";
    private final String serverCoding = "ISO-8859-1";


    /**
     *  连接ftp服务器
     * @param host 服务器ip
     * @param port 端口号
     * @param username 登录用户名
     * @param password 登录密码
     * @throws Exception 连接失败
     */
    public void connect(String host,int port,String username,String password) throws Exception{

        socket = new Socket(host,port);
        reader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),serverCoding));

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

        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;



    }


    /**
     *  列出目录下文件及文件夹
     * @param path 目录名，若为根目录，则传入"/"
     * @return key-文件名/文件夹名，value-类型（文件夹为DIR，其余为FILE）
     * @throws Exception 读取错误
     */
    public LinkedHashMap<String,String> list(String path) throws Exception{
        if(socket == null)
            throw new Exception("连接未建立！");

        path = new String(path.getBytes(),serverCoding);
        LinkedHashMap<String,String> contents = new LinkedHashMap<String, String>();
        calDataHostPort();

        String response;

        sendMsg("NLST "+path);
        response = reader.readLine();
        System.out.println(response);
        if(response.startsWith("451 "))
            return contents;

        dataSocket = new Socket(dataHost,dataPort);
        response = reader.readLine();
        System.out.println(response);

        BufferedReader dataReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream(),localCoding));
        String line;
        while ((line = dataReader.readLine())!= null){
            String type = "DIR";
            if(line.contains("."))
                type = "FILE";
            contents.put(line,type);
            System.out.println(line);
        }


        return contents;
    }

    /**
     * 断开FTP连接
     */
    public void close(){
        try{
            if(socket != null) {
                socket.close();
                socket = null;
            }
            if(reader!= null) {
                reader.close();
                reader = null;
            }
            if(writer != null) {
                writer.close();
                writer = null;
            }
            if(outputStream != null) {
                outputStream.close();
                outputStream = null;
            }
            if(dataSocket != null){
                dataSocket.close();
                dataSocket = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void calDataHostPort()throws Exception{
        sendMsg("PASV");

        try{
            String response = reader.readLine();
            System.out.println(response);
            if (!response.startsWith("227 ")) {
                throw new Exception("无法设置被动模式！");
            }
            int opening = response.indexOf('(');
            int closing = response.indexOf(')', opening + 1);
            if (closing > 0) {
                String datalink = response.substring(opening + 1, closing);
                StringTokenizer tokenizer = new StringTokenizer(datalink, ",");
                dataHost = tokenizer.nextToken() + "." + tokenizer.nextToken() + "." + tokenizer.nextToken() + "." +
                        tokenizer.nextToken();
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
        if(host == null)
            throw new Exception("连接未建立！");
        connect(host,port,username,password);

        localpath = new String(localpath.getBytes(localCoding),serverCoding);
        serverpath = new String(serverpath.getBytes(localCoding),serverCoding);

        calDataHostPort();
        long length = getSize(serverpath);
        System.out.println("文件总长："+length);

        File file = new File(localpath);
        int startIndex = 0;
        if(file.exists())
            startIndex = (int) file.length();
        if(startIndex == length)
            throw new Exception("文件已存在！");

        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
        randomAccessFile.seek(startIndex);

        sendMsg("REST "+startIndex);

        String response = reader.readLine();
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
        response = reader.readLine();
        if(!response.startsWith("226 "))
            throw new Exception("传输失败！");

    }

    /**
     * 上传文件
     * @param localpath 文件的本地地址
     * @param serverpath 服务器上的地址
     * @throws Exception 读写错误
     */
    public void upload(String localpath,String serverpath,Object lock) throws Exception{
        if(host == null)
            throw new Exception("连接未建立！");
        connect(host,port,username,password);

        localpath = new String(localpath.getBytes(localCoding),serverCoding);
        serverpath = new String(serverpath.getBytes(localCoding),serverCoding);

        calDataHostPort();

        long size= getSize(serverpath);
        sendMsg("APPE "+serverpath);
        String response = reader.readLine();
        System.out.println(response);

        dataSocket = new Socket(dataHost,dataPort);

        File file = new File(localpath);
        long length = file.length();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
        randomAccessFile.seek(size);

        outputStream = new BufferedOutputStream(dataSocket.getOutputStream());

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        long already = size;
        while ((bytesRead = randomAccessFile.read(buffer))!=-1){
            outputStream.write(buffer,0,bytesRead);
            already += bytesRead;
            synchronized (lock) {
                progress = (double) already / length;
            }
            outputStream.flush();

        }
        randomAccessFile.close();
        dataSocket.close();

        response = reader.readLine();
        if(!response.startsWith("226 "))
            throw new Exception("传输失败！");
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

    /**
     * 获得文件大小
     * @param filepath 文件相对根目录的路径
     * @return 文件大小
     * @throws Exception ftp连接错误
     */
    public long getSize(String filepath) throws Exception{
        filepath = new String(filepath.getBytes(localCoding),serverCoding);

        sendMsg("SIZE "+filepath);

        String response = reader.readLine();
        long length = 0;
        if(!response.startsWith("550"))
            length = Integer.parseInt(response.substring(4,response.length()));
        System.out.println(filepath+": "+length);

        return length;
    }

    /**
     * 获得文件夹大小
     * @param path 文件夹路径
     * @return 文件夹大小
     * @throws Exception ftp连接错误
     */
    public long getDirSize(String path) throws Exception{
        HashMap<String,String> contents = list(path);
        long length = 0;
        Iterator it = contents.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            if(entry.getValue().toString().equals("FILE")){
                long l = getSize(entry.getKey().toString());
                length += l;
            }else{
                long l = getDirSize(entry.getKey().toString());
                length += l;
            }

        }
        System.out.println("("+path+")大小："+length);
        return length;
    }
}
