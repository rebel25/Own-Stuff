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
TokenBullet tokenBullet;
Token token;

float color1 = random(255);
float color2 = random(255);
float color3 = random(255);
int fieldTop;
int fieldBot;

/*------------------------------------------------------------------
||  Setup function for processing
-------------------------------------------------------------------*/
public void setup() {
  size(640, 360);

  fieldTop = height/10;
  fieldBot = ((height/10)*9);
	playerKeyboard = new PlayerKeyboard();
	playerKeyboard.setYPosition(height/2);
	playerKeyboard.setXPosition(width/5);
	tokenBullet = new TokenBullet();


}

public void draw() {
	background(255-color1, 255-color2, 255-color3);
	noStroke();

	fill(color1, color2, color3);
	rect(0, 0, width, fieldTop);
	rect(0, fieldBot, width, height);
	
	playerKeyboard.playerBody();
	//tokenBullet.makeBullet(20, 20);
	tokenBullet.makeToken();
}

public void keyPressed() {
  playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
}

/*void createToken() {
	tokenBullet.tokenMovement();
}*/
class Player {

  protected int playerWidth, playerPosY, playerPosX, gapHeight;

  /*------------------------------------------------------------------
  ||  Initializer for Class/Object
  ||  Set playerWidth(int)
  ||  Set playerPosY(int)
  ||  Set playerPosX(int)
  ||  Set gapHeight(int)
  -------------------------------------------------------------------*/
  Player() {
    playerWidth = 25;
    playerPosY = 0;
    playerPosX = 0;
    gapHeight = 30;
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
  public void moveWithKeyboard(int fieldTop, int fieldBot) {
    switch(key) {
      case 'w': case 'W':
        if (playerPosY - gapHeight/2 < fieldTop) {
          setYPosition(fieldTop + gapHeight/2);
        } else if (playerPosY - gapHeight/2 == fieldTop) {
          setYPositionUp(0);
        } else {
          setYPositionUp(3); 
        }
      break;
      case 's': case 'S':
        if (playerPosY + gapHeight/2 > fieldBot) {
          setYPosition(fieldBot - gapHeight/2);
        } else if (playerPosY + gapHeight/2 == fieldBot) {
          setYPositionUp(0);
        } else {
          setYPositionDown(3);          
        }
      break;
      default:
        println("Default!");
      break;
    }
  }
}
class Token {

	protected int kind, tokenSpeed, tokenSize, tokenX, tokenY;


	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/
	TokenBullet tokenbullet;

	Token() {
		kind = 0;
		tokenSpeed = 3;
		tokenSize = 10;
		tokenX = 0;
		tokenY = 0;
	}
	float startYmin = fieldTop;
	float startYmax = fieldBot;


	public void makeToken() {
		//float tokenX = -tokenSize;
		float tokenY = random(fieldTop, fieldBot);

		//tokenBullet.makeBullet(tokenX, tokenY);
		for (float tokenX = -tokenSize; tokenX < width; tokenX = tokenX + tokenSpeed) {
			tokenbullet.makeBullet(tokenX, tokenY);
		}


	}


	
}
class TokenBullet extends Token {

  /*------------------------------------------------------------------
  ||  Initializer for Class/Object from Superclass Player
  -------------------------------------------------------------------*/
	TokenBullet() {
		super();
	}





	public void makeBullet(float tokenX, float tokenY) {
		fill(255, 255, 255);
		ellipse(tokenX, tokenY, tokenSize, tokenSize);
	}






}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "WallGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
