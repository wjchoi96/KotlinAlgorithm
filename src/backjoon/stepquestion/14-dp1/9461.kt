package src.backjoon.stepquestion.`14-dp1`
// sliver3
// 15-4

/*
    1 : 1
    2 : 1
    3 : 1
    4 : 1 + 1 ( 역삼각형 )
    5 : 2
    6 : 1 + 2 ( 역삼각형 )
    7 : 1 + 3
    8 : 1 + 4 ( 역삼각형 )
    9 : 2 + 5
    10 : 2 + 7 ( 역삼각형 )
    11 : 3 + 9
    12 : 4 : 12 ( 역삼각형 )
    13 : 16
    14 : 5 + 16
    15 : 5 + 16 + 7

    같은모양??

    1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16, 16

    1 : 1
    2 : 1번과 같음
    3 : 1번과 같음
    4 : 3번 + 1
    5 : 4번과 같음
    6 : 5번 + 1번 / 1 + 2 ( 역삼각형 )
    7 : 6번 + 2번 / 1 + 3
    8 : 7번 + 3번 / 1 + 4 ( 역삼각형 )
    9 : 8번 + 4번 / 2 + 5
    10 : 2 + 7 ( 역삼각형 )
    11 : 10번 + 6번 / 3 + 9
    12 : 4 : 12 ( 역삼각형 )
    13 : 16
    14 : 5 + 16
    15 : 5 + 16 + 7

    nasun(n) = nasun(n-1) + nasun(n-5)
    1 <= n <= 100
*/

import java.io.*
lateinit var nasunDp : Array<Long> 
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    nasunDp = Array(101){-1}
    for(i in 0 until n){
        bw.write("${nasun(br.readLine().toInt())}\n")
    }
    
    br.close()
    bw.flush()
    bw.close()
}

//nasun(n) = nasun(n-1) + nasun(n-5)
private fun nasun(n : Int) : Long{
    if(n<=3){
        return 1
    }else if(n<=5){
        return 2
    }
    if(nasunDp[n] == -1.toLong()){
        nasunDp[n] = nasun(n-1) + nasun(n-5)
    }
    return nasunDp[n]
}
