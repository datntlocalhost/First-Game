package datnt.gmoz.com;

import org.lwjgl.opengl.Display;

import datnt.gmoz.com.model.ModelBase;
import datnt.gmoz.com.renderer.DisplayManager;
import datnt.gmoz.com.renderer.Loader;
import datnt.gmoz.com.renderer.RawModel;
import datnt.gmoz.com.renderer.Renderer;
import datnt.gmoz.com.renderer.texture.ModelTexture;
import datnt.gmoz.com.shader.StaticShader;

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
        
        StaticShader staticShader = new StaticShader();
        
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
        
        float[] textureCoords = {
            0, 0, 
            0, 1, 
            1, 1, 
            1, 0
        };
        
        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture("image"));
        
        ModelBase modelBase = new ModelBase(model, modelTexture);
        
        while (!Display.isCloseRequested()) {
            
            renderer.prepare();

            //TODO: game logic
            
            //TODO: start shader
            staticShader.start();
            
            //TODO: render
            renderer.render(modelBase);
            
            //TODO: stop shader
            staticShader.stop();
            
            DisplayManager.update();
        }

        staticShader.cleanUp();
        loader.cleanUp();
        
        DisplayManager.close();
    }

}
