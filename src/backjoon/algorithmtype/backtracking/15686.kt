//gold5
/*
    네이버 카페 문제50선 중 백트래킹, 완전탐색 유형

    치킨 배달

    N * M 도시
    빈칸, 치킨집, 집 중 하나
    r행 c열, 1부터 시작

    치킨거리 : 집과 가장 가까운 치킨집 사이의 거리
    치킨거리는 집을 기준으로 정해진다
    각각의 집은 치킨거리를 가지고 있다

    도시의 치킨거리 : 모든집의 치킨거리의 합

    (r1, c1) <-> (r2, c2) 의 거리 => |r1-r2| + |c1-c2|

    0 : 빈칸
    1 : 집
    2 : 치킨집

    해당 도시에서 치킨집 M개를 제외하고 모두 없앤다
    해당 작업을 실시했을때, 도시의 치킨거리의 최소값을 구하라
    => 도시의 치킨거리가 최소가 되도록 M개의 치킨집을 제외하고 모두 없애라

    2 <= N <= 50
    1 <= M <= 13
    
    1 <= 집 <= 2N
    M <= 치킨집 <= 13
    
    1초
*/
/*
    제출
    1. 성공
    
*/

/*
    개선안

    https://www.acmicpc.net/source/38096140
    1. home, chicken 을 각 list 로 가지고있는다
    2. 폐업할 치킨집을 고르는게 아닌, 그대로 유지될 치킨집m개를 선택하는 백트래킹을 실시
    3. 종료조건을 만족하면 home과 liveChicken끼리의 거리를 구해서 조건을 완료한다

    제출
    1. 시간초과
    - 또 같은실수 dfs2 에서 다음 dfs 호출시 i+1 해야하는데 idx+1을 했다

    2. 맞았습니다
*/

/*
    본래 치킨집 크기 c
    크기가c인 배열에서 크기가c-m 인 수열을 뽑고, 해당 수열의 치킨거리가 최소가 되는경우를 구하는것?
    - 같은 조합 + 다른 순서의 수열의 경우의 수는 뺄 수 있게 설정

    1. m개 제거
    2. 현 상태에서 치킨거리 구하기
    3. 반복
*/  
import java.io.*
import java.util.StringTokenizer
private lateinit var board : Array<Array<Int>>
private val chickens : ArrayList<Pair<Int, Int>> = ArrayList()
private var originalChickenCount = 0
private var m = 0 // 페업시키지 않고 살려둘 치킨집
private var n = 0
private var minRoad = Int.MAX_VALUE
/*
    개선 dfs2 에서 사용되는 전역변수
*/
private val homes : ArrayList<Pair<Int, Int>> = ArrayList()
private lateinit var selectChicekns : Array<Pair<Int, Int>>
private lateinit var visit : Array<Boolean>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    board = Array(n){Array(n){0}}
    for(x in 0 until n){
        st = StringTokenizer(br.readLine())
        for(y in 0 until n){
            board[x][y] = st.nextToken().toInt()
            if(board[x][y] == 2){
                chickens.add(Pair(x, y))
                originalChickenCount++
            }
            // use at dfs2
            else if(board[x][y] == 1){
                homes.add(Pair(x, y))
            }
        }
    }
    //use at dfs2
    visit = Array(chickens.size){false}
    selectChicekns = Array(m){Pair(0,0)}

    // print("chickensCount[$originalChickenCount] - m[$m] => ${originalChickenCount - m}\n")
    dfs2()
    bw.write("$minRoad\n")

    bw.flush()
    bw.close()
    br.close()
}
/*
    개선 dfs
*/
private fun dfs2(idx : Int = 0, depth : Int = 0){
    if(depth == m){
        var city = 0
        for(home in homes){
            var min = Int.MAX_VALUE
            for(chicken in selectChicekns){
                min = Math.min(min, getLength(chicken, home))
            }
            city += min
        }
        minRoad = Math.min(city, minRoad)
        return
    }
    for(i in idx until chickens.size){
        if(visit[i] == false){
            visit[i] = true
            selectChicekns[depth] = chickens[i]
            dfs2(i + 1, depth + 1)
            visit[i] = false
        }
    }   
}
/*
    dfs 루트가 이상한거같은데
    0,1 => dfs(0,1) called
    0,3 => dfs(0,3) called

    1,1 => 건너뛰고
    1,3 => dfs(1,3) called

    2,1 -> 건너뛰고
    2,3

*/
/*
    2개의 for문으로 순회하며 dfs를 수행하는경우
    x, y 를 모두 dfs 인자로 전달받아, 순회의 시작 idx로 사용해선 안된다
    => y축을 끝까지 순회하고 다음 x축이 되었을때 y가 0부터가 아닌 dfs인자로 전달된 y부터 시작한다
    => 순회가 제대로 안된다
    보통 x축을 전달해주고 y는 처음 인자부터 시작하는게 좋다
    => y축을 다 순히하고 x축을 하나씩 넘어가는 방법이 된다
*/
private fun dfs(x : Int = 0, depth : Int = 0){
    if(depth == originalChickenCount - m){
        // print("catch $depth\n") 
        minRoad = Math.min(minRoad, getCityChickenRoad())  // 치킨거리 구하기, 최소값 갱신
        return
    }
    for(nx in x until n){
        for(ny in 0 until n){
            // print("node[$nx][$ny] when [$x][0]: $depth\n")
            if(board[nx][ny] == 2){ // 치킨집이라면
                board[nx][ny] = 0 // 폐업 시킨다
                chickens.remove(Pair(nx, ny))
                // print("destroy[$nx][$ny] when $depth\n")
                dfs(nx, depth + 1) // nx+1 하면 안된다
                board[nx][ny] = 2 // 다시 살려줌
                chickens.add(Pair(nx, ny))
            }
        }
    }
}

private fun getCityChickenRoad() : Int{
    var cityChickenRoad = 0
    for(x in 0 until n){
        for(y in 0 until n){
            if(board[x][y] == 1){ // 집 발견
                cityChickenRoad += getChickenRoad(Pair(x, y))
            }
        }
    }
    return cityChickenRoad
}

private fun getLength(p1 : Pair<Int, Int>, p2 : Pair<Int, Int>) : Int {
    return Math.abs(p1.first - p2.first) + Math.abs(p1.second - p2.second)
}
private fun getChickenRoad(home : Pair<Int, Int>) : Int{
    var min = Int.MAX_VALUE
    for(chicken in chickens){
        min = Math.min(getLength(home, chicken), min)
    }
    print("home[${home.first}][${home.second}] : $min\n")
    return min
}