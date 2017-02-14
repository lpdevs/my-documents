#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define maxN 101

char ref[]="machula";
char st[maxN];
int pos_1, pos_2, pos_3;

/* Ham tra ve vi tri cua ki tu *des trong chuoi *source */
int position(char *source, char *des) {
	int i;
	for (i=0; i<strlen(source); i++) 
		if (source[i]==des[0]) return i;
}

/* 
	case_A: machula13 + 75425 = 77038	=> 'machula' nam o toan hang dau tien
	Su dung ham cat chuoi strncpy, cat ra toan hang thu 2 (B) va tong (Sum)
	Su dung ham atoi de tinh gia tri toan hang thu 1 va in ra ket qua.
*/
void case_A() {
	char B[maxN], Sum[maxN];
	int len_B, len_S, A;
	
	len_B=pos_2-pos_1-3;
	strncpy(B, st+pos_1+2, len_B);
	B[len_B] = '\0';
	len_S=strlen(st)-pos_2-2;
	strncpy(Sum, st+pos_2+2, len_S);
	Sum[len_S] = '\0';
	
	//printf("%s-%s\n", B, Sum);
	A = atoi(Sum) - atoi(B);
	printf("%d + %s = %s\n", A, B, Sum);
}

/* 
	case_B: 3247 + 5machula2 = 3749		=> 'machula' nam o toan hang thu hai
	Su dung ham cat chuoi strncpy, cat ra toan hang thu 1 (A) va tong (Sum)
	Su dung ham atoi de tinh gia tri toan hang thu 2 va in ra ket qua.
*/
void case_B() {
	char A[maxN], Sum[maxN];
	int len_A, len_S, B;
	
	len_A=pos_1-1;
	strncpy(A, st+0, len_A);
	A[len_A] = '\0';
	len_S=strlen(st)-pos_2-2;
	strncpy(Sum, st+pos_2+2, len_S);
	Sum[len_S] = '\0';
	
	//printf("%s-%s\n", B, Sum);
	B = atoi(Sum) - atoi(A);
	printf("%s + %d = %s\n", A, B, Sum);
}

/* 
	case_C: 23 + 47 = machula			=> 'machula' nam o tong
	Su dung ham cat chuoi strncpy, cat ra toan hang thu 1 (A) va thu 2 (B)
	Su dung ham atoi de tinh gia tri tong (Sum) va in ra ket qua.
*/
void case_C() {
	char A[maxN], B[maxN];
	int len_A, len_B, Sum;
	
	len_A=pos_1-1;
	strncpy(A, st+0, len_A);
	A[len_A] = '\0';
	len_B=pos_2-pos_1-3;
	strncpy(B, st+pos_1+2, len_B);
	B[len_B] = '\0';
	
	//printf("%s-%s\n", B, Sum);
	Sum = atoi(A) + atoi(B);
	printf("%s + %s = %d\n", A, B, Sum);
}

void process() {
	/* Tim vi tri cua cac ki tu '+', '=', 'm' trong chuoi input */
	pos_1 = position(st, "+");
	pos_2 = position(st, "=");
	pos_3 = position(st, "m");
	
	/* Xu ly lan luot vi tri cua chuoi 'machula' */
	/* 
		case_A: machula13 + 75425 = 77038	=> 'machula' nam o toan hang dau tien
		case_B: 3247 + 5machula2 = 3749		=> 'machula' nam o toan hang thu hai
		case_C: 23 + 47 = machula			=> 'machula' nam o tong
	*/
	if (pos_3<pos_1) case_A();
	else if (pos_3<pos_2) case_B();
		else case_C();

	//printf("%d %d %d\n", pos_1, pos_2, pos_3);
}

int main() {
	int Tc;
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	setbuf(stdout, NULL);

	scanf("%d", &Tc);
	while (Tc>0) {
		/* Doc tung test case va goi ham process() de tim ket qua */
		do {
			gets(st);
		} while (st[0]=='\0');
		//printf("%s %d\n", st, strlen(st));
		process();
		Tc--;
	}
	return 0;
}