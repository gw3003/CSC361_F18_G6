package packtpub.libgdx.canyonbunny;

import com.badlogic.gdx.ApplicationListener;
import com.packtpub.libgdx.canyonbunny.game.WorldController;
import com.packtpub.libgdx.canyonbunny.game.WorldRenderer;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class CanyonBunnyMain {
	private static final String TAG = CanyonBunnyMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
	
@Override 
public void create() {
	// Set Libgdx log level to Debug
	Gdx.app.setLogLevel(Application.LOG_DEBUG);
	//Initialize controller and renderer
	worldController = new WorldController();
	worldRendrerer = new WorldRenderer(worldController);
	paused = false;
}
@Override
public void render() {
	//Do not update game world when paused.
	if (!paused) {
		//Update game world by the time that has passed since last rendered frame.
		worldController.update(Gdx.graphics.getDeltaTime());
	}
	// Sets the clear screen color to: Cornflower Blue
	Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
	//Clears the screen
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	//Render game world to screen
	worldRenderer.render();
}
@Override 
public void resize (int width, int height) { }
@Override 
public void pause() {
	paused = true;
}
@Override 
public void resume() {
	paused = false;
}
@Override 
public void dispose() {
	worldRenderer.dispose();
}
}
