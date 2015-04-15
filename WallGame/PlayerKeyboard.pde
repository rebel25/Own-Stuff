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

  void moveWithKeyboard(int fieldTop, int fieldBot) {
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