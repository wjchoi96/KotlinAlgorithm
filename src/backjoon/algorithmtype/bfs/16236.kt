//gold3
/*
    n*m
    아기상어 1마리

    물고기는 자연수의 크기를 가지고있다
    아기상어의 크기는2
    1초에 상하좌우 인접한 한칸씩 이동

    자신보다 큰 물고기가 있는칸은 지나갈 수 없다
    크기가 같은 물고기는 먹을 수 없지만, 지나갈수있다

    자신의 크기와 같은 수의 물고기를 먹으면 크기가 +1 된다

    1. 더이상 먹을 수 있는 물고기가 공간에 없다면 아기상어는 엄마상어를 찾으러 갇나
    2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다
    3. 먹을 수 있는 몰고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다
     - 거리는 아기상어가 있는 칸에서 물고기가 있는 칸으로 이동할때, 지나야 하는 칸의 개수의 최소값
     - 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그중에서는 가장 왼쪽 물고기를 먹는다

    0 : 빈칸
    9 : 상어의 위치
    나머지 : 물고기의 크기

    1. 전체 칸을 탐색
    2. 현재 먹을 수 있는 물고기의 타일들을 꺼낸다
    3. bfs 진행하여 해당 타일까지의 거리 측정
    4. 해당 타일의 물고기를 먹고 1번 진행

    2 <= n <= 20

    20*20 = 400 시간 될것같은데

    bfs 메소드
    input : start, dest
    output : disit( start to dest )

    현재 아기상어의 크기
    현재 아기상어가 먹은 물고기의 수

    ===============================
    갑자기 생각난 다른 아이디어 => 이거 먼저 해보자

    1. 처음 상어 위치를 가지고 bfs를 진행, 해당 위치에서의 disit 목록을 갱신
    2. board를 순회하며 disit를 이용해 물고기와, 거리를 비교하여 먹을 물고기 탐색
    3. 먹는다( 해당 위치로 상어 이동, eatCount++, 조건에 따라 크기 증가 ) 

    4. 바뀐 상어의 위치에 따른 disit를 갱신하기 위해 1번 진행

    제출
    1. 시간초과(2%)
    : 매번 모든 board 를 순회하지말고 fish 위치정보를 저장한 배열만 순회해보자

    2. 성공!
    : bfs 내부에서 한번에 체크가 가능할까?
    : dx, dy 를 조절하면 거리가 가까울때 상단, 왼쪽item 을 우선시 하도록 할 수 있지 않을까
    : 나는 while() -> bfs + board 순회 를 진행했었고 개선한 버전이
    while() -> bfs + fishs 순회를 진행
    => 개선해나가면 whil() -> bfs 로만 진행 가능한거같은데

    개선
    1. 성공

*/

