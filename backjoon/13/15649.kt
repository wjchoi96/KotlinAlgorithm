//sliver 3
// 13-1

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt() // 1부터 n 까지의 자연수중
    val m = st.nextToken().toInt() // 중복없이 m 개를 고른 수열

    val arr = Array<Int>(m){0} // 수열을 저장할 배열
    val visit = Array<Boolean>(n+1){ false } // 각 노드를 방문한적이 있나 저장할 배열
    // n+1 인 이유는 1-n 까지의 숫자이기 떄문이다

    dfs(n, m, 0, arr, visit, bw)

    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(n : Int, m : Int, depth : Int, arr : Array<Int>, visit : Array<Boolean>, bw : BufferedWriter){
    if(depth == m){
        for(value in arr){
            bw.write("$value ")
        }
        bw.write("\n")
        return
    }

    for(i in 1 until n+1){
        // 해당 노드를 방문하지 않았었다면
        if(visit[i] == false){
            visit[i] = true // 지금 방문
            arr[depth] = i; // 해당 깊이를 idx로 해서 i+1값 저장
            dfs(n, m, depth+1, arr, visit, bw) // 다음 자식 노드 방문 -> depth + 1

            // 자식노드 방문이 끝나고 돌아오면 방문노드를 방문하지 않은 상태로 변경
            visit[i] = false
        }
    }
}

/*
    백트래킹 알고리즘
    모든 경우를 탐색하는 알고리즘

    DFS 도중 가지치기를 하는 알고리즘인가?

    브루트포스, DFS, 백트래킹 을 잘 구분하자

    예) " a + b + c + d = 20 을 만족하는 두 수를 모두 찾아내시오. ( 0 ≤ a ,b ,c ,d < 100) "

    브루트포스 : 말 그대로 '모든 경우의 수'를 찾아보는 것이다. 
    a = 1, b = 1, c =1, d = 1 부터 시작하여 a = 100, b = 100, c = 100, d = 100 까지 총 1억개의 경우의 수를 모두 탐색

    백트래킹은 : 해당 노드의 유망성을 판단
    하나라도 a = 21 또는 b = 21 또는 c = 21 또는 d = 21 일 경우 20일 가능성이 1 ~ 100 범위 내에서는 절대 불가능하다. 
    그렇기 때문에 a > 20 이거나 b > 20, c > 20, d > 20 일 경우는 탐색하지 않는다

    DFS(깊이우선탐색) : 트리순회의 한 형태다
    백트래킹 = DFS가 아니라 백트래킹의 방법 중 하나가 DFS인 것이다

    백트래킹 문제를 DFS 방법을 통해 구현하는 것이 이번 문제의 포인트다

    참고
    https://st-lab.tistory.com/114

    1부터 n까지의 자연수 중 중복없이 m개를 고른 수열

    1-3, 1
    1, 2, 3

    1-4, 2
    1234, 1243, 1324, 1342 ...

*/