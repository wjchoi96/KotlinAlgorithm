/*
    백준 16118
    네이버 카페 문제 50선 중 다익스트라 유형 문제
    https://www.acmicpc.net/problem/16118

    gold 1

    여우와 늑대는 보름달의 달빛을 받으면 변신함
    1번부터 N번까지 번호가 붙은 N개의 나무 그루터기가 존재
    그루터기 사이에는 M개의 오솔길이 존재

    오솔길은 어떤 방향으로든 지나갈 수 있음
    그루터기 사이에 두 개 이상의 오솔길이 있는 경우는 없음
    여우와 늑대는 1번 그루터기에 살고 있음
    - 여우는 일정한 속도로 달림
    - 늑대는 오솔길 하나를 여우의 두배 속도로 달림
    - 그 다음 오솔길 하나를 여우의 절반 속도로 걸어가는것을 반복
    - 여우와 늑대는 각자 가장 빠르게 달빛이 비치는 그루터기로 갈 수 있는 경로로 이동
    - 둘의 경로는 서로 다를 수 있음

    여우가 늑대보다 먼저 도착할 수 있는 그루터기에 달빛을 비춰주려함
    이런 그루터기가 몇개 있는지 산출

    - N개의 노드, M개의 간선
    - 양방향 그래프
    - 정점사이에 최대 1개의 간선
    - 1번 노드에서 시작

    2 ≤ N ≤ 4,000 => 정점 개수
    1 ≤ M ≤ 100,000 => 간선 개수
    1 ≤ a, b ≤ N => a번 그루터기, b번 그루터기
    a != b
    1 ≤ d ≤ 100,000 => 가중치
*/
/*
    늑대는 한턴은 가중치/2, 한턴은 가중치*2가 적용됨
    - 시작할때 가충치/2로 시작

    1번노드에서 시작해, 다른 노드로 도착하는 테이블을 2개 작성
    - 여우 테이블, 늑대 테이블

    풀이를 어느정도 진행했지만 문제점을 파악하고, 해결하려 했지만 잘 해결되지 않아서 블로그를 참고
    https://maivve.tistory.com/238
    -> 발견한 문제는, 매 순간 최소값만을 선택해서는 다익스트라 알고리즘을 완성할 수 없다는점
    이번턴에 느리게가고, 다음턴에 빠르게 간 경우가 더 빠를 수 있는데
    이번턴에 느리게 간다면, 가중치 * 2가되어, 간선이 선택되기 힘듬
    -> 2차원배열을 사용해, 현재 지점까지 느리게 걸어온 경우, 현재 지점까지 빠르게 걸어온 경우를
    만들어, 두 경우의 이동 최소시간을 저장

    또한, 나는 Double형을 사용해 /2를 처리하였는데,
    소수가 나와버리게 되면 계산이 더 느리게된다는 문제가 있기에,
    모든 가중치를 *2하여 짝수로 만들어서 진행하는게 편리함

    #제출
    1. 시간초과(2%)
    - 입력을 split()을 사용하지 않고 StringTokenizer를 사용

    2. 시간초과(7%)
    - n, m 입력을 split()을 사용하지 않고 StringTokenizer를 사용

    3. 시간초과(33%)
    - class 구조 제거

    4. 시간초과(28%) - 55168689

    5. 시간초과(42%)
    - 4번에서 바꾼게 없는데 이럼
    - let문을 해제하면 더 오래걸림

    6. JVM 추가시간없이 못풀것 같음..

*/

import java.util.PriorityQueue
import java.util.StringTokenizer
fun main(){
    Solution16118().solve()
}
class Solution16118 {
    private lateinit var graph: Array<ArrayList<Pair<Int, Int>>>
    private lateinit var foxTable: Array<Int>
    private lateinit var wolfTable: Array<Array<Int>>
    private val FAST = 0
    private val SLOW = 1
    private val INF: Int = 2000000000 //2e9 => 2* 10^9
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val stringTokenizer = StringTokenizer(br.readLine())
        val n = stringTokenizer.nextToken().toInt()
        val m = stringTokenizer.nextToken().toInt()

        graph = Array(n+1){ArrayList()}
        foxTable = Array(n+1) { INF }
        wolfTable = Array(2) { Array(n+1) { INF } }

        repeat(m) { _-> 
            val st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            val c = st.nextToken().toInt()
            graph[a].add(b to c*2)
            graph[b].add(a to c*2)
        }

        foxDijkstra(1)
        wolfDijkstra(1)
        
        var count = 0
        for(i in 1..n){
            if(foxTable[i] < Math.min(wolfTable[FAST][i], wolfTable[SLOW][i])) 
                count++
        }
        bw.write("$count\n")
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun foxDijkstra(start: Int) {
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy({it.second}))
        foxTable[start] = 0
        minHeap.offer(start to 0)

        while(!minHeap.isEmpty()) {
            val cur = minHeap.poll()
            if(foxTable[cur.first] != cur.second) continue
            for(nxt in graph[cur.first]) {
                (foxTable[cur.first] + nxt.second).let {
                    if(it < foxTable[nxt.first]){
                        foxTable[nxt.first] = it
                        minHeap.offer(nxt.first to it)
                    }
                }
            }
        }
    }

    private fun wolfDijkstra(start: Int) {
        val minHeap = PriorityQueue<WolfNode>(compareBy({it.cost}))
        wolfTable[SLOW][start] = 0 // 0에서 출발할때 빠르게 출발하기에, SLOW를 초기화
        minHeap.offer(WolfNode(start, 0, SLOW))

        while(!minHeap.isEmpty()) {
            val cur = minHeap.poll()
            val nxtState = 1 - cur.state // 1이면 0, 0이면 1이되기에 toggle
            if(wolfTable[cur.state][cur.node] != cur.cost) continue
            
            for(nxt in graph[cur.node]) {
                if(nxtState == FAST) {
                    nxt.second / 2
                }else {
                    nxt.second * 2
                }.let {
                    wolfTable[cur.state][cur.node] + it
                }.let { nxtCost -> 
                    if(nxtCost < wolfTable[nxtState][nxt.first]){
                        wolfTable[nxtState][nxt.first] = nxtCost
                        minHeap.offer(WolfNode(nxt.first, nxtCost, nxtState))
                    }
                }
            }
        }
    }

    data class WolfNode (
        val node: Int,
        val cost: Int,
        val state: Int
    )

    private fun printFox(n: Int){
        for(i in 1..n){
            print("${foxTable[i]} ")
        }
        println()
    }
    private fun printWolfTable(n: Int) {
        print("FAST => ")
        for(i in 1..n){
            print("${wolfTable[FAST][i]} ")
        }
        println()
        print("SLOW => ")
        for(i in 1..n){
            print("${wolfTable[SLOW][i]} ")
        }
        println()
        println()
    }
}