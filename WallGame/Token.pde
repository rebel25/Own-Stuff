class Token {

	protected int kind, tokenSpeed, tokenSize;


	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/

	Token () {
		kind = 0;
		tokenSpeed = 3;
		tokenSize = 10;
	}

	float startY;
	int startX;

	protected void makeBuddy(int fieldTop, int fieldBot) {
		startY = random(fieldTop+tokenSize/2, fieldBot-tokenSize/2);
		startX = -tokenSize;

		ellipse(startX, startY, tokenSize, tokenSize);
	}
}