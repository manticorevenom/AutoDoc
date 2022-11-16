/**
 * @project AutoDoc
 * @author manticorevenom (Hunter Leary), lovecraftssiren (Alexandra Thompson)
 * @version 1.0
 * @date 2022.09.07
 */
package com.autodoc.autodoc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;


/**
 * AutoDocApplication
 * is the class for the GUI
 * Should just launch the GUI
 */
public class AutoDocApplication extends Application{
    Stage window;
    Scene startScene, mainScene;

    Label code_file_label;
    Label doc_file_label;

    public static ArrayList<String> keywords;

    String code_path, doc_path, doc_name;

    Auto auto;

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) throws IOException {
        window = stage;
        initUI(window);
    }

    public void initUI(Stage stage){
        // Set up the scenes before starting the program
        setUpStartScene();
        setUpMainScene();

        // Set title for the application
        stage.setTitle("AutoDoc");
        stage.setScene(startScene);
        stage.show();
    }

    public void setUpStartScene() {
        // Set scene with VBox, 400 height, 700 width, centering
        var root = new VBox(40);
        root.setPrefHeight(400);
        root.setPrefWidth(700);
        root.setAlignment(Pos.CENTER);

        // Scene title label
        var label = new Label("AutoDoc: Start Screen");
        label.setFont(new Font("Arial",30));

        // Go Button To Main Menu
        var go_button = new Button("Go to Main Menu");
        go_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                if (code_path != null && doc_path != null){
                    String[] words = {"public", "private", "protected", "class"};
                    keywords = new ArrayList<>(List.of(words));
                    auto = new Auto(doc_path, "FREQ", code_path, "FREQ", keywords);
                    window.setScene(mainScene);
                }
                else{
                    System.out.println("Both code and documentation files need to be selected.");
                }

            }
        });
        go_button.setMinWidth(150);

        // File Buttons
        var file_buttons = new HBox(50);
        file_buttons.setAlignment(Pos.CENTER);

        //Printing document names
        var file_names = new VBox(4);
        file_names.setAlignment(Pos.CENTER);
        code_file_label = new Label("No Code Selected");
        doc_file_label = new Label("No Documentation Selected");
        file_names.getChildren().addAll(code_file_label,doc_file_label);

        // Select code button
        var select_code_button = new Button("Select code file");
        select_code_button.setMinWidth(150);
        select_code_button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                final FileChooser codeFileChooser = new FileChooser();
                File file = codeFileChooser.showOpenDialog(window);
                if (file != null) {
                    System.out.println(file.getAbsolutePath());
                    code_path = file.getAbsolutePath();
                    code_file_label.setText("Code file: "+file.getName());
                }
            }
        });

        // Select documentation button
        var select_doc_button = new Button("Select documentation file");
        select_doc_button.setMinWidth(150);
        select_doc_button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                final FileChooser docFileChooser = new FileChooser();
                File file = docFileChooser.showOpenDialog(window);
                if (file != null) {
                    System.out.println(file.getAbsolutePath());
                    doc_path = file.getAbsolutePath();
                    doc_name = file.getName();
                    doc_file_label.setText("Documentation file: "+file.getName());
                }
            }
        });

        //File buttons adding
        file_buttons.getChildren().addAll(select_code_button, select_doc_button);

        //Add all aspects to the scene
        root.getChildren().addAll(label, file_buttons, file_names, go_button);

        //Set up the scene
        this.startScene = new Scene(root);
    }

    public void setUpMainScene() {

        // Set scene with VBox, 400 height, 700 width, centering
        var root = new VBox(20);
        root.setPrefHeight(400);
        root.setPrefWidth(700);
        root.setAlignment(Pos.CENTER);

        // Scene title label
        var label = new Label("AutoDoc: Main Menu");
        label.setFont(new Font("Arial",30));

        //Code requirements text
        ScrollPane code_reqs = new ScrollPane();
        Label code_reqs_text = new Label();
        code_reqs.setContent(code_reqs_text);
        code_reqs.setMinWidth(300);
        code_reqs.setMaxHeight(500);

        //Surround text with label
        VBox code_reqs_block = new VBox(10);
        code_reqs_block.setAlignment(Pos.CENTER);
        Label code_reqs_title = new Label("Code Requirements");
        code_reqs_title.setFont(new Font(16));
        code_reqs_title.setUnderline(true);

        code_reqs_block.getChildren().addAll(code_reqs_title, code_reqs);

        //Doc requirements text
        ScrollPane doc_reqs = new ScrollPane();
        Label doc_reqs_text = new Label();
        doc_reqs.setContent(doc_reqs_text);
        doc_reqs.setMinWidth(300);
        doc_reqs.setMaxHeight(500);

        //Surround text with label
        VBox doc_reqs_block = new VBox(10);
        doc_reqs_block.setAlignment(Pos.CENTER);
        Label doc_reqs_title = new Label("Document Requirements");
        doc_reqs_title.setFont(new Font(16));
        doc_reqs_title.setUnderline(true);

        doc_reqs_block.getChildren().addAll(doc_reqs_title, doc_reqs);

        //Conflicts text
        ScrollPane conflicts_scroll = new ScrollPane();
        Label conflicts_text = new Label();
        conflicts_scroll.setContent(conflicts_text);
        conflicts_scroll.setMinWidth(300);
        conflicts_scroll.setMaxHeight(500);

        //Surround text with label
        VBox conflicts_block = new VBox(10);
        conflicts_block.setAlignment(Pos.CENTER);
        Label conflicts_title = new Label("Conflicts");
        conflicts_title.setFont(new Font(16));
        conflicts_title.setUnderline(true);

        conflicts_block.getChildren().addAll(conflicts_title, conflicts_scroll);

        // Text formatting
        HBox text_boxes = new HBox(20);
        text_boxes.setAlignment(Pos.CENTER);
        text_boxes.getChildren().addAll(new Label("Click Check Requirements to Get Started"));


        // Check + Print Requirements + Print Conflicts
        var check_requirements = new Button("Check Requirements");
        check_requirements.setMinWidth(150);
        check_requirements.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                code_reqs_text.setText(auto.printCodeReqs());
                doc_reqs_text.setText(auto.printDocReqs());

                text_boxes.getChildren().clear();
                text_boxes.getChildren().addAll(code_reqs_block, doc_reqs_block);
            }
        });

        // View Conflicts in Documentation
        var view_conflicts = new Button("View Conflicts");
        view_conflicts.setMinWidth(150);
        view_conflicts.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                text_boxes.getChildren().clear();

                auto.check();

                conflicts_text.setText(auto.printConflicts());

                text_boxes.getChildren().addAll(conflicts_block);
            }
        });



        // Update Requirements in Documentation
        var update_requirements = new Button("Update Requirements");
        update_requirements.setMinWidth(150);
        update_requirements.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                auto.update();

                text_boxes.getChildren().clear();
                text_boxes.getChildren().addAll(new Label(doc_name+" has been updated."));
            }
        });






        // Check + Update Buttons Box
        var buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(check_requirements, view_conflicts, update_requirements);


        // Start over (go back) button
        var start_button = new Button("Start Over");
        start_button.setMinWidth(150);
        start_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.setScene(startScene);
                auto = null;
                code_path = null;
                doc_path = null;
                doc_name = null;
                doc_file_label.setText("No Documentation Selected");
                code_file_label.setText("No Code Selected");

            }
        });

        // Add all factors to scene
        root.getChildren().addAll(label, buttons, text_boxes, start_button);

        //Set up this scene
        this.mainScene = new Scene(root);
    }

}