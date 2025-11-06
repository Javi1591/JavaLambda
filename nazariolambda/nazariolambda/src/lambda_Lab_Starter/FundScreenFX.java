package lambda_Lab_Starter;

import java.util.function.Predicate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FundScreenFX extends Application {

    public void start(Stage primaryStage) {

        Portfolio portfolio = new Portfolio();
        System.out.println("Size : " + portfolio.size());
        TextArea textArea = new TextArea();
        Button btKeepByTicker = new Button("Ticker");
        Button btKeepByHoldingSize = new Button("Holding Size");
        Button btKeepByMinimumInvestment = new Button("Min Invest");
        Button btKeepByValue = new Button("Value");
        Button btKeepByLocation = new Button("Location");

        Button btExcludeByTicker = new Button("Ticker");
        Button btExcludeByHoldingSize = new Button("Holding Size");
        Button btExcludeByMinimumInvestment = new Button("Min Invest");
        Button btExcludeByValue = new Button("Value");
        Button btExcludeByLocation = new Button("Location");

        HBox hBoxTopLeft = new HBox(new Label("Keep by"), btKeepByTicker, btKeepByHoldingSize,
                btKeepByMinimumInvestment, btKeepByValue, btKeepByLocation);
        hBoxTopLeft.setSpacing(10);
        hBoxTopLeft.setAlignment(Pos.CENTER);
        hBoxTopLeft.setPadding(new Insets(5, 5, 5, 5));

        HBox hBoxTopRight = new HBox(new Label("Exclude by"), btExcludeByTicker, btExcludeByHoldingSize,
                btExcludeByMinimumInvestment, btExcludeByValue, btExcludeByLocation);
        hBoxTopRight.setSpacing(10);
        hBoxTopRight.setAlignment(Pos.CENTER);
        hBoxTopRight.setPadding(new Insets(5, 5, 5, 5));

        GridPane gridPaneTop = new GridPane();
        gridPaneTop.add(hBoxTopLeft, 0, 0);
        gridPaneTop.add(hBoxTopRight, 1, 0);

        Button btListPortfolio = new Button("List Portfolio");
        Button btResetPortfolio = new Button("Reset Portfolio");
        TextField txTicker = new TextField();
        txTicker.setPrefWidth(50);
        TextField txFloor = new TextField();
        txFloor.setPrefWidth(50);
        TextField txCeiling = new TextField();
        txCeiling.setPrefWidth(50);

        ChoiceBox<String> cbdomicile = new ChoiceBox<>();
        cbdomicile.getItems().addAll("Domestic", "International", "Global");
        cbdomicile.getSelectionModel().select(0);
        Button btClearAnswers = new Button("Clear");
        Button btnExit = new Button("Exit");

        HBox hBoxBottom = new HBox(btListPortfolio, btResetPortfolio, new Label("Ticker"), txTicker, new Label("Floor"),
                txFloor, new Label("Ceiling"), txCeiling, new Label("Location"), cbdomicile, btClearAnswers, btnExit);
        hBoxBottom.setSpacing(10);
        hBoxBottom.setAlignment(Pos.CENTER);
        hBoxBottom.setPadding(new Insets(5, 5, 5, 5));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(gridPaneTop);
        borderPane.setBottom(hBoxBottom);
        borderPane.setCenter(textArea);

        btListPortfolio.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String output = "Current Portfolio\n";
                for (int i = 0; i < portfolio.size(); i++) {
                    output += "[" + i + "] " + portfolio.get(i) + "\n";
                }
                textArea.setText(output);
            }
        });

        btResetPortfolio.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                portfolio.refresh();
                btListPortfolio.fire();
            }
        });

        // Keep by Ticker (Using Predicate)
        btKeepByTicker.setOnAction(e -> {
            String ticker = txTicker.getText().toUpperCase();
            Predicate<MutualFund> filter = fund -> !fund.getTicker().equalsIgnoreCase(ticker);
            portfolio.removeIf(filter);
            btListPortfolio.fire();
        });
        
        // Keep by Holding Size (Using Predicate)
        btKeepByHoldingSize.setOnAction(e -> {
            if (txFloor.getText().isEmpty()) {
                textArea.setText("Enter a Holding Size floor value.");
                return;
            }
            double floor = Double.parseDouble(txFloor.getText());
            Predicate<MutualFund> filter = fund -> fund.getAvgHoldingSize() < floor;
            portfolio.removeIf(filter);
            btListPortfolio.fire();
        });
        
        // Keep by Minimum Investment (Using Predicate)
        btKeepByMinimumInvestment.setOnAction(e -> {
            if (txFloor.getText().isEmpty()) {
                textArea.setText("Enter a Minimum Investment floor value.");
                return;
            }
            double floor = Double.parseDouble(txFloor.getText());
            Predicate<MutualFund> filter = fund -> fund.getMinimumInvestment() < floor;
            portfolio.removeIf(filter);
            btListPortfolio.fire();
        });

        // Keep by Value (Using Predicate)
        btKeepByValue.setOnAction(e -> {
            if (txFloor.getText().isEmpty() || txCeiling.getText().isEmpty()) {
                textArea.setText("Enter Floor and Ceiling values.");
                return;
            }
            double floor = Double.parseDouble(txFloor.getText());
            double ceiling = Double.parseDouble(txCeiling.getText());
            Predicate<MutualFund> filter = fund -> fund.getValueMeasure() < floor || fund.getValueMeasure() > ceiling;
            portfolio.removeIf(filter);
            btListPortfolio.fire();
        });
        
        // Keep by Location (Using Predicate)
        btKeepByLocation.setOnAction(e -> {
            MutualFund.MARKET mkt = MutualFund.MARKET.valueOf(cbdomicile.getValue().toUpperCase());
            Predicate<MutualFund> filter = fund -> fund.getDomicile() != mkt;
            portfolio.removeIf(filter);
            btListPortfolio.fire();
        });

        // Exclude by Ticker (Using Lambda)
        btExcludeByTicker.setOnAction(e -> {
            String ticker = txTicker.getText().toUpperCase();
            portfolio.removeIf(fund -> fund.getTicker().equalsIgnoreCase(ticker));
            btListPortfolio.fire();
        });
        
        // Exclude by Holding Size (Using Lambda)
        btExcludeByHoldingSize.setOnAction(e -> {
            if (txFloor.getText().isEmpty()) {
                textArea.setText("Enter a Holding Size floor value.");
                return;
            }
            double floor = Double.parseDouble(txFloor.getText());
            portfolio.removeIf(fund -> fund.getAvgHoldingSize() >= floor);
            btListPortfolio.fire();
        });

        // Exclude by Minimum Investment (Using Lambda)
        btExcludeByMinimumInvestment.setOnAction(e -> {
            if (txFloor.getText().isEmpty()) {
                textArea.setText("Enter a Minimum Investment floor value.");
                return;
            }
            double floor = Double.parseDouble(txFloor.getText());
            portfolio.removeIf(fund -> fund.getMinimumInvestment() >= floor);
            btListPortfolio.fire();
        });

        // Exclude by Value (Using Lambda)
        btExcludeByValue.setOnAction(e -> {
            if (txFloor.getText().isEmpty() || txCeiling.getText().isEmpty()) {
                textArea.setText("Enter Floor and Ceiling values.");
                return;
            }
            double floor = Double.parseDouble(txFloor.getText());
            double ceiling = Double.parseDouble(txCeiling.getText());
            portfolio.removeIf(fund -> fund.getValueMeasure() >= floor && fund.getValueMeasure() <= ceiling);
            btListPortfolio.fire();
        });

        // Exclude by Location (Using Lambda)
        btExcludeByLocation.setOnAction(e -> {
            MutualFund.MARKET mkt = MutualFund.MARKET.valueOf(cbdomicile.getValue().toUpperCase());
            portfolio.removeIf(fund -> fund.getDomicile() == mkt);
            btListPortfolio.fire();
        });

        // Clear button
        btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                txTicker.clear();
                txFloor.clear();
                txCeiling.clear();
                textArea.clear();
            }
        });

        // Exit button
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        // Scene setup
        Scene scene = new Scene(borderPane, 900, 350);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Image myIcon = new Image(getClass().getResourceAsStream("SP.png"));
        primaryStage.getIcons().add(myIcon);
        primaryStage.setTitle("Fund Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support. Not
     * needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
