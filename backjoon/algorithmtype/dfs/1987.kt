//gold4
/*
    네이버카페 문제50선 중 dfs 유형

    R*C 의 board
    좌측상단(0,0) (문제에서 표기는 1행 1열로 되어있다) 에 말 존재

    인접한 한칸으로 이동가능
    새로 이동한 칸에 적혀있는 알파벳은 지금까지 지나온 모든 칸에 적혀있는 알파벳과 달라야한다
    => 같은 알파벳이 적힌 칸을 두번 지날 수 없다

    말이 최대한 지나갈수 있는지 구하라

    visitAlpha : Map<Pair<Int, Int>, char> 
    1<= R,C <= 20
    알파벳 개수 26개
    20*20 * 26 = 5200개 (여태 지나온 알파벳 정보를 담을 변수의 최대 크기 예상)
    말이 (0,0)에서 출발하는게 정해져있으니 필요없을듯 => map 자체는 필요하긴 할듯
    ======================================

    단순 생각으로는..
    dfs돌면서 방문한 알파벳들을 만나면 쳐내면 될것같은데

    dfs(0, 0)을 진행하면서
    지나온 알파벳들을 저장 => 어디에? map에 

    ======================================

    list 를 사용하지 않고 map 혹은 set 을 사용해야하는 이유
    list 에서의 탐색(contains)는 O(n) 이지만
    set, map 에서의 탐색(contains)는 O(1) 로 훨씬 빠르기 때문

    탐색이 잦은 작업을 할때는 list 대신, set, map 을 사용

    다만 set 과, map 의 key 값은 중복이 허용되지 않는다

    ======================================
    풀다가 백트래킹을 반복문으로 구현할줄을 몰라서 검색
    : 가능여부도 알아봐야겠는데

    검색도중 dfs를 재귀로 구현하는 방법을 학습하였고, 좀 더 익숙한 재귀 방식으로
    dfs + 백트래킹 방식으로 작성
    참고 : https://yabmoons.tistory.com/84

    ======================================
    이 문제를 반복문으로 풀수 있는지 알아보자
*/


import java.util.StringTokenizer
import java.util.Stack
import java.io.*
private val dest = arrayOf(Pair(1,0), Pair(0,1), Pair(-1,0), Pair(0,-1))
private val stack = Stack<Pair<Int, Int>>()
private lateinit var board : Array<String>
private lateinit var visit : Array<Array<Boolean>>
private val alpha : HashMap<Char, Pair<Int, Int>> = HashMap()
private var n = 0
private var m = 0
fun main(arsg : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    board = Array(n){""}
    for(i in 0 until n){
        board[i] = br.readLine()
    }
    visit = Array(n){Array(m){false}}
    
    // bw.write("${dfs(Pair(0, 0))}\n")
    dfs2(Pair(0,0), 1)
    bw.write("$maxDepth\n")

    bw.flush()
    bw.close()
    br.close()
}
/*
    재귀방식으로 dfs구현 + 백트래킹
*/
private var maxDepth : Int = Int.MIN_VALUE
private fun dfs2(start : Pair<Int, Int>, depth : Int){
    maxDepth = Math.max(maxDepth, depth)
    print("[Visit] node[$depth] : [${start.first}][${start.second}] => ${board[start.first][start.second]}\n")
    visit[start.first][start.second] = true
    alpha.put(board[start.first][start.second], start)
    for(i in 0 until 4){
        val nx = start.first + dest[i].first
        val ny = start.second + dest[i].second
        if(nx<0 || nx>=n || ny<0 || ny>=m){
            continue
        }
        if(visit[nx][ny] == true){
            continue
        }
        // 이미 거쳐온 알파벳이다
        if(alpha.contains(board[nx][ny])){
            continue
        }       
        print("[Add] node[$depth] : [${nx}][${ny}] => ${board[nx][ny]}\n")
        dfs2(Pair(nx, ny), depth + 1)

        print("[Remove] node[$depth] : [${nx}][${ny}] => ${board[nx][ny]}\n")
        // 백트래킹을 위해 해당 알파벳을 방문 안한것으로 처리
        visit[nx][ny] = false
        alpha.remove(board[nx][ny]) 
    }
}

/*
    한번 거쳐간 depth 보다 더 긴 depth 가 있을경우, 이미 지나온 depth의 map data를 삭제해야하는데..
*/
private fun dfs(start : Pair<Int, Int>) : Int{
    var count = 1
    stack.push(start)
    visit[start.first][start.second] = true
    alpha.put(board[start.first][start.second], start)

    while(!stack.isEmpty()){
        val node = stack.pop()
        for(i in 0 until 4){
            val nx = node.first + dest[i].first
            val ny = node.second + dest[i].second   
            if(nx<0 || nx>=n || ny<0 || ny>=m){
                continue
            }
            if(visit[nx][ny] == true){
                continue
            }

            // 이미 거쳐온 알파벳이다
            if(alpha.contains(board[nx][ny])){
                continue
            }

            stack.push(Pair(nx, ny))
            visit[nx][ny] = true
            alpha.put(board[nx][ny], Pair(nx, ny))
            count++
        }
    }
    return count
}
