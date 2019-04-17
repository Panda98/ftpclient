import java.io.*;
import java.net.Socket;
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

    public void connect(String host,int port,String username,String password) throws Exception{
        if(socket!= null)
            throw new Exception("socket has already connected!");

        try{
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

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("connect succeed. ");

    }

    private void sendMsg(String msg) throws Exception{
        if(socket == null){
            System.out.println("socket hasn't been connected!");
            return;
        }
        writer.write(msg+"\r\n");
    }


    public void printFilename(){

        String line = "";
        try{
            writer.println("PASV");
            writer.flush();
            writer.println("LIST");
            writer.flush();
            while ((line = reader.readLine())!= null){
                if(line.equals("end of files"))
                    break;
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void close(){
        try{
            socket.close();
            reader.close();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void calDataHostPort(){
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



    public void download(String localpath,String serverpath){
        calDataHostPort();
        try {

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

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void upload(String localpath,String serverpath){
        calDataHostPort();

        try{
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


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
