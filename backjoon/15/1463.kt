//15-8
//sliver 3

import java.io.*
lateinit var oneDp : Array<Int>
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))    

    val size = br.readLine().toInt()
    oneDp = Array(size+1){-1}
    oneDp[0] = 0
    oneDp[1] = 0

    bw.write("${getOneDp(size)}\n")

    br.close()
    bw.flush()
    bw.close()
}

/*
    1. x가 3으로 나누어 떨어지면 3으로 나눈다
    2. x가 2로 나누어 떨어지면 2로 나눈다
    3. 1을 뺀다

    3개의 연산을 사용해서 1을 만드려고 한다
    연산 사용횟수를 출력

    1 <= n <= 10^6

    n = 1 : 0
    n = 2 : 1 => 2-1 or 2/2 
    n = 3 : 1 => 3/3
    n = 4 : 2 => 4/2 - 1
    n = 5 : 2 => 5/2 - 1
    n = 6 : 2 => 6/3 - 1
    n = 7 : 2 => 7-1 / 3
    n = 8 : 2 => 8/3 - 1

    6일떄
    dp(6/3) 과 dp(6/2)를 비교한다
    
    (6/2) 

*/

// count 를 구하는것
private fun getOneDp(n : Int) : Int {
    if(oneDp[n] != -1){
        return oneDp[n]
    }
    oneDp[n] = if(n%3 == 0 || n%2 == 0){
        if(n%3 == 0 && n%2 == 0){
            Math.min(getOneDp(n/3), getOneDp(n/2)) + 1
        }else if(n%3 == 0){
            Math.min(getOneDp(n/3), getOneDp(n-1)) + 1
        }else{
            Math.min(getOneDp(n/2), getOneDp(n-1)) + 1
        }
    }else{
        getOneDp(n-1) + 1
    }
    return oneDp[n]
}

/*
    10
    1. 10/2 => 5
    2. 5-1 => 4
    3. 4/2 => 2
    4. 2-1 or 2/2 => 1

    1. 10-1 => 9
    2. 9/3 => 3
    3. 3/3 => 1
    와 ㅅㅂ3번이 되네?
    4번인데?
*/