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
          
            // Left bottom triangle
            -0.5f,  0.5f, 0f,
            -0.5f, -0.5f, 0f,
             0.5f, -0.5f, 0f,

            // Right top triangle
             0.5f, -0.5f, 0f,
             0.5f,  0.5f, 0f,
            -0.5f,  0.5f, 0f
        };
        
        RawModel model = loader.loadToVAO(vertices);
        
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
