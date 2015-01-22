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