/*
    백준 2637
    네이버 카페 문제 50선 중 위상정렬 유형 문제
    https://www.acmicpc.net/problem/2637

    gold 2

    장난감을 여러 부품을 사용해 조립
    - 기본 부품은 다른 부품을 사용하여 조립될 수 없는 부품
    - 중간 부품은 또 다른 중간 부품이나 기본 부품을 이용해 만들어지는 부품

    예) 
    기본부품 1, 2, 3, 4
    중간부품 5는 2개의 기본부품(1), 2개의 기본부품(2)로 만들어짐
    중간부품 6은 2개의 중간부품(5), 3개의 기본부품(3), 4개의 기본부품(4)로 만들어짐
    장난감 완제품 7은 2개의 중간부품(5), 3개의 중간부품(6), 5개의 기본부품(4)로 만들어짐
    -> 장난감(7)을 만들기 위해 기본부품 1번 16개, 2번 16개, 3번 9개, 4번 17개

    장난감 완제품을 조립하기 위해 필요한 기본부품의 종류별 개수를 계산

    3 ≤ N ≤ 100 => 1부터 N-1까지는 기본, 중간부품의 번호. N은 완제품의 번호
    3 ≤ M ≤ 100 => 부품들간의 관계의 수
    X, Y, K => 중간부품이나 완제품 X를 만드는데, 중간부품 혹은 기본부품 Y가 K개 필요
    정답은 2,147,483,647 이하

    하나의 완제품을 조립하는데 필요한 기본부품의 수를 한줄에 하나씩 출력
    - 중간부품은 출력하지 않음
    - 기본부품의 번호가 작은것부터 큰 순서가 되도록 함
*/
/*
    스타크래프트 문제랑 비슷한것 같음

    기본부품, 중간부품의 구분
    - 초기에 inDegree가 하나도 없는 부품이 기본부품

    그래프를 통해 부품간 의존도 방향을 표현
    부품 조립에 필요한 개수
    -> 각 간선의 비용
    -> 간선 class를 따로 생성해야 하나 => Edge(cost, destNode)
    -> 별도의 배열에 저장가능한가. 여러갠데

    각 부품을 만들기 위해 필요한 기본 부품의 개수를 별도의 배열에 저장

    기본부품
    -> cost배열에 해당 idx로 더해주면 됨

    중간부품
    -> 중간부품을 만드는데 필요한 기본부품의 개수를 어떻게 저장할지..
    -> 중간부품 하나당 배열을 하나씩?

    indegree--를 수행할때마다, 현재 부품 조립에 필요한 개수를 cost에 더해줘야함

    트리 순회를 거꾸로 돌아야할것 같은데..(이런 생각이 계속 들다가, 블로그를 참고했는데 역 위상정렬 단어 하나만 보고 바로 끔)
    간선을 반대로 위상정렬하며, count[nxt.destNode] += nxt.cost * count[nxt.fromNode] 공식으로 부품의 누적합을 구함

    #제출
    1. 성공

*/
/*
    피드백
    1. 트리 순회를 거꾸로 돌아야 할것 같다고 생각은했지만, 위상정렬 학습 내용에 빠져서 시도해볼 생각을 안함
*/

import java.util.Queue
import java.util.LinkedList
fun main(){
    Solution2637().solve()
}
class Solution2637 {
    data class Edge(
        val fromNode: Int,
        val destNode: Int,
        val cost: Int
    )
    private lateinit var graph: Array<ArrayList<Edge>>
    private lateinit var indegree: Array<Int>
    private lateinit var outdegree: Array<Int>
    private lateinit var count: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        graph = Array(n+1){ArrayList()}
        indegree = Array(n+1){0}
        outdegree = Array(n+1){0}
        count = Array(n+1){0}
        count[n] = 1

        repeat(m) { _ ->
            br.readLine().split(' ').map{ it.toInt() }.let {
                // it[0]을 만드는데, it[1]이 it[2]개 필요
                // 간선이 it[1]에서 it[0]으로 향하지만, 역순으로 트리를 순회하기 위해 방향을 반대로
                graph[it[0]].add(Edge(it[0], it[1], it[2]))
                outdegree[it[1]]++
                indegree[it[0]]++
            }
        }

        //debug log start
        bw.write("graph debug\n")
        for(i in 0 until graph.size){
             bw.write("$i => ")
            for(j in 0 until graph[i].size){
                bw.write("${graph[i][j]} ")
            }
            bw.write("\n")
        }
        bw.write("outdegree => ${outdegree.toList()}\n")
        //debug log

        val queue: Queue<Int> = LinkedList()
        for(i in 1 until outdegree.size){
            if(outdegree[i] == 0){
                queue.offer(i)
            }
        }

        while(!queue.isEmpty()){
            val cur = queue.poll()
            for(nxt in graph[cur]){
                outdegree[nxt.destNode]--
                count[nxt.destNode] += nxt.cost * count[nxt.fromNode]

                if(outdegree[nxt.destNode] == 0){
                    queue.offer(nxt.destNode)
                }
            }
        }

        bw.write("count => ${count.toList()}\n")
        for(i in 1 until count.size){
            if(indegree[i] == 0){
                bw.write("$i ${count[i]}\n")
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}