package kev;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

/**
 * JavaFX GUI for Kev chatbot.
 * <p>
 * GUI allows the user to interact with the chatbot using a text field
 * for input and a scrollable area to display both user messages and chatbot responses.
 * </p>
 */
public class KevGui extends Application {

    /** Instance of the Kev chatbot. */
    private Kev kev;

    /**
     * Entry point for the JavaFX application.
     * Initializes the Duke chatbot and sets up the GUI layout and event handlers.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        kev = new Kev("data/tasks.txt"); // initialize chatbot

        // root container for all UI elements
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // scrollable container for dialog messages
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox(5);
        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);

        // input field and send button
        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        HBox inputContainer = new HBox(5, userInput, sendButton);
        root.getChildren().addAll(scrollPane, inputContainer);

        // event handling for user input
        sendButton.setOnAction(e -> handleUserInput(userInput, dialogContainer));
        userInput.setOnAction(e -> handleUserInput(userInput, dialogContainer));

        // setting up scene and stage
        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Kev Chatbot");
        stage.show();
    }

    /**
     * handles user input by sending it to chatbot, capturing the response,
     * and displaying both the user's message and chatbot's response in the dialog container.
     *
     * @param userInput      the TextField containing the user's input
     * @param dialogContainer the VBox container where dialog messages are displayed
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        String input = userInput.getText().trim();
        assert input != null : "User input should not be null";

        if (input.isEmpty()) {
            return;
        }

        // Display user's message
        Label userLabel = new Label("You: " + input);
        dialogContainer.getChildren().add(userLabel);

        // Get response from Duke
        String response = kev.getResponse(input);
        assert response != null : "Kev response should not be null";

        Label botLabel = new Label("Kev: " + response);
        dialogContainer.getChildren().add(botLabel);

        // Clear the input field
        userInput.clear();
    }
}
