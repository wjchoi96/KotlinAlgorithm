//bronze1
/*
    네이버 카페 문제50선 중 백트래킹, 완전탐색 유형
    브루트포스 유형 : 모든 경우의 수를 탐색

    N장의 카드 중 3장의 카드를 고른다
    M을 넘지 않으며, 최대한 M과 가까워야한다

    N장의 카드와 M을 입력

    3 <= N <= 100
    10 <= M <= 300,000

*/
/*
    그냥 dfs 돌리면 될것같은데

    이전엔 3중 for문을 돌려서 풀었다

    최적화
    1. 카드가 중복되어선 안된다
    = 해결되어있다
    2. 카드의 조합이 같을 필요없다(같은 조합, 다른 순서)
    = 오름차순 적용시키자 => dfs2 생성
*/
/*
    제출 
    1. 성공

    최적화
    2. 시간초과
    - 선택하지 않은 가지들까지 모두 처리해서 그런가?

    3. 성공
    - 오름차순만 적용
*/
import java.io.*
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    
    val (n, m) = br.readLine().split(' ').map{it.toInt()}
    val card = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    card.sort()
    val select = Array(3){0}
    val visit = Array(n){false}

    var max = Int.MIN_VALUE
    fun dfs(depth : Int = 0, sum : Int = 0){
        if(sum > m){
            return
        }
        if(depth == select.size){
            max = Math.max(max, sum)
            return 
        }
        for(i in 0 until n){
            if(visit[i] == false){
                select[depth] = card[i]
                visit[i] = true
                dfs(depth + 1, sum + select[depth])
                visit[i] = false
            }
        }
    }
    fun dfs2(depth : Int = 0, idx : Int = 0, sum : Int = 0){
        if(sum > m){
            return
        }
        if(depth == 3){
            max = Math.max(max, sum)
            return 
        }
            
        for(i in idx until n){
            if(visit[i] == false){
                visit[i] = true
                dfs2(depth + 1, i + 1, sum + card[i])
                visit[i] = false
            }
        }
    }

    dfs2()
    bw.write("$max\n")

    bw.flush()
    bw.close()
    br.close()
}