package datnt.gmoz.com.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import datnt.gmoz.com.common.CommonConstants;

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

            Display.setDisplayMode(new DisplayMode(CommonConstants.INT_WIDTH_SCREEN, CommonConstants.INT_HEIGHT_SCREEN));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle(CommonConstants.STR_GAME_TITLE);

        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(CommonConstants.INT_ZERO, CommonConstants.INT_ZERO, CommonConstants.INT_WIDTH_SCREEN,
                        CommonConstants.INT_HEIGHT_SCREEN);
    }

    /**
     * Update.
     */
    public static void update() {

        Display.sync(CommonConstants.INT_FPS_CAP);
        Display.update();
    }

    /**
     * Close.
     */
    public static void close() {

        Display.destroy();
    }
}