import java.io.*
import java.util.*
private lateinit var board : Array<Array<Int>>
private lateinit var disit : Array<Array<Int>>
private var sharkSize : Int = 2
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    board = Array(n){Array(n){-1}}
    lateinit var shark : Pair<Int, Int>
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until n){
            board[i][j] = st.nextToken().toInt()
            if(board[i][j] == 9){
                shark = Pair(i, j)
            }
        }
    }

    var moveWay : Int = 0
    var fishEatCount : Int = 0
    while(true){
        val closerFish : Pair<Int, Int>? = bfs2(shark, n)
        // 먹을 물고기가 없다 
        if(closerFish == null){
            break
        }
        //move 
        moveWay += disit[closerFish.first][closerFish.second]
        board[shark.first][shark.second] = 0 // 원래 상어의 위치를 비운다
        board[closerFish.first][closerFish.second] = 9 // 이동한 위치에 상어 배치
        shark = Pair(closerFish.first, closerFish.second) // 상어의 위치정보 저장 갱신

        //eat fish
        fishEatCount++
        if(fishEatCount == sharkSize){
            fishEatCount = 0
            sharkSize++
        }
        // print("eat ")
        // printNode(closerFish)
        // printBoard()
    }

    bw.write("$moveWay\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun bfs2(start : Pair<Int, Int>, n : Int) : Pair<Int, Int>? {
    var closerFish : Pair<Int, Int>? = null
    disit = Array(n){Array(n){-1}}
    val queue : Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(start)
    disit[start.first][start.second] = 0

    // 상단, 좌측을 먼저 검사함으로서 같은 거리라면 상단과 좌측에 우선권을 가지도록 설정
    // dx, dy로 조절 못한다
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)
    while(!queue.isEmpty()){
        val node = queue.poll()
        
        for(i in 0 until 4){
            val nx = dx[i] + node.first
            val ny = dy[i] + node.second

            if(nx<0 || nx>=n || ny<0 || ny>=n){
                continue
            }
            
            //크기가 더 큰 물고기가 있거나, 방문한적이 있는곳이라면 건너뛴다
            if(board[nx][ny] > sharkSize || disit[nx][ny] >= 0){
                continue
            }

            queue.offer(Pair(nx, ny))
            disit[nx][ny] = disit[node.first][node.second] + 1

            // 여기서 물고기를 먹을 수 있나 없나 체크
            // 먹을 수 있는 물고기를 발견하면 해당 물고기의 거리를 측정해 가장 가까운 물고기를 갱신
            if(board[nx][ny] != 0 && board[nx][ny] < sharkSize){
                if(closerFish == null){
                    closerFish = Pair(nx, ny)
                }else{
                    if(disit[nx][ny] < disit[closerFish.first][closerFish.second]){
                        closerFish = Pair(nx, ny)
                    }
                    // 같은 거리일때는 상단, 같은 상단일때는 좌측에 우선을 둔다
                    else if(disit[nx][ny] == disit[closerFish.first][closerFish.second]){
                        // 같은 줄에 있을때
                        if(closerFish.first == nx){
                            // y값이 낮은쪽이 우선 -> 좌측 우선
                            if(ny < closerFish.second){
                                closerFish = Pair(nx, ny)
                            }
                        }
                        // 다른줄에있을때 -> x값이 낮은쪽이 우선 -> 상단 우선
                        else if(nx < closerFish.first){
                            closerFish = Pair(nx, ny)
                        }
                    }
                }
            }
        }
    }
    return closerFish
}


// solve 1
private fun solve1(){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    board = Array(n){Array(n){-1}}
    lateinit var shark : Pair<Int, Int>
    val fishs : ArrayList<Pair<Int, Int>> = ArrayList()
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until n){
            board[i][j] = st.nextToken().toInt()
            if(board[i][j] == 9){
                shark = Pair(i, j)
            }else if(board[i][j] != 0){
                fishs.add(Pair(i, j))
            }
        }
    }

    var moveWay : Int = 0
    var fishEatCount : Int = 0
    var closerFish : Pair<Int, Int>?
    while(true){
        closerFish = null
        bfs(shark, n)
        
        for(fish in fishs){
            val i = fish.first
            val j = fish.second
            if(
                board[i][j] != 0 &&
                disit[i][j] != -1 &&
                board[i][j] < sharkSize
            ){
                if(closerFish == null){
                    closerFish = Pair(i, j)
                }else{
                    // 같은 거리일때는 갱신하지않는다 => 우선순위가 높은 좌상단부터 탐색했기 때문
                    if(disit[i][j] < disit[closerFish.first][closerFish.second]){
                        closerFish = Pair(i, j)
                    }
                }
            }
        }
        // 먹을 물고기가 없다 
        if(closerFish == null){
            break
        }
        //move 
        moveWay += disit[closerFish.first][closerFish.second]
        fishs.remove(closerFish)
        board[shark.first][shark.second] = 0 // 원래 상어의 위치를 비운다
        board[closerFish.first][closerFish.second] = 9 // 이동한 위치에 상어 배치
        shark = Pair(closerFish.first, closerFish.second) // 상어의 위치정보 저장 갱신

        //eat fish
        fishEatCount++
        if(fishEatCount == sharkSize){
            fishEatCount = 0
            sharkSize++
        }
        print("eat ")
        printNode(closerFish)
    }

    bw.write("$moveWay\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(start : Pair<Int, Int>, n : Int){
    disit = Array(n){Array(n){-1}}
    val queue : Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(start)
    disit[start.first][start.second] = 0

    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)
    while(!queue.isEmpty()){
        val node = queue.poll()
        
        for(i in 0 until 4){
            val nx = dx[i] + node.first
            val ny = dy[i] + node.second

            if(nx<0 || nx>=n || ny<0 || ny>=n){
                continue
            }
            
            //크기가 더 큰 물고기가 있거나, 방문한적이 있는곳이라면 건너뛴다
            if(board[nx][ny] > sharkSize || disit[nx][ny] >= 0){
                continue
            }

            queue.offer(Pair(nx, ny))
            disit[nx][ny] = disit[node.first][node.second] + 1
        }
    }
}

