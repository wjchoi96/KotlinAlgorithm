/*
    백준 11779
    바킹독님 알고리즘 강의 중 다익스트라 알고리즘 단원 연습문제
    https://www.acmicpc.net/problem/11779

    gold 3

    1≤n≤1,000 => N개의 도시
    1≤m≤100,000 => M개의 버스
    A에서 B로 가는데 드는 버스 비용을 최소화 하려 함
    최소비용과 경로를 출력
*/
/*
    두 정점간의 최소간격을 구하는 문제지만
    간선이 1000개 이하라 플로이드도 풀이가 가능할 것 같음

    #제출

    1. 메모리초과(50%)
    - <= 를 =로 변경해봄
    - 원래 다익스트라 알고리즘은, 최단거리이기 때문에 굳이 비용이 같은 경우에는 갱신을 안해줬는데
    예제 답변대로 출력되려면 비용이 같은 경우에도 갱신을 해줘야 하는것으로 보여 테스트 삼아 <=로 진행해보았음
    - 내 예상대로라면 <건 <=건 둘다 답이여야 하는것 같음
    - 맨 마지막에 테스트해봤는데, <=면 메모리 초과 발생
    (굳이 갱신할 필요 없는 상황에서 갱신을 하기 떄문에, Queue에 더 많은 Node가 쌓이게 됨)

    2. 메모리초과(57%)
    - path 출력시 Stack을 사용하지 않고 StringBuilder사용으로 변경
    - reverse를 사용시, 두 자릿수 이상의 수가 모두 뒤집어짐
    - StringBuilder의 insert를 사용하도록 변경

    3. 시간초과(57%)
    - INF값이 너무 작아서 무한루프가 발생할수도 있다는 생각이 듬
    - INF값을 임시로 지정해놓고, 생각해서 바꾸려했는데 까먹음

    4. 성공

    #숏코딩
    - path 배열을 추적하여 경로복원 조건문을 cur != start가 아닌, cur != 0으로 하면
    앞, 뒤로 추가 코드를 작성해야 하는것을 제거할 수 있음
    - table[start] = 0이기 때문

*/

import java.util.PriorityQueue
fun main(){
    Solution11779().solve()
}
class Solution11779 {
    private lateinit var graph: Array<ArrayList<Pair<Int, Int>>>
    private lateinit var path: Array<Int>
    private lateinit var table: Array<Int>
    private val INF = 100000000
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val v = br.readLine().toInt()
        val e = br.readLine().toInt()

        graph = Array(v+1){ArrayList()}
        table = Array(v+1){INF}
        path = Array(v+1){0}

        repeat(e) { _ -> 
            br.readLine().split(' ').map{it.toInt()}.let {
                graph[it[0]].add(it[1] to it[2])
            }
        }

        val (start, end) = br.readLine().split(' ').map{it.toInt()}
        table[start] = 0

        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy({it.second}))
        minHeap.offer(start to 0)

        while(!minHeap.isEmpty()){
            val cur = minHeap.poll()
            if(table[cur.first] != cur.second) continue
            bw.write("$cur\n")
            for(nxt in graph[cur.first]) {
                (table[cur.first] + nxt.second).let {
                    if(it <= table[nxt.first]) {
                        table[nxt.first] = it
                        path[nxt.first] = cur.first
                        minHeap.offer(nxt.first to it)
                    }
                }
            }
        }

        bw.write("${table[end]}\n")

        val sb = StringBuilder()
        var count = 0
        var cur = end
        while(cur != 0){
            sb.insert(0, "$cur ")
            cur = path[cur]
            count++
        }
        bw.write("$count\n")
        bw.write("${sb}\n")

        for(i in 1..v){
            bw.write("${table[i]} ")
        }
        bw.write("\n")
    
        bw.flush()
        bw.close()
        br.close()
    }
}