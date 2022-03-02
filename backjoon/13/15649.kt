//sliver 3
// 13-1

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    bw.flush()
    bw.close()
    br.close()
}

/*
    백트래킹 알고리즘
    모든 경우를 탐색하는 알고리즘

    DFS 도중 가지치기를 하는 알고리즘인가?

    참고
    https://velog.io/@mmindoong/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%ED%8A%B8%EB%9E%98%ED%82%B9BackTracking

    1부터 n까지의 자연수 중 중복없이 m개를 고른 수열

    1-3, 1
    1, 2, 3

    1-4, 2
    1234, 1243, 1324, 1342 ...

*/