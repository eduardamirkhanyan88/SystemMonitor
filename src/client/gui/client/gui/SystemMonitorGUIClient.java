package client.gui;

import client.loader.IClient;

/**
 * Created by Eduard on 6/26/2017.
 */
public class SystemMonitorGUIClient implements IClient {
    @Override
    public void run() {
        javafx.application.Application.launch(SystemMonitorGUI.class);
    }
}
