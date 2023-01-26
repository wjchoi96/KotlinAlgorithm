/*
    백준 3665
    네이버카페 문제 50선 중 위상정렬 문제
    https://www.acmicpc.net/problem/3665

    gold 1

    인터넷 예선에 n개의 팀이 참가
    - 1번부터 n번까지 번호가 매겨짐
    - 올해 참가하는 팀은 작년에 참가한 팀과 동일

    작년에는 순위를 발표했지만, 올해는 작년에 비해 상대적 순위가 바뀐 팀의 목록만 발표
    (예) 팀13이 팀6보다 작년에 순위가 높았는데, 올해는 팀13이 더 높다면 (6, 13)을 발표

    해당 정보만을 가지고, 올해 최종 순위를 만들어보려 함
    - 본부에서 발표한 정보를 가지고 확실한 올해 순위를 만들 수 없는 경우
    - 일관성이 없는 잘못된 정보
    두 가지의 경우도 모두 찾아내야 함

    TC는 100개를 넘지 않음
    2 ≤ n ≤ 500 => 팀의 수
    1 ≤ ti ≤ n => ti는 작년에 i등을 한 팀의 번호. 1등이 가장 성적이 높은 팀
    0 ≤ m ≤ 25000 => 상대적으로 등수가 바뀐 쌍의 수
    1 ≤ ai < bi ≤ n => 상대적으로 등수가 바뀐 두 팀

    1등팀부터 순서대로 출력
    확실한 순위를 찾을 수 없다면 ? 출력
    데이터에 일관성이 없어 순위를 매길 수 없다면 IMPOSSIBLE 출력

*/
/*
    순위를 매길 수 없음 -> 사이클이 존재하는 그래프
    확실한 순위를 찾을 수 없음 -> 위상 정렬 결과가 여러개 나옴
    -> indegree가 동시에 0이되는 경우가 여러개 존재

    작년 등수대로 간선을 부여
    상대적으로 등수가 바뀐 팀들간에 방향 간선을 반대로 부여
    + 반대로 부여하며, 더 작았던 쪽이 가진 outdegree를 높았던 쪽이 가져가야함
    예) 
    5, 4, 3, 2, 1
    (2, 4)
    (3, 4)

    원래
    4 -> 2
    4 -> 3
    3 -> 2
    에서

    2 -> 4
    3 -> 4
    3 -> 2
    로 변경

    또한, (2, 4)의 경우
    2 -> 1 이 존재했는데
    4 -> 1 로 변경해줘야 함

    #순위가 작은노드가, 자신보다 높은 순위의 모든 노드들에게 간선을 받게 해야하나
    O(n*n)이

    zeroCount가 여러개이며, 그래프가 사이클을 형성하는 경우
    -> 그래프 사이클을 우선

    #제출
    1. 정답
*/

import java.util.Queue
import java.util.LinkedList
fun main(){
    Solution3665().solve()
}
class Solution3665 {
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var indegree: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        repeat(br.readLine().toInt()) { _ ->
            val n = br.readLine().toInt()
            graph = Array(n+1){ ArrayList() }
            indegree = Array(n+1) { 0 }

            br.readLine().split(' ').map{ it.toInt() }.let {
                for(i in 0 until it.size){
                    for(j in i+1 until it.size) {
                        graph[it[i]].add(it[j])
                        indegree[it[j]]++
                    }
                }
            }

            repeat(br.readLine().toInt()) { _ -> 
                br.readLine().split(' ').map{ it.toInt() }.let {
                    when (graph[it[0]].contains(it[1])) { 
                        true -> { 
                            graph[it[0]].remove(it[1])
                            graph[it[1]].add(it[0])
                            indegree[it[1]]--
                            indegree[it[0]]++
                        }
                        else -> {
                            graph[it[0]].add(it[1])
                            graph[it[1]].remove(it[0])
                            indegree[it[0]]--
                            indegree[it[1]]++
                        }
                    }
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
            bw.write("indegree => ${indegree.toList()}\n")
            //debug log

            val queue: Queue<Int> = LinkedList()
            var zeroCount = 0
            for(i in 1 until indegree.size) {
                if(indegree[i] == 0){
                    zeroCount++
                    queue.offer(i)
                }
            }
            if(zeroCount > 1){
                bw.write("?\n")
                bw.flush()
                bw.close()
                br.close()
                return
            }

            val result: Queue<Int> = LinkedList()
            while(!queue.isEmpty()) {
                val cur = queue.poll()
                result.offer(cur)
                for(nxt in graph[cur]) {
                    indegree[nxt]--
                    if(indegree[nxt] == 0){
                        queue.offer(nxt)
                    }
                }
            }

            if(result.size != n) {
                bw.write("IMPOSSIBLE")
            }else {
                for(i in 0 until n) {
                    bw.write("${result.toList()[i]} ")
                }
            }
            bw.write("\n")

        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}