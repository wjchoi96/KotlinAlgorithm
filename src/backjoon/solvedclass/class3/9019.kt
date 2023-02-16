/*
    백준 9019
    solved.ac class 3
    https://www.acmicpc.net/problem/9019

    gold 4

    D, S, L, R 4개의 명령어를 이용하는 계산기
    계산기의 레지스터는 0 이상 10,000 미만의 십진수를 저장 가능
    각 명령어는 레지스터에 저장된 n을 다음과 같이 변환
    - n의 네 자릿수를 d1, d2, d3, d4라고 칭함
    => n = ((d1*10 + d2) * 10 + d3) * 10 + d4

    명령어
    D
    - D는 n을 2배로 바꿈
    - 결과값이 9999보다 큰 경우, 10000으로 나눈 나머지를 취함
    - 그 결과값(2n mod 10000)을 레지스터에 저장

    S
    - S는 n에서 1을 뺸 결과(n-1)를 레지스터에 저장
    - n이 0이라면 9999가 레지스터에 저장

    L
    - L은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장
    - 이 연산 이후, 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 됨
    => 1234 => 2341

    R
    - R은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장
    - 이 연산 이후, 레지스터에 저장된 네 자릿수는 오른편부터 d4, d1, d2, d3이 됨
    => 1234 => 4123

    서로 다른 두 정수 A, B에 대하여, A를 B로 바꾸는 최소한의 명령어를 생성
    예)
    A = 1234
    B = 3412 일떄,
    L, L을 수행하면 1234 -> 2341 -> 3412
    R, R을 수행하면 1234 -> 4123 -> 3412
    => LL or RR을 출력

    n의 자릿수로 0이 포함된 경우 주의
    1000에 L을 적용하면 0001이 되어 결과는 1이 됨
    R을 적용하면 0100이되므로 결과는 100이 됨 

    A 와 B는 모두 0 이상 10,000 미만
*/
/*
    예상을 못했는데 그래프, bfs유형 문제라고 소개되어 있음
    - bfs라고 알고리즘 유형을 확인하지 않았더라면, bfs풀이를 생각해 낼 수 있었을까?ㄴㄴㄴ

    A를 start로 삼음
    nxtNode를 선정할때, D, S, L, R 연산을 수행한 결과들을 nxtNode로 선정
    => 이때, B가 나온 순간 최단경로가 완성된것이니 즉시 bfs를 종료
    => visit 체크를 하지 않아 무한 loop으로 들어갈 수 있음
        => visit체크를 해도 좋을듯..?

    V는 10000개
    E는 4개
    E가 무척 작으므로, bfs의 시간복잡도는 O(V)
    
    bfs내부에서 연산을 수행하는 시간복잡도
    - 4자리수의 숫자를 가공
    - O(n) => O(4)

    최단경로를 어떻게 출력해줄까
    - StringBuilder를 Queue에 함께 넣어줌
    - 메모리 초과 걱정됐는데 ㄹㅇ 초과해버림
    - 근데 StringBuilder때문인지, Queue에 너무 많은 Node가 추가되서인지 확인 필요

    #padStart(size: Int, c: Char)
    - 시작 기준 데이터의 부족한 길이를 특정 문자로 채우기

    #제출
    1. 메모리 초과
    - visit 적용

    2. 런타임 에러(ArrayIndexOutOfBound)(3%)
    - s에 0을 넣으면 -1이 리턴됨

    3. 틀렸습니다(3%)
    - s 로직 잘못 짰음

    4. 시간초과(53%)
    - String이 무거우니, Char를 sb에 append하도록 변경

    5. 성공


*/
import java.util.Queue
import java.util.LinkedList
fun main(){
    Solution9019().solve()
}
class Solution9019 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        val tc = br.readLine().toInt()
        repeat(tc) { _ ->
            val (a, b) = br.readLine().split(' ').map{it.toInt()}
            val res = bfs(a, b)
            bw.write("$res\n")
        }

    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun bfs(start: Int, to: Int): String? {
        val queue: Queue<Pair<Int, StringBuilder>> = LinkedList()
        queue.offer(start to StringBuilder())
        val visit = Array(10000){false}
        visit[start] = true

        while(queue.isNotEmpty()) {
            val cur = queue.poll()
            for(i in 0 until 4) {
                val (nxt, op) = when (i) {
                    0 -> d(cur.first) to 'D'
                    1 -> s(cur.first) to 'S'
                    2 -> l(cur.first) to 'L'
                    else -> r(cur.first) to 'R'
                }
                val sb = StringBuilder(cur.second).append(op)
                if(nxt == to) {
                    return sb.toString()
                }
                if(visit[nxt]){
                    continue
                }
                
                queue.offer(nxt to sb)
                visit[nxt] = true
            }
        }
        return null
    }

    private fun d(n: Int): Int {
        return (2*n).let {
            if(it > 9999) it % 10000 else it
        }
    }

    private fun s(n: Int): Int {
        return if(n==0) 9999 else n-1
    }

    private fun l(n: Int): Int {
        val str = n.toString().padStart(4, '0').toCharArray()
        val first = str.first()
        print("L => ${String(str)} to ")
        str[0] = str[1]
        str[1] = str[2]
        str[2] = str[3]
        str[3] = first
        println("${String(str)}")
        return String(str).toInt()
    }

    private fun r(n: Int): Int {
        val str = n.toString().padStart(4, '0').toCharArray()
        val last = str.last()
        print("R => ${String(str)} to ")
        str[3] = str[2]
        str[2] = str[1]
        str[1] = str[0]
        str[0] = last
        println("${String(str)}")
        return String(str).toInt()
    }
}