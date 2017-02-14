#include <stdio.h>
#include <string.h>

#define SIZE 4000

char buf[SIZE]; //buffer used to store input string
int len;
int length; //length of the longest palindromic string
int num; //number of LONGEST palidromic string

/* 
Check if string is palindromic with single character at the middle
*/
int span_single(int center) {
	int i = center - 1;
	int j = center + 1;
	/*
	This loop check 2 characters at opposite sides of the center character
	If they are same, then check next pair.
	If not, stop the loop.
	Based on the position of different character, we can calculate the length of CURRENT palindromic string
	*/
	while (i >= 0 && j < len && buf[i] == buf[j]) {
		--i;
		++j;
	}
	return (j - i - 1);
}

/*
Check if string is palindromic with 2 characters at the middle
*/
int span_double(int center) {
	int i = center;
	int j = center + 1;
	if (j >= len || buf[i] != buf[j]) return 0;
	--i;
	++j;
	/*
	This loop check 2 characters at opposite sides of the 2 center characters
	If they are same, then check next pair.
	If not, stop the loop.
	Based on the position of different character, we can calculate the length of CURRENT palindromic string
	*/
	while (i >= 0 && j < len && buf[i] == buf[j]) {
		--i;
		++j;
	}
	return (j - i - 1);
}

int main() {
	//freopen("input.txt", "r", stdin);
	int T, testcase;
	scanf("%d", &T); //input number of test cases
	fgets(buf, SIZE, stdin); //read (skip) the "\n" character remaining at 1st row
	for (testcase = 1; testcase <= T; ++testcase) {
		fgets(buf, SIZE, stdin); //read input string
		len = strlen(buf); //calculate length of input string
		if (buf[len - 1] == '\n') buf[--len] = 0; //remove last "\n" character because functon fgets() included the "\n" character in result

		//init counting data
		length = 0;
		num = 0;

		int i, j, k;
		/*
		At each position, we'll check the longest palindromic centered at that position
		There are 2 kind of palindromic: 1 or 2 center character(s)
		So we need to check both
		*/
		for (i = len - 1; i >= 0; --i) {
			j = span_single(i); //length of palindromic string type 1
			k = span_double(i); //length of palindromic string type 2
			if (j < k) j = k; //we only need to longest length
			if (j > length) {
				//new record
				//reset counting to 1
				length = j;
				num = 1;
			}
			else if (j == length) {
				//equal to old record
				//increase counting
				++num;
			}
		}
		//print result
		printf("%d %d", length, num);
	}
	return 0;
}