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
    
    //Variables
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
    
    /**
     * Builds a new AnchorPane and returns it to be updated in the GUI
     * @param forceSquare - tells the method to build an AnchorPane with forced equals square sizes or just fill it with rectangles
     * @return 
     */
    public AnchorPane build(boolean forceSquare) {

        AnchorPane anchorPane = new AnchorPane();
        
        //Sets the square sizes (forces them to be squares)
        setDimensions(forceSquare);
        
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumCols(); col++) {
                
                //Sets the color of the square based on row/column currently being drawn
                Color color;
                if(row % 2 == 0) {
                    color = (col % 2 == 0 ? getLightColor(): getDarkColor());
                } else {
                    color = (col % 2 == 0 ? getDarkColor() : getLightColor());
                }
                
                Rectangle rect = new Rectangle(getRectangleWidth(), getRectangleHeight(), color);
                //Uses anchors to determine the placement of the rectangle (square)
                
                if(!forceSquare) {
                    rect.setX(row * getRectangleWidth());
                    rect.setY(col * getRectangleHeight());
                } else {
                    anchorPane.setTopAnchor(rect,row * getRectangleWidth());
                    anchorPane.setLeftAnchor(rect,col * getRectangleHeight());
                }
                
                anchorPane.getChildren().add(rect);
            }
        }
        
        //Sets the minimum width so we can load the stage in a correct size on initialization (only if were forcing squares to be drawn)
        if(forceSquare)
            anchorPane.setMinWidth(getNumRows() * getRectangleWidth());
        
        //Update our root variable
        root = anchorPane;
        
        return anchorPane;
    }
    
    /**
     * Sets the dimensions of the rectangles
     */
    public void setDimensions(boolean forceSquare) {
        if(forceSquare) {
            if(getHeight() >= getWidth()) {
                rectangleWidth = getWidth() / ((double) getNumRows());
                rectangleHeight = getWidth() / ((double) getNumCols());
            } else {
                rectangleWidth = getHeight() / ((double) getNumRows());
                rectangleHeight = getHeight() / ((double) getNumCols());
            }
        } else {
            rectangleWidth = getWidth() / getNumRows();
            rectangleHeight = getHeight() / getNumCols();
        }
    }
    
    //All of our getter's below here
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
