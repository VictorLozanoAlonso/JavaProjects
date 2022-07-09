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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BabiesNames extends Application {

    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e) {
            System.out.println("\n**************** Error *****************");
            System.out.println(e);
            System.out.println("----------------------------------------\n");
            throw e;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setPadding(new Insets(40, 100, 40, 80));
        pane.setHgap(5);
        pane.setVgap(20);

        //Text definition
        Text txtWarning = new Text();

        //Labels definition
        Label lblInfo = new Label("The application will find the names that are used for both genders in the file.");
        Label lblYear = new Label("Enter the Year:");

        //Input Text fields definition
        TextField year = new TextField();
        year.setMaxWidth(110);

        //Buttons definition
        Button btnSubmit = new Button("Submit Query");
        btnSubmit.setTranslateY(20);
        btnSubmit.setPrefWidth(95);
        Button btnExit = new Button("Exit");
        btnExit.setTranslateX(40);
        btnExit.setTranslateY(20);
        btnExit.setPrefWidth(85);

        //Building the screen
        GridPane.setHalignment(lblInfo, HPos.CENTER);
        GridPane.setColumnSpan(lblInfo, 2);
        pane.add(lblInfo, 0, 0);
        pane.add(lblYear, 0, 1);
        pane.add(year, 1, 1);
        pane.add(btnSubmit, 0, 2);
        pane.add(btnExit, 1, 2);
        txtWarning.setTranslateY(18);
        txtWarning.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        txtWarning.setFill(Color.RED);
        GridPane.setColumnSpan(txtWarning, 2);
        GridPane.setHalignment(txtWarning, HPos.CENTER);
        pane.add(txtWarning, 0, 3);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Name Ranking Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Input fields basic verifications
                    if (year.getText().isEmpty())
                        txtWarning.setText("Empty Field. Enter the year");
                    else if(Integer.parseInt(year.getText()) < 2009 || Integer.parseInt(year.getText()) > 2018)
                        txtWarning.setText("Year should be between 2009 to 2018");
                    else {
                        BabiesNamesResult result = new BabiesNamesResult();
                        result.setYear(year.getText());
                        result.start(primaryStage);
                    }
                } catch (Exception e){
                    txtWarning.setText("Year Field have to be a number");
                }
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
    }
}
