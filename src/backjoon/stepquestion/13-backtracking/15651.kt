package src.backjoon.stepquestion.`13-backtracking`
//sliver3
//14-3

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt() 
    val m = st.nextToken().toInt()

    val arr = Array<Int>(m){0} // 길이가 m인 수열을 담을 배열
    dfs(n, m, 0, arr, bw)

    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(n : Int, m : Int, depth : Int, arr : Array<Int>, bw : BufferedWriter){
    if(depth == m){ // 깊이가 수열의 최대 개수와 같다면 방문 완료
        for(value in arr){
            bw.write("$value ")
        }
        bw.write("\n")
        return
    }

    // 1부터 n까지
    for(i in 1 until n+1){
        arr[depth] = i // 현재 깊이로 배열에 접근하여 값 저장
        dfs(n, m, depth+1, arr, bw) // 다음 노드로 출발
    }
}

/*
    자연수 1부터 N가지의 자연수중
    길이가 M인 수열들을 출력
    중복 허용
    1. 일단 중복허용하지않는 dfs 를 구현해보자 (완)
    2. 구현 후 조건식을 변경해서 완성하자

    1. 각 숫자 중복허용
    2. 수열간에는 중복을 허용하지않는다

*/