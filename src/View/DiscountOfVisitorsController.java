package View;

import Model.Visitor;
import Model.Zoo;
import Utils.TicketType;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DiscountOfVisitorsController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Label title;

	@FXML
	private Button Display;

	@FXML
	private TableView<Visitor> table;

	@FXML
	private TableColumn<Visitor, String> lastName;

	@FXML
	private TableColumn<Visitor, String> Name;

	@FXML
	private TableColumn<Visitor, TicketType> ticketType;

	@FXML
	private TableColumn<Visitor, Double> discount;

	@FXML
	private TableColumn<Visitor, Double> visitorDiscount;

	ObservableList<Visitor> tableList = FXCollections.observableArrayList();

	// query of discounts- shows all ticket details
	@FXML
	void submit(ActionEvent event) {
		try {
			tableList.clear();
			tableList.addAll(zoo.geAllDiscountAmount().keySet());
			Name.setCellValueFactory(new PropertyValueFactory<Visitor, String>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<Visitor, String>("LastName"));
			discount.setCellValueFactory(
					cellData -> new ReadOnlyObjectWrapper<Double>(zoo.geAllDiscountAmount().get(cellData.getValue())));
			ticketType.setCellValueFactory(new PropertyValueFactory<Visitor, TicketType>("ticket"));
			visitorDiscount.setCellValueFactory(new PropertyValueFactory<Visitor, Double>("discount"));
			table.setItems(tableList);
		} catch(NullPointerException n) {
			table.setVisible(true);
		}

	}

}
