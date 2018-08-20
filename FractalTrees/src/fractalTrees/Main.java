package fractalTrees;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{
	private Canvas canvas;
	private FractalTree tree;
	private TreeControls controls;
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	
	public Main()
	{
		canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		tree = new FractalTree((int) canvas.getWidth() / 2, (int) canvas.getHeight());
		controls = new TreeControls(tree);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		
		gc.beginPath();
		
		tree.draw(gc);
		gc.stroke();
		
		gc.closePath();
		
		primaryStage.setTitle("Fractal Trees");
		
		BorderPane root = new BorderPane();
		
		controls.setParent(this);
		
		GridPane canvasPane = new GridPane();
		canvasPane.add(canvas, 0, 0);
		canvasPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		canvasPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		GridPane controls = new GridPane();
		controls.add(this.controls.getBaLabel(), 0, 1);
		controls.add(this.controls.getBbLabel(), 0, 2);
		controls.add(this.controls.getLrLabel(), 0, 3);
		
		controls.add(this.controls.getBranchAngle(), 1, 1);
		controls.add(this.controls.getBranchBalance(), 1, 2);
		controls.add(this.controls.getLengthRefactor(), 1, 3);
		
		root.setCenter(canvasPane);
		root.setBottom(controls);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		DoubleBinding binding = root.heightProperty().subtract(root.bottomProperty().getValue().getBoundsInParent().getHeight());
		canvas.heightProperty().bind(binding);
		canvas.widthProperty().bind(root.widthProperty());
		
		
	}
	
	public void redraw()
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.beginPath();
		tree.draw(gc);
		gc.closePath();
		gc.stroke();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
