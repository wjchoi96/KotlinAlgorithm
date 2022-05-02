// gold2
/*
    바킹독님 알고리즘 강의 중 시뮬레이션 유형

    2048

    m*n board
    전체 블록을 상하좌우 네 방향중 하나로 이동시킬 수 있다
    같은 값을 가지는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다

    각 블록들은 한번의 이동에 한번의 합체밖에 못한다
    블록은 추가되지 않는다

    똑같은 수가 세개가 있는경우는, 이동하려고 하는 쪽의 칸이 먼저 합쳐진다
    예) 왼쪽으로 이동하는경우 y idx가 적은 블록들이 우선권을 가진다
    -> 이동하려고 하는쪽들의 블록을 먼저 합치게 하면 될듯

    최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하라

    1 <= N <= 20
    0 : 빈칸
    이외의값 : 블록의 값
    2 <= 블록값 <= 1024, 2의 제곱꼴

    블록은 적어도 하나 주어진다
*/
/*
    알고리즘 선택
    1. 브루트 포스
    2. 백트래킹

    백트래킹으로 진행하려면 밀었던 블록을 다시 원상복구 시키는 메소드가 추가되어야하는데,
    이때 합체된 블록을 다시 나누는 작업도 필요할것
    합체된 블록을 다시 나누려면, 어떤게 방금 합쳐졌던 블록인지 정보가 필요한데, 너무 비효율적일것 같다

    board를 copy해서 브루트 포스로 진행하는것이 좋아보인다

    필요한 기능
    1. 블록을 미는 기능
    2. 블록을 합치는 기능
    3. 하나의 이동에 각 블록은 한번만 합체할 수 있는 기능

    1,2는 한번에 처리되어야 하고, 3번은 2번에 포함되어야할듯
*/

/*
    1. 블록을 상하좌우 중 한방향으로 미는 기능

    방향을 정한다
    up, down, right, left

    up, down의 경우 x축 이동
    right, left의 경우 y축 이동

    up down 기준으로 작성
    start = arrayOf(0, n)
    dx = arrayOf(1,-1)
    모든 y열을 옮겨줘야한다

    하나의 y열을 기준으로 작성 => 0
    up의 경우 
    0..n-1을 순회하며 가장 위에 있는 block을 찾아낸다
    해당 block.x 에 +1 을 계속 해주면 이동시킨다
    - 실제로 board에 적용시키지는 않고, 벽(n-1)에 도착하거나, 블록을 만나는경우 loop이 종료가 되니
    그때 board 에 적용시켜주면 된다
    - 이동중에 block을 만나면 합체할수 있나 체크.
        - 합체할 수 있다면 합체 후 break
        - 합체할 수 없다면 그대로 break
    - block을 만나지 못하고 벽(n-1)에 도착하면 loop 종료로 자연스럽게 멈춘다
*/

