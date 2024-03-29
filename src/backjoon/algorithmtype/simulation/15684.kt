package src.backjoon.algorithmtype.simulation
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
    백트래킹 아닌가??? => 백트래킹은 아니고 dfs를 사용한 브루트 포스
    => 아닌데 백트래킹인데;

    cctv를 배치했을때 감시영역을 #으로 바꾸며, 배치했을때 가장 많은 #을 획득하는 위치로 배치?
    => 이건 그리딘데

    cctv 
    1. 감시할 방향을 하나 선택
    => 경우의수 4개
    2. 감시할 축 하나를 선택
    => 경우의수 2개
    3. x축,y축 하나씩
    => 경우의수 4개
    4. 감시를 안할 방향 하나 선택
    => 경우의수 4개
    5. 고려x
    => 경우의수 1개

*/

/*
    브루트 포스 방법으로 진행하자
    모든 cctv의 방향을 정한다
    정하고 나서 lookArea 탐색
    clear

    새로운 조합의 cctv 방향을 정한다
    반복

    cctv 방향을 저장할 방법?
    cctv 좌표
    level
    방향

    방향의 경우는 1~4개다
    3차원 배열?

    cctv 순회

    각 cctv 방식대로
*/
/*
    제출
    1. 성공

    코드 양좀 줄여보자... + 개선방법
    개선
    1. cctv loop 이 필요가없다
    depth == idx의 역할을 이미 하고있네

    2. look, clear 를 dx, dy 사용과 반복문을 통해서 개선
    // https://www.acmicpc.net/source/27189384 의 monitoring 함수가 상당히 개선되어있는 형태

    개선통합안
    1. cctv loop 제거 -> depth 로 대체
    2. look, clear 함수 개선
    3. board 를 copy 하여 브루트포스 방식으로도 구현

    개선 제출
    1. 성공 : dfs2 시리즈
     - 개선안 1,2 적용

    2. 성공 : dfs3 시리즈
     - 개선안 1,2,3 적용
*/
/*
    숏코딩으로 본 다른 풀이
    
    1. 백트래킹을 하지 않고, 현재 cctv의 위치로 인한 look이 적용된 cloneMap을
    다음 dfs에 넘긴다
    => 이러면 백트래킹이 아니라 브루트 포스가 되는거같다
    => 단점? 매번 board를 복사하고, 값을 적용시켜줘야한다

    좀 둘러본 결과 보통 클래스를 정의하여 구현하였고
    공통적인것으로는 board 를 카피해서 브루트포스로 구현한 사람들이 더 많았다
    look 을 구현하는 방법은 모두 다 달랐다
*/
import java.io.*
import java.util.StringTokenizer

private var n = 0
private var m = 0
private lateinit var board : Array<Array<Int>>
private var cctvs : ArrayList<Pair<Int, Int>> = ArrayList()
private const val lookArea = 10
private const val wall = 6
private var max : Int = Int.MIN_VALUE
private val cctvRange = 1..5 // 1...5+1 == 1..5
private enum class Way(val v : Int){
    Up(0), Down(1), Right(2), Left(3)
}
private val dx = arrayOf(-1, 1, 0 ,0)
private val dy = arrayOf(0, 0, 1, -1)
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    board = Array(n){Array(m){0}}
    var wallCount = 0
    for(x in 0 until n){
        st = StringTokenizer(br.readLine())
        for(y in 0 until m){
            board[x][y] = st.nextToken().toInt()
            if(board[x][y] == wall){
                wallCount++
            }else if(board[x][y] in cctvRange){
                cctvs.add(Pair(x, y))
            }
        }
    }

    dfs3(map=board)
    bw.write("${n*m-max-wallCount-cctvs.size}\n")

    bw.flush()
    bw.close()
    br.close()
}

