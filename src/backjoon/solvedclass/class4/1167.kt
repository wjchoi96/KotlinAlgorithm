/*
    백준 1167
    solved.ac class 4 문제
    https://www.acmicpc.net/problem/1167

    gold 2

    트리의 지름
    트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴것을 말함
    - 최장거리 산출

    2 ≤ V ≤ 100,000 => 정점의 개수
    가중치는 10000이하의 자연수

    !!입력이 정점 번호 순이 아님
*/
/*
    다익스트라는 매 순간 최단거리 정점을 선택해나가는 그리디 알고리즘
    매 순간 최장거리 정점을 선택한다면 최장거리를 구하는 그리디 알고리즘이 될것 같은데
    성립할까? -> 성립하는것 같음
    
    정점의 개수가 100000개라 플로이드 사용 불가
    모든 정점 쌍을 확인해야해서, 다익스트라도 O(VE log E) 
    -> 시간초과날것 같음

    dfs(node)를 V번 수행
    -> O(V+E) * O(V)
    -> 시간초과

    블로그 참고
    - https://moonsbeen.tistory.com/101

    - 트리는 모든 정점은 사이클이 없이 연결되어있고,
    한 정점에서 다른 정점으로 가는 경로는 유일

    1. 최장거리의 경로에는 어느 정점에서의 가장 먼 거리에 있는 정점의 경로와 겹침
    - 임의 정점에서 가장 먼 정점을 구함

    2. 가장 먼 정점에서 가장 멀리 떨어진 정점을 dfs를 통해 구함 

    #제출
    1. 틀렸습니다(2%)
    - 입력이 노드 순서대로 주어지지 않는점을 수정

    2. 성공
    - 블로그를 보고 접근방식을 생각해서 좀 찝찝
    - 다만, 시간복잡도를 고려해서 안될 방식을을 구분한건 잘한것 같음
*/

fun main(){
    Solution1167().solve()
}
class Solution1167 {
    private lateinit var graph: Array<ArrayList<Pair<Int, Int>>>
    private lateinit var visit: Array<Boolean>

    private var max = 0
    private var maxNode: Int = -1
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        graph = Array(n+1){ArrayList()}
        repeat(n) { _ ->
            val input = br.readLine().split(' ').map{it.toInt()}
            for(i in 1 until input.size - 1) { // 맨앞 node번호, 맨뒤 -1은 다루지 않음
                if(i%2==0) continue
                graph[input[0]].add(input[i] to input[i+1])
            }
        }

        for(i in 1..n) {
            bw.write("$i => ${graph[i].toList()}\n")
        }

        visit = Array(n+1){false}
        dfs(1, 0)
        bw.write("maxNode[$maxNode], max[$max]\n")
        
        visit = Array(n+1){false}
        dfs(maxNode, 0)
    
        bw.write("$max\n")

        bw.flush()
        bw.close()
        br.close()
    }

    private fun dfs(node: Int, cost: Int) {
        if(cost > max){
            max = cost
            maxNode = node
        }
        visit[node] = true
        for(nxt in graph[node]) {
            if(visit[nxt.first]) continue
            dfs(nxt.first, cost + nxt.second)
            visit[nxt.first] = true
        }
    }
}