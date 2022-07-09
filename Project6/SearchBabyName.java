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

public class SearchBabyName extends Application {
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
        Label lblYear = new Label("Enter the Year:");
        Label lblGender = new Label("Enter the Gender:");
        Label lblName = new Label("Enter the Name:");

        //Input Text fields definition
        TextField year = new TextField();
        year.setMaxWidth(110);
        TextField gender = new TextField();
        gender.setMaxWidth(30);
        TextField name = new TextField();
        name.setMaxWidth(110);

        //Buttons definition
        Button btnSubmit = new Button("Submit Query");
        btnSubmit.setTranslateY(20);
        btnSubmit.setPrefWidth(95);
        Button btnExit = new Button("Exit");
        btnExit.setTranslateX(40);
        btnExit.setTranslateY(20);
        btnExit.setPrefWidth(85);

        //Building the screen
        GridPane.setHalignment(lblYear, HPos.LEFT);
        pane.add(lblYear, 0, 0);
        pane.add(year, 1, 0);
        GridPane.setHalignment(lblGender, HPos.LEFT);
        pane.add(lblGender, 0, 1);
        pane.add(gender, 1, 1);
        GridPane.setHalignment(lblName, HPos.LEFT);
        pane.add(lblName, 0, 2);
        pane.add(name, 1, 2);
        pane.add(btnSubmit, 0, 3);
        pane.add(btnExit, 1, 3);
        txtWarning.setTranslateY(18);
        txtWarning.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        txtWarning.setFill(Color.RED);
        GridPane.setColumnSpan(txtWarning, 2);
        GridPane.setHalignment(txtWarning, HPos.CENTER);
        pane.add(txtWarning, 0, 4);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Search Name Ranking Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Buttons Event - Handle
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try {
                        //Input fields basic verifications
                        if (name.getText().isEmpty() || gender.getText().isEmpty() || year.getText().isEmpty())
                            txtWarning.setText("Some Empty Field. You should fill out all of them");
                        else if(Integer.parseInt(year.getText()) < 2009 || Integer.parseInt(year.getText()) > 2018)
                            txtWarning.setText("Year should be between 2009 to 2018");
                        else if(((gender.getText()).toLowerCase().compareTo("m") != 0 && (gender.getText()).toLowerCase().compareTo("f") != 0) || gender.getText().toString().length() > 1)
                            txtWarning.setText("Gender only can be 'M' or 'F'");
                        else {
                            NameResult result = new NameResult();
                            result.setName(name.getText());
                            result.setGender(gender.getText());
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
}