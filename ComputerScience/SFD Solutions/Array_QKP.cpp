#include <stdio.h>

#define QUEEN 1
#define KNIGHT 2
#define PAW 3
#define MAX 1001

// 8 huong di cua knight va queen
int knight_direction[8][2] = {{1, 2}, {1, -2},{2, 1}, {2, -1}, {-1, 2}, {-1, -2},{-2, 1}, {-2, -1}};
int queen_direction[8][2] = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

//luu tru toa do cua cac quan co
typedef struct POINT
{
    int x;
    int y;
} ;

int N, M, Answer;
POINT queen [MAX], knight [MAX];
int matrix_board [MAX][MAX];
int count, queenLen, knightLen, tmp, x, y;    

// dem nhung vi tri ko an toan
int process(){
	int count = 0;
	int i, j;
	int x;
	int y;

	// dem nhung vi tri knight an
	for (i = 0; i < knightLen; i++) {
			POINT p = knight[i];
			
			// ktra theo 8 huong
			for (j = 0; j < 8; j++) {
				x = p.x + knight_direction[j][0];
				y = p.y + knight_direction[j][1];
				
				if (x > 0 && x <= N && y > 0 && y <= M){
					if (matrix_board[x][y] == 0) {
						count++;
						matrix_board[x][y] = -1;
					}					
				}
			}
		}

	// dem vi tri queen an
	for (i = 0; i < queenLen; i++) {
		POINT p = queen[i];
			
		// ktra theo 8 huong
			for (j = 0; j < 8; j++) {
				x = p.x + queen_direction[j][0];
				y = p.y + queen_direction[j][1];
				
				// di toi khi nao toi goc hoac bi chan
				while (true) {
					if (x < 1 || x > N || y < 1 || y > M || matrix_board[x][y] > 0 || matrix_board[x][y] > 0){
						break;
					}
					
					if (matrix_board[x][y] == 0){
						count++;
						matrix_board[x][y] = -1;
					}
					
					x += queen_direction[j][0];
					y += queen_direction[j][1];
				}
			}
		}

	return count;
}

int main(void)
{
   
    freopen("input.txt", "r", stdin);
	count = 0;
	
	while (true){

			scanf("%d \n", &N);
			scanf("%d \n", &M);
			count ++;

			if (N == 0) {
				break;
			}

			// reset ma tran
			tmp = 0;
			for (int i = 0; i < N+1; i++) {
				for (int j = 0; j < M+1; j++) {
					matrix_board[i][j] = 0;
				}
			}
			Answer = N * M;

			// doc du lieu va luu toa do vao mang
			// queen
			scanf("%d \n", &queenLen);
			for (int i = 0; i < queenLen; i++) {
				scanf("%d \n", &x);
				scanf("%d \n", &y);
				
				queen[i].x = x;
				queen[i].y = y;
				matrix_board[x][y] = QUEEN;
			}
			Answer -= queenLen;

			// knight
			scanf("%d \n", &knightLen);
			for (int i = 0; i < knightLen; i++) {
				scanf("%d \n", &x);
				scanf("%d \n", &y);
				
				knight[i].x = x;
				knight[i].y = y;
				matrix_board[x][y] = KNIGHT;
			}
			Answer -= knightLen;

			// paw
			scanf("%d \n", &tmp);
			for (int i = 0; i < tmp; i++) {
				scanf("%d \n", &x);
				scanf("%d \n", &y);

				matrix_board[x][y] = PAW;
			}
			Answer -= tmp;

			// goi ham chinh
			Answer -= process();
			printf("Board %d has %d safe squares.", count, Answer);
			printf("\n");
	}
    return 0;
}