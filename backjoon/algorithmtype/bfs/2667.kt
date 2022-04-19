//sliver1
/*
    네이버 카페 문제50선 중 bfs 유형

    2차원 배열을 bfs로 순회하면서
    각 집들의 넓이를 구하라

    집이 몇개있는지 미리 알수있나?

    집의 넓이를 저장할 배열 => 집의 개수를 미리 알 수 없으니 n*n 으로 크기를 설정해놓자
    disit
    queue
*/

import java.io.*
import java.util.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val queue : Queue<Pair<Int, Int>> = LinkedList()
    val n = br.readLine().toInt()
    
    val houseArr : Array<Int> = Array(n*n){-1}
    val board : Array<Array<Int>> = Array(n){Array(n){-1}}
    val disit : Array<Array<Int>> = Array(n){Array(n){-1}}
    for(i in 0 until n){
        val row = br.readLine()
        board[i] = Array(n){j -> row[j].toString().toInt()}
    }

    for(i in 0 until n){
        for(j in 0 until n){
            print("${board[i][j]} ")
        }
        print("\n")
    }
    print("\n")
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)


    var houseCount = 0
    for(i in 0 until n){
        for(j in 0 until n){
            if(board[i][j] == 1 && disit[i][j] < 0){
                queue.offer(Pair(i, j))
                disit[i][j] = 0
                // print("[$i][$j]\n")
                var area = 0
                while(!queue.isEmpty()){
                    val node = queue.poll()
                    print("node[${disit[node.first][node.second]}] : [${node.first}, ${node.second}]\n")
                    area++
                    for(a in 0 until 4){
                        val nx = node.first + dx[a]
                        val ny = node.second + dy[a]

                        if(nx<0 || nx>=n || ny<0 || ny>=n){
                            continue
                        }
                        if(board[nx][ny] == 0 || disit[nx][ny] >= 0){
                            continue
                        }

                        queue.offer(Pair(nx, ny))
                        disit[nx][ny] = disit[node.first][node.second] + 1
                    }
                }
                houseArr[houseCount++] = area
                print("houseCount : $houseCount, area : $area\n")
            }
        }
    }
    houseArr.sort()
    houseArr.reverse()
    bw.write("$houseCount\n")
    for(i in houseCount-1 downTo 0){
        bw.write("${houseArr[i]}\n")
    }
    

    bw.flush()
    bw.close()
    br.close()

}
 