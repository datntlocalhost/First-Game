package datnt.gmoz.com.model;

import datnt.gmoz.com.renderer.RawModel;
import datnt.gmoz.com.renderer.texture.ModelTexture;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelBase.
 */
public class ModelBase {

    /** The model. */
    private RawModel model;

    /** The texture. */
    private ModelTexture texture;

    /**
     * Instantiates a new model base.
     *
     * @param model the model
     * @param texture the texture
     */
    public ModelBase(RawModel model, ModelTexture texture) {
        super();
        this.model = model;
        this.texture = texture;
    }

    /**
     * Gets the model.
     *
     * @return the model
     */
    public RawModel getModel() {
        return model;
    }

    /**
     * Gets the texture.
     *
     * @return the texture
     */
    public ModelTexture getTexture() {
        return texture;
    }

}
