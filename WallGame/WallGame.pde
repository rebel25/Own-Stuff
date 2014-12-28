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
void setup() {
  size(640, 360);

  fieldTop = height/10;
  fieldBot = ((height/10)*9);
	playerKeyboard = new PlayerKeyboard();
	playerKeyboard.setYPosition(height/2);
	playerKeyboard.setXPosition(width/5);
	tokenBullet = new TokenBullet();


}

void draw() {
	background(255-color1, 255-color2, 255-color3);
	noStroke();

	fill(color1, color2, color3);
	rect(0, 0, width, fieldTop);
	rect(0, fieldBot, width, height);
	
	playerKeyboard.playerBody();
	//tokenBullet.makeBullet(20, 20);
	tokenBullet.makeToken();
}

void keyPressed() {
  playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
}

/*void createToken() {
	tokenBullet.tokenMovement();
}*/