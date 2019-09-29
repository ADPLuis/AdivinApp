package AdivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	private Label headLabel;
	private Button checkButton;
	private TextField texto;
	int intentos = 0;
	int goal = (int) (Math.random() * 100) + 1;
	int num;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		headLabel = new Label("Introduce un número entre 0 y 100");

		texto = new TextField();
		texto.setMaxWidth(150);

		checkButton = new Button("Comprobar");
		checkButton.setDefaultButton(true);
		checkButton.setOnAction(e -> onCheckButtonAction(e));

		VBox root = new VBox();
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(headLabel, texto, checkButton);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void onCheckButtonAction(ActionEvent e) {
		// TODO Auto-generated method stub
		String intento = texto.getText();

		Alert invAlert = new Alert(AlertType.ERROR);
		invAlert.setTitle("AdivinApp");
		invAlert.setHeaderText("Texto no válido");
		invAlert.setContentText("El número introducido no es válido ");

		try {
			num = Integer.parseInt(intento);
		} catch (NumberFormatException excp) {
			invAlert.showAndWait();
		}

		Alert rangoInvAlert = new Alert(AlertType.ERROR);
		rangoInvAlert.setTitle("Adivinapp");
		rangoInvAlert.setHeaderText("Error");
		rangoInvAlert.setContentText("Introduce un numero entre 0 y 100, ni mayor, ni menor.");

		Alert winAlert = new Alert(AlertType.INFORMATION);
		winAlert.setTitle("AdivinApp");
		winAlert.setHeaderText("¡Has ganado!");
		winAlert.setContentText("Solo has necesitado " + intentos + " intentos. \n\n ¡Vuelve a jugar y hazlo mejor!");

		Alert menorAlert = new Alert(AlertType.WARNING);
		menorAlert.setTitle("Adivinapp");
		menorAlert.setHeaderText("¡Has fallado!");
		menorAlert.setContentText("El numero a adivinar es mayor que " + num + "\n\n Vuelve a intentarlo.");

		Alert mayorAlert = new Alert(AlertType.WARNING);
		mayorAlert.setTitle("Adivinapp");
		mayorAlert.setHeaderText("¡Has fallado!");
		mayorAlert.setContentText("El numero a adivinar es menor que " + num + "\n\n Vuelve a intentarlo");
		
		//comprobacion

		if (num > 100 || num < 0) {
			intentos++;
			rangoInvAlert.showAndWait();
		} else if (num == goal) {
			winAlert.showAndWait();
			intentos = 0;
			goal = (int) (Math.random() * 100) + 1;
		} else if (num < goal) {
			intentos++;
			menorAlert.showAndWait();
		} else if (num > goal) {
			intentos++;
			mayorAlert.showAndWait();
		}
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
