package datnt.gmoz.com.renderer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import datnt.gmoz.com.common.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Loader.
 */
public class Loader {

    /** The vaos. */
    private List<Integer> vaos = new ArrayList<Integer>();

    /** The vbos. */
    private List<Integer> vbos = new ArrayList<Integer>();

    /**
     * Load to VAO.
     *
     * @param positions the positions
     * @return the raw model
     */
    public RawModel loadToVAO(float[] positions) {

        int vaoId = createVAO();

        storeDataToAttributeList(Constants.INT_ZERO, positions);

        unbindVAO();

        return new RawModel(vaoId, positions.length / Constants.INT_NUM_COOR_VERTEX);
    }
    
    /**
     * Load to VAO.
     *
     * @param positions the positions
     * @param indices the indices
     * @return the raw model
     */
    public RawModel loadToVAO(float[] positions, int[] indices) {
        
        int vaoId = createVAO();

        bindIndicesBuffer(indices);
        
        storeDataToAttributeList(Constants.INT_ZERO, positions);

        unbindVAO();

        return new RawModel(vaoId, indices.length);
    }
    
    /**
     * Clean up.
     */
    public void cleanUp() {
        
        for (int vao : vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
        
        for (int vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }
    }

    /**
     * Creates the VAO.
     *
     * @return the int
     */
    private int createVAO() {

        int vaoId = GL30.glGenVertexArrays();

        this.vaos.add(vaoId);
        
        GL30.glBindVertexArray(vaoId);

        return vaoId;
    }

    /**
     * Store data to attribute list.
     *
     * @param attributeNumber the attribute number
     * @param data the data
     */
    private void storeDataToAttributeList(int attributeNumber, float[] data) {

        int vboId = GL15.glGenBuffers();
        
        this.vbos.add(vboId);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, Constants.INT_ZERO, Constants.INT_ZERO);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, Constants.INT_ZERO);
    }

    /**
     * Unbind VAO.
     */
    private void unbindVAO() {

        GL30.glBindVertexArray(Constants.INT_ZERO);
    }
    
    /**
     * Bind indices buffer.
     *
     * @param indices the indices
     */
    private void bindIndicesBuffer(int[] indices) {
        
        int vboId = GL15.glGenBuffers();
        
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
        
        IntBuffer buffer = storeDataInIntBuffer(indices);
        
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * Store data in float buffer.
     *
     * @param data the data
     * @return the float buffer
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data) {

        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);

        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    /**
     * Store data in int buffer.
     *
     * @param data the data
     * @return the int buffer
     */
    private IntBuffer storeDataInIntBuffer(int[] data) {
        
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        
        buffer.put(data);
        buffer.flip();
        
        return buffer;
    }
    /**
     * Gets the vaos.
     *
     * @return the vaos
     */
    public List<Integer> getVaos() {
        return vaos;
    }

    /**
     * Gets the vbos.
     *
     * @return the vbos
     */
    public List<Integer> getVbos() {
        return vbos;
    }

}
