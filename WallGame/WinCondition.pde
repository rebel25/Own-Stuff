class WinConditions {	


	void printPlayerLifes(float _fieldBot, float _playerPosX, float _fieldTop, int _lifes) {
		int fondtSize = 35;
		textSize(fondtSize);
		textAlign(CENTER);
		fill(field);
		text(_lifes, _playerPosX, (_fieldBot + _fieldTop/2 + fondtSize/3));
	}
}