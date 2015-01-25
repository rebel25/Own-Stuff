class TokenBullet extends Token {

	PShape tokenSvg;

	TokenBullet() {
		super();
	}

	protected void makeTokenSvg(String _tokenPath) {
		tokenSvg = loadShape(_tokenPath);
		tokenSvg.disableStyle();
		noStroke();
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
		shape(tokenSvg, tokenX-_step/2, tokenY-_step/2, tokenWidth+_step/2, tokenHeight+_step/2);
	}

	void makeBullet(float tokenX, float tokenY) {
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
		collider.collision();

		if (collider.collision()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/rectangle.svg");
				setCollisionStepUp(1);
			} else {
					if (tokenX < width/2) {
  				playerKeyboard.setPlayerLifesDown(1);
  			}
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
			fill(player);
			makeTokenSvg("svgs/rectangle.svg");
		}
	}

	void makeSpeed(float tokenX, float tokenY) {
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
		collider.collision();

		if (collider.collision()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/MovePlayerRight.svg");
				setCollisionStepUp(1);
			} else {
				playerKeyboard.setPlayerSpeedUp(2);
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
			fill(player);
			makeTokenSvg("svgs/MovePlayerRight.svg");
		}

	}
}