#include <iostream>
using namespace std;

const int TEXT_TYPE_LENGTH_MAX = 1000000000;
const int CHAR_NUM_MAX = 256;

int in_sub_s[CHAR_NUM_MAX];

int available_keys_num;
char * text_to_type;
char * sub_s_start;
char * sub_s_end;
int sub_s_chars_num;

/**
* Try to add one kind of character to substring
*/
bool enqueue_1_char() {
	char new_char = *(sub_s_end + 1);
	if ((new_char != 0) && (sub_s_chars_num < available_keys_num)) {
		do {
			++sub_s_end;
			++in_sub_s[new_char];

			new_char = *(sub_s_end + 1);
		} while ((new_char != 0) && in_sub_s[new_char]);
		++sub_s_chars_num;
		return true;
	} else {
		return false;
	}
}

/**
* Try to remove one kind of character from substring
*/
bool dequeue_1_char() {
	char old_char = *(sub_s_start);
	if (old_char != 0 && sub_s_chars_num > 0) {
		while (--in_sub_s[old_char]) {
			++sub_s_start;
			old_char = *(sub_s_start);
		}
		++sub_s_start;
		--sub_s_chars_num;
		return true;
	} else {
		return false;
	}
}

int main() {

	freopen("input.txt", "r", stdin);
	setbuf(stdout, NULL);
	char * text_to_type = new char[TEXT_TYPE_LENGTH_MAX];
	for (int i = 0; i < CHAR_NUM_MAX; ++i) {
		in_sub_s[i] = 0;
	}

	cin >> available_keys_num; 
	gets(text_to_type);//remove rest of current line
	while (available_keys_num > 0) {
		gets(text_to_type);

		sub_s_start = text_to_type;
		sub_s_end = text_to_type - 1;
		int sub_s_len_max = 0;

		do{
			while (enqueue_1_char()) {}
			int new_len = sub_s_end - sub_s_start + 1;
			if (sub_s_len_max < new_len) {
				sub_s_len_max = new_len;
			}
		} while (dequeue_1_char());
		
		cout << sub_s_len_max << endl;

		cin >> available_keys_num;
		gets(text_to_type);//remove rest of current line
	}	

	delete text_to_type;
	return 0;
}