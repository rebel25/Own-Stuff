class Token {

	protected int kind, tokenSpeed, tokenWidth, tokenHeight, tokenX, tokenY, maxKind, collision, collisionStep, reset;

	/*------------------------------------------------------------------
	||  Initializer for Class/Object
	||  Set kind(int)
	|| 	Set tokenSpeed(int)
	||  Set tokenSize(int)
	-------------------------------------------------------------------*/
	Token() {
		maxKind = 1;
		kind = 0;
		tokenSpeed = 4;
		tokenWidth = 15;
		tokenHeight = 15;
		tokenX = 0;
		tokenY = 0;
		collision = 0;
		collisionStep = 0;
		reset = 0;
	}
	
	float startYmin = fieldTop;
	float startYmax = fieldBot - tokenHeight;
	
	void makeToken() {
		if (tokenX == -tokenWidth) {
			kind = int(random(0, maxKind+1));
			setTokenY(random(startYmin, startYmax));
			resetToken(0);
		}

  /*------------------------------------------------------------------
  ||  check collision 
  -------------------------------------------------------------------*/
		switch (kind) {
			case 0 :
				if (tokenX <= width + tokenWidth) {
					if (reset == 0) {
						setTokenXRight(getTokenSpeed());
						tokenBullet.makeBullet(tokenX, tokenY);
					} else {

						setTokenSpeed(6);
						setTokenX(-tokenWidth);
					  resetToken(0);
					}
				} else {
					setTokenX(-tokenWidth);
					setTokenY(random(startYmin, startYmax));
				}
			break;	
			case 1 :
				if (tokenX <= width + tokenWidth) {
					if (reset == 0) {
					 	setTokenXRight(getTokenSpeed());
						tokenBullet.makeSpeed(tokenX, tokenY);
					} else {
						setTokenX(-tokenWidth);
						setTokenSpeed(6);
						resetToken(0);
					}
				} else {
					setTokenX(-tokenWidth);
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

  /*------------------------------------------------------------------
  ||  Set/Get Token speed
  -------------------------------------------------------------------*/
  void setTokenSpeed(int _tokenSpeed) {
  	tokenSpeed = int(_tokenSpeed);
  }
  void setTokenSpeedUp(int _step) {
  	tokenSpeed = getTokenSpeed() + _step;
  }
  void setTokenSpeedDown(int _step) {
  	tokenSpeed = getTokenSpeed() - _step;
  }
  int getTokenSpeed() {
  	return tokenSpeed;
  }

  /*------------------------------------------------------------------
  ||  Set/Get Collision Step
  -------------------------------------------------------------------*/
  void setCollisionStep(int _collisionStep) {
  	collisionStep = int(_collisionStep);
  }
  void setCollisionStepUp(int _step) {
  	collisionStep = getCollisionStep() + _step;
  }
  int getCollisionStep() {
  	return collisionStep;
  }


 	boolean resetToken(int _reset) {
  	if (_reset > 0) {
  		reset = 1;
  		return true;
  	} else {
  		reset = 0;
  	return false;
  	}
  }
}