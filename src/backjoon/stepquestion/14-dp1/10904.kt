package src.backjoon.stepquestion.`14-dp1`
//15-3
//sliver 3
// 약 참조 : https://st-lab.tistory.com/125

import java.io.*
lateinit var tileFabonacciArr : Array<Int>
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    tileFabonacciArr = Array(n+1){-1}
    bw.write("${tileFabonacci(n)}\n")
    
    br.close()
    bw.flush()
    bw.close()
}

/*
    1 타일
    00 타일 두종류
    n=1 ==> 1
    N=2 ==> 00, 11
    n=3 ==> 001, 100, 111
    n=4 ==> 0000, 0011, 1001, 1100, 1111
    n=5 ==> 00001, 00100, 10000, 11100, 11001, 10011, 00111, 11111

    1 -> 2 -> 3 -> 5 -> 8
    파보나치 수열의 증가량

    N의 범위는 무한

    첫 번째 줄에 지원이가 만들 수 있는 길이가 N인 모든 2진 수열의 개수를
     15746으로 나눈 나머지를 출력한다.

     !! 파보나치를 수행하고, 맨 마지막 결과값을 나누어줬는데,
     그렇다면 결과값이 너무 커져서 int형을 벗어날수가있다

     매 파보나치 결과를 나누어주고 해당 결과를 저장하자
*/

fun tileFabonacci(n : Int) : Int{
    if(n<=3){ 
        // 현재 타일의 규칙성 적용
        return n
    }
    if(tileFabonacciArr[n] == -1){
        tileFabonacciArr[n] = (tileFabonacci(n-1) + tileFabonacci(n-2)) % 15746
    }
    return tileFabonacciArr[n]
}