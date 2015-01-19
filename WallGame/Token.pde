class Token {

	protected int kind, tokenSpeed, tokenSize, tokenX, tokenY, maxKind, collision;

	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/
	//TokenBullet tokenbullet;

	Token() {
		maxKind = 1;
		kind = 0;
		tokenSpeed = 8;
		tokenSize = 10;
		tokenX = 0;
		tokenY = 0;
		collision = 0;
	}
	float startYmin = fieldTop + tokenSize/2;
	float startYmax = fieldBot - tokenSize/2;
	
	void makeToken() {
		if (tokenX == -tokenSize) {
			kind = int(random(0, maxKind+1));
			setTokenY(random(startYmin, startYmax));
			println("kind: "+kind);
		}

  /*------------------------------------------------------------------
  ||  check collision 
  -------------------------------------------------------------------*/

		switch (kind) {
			case 0 :
				if (tokenX <= width + tokenSize) {
					setTokenXRight(tokenSpeed);
					println("tokenX: "+tokenX);
					tokenBullet.makeBullet(tokenX, tokenY);
				} else {
					setTokenX(-tokenSize);
					setTokenY(random(startYmin, startYmax));
				}
			break;	
			case 1 :
				if (tokenX <= width + tokenSize) {
					setTokenXRight(tokenSpeed);
					//println("tokenX: "+tokenX);
					tokenBullet.makeSpeed(tokenX, tokenY);
				} else {
					setTokenX(-tokenSize);
					setTokenY(random(startYmin, startYmax));
				}
			break;	
		}
	}

  /*------------------------------------------------------------------
  ||  Set/Get X Position of Token
  -------------------------------------------------------------------*/

	void setTokenX(float _position){
		tokenX = int(_position);
	}

  void setTokenXRight(int _step) {
	tokenX = getTokenX() + _step;
  }

  void setTokenXLeft(int _step) {
	tokenX = getTokenX() - _step;
  }

  int getTokenX() {
	return tokenX;
  }

  /*------------------------------------------------------------------
  ||  Set/Get Y Position of Token
  -------------------------------------------------------------------*/

	void setTokenY(float _position){
		tokenY = int(_position);
	}

  void setTokenYDown(int _step) {
	tokenY = getTokenY() + _step;
  }

  void setTokenYUp(int _step) {
	tokenY = getTokenY() - _step;
  }

  int getTokenY() {
	return tokenY;
  }
}