private fun printBoard(){
    for(i in 0 until board.size){
        for(j in 0 until board[i].size){
            print("${board[i][j]} ")
        }
        print("\n")
    }
    print("\n")
}
private fun printNode(node : Pair<Int, Int>){
    print("shark[$sharkSize] node[${disit[node.first][node.second]}] : [${node.first}, ${node.second}]\n")
}

/*
solve

eat shark[2] node[2] : [3, 1]
eat shark[3] node[2] : [4, 2]
eat shark[3] node[1] : [3, 2]
eat shark[3] node[2] : [1, 2]
eat shark[4] node[2] : [0, 3]
eat shark[4] node[1] : [0, 2]

eat shark[4] node[2] : [0, 4]
eat shark[4] node[2] : [1, 3]
eat shark[5] node[2] : [1, 1]
eat shark[5] node[1] : [0, 1]
eat shark[5] node[2] : [1, 0]
eat shark[5] node[1] : [2, 0]
eat shark[5] node[1] : [2, 1]
eat shark[6] node[2] : [3, 0]
eat shark[6] node[1] : [4, 0]
eat shark[6] node[1] : [4, 1]
eat shark[6] node[3] : [3, 3]
eat shark[6] node[1] : [2, 3]
eat shark[6] node[2] : [1, 4]
eat shark[7] node[1] : [1, 5]
eat shark[7] node[1] : [0, 5]
eat shark[7] node[2] : [2, 5]
eat shark[7] node[1] : [2, 4]
eat shark[7] node[1] : [3, 4]
eat shark[7] node[1] : [3, 5]
eat shark[7] node[1] : [4, 5]
eat shark[8] node[1] : [4, 4]
eat shark[8] node[1] : [4, 3]
eat shark[8] node[1] : [5, 3]
eat shark[8] node[1] : [5, 2]
eat shark[8] node[1] : [5, 1]
eat shark[8] node[1] : [5, 0]
eat shark[8] node[4] : [5, 4]
eat shark[8] node[1] : [5, 5]
eat shark[9] node[10] : [0, 0]




eat shark[2] node[2] : [3, 1]
eat shark[3] node[2] : [4, 2]
eat shark[3] node[1] : [3, 2]
eat shark[3] node[2] : [1, 2]
eat shark[4] node[2] : [0, 3]
eat shark[4] node[1] : [0, 2]

eat shark[4] node[2] : [1, 1]
eat shark[4] node[1] : [2, 1]
eat shark[5] node[1] : [2, 0]
eat shark[5] node[1] : [1, 0]
eat shark[5] node[2] : [0, 1]
eat shark[5] node[3] : [1, 3]
eat shark[5] node[1] : [1, 4]
eat shark[6] node[1] : [0, 4]
eat shark[6] node[1] : [0, 5]
eat shark[6] node[1] : [1, 5]
eat shark[6] node[2] : [3, 5]
eat shark[6] node[1] : [3, 4]
eat shark[6] node[1] : [3, 3]
eat shark[7] node[1] : [2, 3]
eat shark[7] node[1] : [2, 4]
eat shark[7] node[1] : [2, 5]
eat shark[7] node[2] : [4, 5]
eat shark[7] node[1] : [4, 4]
eat shark[7] node[1] : [4, 3]
eat shark[7] node[1] : [5, 3]
eat shark[8] node[1] : [5, 2]
eat shark[8] node[1] : [5, 1]
eat shark[8] node[1] : [4, 1]
eat shark[8] node[1] : [4, 0]
eat shark[8] node[1] : [3, 0]
eat shark[8] node[2] : [5, 0]
eat shark[8] node[4] : [5, 4]
eat shark[8] node[1] : [5, 5]
eat shark[9] node[10] : [0, 0]
*/