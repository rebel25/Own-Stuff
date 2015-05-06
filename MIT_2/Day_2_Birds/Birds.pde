class Birds {

	String name;
	boolean isSleeping;
	boolean isSinging;
	int posX;
	int posY;
	int birdWidth = 50;
	int birdHeight = 70;
	int growth = 15;

	Birds(String _name, int _posX, int _posY) {
		name = _name;
		posX = _posX;
		posY = _posY;
		isSleeping = false;
		isSinging = false;
	}


	void draw() {
		if (isSinging == true) {
			fill(100, 0, 50);
			ellipse(posX, posY, birdWidth+growth, birdHeight+growth);
		} else {
			fill(50, 50, 50);
			ellipse(posX, posY, birdWidth, birdHeight);
		}	
	}

	void animate() {}


	void tap( int _topX, int _topY) {
		if (mousePressed && _topX < posX+birdWidth/2 && _topX > posX-birdWidth/2 && _topY < posY+birdHeight/2 && posY > _topY-birdHeight/2) {
			posX = _topX;
			posY = _topY;
			println("jeah");
			isSinging = true;
		} else {
			isSinging = false;
			println("ustadfi");
		}
	}
}