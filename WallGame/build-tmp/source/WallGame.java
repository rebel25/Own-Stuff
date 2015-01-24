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
Collider collider;

int color1 = PApplet.parseInt(random(255));
int color2 = PApplet.parseInt(random(255));
int color3 = PApplet.parseInt(random(255));
int field = color(color1, color2, color3);
int player = color((255-color1), (255-color2), (255-color3));
int fieldTop;
int fieldBot;

/*------------------------------------------------------------------
||  Setup function for processing
-------------------------------------------------------------------*/
public void setup() {
  size(1280, 720);
  //frameRate(5);

  fieldTop = height/10;
  fieldBot = ((height/10)*9);
	playerKeyboard = new PlayerKeyboard();
	playerKeyboard.setYPosition(height/2);
	playerKeyboard.setXPosition(width/5);
	tokenBullet = new TokenBullet();
	tokenBullet.setTokenX(-10);
  collider = new Collider();
}

public void draw() {
	background(field);
	noStroke();

	fill(player);
	rect(0, 0, width, fieldTop);
	rect(0, fieldBot, width, height);
	
	playerKeyboard.playerBody();
	tokenBullet.makeToken();
}

public void keyPressed() {
  playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
}
class Collider {

	protected int playerY, playerX, gapTop, gapBottom, barLeft, barRight, kind;
	protected float[] playerArray = {0, 0, 0, 0, 0, 0};
	protected float[] tokenArray = {0, 0, 0, 0};

	Collider() {
	playerY = 0;
	playerX = 0;
	gapTop = 0;
	gapBottom = 0;
	barLeft = 0;
	barRight = 0;
	kind = 0;
	}

	Player player;

	public boolean collision(){
	return box_box_p(tokenArray[0], tokenArray[1], tokenArray[2], tokenArray[3], playerArray[0], playerArray[1], playerArray[2], playerArray[3], playerArray[4], playerArray[5]);
	}

	public void fillPlayerArray(float _barLeft, float _fieldTop, float _barRight, float _gapTop, float _gapBottom, float _fieldBot){
		playerArray[0] = _barLeft;
		playerArray[1] = _fieldTop;
		playerArray[2] = _barRight;
		playerArray[3] = _gapTop;
		playerArray[4] = _gapBottom;
		playerArray[5] = _fieldBot;
	}

	public void fillTokenArray(float _tokenX, float _tokenY, float _tokenWidth, float _tokenHeight){
		tokenArray[0] = _tokenX;
		tokenArray[1] = _tokenY;
		tokenArray[2] = _tokenWidth;
		tokenArray[3] = _tokenHeight;
	}

	public boolean box_box_p(float tx0, float ty0, float tx1, float ty1, float px0, float py0, float px1, float py1, float py2, float py3){
		
		float topOverlap = 0;
		float botOverlap = 0;
		float leftOverlap = 0;
		float rightOverlap = 0;

		float topToken = min(ty0, ty0+ty1);
		float botToken = max(ty0, ty0+ty1);
		float leftToken = min(tx0, tx1);
		float rightToken = max(tx0, tx1);

		float topPlayer1 = min(py0, py1);
		float botPlayer1 = max(py0, py1);
		float topPlayer2 = min(py2, py3);
		float botPlayer2 = max(py2, py3);

		float leftPlayer = min(px0, px1);
		float rightPlayer = max(px0, px1);

		if(((botToken >= topPlayer2) || (topToken <= botPlayer1)) && ((leftToken <= rightPlayer) && (leftToken >= leftPlayer) || (rightToken >= leftPlayer) && (rightToken <= rightPlayer))){
			//println("new: yes");
			//println("topToken: "+topToken + " botPlayer1: " + botPlayer1);
			return true;
		} else {
			//println("new: no");
			return false;
		}
	}
}	
class Player {
  PShape playerBodySvg;

  protected int playerWidth, playerPosY, playerPosX, gapHeight, playerSpeed, barLeft, barRight, gapTop, gapBottom;
          
  /*------------------------------------------------------------------
  ||  Initializer for Class/Object
  ||  Set playerWidth(int)
  ||  Set playerPosY(int)
  ||  Set playerPosX(int)
  ||  Set gapHeight(int)
  -------------------------------------------------------------------*/


  Player() {
    playerWidth = fieldTop;
    playerPosY = 0;
    playerPosX = 0;
    gapHeight = 120;
    playerSpeed = 8;
    barLeft = 0;

  }

  /*------------------------------------------------------------------
  ||  Create Playerbody
  -------------------------------------------------------------------*/
  protected void playerBody(){

    barLeft = (playerPosX-playerWidth/2);
    barRight = (playerPosX+playerWidth/2);
    gapTop = (playerPosY-gapHeight/2);
    gapBottom = (playerPosY+gapHeight/2);

    playerBodySvg = loadShape("svgs/rectangle.svg");
    playerBodySvg.disableStyle();

    noStroke();
    shapeMode(CORNERS);
    shape(playerBodySvg, barLeft, fieldTop, barRight, gapTop);
    shapeMode(CORNERS);
    shape(playerBodySvg, barLeft, gapBottom, barRight, fieldBot);

    collider.fillPlayerArray(barLeft, fieldTop, barRight, gapTop, gapBottom, fieldBot);
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

  /*------------------------------------------------------------------
  ||  Get Body values
  -------------------------------------------------------------------*/

 /* protected void setBarLeft() {
    
  }*/

  protected int getBarLeft() {
    return barLeft;
  }

  protected int getBarRight() {
    return barRight;
  }

  protected int getGapTop() {
    return gapTop;
  }

  protected int getGapBottom() {
    return gapBottom;
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
          setYPositionUp(playerSpeed);
        }
      break;
      case 's': case 'S':
        if (playerPosY + gapHeight/2 > fieldBot) {
          setYPosition(fieldBot - gapHeight/2);
        } else if (playerPosY + gapHeight/2 == fieldBot) {
          setYPositionUp(0);
        } else {
          setYPositionDown(playerSpeed);          
        }
      break;
      default:
        println("Default!");
      break;
    }
  }
}
class Token {

