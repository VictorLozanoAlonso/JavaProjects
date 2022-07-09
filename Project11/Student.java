import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Student extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        GridPane pane1 = new GridPane();
        GridPane pane2 = new GridPane();
        GridPane pane3 = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane1.setAlignment(Pos.CENTER);
        pane2.setAlignment(Pos.CENTER);
        pane3.setAlignment(Pos.CENTER);
        pane1.setPadding(new Insets(10, 10, 10, 10));
        pane2.setPadding(new Insets(10, 5, 5, 5));
        pane3.setPadding(new Insets(10, 5, 5, 5));
        pane1.setHgap(7);
        pane1.setVgap(7);
        pane2.setVgap(7);
        pane2.setHgap(5);
        pane3.setVgap(10);

        //Labels definition
        Label lblId = new Label("ID:");
        Label lblName = new Label("Name:");
        Label lblEmail = new Label("Email:");
        Label lblAddress = new Label("Address:");
        Label lblEnrollYear = new Label("Enrolled Year:");
        Label lblProgram = new Label("Program:");

        //Text Definition
        Text warning = new Text();

        //Input Text fields definition
        TextField id = new TextField();
        TextField name = new TextField();
        TextField email = new TextField();
        TextField address = new TextField();
        TextField enrollYear = new TextField();
        TextField program = new TextField();

        //Buttons definition
        Button btnAdd = new Button("Add");
        btnAdd.setPrefWidth(120);
        Button btnSearch = new Button("View");
        btnSearch.setPrefWidth(120);
        Button btnUpdate = new Button("Update");
        btnUpdate.setPrefWidth(120);
        Button btnDelete = new Button("Delete");
        btnDelete.setPrefWidth(120);
        Button btnNext = new Button(">>");
        btnNext.setPrefWidth(120);
        Button btnPrevious = new Button("<<");
        btnPrevious.setPrefWidth(120);

        //Building the screen
        GridPane.setHalignment(lblId, HPos.RIGHT);
        pane1.add(lblId, 0, 0);
        pane1.add(id, 1, 0);
        GridPane.setHalignment(lblName, HPos.RIGHT);
        pane1.add(lblName, 2, 0);
        GridPane.setColumnSpan(name, 4);
        pane1.add(name, 3, 0);
        GridPane.setHalignment(lblEmail, HPos.RIGHT);
        pane1.add(lblEmail, 0, 1);
        GridPane.setColumnSpan(email, 5);
        pane1.add(email, 1, 1);
        GridPane.setHalignment(lblAddress, HPos.RIGHT);
        pane1.add(lblAddress, 0, 2);
        pane1.add(address, 1, 2);
        GridPane.setHalignment(lblEnrollYear, HPos.RIGHT);
        pane1.add(lblEnrollYear, 2, 2);
        pane1.add(enrollYear, 3, 2);
        pane1.add(lblProgram, 4, 2);
        pane1.add(program, 5, 2);
        pane2.add(btnPrevious, 0, 0);
        pane2.add(btnAdd, 1, 0);
        pane2.add(btnUpdate, 2, 0);
        pane2.add(btnDelete, 3, 0);
        pane2.add(btnSearch, 4, 0);
        pane2.add(btnNext, 5, 0);
        warning.setFill(Color.RED);
        warning.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        pane3.add(warning, 0, 0);
        pane.add(pane1, 0, 0);
        pane.add(pane2, 0, 1);
        pane.add(pane3, 0, 2);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Student Database");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Buttons Event - Handle
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            StudentModel sData = new StudentModel();
            DbConect c = new DbConect();

            @Override
            public void handle(ActionEvent event) {
                if (!(id.getText().isEmpty() || name.getText().isEmpty() || email.getText().isEmpty()
                        || address.getText().isEmpty() || enrollYear.getText().isEmpty()
                        || program.getText().isEmpty())) {
                    sData.setStudent(parseInt(id.getText()), name.getText(), email.getText(),
                            address.getText(), parseInt(enrollYear.getText()), program.getText());
                    try {
                        warning.setText("");
                        c.addToDb(sData);
                        warning.setText("Student added to the Database");
                        id.clear();
                        name.clear();
                        email.clear();
                        address.clear();
                        enrollYear.clear();
                        program.clear();
                    }catch (Exception e){
                        warning.setText(e.getMessage());
                    }
                } else{
                    warning.setText("Error: Empty Fields");
                }
            }

        });
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            StudentModel sData = new StudentModel();
            DbConect c = new DbConect();

            @Override
            public void handle(ActionEvent event) {
                if(!id.getText().isEmpty()){
                    try {
                        sData = c.search(parseInt(id.getText()));
                        if(sData.getId() != -1) {
                            id.setText(Integer.toString(sData.getId()));
                            name.setText(sData.getName());
                            email.setText(sData.getEmail());
                            address.setText(sData.getAddress());
                            enrollYear.setText(Integer.toString(sData.getEnrYear()));
                            program.setText(sData.getProgram());
                            warning.setText("");
                        }else{
                            warning.setText("ID not found");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else{
                    warning.setText("Please enter an ID");
                }
            }

        });
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            StudentModel sData = new StudentModel();
            boolean result = true;
            DbConect c = new DbConect();

            @Override
            public void handle(ActionEvent event) {
                if (!(id.getText().isEmpty() || name.getText().isEmpty() || email.getText().isEmpty()
                        || address.getText().isEmpty() || enrollYear.getText().isEmpty()
                        || program.getText().isEmpty())) {
                    try {
                        sData = c.search(parseInt(id.getText()));
                        if(sData.getId() != -1) {
                            sData.setStudent(sData.getId(), name.getText(), email.getText(), address.getText(),
                                    parseInt(enrollYear.getText()), program.getText());
                            result = c.update(parseInt(id.getText()), sData);
                            if(result) {
                                warning.setText("Student Updated");
                                id.clear();
                                name.clear();
                                email.clear();
                                address.clear();
                                enrollYear.clear();
                                program.clear();
                            }
                            else
                                warning.setText("Error with update");
                        }else{
                            warning.setText("ID not found");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else{
                    warning.setText("Error: Empty Fields");
                }
            }

        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            StudentModel sData = new StudentModel();
            DbConect c = new DbConect();
            boolean result = true;

            @Override
            public void handle(ActionEvent event) {
                if(!id.getText().isEmpty()){
                    try {
                        sData = c.search(parseInt(id.getText()));
                        if(sData.getId() != -1) {
                            result = c.delete(parseInt(id.getText()));
                            if (result) {
                                warning.setText("Student deleted");
                                id.clear();
                                name.clear();
                                email.clear();
                                address.clear();
                                enrollYear.clear();
                                program.clear();
                            }else{
                                warning.setText("Error with deleting");
                            }
                        }else{
                            warning.setText("ID not found");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else{
                    warning.setText("Please enter an ID");
                }
            }

        });
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            List<StudentModel> students = new ArrayList<StudentModel>();
            StudentModel sData = new StudentModel();
            DbConect c = new DbConect();

            @Override
            public void handle(ActionEvent event) {
                try {
                    int last = -1, position = 0;
                    students = c.totalStudents();
                    last = students.size() - 1;
                    warning.setText("");
                    if (last == -1) {
                        warning.setText("There is no students");
                    }else {
                        if (id.getText().isEmpty()) {
                            id.setText(Integer.toString(students.get(0).getId()));
                            name.setText(students.get(0).getName());
                            email.setText(students.get(0).getEmail());
                            address.setText(students.get(0).getAddress());
                            enrollYear.setText(Integer.toString(students.get(0).getEnrYear()));
                            program.setText(students.get(0).getProgram());
                            warning.setText("");
                        } else {
                            sData = c.search(parseInt(id.getText()));
                            if (sData.getId() != -1) {
                                while (students.get(position).getId() != parseInt(id.getText()))
                                    position++;
                                if (position < last) {
                                    id.setText(Integer.toString(students.get(position + 1).getId()));
                                    name.setText(students.get(position + 1).getName());
                                    email.setText(students.get(position + 1).getEmail());
                                    address.setText(students.get(position + 1).getAddress());
                                    enrollYear.setText(Integer.toString(students.get(position + 1).getEnrYear()));
                                    program.setText(students.get(position + 1).getProgram());
                                    warning.setText("");
                                } else {
                                    warning.setText("This is the last student");
                                }
                            } else {
                                warning.setText("ID not found");
                                id.clear();
                                name.clear();
                                email.clear();
                                address.clear();
                                enrollYear.clear();
                                program.clear();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            List<StudentModel> students = new ArrayList<StudentModel>();
            StudentModel sData = new StudentModel();
            DbConect c = new DbConect();

            @Override
            public void handle(ActionEvent event) {
                try {
                    int position = 0;
                    warning.setText("");
                    students = c.totalStudents();
                    if(students.size() == 0) {
                        warning.setText("There is no students");
                    }else{
                        if (id.getText().isEmpty()) {
                            id.setText(Integer.toString(students.get(0).getId()));
                            name.setText(students.get(0).getName());
                            email.setText(students.get(0).getEmail());
                            address.setText(students.get(0).getAddress());
                            enrollYear.setText(Integer.toString(students.get(0).getEnrYear()));
                            program.setText(students.get(0).getProgram());
                            warning.setText("");
                        } else {
                            sData = c.search(parseInt(id.getText()));
                            if (sData.getId() != -1) {
                                while (students.get(position).getId() != parseInt(id.getText()))
                                    position++;
                                if (position == 0)
                                    warning.setText("This is the first student");
                                else {
                                    id.setText(Integer.toString(students.get(position - 1).getId()));
                                    name.setText(students.get(position - 1).getName());
                                    email.setText(students.get(position - 1).getEmail());
                                    address.setText(students.get(position - 1).getAddress());
                                    enrollYear.setText(Integer.toString(students.get(position - 1).getEnrYear()));
                                    program.setText(students.get(position - 1).getProgram());
                                    warning.setText("");
                                }
                            } else {
                                warning.setText("ID not found");
                                id.clear();
                                name.clear();
                                email.clear();
                                address.clear();
                                enrollYear.clear();
                                program.clear();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
