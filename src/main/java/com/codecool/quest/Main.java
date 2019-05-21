package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    static Button pickupButton = new Button("Pick up item");
    List<String> itemsList = new ArrayList<>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        pickupButton.setVisible(false);
        ui.add(pickupButton, 1, 1);

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
            case UP:
                map.getPlayer().move(0, -1);
                handlePickupButton();

                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                handlePickupButton();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                handlePickupButton();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                handlePickupButton();
                refresh();
                break;
        }
    }

    private void handlePickupButton() {
        if (map.getPlayer().getCell().hasItem()) {
            pickupButton.setVisible(true);
            pickupButton.setOnAction(event -> {
                itemsList.add(map.getPlayer().getCell().getObject().getTileName());
                printItems();
                map.getPlayer().getCell().setObject(null);
            });

        } else {
            pickupButton.setVisible(false);
        }
    }

    private void printItems(){
        for (String item:itemsList) {
            System.out.println(item);
        }
        System.out.println("+++++");
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
            healthLabel.setText("" + map.getPlayer().

                    getHealth());

        }

    }
}
