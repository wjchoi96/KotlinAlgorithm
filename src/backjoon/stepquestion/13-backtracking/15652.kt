package src.backjoon.stepquestion.`13-backtracking`
//sliver 3
//14-4


import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val arr = Array<Int>(m){0} // 길이가 m 인 수열을 저장할 배열
    dfs(n, m, 0, arr, bw)

    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(n : Int, m : Int, depth : Int, arr : Array<Int>, bw : BufferedWriter){
    if(depth == m){
        for(value in arr){
            bw.write("$value ")
        }
        bw.write("\n")
        return
    }

    for(i in 1 until n+1){
        // 이전에 저장된 노드의 값이 현재 값보다 크면 잘못된 순회
        if(depth != 0 && arr[depth-1] > i){
            continue
        }
        arr[depth] = i // 현재 노드를 저장
        dfs(n, m, depth+1, arr, bw) // 다음 노드로 출발
    }
}

/*
    1부터 N까지의 숫자중
    길이가 M인 수열들

    1. 같은 수 여러번 허용
    2. 수열은 비내림차순 
     - 0번째 idx 가 제일 작아야한다
*/