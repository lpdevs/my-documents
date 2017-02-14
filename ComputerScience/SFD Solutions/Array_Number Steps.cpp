#include <iostream>
using namespace std;

int main() {
	
	int T,x,y;
	//freopen("input.txt","r",stdin);
	scanf("%d",&T);
	for (int i = 0; i < T; i++)
	{
		scanf("%d %d",&x,&y);
		/* Notice the rule of setting number
		- x should be equal to y or y + 2, otherwise there is no number
		*/
		if (x == y){
			if (y%2==0)
				printf("%d\n",y*2);
			else 
				printf("%d\n",y*2-1);
		} else if (x == y+2){
			if (y%2==0)
				printf("%d\n",y*2+2);
			else 
				printf("%d\n",y*2+1);
		}
		else printf("No Number\n");
	}

	return 0;
}