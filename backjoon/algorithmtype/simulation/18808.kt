//gold3
/*
    바킹독님 알고리즘 강의 중 시뮬레이션 단원 연습문제
    바킹독님 출제 문제

    스티커 붙이기


    스티커
    1. 스티커는 상하좌우 모두 연결되어있다
    2. 상하좌우에 스티커가 포함되지 않는 불필요한 행, 열이 없다

    노트북에 스티커 붙이기
    1. 스티커를 회전시키지 않고 붙일 준비를 한다
    2. 다른 스티커와 겹치지않고, 노트북을 벗어나지 않으면서 붙일수 있는 위치를 탐색
    - 위쪽에 우선순위를 가진다
    - 위쪽에서는 왼쪽에 우선순위를 가진다
    3. 스티커를 붙일 수 있다면 붙인다
    4. 붙일 수 없다면 스티커를 시계방향으로 90도 회전하고, 2번 탐색 과정으로 돌아간다
    5. 위의 과정을 4번 반복해서 스티커를 0도, 90도, 180도, 270도 회전시켜봐도 스티커를 붙이지 못했다면
    해당 스티커를 버린다

    차례대로 스티커를 다 붙인 후 노트북에서 몇칸이 스티커로 채워졌는지 출력
*/

/*
    1. 스티커 회전 메소드
    2. 스티커를 붙일 수 있는지 탐색, 붙일 위치 선정
    3. 스티커를 붙인다
*/

/*
    스티커를 붙일 수 있나 없나 어떻게 체크할까

    board를 순회하면서 스티커의 범위를 탐색
    1. 스티커를 붙일 시작점을 탐색
    - 스티커가 붙어있지 않은 공간이여야 하며
    - 해당 위치에서 시작했을때 스티커의 범위가 들어갈 수 있어야 한다
    - 시작점에서 스티커의 크기만큼 board를 순회하며 스티커가 붙을 수 있나 확인
*/

/*
    제출
    1. 성공
*/
import java.io.*
import java.util.StringTokenizer
private var n = 0
private var m = 0
private lateinit var board : Array<Array<Int>> 
private lateinit var stickers : Array<Sticker>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    val s = st.nextToken().toInt()

    board = Array(n){Array(m){0}}
    stickers = Array(s) { 
        val (sn, sm) = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        val area = Array(sn){Array(sm){0}}
        for(x in 0 until sn){
            st = StringTokenizer(br.readLine())
            for(y in 0 until sm){
                area[x][y] = st.nextToken().toInt()
            }
        }
        Sticker(area)
    }

    for(i in 0 until stickers.size){
        checkStickers(i)
    }
    print("\nfinish===\n")
    printBoard()

    var count = 0
    for(x in 0 until n){
        for(y in 0 until m){
            if(board[x][y] != 0){
                count++
            }
        }
    }
    bw.write("$count\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun checkStickers(depth : Int){
    if(depth == stickers.size){
        return
    }
    val sticker = stickers[depth]
    print("sticker[$depth]\n")
    sticker.printSticker()
    
    //n-sticker.n 의 남은 칸수 + 1번 만큼 loop이 돌아야하는데
    //5-3 = 2 => 0 until 2 => 0,1 => 하지만 0,1,2 까지 해줘야한다
    //5-4 = 1 => 0 until 1 -> 0 => 하지만 0,1 까지 해줘야한다
    //5-5 = 0 => 0 until 0 -> null => 하지만 0 까지  체크해야한다
    //n+1-sticker.n
    for(x in 0 until n+1-sticker.n){  
        for(y in 0 until m+1-sticker.m){
            if(canAttackSticker(sticker, Pair(x,y))){
                attackSticker(sticker, Pair(x,y))
                print("attach[$depth]----\n")
                printBoard()
                return
            }
        }
    }
     // 현재 스티커를 못 붙여서 회전시킨 경우 재귀로 재호출한다
    if(sticker.rotate()){
        checkStickers(depth)
    }    
}
private fun canAttackSticker(sticker : Sticker, from : Pair<Int,Int>) : Boolean{
    if(from.first + sticker.n > n || from.second + sticker.m > m){
        return false
    }
    for(x in 0 until sticker.n){
        for(y in 0 until sticker.m){
            // 스티커를 붙일 위치가 board 에서 비어있지 않다면
            if(sticker.area[x][y] != 0 && board[x+from.first][y+from.second] != 0){
                return false
            }
        }
    }

    return true
}

private fun attackSticker(sticker : Sticker, from : Pair<Int,Int>){
    for(x in 0 until sticker.n){
        for(y in 0 until sticker.m){
            // 스티커를 붙일 위치가 board 에서 비어있다면
            if(sticker.area[x][y] != 0){
                // 혹시나 중첩되어 붙여지는 공간이 있다면 디버깅하게 ++로 처리
                board[x+from.first][y+from.second]++ // 스티커를 붙인다
            }
        }
    }

}
data class Sticker( 
    var area : Array<Array<Int>>,
    private var rotateCount : Int = 0
){
    val n : Int
        get() = area.size
    val m : Int
        get() {
            return if(area.isEmpty()){
                0
            }else{
                area.first().size
            }
        }
    fun printSticker(){
        print("sticker===\n")
        for(x in 0 until n){
            for(y in 0 until m){
                print("${area[x][y]} ")
            }
            print("\n")
        }
    }
    fun rotate() : Boolean {
        if(rotateCount > 4){
            return false
        }
        rotateCount++
        // print("before rotate[$rotateCount], size[$n, $m]\n")
        // printSticker()
        val newArea = Array(this.m){Array(this.n){0}} // 어느 방향이건 90도 회전
        for(x in 0 until n){
            for(y in 0 until m){
                val (nx, ny) = arrayOf(y, n-x-1) //[by, am-bx-1] [beforeY, afterM-beforeX-1]
                // print("[$x, $y] to [$nx, $ny]\n")
                newArea[nx][ny] = area[x][y]
            }
        }
        area = newArea
        // print("after rotate[$rotateCount], size[$n, $m]\n")
        // printSticker()
        return true
    }
    private fun getIdx(idx : Int, y : Int) : Pair<Int, Int> {
        return Pair(idx/y, idx%y)
    }
}
/*
process rotate stiker idx

1 1 1 1 1
0 0 0 1 0

to

0 1
0 1
0 1
1 1
0 1

n = 2, m = 5
[0,0] -> [0,1]
[0,1] -> [1,1]
[0,2] -> [2,1]
[0,3] -> [3,1]
[0,4] -> [4,1]

n = 5, m = 2
[1,0] -> [0,0]
[1,1] -> [1,0]
[1,2] -> [2,0]
[1,3] -> [3,0]
[1,4] -> [4,0]

가장 위쪽행(x==0)이 가장 오른쪽열(y==m-1)로
가장 아래쪽행(x==n-1)이 가장 왼쪽열(y==0)

[0, 0] -> [by, am-bx-1]
[0, 1] -> [by, am-bx-1] 
[0, 2] -> [by, am-bx-1]
[0, 3] -> [by, am-bx-1]
[0, 4] -> [by, am-bx-1] 

[1, 0] -> [by, am-bx-1]
[1, 1] -> [by, am-bx-1]
[1, 2] -> [by, am-bx-1]
[1, 3] -> [by, am-bx-1]
[1, 4] -> [by, am-bx-1]

*/


private fun printBoard(){
    for(x in 0 until n){
        for(y in 0 until m){
            print("${board[x][y]} ")
        }
        print("\n")
    }
}