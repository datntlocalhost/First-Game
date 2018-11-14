package datnt.gmoz.com.renderer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import datnt.gmoz.com.common.CommonConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class Loader.
 */
public class Loader {

    /** The vaos. */
    private List<Integer> vaos = new ArrayList<Integer>();

    /** The vbos. */
    private List<Integer> vbos = new ArrayList<Integer>();

    /** The textures. */
    private List<Integer> textures = new ArrayList<Integer>();

    /**
     * Load to VAO.
     *
     * @param positions the positions
     * @return the raw model
     */
    public RawModel loadToVAO(float[] positions) {

        int vaoId = createVAO();

        storeDataToAttributeList(CommonConstants.INT_ZERO, CommonConstants.INT_NUM_COOR_VERTEX, positions);

        unbindVAO();

        return new RawModel(vaoId, positions.length / CommonConstants.INT_NUM_COOR_VERTEX);
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

        storeDataToAttributeList(CommonConstants.INT_ZERO, CommonConstants.INT_NUM_COOR_VERTEX, positions);

        unbindVAO();

        return new RawModel(vaoId, indices.length);
    }

    /**
     * Load to VAO.
     *
     * @param positions the positions
     * @param textureCoors the texture coors
     * @param indices the indices
     * @return the raw model
     */
    public RawModel loadToVAO(float[] positions, float[] textureCoors, int[] indices) {

        int vaoId = createVAO();

        bindIndicesBuffer(indices);

        storeDataToAttributeList(CommonConstants.INT_ZERO, CommonConstants.INT_NUM_COOR_VERTEX, positions);
        storeDataToAttributeList(CommonConstants.INT_ONE, CommonConstants.INT_NUM_COOR_TEXTURE, textureCoors);

        unbindVAO();

        return new RawModel(vaoId, indices.length);
    }

    /**
     * Load texture.
     *
     * @param file the file
     * @return the int
     */
    public int loadTexture(String file) {

        Texture texture = null;

        try {
            texture = TextureLoader.getTexture(CommonConstants.STR_TEXTURE_FILE_TYPE, new FileInputStream(
                CommonConstants.STR_RESOURCE_PATH + file + CommonConstants.STR_TEXTURE_FILE_EXTENSION));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int textureId = texture.getTextureID();

        this.textures.add(textureId);

        return textureId;
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

        for (int texture : textures) {
            GL11.glDeleteTextures(texture);
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
     * @param coordinateSize the coordinate size
     * @param data the data
     */
    private void storeDataToAttributeList(int attributeNumber, int coordinateSize, float[] data) {

        int vboId = GL15.glGenBuffers();

        this.vbos.add(vboId);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);

        FloatBuffer buffer = storeDataInFloatBuffer(data);

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, CommonConstants.INT_ZERO,
            CommonConstants.INT_ZERO);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, CommonConstants.INT_ZERO);
    }

    /**
     * Unbind VAO.
     */
    private void unbindVAO() {

        GL30.glBindVertexArray(CommonConstants.INT_ZERO);
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