// ============= 개선안2 ==========================
/*
    브루트 포스 방식으로 개선
    board를 매번 cctv 배치마다 copy 하는 방식
*/
private fun dfs3(depth : Int = 0, map : Array<Array<Int>>){
    if(depth == cctvs.size){
        max = Math.max(max, getLookArea2(map)) // 감시지대의 최대값을 구한다
        return
    }
    when(board[cctvs[depth].first][cctvs[depth].second]){
        1 -> {
            for(idx in 0 until Way.values().size){
                val way = Way.values()[idx]
                val copy = getCopyMap(map)
                look3(way, cctvs[depth], copy)
                dfs3(depth + 1, copy)
            }
        }
        2 -> {
            for(idx in 0 until Way.values().size-1 step(2)){ // 0, 2 두번실행
                val copy = getCopyMap(map)
                val way = Way.values()[idx]
                val way2 = Way.values()[idx+1]
                look3(way, cctvs[depth], copy)
                look3(way2, cctvs[depth], copy)
                dfs3(depth + 1, copy)
            }
        }
        3 -> {
            for(idx in 0 until 2){
                for(idx2 in 2 until 4){
                    val copy = getCopyMap(map)
                    val way = Way.values()[idx]
                    val way2 = Way.values()[idx2]
                    look3(way, cctvs[depth], copy) 
                    look3(way2, cctvs[depth], copy) 
                    dfs3(depth + 1, copy)
                }
            }
        }
        4 -> {
            for(notLook in 0 until Way.values().size){
                val copy = getCopyMap(map)
                for(idx in 0 until Way.values().size){ // 한방향을 제외하고 모두 바라본다
                    if(idx == notLook){
                        continue
                    }
                    look3(Way.values()[idx], cctvs[depth], copy)
                }
                dfs3(depth + 1, copy)
            }
        }
        5 -> {
            val copy = getCopyMap(map)
            for(idx in 0 until Way.values().size){ // 4방향을 전부 보고 넘어간다
                look3(Way.values()[idx], cctvs[depth], copy)
            }
            dfs3(depth + 1, copy)
        }
    }
}

private fun getCopyMap(original : Array<Array<Int>>) : Array<Array<Int>> {
    val copy = Array(n){Array(m){0}}
    for(x in 0 until n){
        for(y in 0 until m){
            copy[x][y] = original[x][y]
        }
    }
    return copy
}
private fun look3(way : Way, cctv : Pair<Int, Int>, copyMap : Array<Array<Int>>) : Int {
    var count = 0
    var visit = Pair(cctv.first + dx[way.v], cctv.second + dy[way.v])
    while(visit.first in 0..n-1 && visit.second in 0..m-1){
        if(copyMap[visit.first][visit.second] == wall){
            break
        }
        if(copyMap[visit.first][visit.second] in cctvRange){
            visit = Pair(visit.first + dx[way.v], visit.second + dy[way.v])
            continue
        }
        if(copyMap[visit.first][visit.second] >= lookArea){
            copyMap[visit.first][visit.second]++
        }else{
            copyMap[visit.first][visit.second] = lookArea
        }
        count++
        visit = Pair(visit.first + dx[way.v], visit.second + dy[way.v])
    }
    return count
}
private fun getLookArea2(map : Array<Array<Int>>) : Int {
    var count = 0
    print("\ngetLookArea2=====\n")
    for(x in 0 until n){
        for(y in 0 until m){
            if(map[x][y] >= lookArea){
                print("9 ")
                count++
            }else{
                print("${map[x][y]} ")
            }
        }
        print("\n")
    }
    return count
}


