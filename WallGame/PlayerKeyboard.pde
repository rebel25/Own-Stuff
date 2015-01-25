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
    switch(key) {
      case 'w': case 'W':
        if (playerPosY - gapHeight/2 < fieldTop) {
          setYPosition(fieldTop + gapHeight/2);
        } else if (playerPosY - gapHeight/2 == fieldTop) {
          setYPositionUp(0);
        } else {
          setYPositionUp(getPlayerSpeed());
        }
      break;
      case 's': case 'S':
        if (playerPosY + gapHeight/2 > fieldBot) {
          setYPosition(fieldBot - gapHeight/2);
        } else if (playerPosY + gapHeight/2 == fieldBot) {
          setYPositionUp(0);
        } else {
          setYPositionDown(getPlayerSpeed());          
        }
      break;
      default:
        println("Default!");
      break;
    }
  }
}