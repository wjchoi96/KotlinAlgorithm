//sliver3
/*
    바킹독님 백트래킹 단원에서 제시된 연습문제

    1부터 n까지 자연수 중에서 m개를 고른 수열
    같은수를 여러번 골라도 된다
*/


import java.io.*
private lateinit var bw : BufferedWriter
private lateinit var res : Array<Int>
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(' ').map{ it.toInt() }
    res = Array(m){-1}
    
    dfs(0, n)

    bw.flush()
    bw.close()
    br.close()
}


private fun dfs(depth : Int, size : Int){
    if(depth == res.size){
        res.forEach{bw.write("$it ")}
        bw.write("\n")
        return
    }
    for(i in 1 until size+1){
        res[depth] = i
        dfs(depth+1, size)
    }
}