/*
    공용 메소드로 변경하기 위해 필요한것

    1. 고정축 : y 인지 x인지
    2. wall : 0인지 n-1인지
    3. 이동하는 방향 -> dx, dy로 체크 가능
    => dy가 0이면, 결국 고정 아닌가?

    3. 근데 결국 board 가 아니라 하나의 1차원 배열에서 일어나는 일 아닌가?
    - 얕은복사로 해당 행이나 열을 넘겨주면 안되나? => x
    => x축은 되는데, y축이안된다
*/
/*
    제출
    1. 틀렸습니다(0%)
    - merge나 move를 한번도 하지 않으면 max block값이 초기값으로 출력되는 문제 수정

    2. 틀렸습니다(0%)
    - copy map이 잘못되어있었다
    - row가 얕은복사로 진행되고있었다

    3. 맞았습니다
*/
private enum class Direction(val v : Int){
    Up(0),
    Down(1),
    Right(2),
    Left(3),
}
// 게임판 전체를 밀어버리는 메소드
private fun pushBoard(d : Direction){
    combine = Array(n){Array(n){false}} //  한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다
    // print("pushBoard $d\n")
    for(i in 0 until n){
        pushRowCol(i, d)
    }
}
// 하나의 열 또는 행의 블록을 모두 밀어버리는 메소드
private fun pushRowCol(rowCol : Int, d : Direction){
    /*
        up, down 이면 x축을 순회하며 block을 찾아야하고
        right, left 이면 y축을 순회하며 block을 찾아야한다

        for문 은 역순이면 downTo를 써야하기때문에 그걸 보완할 수 있나 찾아보던지
        while + idx 를 써서 진행해야할것 같다

        방향에 따른 start
        방향에 따른 end
        방향에 따른 +- value

        start 부터 end까지 value를 적용해주며 반복하다가
        block을 만나면 멈춤 => pushBlock

        up
        (n-1, col) to (0, col)
        down
        (0, col) to (n-1, col)
        right
        (row, n-1) to (row, 0)
        down
        (row, 0) to (row, n-1)
        미려는 방향쪽에서 시작해서 미려는 방향쪽에 있는 block부터 밀어줘야한다
    */
    val dStart = arrayOf(
        Pair(n-1, rowCol), 
        Pair(0, rowCol),
        Pair(rowCol, n-1),
        Pair(rowCol, 0)
    )
    // while문에 걸맞는 종료조건이 되기 위해 +- 1 씩 더 해준다
    val dEnd = arrayOf(
        Pair(-1, rowCol),
        Pair(n, rowCol),
        Pair(rowCol, -1),
        Pair(rowCol, n)
    )
    val dv = arrayOf(
        Pair(-1, 0),
        Pair(1, 0),
        Pair(0, -1),
        Pair(0, 1)
    )
    var start = dStart[d.v]
    val end = dEnd[d.v]
    // 한 열 혹은 횡을 모두 순회하며 block을 찾고, 찾은 block은 밀어버린다
    while(start.first != end.first || start.second != end.second){
        // print("check [${start.first}][${start.second}] => ")
        if(board[start.first][start.second] != 0){ // block을 찾았다
            // print("block!!\n")
            // maxBlock = Math.max(maxBlock, board[start.first][start.second]) // block을 만나면 처리
            checkMax(board[start.first][start.second])
            pushBlock(start, d)
        }
        // else{
            // print(" 0\n")
        // }
        start = Pair(start.first + dv[d.v].first, start.second + dv[d.v].second)
    }
}

//block을 해당 방향으로 밀어버리는 메소드
private fun pushBlock(b : Pair<Int, Int>, d : Direction){
    val dx = arrayOf(1, -1, 0, 0)
    val dy = arrayOf(0, 0, 1, -1) // block 이 나가가는 x,y값
    val dWall = arrayOf(
        Pair(n-1, -1), 
        Pair(0, -1), 
        Pair(-1, n-1), 
        Pair(-1, 0)
    ) // 방향에 따른 종료조건(벽을 만나면 종료)
    val wall = dWall[d.v]
    /*
        block을 찾으면 해당 메소드가 호출되는거니까
        현재 무조건 while문 첫번째부터 시작이된다
    */
    
    var block = b
    var nextSpace = Pair(block.first + dx[d.v], block.second + dy[d.v])
    while(true){
        /*
            벽을 만난 경우... 흠
            x를 체크해야하는지, y를 체크해야하는지 어케알지
            x==wall || y==wall 로 해놓고, 
            대상이 아닌 축은 -1 이런거로 설정해놓으면 절대 같을 일이 없다
        */
        // block을 만나지않고 벽을 만남 => 벽으로 move
        if(block.first == wall.first || block.second == wall.second){
            moveBlock(from=b, to=block)
            break
        }
        // 다른 block을 만난 경우 => merge 가능하다면 merge, 현재 block 포인터 위치로 move
        if(board[nextSpace.first][nextSpace.second] != 0){
            if(
                combine[nextSpace.first][nextSpace.second] == false &&
                board[nextSpace.first][nextSpace.second] == board[b.first][b.second]
            ) {
                mergeBlock(from=b, to=nextSpace)
            }else{
                moveBlock(from=b, to=block)
            }
            break
        }
        
        // way에 따라 다음 칸으로 이동
        block = Pair(block.first + dx[d.v], block.second + dy[d.v])
        nextSpace = Pair(block.first + dx[d.v], block.second + dy[d.v])
    }
}
/*
    max를 여기서만 체크하면 한번도 merge, move를 하지 않는 tc는 통과하지 못한다
*/
private fun mergeBlock(from : Pair<Int, Int>, to : Pair<Int, Int>){
    board[to.first][to.second] += board[from.first][from.second]
    combine[to.first][to.second] = true

    combine[from.first][from.second] = false
    board[from.first][from.second] = 0

    // maxBlock = Math.max(maxBlock, board[to.first][to.second])
    checkMax(board[to.first][to.second])
    // print("merge block => from[${from.first}][${from.second}] - to[${to.first}][${to.second}]\n")
    // printBoard()
}
private fun moveBlock(from : Pair<Int, Int>, to : Pair<Int, Int>){
    if(from == to){ 
        return
    }
    board[to.first][to.second] = board[from.first][from.second]
    combine[to.first][to.second] = false

    combine[from.first][from.second] = false
    board[from.first][from.second] = 0

    // maxBlock = Math.max(maxBlock, board[to.first][to.second])
    checkMax(board[to.first][to.second])
    // print("move block => from[${from.first}][${from.second}] - to[${to.first}][${to.second}]\n")
    // printBoard()
}

