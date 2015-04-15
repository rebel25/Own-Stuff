void setup() {
  noLoop();
}
int input = 80;

void draw() {
  divideNumber(input);
}

void divideNumber(int number) {
  int newnumber = number;
  if(newnumber % 2 == 0){
    newnumber = number / 2;
    divideNumber(newnumber);
    println("newnumber: "+newnumber);
  } else {
    println("Zahl nicht gerade");
  }
}
