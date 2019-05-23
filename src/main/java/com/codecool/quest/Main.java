package com.codecool.quest;

import com.codecool.quest.logic.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label nameLabel = new Label();
    Button pickupButton = new Button("Pick up item");


    List<String> itemsList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        TextInputDialog dialog = new TextInputDialog("Admin");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Name of the player");
        dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            map.getPlayer().getCell()
            System.out.println("Your name: " + result.get());
        }


        ui.add(new Label("Name: "), 0, 0);
        ui.add(nameLabel, 1, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);
        pickupButton.setVisible(false);
        ui.add(pickupButton, 1, 2);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            // Skeleton move while pressing the arrow
            case UP:
                map.getPlayer().move(0, -1);
                openDoor(0, -1);
                moveSkeleton();
                handlePickupButton();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                openDoor(0, 1);
                moveSkeleton();
                handlePickupButton();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                openDoor(-1, 0);
                moveSkeleton();
                handlePickupButton();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                openDoor(1, 0);
                moveSkeleton();
                handlePickupButton();
                refresh();
                break;
        }
    }

    private void moveSkeleton() {
        int randomNumber;
        for (Skeleton skeleton : map.getSkeletons()) {
            // Skeletons are created in MapLoader class
            // Skeletons are gathered in the ArrayList in GameMap class
            // I hope it's correct according to MVC model...
            randomNumber = (int) Math.floor(Math.random() * 4);
            // random number is from 0-3 -> calculated for each skeleton in each iteration
            switch (randomNumber) {
                case 0:
                    skeleton.move(0, -1);
                    break;
                case 1:
                    skeleton.move(0, 1);
                    break;
                case 2:
                    skeleton.move(-1, 0);
                    break;
                case 3:
                    skeleton.move(1, 0);
                    break;
                default:
                    throw new RuntimeException("Number must be from 0 to 3");
            }
        }
    }

    private void handlePickupButton() {
        if (map.getPlayer().getCell().hasItem()
                && !map.getPlayer().getCell().getObject().getTileName().equals(CellType.OPEN_DOOR.getTileName())) {
            pickupButton.setVisible(true);
            pickupButton.setOnAction(event -> {
                itemsList.add(map.getPlayer().getCell().getObject().getTileName());
                String objectName = map.getPlayer().getCell().getObject().getTileName();

                switch (objectName) {
                    case "sword":
                        new Sword(map.getCell(28, itemsList.size()));
                        break;
                    case "key":
                        new Key(map.getCell(28, itemsList.size()));
                        break;
                }
                map.getPlayer().getCell().setObject(null);
                pickupButton.setVisible(false);
                refresh();
            });
        } else {
            pickupButton.setVisible(false);
        }
    }

    private void openDoor(int x, int y) {
        if (itemsList.contains("key")
                && map.getPlayer().getCell().getNeighbor(x, y).getTileName().equals(CellType.DOOR.getTileName())) {
            int doorX = map.getPlayer().getCell().getNeighbor(x, y).getX();
            int doorY = map.getPlayer().getCell().getNeighbor(x, y).getY();
            map.getPlayer().getCell().getNeighbor(x, y).setType(CellType.FLOOR);
            map.getPlayer().getCell().getNeighbor(x, y).setObject(null);
            Door openedDoor = new Door(map.getCell(doorX, doorY));
            openedDoor.setOpened(true);
            for (int i = 1; i <= itemsList.size(); i++) {
                if (map.getCell(28, i).getObject().getTileName().equals("key")) {
                    map.getCell(28, i).setObject(null);
                    itemsList.remove("key");
                }
            }
        }
    }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getObject() != null) {
                    Tiles.drawTile(context, cell.getObject(), x, y);

                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
            healthLabel.setText("" + map.getPlayer().getHealth());
            nameLabel.setText("" +  );
        }

    }
}
