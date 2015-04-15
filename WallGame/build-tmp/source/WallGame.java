import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

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


PlayerKeyboard playerKeyboard;
TokenBullet tokenBullet;
Token token;
Collider collider;
WinConditions winConditions;
Minim minim;
AudioPlayer audioPlayer;

boolean[] keys = new boolean[2];
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

  //background
  fieldTop = height/10;
  fieldBot = ((height/10)*9);
  winConditions = new WinConditions();
	playerKeyboard = new PlayerKeyboard();
	playerKeyboard.setYPosition(height/2);
	playerKeyboard.setXPosition(width/3);
	

	tokenBullet = new TokenBullet();
	collider = new Collider();
	
  
  //soundtrack
  minim = new Minim(this); 
  audioPlayer = minim.loadFile("music/theme.mp3");
  audioPlayer.loop();
}

/*------------------------------------------------------------------
||  Draw function for processing
||  Switch for displayed screen
-------------------------------------------------------------------*/
public void draw() {
	background(field);
  switch (winConditions.getGameState()) {
   	case 0 :
   		winConditions.startScreen();
   	break;
   	case 1 :
   		noStroke();
			fill(player);
			rect(0, 0, width, fieldTop);
			rect(0, fieldBot, width, height);

			playerKeyboard.playerBody();
			fill(player);
			tokenBullet.makeToken();
   	break;	
   	case 2 :
   		winConditions.finalScreen();
   	break;
  }
}

 /*------------------------------------------------------------------
  ||  Make Player move with keyboard - method
  ||  W || w => Player is going up.
  ||  S || s => Player is going down.
  -------------------------------------------------------------------*/
public void keyPressed() {
    if(key == 'w' || key == 'W') {
      keys[0] = true;
    }
    if(key == 's' || key == 'S') {
      keys[1] = true;
    }
    playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
  }

public void keyReleased() {
  if(key == 'w' || key == 'W') {
    keys[0] = false;
  }
  if(key == 's' || key == 'S') {
    keys[1] = false;
  }
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

	public boolean collision(){
	return box_box_p(tokenArray[0], tokenArray[1], tokenArray[2], tokenArray[3], playerArray[0], playerArray[1], playerArray[2], playerArray[3], playerArray[4], playerArray[5]);
	}

  /*------------------------------------------------------------------
  ||  fill player array
  -------------------------------------------------------------------*/
	public void fillPlayerArray(float _barLeft, float _fieldTop, float _barRight, float _gapTop, float _gapBottom, float _fieldBot){
		playerArray[0] = _barLeft;
		playerArray[1] = _fieldTop;
		playerArray[2] = _barRight;
		playerArray[3] = _gapTop;
		playerArray[4] = _gapBottom;
		playerArray[5] = _fieldBot;
	}

  /*------------------------------------------------------------------
  ||  fill token array
  -------------------------------------------------------------------*/
	public void fillTokenArray(float _tokenX, float _tokenY, float _tokenWidth, float _tokenHeight){
		tokenArray[0] = _tokenX;
		tokenArray[1] = _tokenY;
		tokenArray[2] = _tokenWidth;
		tokenArray[3] = _tokenHeight;
	}

  /*------------------------------------------------------------------
  ||  collision detection
  -------------------------------------------------------------------*/

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
				tokenBullet.activateAnimation(1);
			return true;
		} else {
			return false;
		}
	}
}	
/*class Music {





void playIntro() {
	intro = new Minim("music/intro.wav");
	file.play();
	}
}*/
class Player {
  PShape playerBodySvg;

  protected int playerWidth, playerPosY, playerPosX, gapHeight, playerSpeed, barLeft, barRight, gapTop, gapBottom, playerLifes;
          
  /*------------------------------------------------------------------
  ||  Initializer for Class/Object
  ||  Set playerWidth(int)
  ||  Set playerPosY(int)
  ||  Set playerPosX(int)
  ||  Set gapHeight(int)
  ||  Set playerSpeed(int)
  ||  Set barLeft(int)
  ||  Set playerLifes(int)
  -------------------------------------------------------------------*/
  Player() {
    playerWidth = fieldTop;
    playerPosY = 0;
    playerPosX = 0;
    gapHeight = 120;
    playerSpeed = 8;
    barLeft = 0;
    playerLifes = 10;
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

    // fill player array
    collider.fillPlayerArray(barLeft, fieldTop, barRight, gapTop, gapBottom, fieldBot);

    winConditions.printPlayerLifes(fieldBot, playerPosX, fieldTop, getPlayerLifes());
    winConditions.gameLost(getPlayerLifes());
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

 /*------------------------------------------------------------------
  ||  Set / Get player speed
  -------------------------------------------------------------------*/
  protected void setPlayerSpeed(int _step) {
    playerSpeed = PApplet.parseInt(_step);
  }
  protected void setPlayerSpeedUp(int _step) {
    playerSpeed = getPlayerSpeed() + _step;
  }
  protected int getPlayerSpeed() {
    return playerSpeed;
  }

/*------------------------------------------------------------------
  ||  Set / Get player lifes
  -------------------------------------------------------------------*/
  protected void setPlayerLifes(int _step) {
    playerLifes = PApplet.parseInt(_step);
  }
  protected void setPlayerLifesDown(int _step) {
    playerLifes = getPlayerLifes() - PApplet.parseInt(_step);
  }
  protected int getPlayerLifes() {
    return playerLifes;
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
    if (keys[0]) {
      if (playerPosY - gapHeight/2 < fieldTop) {
        setYPosition(fieldTop + gapHeight/2);
      } else if (playerPosY - gapHeight/2 == fieldTop) {
        setYPositionUp(0);
      } else {
        setYPositionUp(getPlayerSpeed());
      }
    }

    if(keys[1]) {
      if (playerPosY + gapHeight/2 > fieldBot) {
        setYPosition(fieldBot - gapHeight/2);
      } else if (playerPosY + gapHeight/2 == fieldBot) {
        setYPositionUp(0);
      } else {
        setYPositionDown(getPlayerSpeed());          
      }     
    }
  }
}
class Token {

