class Collider {

	protected int playerY, playerX, gapTop, gapBottom, barLeft, barRight, kind;
	protected int[] playerArray = {0, 0, 0, 0, 0, 0};
	protected float[] tokenBulletArray = {0, 0, 0};
	PVector player1;
	PVector player11;
	PVector tokenBullet;

	Collider() {
	playerY = 0;
	playerX = 0;
	gapTop = 0;
	gapBottom = 0;
	barLeft = 0;
	barRight = 0;
	kind = 0;
	}

	Player player;

	void collision(int _kind){
		kind = _kind;
		//PVector playerVector1 = new Pvector(playerArray[2], playerArray[4]);
		player1 = new PVector(playerArray[2], playerArray[4], 0);
		player1.add(0, playerArray[0] - playerArray[4], 0);
		player1.add(playerArray[3] - playerArray[2], 0, 0);
		player1.add(0, -playerArray[0] - playerArray[4], 0);

		player11 = new PVector(playerArray[2], playerArray[5], 0);
		player11.add(0, -playerArray[5] - playerArray[1], 0);
		player11.add(playerArray[3] - playerArray[2], 0, 0);
		player11.add(0, playerArray[5] - playerArray[1], 0);

		tokenBullet = new PVector(tokenBulletArray[0],tokenBulletArray[1], 0);
		
		if (kind == 1) {
			if (is_point_near_line(PVector player1, PVector player11, PVector tokenBullet) == true);
			collision = true;
		}
		/*
		v1 = new playerVector1(playerArray[2], playerArray[4]);
		v2 = new playerVector1(playerArray[2], playerArray[0]);
		v2 = new playerVector2(playerArray[2], playerArray[0]);
		v3 = new playerVector2(playerArray[3], playerArray[0]);
		v3 = new playerVector3(playerArray[3], playerArray[0]);
		v4 = new playerVector3(playerArray[3], playerArray[4]);

		v5 = new playerVector4(playerArray[2], playerArray[5]);
		v6 = new playerVector4(playerArray[2], playerArray[1]);
		v6 = new playerVector5(playerArray[2], playerArray[1]);
		v7 = new playerVector5(playerArray[3], playerArray[1]);
		v7 = new playerVector6(playerArray[3], playerArray[1]);
		v8 = new playerVector6(playerArray[3], playerArray[5]);
*/
	}

	boolean is_point_near_line(PVector v0,Vector v1, PVector p){
			PVector pnl = point_nearest_line(v0, v1, p);
			if(pnl != null){
				float d = sqrt((p.x-pln.x)*(p.x-pln.x) + (p.y-pln.y)*(p.y*pln.y))
				if ( d <= tokenBulletArray[2])
					return true;
			}
			return false;
		}

	void fillPlayerArray(int _gapTop, int _gapBottom, int _barLeft, int _barRight, int _fieldTop, int _fieldBot){
		playerArray[0] = _gapTop;
		playerArray[1] = _gapBottom;
		playerArray[2] = _barLeft;
		playerArray[3] = _barRight;
		playerArray[4] = _fieldTop;
		playerArray[5] = _fieldBot;
	}

	void fillTokenBulletArray(float _tokenX, float _tokenY, float _tokenSize){
		if (kind == 0) {
			tokenBulletArray[0] = _tokenX;
			tokenBulletArray[1] = _tokenY;
			tokenBulletArray[2] = _tokenSize;
		}
	}
}