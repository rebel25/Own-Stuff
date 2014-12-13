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
  playerPosY = int(_position);
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
    playerPosX = int(_position);
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