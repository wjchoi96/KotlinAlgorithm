//gold4
/*
    네이버 카페 문제 50선 중 bfs 유형

    n*m의 board
    5 ≤ N, M ≤ 100
    1초

    2변 이상이 외부 공기에 접촉한것은 한시간 뒤에 녹아 사라진다

    치즈내부에 있는 공기는 외부공기로 취급하지 않는다

    board 의 가장자리는 치즈가 놓이지 않는다

    치즈가 모두 녹아 사라지는데 걸리는 시간을 구하시오

    

    1. board 를 순회하여 bfs를 돈다
    - 외부공기 => -1
    - 치즈 => 1
    - 내부공기 => 0
    => 무조건 외부공기 순회를 먼저 진행하기때문에 외부공기의 값들이 -1로 모두 초기화가 되어버린다
    => 치즈를 순회하면서 두변이상이 외부공기에 닿아있다면 값을 2로 변경, 아니라면 1로 유지

    2. 하루가 지나서 board 를 다시 순회한다
    - 외부공기 => 건너뛴다
    - 치즈2 => 외부공기값으로 변경
    - 치즈1 => bfs를 실시하며 2변 이상이 외부공기에 닿아있다면 2로 변경

    제출
    1. 틀렸습니다(9%)
    => board 를 돌면서 visit 하지않은 공기에서 공기 dfs를 실행하는데, 이러면 내부공기가 남아나질않는다
    => (0, 0)은 확실히 공기니까 해당 위치에서 air bfs 를 한번만 실행한다

    2. 메모리초과(9%)
    => 당황

    3. 메모리초과(9%)
    => 치즈list 를 사용하는 방식을 테스트해봤다

    4. 메모리초과(9%)
    => 큐를 전역변수로 설정해봤다
    => 개선 방법 하나 찾음
    => air bfs, cheess bfs 를 각각 돌리는게 아닌, air bfs 를 돌리며 cheese를 만나면 +1을 해준다( cheese 입장에서는 인접한 air 당 +1을 하나씩 받는것 )
    => board 를 순회하며 cheeseCount 가 2이상이면 공기로 변경

    반복

    이러면 
    air bfs
    board 순회
    를 반복하네

    이게 더 효율적일듯

    지금은 
    ari bfs 1회
    board 순회 중 cheese bfs 반복
    

    5. 틀렸습니다(9%)
    6. 틀렸습니다(9%)
    7. 메모리초과(9%)
    8. 성공 
    - 너무 생각이 꼬인거같아서 환기성으로 검색해서 제일 좋아보이는 정답을 작성해보니 성공하네
*/

/*
    다시 잘 생각해보자
    1. outSideAir 을 -1로 초기화

    day는 1일에서 시작

    day++
    2. outSideBfs를 돌면서 인접한 치즈에 airCount++
    3. airCount >=2 인 치즈는 녹인다 -> outSideAir 로 변경


    !!!!!!!!! 문제이해를 잘못했었다
    내부공기를 둘러싼 치즈벽에 구멍이생겨서 외부공기와 내부공기가 닿으면
    내부공기도 외부공기 취급을 받게되는것
    
*/



import java.io.*
import java.util.StringTokenizer
import java.util.Queue
import java.util.LinkedList
private val dx = arrayOf(1, 0, -1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private lateinit var visit : Array<Array<Int>>
private lateinit var board : Array<Array<Int>>
private lateinit var airSideCount : Array<Array<Int>>
private const val outSideAir = 5
private const val cheese = 1
private var n : Int = 0
private var m : Int = 0
private val queue : Queue<Pair<Int, Int>> = LinkedList()
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    
    board = Array(n){Array(m){0}}
    visit = Array(n){Array(m){0}}
    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        for(j in 0 until m){
            board[i][j] = st.nextToken().toInt()
        }
    }

    var day = 0
    var check = true
    while(check){
        initVisit()
        bfs()

        check = false
        for(x in 0 until n){
            for(y in 0 until m){
                if(board[x][y] != 0){
                    check = true
                    break
                }
            }   
            if(check){
                break
            }
        }
        day++
    }

    bw.write("$day\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(){
    queue.offer(Pair(0, 0))
    visit[0][0] = 1

    while(!queue.isEmpty()){
        val node = queue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            if(nx<0 || nx>=n || ny<0 || ny>=m){
                continue
            }
            if(board[nx][ny] == 0 && visit[nx][ny] == 0){
                queue.offer(Pair(nx, ny))
                visit[nx][ny] = 1
            }
            if(board[nx][ny] == 1){
                visit[nx][ny]++
                if(visit[nx][ny]>=2){
                    board[nx][ny] = 0
                }
            }
        }
    }
}

private fun initVisit(){
    for(i in 0 until n){
        for(j in 0 until m){
            visit[i][j] = 0
        }
    }
}

