//sliver3
/*
    바킹독님 백트래킹 단원에서 제시된 연습문제

    1부터 n까지 중복없이 m개를 고른 수열
    고른 수열은 오름차순이여야 한다
*/

/*
    제출
    1. 성공
*/

import java.io.*
private lateinit var bw : BufferedWriter
private lateinit var visit : Array<Boolean>
private lateinit var res : Array<Int>
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(' ').map{ it.toInt() }
    visit = Array(n+1){false}
    res = Array(m+1){-1}   

    dfs(1)
 
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(depth : Int){
    if(depth == res.size){
        for(i in 1 until res.size){
            bw.write("${res[i]} ")
        }
        bw.write("\n")
        return
    }
    for(i in 1 until visit.size){
        if(visit[i] == false && res[depth-1] < i){
            res[depth] = i
            visit[i] = true
            dfs(depth + 1)
            visit[i] = false
        }
    }
}


