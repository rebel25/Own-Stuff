class Token {

	protected int kind, tokenSpeed, tokenSize, tokenX, tokenY;


	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/
	TokenBullet tokenbullet;

	Token() {
		kind = 0;
		tokenSpeed = 3;
		tokenSize = 10;
		tokenX = 0;
		tokenY = 0;
	}
	float startYmin = fieldTop;
	float startYmax = fieldBot;


	void makeToken() {
		//float tokenX = -tokenSize;
		float tokenY = random(fieldTop, fieldBot);

		//tokenBullet.makeBullet(tokenX, tokenY);
		for (float tokenX = -tokenSize; tokenX < width; tokenX = tokenX + tokenSpeed) {
			tokenbullet.makeBullet(tokenX, tokenY);
		}


	}


	
}