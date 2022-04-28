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

private var n = 0
private var m = 0
private lateinit var board : Array<Array<Int>> 
private lateinit var stickers : Array<Sticker>
data class Sticker( 
    val n : Int,
    val m : Int,
    val area : Array<Array<Int>>,
    private val rotateCount : Int = 0
){
    fun rotate() : Boolean {
        if(rotateCount > 4){
            return false
        }
        // do rotate
        return true
    }
}
enum class Rotate {

}

private fun checkStickers(depth : Int = 0){
    if(depth == stickers.size){
        return
    }
    val sticker = stickers[depth]
    var isAttach = false
    for(x in 0 until n){
        for(y in 0 until m){
            if(board[x][y] != 0){
                continue
            }
            if(canAttackSticker(sticker, Pair(x,y))){
                attackSticker(sticker, Pair(x,y))
                isAttach = true
            }
        }
    }
    if(!isAttach && sticker.rotate()){
        checkStickers(depth) // 현재 스티커를 못 붙여서 돌린 경우 
    }else { // 현재스티커를 붙여서든, 결국 못붙여서 버렸든 다음 depth 로 넘어간다
        checkStickers(depth + 1)
    }
    
}

/*
        0 0 0 0
        0 0 0 0
        0 0 0 0

        n = 3
        m = 4

        0 -> 0,0
        3 -> 0,3
        4 -> 1,0
        5 -> 1,1
        8 -> 2,0
    */
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

private fun rotateSticker(idx : Int){
    // rotate가 까다롭네
    // 이거야 말로 1차원 배열로 처리해서 쭈루루룩 옮겨야 하지 않을까
    val original = stickers[idx]
    val newArea = Array(original.m){Array(original.n){0}} // 어느 방향이건 90도 회전
    val size = original.n * original.m
    var (x, y) = arrayOf(0,0)
    for(i in 0 until size){
        (x, y) = arrayOf(i/m, i%m)
        
    }
}
// 2차원배열을 1차원배열로 펼쳤을때의 idx를 넣으면, 2차원배열의 idx로 리턴
// y가 우선 증가되도록 펼친다 => (0,0) (0,1) (1,0) (1,1) => 0..4
private fun getIdx(idx : Int, n : Int, m : Int) : Pair<Int, Int> {
    return Pair(0, 0)
}