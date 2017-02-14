#include <vector>
#include "Test.cpp"
using namespace std;

class KiemTraTest{
public:
       int soLuongTest;
       vector<Test> test;
public:
       KiemTraTest(){
                     
       }
       KiemTraTest(vector<Test> _test){
            test = _test;  
       } 
};
