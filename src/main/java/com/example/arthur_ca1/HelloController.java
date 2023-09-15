package com.example.arthur_ca1;

import Classes.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;


public class HelloController {
	// Linked-Lists
	public LinkedListImpl<DisplayCase> DisplayCases;
	private LinkedListImpl<MaterialComponent> materials;
	private LinkedListImpl<DisplayTray> trays;
	private LinkedListImpl<JewelleryItem> jewlery;

	// Jewelry components
	@FXML
	private ComboBox<DisplayTray> selectedTray;
	@FXML
	private ComboBox<MaterialComponent> MaterialJewelery;
	@FXML
	private Button addJeweleryItem;
	@FXML
	private TextArea description;
	@FXML
	private RadioButton genderJewelry;
	@FXML
	private MenuButton typeJewelry;

	@FXML
	private TextField costField;
	@FXML
	private ListView<JewelleryItem> jewelryListView;

	// Display case components
	@FXML
	private Button addDisplayCase;
	@FXML
	private TextField identifierDisplayCase;
	@FXML
	private RadioButton isLit;
	@FXML
	private RadioButton isFreeStanding;
	@FXML
	private ListView<DisplayCase> displayCaseListView;

	// Display tray components
	@FXML
	private Button addDisplayTray;

	@FXML
	private ComboBox<DisplayCase> selectedCase;
	@FXML
	private TextField identifierDisplayTray;
	@FXML
	private TextField inlayColour;
	@FXML
	private TextField width;
	@FXML
	private TextField depth;
	@FXML
	private ListView<DisplayTray> displayTrayListView;

	// Components and materials
	@FXML
	private Button addComponentsAndMaterials;
	@FXML
	private TextField componentName;
	@FXML
	private TextArea componentDescription;
	@FXML
	private TextField componentWeight;
	@FXML
	private TextField componentQuality;

	@FXML
	private ListView listView;
	@FXML
	private ListView<MaterialComponent> materialComponentListView;

	//Delete Buttons
	@FXML
	private Button jewleryDeleteButton;
	@FXML
	private Button displayCaseDeleteButton;
	@FXML
	private Button trayDeleteButton;
	@FXML
	private Button componentDeleteButton;

	//Stock Items
	@FXML
	private Label costLabel;

	@FXML
	private TableView StockTableView;
	@FXML
	private TextField searchField;

	// This method is called after all @FXML annotated members have been injected.
	@FXML
	public void initialize() {
		for (MenuItem menuItem : typeJewelry.getItems()) {
			menuItem.setOnAction(event -> {
				typeJewelry.setText(menuItem.getText());
			});
		}
	}
	@FXML
	private void onResetClick(){
		DisplayCases.head = null;
		jewlery.head = null;
		materials.head = null;
		trays.head = null;
		populateTable(jewlery);
		jewelryListView.refresh();
		materialComponentListView.refresh();
		displayCaseListView.refresh();
		displayTrayListView.refresh();
	}
	@FXML
	private void onSearchClick(){
		String searchParam = searchField.getText();
			LinkedListImpl<JewelleryItem> searchResults = new LinkedListImpl<>();
			LinkedListImpl.Node current = jewlery.head.getNextNode();
			while (current != null) {
				JewelleryItem jeweleryItem = (JewelleryItem) current.getData();
				if (jeweleryItem.toString().contains(searchParam)) {
					searchResults.add(jeweleryItem);
				}
				current = current.getNextNode();
			}
			populateTable(searchResults);

	}

	@FXML
	private void onJewleryAddClick() {
		DisplayTray selected = selectedTray.getValue();
		String desc = description.getText();
		String type = typeJewelry.getText();
		double cost = Double.parseDouble(costField.getText());
		boolean gender = genderJewelry.isSelected();
		MaterialComponent material = (MaterialComponent) MaterialJewelery.getValue();
		JewelleryItem newJewelry = new JewelleryItem(desc, type, gender, cost);
		newJewelry.addMaterial(material);
		if(this.jewlery == null){
			this.jewlery = new LinkedListImpl<JewelleryItem>();
			jewlery.add(newJewelry);

		}else {
			jewlery.add(newJewelry);
		}
		jewelryListView.getItems().add(newJewelry);
		if(selectedTray.getValue() != null){
			selectedTray.getValue().addItem(newJewelry);
		}
		selectedTray.setValue(null);
		description.setText("");
		typeJewelry.setText("");
		genderJewelry.setText("");
		populateTable(jewlery);
	}

	@FXML
	private void onDisplayTrayAddClick() {
		DisplayCase SelectedCase = selectedCase.getValue();
		String displayTrayId = identifierDisplayTray.getText();
		String inlayColourStr = inlayColour.getText();
		int widthInt = Integer.parseInt(width.getText());
		int depthInt = Integer.parseInt(depth.getText());
		DisplayTray newTray = new DisplayTray(displayTrayId, inlayColourStr, widthInt * depthInt);
		if(this.trays == null){
			this.trays = new LinkedListImpl<DisplayTray>();
			trays.add(newTray);
		}else {
			trays.add(newTray);
		}
		displayTrayListView.getItems().add(newTray);
		selectedCase.setValue(null);
		identifierDisplayTray.setText("");
		inlayColour.setText("");
		width.setText("");
		depth.setText("");
		SelectedCase.addTray(newTray);
		updateSelectedTrayComboBox();

	}
	@FXML
	private void onDisplayCaseAddClick() {
		String id = identifierDisplayCase.getText();
		boolean isLitBool = isLit.isSelected();
		boolean isFreeStandingBool = isFreeStanding.isSelected();
		DisplayCase newDisplayCase = new DisplayCase(id, isFreeStandingBool, isLitBool);  // Assuming DisplayCase constructor takes an ID as a string
		if(this.DisplayCases == null){
			this.DisplayCases = new LinkedListImpl<DisplayCase>();
			DisplayCases.add(newDisplayCase);
		}else {
			DisplayCases.add(newDisplayCase);
		}
		displayCaseListView.getItems().add(newDisplayCase);
		updateSelectedCaseComboBox();
		identifierDisplayCase.setText("");
		isLit.setSelected(false);
		isFreeStanding.setSelected(false);
	}

