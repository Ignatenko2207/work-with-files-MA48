package ua.mainacademy.service;

import ua.mainacademy.model.ConnectionInfo;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class ThreadService extends Thread{
    private static final Logger LOGGER = Logger.getLogger(ThreadService.class.getName());

    private ConnectionInfo connectionInfo;

    public ThreadService(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    @Override
    public void run() {
        LOGGER.info("Thread was started");
        connectionInfo.setConnectionIP("1111");
        connectionInfo.setUpdateTime(new AtomicLong(new Date().getTime()));
        FileManager.writeConnectionInfoToFile(connectionInfo, "threads.txt",true);
    }

}
