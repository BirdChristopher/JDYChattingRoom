package center;

import java.io.IOException;

public class ThreadMornitor implements Runnable{
    MySocket mySocket;

    CenterServer.Mythread targetThread;

    public ThreadMornitor(MySocket mySocket,CenterServer.Mythread targetThread){
        this.mySocket = mySocket;
        this.targetThread = targetThread;
    }
    public void run(){
        while(true){
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!targetThread.isConnected()){
                System.out.println(mySocket.getSocket().getPort()+" died."+"\n=======================");
                //终止对应的处理线程
                try {
                    targetThread.br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                targetThread.stop();
                //下线
                CenterServer.offline(mySocket);
                break;
            }
            targetThread.setConnected();
        }
    }
}
