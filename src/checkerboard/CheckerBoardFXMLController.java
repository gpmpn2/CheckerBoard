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
        numCols = 8;
        numRows = 8;
        System.out.println("FIRED");
    }
    
    @FXML
    private void handleBoardColor(ActionEvent event) {
        //TODO
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
    
}
