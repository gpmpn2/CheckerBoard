/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author grant
 */
public class CheckerBoardFXMLController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private VBox vBox;
    
    @FXML
    private MenuItem sixteen;
    
    @FXML
    private MenuItem ten;
    
    @FXML
    private MenuItem eight;
    
    @FXML
    private MenuItem three;
    
    @FXML
    private MenuItem defaultColor;
    
    @FXML
    private MenuItem blueColor;
    
    private double checkerBoardHeight;
    private double checkerBoardWidth;
    
    private int numCols;
    private int numRows;
    
    private Color lightColor;
    private Color darkColor;
    
    private static final int DEFAULT_NUM_ROWS = 8;
    private static final int DEFAULT_NUM_COLS = 8;
    private static final Color DEFAULT_COLOR_LIGHT = Color.RED;
    private static final Color DEFAULT_COLOR_DARK = Color.BLACK;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numCols = DEFAULT_NUM_ROWS;
        numRows = DEFAULT_NUM_COLS;
        
        lightColor = DEFAULT_COLOR_LIGHT;
        darkColor = DEFAULT_COLOR_DARK;
    }    
    
    @FXML
    private void handleBoardSize(ActionEvent event) {
        MenuItem selected = ((MenuItem) event.getSource());
        switch(selected.getId()) {
            case "sixteen":
                resize(16,16);
                break;
            case "ten":
                resize(10,10);
                break;
            case "eight":
                resize(8,8);
                break;
            case "three":
                resize(3,3);
                break;
        }
        
        refresh();
    }
    
    @FXML
    private void handleBoardColor(ActionEvent event) {
        MenuItem selected = ((MenuItem) event.getSource());
        switch(selected.getId()) {
            case "defaultColor":
                recolor(Color.RED, Color.BLACK);
                break;
            case "blueColor":
                recolor(Color.SKYBLUE, Color.DARKBLUE);
                break;
        }
        
        refresh();
    }
    
    public void initialize(Stage stage) {   
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refresh();
        };
        
        stage.widthProperty().addListener(lambdaChangeListener);
        stage.heightProperty().addListener(lambdaChangeListener);

        refresh();
    }
    
    public void refresh() {
        checkerBoardHeight = vBox.getHeight() - menuBar.getHeight();
        checkerBoardWidth = vBox.getWidth();
        
        CheckerBoard cb = new CheckerBoard(numCols, numRows, checkerBoardWidth, checkerBoardHeight, lightColor, darkColor);
        AnchorPane updatedAnchorPane = cb.build();
        
        clear();
        
        anchorPane.getChildren().add(updatedAnchorPane);
        
    }
    
    public void clear() {
        anchorPane.getChildren().clear();
    }
    
    public void resize(int rows, int cols) {
        numCols = cols;
        numRows = rows;
    }
    
    public void recolor(Color lightColor, Color darkColor) {
        this.lightColor = lightColor;
        this.darkColor = darkColor;
    }
}
