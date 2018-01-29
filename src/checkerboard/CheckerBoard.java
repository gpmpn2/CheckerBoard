/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Grant
 * 
 * Use an anchor pane and place the rectangles by coordinate with math
 */
public class CheckerBoard {
    
    private int numRows;
    private int numCols;
    private double boardWidth;
    private double boardHeight;
    
    private Color lightColor;
    private Color darkColor;
    
    private AnchorPane root;
    
    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }
    
    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight, Color lightColor, Color darkColor) {
        this(numRows, numCols, boardWidth, boardHeight);
        this.lightColor = lightColor;
        this.darkColor = darkColor;
    }
    
    public AnchorPane build() {

        AnchorPane anchorPane = new AnchorPane();
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color color;
                if(row % 2 == 0) {
                    color = (col % 2 == 0 ? Color.RED : Color.BLACK);
                } else {
                    color = (col % 2 == 0 ? Color.BLACK : Color.RED);
                }
                
                Rectangle rect = new Rectangle(getRectangleWidth(), getRectangleHeight(), color);
                anchorPane.setTopAnchor(rect,row * getRectangleHeight());
                anchorPane.setLeftAnchor(rect,col * getRectangleWidth());
                anchorPane.getChildren().add(rect);
            }
        }
        
        root = anchorPane;
        
        return anchorPane;
    }
    
    public AnchorPane getRoot() {
        return root;
    }
    
    public int getNumRows(){
        return numRows;
    }
    
    public int getNumCols() {
        return numCols;
    }
    
    public Color getDarkColor(){
        return darkColor;
    }
    
    public Color getLightColor() {
        return lightColor;
    }
    
    public double getRectangleWidth() {
        return getWidth() / numCols;
    }
    
    public double getRectangleHeight() {
        return getHeight() / numRows;
    }
    
    public double getWidth(){
        return boardWidth;
    }
    
    public double getHeight(){
        return boardHeight;
    }
}
