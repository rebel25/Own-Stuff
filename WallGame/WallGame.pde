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
void setup() {
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

void draw() {
	noStroke();
	fill(color1, color2, color3);
	rect(0, 0, width,height/10);
	fill(color1, color2, color3);
	rect(0, height/10*9, width,height);

	playerKeyboard.playerBody();
}

void keyPressed() {
  playerKeyboard.moveWithKeyboard();
}