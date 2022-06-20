/*
    네이버 카페 문제 50선 중 이분탐색 유형
    gold4

    중량제한
    https://www.acmicpc.net/problem/1939
    
    N개의 섬으로 이루어진 나라가 있다
    몇 개의 섬 사이에는 다리가 설치되어 차들이 다닐 수 있다

    양식 중공업에서는 두 개의 섬에 공장을 세워 두고 물품을 생산하고 있다
    물품을 생산하다 보면 공장에서 다른 공장으로 생산 중이던 물품을 수송해야 할 일이 생긴다
    각 다리마다 중량 제한이 존재하기 때문에 무턱대고 물건을 옮길 수 없다

    한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하시오

    단순 이분탐색 문제가 아닌 것 같고, 그래프 탐색 알고리즘을 사용해야 할 것 같아서
    알고리즘 분류를 확인해보니 맞다
*/
/*
    2<= N <=10,000
    1<= M <= 100,000  다리의 정보를 나타내는 개수

    A,B.C 는 다리에 대한 정보를 나타내는 세 정수
    1<= A,B <= N
    1<= C <= 1,000,000,000
    ==> A번과 B번 섬 사이에 중량제한이 C인 다리가 존재

    서로 같은 두 섬 사이에 여러 개의 다리가 있을 수도 있다.
    => 그렇다면 최대중량의 다리만 기억하면 된다
    모든 다리는 양방향

    마지막 줄에 공장이 위치해 있는 섬의 번호를 나타내는 두 정수가 주어진다
    공장이 있는 두 섬을 연결하는 경로는 항상 존재하는 데이터만 입력으로 주어진다
*/
/*
    이건 좌표형태가 아닌 그래프 형태로 자료를 저장해야 한다
    -> 그래프 간선간에 중량이 존재하는건데

    그래프 탐색이 잘 기억이 안나서 그래프 형태로 탐색을 진행했던 16437.kt를 살짝 보고 
    큰 생각없이 dfs로 진행하려다가, 잠시 고민 후 bfs로 진행
    - 최단거리 탐색과 비슷하다고 판단
    - 가능한 루트로만 진입하기 위해서?

*/
/*
    제출
    1. 메모리 초과(6%)
    - 썪을

    2. 메모리 초과(6%)
    - graph bridge 합침
    - 썪을

    3. 메모리 초과(6%)
    - 근데 지금 이분탐색이 의미가 없는 풀이인 것 같다는 생각이 들었다
    https://www.acmicpc.net/board/view/89881
    이 문제의 이분 탐색 풀이의 경우 시작 지점에서 출발해서 도착 지점으로 도달할 수 있는지를 판단해 주어야 합니다. 도달 가능성을 판단하기 위해서 가능한 모든 경로를 확인할 필요는 없으므로 BFS, DFS, 분리 집합 모두 유효한 풀이가 될 것입니다 :)
    => 뭔소리징?

    4. 메모리 초과(6%)
    - 일단 linked list 로 구현 (정점의 개수가 많아서 bridge 배열로 했다간 너무 반복이 많아진다)
    - bfs 조건을 수정해야 할듯
    - https://www.acmicpc.net/board/view/45133
    - 내 첫번째 풀이 구현대로 진행한 사람이 7% 틀렸습니다 라고 한다
    - 메모리 초과 왜뜨지

    5. 메모리 초과(6%)
    - 이분탐색으로 중량제한 하지 않으면 큐가 터집니다... 속도도안나오고 => https://www.acmicpc.net/board/view/25076
    - 인접 행렬이 아닌 인접 리스트로 푸는 것이 더 좋은 문제   
    - n 이 최대 10000 이므로, 2차원 배열 생성시 메모리 초과 => bridge 가 원인?
    => https://velog.io/@6v6/%EB%B0%B1%EC%A4%80-1939-%EC%A4%91%EB%9F%89%EC%A0%9C%ED%95%9C
    => map 으로 변경

    6. 틀렸습니다(18%)
    - 메모리는 해결

    7. 틀렸습니다(12%)
    - end 를 max+1, mid 값 구할때 +1 진행

    8. 틀렸습니다(6%)
    - 일단 start 값이 정답이 되는 과정에서 이상함을 느낌
    - 답이 3일때 mid[2]까지만 검사하고, start=mid+1이 되어 3이 되고, 3에 대한 유효성은 검사하지 못한채 반복문이 종료되어 3이 정답으로 출력되는 현상 발견
    - max값 유효성이 체크되지 않고 있다는 것을 알게 되었다
    - end 를 max+1 로 두어 max까지 mid에 포함 될 수 있도록 수정
    - 하지만 결국 start값(mid+1)이 답으로 출력되는 케이스가 존재한다. 
    - res 값을 추가하여 mid값을 저장하도록 지정. start 대신 res 리턴
    - start<end 로 지정을 하니, 모든 값을 체크를 못하고 있는 것을 확인 => #반례3
    - start<=end 로 지정

    9. 맞췄습니다
*/
/*
    시간복잡도
    https://www.acmicpc.net/board/view/73375
    조금 더 정확히 하자면 BFS의 시간 복잡도는 O(N+M)이고, 이분 탐색은 O(log(중량의 최댓값)) 또는 그냥 log(10억)입니다.
*/
/*
    가능한 풀이
    - 이분탐색 + bfs 성공
    - bfs + 메모이제이션
    - bfs

    - 다익스트라 or 플로이드? 얘네는 아직 안배워서 가능한지 모르겠다
*/
import java.util.Queue
import java.util.LinkedList
import java.util.HashMap
fun main(args: Array<String>){
    Solution1939().solve()
}
class Solution1939 {
    private lateinit var bridge: Array<Array<Int>>
    private val bridgeMap: HashMap<Pair<Int, Int>, Int> = HashMap()
    private lateinit var graph: Array<LinkedList<Int>>
    private var n = 0
    private var m = 0
    private var maxM = 0
    private var maxDelivery = 0
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        br.readLine().split(' ').map{it.toInt()}.apply{
            n = this[0]; m = this[1]
        }
        graph = Array(n+1){LinkedList()}
        bridge = Array(n+1){Array(n+1){-1}}
        repeat(m){
            br.readLine().split(' ').map{it.toInt()}.apply{
                val start = this[0]
                val end = this[1]
                // 같은 섬에 두개의 다리가 존재할 수 있다 -> 최대값으로 허용 가능 무게 저장
                graph[start].add(end)
                graph[end].add(start)
                bridge[start][end] = Math.max(this[2], bridge[start][end])
                bridge[end][start] = Math.max(this[2], bridge[end][start])
                bridgeMap[start to end] = Math.max(this[2], bridgeMap.getOrDefault(start to end, 0))
                bridgeMap[end to start] = Math.max(this[2], bridgeMap.getOrDefault(end to start, 0))
                maxM = Math.max(maxM, this[2])
            }
        }
        // var maxD = 0
        br.readLine().split(' ').map{it.toInt()}.let {
            // maxD = getMaxDelivery(it[0], it[1])
            findMaxWeight(it[0], it[1])
        }
        // getMaxWieght(maxD)

