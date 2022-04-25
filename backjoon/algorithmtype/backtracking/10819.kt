//sliver2
/*
    네이버 카페 문제50선 중 백트래킹 + 완전탐색 유형

    N개의 정수로 이루어진 배열 A
    배열의 순서를 적절히 바꿔서 다음 식의 최대값을 구하시오

    |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2]-A[N-1]|

    3<=N<=8
    -100 <= value <= 100
*/

/*  
    최소한의 조건이 필요한데..
    작 큰 작 큰 작 큰 이런식으로 배치되어야 제일 클텐데

    =========== 검색 ==============
    1. N이 작은 수이므로 그냥 전부 확인하는 방법
    
*/

import java.io.*
private lateinit var numbers : Array<Int>
private lateinit var res : Array<Int>
private var n = 0
private var max = Int.MIN_VALUE
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    
    
    n = br.readLine().toInt()
    numbers = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    res = Array(n){-1}

    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(idx : Int = 0, depth : Int = 0){
    if(idx == n){
        max = Math.max(processRes(), max)
        return
    }
    res[depth] = numbers[idx]
    
}
/*
    i = 1 : 0 - 1, 1 - 2
    i = 2 : 1 - 2, 2 - 3
    ...
    i = n-2 : n-3 - n-2, n-2 - n-1 

*/
private fun processRes() : Int {
    var sum = 0
    for(i in 1 until n-2){
        sum += Math.abs(res[i-1] - res[i]) + Math.abs(res[i] - res[i+1])
    }
    return sum
}