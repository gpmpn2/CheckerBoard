/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    
    private double rectangleWidth;
    private double rectangleHeight;
    
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
        
        setDimensions();
                        
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumCols(); col++) {
                Color color;
                if(row % 2 == 0) {
                    color = (col % 2 == 0 ? getLightColor(): getDarkColor());
                } else {
                    color = (col % 2 == 0 ? getDarkColor() : getLightColor());
                }
                
                Rectangle rect = new Rectangle(getRectangleWidth(), getRectangleHeight(), color);
                anchorPane.setTopAnchor(rect,row * getRectangleWidth());
                anchorPane.setLeftAnchor(rect,col * getRectangleHeight());
                anchorPane.getChildren().add(rect);
            }
        }
        
        root = anchorPane;
        
        return anchorPane;
    }
    
    public void setDimensions() {
        if(getHeight() >= getWidth()) {
            rectangleWidth = getWidth() / ((double) getNumRows());
            rectangleHeight = getWidth() / ((double) getNumCols());
        } else {
            rectangleWidth = getHeight() / ((double) getNumRows());
            rectangleHeight = getHeight() / ((double) getNumCols());
        }
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
        return rectangleWidth;
    }
    
    public double getRectangleHeight() {
        return rectangleHeight;
    }
    
    public double getWidth(){
        return boardWidth;
    }
    
    public double getHeight(){
        return boardHeight;
    }
}
