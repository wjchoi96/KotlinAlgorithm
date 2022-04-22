//gold2
/*
    네이버 카페 문제 50선 중 dfs 유형 

    N개의 섬으로 이루어진 나라

    1번 섬에는 구명보트만 있고
    다른 섬에는 양들 또는 늑대들이 살고있다

    양들은 구명보트를 타고 늑대가 없는 나라로 이주를 결심

    각 섬에서 1번섬으로 가는 경로는 유일하며, i번 섬에는 pi번 섬으로 가는 다리가 존재

    양들은 1번섬으로 가는 경로를 이동하며
    늑대들은 원래있는 섬에서 움직이지 않으며 섬으로 들어온 양들을 잡아먹는다

    늑대한마리는 최대 한마리의 양만 잡아먹는다

    얼마나 많은 양이 1번섬에 도달할 수 있는지 구하라

    2<= N <+ 123,456 
    둘쨰줄부터 n-1개의 줄에 2번섬부터 N번섬까지의 정보를 입력
    t, a, p 세가지 정보를 입력
    1<= a <= 10
    1<= p <= n
    
    t : 'W'->늑대 'S'->양
    a : 개체수
    p : 현재 섬에서 해당섬으로 갈수있는 다리가 존재

    4
    S 100 3
    W 50 1
    S 10 1
    => 입력 데이터 해석
    2번섬에 양이 100마리 살고있다. 2-3 다리
    3번섬에 늑대가 50마리 살고있다. 3-1 다리
    4번섬에 양이 10마리 살고있다. 4-1 다리

    동빈나님 bfs, dfs 코드 좀더 살펴보고 풀어보면 도움될듯
    동빈나님 코드는 2차원 행렬이 아닌, 트리 그래프 를 기준으로 코드를 설명해주신다
    dfs : https://github.com/ndb796/python-for-coding-test/blob/master/5/8.java
    bfs : https://github.com/ndb796/python-for-coding-test/blob/master/5/9.java

    arrayOf {
        arrayOf{},
        arrayOf{2, 3},
        arrayOf{3},
        arrayOf{1, 2},
        arrayOf{1}
    }
    이렇게 되는건가

    graph = ArrayList<ArrayList<Int>>()
    graph[1].add(2)
    graph[1].add(3)

    이런식으로 그래프 생성

    동물 현황 2차원 배열생성
    0 wolf
    1 sheep

    1번에서 시작해서 각 끝자락 노드에 도착할때까지로
    역순으로 계산??
    섬이 여러개니까 이게 맞는듯

    =================================
    입력값 받아서 그래프생성
    각 섬당 개채 종류, 개채 수 저장 2차원 배열
    0 : W 
    1 : S

    n+1 size를 가진 2차원 배열 graph
    0번 노드는 무시

    1번 노드(루트 노드)부터 출발
    루트 <-> 단말노드까지의 연산
    1. 양 - 늑대. 양+양. 결과값은 음수가 되지않는다
    
    단말노드에 도착했는지 어떻게 알지?
    => 그래프를 탐색하는 dfs를 작성해보자

*/

private const val wolf = 0
private const val sheep = 1
private val graph : ArrayList<ArrayList<Int>> = ArrayList()
lateinit private var visit : Array<Boolean>
lateinit private var animals : Array<Array<Int>>
private fun initTestGraph(n : Int){
    visit = Array(n){false}
    for(i in 0 until n){
        graph[i] = ArrayList()
    }
    graph[1].add(2)
    graph[1].add(3)
    graph[1].add(4)

    graph[2].add(1)
    graph[2].add(5)
    graph[2].add(6)

    graph[3].add(1)

    graph[4].add(1)

    graph[5].add(2)

    graph[6].add(2)
    graph[6].add(7)

    graph[7].add(6)

    // animals = Array
}

fun main(args : Array<String>){
    val n = 7
    initTestGraph(n+1)
    dfs(1, n+1)
}
// x : 현재 노드
private fun dfs(x : Int, n : Int){
    visit[x] = true
    // 간선노드
    if(graph[x].size == 1){

    }
    for(i in 0 until graph[x].size){
        val y = graph[x][i] // 작은 노드부터 탐색
        if(visit[y] == false){
            
            dfs(y, n) // 방문
        }
    }
}
/*
    1, 2. 5 
    단말노드는 간선이 하나밖에없다(부모뿐)
*/