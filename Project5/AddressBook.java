import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddressBook extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            AddressRecords records = new AddressRecords("data.txt");
            records.read("data.txt");
            String[] provList = {"Alberta", "British Columbia", "Manitoba", "New Btunswick", "Newfoundland and Labrador",
                    "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan"};
            GridPane pane = new GridPane();
            GridPane pane1 = new GridPane();
            GridPane pane2 = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane1.setAlignment(Pos.CENTER);
            pane2.setAlignment(Pos.CENTER);
            pane1.setPadding(new Insets(10, 10, 10, 10));
            pane2.setPadding(new Insets(10, 5, 5, 5));
            pane1.setHgap(7);
            pane1.setVgap(7);
            pane2.setVgap(7);
            pane2.setHgap(5);

            //Labels definition
            Label lblFName = new Label("First Name:");
            Label lblLName = new Label("Last Name:");
            Label lblCity = new Label("City:");
            Label lblProvince = new Label("Province:");
            Label lblPCode = new Label("Postal Code:");

            //Input Text fields definition
            TextField fName = new TextField(records.getRecord(0)[0]);
            TextField lName = new TextField(records.getRecord(0)[1]);
            TextField city = new TextField(records.getRecord(0)[2]);
            TextField pCode = new TextField(records.getRecord(0)[4]);
            //Combo box definition
            ComboBox province = new ComboBox<>(FXCollections
                    .observableArrayList(provList));
            province.setValue(records.getRecord(0)[3]);
            province.setPrefWidth(150);

            //Buttons definition
            Button btnAdd = new Button("Add");
            btnAdd.setPrefWidth(120);
            Button btnFirst = new Button("First");
            btnFirst.setPrefWidth(120);
            Button btnNext = new Button("Next");
            btnNext.setPrefWidth(120);
            Button btnPrevious = new Button("Previous");
            btnPrevious.setPrefWidth(120);
            Button btnLast = new Button("Last");
            btnLast.setPrefWidth(120);
            Button btnUpdate = new Button("Update");
            btnUpdate.setPrefWidth(120);

            //Building the screen
            GridPane.setHalignment(lblFName, HPos.RIGHT);
            pane1.add(lblFName, 0, 0);
            GridPane.setColumnSpan(fName, 5);
            pane1.add(fName, 1, 0);
            GridPane.setHalignment(lblLName, HPos.RIGHT);
            pane1.add(lblLName, 0, 1);
            GridPane.setColumnSpan(lName, 5);
            pane1.add(lName, 1, 1);
            GridPane.setHalignment(lblCity, HPos.RIGHT);
            pane1.add(lblCity, 0, 2);
            pane1.add(city, 1, 2);
            GridPane.setHalignment(lblProvince, HPos.RIGHT);
            pane1.add(lblProvince, 2, 2);
            pane1.add(province, 3, 2);
            GridPane.setHalignment(lblPCode, HPos.RIGHT);
            pane1.add(lblPCode, 4, 2);
            pane1.add(pCode, 5, 2);
            pane2.add(btnAdd, 0, 0);
            pane2.add(btnFirst, 1, 0);
            pane2.add(btnNext, 2, 0);
            pane2.add(btnPrevious, 3, 0);
            pane2.add(btnLast, 4, 0);
            pane2.add(btnUpdate, 5, 0);
            pane.add(pane1, 0, 0);
            pane.add(pane2, 0, 1);

            Scene scene = new Scene(pane);
            primaryStage.setTitle("Address Book");
            primaryStage.setScene(scene);
            primaryStage.show();

            //Buttons Event - Handle
            btnAdd.setOnAction(new EventHandler<ActionEvent>() {
                String[] record = new String[5];

                @Override
                public void handle(ActionEvent event) {
                    if (!(fName.getText().isEmpty() || lName.getText().isEmpty() || city.getText().isEmpty()
                            || province.getValue() == "" || pCode.getText().isEmpty())) {
                        if (pCode.getText().length() == 6) {
                            record[0] = fName.getText();
                            record[1] = lName.getText();
                            record[2] = city.getText();
                            record[3] = province.getValue().toString();
                            record[4] = pCode.getText();
                            records.write(record, "data.txt", true);
                            records.incPosition();
                        }
                    }
                }

            });

            btnFirst.setOnAction(new EventHandler<ActionEvent>() {
                String[] record = new String[5];

                @Override
                public void handle(ActionEvent event) {
                    record = records.getRecord(0);
                    fName.setText(record[0]);
                    lName.setText(record[1]);
                    city.setText(record[2]);
                    province.setValue(record[3]);
                    pCode.setText(record[4]);
                    records.setPosition(0);
                }

            });
            btnLast.setOnAction(new EventHandler<ActionEvent>() {
                String[] record = new String[5];

                @Override
                public void handle(ActionEvent event) {
                    record = records.getRecord(records.getNumRecords() - 1);
                    fName.setText(record[0]);
                    lName.setText(record[1]);
                    city.setText(record[2]);
                    province.setValue(record[3]);
                    pCode.setText(record[4]);
                    records.setPosition(records.getNumRecords() - 1);
                }

            });

            btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
                String[] record = new String[5];

                @Override
                public void handle(ActionEvent event) {
                    if (!(fName.getText().isEmpty() || lName.getText().isEmpty() || city.getText().isEmpty()
                            || province.getValue() == "" || pCode.getText().isEmpty())) {
                        if (pCode.getText().length() == 6) {
                            record[0] = fName.getText();
                            record[1] = lName.getText();
                            record[2] = city.getText();
                            record[3] = province.getValue().toString();
                            record[4] = pCode.getText();
                            records.updateRecord(record, records.getPosition());
                            records.write(record, "data.txt", false);
                        }
                    }
                }

            });

            btnNext.setOnAction(new EventHandler<ActionEvent>() {
                String[] record = new String[5];

                @Override
                public void handle(ActionEvent event) {
                    if (records.getPosition() < records.nRecords - 1) {
                        records.incPosition();
                        record = records.getRecord(records.getPosition());
                        fName.setText(record[0]);
                        lName.setText(record[1]);
                        city.setText(record[2]);
                        province.setValue(record[3]);
                        pCode.setText(record[4]);
                    }
                }

            });

            btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
                String[] record = new String[5];

                @Override
                public void handle(ActionEvent event) {
                    if (records.getPosition() > 0) {
                        records.decPosition();
                        record = records.getRecord(records.getPosition());
                        fName.setText(record[0]);
                        lName.setText(record[1]);
                        city.setText(record[2]);
                        province.setValue(record[3]);
                        pCode.setText(record[4]);
                    }
                }

            });
        } catch (Exception e) {
            System.out.println("\n**************** Error *****************");
            System.out.println(e);
            System.out.println("----------------------------------------\n");
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