/*
import java.io.*
import java.util.StringTokenizer
import java.util.Queue
import java.util.LinkedList
private val dx = arrayOf(1, 0, -1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private lateinit var visit : Array<Array<Boolean>>
private lateinit var board : Array<Array<Int>>
private lateinit var airSideCount : Array<Array<Int>>
private const val outSideAir = 5
private const val cheese = 1
private var n : Int = 0
private var m : Int = 0
private val queue : Queue<Pair<Int, Int>> = LinkedList()
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    board = Array(n){Array(m){0}}
    airSideCount = Array(n){Array(m){0}}
    var cheeseCount = 0
    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        for(j in 0 until m){
            board[i][j] = st.nextToken().toInt()
            if(board[i][j] == cheese){
                cheeseCount++
            }
        }
        // board[i] = Array(m){st.nextToken().toInt()}
    }

    initOutSideAirBfs()
   
    var meltingCount = 0
    var day = 1
    while(cheeseCount != 0){
        day++
        outSideAirBfs2(Pair(0, 0))
        for(x in 0 until n){
            for(y in 0 until m){
                if(airSideCount[x][y] >= 2){
                    meltingCount++
                    board[x][y] = outSideAir
                    airSideCount[x][y] = 0
                }
            }
        }
        
        print("cheeseCount : $cheeseCount, meltingCount : $meltingCount\n")
        // 여기서 종료조건을 판독해야하는데
        cheeseCount -= meltingCount
        meltingCount = 0
        printBoard()
    }

    bw.write("$day\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun initOutSideAirBfs(){
    visit = Array(n){Array(m){false}}
    queue.offer(Pair(0, 0))
    visit[0][0] = true
    board[0][0] = outSideAir

    while(!queue.isEmpty()){
        val node = queue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            if(nx<0 || nx>=n || ny<0 || ny>=m){
                continue
            }
            if(board[nx][ny] != 0 || visit[nx][ny] == true){
                continue
            }
            queue.offer(Pair(nx, ny))
            visit[nx][ny] = true
            board[nx][ny] = outSideAir
        }
    }
}

private fun outSideAirBfs2(start : Pair<Int, Int>){
    visit = Array(n){Array(m){false}}
    queue.offer(start)
    visit[start.first][start.second] = true

    while(!queue.isEmpty()){
        val node = queue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            if(nx<0 || nx>=n || ny<0 || ny>=m){
                continue
            }
            if(board[nx][ny] == cheese){
                airSideCount[nx][ny]++
            }
            if(board[nx][ny] != outSideAir || visit[nx][ny] == true){
                continue
            }
            queue.offer(Pair(nx, ny))
            visit[nx][ny] = true
        }
    }
}






// 메모리 초과
private fun try1(){
     val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    board = Array(n){Array(m){0}}
    visit = Array(n){Array(m){false}}
    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        board[i] = Array(m){st.nextToken().toInt()}
    }

    outSideAirBfs(Pair(0, 0))
    for(x in 0 until n){
        for(y in 0 until m){
            if(visit[x][y] == false && board[x][y] == cheese){
                cheeseBfs(Pair(x, y))
            }
        }
    }
    printBoard()

    var day = 2
    var cheeseCount = Int.MIN_VALUE
    while(cheeseCount != 0){
        visit = Array(n){Array(m){false}}
        day++
        cheeseCount = 0
        for(x in 0 until n){
            for(y in 0 until m){
                when(board[x][y]){
                    cheese -> {
                        cheeseCount++
                        if(visit[x][y] == false){
                            cheeseBfs(Pair(x, y))
                        }
                    }
                }
            }
        }
        printBoard()
    }

    bw.write("$day\n")
   
    bw.flush()
    bw.close()
    br.close()
}
*/
/*
    상한치즈의 조건을 만족한 치즈는 상한치즈로 바꾸고
    다음날에 board 를 순회돌며 상한치즈는 공기로, 치즈는 bfs를 실시하는데
    day 가 시작되면 상한치즈가 공기로 바뀌는게 먼저 시행되고, 치즈 bfs를 시행해야한다
    => 결국 치즈 bfs에서 상한치즈의 조건을 만족하면 공기로 바꾸면 되는거 아닌가?
    => 첫번째 bfs를 시행하면 하루가 된게 아닌, 하루가 지난상태가 되는것 => day=2 로 시작
*/
/*
private fun cheeseBfs(start : Pair<Int, Int>){
    queue.offer(start)
    visit[start.first][start.second] = true

    while(!queue.isEmpty()){
        val node = queue.poll()
        var airCount = 0
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            if(nx<0 || nx>=n || ny<0 || ny>=m){
                continue
            }
            //바깥공기라면 node의 air count 를 올려준다
            if(board[nx][ny] == outSideAir){
                airCount++
            }
            //치즈가 아니거나, 방문한 곳이라면 continue
            if(board[nx][ny] != cheese || visit[nx][ny] == true){
                continue
            }
            queue.offer(Pair(nx, ny))
            visit[nx][ny] = true
        }
        // 바깥공기와 2변 이상 접해있다면 상한치즈로 바꾼다 => 하루가 지났을때 상한치즈를 air 로 바꾸는 작업이 먼저 진행되어야 하니, 그냥 air 로 바꾼다
        if(airCount >= 2){
            board[node.first][node.second] = outSideAir 
        }
    }
}

private fun outSideAirBfs(start : Pair<Int, Int>){
    queue.offer(start)
    visit[start.first][start.second] = true
    board[start.first][start.second] = outSideAir

    while(!queue.isEmpty()){
        val node = queue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            if(nx<0 || nx>=n || ny<0 || ny>=m){
                continue
            }
            if(board[nx][ny] != 0 || visit[nx][ny] == true){
                continue
            }
            queue.offer(Pair(nx, ny))
            visit[nx][ny] = true
            board[nx][ny] = outSideAir
        }
    }
}

private fun printBoard(){
    print("\nprintBoard\n")
    for(i in 0 until board.size){
        for(j in 0 until board[i].size){
            print("${board[i][j]} ")
        }
        print("\n")
    }
    printAirCount()
    print("\n")
}
private fun printAirCount(){
    print("printAirCount\n")
    for(i in 0 until airSideCount.size){
        for(j in 0 until airSideCount[i].size){
            print("${airSideCount[i][j]} ")
        }
        print("\n")
    }
    print("\n")
}
 */