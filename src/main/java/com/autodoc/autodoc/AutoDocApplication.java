/**
 * @project AutoDoc
 * @author manticorevenom (Hunter Leary)
 * @version 1.0
 * @date 2022.09.07
 */
package com.autodoc.autodoc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * AutoDocApplication
 * is the class for the GUI
 * Should just launch the GUI
 */
public class AutoDocApplication extends Application {
    /**
     * start, this is generated I wouldn't mess with it
     * It handles GUI stuff
     * @sreq.freq tag_here
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoDocApplication.class.getResource("auto-doc-gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("AutoDoc");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main, literally launches the GUI
     * @sreq.freq tag_here
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}