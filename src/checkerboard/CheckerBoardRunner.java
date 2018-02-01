/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Grant
 */
public class CheckerBoardRunner extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckerBoardFXML.fxml"));
        
        Parent root = (Parent) loader.load();
        
        //Grabs our controller to use here
        CheckerBoardFXMLController controller = (CheckerBoardFXMLController) loader.getController();
     
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("CheckerBoard");
        
        //This is what I found on google, it kind of works, but it still wont let me force the stage size to start in a square shape...
        //stage.minWidthProperty().bind(scene.heightProperty().multiply(2));
        //stage.minHeightProperty().bind(scene.widthProperty().divide(2));
        
        stage.show();
        
        controller.load(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