	protected int kind, tokenSpeed, tokenWidth, tokenHeight, tokenX, tokenY, maxKind, collision, collisionStep, reset, animation;

	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/
	Token() {
		maxKind = 1;
		kind = 0;
		tokenSpeed = 4;
		tokenWidth = height/20;
		tokenHeight = height/20;
		tokenX = -10;
		tokenY = 0;
		collision = 0;
		collisionStep = 0;
		reset = 0;
		animation = 0;
	}

	float startYmin = fieldTop;
	float startYmax = fieldBot - tokenHeight;
	
	public void makeToken() {
		collider.collision();
		if (tokenX == -tokenWidth) {
			kind = PApplet.parseInt(random(0, maxKind+1));
			setTokenY(random(startYmin, startYmax));
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
					  resetToken(0);
					  setTokenX(-tokenWidth);
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
						setTokenSpeed(6);
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

  /*------------------------------------------------------------------
  ||  Set/Get Collision Step
  -------------------------------------------------------------------*/
  public void setCollisionStep(int _collisionStep) {
  	collisionStep = PApplet.parseInt(_collisionStep);
  }
  public void setCollisionStepUp(int _step) {
  	collisionStep = getCollisionStep() + _step;
  }
  public int getCollisionStep() {
  	return collisionStep;
  }

  public void activateAnimation(int _activate) {
  	if (_activate == 1) {
  		animation = 1;
  	}
  }
  public void deactivateAnimation(int _activate) {
  	if (_activate == 1) {
  		animation = 0;
  	}
  }
  public boolean getAnimation(){
  	if (animation == 1) {
  		return true;
  	} else {
  		return false;
  	}
  }


 	public boolean resetToken(int _reset) {
  	if (_reset > 0) {
  		reset = 1;
  		deactivateAnimation(1);
  		return true;
  	} else {
  		reset = 0;
  	return false;
  	}
  }
}
class TokenBullet extends Token {

	TokenBullet() {
		super();
	}

	PShape tokenSvg;

/*------------------------------------------------------------------
  ||  Token states
-------------------------------------------------------------------*/
	protected void makeTokenSvg(String _tokenPath) {
		tokenSvg = loadShape(_tokenPath);
		tokenSvg.disableStyle();
		noStroke();
		fill(player);
		shapeMode(CORNER);
		shape(tokenSvg, tokenX, tokenY, tokenWidth, tokenHeight);
	}

	protected void makeTokenExplode(String _tokenPath){
		tokenSvg = loadShape(_tokenPath);
		tokenSvg.disableStyle();
		noStroke();
		int _step = getCollisionStep();
		fill(player, 255-(_step*7));
		shapeMode(CORNER);
		shape(tokenSvg, tokenX-_step, tokenY-_step, tokenWidth+_step, tokenHeight+_step);
	}

	public void makeBullet(float tokenX, float tokenY) {
		//fill token Array
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);

		if (getAnimation()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/rectangle.svg");
				setCollisionStepUp(1);
			} else {
  			playerKeyboard.setPlayerLifesDown(1);
  			deactivateAnimation(1);
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
				makeTokenSvg("svgs/rectangle.svg");
		}
	}

	public void makeSpeed(float tokenX, float tokenY) {
	//fill player array	
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);

		if (getAnimation()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/trth.svg");
				setCollisionStepUp(1);
			} else {
				playerKeyboard.setPlayerSpeedUp(2);
				deactivateAnimation(1);
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
			makeTokenSvg("svgs/trth.svg");
		}
	}
}
class WinConditions {	

	int gameState = 0;

/*------------------------------------------------------------------
  ||  Display Playerlifes
  -------------------------------------------------------------------*/
	public void printPlayerLifes(float _fieldBot, float _playerPosX, float _fieldTop, int _lifes) {
		int fondtSize = 35;
		textSize(fondtSize);
		textAlign(CENTER);
		fill(field);
		text(_lifes, _playerPosX, (_fieldBot + _fieldTop/2 + fondtSize/3));
	}

/*------------------------------------------------------------------
  ||  create Screen Layouts
  ||  input for next screen
  -------------------------------------------------------------------*/
	public void startScreen() {
		textAlign(CENTER);
		int fondtSize = 60;

		textSize(fondtSize);
		fill(player);
		text("Start Game", width/2, height/2);

		textSize((fondtSize/3)*2);
		fill(player, 200);
		text("press space", width/2, (height/7)*4);

		if (keyPressed) {
			if( key == ' '){
				setGameState(1);
			}
		}
	}

	public void finalScreen() {
		textAlign(CENTER);
		int fondtSize = 60;

		textSize(fondtSize);
		fill(player);
		text("You Lost", width/2, height/2);

		textSize((fondtSize/3)*2);
		fill(player, 200);
		text("play again? press space", width/2, (height/7)*4);

		if (keyPressed) {
			if( key == ' '){
				setGameState(1);
				playerKeyboard.setPlayerLifes(10);
			}
		}
	}


	/*------------------------------------------------------------------
  ||  lose condition
  -------------------------------------------------------------------*/
	public void gameLost(int _playerLifes) {
		if (_playerLifes == 0) {
			setGameState(2);
		}
	}

/*------------------------------------------------------------------
  ||  set/get gamestate
  -------------------------------------------------------------------*/
	public int getGameState() {
		return gameState;
	}
	public void setGameState(int _step) {
		gameState = _step;
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
