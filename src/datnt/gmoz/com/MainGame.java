package datnt.gmoz.com;

import org.lwjgl.opengl.Display;

import datnt.gmoz.com.render.DisplayManager;

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

        while (!Display.isCloseRequested()) {

            //TODO: game logic
            DisplayManager.update();
        }

        DisplayManager.close();
    }

}
