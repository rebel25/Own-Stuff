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
void setup() {
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

void draw() {
	background(color1, color2, color3);
	noStroke();

	fill((255-color1), (255-color2), (255-color3));
	rect(0, 0, width, fieldTop);
	rect(0, fieldBot, width, height);
	
	playerKeyboard.playerBody();
	tokenBullet.makeToken();
}

void keyPressed() {
  playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
}

/*void createToken() {
	tokenBullet.tokenMovement();
}*/