// ===================== second solve ===================
/*
    백트래킹 방식의 최적화 코드
*/
private fun dfs2(depth : Int = 0){
    if(depth == cctvs.size){
        max = Math.max(max, getLookArea()) // 감시지대의 최대값을 구한다
        return
    }
    print("visit node[$depth] : ${board[cctvs[depth].first][cctvs[depth].second]}\n")
    when(board[cctvs[depth].first][cctvs[depth].second]){
        1 -> {
            for(idx in 0 until Way.values().size){
                val way = Way.values()[idx]
                var count = 0
                count += look2(way, cctvs[depth])
                dfs2(depth + 1)
                if(count != 0){
                    clear2(way, cctvs[depth]) // clear look area
                }
            }
        }
        2 -> {
            for(idx in 0 until Way.values().size-1 step(2)){ // 0, 2 두번실행
                val way = Way.values()[idx]
                val way2 = Way.values()[idx+1]
                var count = 0
                count += look2(way, cctvs[depth]) // up, right
                count += look2(way2, cctvs[depth])// down, left
                dfs2(depth + 1)
                if(count != 0){
                    clear2(way, cctvs[depth])
                    clear2(way2, cctvs[depth])
                }
            }
        }
        3 -> {
            for(idx in 0 until 2){
                for(idx2 in 2 until 4){
                    val way = Way.values()[idx]
                    val way2 = Way.values()[idx2]
                    var count = 0
                    count += look2(way, cctvs[depth]) 
                    count += look2(way2, cctvs[depth]) 
                    dfs2(depth + 1)
                    if(count != 0){
                        clear2(way, cctvs[depth]) 
                        clear2(way2, cctvs[depth]) 
                    }
                }
            }
        }
        4 -> {
            for(notLook in 0 until Way.values().size){
                var count = 0
                for(idx in 0 until Way.values().size){ // 한방향을 제외하고 모두 바라본다
                    if(idx == notLook){
                        continue
                    }
                    count += look2(Way.values()[idx], cctvs[depth])
                }
                dfs2(depth + 1)
                if(count != 0){
                    for(idx in 0 until Way.values().size){ // 한방향을 제외하고 모두 바라본다
                        if(idx == notLook){
                            continue
                        }
                        clear2(Way.values()[idx], cctvs[depth])
                    }
                }
            }
        }
        5 -> {
            var count = 0
            for(idx in 0 until Way.values().size){ // 4방향을 전부 보고 넘어간다
                count += look2(Way.values()[idx], cctvs[depth])
            }
            dfs2(depth + 1)
            if(count != 0){
                for(idx in 0 until Way.values().size){ 
                    clear2(Way.values()[idx], cctvs[depth])
                }
            }
        }
    }
}

private fun look2(way : Way, cctv : Pair<Int, Int>) : Int {
    var count = 0
    var visit = Pair(cctv.first + dx[way.v], cctv.second + dy[way.v])
    while(visit.first in 0..n-1 && visit.second in 0..m-1){
        print("look2 : [${visit.first}][${visit.second}]\n")
        if(board[visit.first][visit.second] == wall){
            break
        }
        if(board[visit.first][visit.second] in cctvRange){
            visit = Pair(visit.first + dx[way.v], visit.second + dy[way.v])
            continue
        }
        if(board[visit.first][visit.second] >= lookArea){
            board[visit.first][visit.second]++
        }else{
            board[visit.first][visit.second] = lookArea
        }
        count++
        visit = Pair(visit.first + dx[way.v], visit.second + dy[way.v])
    }
    return count
}
private fun clear2(way : Way, cctv : Pair<Int, Int>) {
    var visit = Pair(cctv.first + dx[way.v], cctv.second + dy[way.v])
    while(visit.first in 0..n-1 && visit.second in 0..m-1){
        if(board[visit.first][visit.second] == wall){
            break
        }
        if(board[visit.first][visit.second] in cctvRange){
            visit = Pair(visit.first + dx[way.v], visit.second + dy[way.v])
            continue
        }
        if(board[visit.first][visit.second] > lookArea){
            board[visit.first][visit.second]--
        }else if(board[visit.first][visit.second] == lookArea){
            board[visit.first][visit.second] = 0
        }
        visit = Pair(visit.first + dx[way.v], visit.second + dy[way.v])
    }
}

private fun getLookArea() : Int {
    var count = 0
    print("\ngetLookArea=====\n")
    for(x in 0 until n){
        for(y in 0 until m){
            if(board[x][y] >= lookArea){
                print("9 ")
                count++
            }else{
                print("${board[x][y]} ")
            }
        }
        print("\n")
    }
    printBoard()
    return count
}

// ===================== first solve =====================
/*
    백트래킹 방식
    look와 clear 함수의 길이가 상당하다
*/

