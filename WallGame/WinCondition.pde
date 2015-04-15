class WinConditions {	

	int gameState = 0;

/*------------------------------------------------------------------
  ||  Display Playerlifes
  -------------------------------------------------------------------*/
	void printPlayerLifes(float _fieldBot, float _playerPosX, float _fieldTop, int _lifes) {
		int fondtSize = 35;
		textSize(fondtSize);
		textAlign(CENTER);
		fill(field);
		text(_lifes, _playerPosX, (_fieldBot + _fieldTop/2 + fondtSize/3));
	}

/*------------------------------------------------------------------
  ||  create Screen Layouts
  ||  input for next screen
  -------------------------------------------------------------------*/
	void startScreen() {
		textAlign(CENTER);
		int fondtSize = 60;

		textSize(fondtSize);
		fill(player);
		text("Start Game", width/2, height/2);

		textSize((fondtSize/3)*2);
		fill(player, 200);
		text("press space", width/2, (height/7)*4);

		if (keyPressed) {
			if( key == ' '){
				setGameState(1);
			}
		}
	}

	void finalScreen() {
		textAlign(CENTER);
		int fondtSize = 60;

		textSize(fondtSize);
		fill(player);
		text("You Lost", width/2, height/2);

		textSize((fondtSize/3)*2);
		fill(player, 200);
		text("play again? press space", width/2, (height/7)*4);

		if (keyPressed) {
			if( key == ' '){
				setGameState(1);
				playerKeyboard.setPlayerLifes(10);
			}
		}
	}


	/*------------------------------------------------------------------
  ||  lose condition
  -------------------------------------------------------------------*/
	void gameLost(int _playerLifes) {
		if (_playerLifes == 0) {
			setGameState(2);
		}
	}

/*------------------------------------------------------------------
  ||  set/get gamestate
  -------------------------------------------------------------------*/
	int getGameState() {
		return gameState;
	}
	void setGameState(int _step) {
		gameState = _step;
	}
}