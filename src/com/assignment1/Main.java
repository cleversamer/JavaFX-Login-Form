package com.assignment1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage currentStage;
    static boolean isFullScreen = false;

    @Override
    public void start(Stage stage) {

        Label welcomingLabel = createWelcomingLabel();
        Label adLabel = new Label("ads");
        Label copyrightLabel = new Label("© 2022 Model");
        GridPane grid = createGridPane();
        BorderPane container = createBorderPane(welcomingLabel, adLabel, copyrightLabel, grid);
        setupStage(stage, container);
        stage.show();

        currentStage = stage;
    }

    public void setupStage(Stage stage, BorderPane container) {
        Scene scene = new Scene(container, 400, 400);
        Image icon = new Image("images/logo.png");
        stage.setTitle("Model");
        stage.setScene(scene);
        stage.getIcons().add(icon);
    }

    public BorderPane createBorderPane(Label welcomingLabel, Label adLabel, Label copyrightLabel, GridPane grid) {
        HBox hBox = new HBox(adLabel);
        hBox.alignmentProperty().set(Pos.TOP_RIGHT);

        VBox vBox = new VBox(welcomingLabel, hBox);
        vBox.alignmentProperty().setValue(Pos.CENTER);

        BorderPane parent = new BorderPane();
        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setAlignment(grid, Pos.CENTER);

        parent.setPadding(new Insets(5, 5, 5, 5));
        parent.setBackground(new Background(new BackgroundFill(Color.GOLD, new CornerRadii(0), Insets.EMPTY)));
        parent.setTop(vBox);
        parent.setCenter(grid);
        parent.setBottom(copyrightLabel);

        return parent;
    }

    public Label createWelcomingLabel() {
        Label label = new Label("Welcome To Model");
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Freestyle Script", FontWeight.BOLD, FontPosture.ITALIC, 26));
        label.setUnderline(true);
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(10, 10, 10, 10));
        return label;
    }

    public GridPane createGridPane() {
        Label welcomingLabel = new Label("Welcome User");
        welcomingLabel.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.ITALIC, 26));

        Label loginLabel = new Label("Login Form");

        Label userLabel = new Label("Username");

        TextField userField = new TextField();
        userField.setPromptText("Username");

        Label passwordLabel = new Label("Password");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button getUsernameBtn = new Button("Get Username");
        getUsernameBtn.setMinHeight(30);
        getUsernameBtn.setOnAction(even -> {
            String username = userField.getText().trim();
            welcomingLabel.setText("Welcome " + username);
            System.out.println("Username = " + username);
        });

        Button getPasswordBtn = new Button("Get Password");
        getPasswordBtn.setMinHeight(30);
        getPasswordBtn.setOnAction(even -> {
            String password = passwordField.getText().trim();
            System.out.println("Password = " + password);
        });

        Image btnIcon = new Image("images/fullscreen.png");
        ImageView view = new ImageView(btnIcon);
        view.setFitWidth(25);
        view.setPreserveRatio(true);

        Button stageSizeBtn = new Button();
        stageSizeBtn.setPrefSize(30, 30);
        stageSizeBtn.setGraphic(view);
        stageSizeBtn.setOnAction(event -> {
            String url = isFullScreen ?  "images/fullscreen.png" : "images/exit_fullscreen.png";
            Image newBtnIcon = new Image(url);
            ImageView newView = new ImageView(newBtnIcon);
            newView.setFitWidth(25);
            newView.setPreserveRatio(true);
            stageSizeBtn.setGraphic(newView);

            currentStage.setFullScreen(!isFullScreen);
            isFullScreen = !isFullScreen;
        });

        VBox gridHeadingsParent = new VBox(welcomingLabel, loginLabel);
        gridHeadingsParent.alignmentProperty().setValue(Pos.CENTER);

        HBox buttonsParent = new HBox(getUsernameBtn, getPasswordBtn, stageSizeBtn);
        buttonsParent.setAlignment(Pos.CENTER_LEFT);
        buttonsParent.setSpacing(5);

        GridPane grid = new GridPane();
        grid.add(gridHeadingsParent, 0, 0, 3, 1);
        grid.add(userLabel, 0, 1);
        grid.add(userField, 1, 1, 2, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2, 2, 1);
        grid.add(buttonsParent, 0, 3, 3, 1);

        GridPane.setHalignment(welcomingLabel, HPos.CENTER);
        GridPane.setHalignment(loginLabel, HPos.CENTER);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(10);

        return grid;
    }
}
