package datnt.gmoz.com.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import datnt.gmoz.com.common.Constants;

/**
 * The Class DisplayManager.
 */
public class DisplayManager {

    /**
     * Creates the.
     */
    public static void create() {

        try {

            ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

            Display.setDisplayMode(new DisplayMode(Constants.INT_WIDTH_SCREEN, Constants.INT_HEIGHT_SCREEN));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle(Constants.STR_GAME_TITLE);

        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(Constants.INT_ZERO, Constants.INT_ZERO, Constants.INT_WIDTH_SCREEN,
                        Constants.INT_HEIGHT_SCREEN);
    }

    /**
     * Update.
     */
    public static void update() {

        Display.sync(Constants.INT_FPS_CAP);
        Display.update();
    }

    /**
     * Close.
     */
    public static void close() {

        Display.destroy();
    }
}
