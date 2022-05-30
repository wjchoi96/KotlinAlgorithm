/*
    카카오 2019 인턴십 기출문제 1번
    level 1

    크레인 인형뽑기 게임

    n*n 의 board
    바구니 : stack? queue? list?

    board에는 다양한 인형과 빈칸이 존재

    크레인을 좌우로 움직여서 멈춘위치의 가장 위에있는 인형을 집어올려 바구니에 넣는다
    바구니의 가장 아래칸부터 쌓인다

    같은모양의 인형 두개가 바구니에 연속해서 쌓이게 되면 두 인형은 터지면서 바구니에서 사라지게 된다
    인형이 없는곳에서 크레인을 작동시키면 아무 일도 일어나지 않는다

    board, moves 가 주어질때 터트린 인형의 개수를 출력

    5<= n <=30
    
    0 : 빈칸
    1~100 : 인형

    1<= moves <= 1000
*/
/*
    catchDoll(col : Int) : Int
    : col열에서 인형을 집어 리턴

    pushBucket(doll : Int) 
    : 인형을 bucket 에 넣는다
    : 넣어서 터지는 경우도 여기서 구현
    - 인형이 터지고, index를 정리한 후에 연속으로 인형이 터질 수 있음
    => 해당 경우는 없겠는데?

    bucket 은 add, removeAt 메소드를 주로 사용할것
    LinkedList 가 좋을것 같다
*/
/*
    제출

    1. 시간초과
    - linkedList 가 get의 시간복잡도가 o(n) 이라 그런가?

    2. 성공
    - 1차원 배열이라서 doll 을 add할때만 bucket의 가장 위의 item과 비교해보기만 하면 된다
*/

fun main(args : Array<String>){
    val board = arrayOf(
        intArrayOf(0,0,0,0,0),
        intArrayOf(0,0,1,0,3),
        intArrayOf(0,2,5,0,1),
        intArrayOf(4,2,4,4,2),
        intArrayOf(3,5,1,3,1)
    )
    val moves = intArrayOf(
        1,5,3,5,1,2,1,4
    )
    Kakao2019001().solution(board, moves)
}

private class Kakao2019001 {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        val bucket : ArrayList<Int> = ArrayList()

        var count = 0
        moves.forEach {
            val doll = catchDoll(it-1, board)
            if(doll != 0){
                count += pushBucket(doll, bucket)
            }
        }
        print("$count\n")
        return count
    }
    private fun catchDoll(col : Int, board: Array<IntArray>) : Int{
        for(x in 0 until board.size){
            if(board[x][col] != 0){
                val doll = board[x][col]
                board[x][col] = 0
                return doll
            }
        }
        return 0
    }

    /*
    1 2 3 2 2
    */
    private fun pushBucket(doll : Int, bucket : ArrayList<Int>) : Int{
        return if(!bucket.isEmpty() && bucket.last() == doll){
            bucket.removeLast()
            2
        }else{
            bucket.add(doll)
            0
        }
        // bucket의 위부터 검사해야한다
        // var count = 0
        // var idx = bucket.size-1
        // while(idx-1>=0){
        //     if(bucket[idx] == bucket[idx-1]){
        //         bucket.remove(idx) 
        //         bucket.remove(idx-1)
        //         idx = bucket.size-1
        //         count += 2
        //     }else{
        //         idx--
        //     }
        // }
        // return count
    }
}