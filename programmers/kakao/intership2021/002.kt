/*
    2021 카카오 인턴 기출문제 2번

    거리두기 확인하기
    level 2

    대기실 5개
    5*5

    맨해튼거리 : |r1-r2| + |c1-c1| (좌표간 거리구하기, 치킨거리 문제랑 비슷)
    => bfs순회시 disit 랑 같다

    응시자들끼리는 맨해튼거리 2이하로 앉지 말아주세요 => 3이상만이 허용
    던 응시자 간에 파티션으로 막혀있다면 허용 
    => 거리가 1일때는 파티션으로 막힐 수 없으니, 해당 경우는 거리가 2인 경우만 해당

    응시자 : p
    빈 테이블 : 0
    파티션 : x

    5개의 대기실 정보가 입력된다
    각 대기실별로 거리두기를 지키고있다면 1
    한명이라도 지키지 않고 있다면 0

    응시자 위치에서 시작하는 bfs
    파티션 => 못지나간다
    테이블 => 방문처리
    응시자 => disit 2이내라면 거리두기를 지키지 않은것
    (1,0)(3,0)

    응시자별로 bfs를 시작할때마다 disit 를 초기화해주어야 한다
*/
/*
    places : 2차원배열
    각 행은 하나의 대기실 구조
    => 대기실구조가 1차원 배열로 담겨서 온다

    disit 초기값을 0으로하고 start 를 disit 1로 시작해서 틀렸던것
    거리가 +1 된 효과였다


    개선
    => 거리가 2 이상은 탐색할 필요가 없다
*/

import java.util.Queue
import java.util.LinkedList
fun main(args : Array<String>){
    val places = arrayOf(
        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        // arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
        arrayOf("OPOOP", "PXXOX", "OPXPX", "OOXOX", "POXXP")
    )
    
    /*
    ["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"] to 2차원 배열
    */
    val res = Kakao002().solution(places)
    for(i in 0 until 5){
        print("${res[i]} ")
    }
    print("\n")

}
class Kakao002 {
    private lateinit var board : Array<Array<Char>>
    private lateinit var disit : Array<Array<Int>>
    private val n = 5
    private val person = 'P'
    private val wall = 'X'
    private val table = 'O'

    fun solution(places: Array<Array<String>>): IntArray {
        val resArr = Array(n){-1}
        for(it in 0 until n){
            board = Array(n){Array(n){' '}}
            for(x in 0 until n){
                val row = places[it][x]
                for(y in 0 until n){
                    board[x][y] = row[y]
                }
            }
            resArr[it] = checkBoard(board) 
        }
        return resArr.toIntArray()
    }

    private fun checkBoard(board : Array<Array<Char>>) : Int{
        for(x in 0 until n){
            for(y in 0 until n){
                if(board[x][y] == person){
                    if(!bfs(x to y, board)){
                        return 0
                    }
                }
            }
        }
        return 1
    }

    /*
        순회하며 disit를 통해 안전거리를 체크하는게 아닌
        방문할 수 있는 사람을 만났는데, disit를 적용해보니 2이상이면 return false
    */
    private fun bfs(start : Pair<Int, Int>, board : Array<Array<Char>>) : Boolean {
        disit = Array(n){Array(n){-1}}
        val dx = arrayOf(1, 0, -1, 0)
        val dy = arrayOf(0, 1, 0, -1)
        val queue : Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(start)
        disit[start.first][start.second] = 0

        while(!queue.isEmpty()){
            val node = queue.poll()
            for(i in 0 until 4){
                val nx = node.first + dx[i]
                val ny = node.second + dy[i]
                if(nx<0 || nx>=n || ny<0 || ny>=n){
                    continue
                }
                if(board[nx][ny] == wall || disit[nx][ny] != -1){
                    continue
                }
                // 현재 칸이 사람이고, 거리가 안전거리 이하라면
                if(board[nx][ny] == person && disit[node.first][node.second] + 1 <= 2){
                    return false
                }else if( disit[node.first][node.second] + 1 > 2){ // 거리가 2를 넘으면 안전거리이므로 방문할 필요 없다
                    continue
                }
                queue.offer(nx to ny)
                disit[nx][ny] = disit[node.first][node.second] + 1
            }
        } 
        return true
    }
}
/*
// print("person[${node.first}][${node.second}]: disit=${disit[node.first][node.second]} b=${board[node.first][node.second]}\n")
*/

// solve 1
// 정확성 76.3
// disit 초기값을 0으로하고 start 를 disit 1로 시작해서 틀렸던것
// 거리가 +1 된 효과였다
// 수정
class Kakao002Solve1 {
    private lateinit var board : Array<Array<Char>>
    private lateinit var disit : Array<Array<Int>>
    private val n = 5
    private val person = 'P'
    private val wall = 'X'
    private val table = 'O'

    fun solution(places: Array<Array<String>>): IntArray {
        val resArr = Array(n){-1}
        for(it in 0 until n){
            board = Array(n){Array(n){' '}}
            for(x in 0 until n){
                val row = places[it][x]
                for(y in 0 until n){
                    board[x][y] = row[y]
                }
            }
            resArr[it] = checkBoard(board) 
        }
        return resArr.toIntArray()
    }

    private fun checkBoard(board : Array<Array<Char>>) : Int{
        for(x in 0 until n){
            for(y in 0 until n){
                if(board[x][y] == person){
                    if(!bfs(x to y, board)){
                        return 0
                    }
                }
            }
        }
        return 1
    }

