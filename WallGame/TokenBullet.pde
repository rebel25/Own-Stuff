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

	void makeBullet(float tokenX, float tokenY) {
		//color[] tokenColor {255, 255, 255};
		fill(255, 255, 255);
		makeTokenSvg("svgs/rectangle.svg");
		//ellipse(tokenX, tokenY, tokenSize, tokenSize);
		//collider.collision(kind);
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
	}

	void makeSpeed(float tokenX, float tokenY) {
		fill(0, 0, 0);
		makeTokenSvg("svgs/triangle.svg");
		//triangle(tokenX - tokenSize/2, tokenY - tokenSize/2, tokenX + tokenSize/2, tokenY - tokenSize/2, tokenX, tokenY + tokenSize/2);
		//collider.collision(kind);
		collider.fillTokenArray(tokenX, tokenY, tokenWidth, tokenHeight);
	}
}