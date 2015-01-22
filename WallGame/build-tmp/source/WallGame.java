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

float color1 = random(255);
float color2 = random(255);
float color3 = random(255);
//color field = (color1, color2, color3);
//color player = ((255-color1), (255-color2), (255-color3));
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
	background(color1, color2, color3);
	noStroke();

	fill((255-color1), (255-color2), (255-color3));
	rect(0, 0, width, fieldTop);
	rect(0, fieldBot, width, height);
	
	playerKeyboard.playerBody();
	tokenBullet.makeToken();
}

public void keyPressed() {
  playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
}

/*void createToken() {
	tokenBullet.tokenMovement();
}*/
class Collider {

	protected int playerY, playerX, gapTop, gapBottom, barLeft, barRight, kind;
	protected float[] playerArray = {0, 0, 0, 0, 0, 0};
	protected float[] tokenArray = {0, 0, 0, 0};
	float[] overlap;
	//Player player;



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

	public void collision(int _kind){
		kind = _kind;
		box_box_p(tokenArray[0], tokenArray[1], tokenArray[2], tokenArray[3], playerArray[0], playerArray[1], playerArray[2], playerArray[3], playerArray[4], playerArray[5]);
		//overlap(float[] box_box_p,)


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

		float[] result = null;
		float topToken = min(ty0, ty1);
		float botToken = max(ty0, ty1);
		float leftToken = min(tx0, tx1);
		float rightToken = max(tx0, tx1);

		float topPlayer1 = min(py0, py1);
		float botPlayer1 = max(py0, py1);
		float topPlayer2 = min(py2, py3);
		float botPlayer2 = max(py2, py3);

		float leftPlayer = min(px0, px1);
		float rightPlayer = max(px0, px1);

		if(botToken <= topPlayer1 || botToken <= topPlayer2 || botPlayer1 <= topToken || botPlayer2 <= topToken || rightToken <= leftPlayer || rightPlayer <= leftToken){
			return false;
		} else {
			leftOverlap = (leftToken < leftPlayer) ? leftPlayer : leftToken;
			rightOverlap = (rightToken > rightPlayer) ? rightPlayer : rightToken;
			if (topToken < botPlayer1) {
				topOverlap = (topToken < topPlayer1) ? topPlayer1 : topToken;
				botOverlap = (botToken < botPlayer1) ? botPlayer1 : botToken;
			} else {
				topOverlap = (topToken < topPlayer2) ? topPlayer2 : topToken;
				botOverlap = (botToken < botPlayer2) ? botPlayer2 : botToken;
			}
			return true;//{leftOverlap, topOverlap, rightOverlap, botOverlap};
		}

		float aPx = leftOverlap - leftToken;
		float aPy = topOverlap - topToken;
		float aSx = rightOverlap - leftToken;
		float aSy = botOverlap - topToken - 1;
		float bPx = leftOverlap - leftPlayer;
		float bPy1 = topOverlap - topPlayer1;
		float bPy2 = topOverlap - topPlayer2;

		float widthOverlap = rightOverlap - leftOverlap;
		boolean foundCollision = false;

		player.playerBody();
		tokenBullet.makeToken();

		boolean pixelAtrans = true;
		boolean pixelBtrans = true;

		float pA = (aPy * (barRight - barLeft)) + aPx;
		float pB = (bPy1 * tokenArray[2]) + bPx;

		//float ax = aPx;
		//float ay = aPy;
		float bx = bPx;
		float by1 = bPy1;
		float by2 = bPy2;

		for (float ay = aPy; ay < aSy; ay++) {
			bx = bPx;
			for (float ax = aPx; ax < aSx; ax++) {
				pixelAtrans = alpha(playerBody.pixels[pA]) < ALPHALEVEL;
				pixelBtrans = alpha(makeToken.pixels[pB]) < ALPHALEVEL;

				if (!pixelAtrans && !pixelBtrans) {
					foundCollision = true;
					break;
				}
				pA ++;
				pB ++;
				bx ++;
			}
			if (foundCollision) break;
			pA = pA + (barRight - barLeft) - widthOverlap;
			pB = pB + tokenArray[2] - widthOverlap;
			by1++;
		}
		return foundCollision;
	}









/*

	boolean overLap(float[] box_box_p, color[] data1, color[] data2){
		float left = float[0];
		float top = float[1];
		float right = float[2];
		float bot = float[3];
		for(int y = top; y < bot; y++){
			for(int x = left; y < right; x++){

				color color1 = data1[(x - left) + (y - top) * (right - left)];
				color color2 = data2[(x - right) + (y - bot) * (right - left)];

			}
		}
	}*/
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
    gapHeight = 60;
    playerSpeed = 5;
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

	protected int kind, tokenSpeed, tokenWidth, tokenHeight, tokenX, tokenY, maxKind, collision;

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
		tokenSpeed = 8;
		tokenWidth = 10;
		tokenHeight = 10;
		tokenX = 0;
		tokenY = 0;
		collision = 0;
	}
	float startYmin = fieldTop;
	float startYmax = fieldBot - tokenWidth;
	
	public void makeToken() {
		if (tokenX == -tokenWidth) {
			kind = PApplet.parseInt(random(0, maxKind+1));
			setTokenY(random(startYmin, startYmax));
			println("kind: "+kind);
		}

  /*------------------------------------------------------------------
  ||  check collision 
  -------------------------------------------------------------------*/

		switch (kind) {
			case 0 :
				if (tokenX <= width + tokenWidth) {
					setTokenXRight(tokenSpeed);
					println("tokenX: "+tokenX);
					tokenBullet.makeBullet(tokenX, tokenY);
				} else {
					setTokenX(-tokenWidth);
					setTokenY(random(startYmin, startYmax));
				}
			break;	
			case 1 :
				if (tokenX <= width + tokenWidth) {
					setTokenXRight(tokenSpeed);
					//println("tokenX: "+tokenX);
					tokenBullet.makeSpeed(tokenX, tokenY);
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

	public void makeBullet(float tokenX, float tokenY) {
		//color[] tokenColor {255, 255, 255};
		fill(255, 255, 255);
		makeTokenSvg("svgs/rectangle.svg");
		//ellipse(tokenX, tokenY, tokenSize, tokenSize);
		//collider.collision(kind);
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
	}

	public void makeSpeed(float tokenX, float tokenY) {
		fill(0, 0, 0);
		makeTokenSvg("svgs/triangle.svg");
		//triangle(tokenX - tokenSize/2, tokenY - tokenSize/2, tokenX + tokenSize/2, tokenY - tokenSize/2, tokenX, tokenY + tokenSize/2);
		//collider.collision(kind);
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