	protected int kind, tokenSpeed, tokenWidth, tokenHeight, tokenX, tokenY, maxKind, collision, collisionStep, reset;

	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/
	//TokenBullet tokenbullet;

	Token() {
		maxKind = 1;
		kind = 0;
		tokenSpeed = 6;
		tokenWidth = 10;
		tokenHeight = 10;
		tokenX = 0;
		tokenY = 0;
		collision = 0;
		collisionStep = 0;
		reset = 0;
	}
	
	float startYmin = fieldTop;
	float startYmax = fieldBot - tokenWidth;
	
	public void makeToken() {
		//setTokenXRight(-tokenWidth);
		if (tokenX == -tokenWidth) {
			kind = PApplet.parseInt(random(0, maxKind));
			setTokenY(random(startYmin, startYmax));
			//println("tokenX: "+tokenX);
			resetToken(0);
		}

  /*------------------------------------------------------------------
  ||  check collision 
  -------------------------------------------------------------------*/

		switch (kind) {
			case 0 :
				if (tokenX <= width + tokenWidth) {
					if (reset == 0) {
						setTokenXRight(getTokenSpeed());
						tokenBullet.makeBullet(tokenX, tokenY);
					} else {
						setTokenSpeed(6);
						setTokenX(-tokenWidth);

					  resetToken(0);
					}
				} else {
					setTokenX(-tokenWidth);
					setTokenY(random(startYmin, startYmax));
				}
			break;	
			case 1 :
				if (tokenX <= width + tokenWidth) {
					if (reset == 0) {
					 	setTokenXRight(getTokenSpeed());
						tokenBullet.makeSpeed(tokenX, tokenY);
					} else {
						setTokenX(-tokenWidth);
						resetToken(0);
					}
				} else {
					setTokenX(-tokenWidth);
					setTokenY(random(startYmin, startYmax));
				}
			break;	
		}
	}

  /*------------------------------------------------------------------
  ||  Set/Get X Position of Token
  -------------------------------------------------------------------*/

	public void setTokenX(float _position){
		tokenX = PApplet.parseInt(_position);
	}

  public void setTokenXRight(int _step) {
		tokenX = getTokenX() + _step;
  }

  public void setTokenXLeft(int _step) {
		tokenX = getTokenX() - _step;
  }

  public int getTokenX() {
		return tokenX;
  }

  /*------------------------------------------------------------------
  ||  Set/Get Y Position of Token
  -------------------------------------------------------------------*/

	public void setTokenY(float _position){
		tokenY = PApplet.parseInt(_position);
	}

  public void setTokenYDown(int _step) {
		tokenY = getTokenY() + _step;
  }

  public void setTokenYUp(int _step) {
		tokenY = getTokenY() - _step;
  }

  public int getTokenY() {
		return tokenY;
  }

  /*------------------------------------------------------------------
  ||  Set/Get Token speed
  -------------------------------------------------------------------*/


  public void setTokenSpeed(int _tokenSpeed) {
  	tokenSpeed = PApplet.parseInt(_tokenSpeed);
  }

  public void setTokenSpeedUp(int _step) {
  	tokenSpeed = getTokenSpeed() + _step;
  }

  public void setTokenSpeedDown(int _step) {
  	tokenSpeed = getTokenSpeed() - _step;
  }

  public int getTokenSpeed() {
  	return tokenSpeed;
  }




  public void setCollisionStep(int _collisionStep) {
  	collisionStep = PApplet.parseInt(_collisionStep);
  }

  public void setCollisionStepUp(int _step) {
  	collisionStep = getCollisionStep() + _step;
  }

  public int getCollisionStep() {
  	return collisionStep;
  }



 	public boolean resetToken(int _reset) {
  	if (_reset > 0) {
  		reset = 1;
  		return true;
  	} else {
  		reset = 0;
  	return false;
  	}
  }
}
class TokenBullet extends Token {

	PShape tokenSvg;

	TokenBullet() {
		super();
	}


	protected void makeTokenSvg(String _tokenPath) {
		tokenSvg = loadShape(_tokenPath);
		tokenSvg.disableStyle();
		noStroke();
		shapeMode(CORNER);
		shape(tokenSvg, tokenX, tokenY, tokenWidth, tokenHeight);
	}


	protected void makeTokenExplode(String _tokenPath){
		tokenSvg = loadShape(_tokenPath);
		tokenSvg.disableStyle();
		noStroke();
		int _step = getCollisionStep();
		println("_step: "+_step);
		fill(255, 255, 255, 255-(_step*7));
		shapeMode(CORNER);
		shape(tokenSvg, tokenX, tokenY, tokenWidth, tokenHeight);
	}



	public void makeBullet(float tokenX, float tokenY) {
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
		collider.collision();
		println("collider.collision(): "+collider.collision());

		if (collider.collision()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/rectangle.svg");
				setCollisionStepUp(1);
			} else {
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
			fill(255, 255, 255);
			makeTokenSvg("svgs/rectangle.svg");
		}
	}



	public void makeSpeed(float tokenX, float tokenY) {
		fill(0, 0, 0);
		makeTokenSvg("svgs/triangle.svg");
		collider.collision();
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
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
