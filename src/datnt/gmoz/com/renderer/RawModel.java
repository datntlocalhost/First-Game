package datnt.gmoz.com.renderer;

/**
 * The Class RawModel.
 */
public class RawModel {

    /** The vao id. */
    private int vaoId;
    
    /** The vertex count. */
    private int vertexCount;

    /**
     * Instantiates a new raw model.
     *
     * @param vaoId the vao id
     * @param vertexCount the vertex count
     */
    public RawModel(int vaoId, int vertexCount) {
        super();
        this.vaoId = vaoId;
        this.vertexCount = vertexCount;
    }

    /**
     * Gets the vao id.
     *
     * @return the vao id
     */
    public int getVaoId() {
        return vaoId;
    }

    /**
     * Gets the vertex count.
     *
     * @return the vertex count
     */
    public int getVertexCount() {
        return vertexCount;
    }
}
