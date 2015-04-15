class TokenBullet extends Token {

	TokenBullet() {
		super();
	}

	PShape tokenSvg;

/*------------------------------------------------------------------
  ||  Token states
-------------------------------------------------------------------*/
	protected void makeTokenSvg(String _tokenPath) {
		tokenSvg = loadShape(_tokenPath);
		tokenSvg.disableStyle();
		noStroke();
		fill(player);
		shapeMode(CORNER);
		shape(tokenSvg, tokenX, tokenY, tokenWidth, tokenHeight);
	}

	protected void makeTokenExplode(String _tokenPath){
		tokenSvg = loadShape(_tokenPath);
		tokenSvg.disableStyle();
		noStroke();
		int _step = getCollisionStep();
		fill(player, 255-(_step*7));
		shapeMode(CORNER);
		shape(tokenSvg, tokenX-_step, tokenY-_step, tokenWidth+_step, tokenHeight+_step);
	}

	void makeBullet(float tokenX, float tokenY) {
		//fill token Array
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);

		if (getAnimation()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/rectangle.svg");
				setCollisionStepUp(1);
			} else {
  			playerKeyboard.setPlayerLifesDown(1);
  			deactivateAnimation(1);
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
				makeTokenSvg("svgs/rectangle.svg");
		}
	}

	void makeSpeed(float tokenX, float tokenY) {
	//fill player array	
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);

		if (getAnimation()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/trth.svg");
				setCollisionStepUp(1);
			} else {
				playerKeyboard.setPlayerSpeedUp(2);
				deactivateAnimation(1);
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
			makeTokenSvg("svgs/trth.svg");
		}
	}
}