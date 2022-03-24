package center;

import java.io.IOException;

public class ThreadMornitor implements Runnable{
    MySocket mySocket;

    CenterServer.Mythread targetThread;

    public ThreadMornitor(MySocket mySocket,CenterServer.Mythread targetThread){
        this.mySocket = mySocket;
        this.targetThread = targetThread;
    }

    //规定每5s客户端需要发送一个心跳包。因此每隔8s，monitor都会置在线位为否，需要在八秒内有心跳包将在线位置为True。
    // 每八秒监控线程都会check一遍是否有心跳包发来。若8秒内无心跳包则判定已下线。
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
