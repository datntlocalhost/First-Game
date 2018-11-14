package datnt.gmoz.com.shader;

import datnt.gmoz.com.common.CommonConstants;

public class StaticShader extends ShaderManager {

    private static final String VERTEX_FILE = CommonConstants.STR_SHADER_FILE_PATH + "staticVertex.txt";

    private static final String FRAGMENT_FILE = CommonConstants.STR_SHADER_FILE_PATH + "staticFragment.txt";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(CommonConstants.INT_ZERO, CommonConstants.STR_POSITION);
        super.bindAttribute(CommonConstants.INT_ONE, CommonConstants.STR_TEXTURE_COORDS);
    }
}
