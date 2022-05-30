package src.backjoon.stepquestion.`13-backtracking`
//sliver3
//14-2

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt() // 1-n 까지의 숫자중
    val m = st.nextToken().toInt() // 중복없는 길이가m인 수열들을 구하시오

    val arr = Array<Int>(m){0} // 수열을 담을 배열
    val visit = Array<Boolean>(n){false} // 각 노드의 방문 이력을 저장할 배열 1-n 까지인데, size를 n으로 설정했다
    // visit 배열에 접근할때는 0-n-1 로 훑어서 idx-1 해서 접근해야한다

    dfs(n, m, 0, arr, visit, bw)


    bw.flush()
    bw.close()
    br.close()
}

// arr 결과를 저장할 배열
// depth : 탐색 깊이
// 수열은 오름차순이여야한다
private fun dfs(n : Int, m : Int, depth : Int, arr : Array<Int>, visit : Array<Boolean>, bw : BufferedWriter){
    if(depth == m){
        for(value in arr){
            bw.write("$value ")
        }
        bw.write("\n")
        return
    }

    for(i in 1 until n+1){ //1-n 까지
        if(visit[i-1] == false){ // 해당 노드를 방문한적이 없다면
            // 첫번째 노드가 아니고, 이전에 저장된 노드가 현재 방문한 노드보다 크다면 방문취소
            if(depth != 0 && arr[depth-1]+1 > i){ // 오름차순이 아닌지 체크
                continue
            }
            visit[i-1] = true
            arr[depth] = i // 깊이를 idx로 해서 값 저장
            dfs(n, m, depth+1, arr, visit, bw)
            visit[i-1] = false // 자식 노드를 모두 방문했다면 해당 노드 방문이력을 초기화
        }
    }
}