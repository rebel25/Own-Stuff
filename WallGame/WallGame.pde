// Daniel Neumayr - 2014

/*------------------------------------------------------------------
||  Initializer for Class/Object from Superclass Player
-------------------------------------------------------------------*/
import ddf.minim.*;

PlayerKeyboard playerKeyboard;
TokenBullet tokenBullet;
Token token;
Collider collider;
WinConditions winConditions;
Minim minim;
AudioPlayer audioPlayer;

boolean[] keys = new boolean[2];
int color1 = int(random(255));
int color2 = int(random(255));
int color3 = int(random(255));
color field = color(color1, color2, color3);
color player = color((255-color1), (255-color2), (255-color3));
int fieldTop;
int fieldBot;

/*------------------------------------------------------------------
||  Setup function for processing
-------------------------------------------------------------------*/
void setup() {
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
void draw() {
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
void keyPressed() {
    if(key == 'w' || key == 'W') {
      keys[0] = true;
    }
    if(key == 's' || key == 'S') {
      keys[1] = true;
    }
    playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
  }

void keyReleased() {
  if(key == 'w' || key == 'W') {
    keys[0] = false;
  }
  if(key == 's' || key == 'S') {
    keys[1] = false;
  }
  playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
}