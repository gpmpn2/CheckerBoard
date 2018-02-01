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
    private static final int STAGE_OFFSET = 14;
    
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Sets our default values for when the board initially loads
        numCols = DEFAULT_NUM_ROWS;
        numRows = DEFAULT_NUM_COLS;
        
        lightColor = DEFAULT_COLOR_LIGHT;
        darkColor = DEFAULT_COLOR_DARK;
    }    
    
    @FXML
    private void handleBoardSize(ActionEvent event) {
        //Gets the option the user clicked
        MenuItem selected = ((MenuItem) event.getSource());
        
        //Switch statement to see what size to set
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
        //Gets the option the user clicked
        MenuItem selected = ((MenuItem) event.getSource());
        
        //Switch to see what color to set the board to
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
    
    public void load(Stage stage) {  
        this.stage = stage;
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refresh();
        };
        
        //Add's width and height listeners to tell the board when to refresh
        stage.widthProperty().addListener(lambdaChangeListener);
        stage.heightProperty().addListener(lambdaChangeListener);
    
        //Need a one time refresh right after loading
        refresh();
        
        //Sets the stage to a square size
        stage.setWidth(anchorPane.getMinWidth() + STAGE_OFFSET);
    }
    
    /**
     * Refreshes our checkerboard
     */
    public void refresh() {
        //Get the height of the vbox - the height of the menu bar so we can calculate the square size
        checkerBoardHeight = vBox.getHeight() - menuBar.getHeight();
        
        //Just the width of the vBox
        checkerBoardWidth = vBox.getWidth();
        
        CheckerBoard cb = new CheckerBoard(numCols, numRows, checkerBoardWidth, checkerBoardHeight, lightColor, darkColor);
        AnchorPane updatedAnchorPane = cb.build();
        
        //Set our new anchor pane
        anchorPane = updatedAnchorPane;
        
        //Clear our vbox
        clear();

        //Add back our menuBar and add our new anchorpane
        vBox.getChildren().add(menuBar);
        vBox.getChildren().add(anchorPane);
    }
    
    /**
     * Clears the VBox
     */
    public void clear() {
        vBox.getChildren().clear();
    }
    
    /**
     * Resizes the rows and columns when called
     * @param rows
     * @param cols 
     */
    public void resize(int rows, int cols) {
        numCols = cols;
        numRows = rows;
    }
    
    /**
     * Set the light and dark colors
     * @param lightColor
     * @param darkColor 
     */
    public void recolor(Color lightColor, Color darkColor) {
        this.lightColor = lightColor;
        this.darkColor = darkColor;
    }
}
