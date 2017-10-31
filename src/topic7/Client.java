package topic7;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class Client extends Application {
    private TextArea serverAnswerArea;
    private TextField clientMessageArea;
    private ListView<String> actionsList;

    private Socket socket;
    private BufferedReader inputStream;
    private ObjectOutputStream outputStream;
    private boolean connected;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        connected = false;
        primaryStage.setTitle("Laboratory work 3. Client");
        StackPane root = new StackPane();

        Map<String, Runnable> eventHandlers = new LinkedHashMap<>();
        eventHandlers.put("Send symbols", () -> {
            try {
                String text = clientMessageArea.getText();
                if (text.length() == 1) {
                    log("[Client]: add '" + text.charAt(0) + "' symbol\n");
                    outputStream.writeObject(new Packet(Packet.Type.SEND, text.charAt(0)));
                    outputStream.flush();
                    clientMessageArea.clear();
                }
                else {
                    log("Enter something in middle field\n");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        eventHandlers.put("Get all symbols", () -> {
            try {
                log("[Client]: get all symbols\n");
                outputStream.writeObject(new Packet(Packet.Type.GET));
                outputStream.flush();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        eventHandlers.put("Count symbols of type", () -> {
            try {
                String text = clientMessageArea.getText();
                if (text.length() == 1) {
                    log("[Client]: count '" + text.charAt(0) + "' symbol\n");
                    outputStream.writeObject(new Packet(Packet.Type.COUNT, text.charAt(0)));
                    outputStream.flush();
                    clientMessageArea.clear();
                }
                else {
                    log("Enter something in middle field\n");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        eventHandlers.put("Clear symbols", () -> {
            try {
                log("[Client]: clear all data\n");
                outputStream.writeObject(new Packet(Packet.Type.CLEAR));
                outputStream.flush();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        eventHandlers.put("Connect", () -> {
            try {
                if (socket != null) {
                    return;
                }

                socket = new Socket("127.0.0.1", 7743);

                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.flush();
                inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                log("Connected to server 127.0.0.1:7743\n");

                connected = true;
                new Thread(() -> {
                    while (connected) {
                        try {
                            String answer = inputStream.readLine();
                            log("[Server]: " + answer + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Main layout
        final VBox vbox = new VBox();

        // Creating server answer area
        serverAnswerArea = new TextArea();
        serverAnswerArea.setEditable(false);
        serverAnswerArea.setPrefHeight(150.0f);
        serverAnswerArea.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                serverAnswerArea.setScrollTop(Double.MAX_VALUE);
            }
        });
        vbox.getChildren().add(serverAnswerArea);

        // Creating client message area
        clientMessageArea = new TextField();
        clientMessageArea.setPrefHeight(30.0f);
        clientMessageArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (clientMessageArea.getText().length() > 1) {
                    clientMessageArea.setText("" + clientMessageArea.getText().charAt(0));
                }
            }
        });
        vbox.getChildren().add(clientMessageArea);

        // Creating actions list
        actionsList = new ListView<String>();
        ObservableList<String> actionsNames = FXCollections.observableArrayList();
        actionsNames.addAll(eventHandlers.keySet());
        actionsList.setItems(actionsNames);
        actionsList.setPrefHeight(117.0f);
        actionsList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String currentItemSelected = actionsList.getSelectionModel().getSelectedItem();
                eventHandlers.get(currentItemSelected).run();
                root.requestFocus();
            }
        });
        vbox.getChildren().add(actionsList);

        // Creating scene
        root.getChildren().add(vbox);
        primaryStage.setScene(new Scene(root, 500,
                serverAnswerArea.getPrefHeight() +
                        clientMessageArea.getPrefHeight() +
                        actionsList.getPrefHeight()));
        primaryStage.show();
    }

    private void log(String text) {
        synchronized (serverAnswerArea) {
            serverAnswerArea.appendText(text);
        }
    }
}
