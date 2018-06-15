#include <iostream>
#include <string>
#include <stdio.h>
#include <vector>
#include <math.h>
using namespace std;
void swap(){

}
int main()
{
    int n, y;

    cin >> n; // 0<n<201
    //cout << n << endl;
    //vector<double> a[n];
    double a[n][n];
    double b[n][n];
    double change[n];
    double temp;
    double divide;
    double pivot;
    int i, j, k;
    for(i=0; i<n; i++){
        for(j=0; j<n; j++){
            cin >> temp;
            a[i][j]=temp;
            if(i==j){
                b[i][j]=1;
            }else{
                b[i][j]=0;
            }
        }
    }


    for(k=0; k<n; k++){
        for(i=0; i<n; i++){
            temp = abs(a[i][k]);
            if()
            if(abs(a[i][k]))
        }

        for(i=k+1; i<n; i++){
            divide = (a[i][k]/a[k][k]);
            for(j=0; j<n; j++){
                a[i][j] = a[i][j] + divide*(-1)*a[k][j];
                b[i][j] = b[i][j] + divide*(-1)*b[k][j];
            }
        }
    }

    temp = 1;
    for(i=0; i<n; i++){
        if(a[i][i]!=0 && a[i][i]!=1){
            temp = a[i][i];
        }
        for(j=0; j<n; j++){
            a[i][j] = a[i][j]/temp;
            b[i][j] = b[i][j]/temp;
        }
    }

    for(k=n-1; k>=0; k--){
        for(i=k-1; i>0; i--){
            divide = (a[i][k]/a[k][k]);
            for(j=0; j<n; j++){
                a[i][j] = a[i][j] + divide*(-1)*a[k][j];
                b[i][j] = b[i][j] + divide*(-1)*b[k][j];
            }
        }
    }

    for(i=0; i<n; i++){
        for(j=0; j<n; j++){
            printf(""%lf "",a[i][j]);
        }
        printf(""\n"");
    }
    printf(""------------\n"");

    for(i=0; i<n; i++){
        for(j=0; j<n; j++){
            printf(""%lf "",b[i][j]);
        }
        printf(""\n"");
    }


//    for (int i=0; i<n; i++){
//        for (int j=0; j<n; j++){
//            cin >> temp;
//            a[i].push_back(temp);
//        }
//    }
//    for (int i=0; i<n; i++){
//        vector<int> :: iterator iter = a[i].begin();
//        for (int j=0; iter!=a[i].end(); j++){
//            cout << *iter << endl;
//        }
//    }

    //getch();
    return 0;
}