    /*
        순회하며 disit를 통해 안전거리를 체크하는게 아닌
        방문할 수 있는 사람을 만났는데, disit를 적용해보니 2이상이면 return false
    */
    private fun bfs(start : Pair<Int, Int>, board : Array<Array<Char>>) : Boolean {
        disit = Array(n){Array(n){-1}}
        val dx = arrayOf(1, 0, -1, 0)
        val dy = arrayOf(0, 1, 0, -1)
        val queue : Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(start)
        disit[start.first][start.second] = 0

        while(!queue.isEmpty()){
            val node = queue.poll()
            for(i in 0 until 4){
                val nx = node.first + dx[i]
                val ny = node.second + dy[i]
                if(nx<0 || nx>=n || ny<0 || ny>=n){
                    continue
                }
                if(board[nx][ny] == wall || disit[nx][ny] != -1){
                    continue
                }
                queue.offer(nx to ny)
                disit[nx][ny] = disit[node.first][node.second] + 1
                //disit 값을 적용시켜보고 나서 방문할곳이 person && 안전거리 이내라면 return
                if(board[nx][ny] == person && disit[nx][ny] <= 2){
                    return false
                }
            }
        } 
        return true
    }
}

/*
통과 로그
solve1

테스트 1 〉	통과 (14.83ms, 65.4MB)
테스트 2 〉	통과 (14.11ms, 65.5MB)
테스트 3 〉	통과 (14.80ms, 65MB)
테스트 4 〉	통과 (14.84ms, 65.9MB)
테스트 5 〉	통과 (16.96ms, 65.1MB)
테스트 6 〉	통과 (21.05ms, 64.6MB)
테스트 7 〉	통과 (16.40ms, 65.7MB)
테스트 8 〉	통과 (15.33ms, 64.2MB)
테스트 9 〉	통과 (23.36ms, 64.7MB)
테스트 10 〉	통과 (25.37ms, 64.9MB)
테스트 11 〉	통과 (17.96ms, 65.2MB)
테스트 12 〉	통과 (17.45ms, 65.3MB)
테스트 13 〉	통과 (18.73ms, 64.8MB)
테스트 14 〉	통과 (17.01ms, 65.4MB)
테스트 15 〉	통과 (17.77ms, 65.2MB)
테스트 16 〉	통과 (15.42ms, 64.1MB)
테스트 17 〉	통과 (14.96ms, 64MB)
테스트 18 〉	통과 (21.75ms, 65.6MB)
테스트 19 〉	통과 (21.01ms, 65.2MB)
테스트 20 〉	통과 (20.45ms, 65.2MB)
테스트 21 〉	통과 (18.62ms, 65.2MB)
테스트 22 〉	통과 (17.53ms, 66.9MB)
테스트 23 〉	통과 (14.77ms, 66.2MB)
테스트 24 〉	통과 (14.77ms, 65.1MB)
테스트 25 〉	통과 (20.76ms, 64MB)
테스트 26 〉	통과 (15.22ms, 65.8MB)
테스트 27 〉	통과 (22.59ms, 65.4MB)
테스트 28 〉	통과 (21.90ms, 64.5MB)
테스트 29 〉	통과 (14.61ms, 64.7MB)
테스트 30 〉	통과 (16.63ms, 66.4MB)
테스트 31 〉	통과 (14.33ms, 65.5MB)

solve2 => 개선  => 크게 효과가 없는듯?
테스트 1 〉	통과 (14.57ms, 65.7MB)
테스트 2 〉	통과 (14.48ms, 64.9MB)
테스트 3 〉	통과 (14.17ms, 65.2MB)
테스트 4 〉	통과 (14.38ms, 65.4MB)
테스트 5 〉	통과 (15.08ms, 65.1MB)
테스트 6 〉	통과 (17.06ms, 65.2MB)
테스트 7 〉	통과 (19.69ms, 65.6MB)
테스트 8 〉	통과 (18.53ms, 65.2MB)
테스트 9 〉	통과 (14.89ms, 65.8MB)
테스트 10 〉	통과 (14.19ms, 64.7MB)
테스트 11 〉	통과 (14.54ms, 65.6MB)
테스트 12 〉	통과 (14.66ms, 65.1MB)
테스트 13 〉	통과 (14.61ms, 67.3MB)
테스트 14 〉	통과 (21.43ms, 65.5MB)
테스트 15 〉	통과 (15.46ms, 66.1MB)
테스트 16 〉	통과 (14.68ms, 65.6MB)
테스트 17 〉	통과 (15.43ms, 65MB)
테스트 18 〉	통과 (14.62ms, 66.4MB)
테스트 19 〉	통과 (14.73ms, 65.5MB)
테스트 20 〉	통과 (14.95ms, 65.3MB)
테스트 21 〉	통과 (20.77ms, 65MB)
테스트 22 〉	통과 (17.90ms, 65.1MB)
테스트 23 〉	통과 (22.75ms, 64.9MB)
테스트 24 〉	통과 (14.34ms, 65.5MB)
테스트 25 〉	통과 (14.50ms, 64.9MB)
테스트 26 〉	통과 (13.74ms, 64.6MB)
테스트 27 〉	통과 (14.32ms, 65.3MB)
테스트 28 〉	통과 (14.26ms, 65.4MB)
테스트 29 〉	통과 (14.30ms, 65.9MB)
테스트 30 〉	통과 (14.16ms, 65.3MB)
테스트 31 〉	통과 (14.26ms, 65.6MB)
*/