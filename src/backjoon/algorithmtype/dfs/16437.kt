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
/*
    1(0)

    2(S.100)            3(S.100)  4(W.100)

    5(S.1000)  6(W.1000)

               7(S.900)
*/

/*

    ====degug
    1,2,3,5 => 50
    1,2,3,6 => 50
    1,2,4,7 => 10
    ====degug

    110이 되네
    1(0)

    2(W.10)            

    3(W.40)              4(W.10)

    5(S.100)   6(S.50)   7(S.20)

    1,2,3,5 => 50

    1,2,3,6 => 0

    1,2,4,7 => 0

    => 정답 110??
    늑대의 일생동안 최대 한마리만 잡아먹는다는 뜻 같은데
    1,2,3,5 => 50
    1,2,3,6 => 50
    1,2,4,7 => 10

    해당 조건을 맞추려면 늑대가 양을 잡아먹는 연산을 실시했다면, 해당 늑대를 0으로 만들어줘야한다
   
*/

/*
    역순으로 계산할때의 문제점을 발견
    1,2,6,7 의 경우
    100마리가 되어야하는데
    역순으로 계산하는 바람에 900이 되었다

    어케하지

    역순계산을 어떻게 하지

    1 <-> 2 의 결과가 같아야하는데


*/

/*
    제출
    1. 틀렸습니다(2%)
    - 결과가 int 범위를 넘어설 수도 있다고 판단

    2. 틀렸습니다(2%)
    - 늑대는 일생동안 최대 한마리의 양만 잡아먹는 조건 인지

    3. 틀렸습니다(2%)
    - 연속으로 늑대섬이 나오는 경우를 처리

    4. 틀렸습니다(2%)
    - 재귀적으로 dfs를 호출하기 전에 동물수를 처리한다면 역순처리처럼 되는것이지만
    dest를 체크하는 for문이 끝나고 동물수를 처리한다면 
    종료된 재귀함수들이 stack에서 빠져나오면서 정순(leaf to root)순서처럼 처리되는거일까?
    
    - 해당사실을 검색을 통해 인지하였지만 너무 많이 꼬인것같아서 한번 더 실패함

    - 추가 검색을 통해 자식노드들의 합을 구한다음, 현재 노드의 동물 현황을 적용시켜주는 방식으로 해결

    5. 성공
    - 참고 코드 : https://velog.io/@kpg0518/%EB%B0%B1%EC%A4%80-16437%EB%B2%88-%EC%96%91-%EA%B5%AC%EC%B6%9C-%EC%9E%91%EC%A0%84
*/

import java.io.*
import java.util.StringTokenizer
private const val W = 0
private const val S = 1
private val graph : ArrayList<ArrayList<Int>> = ArrayList()
lateinit private var visit : Array<Boolean>
lateinit private var animals : Array<Array<Int>>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt() // 7  
    animals = Array(n+1){Array(2) { 0 }}
    visit = Array(n+1) { false }

    for(i in 0 until n+1){
        graph.add(ArrayList())
    }
    for(i in 2 until n+1){
        val st = StringTokenizer(br.readLine())
        when(st.nextToken()){
            "W" -> animals[i][W] = st.nextToken().toInt()
            "S" ->animals[i][S] = st.nextToken().toInt()
        }
        val edge = st.nextToken().toInt()
        graph[i].add(edge) // 현재 노드에 간선 추가
        graph[edge].add(i) // 상대 노드에 간선 추가
    }


    val totalSheepSum = dfs(1, n+1)
    bw.write("$totalSheepSum\n")
   
    bw.flush()
    bw.close()
    br.close()
}

// sheepCount 를 재귀함수에서 전달함으로서 방문이 끝난 섬의 sheepCount를 빼주는 작업을 할 필요가 없다
private fun dfs(now : Int, n : Int) : Long{ // x : 현재 노드(섬)
    var chlidNodeSheepSum : Long = 0
    // 현재 섬을 방문
    visit[now] = true // visit가 필요없지않나? 어차피 한번 방문한 노드는 for문 특성상 다시 체크 안할거같은데
    for(i in 0 until graph[now].size){
        val y = graph[now][i] // 작은 노드부터 탐색
        if(visit[y] == false){
            // y번섬으로 sheep만큼 양들이 이동한다
            chlidNodeSheepSum += dfs(y, n) // 방문
        }
    }
    // chlidNodeSheepSum => 내 자식 노드들에서 온 양의 개수의 합
    // 현재 섬의 늑대, 양 현황을 적용해준다(둘중 하나는 어차피 0임)
    chlidNodeSheepSum += animals[now][S]
    chlidNodeSheepSum -= animals[now][W]
    /*
        포식한 늑대에 대해서 정보를 업데이트 해줄 필요없다
        자식노드의 모든 양들을 불러와서 한번에 처리하기때문에 중복해서 양을 먹을 늑대는 없다
        animals[now][W] = 0 
    */
    if(chlidNodeSheepSum<0)chlidNodeSheepSum = 0
    print("node[$now] => sheep : $chlidNodeSheepSum\n")
    // 현재 섬의 양의 개수를 리턴해라
    return chlidNodeSheepSum
}
/*
    1, 2, 5 를 방문했다고 치자

    5에서 for문이 종료되고
    animal count 를 처리한다

    dfs(5)가 종료되고 dfs(3)의 dfs(5)호출 지점
*/


/*
    tes code
*/
private fun initTestGraph(n : Int){
    visit = Array(n){false}
    for(i in 0 until n){
        graph.add(ArrayList())
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

    animals = Array(n+1){Array(2){0}}
    animals[2][S] = 100
    animals[3][S] = 100
    animals[4][W] = 100
    animals[5][S] = 1000
    animals[6][W] = 1000
    animals[7][S] = 900
}