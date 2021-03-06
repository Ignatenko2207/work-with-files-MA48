package ua.mainacademy;

import ua.mainacademy.model.ConnectionInfo;
import ua.mainacademy.service.FileManager;
import ua.mainacademy.service.ThreadService;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class AppRunner {

    private static final Logger LOGGER = Logger.getLogger(AppRunner.class.getName());

    public static void main(String[] args) {
//        ConnectionInfo connectionInfo = new ConnectionInfo(123456, new Date().getTime(), "123.123.123.123");
//        FileManager.writeConnectionInfoToFile(connectionInfo, "test.txt");
//
//        List<ConnectionInfo> connections = FileManager.readConnectionInfoFromFile("test.txt");
//        LOGGER.info(String.format("The first element is %s", connections.get(0).toString()));

//        FileManager.copyFile("cat.jpg", "copyCat.jpg");
//        FileManager.moveFile("cat.jpg", "otherCopyCat.jpg");

        LOGGER.info("Start program at " + new Date());
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(1000*60*60*24);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ConnectionInfo connectionInfo =
                    new ConnectionInfo(i+1, new Date().getTime(), "111.111.111.11"+i);
            ThreadService threadService = new ThreadService(connectionInfo);
            threadService.start();
        }
        LOGGER.info("End program at " + new Date());

    }

}
