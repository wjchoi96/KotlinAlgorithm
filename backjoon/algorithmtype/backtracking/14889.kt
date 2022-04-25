// sliver2
/*
    스타크와 링크

    N명의 사람, 두개의 팀
    N/2 명씩 나눠지는 팀원들

    1. 중복되는 사람을 뽑아선 안된다
    2. 같은조합, 다른 순서의 조합을 뽑아서도 안된다

    N개의 수열에서 위의 조건을 만족하는 N/2개의 수열을 뽑으면, 뽑히지 않은 사람들은 나머지 팀이다

    3. 1,2를 만족하는 조합을 뽑아도, 뽑히지 않은 사람들까지 고려하면 겹치는 조건이 있네

    4명
    1,2 
    1,3
    1,4
    2,3

    1,2 / 3,4
    1,3 / 2,4
    1,4 / 2,3
    2,3 / 1,4

*/

/*
    제출 
    1. 시간초과
    - 오름차순으로 수열을 뽑는 코드가 누락되어있었다 => 적용할라했는데 누락이 되어있었네

    2. 시간초과
    - 0이 최소값이니 0이뜨면 바로 종료 코드 추가

    3. 시간초과(40%?)
    - 오름차순 코드가 잘못되어있었다 -> i + 1을 다음 dfs에 넘겨야하는데 idx+1 을 넘김

    4. 성공
    - 꾸역꾸역 아슬하게 성공하네
    - 시간복잡도가 진짜 아슬한 문제인건가

    5. 성공
    - 숏코딩 구경도중, 별 차이가 없는 코드인데 시간초과가 안떠서 시도
    - 왜지?

    6. 성공?
    - dfs2 + checkScore 조합

    7. 원래 dfs를 main 밖에서 돌려본다
    - 왜성공하지

    8. 성공
    - 시간초과 떴던 코드를 분석해보니 3번 시간초과 이후 개선점이 문제였었다

    결론 : 오름차순만 잘 시켰으면 되는 문제였다
*/

import java.io.*
private lateinit var bw : BufferedWriter
private lateinit var board : Array<Array<Int>>
private lateinit var visit : Array<Boolean>
private var n = 0
private var min : Int = Int.MAX_VALUE
fun main(args : Array<String>){
    bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    n = br.readLine().toInt()
    board = Array(n){Array(n){-1}}
    visit = Array(n){false}
    for(i in 0 until n){
        board[i] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    }
    br.close()


    dfs()
    bw.write("$min\n")

    bw.flush()
    bw.close()
}

/*
    0,1
    0,2
    0,3

    1,2
    1,3

    3,4

    for문 2중첩으로 하는 이유는, 두명을 한번에 구해서 계산하기 위함인가?
*/
private fun checkScore(){
    var stark = 0
    var link = 0
    for(i in 0 until n-1){
        for(j in i+1 until n){
            if(visit[i]==true && visit[j]==true){
                stark += board[i][j]
                stark += board[j][i]
            }else if(visit[i]==false && visit[j]==false){
                link += board[i][j]
                link += board[j][i]
            }
        }
    }
    min = Math.min(min, Math.abs(stark - link))
    // 절대값 계산이므로 0이 무조건 최소값
    // if(min == 0){
    //     bw.write("$min\n")
    //     bw.flush()
    //     bw.close()
    //     System.exit(0)
    // }
}

private fun dfs(depth : Int = 0, idx : Int = 0){
    if(depth == n/2){
        checkScore() // 뽑힌팀은 visit 가 true, 안뽑힌팀은 visit 가 false 가 되어있을것
        return
    }
    for(i in idx until n){
        if(visit[i] == false){
            visit[i] = true
            dfs(depth + 1, i + 1)
            visit[i] = false
        }
    }
}
private fun dfs2(idx : Int = 0, depth : Int = 0){
    if(depth == n/2){
        // var stark = 0 
        // var link = 0
        // for(i in 0 until n-1){
        //     for(j in i+1 until n){
        //         if(visit[i] == true && visit[j] == true){
        //             stark += board[i][j] + board[j][i]
        //         }else if(visit[i] == false && visit[j] == false){
        //             link += board[i][j] + board[j][i]
        //         }
        //     }
        // }
        // min = Math.min(min, Math.abs(stark - link))
        checkScore()
        return
    }
    for(i in idx until n){
        if(visit[i] == false){
            visit[i] = true
            dfs2(i + 1, depth + 1)
            visit[i] = false
        }
    }
}