package runner;

import functions.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Cupid cupid;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/cupid.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMsg = new Ui().welcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMsg, dukeImage)
        );
    }

    public void setDuke(Cupid cupid) {
        this.cupid = cupid;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.toLowerCase().equals("bye")) {
            String exitMsg = new Ui().goodbye();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(exitMsg, dukeImage)
            );
            // Create a PauseTransition to delay the application exit
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                Platform.exit(); // Exit the application after the delay
            });
            delay.play();
            userInput.clear();
        } else {
            String response = cupid.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
