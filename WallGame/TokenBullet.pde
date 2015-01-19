class TokenBullet extends Token {

	TokenBullet() {
		super();
	}


	void makeBullet(float tokenX, float tokenY) {
		fill(255, 255, 255);
		ellipse(tokenX, tokenY, tokenSize, tokenSize);
		collider.collision(kind);
		collider.fillTokenBulletArray(tokenX, tokenY, tokenSize);
	}

	void makeSpeed(float tokenX, float tokenY) {
		fill(0, 0, 0);
		triangle(tokenX - tokenSize/2, tokenY - tokenSize/2, tokenX + tokenSize/2, tokenY - tokenSize/2, tokenX, tokenY + tokenSize/2);
		collider.collision(kind);
	}
}