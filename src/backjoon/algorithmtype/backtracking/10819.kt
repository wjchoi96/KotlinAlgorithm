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

/*
    제출
    1. 성공
*/

import java.io.*
private lateinit var numbers : Array<Int>
private lateinit var visit : Array<Boolean>
private lateinit var res : Array<Int>
private var n = 0
private var max = Int.MIN_VALUE
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    
    
    n = br.readLine().toInt()
    numbers = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    res = Array(n){-1}
    visit = Array(n){false}

    dfs()

    bw.write("$max\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(depth : Int = 0){
    if(depth == n){
        max = Math.max(processRes(), max)
        return
    }
    for(i in 0 until n){
        if(visit[i] == false){
            res[depth] = numbers[i]
            visit[i] = true
            dfs(depth + 1)
            visit[i] = false
        }
    }
    
}
private fun processRes() : Int {
    var sum = 0
    for(i in 1 until n){
        sum += Math.abs(res[i-1] - res[i])
    }
    // for(i in 0 until n){
    //     print("${res[i]} ")
    // }
    // print(": $sum\n")
    return sum
}
/*
    10 4 20 1 15 8

    6 + 16 + 19 + 14 + 7
    22 + 19 + 14 + 7
    41 + 14 + 7
    56 + 7 
    62

    10 1 20 4 15 8

    9 + 19 + 16 + 11 + 7
    28 + 27 + 7
    28 + 34
    62
*/