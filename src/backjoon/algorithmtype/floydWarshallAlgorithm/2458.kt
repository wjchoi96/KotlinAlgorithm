/*
    백준 2458
    네이버 카페 문제 50선 중 플로이드 알고리즘 유형 문제
    https://www.acmicpc.net/problem/2458

    gold 4

    1번부터 N번까지 번호가 붙여진 학생
    학생들에 대해, 두 학생끼리 키를 비교한 결과의 일부가 주어짐

    해당 비교 결과로부터, 모든 학생중에서 키가 가장 작은 학생부터 자신이 몇번째인지 알 수 있고, 그렇지 못한 학생도 존재
    
    자신의 키가 몇번째인지 알 수 있는 학생들이 모두 몇명인지 계산하라

    예)
    1 < 5
    3 < 4
    5 < 4
    4 < 2
    4 < 6
    5 < 2

    1 -> 5 -> 4 -> 6
                -> 2
         5 -> 2
    3 -> 4 -> 6

    4번은 자신보다 작은 학생 1, 3, 5를 확인 가능하며, 자신보다 큰 2, 6을 확인 가능
    1번과 3번은 서로 자신보다 작은지 큰지 알 수 없음
    5번과 3번은 서로 자신보다 작은지 큰지 알 수 없음
    2번과 6번은 서로 누가 큰지 작은지 알 수 없음

    2 ≤ N ≤ 500 
    0 ≤ M ≤ N(N-1)/2) => 두 학생 키를 비교한 횟수
*/
/*
    #풀이
    - 방향 그래프

    다른 정점 모두에 대해, 자신보다 키가 큰지 작은지 확인할 수 있는 정점의 개수
    1. 자신보다 키가 큰지 확인
    - 자기 자신에서, 해당 정점으로 경로가 존재하는지

    2. 자신보다 키가 작은지 확인
    - 해당 정점으로부터, 자기 자신으로 오는 경로가 존재하는지

    #의문점
    - 간선에 비용이 없는데, 단순 탐색으로도 가능하지 않은가?

    - 플로이드 알고리즘 풀이
        - 시간복잡도 O(V^3)내로 가능
        - path 테이블을 완성(A에서 B 경로의 존재 여부)
            - indegreePath, outdegreePath 두개를 완성시킬수 있을까?
        - A에서 B로 가는 경로가 존재하면 유지, 없다면 A에서 i를 거쳐서 B로 가도록 갱신

    - BFS 풀이
        - 인접행렬 BFS는 O(V^2)
        - 모든 정점에 대해 BFS를 수행해야 하므로, O(V^3)
        - 시간복잡도는 동일하며, BFS로도 풀이는 가능할것으로 추측됨
        - 각 정점이 다른 모든 정점들에 대해 경로가 있는지 확인하는 부분은 O(V^2)

    !!A에서 B로 갈수 있다, 없다를 확인해야함


    #bfs 제출
    1. 성공

    #플로이드 제출
    1. 성공

    
*/

import java.util.Queue
import java.util.LinkedList
fun main(){
    Solution2458().solve()
}
class Solution2458 {
    private lateinit var board: Array<Array<Int>>
    private lateinit var outdegreeTable: Array<Array<Int>>
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        board = Array(n+1){ Array(n+1){ 0 } }
        outdegreeTable = Array(n+1){ Array(n+1){ 0 } }

        repeat(m) {
            br.readLine().split(' ').map{ it.toInt() }.let {
                board[it[0]][it[1]] = 1
            }
        }

        for(i in 1..n) {
            for(x in 1..n) {
                for(y in 1..n) {
                    val hasPath = board[x][i] + board[i][y] == 2
                    when (board[x][y]) {
                        0 -> {
                            if(hasPath){ // 기존에 경로가 없었지만, i를 경유해 가는 경로가 존재할때
                                board[x][y] = 1 // 경로가 있다고 갱신(비용 계산은 필요없음)
                                outdegreeTable[y][x] = 1 // x에서 y로 향하는 경로가 있다고 갱신
                            }
                        }
                        else -> {
                            outdegreeTable[y][x] = 1 // x에서 y로 향하는 경로가 있다고 갱신
                        }
                    }
                }
            }
        }

        bw.write("\nboard debug\n")
        for(x in 1 until board.size){
            for(y in 1 until board.size) {
                bw.write("${board[x][y]} ")
            }
            bw.write("\n")
        }
        bw.write("board debug finish\n")
        bw.write("\noutdegreeTable debug\n")
        for(x in 1 until outdegreeTable.size){
            for(y in 1 until outdegreeTable.size) {
                bw.write("${outdegreeTable[x][y]} ")
            }
            bw.write("\n")
        }
        bw.write("outdegreeTable debug finish\n")

        var count = 0
        for(x in 1..n) {
            var hasPath = true
            for(y in 1..n) {
                when {
                    x == y -> continue
                    board[x][y] == 1 -> continue
                    outdegreeTable[x][y] == 1 -> continue
                    else -> {
                        hasPath = false
                        break
                    }
                }
            }
            if(hasPath) count++
        }

        bw.write("$count\n")

    
        bw.flush()
        bw.close()
        br.close()
    }


    private val map: HashMap<Int, HashSet<Int>> = HashMap() // 각 정점에대한 bfs 경로를 저장 
    // bfs 풀이
    fun solveBfs(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        board = Array(n+1){ Array(n+1){ 0 } }
        repeat(m) {
            br.readLine().split(' ').map{ it.toInt() }.let {
                board[it[0]][it[1]] = 1
            }
        }

        val queue: Queue<Int> = LinkedList()
        for(i in 1..n) {
            queue.offer(i)
            val visit = Array(n+1){false}
            while(!queue.isEmpty()){
                val cur = queue.poll()
                println("bfs[$i] => poll $cur")
                for(nxt in 1 until board[cur].size){
                    if(board[cur][nxt] == 0) continue
                    if(visit[nxt]) continue
                    map[i] = map.getOrDefault(i, HashSet()).apply {
                        this.add(nxt)
                        println("bfs[$i] save $nxt")
                    }
                    queue.offer(nxt)
                    visit[nxt] = true
                    println("bfs[$i] => offer $nxt")
                }
            }
        }

        var count = 0
        val path = Array(n+1){Array(n+1){false}}
        for(i in 1..n) {
            val set = map.getOrDefault(i, HashSet())
            println("$i => ${set.toList()}")
            set.forEach {
                path[i][it] = true
                path[it][i] = true
            }
        }

        bw.write("\npath debug\n")
        for(x in 1 until path.size){
            for(y in 1 until path.size) {
                bw.write("${path[x][y]} ")
            }
            bw.write("\n")
        }
        bw.write("path debug finish\n")
    

        for(i in 1..n) {
            var isSuccess = true
            for(j in 1..n){
                if(i == j) continue
                if(!path[i][j]){
                    isSuccess = false
                    break
                } 
            }   
            if(isSuccess) count++
        }

        bw.write("$count\n")
    
        bw.flush()
        bw.close()
        br.close()
    }
}