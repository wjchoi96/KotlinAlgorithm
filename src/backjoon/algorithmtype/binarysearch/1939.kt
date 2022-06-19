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

*/
import java.util.Queue
import java.util.LinkedList
fun main(args: Array<String>){
    Solution1939().solve()
}
class Solution1939 {
    private lateinit var bridge: Array<Array<Int>>
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
        bridge = Array(n+1){Array(n+1){-1}}
        repeat(m){
            br.readLine().split(' ').map{it.toInt()}.apply{
                val start = this[0]
                val end = this[1]
                // 같은 섬에 두개의 다리가 존재할 수 있다 -> 최대값으로 허용 가능 무게 저장
                bridge[start][end] = Math.max(this[2], bridge[start][end])
                bridge[end][start] = Math.max(this[2], bridge[end][start])
                maxM = Math.max(maxM, this[2])
            }
        }
        var maxD = 0
        br.readLine().split(' ').map{it.toInt()}.let {
            maxD = getMaxDelivery(it[0], it[1])
        }
        getMaxWieght(maxD)

        bw.flush()
        bw.close()
        br.close()
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
            for(visitNode in 0 until bridge[node].size){
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


    // try => 메모리 초과
    // binary search 를 통해 mid 값을 bfs를 통해 가능여부를 체크
    private fun findMaxWeight(place: Int, dest: Int){
        var start = 0
        var end = maxM
        while(start<end){
            val mid = (start+end)/2
            print("start[$start] mid[$mid] end[$end]\n")
            val canDelivery = canDelivery(place, mid, dest)
            println("canDelivery[$canDelivery]")
            when(canDelivery){
                true -> start=mid+1 // 가능하다면 최대값 갱신 시도
                false -> end=mid-1 // 불가능하다면 배달 무게 낮춘다
            }
        }
        println("$start")
    }
    //bfs-> binary search 도중 매번 bfs 를 진행하여 메모리 초과
    private fun canDelivery(start: Int, weight: Int, dest: Int): Boolean{
        val queue: Queue<Int> = LinkedList()
        val visit = Array(n+1){false}
        visit[start] = true
        queue.offer(start)
        while(!queue.isEmpty()){
            val node = queue.poll()
            for(visitNode in 0 until bridge[node].size){
                if(bridge[node][visitNode] == -1) continue
                println("try visit[$node to $visitNode] => [$visitNode], bridge weight[${bridge[node][visitNode]}]")
                if(visit[visitNode]) {
                    continue
                }
                if(bridge[node][visitNode]<weight){
                    continue
                }
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
}