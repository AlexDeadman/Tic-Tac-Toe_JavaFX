package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // Cringe ----------------------------------------------------------------------------------------------------------

    @FXML
    private Button cringeButton;

    @FXML
    private Pane cringePane;

    @FXML
    private ImageView cringeImage;

    @FXML
    private final Alert suckAlert = new Alert(Alert.AlertType.WARNING, "You suck");

    boolean isCringe = false;

    public void showCringe(ActionEvent actionEvent) {
        isCringe = !isCringe;
        cringePane.setVisible(isCringe);
        if (isCringe) {
            cringeButton.setText("Show TTT");
        } else {
            cringeButton.setText("Show cringe");
        }
    }

    public void showSuck(MouseEvent mouseEvent) {
        suckAlert.showAndWait();
    }

    // Tic-tac-toe -----------------------------------------------------------------------------------------------------

    @FXML
    private Pane tttPane;

    @FXML
    private GridPane tttGrid;

    @FXML
    private Button startButton;

    @FXML
    private Label turnInfo;

    Image X = new Image(getClass().getResourceAsStream("/X.png"));
    Image O = new Image(getClass().getResourceAsStream("/O.png"));

    boolean gameStarted = false;
    boolean turnX;

    ObservableList<Node> poles;

    public void StartGame(ActionEvent actionEvent) {
        if(!gameStarted) {
            startButton.setText("Restart game");
            tttPane.setDisable(false);
            gameStarted = true;
        }

        for (int i = 0; i < 9; i++) {
            ((ImageView) poles.get(i)).setImage(null);
            ((ImageView) poles.get(i)).setCursor(Cursor.HAND);
        }

        turnX = new Random().nextBoolean();
        turnInfo.setText("Turn: random");

        if (isCringe) {
            showCringe(actionEvent);
        }
    }

    // Initialize ------------------------------------------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cringeImage.setImage(new Image(getClass().getResourceAsStream("/viblyadok.png")));

        poles = tttGrid.getChildren();

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            ((ImageView) poles.get(i)).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ImageView currPole = (ImageView) poles.get(finalI);

                    if(currPole.getImage() == null) {
                        if (turnX) {
                            currPole.setImage(X);
                            turnInfo.setText("Turn: O");
                        } else {
                            currPole.setImage(O);
                            turnInfo.setText("Turn: X");
                        }
                        currPole.setCursor(Cursor.DEFAULT);
                        turnX = !turnX;
                    }
                }
            });
        }
    }
}