package mainPackage;

import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.geometry.Orientation;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

import static mainPackage.homeScene.orangeBackground;
import static mainPackage.questionioScene.dropShadow;

public class statGUI extends Scene {
    Scene linePage; // All scenes
    Scene leaderboard;
    TabPane tabpane = new TabPane();

    public static LineChart<String, Number> create_line_chart_player_history(String user_id) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Score");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Score over time (Player)");

        history[] player_history = Statistics.get_player_history(user_id);
        XYChart.Series series = new XYChart.Series();
        series.setName("Date/Score");

        // line chart data
        for (int i = 0; i < player_history.length; i++) {
            series.getData().add(new XYChart.Data<>(player_history[i].getDate(), player_history[i].getScore_of_round()));
        }
        // line chart data
        lineChart.getData().add(series);
        return(lineChart);
    }

    public static LineChart<String, Number> create_line_chart_population_history() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Score");
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Score over time (population mainPackage.history)");
        history[] pop_history = Statistics.get_all_history();
        XYChart.Series series = new XYChart.Series();
        series.setName("Date/Score");
        // line chart data
        for (int i = 0; i < pop_history.length; i++) {
            series.getData().add(new XYChart.Data<>(pop_history[i].getDate(), pop_history[i].getScore_of_round()));
        }
        // line chart data
        lineChart.getData().add(series);
        return(lineChart);
    }

    public statGUI(Stage primaryStage) {
        super(new VBox(), 440, 250);
        String user_id = launcher.user_ID;

        // create vertical seperator
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        Insets offset = new Insets(10, 10, 10, 10);


        // x axis is date
        // y axis is score
        //
        // line chart


        GridPane chatLayout = getChartGrid(offset, separator, user_id);
        chatLayout.setStyle("-fx-background-color: #FFD966;");
        // create table view
        TableView table = new TableView();
        // make table not editable
        table.setEditable(false);

        // set table width
        table.setPrefWidth(300);

        // make sure table has 2 columns
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn userNameCol = new TableColumn("Username");
        userNameCol.setStyle("-fx-background-color: #FFE699;");
        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setStyle("-fx-background-color: #FBE29B;");
        table.getColumns().addAll(userNameCol, scoreCol);

        // create table data
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("user_ID"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score_of_round"));
        history[] all_history = Statistics.get_all_history();
        for (int i = 0; i < all_history.length; i++) {
            table.getItems().add(all_history[i]);
        }

        GridPane leaderboardLay = new GridPane();
        leaderboardLay.setStyle("-fx-background-color: #FFD966;");
        // add table to the layout
        leaderboardLay.setConstraints(table, 0, 0);
        leaderboardLay.getChildren().add(table);

        // when user clicks on a row, show the line chart for that user on the right
        table.setOnMouseClicked(e -> {
            // remove the line chart from the previous layout if it exists
            leaderboardLay.getChildren().removeIf(node -> node instanceof LineChart);
            // get the selected row
            System.out.println("clicked on " + table.getSelectionModel().getSelectedItem());
            // get the selected row c
            history selectedRow = (history) table.getSelectionModel().getSelectedItem();
            // get the user id of the selected row
            String row_user_id = selectedRow.getUser_ID();
            // create a line chart for that user
            LineChart<String, Number> lineChart = create_line_chart_player_history(row_user_id);
            // add the line chart to the layout
            leaderboardLay.setConstraints(lineChart, 1, 0);
            leaderboardLay.getChildren().add(lineChart);

        });


        // create stats table, will contain stats for the user (mean, median, standard deviation)
        TableView statsTable = new TableView();
        statsTable.setStyle("-fx-background-color: #FFD966;");
        // make table not editable
        statsTable.setEditable(false);

        // set table width
        statsTable.setPrefWidth(300);

        // make sure table has 2 columns
        statsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn statNameCol = new TableColumn("Stat");
        statNameCol.setStyle("-fx-background-color: #FFD966;");
        TableColumn statValueCol = new TableColumn("Value");
        statValueCol.setStyle("-fx-background-color: #FFD966;");
        statsTable.getColumns().addAll(statNameCol, statValueCol);

        // create table data
        statNameCol.setCellValueFactory(new PropertyValueFactory<>("type_of_stat"));
        statValueCol.setCellValueFactory(new PropertyValueFactory<>("value"));


        stat[] player_stat_data = Statistics.createPlayerStats(user_id);

        for (int i = 0; i < player_stat_data.length; i++) {
            statsTable.getItems().add(player_stat_data[i]);
        }

        // add table to the layout
        GridPane personalStatLayout = new GridPane();
        personalStatLayout.setStyle("-fx-background-color: #FFD966;");
        personalStatLayout.setConstraints(statsTable, 0, 0);
        personalStatLayout.setAlignment(Pos.CENTER);
        personalStatLayout.getChildren().add(statsTable);

        //---------------------------------------------------------------------------------------------------------------------//


        Button backButton = new Button("Back");
        homeScene.buttonCosmetics(backButton, orangeBackground, dropShadow, 10);
        backButton.setOnAction(e -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(mainmenuScene.createScene(stage));
            primaryStage.setFullScreen(true);
        });

        Tab tab1 = new Tab("Leaderboard");
        tabCreation(tab1, leaderboardLay);

        Tab tab2 = new Tab("Your Graphs");
        tabCreation(tab2, chatLayout);

        Tab tab3 = new Tab("Your Stats");
        tabCreation(tab3, statsTable);



        // anchor pane
        AnchorPane anchorPane = new AnchorPane();

        // add button to the right of the tabplane
        AnchorPane.setTopAnchor(tabpane, 2.0);
        AnchorPane.setLeftAnchor(tabpane, 2.0);
        AnchorPane.setRightAnchor(tabpane, 2.0);
        AnchorPane.setTopAnchor(backButton, 5.0);
        AnchorPane.setRightAnchor(backButton, 5.0);
//        tabpane.setStyle("-fx-padding: 2 0 0 50;");

        anchorPane.getChildren().addAll(tabpane, backButton);

        // -----------------------------------------------------------------------------------------------//
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(anchorPane);

    }

    private static GridPane getChartGrid(Insets offset, Separator separator, String user_id) {
        LineChart<String, Number> lineChart_player = create_line_chart_player_history(user_id);
        LineChart<String, Number> lineChart_pop = create_line_chart_population_history();
        //You cannot use the same button on different scenes//


        //home layout
        GridPane pageLay = new GridPane();
        //General layout settings
        pageLay.setPadding(offset);
        pageLay.setVgap(10);
        pageLay.setHgap(5);
        pageLay.setAlignment(Pos.CENTER);
        pageLay.setConstraints(lineChart_player, 0, 2);
        pageLay.setConstraints(separator, 1, 2);
        pageLay.setConstraints(lineChart_pop, 2, 2);
        pageLay.getChildren().addAll(lineChart_player, separator, lineChart_pop);
        pageLay.setPrefSize(1080, 1000);

        return pageLay;
    }

    public static statGUI createScene(Stage primaryStage) {
        return new statGUI(primaryStage);
    }

    public void tabCreation(Tab tab, javafx.scene.Node node){
        tab.setContent(node);
        tab.closableProperty().setValue(false);
        tabpane.getTabs().add(tab);
    }
}