        bw.flush()
        bw.close()
        br.close()
    }

    // binary search 를 통해 mid 값을 bfs를 통해 가능여부를 체크
    private fun findMaxWeight(place: Int, dest: Int){
        var start = 0
        var end = maxM+1
        var res = start
        while(start<=end){
            val mid = (start+end)/2
            print("start[$start] mid[$mid] end[$end]\n")
            val canDelivery = canDelivery(place, mid, dest)
            println("[$mid] canDelivery[$canDelivery]")
            when(canDelivery){
                true -> {
                    start=mid+1 // 가능하다면 최대값 갱신 시도
                    res = mid
                }
                false -> end=mid-1 // 불가능하다면 배달 무게 낮춘다
            }
        }
        println("$res")
    }
    //bfs-> binary search 도중 매번 bfs 를 진행하여 메모리 초과
    private fun canDelivery(start: Int, weight: Int, dest: Int): Boolean{
        val queue: Queue<Int> = LinkedList()
        val visit = Array(n+1){false}
        visit[start] = true
        queue.offer(start)
        while(!queue.isEmpty()){
            val node = queue.poll()
            for(visitNode in graph[node]){
                println("try visit[$node to $visitNode] => [$visitNode], bridge weight[${bridgeMap.getOrDefault(node to visitNode, 0)}]")
                if(visit[visitNode]) continue
                if(bridgeMap.getOrDefault(node to visitNode, 0)<weight) continue
                println("visit[$visitNode]")
                visit[visitNode] = true
                queue.offer(visitNode)
                if(visitNode == dest){
                    return true
                }
            }
        }
        return false
    }

    // bfs 를 통해 start to dest 간 다리의 무게 중 최대값을 구한다
    // visit : 출발지에서 해당 섬까지 나를 수 있는 최대 무게
    private fun getMaxDelivery(start: Int, dest: Int): Int{
        val queue: Queue<Int> = LinkedList()
        val visit = Array(n+1){-1}
        visit[start] = 0
        queue.offer(start)
        println("visit[$start]")
        while(!queue.isEmpty()){
            val node = queue.poll()
            println("try $node")
            for(visitNode in graph[node]){
                val bridgeWeight = bridge[node][visitNode]
                if(bridge[node][visitNode] == -1) continue // 갈 수 없는 루트
                // 출발지거나, 최대값 갱신을 못하는 경우
                if(visit[visitNode] == 0 || visit[visitNode] >= bridgeWeight) continue
                visit[visitNode] = bridgeWeight
                queue.offer(visitNode)
                println("visit[$visitNode]")
            }
        }
        println("${visit.toList()}")
        return visit[dest]
    }
    private fun getMaxWieght(canDeliveryMaxWeight: Int){
        var start = 0
        var end = maxM
        while(start<end){
            val mid = (start+end)/2
            print("start[$start] mid[$mid] end[$end]\n")
            when{
                canDeliveryMaxWeight>=mid -> start=mid+1 // 가능하다면 최대값 갱신 시도
                else -> end=mid-1 // 불가능하다면 배달 무게 낮춘다
            }
        }
        println("$start")
    }

}

/*
9 9
1 4 11
1 5 2
4 5 4
4 3 10
4 2 7
5 2 10
5 6 13
3 2 9
2 6 8
1 6

== aws 
9

2 1
1 2 1
1 2

== aws 
1


#반례3
3 4
1 2 1
1 2 1
1 3 9
3 2 1
1 2
== aws
1 
== return
0

*/