	@FXML
	private void onComponentsAndMaterialsAddClick() {
		String name = componentName.getText();
		String desc = componentDescription.getText();
		String quality = componentQuality.getText();
		String weight = componentWeight.getText();
		MaterialComponent newMaterial = new MaterialComponent(name, desc, quality, weight); // Assuming MaterialComponent constructor takes description and type as strings
		if(this.materials == null){
			this.materials = new LinkedListImpl<MaterialComponent>();
			materials.add(newMaterial);
		}else {
			materials.add(newMaterial);
		}
		materialComponentListView.getItems().add(newMaterial);
		updateMaterialJewelleryComboBox();
		description.setText("");
		componentQuality.setText("");
		componentWeight.setText("");
		componentQuality.setText("");
	}

	private void updateSelectedCaseComboBox() {
		// Clear the current items in the ComboBox
		selectedCase.getItems().clear();

		// For each item in the LinkedLists, add the item to the corresponding ComboBox
		for(int i = 1; i <= DisplayCases.getLength(); i++) {
			selectedCase.getItems().add(DisplayCases.getEntry(i));
		}
	}

	private void updateMaterialJewelleryComboBox() {
		// Clear the current items in the ComboBox
		MaterialJewelery.getItems().clear();

		// For each item in the LinkedLists, add the item to the corresponding ComboBox
		for(int i = 1; i <= materials.getLength(); i++) {
			MaterialJewelery.getItems().add(materials.getEntry(i));
		}
	}

	private void updateSelectedTrayComboBox() {
		// Clear the current items in the ComboBox
		selectedTray.getItems().clear();

		// For each item in the LinkedLists, add the item to the corresponding ComboBox
		for(int i = 1; i <= trays.getLength(); i++) {
			selectedTray.getItems().add(trays.getEntry(i));
		}
	}
	@FXML
	private void onJewelryDeleteButtonClick(ActionEvent event) {
		int indexToDelete = jewelryListView.getSelectionModel().getSelectedIndex();
		if(indexToDelete >= 0) {
			jewlery.remove(indexToDelete);
			jewelryListView.getItems().remove(indexToDelete);
		}
		populateTable(jewlery);
	}

	@FXML
	private void onDisplayCaseDeleteButtonClick(ActionEvent event) {
		int indexToDelete = displayCaseListView.getSelectionModel().getSelectedIndex();
		if(indexToDelete >= 0) {
			DisplayCases.remove(indexToDelete);
			displayCaseListView.getItems().remove(indexToDelete);
		}
	}

	@FXML
	private void onDisplayTrayDeleteButtonClick(ActionEvent event) {
		int indexToDelete = displayTrayListView.getSelectionModel().getSelectedIndex();
		if(indexToDelete >= 0) {
			trays.remove(indexToDelete);
			displayTrayListView.getItems().remove(indexToDelete);
		}
	}

	@FXML
	private void onComponentDeleteButtonClick(ActionEvent event) {
		int indexToDelete = materialComponentListView.getSelectionModel().getSelectedIndex();
		if(indexToDelete >= 0) {
			materials.remove(indexToDelete);
			materialComponentListView.getItems().remove(indexToDelete);
		}
	}
	//TODO: Table View

	public void populateTable(LinkedListImpl<JewelleryItem> linkedList) {
		// Clear existing data in the TableView
		StockTableView.getItems().clear();

		// Get the columns from the TableView
		TableColumn<JewelleryItem, String> typeColumn = (TableColumn<JewelleryItem, String>) StockTableView.getColumns().get(0);
		TableColumn<JewelleryItem, String> descriptionColumn = (TableColumn<JewelleryItem, String>) StockTableView.getColumns().get(1);
		TableColumn<JewelleryItem, String> genderColumn = (TableColumn<JewelleryItem, String>) StockTableView.getColumns().get(2);
		TableColumn<JewelleryItem, String> materialColumn = (TableColumn<JewelleryItem, String>) StockTableView.getColumns().get(3);
		TableColumn<JewelleryItem, String> costColumn = (TableColumn<JewelleryItem, String>) StockTableView.getColumns().get(4);

		// Set the cell value factories for each column
		typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
		descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
		genderColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isGender()).asString());
		//materialColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMaterials()));
		costColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCost())));

		// Iterate over the linked list and add each JewelleryItem to the TableView
		for (int i = 1; i <= linkedList.getLength(); i++) {
			JewelleryItem item = linkedList.getEntry(i);
			StockTableView.getItems().add(item);
		}
	}

}
