// Daniel Neumayr - 2014

/*------------------------------------------------------------------
||  Initializer for Class/Object from Superclass Player
-------------------------------------------------------------------*/
//PlayerMouse playerMouse;
PlayerKeyboard playerKeyboard;
PlayerKeyboard playerKeyboard2;
TokenBullet tokenBullet;
Token token;
Collider collider;
WinConditions winConditions;

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

  fieldTop = height/10;
  fieldBot = ((height/10)*9);
	playerKeyboard = new PlayerKeyboard();
	playerKeyboard.setYPosition(height/2);
	playerKeyboard.setXPosition(width/5);

	playerKeyboard2 =new PlayerKeyboard();
	playerKeyboard2.setYPosition(height/2);
	playerKeyboard2.setXPosition((width/5)*4);

	tokenBullet = new TokenBullet();
	tokenBullet.setTokenX(-10);
  collider = new Collider();
  winConditions = new WinConditions();
}

void draw() {
	background(field);
	noStroke();

	fill(player);
	rect(0, 0, width, fieldTop);
	rect(0, fieldBot, width, height);

	playerKeyboard2.playerBody();
	fill(player);
	playerKeyboard.playerBody();
	tokenBullet.makeToken();
}

void keyPressed() {
  playerKeyboard.moveWithKeyboard(fieldTop, fieldBot);
}