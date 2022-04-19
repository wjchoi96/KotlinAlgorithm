//sliver1

/*
    바캉독님 bfs강의 도중 제시된 문제

    수빈 : 0 <= N <= 100,000
    동생 : 0 <= K <= 100,000

    수빈이는 걷거나 순간이동이 가능
    걷기     : 1초후에 x-1 or x+1 로 이동
    순간이동 : 1초후에 2*X 로 이동 

    이게 bfs라고?
    bfs로 풀수는 있을것..같나??

    동전 잔돈 거슬러주기랑 같은 유형인거같은데..
    그거 뭐였지 => 그리디

    2차원에서의 탐색이 아닌, 1차원에서의 탐색
    dx를 (1, -1, 2*n) 이런식으로 해서 돌리나?

    이미 방문했던 노드라도, 현재 탐색루트보다 거리가 길다면 덮어씌우면 되겠는데

    제출
    1. 런타임에러(4%)
    => ArrayIndexOutOfBounds
    => 누나의 위치가 더 큰 경우를 대비하지못했다 
    => disit 의 크기를 누나와 동생중 더 큰 값으로 생성
    => 범위를 벗어났는지 체크하는 코드또한 max 값으로 변경

    2. 틀렸습니다(34%)
    => 개선점 : 동생의 위치를 넘었다가 -1 해서 돌아오는 경우의 수도 포함시켜주자
    => val max = Math.max(n, k) + 2 // +2를 해준이유는 *2의 조건에서는 node[max+1] -> node[max] 로 도착하는 경우도 있기 때문

    3. 성공
    
 */
/*
val dx = arrayOf(x-1, x+1, x*2) // 이거 순서도 영향이 있을수 있다네
(n*2, x-1, x+1) 순서일경우 n이 0으로 시작하면 0*2 = 0 이므로
disit[0] = disit[prev] + 1 가된다 => 0에서시작했으면 disit[0] = 0 이여야하는데, 시작점에 영향이 간다는 의미
*/
import java.io.*
import java.util.*
private lateinit var disit : Array<Int>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt() // subin
    val k = st.nextToken().toInt() // bro

    val max = Math.max(n, k) + 2 // +2를 해준이유는 *2의 조건에서는 node[max+1] -> node[max] 로 도착하는 경우도 있기 때문
    val queue : Queue<Int> = LinkedList()
    disit = Array(max){-1}

    queue.offer(n)
    disit[n] = 0

    while(!queue.isEmpty()){
        val x = queue.poll()
        bw.write("node[${disit[x]}] : $x\n")
        val dx = arrayOf(x-1, x+1, x*2) 
        for(i in 0 until 3){
            val nx = dx[i]
            // 범위밖
            if(nx<0 || nx>=max){
                continue
            }
            // 이전에 현재 점을 방문했고, 지금 value 가 더 크다면 방문하지않는다 
            if(disit[nx] != -1 && disit[nx] <= disit[x]+1){
                continue
            }
            queue.offer(nx)
            disit[nx] = disit[x] + 1
        }
    }

    bw.write("${disit[k]}\n")

    bw.flush()
    bw.close()
    br.close()
}