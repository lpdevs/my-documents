#include "HinhTru.cpp"
#include <vector>
using namespace std;
class Test{
public:
       int chieuCaoMax;
       int soLuongTru;
       vector<HinhTru> hinhTru;
       
public:
       Test::Test(int hMax,vector<HinhTru> htru){
            chieuCaoMax = hMax;
            hinhTru = htru;
       }
};

