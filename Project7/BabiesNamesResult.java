import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BabiesNamesResult extends Application {
    String year = "";

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public void start(Stage primaryStage) {
        List<String> names = new ArrayList<String>();
        BabiesNamesData bNames = new BabiesNamesData(this.year);
        names = bNames.FindCommonNames();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(40, 80, 40, 60));
        pane.setHgap(5);
        pane.setVgap(20);

        //Labels definition
        String nameList = "";
        for(String n:names)
            nameList += n + ", ";
        nameList = nameList.substring(0, (nameList.length()-2));
        Label lblInfo = new Label(names.size() + " names used for both genders. They are: ");
        Text text = new Text(nameList);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setWrappingWidth(500);

        Label lblAgain = new Label("Do you want to Search for another Name?");

        //Buttons definition
        Button btnYes = new Button("Yes");
        btnYes.setPrefWidth(90);
        btnYes.setTranslateX(80);
        Button btnNo = new Button("No");
        btnNo.setPrefWidth(90);
        btnNo.setTranslateX(80);

        //Building the scene
        GridPane.setHalignment(lblInfo, HPos.CENTER);
        GridPane.setColumnSpan(lblInfo, 2);
        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setColumnSpan(text, 2);
        pane.add(lblInfo, 0, 0);
        pane.add(text, 0, 1);
        GridPane.setHalignment(lblAgain, HPos.CENTER);
        GridPane.setColumnSpan(lblAgain, 2);
        pane.add(lblAgain, 0, 2);
        GridPane.setHalignment(btnYes, HPos.CENTER);
        pane.add(btnYes, 0, 3);
        GridPane.setHalignment(btnNo, HPos.CENTER);
        pane.add(btnNo, 1, 3);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Name Ranking Application");
        primaryStage.show();

        btnYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BabiesNames newName = new BabiesNames();
                newName.start(primaryStage);
            }
        });
        btnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
    }
}
