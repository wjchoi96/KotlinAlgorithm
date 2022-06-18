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
fun main(args: Array<String>){
    /*
        3 3
        1 2 2
        3 1 3
        2 3 2
        1 3
    */
    val bridge = arrayOf(
        arrayOf(2, 3),
        arrayOf(1, 3),
        arrayOf(1, 2)
    )// 근데 이러면 중량은?
    val weight: Array<Array<Int>> = Array(bridge.size){Array(bridge.size){-1}}
    weight[1][2] = 2
    weight[2][1] = 2

    weight[1][3] = 3
    weight[3][1] = 3

    weight[2][3] = 2
    weight[3][2] = 2
    // 같은 형태로 저장?
    // bridge 없이, weight 만으로 탐색 가능하지 않을까?
    // -1인 곳은 해당 섬으로 가는 길이 없는 것 => 불가능?

    Solution1939().solve()
}
class Solution1939 {
    private var graph: Array<ArrayList<Int>>
    private var bridge: Array<Array<Int>>
    private var visit: Array<Boolean>
    private var n = 0
    private var m = 0
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        br.readLine().split(' ').map{it.toInt()}.apply{
            n = this[0]; m = this[1]
        }
        graph = Array(n+1){ArrayList()}
        bridge = Array(n+1){Array(n+1){-1}}
        repeat(m){
            br.readLine().split(' ').map{it.toInt()}.apply{
                val start = this[0]
                val end = this[1]
                graph[start].add(end)
                graph[end].add(start)
                bridge[start][end] = this[3]
                bridge[end][start] = this[3]
            }
        }

    
        bw.flush()
        bw.close()
        br.close()
    }
    // 최대 무게를 구하는거라..
    private fun bfs(c: Int, weight: Int){
        visit[c] = true
        for(i in graph[c].size){
            
        }

    }
}