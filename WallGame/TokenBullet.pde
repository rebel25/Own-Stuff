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
		println("_step: "+_step);
		fill(255, 255, 255, 255-(_step*7));
		shapeMode(CORNER);
		shape(tokenSvg, tokenX, tokenY, tokenWidth, tokenHeight);
	}



	void makeBullet(float tokenX, float tokenY) {
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
		collider.collision();
		println("collider.collision(): "+collider.collision());

		if (collider.collision()) {
			if (getCollisionStep() < 36) {
				setTokenSpeed(0);
				makeTokenExplode("svgs/rectangle.svg");
				setCollisionStepUp(1);
			} else {
				setCollisionStep(0);
				resetToken(1);
			}
		} else {
			fill(255, 255, 255);
			makeTokenSvg("svgs/rectangle.svg");
		}
	}



	void makeSpeed(float tokenX, float tokenY) {
		fill(0, 0, 0);
		makeTokenSvg("svgs/triangle.svg");
		collider.collision();
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
	}
}