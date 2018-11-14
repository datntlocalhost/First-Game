package datnt.gmoz.com;

import org.lwjgl.opengl.Display;

import datnt.gmoz.com.renderer.DisplayManager;
import datnt.gmoz.com.renderer.Loader;
import datnt.gmoz.com.renderer.RawModel;
import datnt.gmoz.com.renderer.Renderer;

/**
 * The Class MainGame.
 */
public class MainGame {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        DisplayManager.create();

        Loader loader = new Loader();
        
        Renderer renderer = new Renderer();
        
        float[] vertices = {

            -0.5f,  0.5f, 0f, //v0
            -0.5f, -0.5f, 0f, //v1
             0.5f, -0.5f, 0f, //v2
             0.5f,  0.5f, 0f  //v3

        };
        
        int[] indices = {
            
            0, 1, 3, //triangle 1
            3, 1, 2  //triangle 2
            
        };
        
        RawModel model = loader.loadToVAO(vertices, indices);
        
        while (!Display.isCloseRequested()) {
            
            renderer.prepare();

            //TODO: game logic
            
            //TODO: render
            renderer.render(model);
            
            DisplayManager.update();
        }

        loader.cleanUp();
        
        DisplayManager.close();
    }

}
