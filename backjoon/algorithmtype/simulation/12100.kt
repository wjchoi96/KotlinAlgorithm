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
private val dx = arrayOf(1, -1, 0, 0)
private val dy = arrayOf(0, 0, 1, -1)
private enum class Direction(val v : Int){
    Up(0),
    Down(1),
    Right(2),
    Left(2),
}
// 하나의 열 또는 행의 블록을 모두 밀어버리는 메소드
private fun pushRowCol(d : Direction){
    /*
        up, down 이면 x축을 순회하며 block을 찾아야하고
        right, left 이면 y축을 순회하며 block을 찾아야한다

        up 이면 n-1 부터 0 으로
        down 이면 0 부터 n-1

        for문 은 역순이면 downTo를 써야하기때문에 그걸 보완할 수 있나 찾아보던지
        while + idx 를 써서 진행해야할것 같다
    */
    
}

//block을 해당 방향으로 밀어버리는 메소드
private fun pushBlock(b : Pair<Int, Int>, d : Direction){
    val dWall = arrayOf(
        Pair(n-1, -1), 
        Pair(0, -1), 
        Pair(-1, n-1), 
        Pair(-1, 0)
    )
    val wall = dWall[d.v]
    
    // block 을 찾아서?
    var block = b
    while(true){
        /*
            벽을 만난 경우... 흠
            x를 체크해야하는지, y를 체크해야하는지 어케알지
            x==wall || y==wall 로 해놓고, 
            대상이 아닌 축은 -1 이런거로 설정해놓으면 절대 같을 일이 없다
        */
        if(block.first == wall.first || block.second == wall.second){
            moveBlock(from=b, to=block)
            break
        }
        // 다른 block을 만난 경우
        if(board[block.first][block.second] != 0){
            if(
                combine[block.first][block.second] == false &&
                board[block.first][block.second] == board[b.first][b.second]
            ) {
                mergeBlock(from=b, to=block)
            }else{
                moveBlock(from=b, to=block)
            }
            break
        }
        
        // way에 따라 다음 칸으로 이동
        block = Pair(block.first + dx[d.v], block.second + dy[d.v])
    }
}
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
private fun mergeBlock(from : Pair<Int, Int>, to : Pair<Int, Int>){
    board[to.first][to.second] += board[from.first][from.second]
    combine[to.first][to.second] = true

    combine[from.first][from.second] = false
    board[from.first][from.second] = 0
}
private fun moveBlock(from : Pair<Int, Int>, to : Pair<Int, Int>){
    board[to.first][to.second] = board[from.first][from.second]
    combine[to.first][to.second] = false

    combine[from.first][from.second] = false
    board[from.first][from.second] = 0
}

private var n = 0
private lateinit var board : Array<Array<Int>>
private lateinit var combine : Array<Array<Boolean>>
fun main(args : Array<String>){
    val bw = System.out.bufferedWriter()
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    bw.write("check")

    bw.flush()
    bw.close()
    br.close()
}