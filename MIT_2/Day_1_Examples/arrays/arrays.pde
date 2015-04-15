float[] grey;

void setup() {
  size(1280, 720);
  background(51);
  noStroke();
  noLoop();
}

void draw() {
  drawTarget(width*0.5, height*0.5, 800, 10);
  drawTarget(width*0.25, height*0.4, 200, 4);
  drawTarget(width*0.75, height*0.3, 120, 6);
}


void drawTarget(float xloc, float yloc, int size, int num) {
  float grayvalues = 255/num;
  float steps = size/num;
  for (int arraypos = 0; arraypos < 30; arraypos++) {
    grey = new float[30];
    for (int i = 0; i < num; i++) {
      float grayvalue = (i*grayvalues);
      grey[arraypos] = grayvalue;
      fill(grayvalue);
      //println("grayvalue: "+grayvalue);
      ellipse(xloc, yloc, size - i*steps, size + i*steps);
    }
  }
  printArray(grey);
}