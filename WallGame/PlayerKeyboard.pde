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
  void moveWithKeyboard() {
    switch(key) {
      case 'w': case 'W':
          setYPositionUp(3);
      break;
      case 's': case 'S':
          setYPositionDown(3);
      break;
      default:
        println("Default!");
      break;
    }
  }
}