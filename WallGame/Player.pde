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
    playerSpeed = int(_step);
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
    playerLifes = int(_step);
  }
  protected void setPlayerLifesDown(int _step) {
    playerLifes = getPlayerLifes() - int(_step);
  }
  protected int getPlayerLifes() {
    return playerLifes;
  }
}