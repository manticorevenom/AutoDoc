/**
 * @project AutoDoc
 * @author manticorevenom (Hunter Leary)
 * @version 1.0
 * @date 2022.09.07
 */
package com.autodoc.autodoc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * AutoDocController
 * Creates functionality for the GUI
 * Buttons, etc
 *
 * Handles I/O, some of this is generated
 * So I wouldn't outright delete things
 */
public class AutoDocController {
    /**
     * Label for application
     * @sreq.freq
     */
    @FXML
    private Label welcomeText;

    /**
     * Button for application
     * @sreq.freq
     */
    @FXML
    private Button helloButton;

    /**
     * Listener for a button
     * Handles onClick events for helloButton
     * @sreq.freq
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}