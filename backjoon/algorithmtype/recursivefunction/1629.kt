//sliver1
/*
    바킹독님 재귀함수 단원 강의 중 제시된 연습문제

    A를 B번 곱한 수를 알고싶다
    구하려는 수가 매우 커질 수 있으니 C로 나눈 나머지를 구하는 프로그램 작성

    1. 반복
    2. 재귀

    1<= A,B,C <= 2,147,483,647

    범위가 매우 크니 자료형 범위를 생각하며 풀어야한다

    n 을 i 번곱한다
*/

/*
    제출
    1. 메모리 초과
    - 스택의 메모리를 넘은듯 하다

    2. 성공
    - 점화식을 사용해서 풀이
    - 바킹독님 코드 참고
*/

import java.io.*
import java.util.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val a = st.nextToken().toInt()
    val b = st.nextToken().toInt()
    val c = st.nextToken().toInt()

    val res = nMod2(a, b, c)
    bw.write("$res\n")

    bw.flush()
    bw.close()
    br.close()
}

/*
    10, 11, 12

    10, 5, 12
    10, 2, 12
    10, 1, 12
    
    10%12 = 10
    10*10%12
    i = 1 => 100%12(4)

    4*4%12 
    i = 2 =>  4

    4*4%12 
    i = 3 => 4*10%12(4)
    
*/

/*
    12^116 mod 67을 구하고 싶다
    이때 12^58승의 값이 4라는걸 안다면?
    결과는 4*4 라는 것을 알 수 있다

    1승을 계산할 수 있다
    k승을 계산했으면 2k + 1도 O(1)에 계산 가능

    n^i % r  
    => n^(i/2) % 2 * n^(i/2) % 2

    i 가 짝수라면 그대로 줘도 된다
    i 가 홀수라면 * n 을 한번 더 해줘야한다
*/

private fun nMod2(n : Int, i : Int, r : Int) : Long{
    if(i == 1){
        return (n%r).toLong()
    }
    var res : Long = nMod2(n, i/2, r)
    res = res * res % r
    if(i%2 == 0) return res
    return res * n % r
}

private fun nMod(n : Int, i : Int, r : Int) : Int{
    if(i == 1){
        return n%r
    }
    return nMod(n*n%r, i-1, r)
}