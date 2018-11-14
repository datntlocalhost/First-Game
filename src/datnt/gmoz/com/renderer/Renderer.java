package datnt.gmoz.com.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import datnt.gmoz.com.common.CommonConstants;

/**
 * The Class Renderer.
 */
public class Renderer {

    /**
     * Prepare.
     */
    public void prepare() {
        GL11.glClearColor(1, 0, 0, 1);
    }

    /**
     * Render.
     *
     * @param model the model
     */
    public void render(RawModel model) {

        // Bind the vao
        GL30.glBindVertexArray(model.getVaoId());

        // Active attribute list in which our data is stored with is zero
        GL20.glEnableVertexAttribArray(CommonConstants.INT_ZERO);

        // Render triangles with the data should start is zero and the number of vertex is vertexCount
        // GL11.glDrawArrays(GL11.GL_TRIANGLES, Constants.INT_ZERO, model.getVertexCount());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, CommonConstants.INT_ZERO);
        
        // Disable attribute list after render
        GL20.glDisableVertexAttribArray(CommonConstants.INT_ZERO);
        
        // Unbind VAO
        GL30.glBindVertexArray(CommonConstants.INT_ZERO);
    }
}
