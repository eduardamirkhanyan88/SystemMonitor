package client.loader;

import client.console.ConsoleClient;
import client.gui.SystemMonitorGUI;
import client.gui.SystemMonitorGUIClient;

import java.awt.*;

/**
 * Created by Eduard on 6/25/2017.
 */
public class ClientController {

    public static IClient createClient(){
        IClient result = null;
        if (GraphicsEnvironment.isHeadless()) {
            result = new ConsoleClient();
        }else{
            result = new SystemMonitorGUIClient();
        }
        return result;
    }
}
