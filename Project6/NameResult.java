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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NameResult extends Application {
    String name = "";
    String year = "";
    String gender = "";

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void start(Stage primaryStage) {
        BabiesNameData bNames = new BabiesNameData(this.year);
        String rank = "";
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(40, 80, 40, 60));
        pane.setHgap(5);
        pane.setVgap(20);

        //Labels definition
        rank = Integer.toString(bNames.getRank(this.name, this.gender));
        Label lblResult = new Label();
        if(rank.compareTo("-1") != 0)
            lblResult.setText(((this.gender.toLowerCase()).compareTo("m") == 0 ? "Boy" : "Girl") + " name " + this.name + " is ranked #" + rank + " in " + this.year + " year");
        else
            lblResult.setText("Name " + this.name + " is not in " + this.year + " ranking names.");
        Label lblAgain = new Label("Do you want to Search for another Name?");

        //Buttons definition
        Button btnYes = new Button("Yes");
        btnYes.setPrefWidth(90);
        btnYes.setTranslateX(10);
        Button btnNo = new Button("No");
        btnNo.setPrefWidth(90);
        btnNo.setTranslateX(10);

        //Building the scene
        GridPane.setHalignment(lblResult, HPos.CENTER);
        GridPane.setColumnSpan(lblResult, 2);
        pane.add(lblResult, 0, 0);
        GridPane.setHalignment(lblAgain, HPos.CENTER);
        GridPane.setColumnSpan(lblAgain, 2);
        pane.add(lblAgain, 0, 1);
        pane.add(btnYes, 0, 2);
        GridPane.setHalignment(btnNo, HPos.CENTER);
        pane.add(btnNo, 1, 2);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Name Ranking Application");
        primaryStage.show();

        btnYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SearchBabyName newName = new SearchBabyName();
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