/*
    방향이 이상하다
    merge
*/
private var n = 0
private lateinit var originalBoard : Array<Array<Int>>
private lateinit var board : Array<Array<Int>>
private lateinit var combine : Array<Array<Boolean>>
private var debugList : ArrayList<Array<Array<Int>>> = ArrayList()
private var maxBlock = Int.MIN_VALUE
fun main(args : Array<String>){
    val bw = System.out.bufferedWriter()
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    originalBoard = Array(n){Array(n){0}}
    for(i in 0 until n){
        originalBoard[i] = br.readLine().split(' ').map { it.toInt() }.toTypedArray()
    }

    // 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록
    playGame()
    bw.write("$maxBlock\n")
    printBoard()

    bw.flush()
    bw.close()
    br.close()
}
/*
    시간복잡도 계산
    4*4*4*4*4 = 1024가지의 push 경우의 수

    board 복사 =>
    board 순회 => 20*20 = 400 

    push board : 최대 20
    push rowCol : 최대 20
    push block : 최대 20
    => 8000

    3,276,800,000 
    1초에 5억정도면 간당간당
    3억 정도니까 가능할듯
*/

private fun playGame(){
    // 4가지의 경우의 수를 조합해 5번 실행

    // copyMap()                    
    // pushBoard(Direction.Up)
    // print("up\n")
    // printBoard()
    // pushBoard(Direction.Down)
    // print("down\n")
    // printBoard()
    // pushBoard(Direction.Right)
    // print("right\n")
    // printBoard()
    // pushBoard(Direction.Up)
    // print("up\n")
    // printBoard()
    // pushBoard(Direction.Left)
    // print("left\n")
    // printBoard()
    // return

    Direction.values().forEach { d1 ->
        Direction.values().forEach { d2 ->
            Direction.values().forEach { d3 ->
                Direction.values().forEach { d4 ->
                    Direction.values().forEach { d5 ->
                        copyMap()
                        val dArr = arrayOf(d1, d2, d3, d4, d5)
                        print("start new game[${dArr[0]} ${dArr[1]} ${dArr[2]} ${dArr[3]} ${dArr[4]}]\n")
                        debugList.clear()
                        for(i in 0 until 5){
                            pushBoard(dArr[i])
                            // addDebugMap()
                        }
                        // printBoard()
                        if(maxBlock == 64){
                            printDebugBoardList()
                            return
                        }
                    }
                }
            }
        }
    }
}
private fun copyMap(){
    board = Array(n){Array(n){0}}
    for(x in 0 until n){
        for(y in 0 until n){
            board[x][y] = originalBoard[x][y]
        }
    }
    print("copy map after ")
    printBoard()
}

