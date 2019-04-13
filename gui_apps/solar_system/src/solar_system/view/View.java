package solar_system.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import solar_system.model.Position;
import solar_system.model.SkyBody;

public class View {

    private Canvas canvas;
    private GraphicsContext gc;

    public View(Stage primaryStage) {
        canvas = new Canvas(900, 700);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FLORALWHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        setStaticSceneElements(primaryStage);
    }

    private void setStaticSceneElements(Stage primaryStage) {
        // CONTROL
        Label header = new Label("Solar system control");
        header.setFont(new Font(20));

        Button btn = new Button("Test");
        Button btn2 = new Button("Test2");

        // control
        VBox right = new VBox(10);
        right.setAlignment(Pos.TOP_RIGHT);
        right.setPadding(new Insets(5));
        right.getChildren().addAll(header, btn, btn2);

        HBox holder = new HBox(5);
        holder.setPadding(new Insets(5));
        holder.getChildren().addAll(canvas, right);

        HBox pane = new HBox();
        pane.setPadding(new Insets(2));
        pane.setStyle("-fx-background-color: white");
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.getChildren().addAll(holder);

        Scene scene = new Scene(pane, 1200, 700);

        primaryStage.setTitle("Solar system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void clearCanvas(){
        gc.clearRect(0, 0, 900, 700);
    }

    public void redrawOrbit(SkyBody body, double scale) {
        double radius = body.getOrbitRadius() * 400 * scale;
        System.out.println(radius);
        gc.strokeOval(
                // @cleanup there's problem with (radius / 2) it is not how it should work
                body.getAnchorPoint().x() + (canvas.getWidth() / 2) - (radius / 2),
                body.getAnchorPoint().y() + (canvas.getHeight() / 2) - (radius / 2),
                radius, radius);
    }

    public void redrawPlanet(SkyBody body, double scale, Position pos) {
        double radius = body.getRadius() * scale;
        System.out.println("Planet redraw - " + body.getName() + ":" + pos);
        double redraw_position_x = pos.x() + canvas.getWidth() / 2 - radius;
        double redraw_position_y = pos.y() + canvas.getHeight() / 2 - radius;
        
        System.out.println("Actual planet coords: " + "\nx: " + redraw_position_x + "\ny: " + redraw_position_y);
                
        gc.strokeOval(redraw_position_x, redraw_position_y, radius * 2, radius * 2);
    }
    
    public void redrawName(SkyBody body, Position pos, double scale){
        double radius = body.getRadius() * scale;
        System.out.println("name redraw - " + body.getName() + ": " + pos);
        double redraw_position_x = pos.x() + canvas.getWidth() / 2 - radius;
        double redraw_position_y = pos.y() + canvas.getHeight() / 2 - radius;
        
        
        gc.strokeText(body.getName(), redraw_position_x + radius * 2, redraw_position_y + radius * 2);
    }
}
