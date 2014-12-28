class TokenBullet extends Token {

  /*------------------------------------------------------------------
  ||  Initializer for Class/Object from Superclass Player
  -------------------------------------------------------------------*/
	TokenBullet() {
		super();
	}





	void makeBullet(float tokenX, float tokenY) {
		fill(255, 255, 255);
		ellipse(tokenX, tokenY, tokenSize, tokenSize);
	}






}