// private const val up = 0
// private const val down = 1
// private const val right = 2
// private const val left = 3
// private fun dfs(idx : Int = 0, depth : Int = 0){
//     if(depth == cctvs.size){
//         max = Math.max(max, getLookArea()) // 감시지대의 최대값을 구한다
//         return
//     }
//     for(i in idx until cctvs.size){
//         when(board[cctvs[i].first][cctvs[i].second]){
//             1 -> {
//                 for(way in 0 until 4){
//                     var count = 0
//                     count += look(way, cctvs[i])
//                     dfs(i + 1, depth + 1)
//                     if(count != 0){
//                         clear(way, cctvs[i]) // clear look area
//                     }
//                 }
//             }
//             2 -> {
//                 for(way in 0 until 3 step(2)){ // 0, 2 두번실행
//                     var count = 0
//                     count += look(way, cctvs[i]) // up, right
//                     count += look(way + 1, cctvs[i])// down, left
//                     dfs(i + 1, depth + 1)
//                     print("clear[${cctvs[i].first}][${cctvs[i].second}] when finish ${depth+1}\n")
//                     if(count != 0){
//                         clear(way, cctvs[i])
//                         clear(way + 1, cctvs[i])
//                     }
//                 }
//             }
//             3 -> {
//                 /*
//                     0,2 up, right
//                     0,3 up, left
//                     1,2 down, right
//                     1,3 down, left
//                 */
//                 for(way in 0 until 2){
//                     for(way2 in 2 until 4){
//                         var count = 0
//                         count += look(way, cctvs[i]) 
//                         count += look(way2, cctvs[i]) 
//                         dfs(i + 1, depth + 1)
//                         if(count != 0){
//                             clear(way, cctvs[i]) 
//                             clear(way2, cctvs[i]) 
//                         }
//                     }
//                 }
//             }
//             4 -> {
//                 for(notLook in 0 until 4){
//                     var count = 0
//                     for(way in 0 until 4){ // 한방향을 제외하고 모두 바라본다
//                         if(way == notLook){
//                             continue
//                         }
//                         count += look(way, cctvs[i])
//                     }
//                     dfs(i + 1, depth + 1)
//                     if(count != 0){
//                         for(way in 0 until 4){ 
//                             if(way == notLook){
//                                 continue
//                             }
//                             clear(way, cctvs[i])
//                         }
//                     }
//                 }
//             }
//             5 -> {
//                 var count = 0
//                 for(way in 0 until 4){ // 4방향을 전부 보고 넘어간다
//                     count += look(way, cctvs[i])
//                 }
//                 dfs(i + 1, depth + 1)
//                 if(count != 0){
//                     for(way in 0 until 4){ 
//                         clear(way, cctvs[i])
//                     }
//                 }
//             }
//         }
//     }
// }
// private fun look(way : Int, cctv : Pair<Int, Int>) : Int { // 여기서 lookArea 된 count 를 리턴?
//     var lookAreaCount = 0
//     val levelForDebug = board[cctv.first][cctv.second]
//     when(way){
//         up -> {
//             for(x in cctv.first-1 downTo 0){ // cctv 아래서부터 0까지
//                 if(board[x][cctv.second] == wall){
//                     break
//                 }
//                 if(board[x][cctv.second] in cctvRange){
//                     continue
//                 }
//                 if(board[x][cctv.second] >= lookArea){
//                     board[x][cctv.second]++
//                 }else{
//                     board[x][cctv.second] = lookArea
//                     print("look from[${cctv.first}][${cctv.second}](level$levelForDebug) to [$x][${cctv.second}] => $lookAreaCount, way : $way\n")
//                 }
//                 lookAreaCount++
        
