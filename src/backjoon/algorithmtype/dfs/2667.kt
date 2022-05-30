package src.backjoon.algorithmtype.dfs
//sliver1
/*
    네이버 카페 문제50선중 dfs 유형

    각 단지에 속하는 집의 수를 오름차순으로 구한다
    dfs 를 사용해서 진행해보자

    섬 다리 연결처럼 진행해보자

    각 단지를 구분하기 위해 단지별로 같은 값으로 board의 값을 갱신해준다

    dfs 를 진행하고 board를 순회하며 각 단지의 집의 개수를 구한다
    => 한번에 진행하려면 그냥 단지 ArrayList 를 사용해서 단지 순회할때 집의 숫자를 세서 넣어준다음 sort 하면 된다
    => 해당 방법은 해봤으니, 단지의 개수를 구하고 해당 단지의 수만큼만 배열을 생성하고 집의 개수를 넣어주는 방식으로 풀어보자

    제출
    1. 맞았습니다
*/

import java.util.Stack
import java.io.*
private val dx = arrayOf(1, 0, -1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private var stack = Stack<Pair<Int, Int>>()
private lateinit var board : Array<Array<Int>>
private lateinit var visit : Array<Array<Boolean>>
private var houseValue = 2 // 나중에 단지의 개수를 구할때는 -2 해주면 된다
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    board = Array(n){Array(n){-1}}
    visit = Array(n){Array(n){false}}
    for(i in 0 until n){
        val row = br.readLine()
        board[i] = Array(n) { k -> row[k].toString().toInt() }
    }
    for(x in 0 until n){
        for(y in 0 until n){
            if(visit[x][y] == false && board[x][y] != 0){
                dfs(Pair(x, y), n)
            }
        }
    }
    if(houseValue-2 <= 0){
        bw.write("${houseValue-2}\n")
    }else{
        val groupArr = Array<Int>(houseValue-2){0}
        for(x in 0 until n){
            for(y in 0 until n){
                if(board[x][y] >= 2){
                    groupArr[board[x][y] - 2]++
                }
            }
        }
        groupArr.sort()
        bw.write("${houseValue-2}\n")
        for(i in groupArr){
            bw.write("$i\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(start : Pair<Int, Int>, n : Int){
    stack.push(start)
    visit[start.first][start.second] = true
    board[start.first][start.second] = houseValue

    while(!stack.isEmpty()){
        val node = stack.pop()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            if(nx<0 || nx>=n || ny<0 || ny>=n){
                continue
            }
            if(board[nx][ny] != 1 || visit[nx][ny] == true){
                continue
            }

            stack.push(Pair(nx, ny))
            visit[nx][ny] = true
            board[nx][ny] = houseValue
        }
    }
    houseValue++
}