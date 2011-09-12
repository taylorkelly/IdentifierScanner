
struct node {
 struct node *left,*right;
 int val;
};

struct element {
 int discsize;
 int next;
};
struct complex { float rp, ip; } ;





float value, fixed, floated;


long seed;


int permarray[10 +1];

unsigned int pctr;


struct node *tree;


int stack[3 +1];
struct element cellspace[18 +1];
int freelist, movesdone;



int ima[40 +1][40 +1], imb[40 +1][40 +1], imr[40 +1][40 +1];
float rma[40 +1][40 +1], rmb[40 +1][40 +1], rmr[40 +1][40 +1];


int piececount[3 +1], class[12 +1], piecemax[12 +1];
int puzzl[511 +1], p[12 +1][511 +1], n, kount;


int sortlist[5000 +1], biggest, littlest, top;


struct complex z[256 +1], w[256 +1], e[129 +1];
float zr, zi;

void Initrand () {
    seed = 74755L;
}

int Rand () {
    seed = (seed * 1309L + 13849L) & 65535L;
    return( (int)seed );
}




void bInitarr() {
 int i;
 long temp;
 Initrand();
 biggest = 0; littlest = 0;
 for ( i = 1; i <= 500; i++ ) {
     temp = Rand();

     sortlist[i] = (int)(temp - (temp/100000L)*100000L - 50000L);
     if ( sortlist[i] > biggest ) biggest = sortlist[i];
     else if ( sortlist[i] < littlest ) littlest = sortlist[i];
 }
}

void Bubble(int run) {
 int i, j;
 bInitarr();
 top=500;

 while ( top>1 ) {

  i=1;
  while ( i<top ) {

   if ( sortlist[i] > sortlist[i+1] ) {
    j = sortlist[i];
    sortlist[i] = sortlist[i+1];
    sortlist[i+1] = j;
   }
   i=i+1;
  }

  top=top-1;
 }
 if ( (sortlist[1] != littlest) || (sortlist[500] != biggest) )
 printf ( "Error3 in Bubble.\n");
 printf("%d\n", sortlist[run + 1]);
}

int main()
{
 int i;
 for (i = 0; i < 100; i++) Bubble(i);
 return 0;
}
