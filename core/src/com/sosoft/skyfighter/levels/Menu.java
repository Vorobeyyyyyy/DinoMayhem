package com.sosoft.skyfighter.levels;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application {
    public static void mainMenu(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SkyFighter");
        primaryStage.show();
    }
}
