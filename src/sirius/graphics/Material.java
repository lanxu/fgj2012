package sirius.graphics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.lwjgl.opengl.GL20;

public class Material {
	private Texture texture_;
	private int shaderProgram_;
	
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
	
	Material(Texture tex, String vertexFilename, String fragmentFilename) {
		setTexture(tex);
		// Create shaders
		int fragShader = loadShader(GL20.GL_FRAGMENT_SHADER, fragmentFilename);
		int vertShader = loadShader(GL20.GL_VERTEX_SHADER, vertexFilename);
		
		shaderProgram_ = GL20.glCreateProgram();

		GL20.glAttachShader(shaderProgram_, fragShader);
		GL20.glAttachShader(shaderProgram_, vertShader);
		
		GL20.glLinkProgram(shaderProgram_);
	}

	public Texture getTexture() {
		return texture_;
	}

	public void setTexture(Texture texture) {
		texture_ = texture;
	}
}
