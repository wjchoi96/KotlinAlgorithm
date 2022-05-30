/*
    카카오 2022 인턴 코테 5번

    
    배열 돌리기
    ShiftRow
    : 모든 행이 한칸씩 아래로 밀려난다

    Rotate
    : 행렬의 바깥쪽에 있는 원소들이 시계방향으로 한칸 회전

*/
/*
    제출

    1. 
    - 정확성 100%
    - 효율성 1개 뺴고 박살
    - 애초에 정확성만 노리고 짜긴함
*/
fun main(args : Array<String>){
    // val board = arrayOf(
    //     intArrayOf(1, 2, 3),
    //     intArrayOf(4, 5, 6),
    //     intArrayOf(7, 8, 9)
    // )
    var board = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12)
    )
    val operations = arrayOf(
        // "Rotate", "ShiftRow"
        // "ShiftRow", "Rotate", "ShiftRow", "Rotate"
        "Rotate", "Rotate", "Rotate"
    )
    Kakao2022005().solution(board, operations)
}

private class Kakao2022005 {
    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
        printBoard(rc)
        println("\n")
        for(op in operations){
            when(op) {
                "Rotate" -> rotate(rc)
                "ShiftRow" -> shiftRow(rc)
            }
            printBoard(rc)
        }
        return rc
    }

    private fun shiftRow(board : Array<IntArray>){
        println("shiftRow")
        val lastRow = board[board.size-1].copyOf()
        for(x in board.size-1 downTo 1){
            board[x] = board[x-1].copyOf()
        }
        board[0] = lastRow
    }

    /*
        [0,0]을 비운다
        [0,0]에 들어와야할 값을 넣는다 => [1,0]
        [1,0]에 들어와야할 값을 넣는다 => [2,0]
        ...
        [n-2, 0]

        [n-1, 0]에 들어가야할 값을 넣는다 => [n-1, 1]
        [n-1, 1]에 들어가야할 값을 넣는다 => [n-1, 2]
        ...
        [n-1, n-2]

        [n-1, n-1]에 들어가야 할 값 => [n-2, n-1]
        [n-2, n-1]에 들어가야 할 값 => [n-3, n-1]
        ...
        [1, n-1]

        
 
    */
    private fun rotate(board : Array<IntArray>){
        println("rotate")
        val start = board[0][0]
        for(x in 0 until board.size-1){ //[0][0] - [n-2][0]
            board[x][0] = board[x+1][0]
        }
        for(y in 0 until board[0].size-1){ //[n-1,0] - [n-1][n-2]
            board[board.size-1][y] = board[board.size-1][y+1]
        }
        for(x in board.size-1 downTo 1){ //[n-1][n-1] - [1][n-1]
            board[x][board[0].size-1] = board[x-1][board[0].size-1]
        }
        for(y in board[0].size-1 downTo 1){ //[0][n-1] - [0][2]
            board[0][y] = board[0][y-1]
        }
        board[0][1] = start
    }

    private fun printBoard(board : Array<IntArray>){
        println("printBoard")
        for(x in 0 until board.size){
            for(y in 0 until board[x].size){
                print("${board[x][y]} ")
            }
            print("\n")
        }
    }
}