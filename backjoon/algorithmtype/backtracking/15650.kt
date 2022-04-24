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
    res = Array(m){-1}   

    dfs(1, 0)
 
    bw.flush()
    bw.close()
    br.close()
}

/*
    개선
    - 이전 item과 비교하는게 아닌, dfs에 시작 숫자를 같이 넘겨주게 해서 자동으로 오름차순이 되도록 한다
    - push 된 값을 넘겨주는게 아닌, +1을 해서 넘겨주므로 visit 배열도 필요가 없어진다
*/
private fun dfs(num : Int, depth : Int){
    if(depth == res.size){
        for(i in 0 until res.size){
            bw.write("${res[i]} ")
        }
        bw.write("\n")
        return
    }
    for(i in num until visit.size){
        res[depth] = i
        dfs(i+1, depth+1)
        
    }
}