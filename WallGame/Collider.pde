class Collider {

	protected int playerY, playerX, gapTop, gapBottom, barLeft, barRight, kind;
	protected float[] playerArray = {0, 0, 0, 0, 0, 0};
	protected float[] tokenArray = {0, 0, 0, 0};
	float[] overlap;
	//Player player;



	Collider() {
	playerY = 0;
	playerX = 0;
	gapTop = 0;
	gapBottom = 0;
	barLeft = 0;
	barRight = 0;
	kind = 0;
	}

	Player player;

	void collision(int _kind){
		kind = _kind;
		box_box_p(tokenArray[0], tokenArray[1], tokenArray[2], tokenArray[3], playerArray[0], playerArray[1], playerArray[2], playerArray[3], playerArray[4], playerArray[5]);
		//overlap(float[] box_box_p,)


	}

	void fillPlayerArray(float _barLeft, float _fieldTop, float _barRight, float _gapTop, float _gapBottom, float _fieldBot){
		playerArray[0] = _barLeft;
		playerArray[1] = _fieldTop;
		playerArray[2] = _barRight;
		playerArray[3] = _gapTop;
		playerArray[4] = _gapBottom;
		playerArray[5] = _fieldBot;
	}

	void fillTokenArray(float _tokenX, float _tokenY, float _tokenWidth, float _tokenHeight){
		tokenArray[0] = _tokenX;
		tokenArray[1] = _tokenY;
		tokenArray[2] = _tokenWidth;
		tokenArray[3] = _tokenHeight;
	}

	boolean box_box_p(float tx0, float ty0, float tx1, float ty1, float px0, float py0, float px1, float py1, float py2, float py3){
		
		float topOverlap = 0;
		float botOverlap = 0;
		float leftOverlap = 0;
		float rightOverlap = 0;

		float[] result = null;
		float topToken = min(ty0, ty1);
		float botToken = max(ty0, ty1);
		float leftToken = min(tx0, tx1);
		float rightToken = max(tx0, tx1);

		float topPlayer1 = min(py0, py1);
		float botPlayer1 = max(py0, py1);
		float topPlayer2 = min(py2, py3);
		float botPlayer2 = max(py2, py3);

		float leftPlayer = min(px0, px1);
		float rightPlayer = max(px0, px1);

		if(botToken <= topPlayer1 || botToken <= topPlayer2 || botPlayer1 <= topToken || botPlayer2 <= topToken || rightToken <= leftPlayer || rightPlayer <= leftToken){
			return false;
		} else {
			leftOverlap = (leftToken < leftPlayer) ? leftPlayer : leftToken;
			rightOverlap = (rightToken > rightPlayer) ? rightPlayer : rightToken;
			if (topToken < botPlayer1) {
				topOverlap = (topToken < topPlayer1) ? topPlayer1 : topToken;
				botOverlap = (botToken < botPlayer1) ? botPlayer1 : botToken;
			} else {
				topOverlap = (topToken < topPlayer2) ? topPlayer2 : topToken;
				botOverlap = (botToken < botPlayer2) ? botPlayer2 : botToken;
			}
			return true;//{leftOverlap, topOverlap, rightOverlap, botOverlap};
		}

		float aPx = leftOverlap - leftToken;
		float aPy = topOverlap - topToken;
		float aSx = rightOverlap - leftToken;
		float aSy = botOverlap - topToken - 1;
		float bPx = leftOverlap - leftPlayer;
		float bPy1 = topOverlap - topPlayer1;
		float bPy2 = topOverlap - topPlayer2;

		float widthOverlap = rightOverlap - leftOverlap;
		boolean foundCollision = false;

		player.playerBody();
		tokenBullet.makeToken();

		boolean pixelAtrans = true;
		boolean pixelBtrans = true;

		float pA = (aPy * (barRight - barLeft)) + aPx;
		float pB = (bPy1 * tokenArray[2]) + bPx;

		//float ax = aPx;
		//float ay = aPy;
		float bx = bPx;
		float by1 = bPy1;
		float by2 = bPy2;

		for (float ay = aPy; ay < aSy; ay++) {
			bx = bPx;
			for (float ax = aPx; ax < aSx; ax++) {
				pixelAtrans = alpha(playerBody.pixels[pA]) < ALPHALEVEL;
				pixelBtrans = alpha(makeToken.pixels[pB]) < ALPHALEVEL;

				if (!pixelAtrans && !pixelBtrans) {
					foundCollision = true;
					break;
				}
				pA ++;
				pB ++;
				bx ++;
			}
			if (foundCollision) break;
			pA = pA + (barRight - barLeft) - widthOverlap;
			pB = pB + tokenArray[2] - widthOverlap;
			by1++;
		}
		return foundCollision;
	}









/*

	boolean overLap(float[] box_box_p, color[] data1, color[] data2){
		float left = float[0];
		float top = float[1];
		float right = float[2];
		float bot = float[3];
		for(int y = top; y < bot; y++){
			for(int x = left; y < right; x++){

				color color1 = data1[(x - left) + (y - top) * (right - left)];
				color color2 = data2[(x - right) + (y - bot) * (right - left)];

			}
		}
	}*/
}	