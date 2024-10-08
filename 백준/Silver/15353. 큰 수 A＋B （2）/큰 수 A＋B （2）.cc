#include<iostream>
#include<cstring>
#include <string>
using namespace std;

int main() {
	int n1, n2;
	int idx = 0;
	int p = 0;
	int s1, s2;
	int ans[10001];
	char arr1[10001];
	char arr2[10001];
	cin >> arr1 >> arr2;
	s1 = strlen(arr1);
	s2 = strlen(arr2);
	while (s1 || s2 || p) {
		if (s1)	n1 = arr1[(s1--) - 1] - '0';
		if (s2)	n2 = arr2[(s2--) - 1] - '0';
		ans[idx++] = (n1 + n2 + p) % 10;
		p = n1 + n2 + p > 9 ? 1 : 0;
		n1 = n2 = 0;
	}
	for (int i = idx - 1; i >= 0; i--) {
		cout << ans[i];
	}
	return 0;
}