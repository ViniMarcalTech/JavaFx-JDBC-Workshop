package gui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.Aniversariantes;
import model.services.AniversariantesService;

public class AniversariantesListController implements Initializable, DataChangeListener {

	private AniversariantesService service;

	@FXML
	private TableView<Aniversariantes> tableViewAniversariantes; 

	@FXML
	private TableColumn<Aniversariantes, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Aniversariantes, String> tableColumnName;
	
	@FXML
	private TableColumn<Aniversariantes, Date> tableColumnBirthDate;
	
	@FXML
	private TableColumn<Aniversariantes, String> tableColumnOm;
	
	@FXML
	private TableColumn<Aniversariantes, String> tableColumnPosto;
	
	@FXML
	private ObservableList<Aniversariantes> obsList;

	public void setAniversariantesService(AniversariantesService service) {
		this.service = service;
	}


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
				
		
		tableColumnOm.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Aniversariantes,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Aniversariantes, String> om) {
			
				return new SimpleStringProperty(om.getValue().getOm().getName());
			}
		});

		tableColumnPosto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Aniversariantes,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Aniversariantes, String> posto) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(posto.getValue().getGrad().getName());
			}
		}	
		);
		
		tableColumnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		Utils.formatTableColumnDate(tableColumnBirthDate, "dd/MM/yyyy");

		
		Stage stage = (Stage) Main.getMainScene().getWindow(); 
		tableViewAniversariantes.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}

		List<Aniversariantes> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewAniversariantes.setItems(obsList);
		

	}


	@Override
	public void onDataChanged() {
		updateTableView();
	}

	



}
