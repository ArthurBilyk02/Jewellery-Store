module com.example.arthur_ca1 {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.example.arthur_ca1 to javafx.fxml;
	exports com.example.arthur_ca1;
}