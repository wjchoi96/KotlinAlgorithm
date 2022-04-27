// gold4
/*
    바킹독님 알고리즘 강의 중 시뮬레이션 단원

    삼성 A형 기출 문제 
    3시간에 이런거 2개를 준다

    감시

    N*M 크기의 사무실
    설치되어 있는 CCTV는 5가지의 종류가 있다

    그림
    1번 : y+1
    2번 : y-1, y+1
    3번 : x-1, y+1
    4번 : x-1, y-1, y+1
    5번 : x-1, x+1, y-1, y+1

    그림과 정확히 일치하는것은 아니고, 저런식으로 방향을 가진다
    1. 한방향
    2. 두방향, 두 방향이 서로 반대 (x, y 둘중 하나만 택해서 +1,-1)
    3. 두방향. 두 방향이 서로 직각 (x, y 하나씩 +1 or -1)
    4. 세방향 (x, y 중 하나는 -1, +1 나머지 하나는 -1 or +1)
    5. 네방향

    ======= +1 이 아니라, 룩처럼 방향 전부를 감시한다

    cctv는 벽을 통과할 수 없다
    감시할 수 없는 영역은 사각지대라 명한다
    cctv 끼리 통과할수 있다

    cctv는 회전시킬 수 있는데, 항상 90도 방향으로만 회전 가능
    감시하려고 하는 방향이 가로 또는 세로 방향이여야 한다 ???

    0 : 빈칸
    6 : 벽
    1~5 : cctv

    정보들이 주어졌을때 cctv의 방향을 적절히 정해서, 사각지대의 최소 크기를 구하라

    그래프 탐색 알고리즘이 아니라 구현??

    1<= N, M <= 8
    cctv의 개수는 8개를 넘지 않는다
    1초
*/

/*
    cctv 개수
    board 입력받으며 cctv level과 좌표값을 저장한다

    cctv를 하나씩 배치해본다고 생각하자
    백트래킹 아닌가???

    cctv를 배치했을때 감시영역을 #으로 바꾸며, 배치했을때 가장 많은 #을 획득하는 위치로 배치?
    => 이건 그리딘데

    cctv 
    1. 감시할 방향을 하나 선택
    => 경우의수 4개
    2. 감시할 축 하나를 선택
    => 경우의수 2개
    3. 감시를 안할 방향 하나 선택
    => 경우의수 4개
    4. 고려x
    => 경우의수 1개

*/

private var n = 0
private lateinit var board : Array<Array<Int>>
private var cctvs : ArrayList<Pair<Int, Int>> = ArrayList()
private var cctvCount = 0
private const val lookArea = 9
private const val wall = 6
private const val up = 0
private const val down = 1
private const val right = 2
private const val left = 3
private fun dfs(idx : Int = 0, depth : Int = 0){
    if(depth == cctvCount){
        return
    }
    for(i in idx until cctvs.size){
        when(board[cctvs[i].first][cctvs[i].second]){
            1 -> {
                for(way in 0 until 4){
                    look(way, cctvs[i])
                    dfs(i + 1, depth + 1)
                }
            }
            2 -> {
                for(way in 0 until 3 step(2)){// 0, 2 두번실행
                    look(way, cctvs[i]) // up, right
                    look(way + 1, cctvs[i])// down, left
                    dfs(i + 1, depth + 1)
                }
            }
            3 -> {
                for(notLook in 0 until 4){
                    for(way in 0 until 4){ // 한방향을 제외하고 모두 바라본다
                        if(way == notLook){
                            continue
                        }
                        look(way, cctvs[i])
                    }
                    dfs(i + 1, depth + 1)
                }
            }
            4 -> {
                for(way in 0 until 4){ // 4방향을 전부 보고 넘어간다
                    look(way, cctvs[i])
                }
                dfs(i + 1, depth + 1)
            }
        }
    }
}
// draw # method 
// 어떻게 구현해야 모든 레벨에서 사용가능할까
// 방향을 줘야한다
private fun look(way : Int, cctv : Pair<Int, Int>){
    when(way){
        up -> {
            for(x in cctv.first downTo 0){
                if(board[x][cctv.second] == wall){
                    break
                }
                board[x][cctv.second] = lookArea
            }
        }
        down -> {}
        right -> {}
        left -> {}
    }
}