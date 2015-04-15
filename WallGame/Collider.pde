class Collider {

	protected int playerY, playerX, gapTop, gapBottom, barLeft, barRight, kind;
	protected float[] playerArray = {0, 0, 0, 0, 0, 0};
	protected float[] tokenArray = {0, 0, 0, 0};

	Collider() {
	playerY = 0;
	playerX = 0;
	gapTop = 0;
	gapBottom = 0;
	barLeft = 0;
	barRight = 0;
	kind = 0;
	}

	boolean collision(){
	return box_box_p(tokenArray[0], tokenArray[1], tokenArray[2], tokenArray[3], playerArray[0], playerArray[1], playerArray[2], playerArray[3], playerArray[4], playerArray[5]);
	}

  /*------------------------------------------------------------------
  ||  fill player array
  -------------------------------------------------------------------*/
	void fillPlayerArray(float _barLeft, float _fieldTop, float _barRight, float _gapTop, float _gapBottom, float _fieldBot){
		playerArray[0] = _barLeft;
		playerArray[1] = _fieldTop;
		playerArray[2] = _barRight;
		playerArray[3] = _gapTop;
		playerArray[4] = _gapBottom;
		playerArray[5] = _fieldBot;
	}

  /*------------------------------------------------------------------
  ||  fill token array
  -------------------------------------------------------------------*/
	void fillTokenArray(float _tokenX, float _tokenY, float _tokenWidth, float _tokenHeight){
		tokenArray[0] = _tokenX;
		tokenArray[1] = _tokenY;
		tokenArray[2] = _tokenWidth;
		tokenArray[3] = _tokenHeight;
	}

  /*------------------------------------------------------------------
  ||  collision detection
  -------------------------------------------------------------------*/

	boolean box_box_p(float tx0, float ty0, float tx1, float ty1, float px0, float py0, float px1, float py1, float py2, float py3){
		
		float topOverlap = 0;
		float botOverlap = 0;
		float leftOverlap = 0;
		float rightOverlap = 0;

		float topToken = min(ty0, ty0+ty1);
		float botToken = max(ty0, ty0+ty1);
		float leftToken = min(tx0, tx1);
		float rightToken = max(tx0, tx1);

		float topPlayer1 = min(py0, py1);
		float botPlayer1 = max(py0, py1);
		float topPlayer2 = min(py2, py3);
		float botPlayer2 = max(py2, py3);

		float leftPlayer = min(px0, px1);
		float rightPlayer = max(px0, px1);

		if(((botToken >= topPlayer2) || (topToken <= botPlayer1)) && ((leftToken <= rightPlayer) && (leftToken >= leftPlayer) || (rightToken >= leftPlayer) && (rightToken <= rightPlayer))){
				tokenBullet.activateAnimation(1);
			return true;
		} else {
			return false;
		}
	}
}	