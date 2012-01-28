package sirius.graphics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.lwjgl.opengl.GL20;

public class Material {
	private Texture texture_;
	private int shaderProgram_;
	private float red_;
	private float green_;
	private float blue_;

	private boolean isShaders_;
	private boolean isTexture_;
	private boolean isSolid_;
	
	private static int loadShader(int shaderType, String filename)
	{
		int shader = GL20.glCreateShader(shaderType);
		String errors;
		if (shader != 0)
		{
			String vertexCode = "";
			String line;
			URL url = TextureLoader.class.getClassLoader().getResource(filename);
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				while((line = reader.readLine()) != null) {
					vertexCode+=line + "\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			
			GL20.glShaderSource(shader, vertexCode);
			GL20.glCompileShader(shader);
		}
		errors = GL20.glGetShaderInfoLog(shader, 1000).trim();
		if (errors.length() > 0)
			System.err.println("Shader errors: " + errors);
		return shader;
	}
	public Material(float red, float green, float blue) {
		red_ = red;
		green_ = green;
		blue_ = blue;
		isSolid_ = true;
		isShaders_ = false;
		isTexture_ = false;
	}
	public Material(String textureFilename) {
		TextureLoader textureLoader = new TextureLoader();
		try {
			texture_ = textureLoader.getTexture(textureFilename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Material(Texture tex) {
		setTexture(tex);
		isSolid_ = false;
		isTexture_ = true;
		isShaders_ = false;
	}
	public Material(Texture tex, String vertexFilename, String fragmentFilename) {
		setTexture(tex);
		// Create shaders
		int fragShader = loadShader(GL20.GL_FRAGMENT_SHADER, fragmentFilename);
		int vertShader = loadShader(GL20.GL_VERTEX_SHADER, vertexFilename);
		
		shaderProgram_ = GL20.glCreateProgram();

		GL20.glAttachShader(shaderProgram_, fragShader);
		GL20.glAttachShader(shaderProgram_, vertShader);
		
		GL20.glLinkProgram(shaderProgram_);
		isSolid_ = false;
		isTexture_ = true;
		isShaders_ = true;
	}

	public Texture getTexture() {
		return texture_;
	}

	public void setTexture(Texture texture) {
		texture_ = texture;
	}
	public boolean isSolid() {
		return isSolid_;
	}
	public boolean isTextured() {
		return isTexture_;
	}
	public boolean hasShader() {
		return isShaders_;
	}
	public float getRed() {
		return red_;
	}
	public float getGreen() {
		return green_;
	}
	public float getBlue() {
		return blue_;
	}
}
