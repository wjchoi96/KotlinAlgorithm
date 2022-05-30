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
    - pushRowCol 

    개선
    - 코드 양을 줄일 수 있는 곳이 없을까

    1. pushBlock, move, merge block을 개선
    //참고 : https://www.acmicpc.net/source/29119881
    - row or col을 순회하며 block을 찾고, 해당 block값을 list에 저장
    해당 block이 있던 위치는 0으로 변경
    이때 먼저 merge or move되어야 할 block먼저 add되어야한다(이 순서는 지금 push row col 하는것처럼 하면된다)
    - block list를 0부터 순회하며 i+1 item과 비교해서 같은 값이라면 합치고, i+1 값은 제거
    - 이러면 모든 block들이 merge는 한번만 수행한다

    해당 작업을 거친 block list를 board에 적용
    => idx 계산은 dv를 이용
    1. x, y중 둘중 하나는 고정되어야하고 하나는 start지점으로부터 +-idx 작업을 수행해야한다
    => push row col에 방향에 따른 start초기값을 구하는 작업이 되어있다
    => push row col에 방향에 따른 idx이동값을 구하는 작업이 되어있다
    이동해야하는 축의 idx는 +-1, 멈춰있어야 하는 축은 0
    block list의 idx를 dv의 x,y값을 곱하고, start 초기값에 더해준다
    => 멈춰있어야 할 축은 +0이되어서 그대로 멈춰있고
    => 움직여야 할 축은 +-1이 적용된다
    => push row col의 순회과정과 비슷하다

    개선 제출
    1. 성공
    : pushRowCol2 에 구현


    !! 백트래킹도 board copy 를 통해 쉽게 구현이 되는구나
    1. board 원본을 copy해준다
    2. copy는 그대로 두고 board를 가지고 play game
    3. 지금 회차가 끝나면 보관해놨던 copy(원본데이터를 가지고있다) 를 board로 적용
*/

//  ================== 개선 ==================
private fun pushRowCol2(rowCol : Int, d : Direction){
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
    val blockList = ArrayList<Int>()
    while(start.first != end.first || start.second != end.second){
        if(board[start.first][start.second] != 0){ // block을 찾았다
            blockList.add(board[start.first][start.second])
            maxBlock = Math.max(maxBlock, board[start.first][start.second]) // 합쳐지는것 + 처음 초기화시에 체크
            board[start.first][start.second] = 0
        }
        start = Pair(start.first + dv[d.v].first, start.second + dv[d.v].second)
    }

    processBlockList(blockList).forEachIndexed { i, v ->
        // 이러면 하나는 무조건 0이므로 계속 0일거고
        // -를 해야하는곳은 -index, +을 해야하는곳은 +index가 된다
        // start 초기값에 + 해주면 된다( 빼야할값은 음수라서 알아서 빠지고, 더해야 할값은 더해지고, 그대로 있어야 할값은 +0이 되어서 그대로)
        val dx = dv[d.v].first * i
        val dy = dv[d.v].second * i
        print("i : $i, dx : $dx, dy : $dy\n")
        print("[${dStart[d.v].first}][${dStart[d.v].second-dy}]\n")
        board[dStart[d.v].first + dx][dStart[d.v].second + dy] = v
    }
}

private fun processBlockList(list : ArrayList<Int>) : List<Int>{
    var i = 0
    while(i < list.size - 1){
        // 현재 block과 다음 block이 같다면
        // 0에 가까운 block이 먼저 추가된 block
        // up의 경우 n-1 to 0
        // down의 경우 0 to n-1
        // 먼저 처리되어야 할 block 먼저 추가해주는것
        if(list[i] == list[i+1]){ 
            list[i] *= 2 // merge
            maxBlock = Math.max(maxBlock, list[i]) // 합쳐지는것 + 처음 초기화시에 체크
            list.removeAt(i+1) // merge 작업이 된 block은 제거
        }
        i++
    }
    return list
}

/*
    push row col method
    
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
    
    push block method
            
        벽을 만난 경우... 흠
        x를 체크해야하는지, y를 체크해야하는지 어케알지
        x==wall || y==wall 로 해놓고, 
        대상이 아닌 축은 -1 이런거로 설정해놓으면 절대 같을 일이 없다
        

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
    for(i in 0 until n){
        pushRowCol2(i, d)
    }
}
// 하나의 열 또는 행의 블록을 모두 밀어버리는 메소드
private fun pushRowCol(rowCol : Int, d : Direction){
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
        if(board[start.first][start.second] != 0){ // block을 찾았다
            maxBlock = Math.max(maxBlock, board[start.first][start.second])
            pushBlock(start, d)
        }
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
    var block = b
    var nextSpace = Pair(block.first + dx[d.v], block.second + dy[d.v])
    while(true){
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

    maxBlock = Math.max(maxBlock, board[to.first][to.second])
}
private fun moveBlock(from : Pair<Int, Int>, to : Pair<Int, Int>){
    if(from == to){ 
        return
    }
    board[to.first][to.second] = board[from.first][from.second]
    combine[to.first][to.second] = false

    combine[from.first][from.second] = false
    board[from.first][from.second] = 0

    maxBlock = Math.max(maxBlock, board[to.first][to.second])
}

private var n = 0
private lateinit var originalBoard : Array<Array<Int>>
private lateinit var board : Array<Array<Int>>
private lateinit var combine : Array<Array<Boolean>>
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
private fun playGame(){
    Direction.values().forEach { d1 ->
        Direction.values().forEach { d2 ->
            Direction.values().forEach { d3 ->
                Direction.values().forEach { d4 ->
                    Direction.values().forEach { d5 ->
                        copyMap()
                        val dArr = arrayOf(d1, d2, d3, d4, d5)
                        for(i in 0 until 5){
                            pushBoard(dArr[i])
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

private fun printBoard(){
    print("=====print borad ====\n")
    for(x in 0 until n){
        for(y in 0 until n){
            print("${board[x][y]} ")
        }
        print("\n")
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