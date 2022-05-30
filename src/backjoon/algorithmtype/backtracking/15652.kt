package src.backjoon.algorithmtype.backtracking
//sliver3
/*
    바킹독님 백트래킹 단원에서 제시된 연습문제

    1부터 N까지 자연수중 m개를 고른 수열
    같은 수를 여러번 골라도 된다
    비내림차순이여야 한다
    => A1 <= A2 <= Ak-1 <= Ak 를 만족
    ? 오름차순에 < 대신 <= 가 조건
*/


import java.io.*
private lateinit var bw : BufferedWriter
private lateinit var res : Array<Int>
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(' ').map{it.toInt()}
    res = Array(m){-1}

    dfs(1, 0, n+1)
   
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(num : Int, depth : Int, n : Int){
    if(depth == res.size){
        for(i in 0 until res.size){
            bw.write("${res[i]} ")
        }
        bw.write("\n")
        return
    }
    for(i in num until n){
        res[depth] = i
        dfs(i, depth + 1, n)
    }
}

