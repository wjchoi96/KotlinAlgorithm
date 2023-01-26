/*
    바킹독 트리 순회 문제
    백준 11725
    https://www.acmicpc.net/problem/11725

    sliver 2

    루트 없는 트리가 주어짐
    트리의 루트를 1이라고 정했을때, 각 노드의 부모를 구하는 프로그램을 작성

    2 ≤ N ≤ 100,000 => 노드의 개수
    N-1개의 줄에 트리 상에서 연결된 두 정점이 주어짐
*/
/*
    Bfs를 진행하며 부모 정보를 수집
    Tree정보는 인접리스트 방식으로 저장

    - BFS, DFS, 재귀 DFS 모두 구현해보자

    #BFS 제출
    1. 성공

    #DFS 제출
    1. 성공

    #재귀 DFS 제출
    1. 성공
    
*/


import java.util.Queue
import java.util.LinkedList
import java.util.Stack
fun main(){
    Solution11725().solve()
}
class Solution11725 {
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var parent: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        graph = Array(n+1) { ArrayList() }
        parent = Array(n+1) { 0 }
    
        repeat(n-1) {
            br.readLine().split(' ').map{ it.toInt() }.let {
                graph[it[0]].add(it[1])
                graph[it[1]].add(it[0])
            }
        }

        bfs(1)
        // dfs(1)
        // recursiveDfs(1)

        for(i in 2..n){
            bw.write("${parent[i]}\n")
        }

        bw.flush()
        bw.close()
        br.close()
    }

    private fun recursiveDfs(node: Int) {
        for(nxt in graph[node]) {
            if(parent[node] == nxt)
                continue
            parent[nxt] = node
            recursiveDfs(nxt)
        }
    }

    private fun dfs(start: Int) {
        val stack = Stack<Int>()
        stack.push(start) 

        while(!stack.isEmpty()) {
            val cur = stack.pop()
            for(nxt in graph[cur]) {
                if(parent[cur] == nxt)
                    continue
                parent[nxt] = cur
                stack.push(nxt)
            }
        }
    }

    private fun bfs(start: Int) {
        val queue: Queue<Int> = LinkedList()
        queue.offer(start)

        while(!queue.isEmpty()) {
            val cur = queue.poll()
            for(nxt in graph[cur]) {
                if(parent[cur] == nxt) 
                    continue
                parent[nxt] = cur
                queue.offer(nxt)
            }
        }
    }
}