package io.Ap.StardewValley.Server.App;


import io.Ap.StardewValley.Common.Message;

import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateThread extends Thread {
    private AtomicBoolean end;

    public UpdateThread() {
        this.end = new AtomicBoolean(false);
    }

    private void sendDiffToAll() {
        System.out.println("sh7");
        for(ClientConnectionThread connection : ServerApp.getConnections()) {
            //TODO
            if (true) {
                System.out.println("sh8");
                connection.sendMessage(new Message(ServerApp.getUpdateBody(), Message.Type.update));
                System.out.println(ServerApp.getUpdateBody());
            }

        }
    }

    @Override
    public void run() {
        int minute = 0;
        while(!end.get()) {
            System.out.println("sh1");
            if(minute == 1 * 60) {
                ServerApp.getUpdateBody().put("advanceTime", 1);
                minute = 0;
            }
            System.out.println("sh2");
            if(!ServerApp.getUpdateBody().isEmpty()) {
                System.out.println("sh3");
                sendDiffToAll();
                System.out.println("sh4");
                ServerApp.getUpdateBody().clear();
                System.out.println("sh5");
            }
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                System.out.println("sh6");
                System.out.println("errorrr");
                throw new RuntimeException(e);
            }
            minute++;
        }
    }

    public void end() {
        end.set(true);
    }
}
