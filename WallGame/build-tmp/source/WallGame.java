import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class WallGame extends PApplet {

// Daniel Neumayr - 2014


/*------------------------------------------------------------------
||  Initializer for Class/Object from Superclass Player
-------------------------------------------------------------------*/
//PlayerMouse playerMouse;
PlayerKeyboard playerKeyboard;

float color1 = random(255);
float color2 = random(255);
float color3 = random(255);


/*------------------------------------------------------------------
||  Setup function for processing
-------------------------------------------------------------------*/
public void setup() {
	frameRate(5);
  size(640, 360);
	background(255-color1, 255-color2, 255-color3);

	 playerKeyboard = new PlayerKeyboard();
	 playerKeyboard.setYPosition(height/2);
	 playerKeyboard.setXPosition(width/5);

  //playerMouse = new PlayerMouse();
  //playerKeyboard = new PlayerKeyboard();
  //playerKeyboard.setXPosition(width/2);
  //playerKeyboard.setYPosition(height/2);
}

public void draw() {
	noStroke();
	fill(color1, color2, color3);
	rect(0, 0, width,height/10);
	fill(color1, color2, color3);
	rect(0, height/10*9, width,height);

	playerKeyboard.playerBody();
}

public void keyPressed() {
  playerKeyboard.moveWithKeyboard();
}
class Player {

  private int playerWidth, playerPosY, playerPosX, gapHeight;

/*------------------------------------------------------------------
||  Initializer for Class/Object
||  Set size(int)
-------------------------------------------------------------------*/
Player() {
  playerWidth = 25;
  playerPosY = 0;
  playerPosX = 0;
  gapHeight = 100;
}



/*------------------------------------------------------------------
||  Create Playerbody
-------------------------------------------------------------------*/
protected void playerBody(){
	int barLeft = (playerPosX-playerWidth/2);
	int barRight = (playerPosX+playerWidth/2);
	int gapTop = (playerPosY-gapHeight/2);
	int gapBottom = (playerPosY+gapHeight/2);


	noStroke();
	rectMode(CORNERS);
	rect(barLeft, 0, barRight, gapTop);
	rectMode(CORNERS);
	rect(barLeft, gapBottom, barRight, height);

	println("barLeft: "+barLeft);
	println("barRight: "+barRight);
	println("gapTop: "+gapTop);
	println("gapBottom: "+gapBottom);


}



/*------------------------------------------------------------------
||  Set/Get Y Position of Player
-------------------------------------------------------------------*/
protected void setYPosition(float _position) {
  playerPosY = PApplet.parseInt(_position);
}

protected void setYPositionDown(int _step) {
  playerPosY = getYPosition() + _step;
}

protected void setYPositionUp(int _step) {
  playerPosY = getYPosition() - _step;
}

protected int getYPosition() {
  return playerPosY;
}

  /*------------------------------------------------------------------
  ||  Set/Get X Position of Player
  -------------------------------------------------------------------*/
  protected void setXPosition(float _position) {
    playerPosX = PApplet.parseInt(_position);
  }

  protected void setXPositionRight(int _step) {
    playerPosX = getXPosition() + _step;
  }

  protected void setXPositionLeft(int _step) {
    playerPosX = getXPosition() - _step;
  }

  protected int getXPosition() {
    return playerPosX;
  }


}
class PlayerKeyboard extends Player {

  /*------------------------------------------------------------------
  ||  Initializer for Class/Object from Superclass Player
  -------------------------------------------------------------------*/
  PlayerKeyboard() {
    super();
  }

  /*------------------------------------------------------------------
  ||  Make Player move with keyboard - method
  ||  W || w => Player is going up.
  ||  S || s => Player is going down.
  -------------------------------------------------------------------*/
  public void moveWithKeyboard() {
    switch(key) {
      case 'w': case 'W':
          setYPositionUp(3);
      break;
      case 's': case 'S':
          setYPositionDown(3);
      break;
      default:
        println("Default!");
      break;
    }
  }
}
class Token {

	protected int kind, tokenSpeed, tokenSize;


	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/

	Token () {
		kind = 0;
		tokenSpeed = 3;
		tokenSize = 10;
	}

	float startY;
	int startX;

	protected void makeBuddy(int fieldTop, int fieldBot) {
		startY = random(fieldTop+tokenSize/2, fieldBot-tokenSize/2);
		startX = -tokenSize;

		ellipse(startX, startY, tokenSize, tokenSize);
	}
}
/*class TokenBullet extends Token {

	Token() {
		super();
	}







}*/
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "WallGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