private fun addDebugMap(){
    val copy = Array(n){Array(n){0}}
    for(i in 0 until n){
        copy[i] = board[i]
    }
    debugList.add(copy)
    print("add debug map => ${debugList.size}\n")
    printBoard()
}

private var isPrint = false
private fun checkMax(v1 : Int){
    if(v1 == 64){
        if(!isPrint){
            print("catch 64=======\n")
            printBoard()
            isPrint = true
        }
    }
    maxBlock = Math.max(maxBlock, v1)
}

private fun printBoard(){
    print("=====print borad ====\n")
    for(x in 0 until n){
        for(y in 0 until n){
            print("${board[x][y]} ")
        }
        print("\n")
    }
}

private fun printDebugBoardList(){
    print("=====debug list=====\n")
    for((idx, b) in debugList.withIndex()){
        print("debug[$idx]\n")
        for(x in 0 until n){
            for(y in 0 until n){
                print("${b[x][y]} ")
            }
            print("\n")
        }
    }
}
/*
test case
3
2 2 2
4 4 4
8 8 8
=> 16

3
2 2 2
2 2 2
2 2 2
=> 8

5
2 0 0 0 0
2 0 0 0 0
4 0 0 0 0
2 0 0 0 0
2 0 0 0 0
=> up 한번 결과
4 0 0 0 0
4 0 0 0 0
4 0 0 0 0
0 0 0 0 0
0 0 0 0 0

3
2 0 2
0 2 0
2 0 2
=> 4

3
256 256 128
32 16 128
128 128 128
=> 1024

1
10
=> 10

10
16 16 8 32 32 0 0 8 8 8
16 0 0 0 0 8 0 0 0 16
0 0 0 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
=> 64

5
2 2 4 8 16
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
2 2 4 8 16
=> 64

2
16 0
0 0
=> 16

!!!!!!!!!!
7
2 2 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 2 2 0 2 2 2
2 2 2 2 2 2 0
2 2 2 2 2 2 0
=> 32
start new game[Up Down Right Up Left]
wrong => 64

!!!!!!!!
10
0 0 64 32 32 0 0 0 0 0
0 32 32 64 0 0 0 0 0 0
0 0 128 0 0 0 0 0 0 0
64 64 128 0 0 0 0 0 0 0
0 0 64 32 32 0 0 0 0 0
0 32 32 64 0 0 0 0 0 0
0 0 128 0 0 0 0 0 0 0
64 64 128 0 0 0 0 0 0 0
128 32 2 4 0 0 0 0 0 0
0 0 128 0 0 0 0 0 0 0
=> 1024
wrong => 512


*/


/*
private fun pushBlockUp(){
    // up 먼저 구현해보자
    var lastBlockX = n-1
    for(y in 0 until n){
        var blockX : Int? = null
        for(x in lastBlockX downTo n){ // 위에서부터 내려와 가장 위에있는 block을 찾아낸다
            if(board[x][y] != 0){
                blockX = x
            }
        }
        if(blockX == null){ // block이 해당 열에 없는경우 -> 다음 열로 넘어간다
            continue
        }
        val originalBlockX : Int = blockX
        while(true){
            if(blockX == null){
                break
            }
            // 벽에 도착한 경우 -> block을 이동시키고 종료
            if(blockX == n-1){
                moveBlock(Pair(originalBlockX, y), Pair(blockX, y))
                lastBlockX = originalBlockX // 다음 block을 찾을 시작 idx
                break
            }

            //block을 만난경우
            if(board[blockX][y] != 0){
                if(
                    combine[blockX][y] == false && 
                    board[blockX][y] == board[originalBlockX][y]
                ){
                    mergeBlock(Pair(originalBlockX, y), Pair(blockX, y))
                    lastBlockX = originalBlockX // 다음 block을 찾을 시작 idx
                }else{
                    moveBlock(Pair(originalBlockX, y), Pair(blockX, y))
                    lastBlockX = originalBlockX // 다음 block을 찾을 시작 idx
                }
                break
            }
            blockX = blockX + 1
        }
    }
}
*/