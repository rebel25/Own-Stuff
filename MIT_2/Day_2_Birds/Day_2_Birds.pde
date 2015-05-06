// Singin Birds

Birds vogel;
Birds vogel1;



void setup() {
	size(512, 512);



	vogel = new Birds("Daniel", 200, 200);
	vogel1 = new Birds("Jan", 100, 100);

	//Vogel.isSinging = true;
}

void draw() {
	background(155);

	vogel.animate();
	vogel.draw();
	vogel.tap(mouseX, mouseY);

	vogel1.animate();
	vogel1.draw();
	vogel1.tap(mouseX, mouseY);
}