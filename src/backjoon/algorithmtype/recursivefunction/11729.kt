//sliver1
/*
    바킹독님 재귀 단원에서 제시된 연습문제

    하노이의 탑 원반 옮기기
    n개의 원반을 a에서 c로 옮기는과정

    1. n-1개의 원반을 a to b로 옮긴다
    2. 제일큰 원반n을 a to c로 옮긴다
    3. n-1개의 원반을 b to c 로 옮긴다

    귀납적 사고
    => 원판이 n-1개일때 옮길 수 있다면 원판이 n개일떄도 옮길 수 있다

    제출
    1. 성공
*/

/*
    총 옮긴 횟수
    k개를 옮기기 위해 a번 이동이 필요하다 가정
    
    원판 k+1개를 옮길때
    1. k개를 빈곳으로 이동 (a)
    2. k+1을 목표로 이동 (1)
    3. k개를 목표로 이동 (a)
    => 2a + 1

    초항이 1이기 때문에 2^k - 1
    2의 k제곱 - 1
*/

/*
    이동 횟수를 구하는 일반항 계산이 잘 안된다
    1629번 에서 사용한 재귀방식으로 진행하려했는데
    res = res.multiple(res) 해줘야하는데 res.multiple(res) 로만 했던 문제
    그리고 Long 타입 변수 범위를 넘지 않아서 long 으로 사용해도 된다
*/

import java.io.*
import java.math.BigInteger
private lateinit var bw : BufferedWriter
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    bw.write("${getHanoiCount(n)}\n")
    // honoi2(1, 2, 3, n)
    hanoi1(1, 3, n)
    
    bw.flush()
    bw.close()
    br.close()
}

// 2^k - 1
// 1 ≤ k ≤ 20
/*
    2^k - 1
    2^k/2 * 2^K/2

    k%2 == 0 그대로
    k%2 != 0 *k 한번더
*/
/*
private fun getHanoiCount(n : Int) : String {
    var res = BigInteger("1")
    for(i in 0 until n){
        res = res.multiply(BigInteger("2"))
    }
    res = res.subtract(BigInteger("1"))
    return res.toString()
}
*/
private fun getHanoiCount(n : Int) : String {
    //2의 20승이 최대 => 1000000000000000000000000 => Int 범위 밖
    // return getPower(2, n).subtract(BigInteger("1")).toString()
    return (getPower(2, n) - 1).toString()
}

private fun getPower(n : Int, i : Int) : Long {
    if(i == 1){
        return n.toLong()
    }
    var res = getPower(n, i/2)
    res = res * res
    if(i%2 == 0){
        return res
    }
    return res * n.toLong()
}

private fun getPowerBigInteger(n : Int, i : Int) : BigInteger {
    if(i == 1){
        return BigInteger("$n")
    }
    var res = getPowerBigInteger(n, i/2)
    res = res.multiply(res)
    if(i%2 == 0){
        return res
    }
    return res.multiply(BigInteger("$n"))
}
/*
    p(2, 3)
    
    res = p(2, 1) (2)
    res * res = 4

    if(3%2 != 0)
    return 4*n

*/

/*
    from, to 를 제외한 나머지 기둥(by 기둥)은 6-from-to (기둥의 총 합이 6이기 때문)
*/
private fun hanoi1(from : Int, to : Int, n : Int){
    if(n == 1){
        bw.write("$from $to\n")
        return
    }
    // n-1개의 원반을 a to b
    hanoi1(from, 6-from-to, n-1)
    // n개의 원반을 a to c
    bw.write("$from $to\n")
    // n-1개의 원반을 b to c
    hanoi1(6-from-to, to, n-1)

}

/*
    from 에서 by를 거쳐 to로 원반 이동
*/
private fun honoi2(from : Int, by : Int, to : Int, n : Int){
    if(n == 1){
        bw.write("$from $to\n")
        return
    }
    // n-1개의 원반을 a to b 
    honoi2(from, to, by, n-1)
    // n 개의 원반을 a to c
    bw.write("$from $to\n")
    // n-1개의 원반을 b to c로 이동
    honoi2(by, from, to, n-1)
}