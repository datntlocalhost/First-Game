package datnt.gmoz.com.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import datnt.gmoz.com.common.CommonConstants;
import datnt.gmoz.com.util.FileUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ShaderManager.
 */
public abstract class ShaderManager {
    
    /** The id. */
    private int id;
    
    /** The vertex shader id. */
    private int vertexShaderId;
    
    /** The fragment shader id. */
    private int fragmentShaderId;
    
    /**
     * Instantiates a new shader manager.
     *
     * @param vertexFile the vertex file
     * @param fragmentFile the fragment file
     */
    public ShaderManager(String vertexFile, String fragmentFile) {
        
        this.vertexShaderId   = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        this.fragmentShaderId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        this.id               = GL20.glCreateProgram();
        
        GL20.glAttachShader(id, this.vertexShaderId);
        GL20.glAttachShader(id, this.fragmentShaderId);
        GL20.glLinkProgram(id);
        GL20.glValidateProgram(id);
    }
    
    /**
     * Start.
     */
    public void start() {
        GL20.glUseProgram(id);
    }
    
    /**
     * Stop.
     */
    public void stop() {
        GL20.glUseProgram(CommonConstants.INT_ZERO);
    }
    
    /**
     * Clean up.
     */
    public void cleanUp() {
        stop();
        GL20.glDetachShader(id, vertexShaderId);
        GL20.glDetachShader(id, fragmentShaderId);
        GL20.glDeleteShader(vertexShaderId);
        GL20.glDeleteShader(fragmentShaderId);
        GL20.glDeleteProgram(id);
    }
    
    /**
     * Bind attributes.
     */
    protected abstract void bindAttributes();

    /**
     * Bind attribute.
     *
     * @param attribute the attribute
     * @param name the name
     */
    protected void bindAttribute(int attribute, String name) {
        GL20.glBindAttribLocation(id, attribute, name);
    }
    
    /**
     * Load shader.
     *
     * @param file the file
     * @param type the type
     * @return the int
     */
    public static int loadShader(String file, int type) {
        
        StringBuffer shaderSource = FileUtil.readContentFile(file);
        
        int shaderId = GL20.glCreateShader(type);
        
        GL20.glShaderSource(shaderId, shaderSource);
        GL20.glCompileShader(shaderId);
        
        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderId, 500));
            System.exit(-1);
        }
        
        return shaderId;
    }

}
