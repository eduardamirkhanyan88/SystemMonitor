package client.gui;/**
 * Created by Eduard on 6/26/2017.
 */

import client.loader.IClient;
import client.service.SystemDataTrackerService;
import common.model.ProcessModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SystemMonitorGUI extends Application {
    private static final Object SYNCOBJECT = new Object();
    private static final int GUIREFRESHINTERVAL = 500;
    private static final ObservableList<ProcessModel> processList = FXCollections.observableArrayList();


    Stage window;
    javafx.scene.control.TableView<ProcessModel> table;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        startUpdate();
        window = primaryStage;
        window.setTitle("System Monitor");

        javafx.scene.control.TableColumn<ProcessModel,Long> pidColumn = new javafx.scene.control.TableColumn<>("Pid");
        pidColumn.setMinWidth(75);
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));

        javafx.scene.control.TableColumn<ProcessModel,String> processnameColumn = new javafx.scene.control.TableColumn<>("Processname");
        processnameColumn.setMinWidth(75);
        processnameColumn.setCellValueFactory(new PropertyValueFactory<>("processname"));

        javafx.scene.control.TableColumn<ProcessModel,String> stateColumn = new javafx.scene.control.TableColumn<>("State");
        stateColumn.setMinWidth(75);
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        javafx.scene.control.TableColumn<ProcessModel,String> priorityColumn = new javafx.scene.control.TableColumn<>("Priority");
        priorityColumn.setMinWidth(75);
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));

        javafx.scene.control.TableColumn<ProcessModel,String> threadsColumn = new javafx.scene.control.TableColumn<>("Threads");
        threadsColumn.setMinWidth(75);
        threadsColumn.setCellValueFactory(new PropertyValueFactory<>("threads"));

        table = new javafx.scene.control.TableView<>();
        synchronized (SYNCOBJECT) {
            table.setItems(processList);
        }
        table.getColumns().addAll(processnameColumn,pidColumn,stateColumn,priorityColumn,threadsColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    private final List<ProcessModel> currList = new ArrayList<ProcessModel>();
    public void startUpdate(){
        Task task = new Task<Void>(){
            @Override
            public Void call(){
                while (true) {
                    synchronized (SYNCOBJECT) {
                        currList.clear();
                        currList.addAll(SystemDataTrackerService.getInstance().getProcessList());
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (SYNCOBJECT) {
                                processList.clear();
                                processList.addAll(currList);
                            }
                        }
                    });

                    try {
                        Thread.sleep(GUIREFRESHINTERVAL);
                    }
                    catch (InterruptedException ex){
                    }
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
