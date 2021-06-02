package com.jdy.Client;

import com.jdy.Client.component.MyWindow;
import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.frame.ChatFrame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/fxml/login.fxml"));
        AnchorPane rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();*/
        //ListViewCell demo = new ListViewCell(new Image("/image/Image 2.png"), "demo");
        //ChatFrame chatFrame = new ChatFrame();
        //StackPane pane = new StackPane(demo);
        //Scene scene = new Scene(chatFrame, 400, 400);
        //scene.getStylesheets().add("CSS/SplitPane.css");
        //Stage stage = new Stage();
        //stage.setScene(scene);
        //stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