//             }
//         }
//         down -> {
//             for(x in cctv.first+1 until n){ // cctv 위에서부터 n-1까지
//                 if(board[x][cctv.second] == wall){
//                     break
//                 }
//                 if(board[x][cctv.second] in cctvRange){
//                     continue
//                 }
//                 if(board[x][cctv.second] >= lookArea){
//                     board[x][cctv.second]++
//                 }else{
//                     board[x][cctv.second] = lookArea
//                     print("look from[${cctv.first}][${cctv.second}](level$levelForDebug) to [$x][${cctv.second}] => $lookAreaCount, way : $way\n")
//                 }
//                 lookAreaCount++
//             }
//         }
//         right -> {
//             for(y in cctv.second+1 until m){ //cctv 오른쪽부터 m-1까지
//                 if(board[cctv.first][y] == wall){
//                     break
//                 }
//                 if(board[cctv.first][y] in cctvRange){
//                     continue
//                 }
//                 if(board[cctv.first][y] >= lookArea){
//                     board[cctv.first][y]++
//                 }else{
//                     board[cctv.first][y] = lookArea
//                     print("look from[${cctv.first}][${cctv.second}](level$levelForDebug) to [${cctv.first}][$y] => $lookAreaCount, way : $way\n")
//                 }
//                 lookAreaCount++
//             }
//         }
//         left -> {
//             for(y in cctv.second-1 downTo 0){ //cctv 왼쪽부터 0까지
//                 if(board[cctv.first][y] == wall){
//                     break
//                 }
//                 if(board[cctv.first][y] in cctvRange){
//                     continue
//                 }
//                 if(board[cctv.first][y] >= lookArea){
//                     board[cctv.first][y]++
//                 }else{
//                     board[cctv.first][y] = lookArea
//                     print("look from[${cctv.first}][${cctv.second}](level$levelForDebug) to [${cctv.first}][$y] => $lookAreaCount, way : $way\n")
//                 }
//                 lookAreaCount++
//             }
//         }
//     }
//     return lookAreaCount
// }

// private fun clear(way : Int, cctv : Pair<Int, Int>) { // 여기서 lookArea 된 count 를 리턴?
//     when(way){
//         up -> {
//             for(x in cctv.first-1 downTo 0){ // cctv 아래서부터 0까지
//                 if(board[x][cctv.second] == wall){
//                     break
//                 }
//                 if(board[x][cctv.second] > lookArea){
//                     board[x][cctv.second]--
//                 }else if(board[x][cctv.second] == lookArea){
//                     board[x][cctv.second] = 0
//                 }
//             }
//         }
//         down -> {
//             for(x in cctv.first+1 until n){ // cctv 위에서부터 n-1까지
//                 if(board[x][cctv.second] == wall){
//                     break
//                 }
//                 if(board[x][cctv.second] > lookArea){
//                     board[x][cctv.second]--
//                 }else if(board[x][cctv.second] == lookArea){
//                     board[x][cctv.second] = 0
//                 }
//             }
//         }
//         right -> {
//             for(y in cctv.second+1 until m){ //cctv 오른쪽부터 m-1까지
//                 if(board[cctv.first][y] == wall){
//                     break
//                 }
//                 if(board[cctv.first][y] > lookArea){
//                     board[cctv.first][y]--
//                 }else if(board[cctv.first][y] == lookArea){
//                     board[cctv.first][y] = 0
//                 }
//             }
//         }
//         left -> {
//             for(y in cctv.second-1 downTo 0){ //cctv 왼쪽부터 0까지
//                 if(board[cctv.first][y] == wall){
//                     break
//                 }
//                 if(board[cctv.first][y] > lookArea){
//                     board[cctv.first][y]--
//                 }else if(board[cctv.first][y] == lookArea){
//                     board[cctv.first][y] = 0
//                 }
//             }
//         }
//     }
// }


/*
    알고리즘 수정 로그

    1. cctv level 을 1..4 로 착각했던점을 수정
    2. 다음 dfs로 넘어가고, 현재cctv가 바라본 lookArea를 빈칸으로 clear시켜줌

    3. 여기서 생각나는 개선안?
    - 지금은 cctv를 돌리고, 적용시키고, 다음detpth 나가고, 지금 가지가 끝나면 clear gksek
    - 완전한 백트래킹 방식
    - 아 이거 안된다. 겹치는 look area 의 경우엔 clear 하면서 지워지는걸 효율적으로 막을 수 없다
    - 브루트포스로 가자
    - 폐기 폐기
    - 처음 감시할때는 10으로 설정
    - 중첩되면 +1
    - clear할때는 10이면 0으로
    - 10 이상이면 -1
    => 성공

    - 모든 cctv의 방향을 정하고 한번에 lookArea 확인
    - 브루트포스
*/

private fun printBoard() : Int {
    var count = 0
    print("printBoard=====\n")
    for(x in 0 until n){
        for(y in 0 until m){
            if(board[x][y] >= lookArea){
                print("${board[x][y]} ")
            }else{
                print("0${board[x][y]} ")
            }
        }
        print("\n")
    }
    return count
}