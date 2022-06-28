/*
    바킹독님 알고리즘 강의 중 이진검색트리, 우선순위큐 단원 문제
    gold 2
    #우선순위 큐 구현
    priorityqueue.1202.kt
    #이진검색트리 구현
    binarysearchtree.1202.kt

    보석 도둑

    도둑이 보석점을 털기로 결심했다
    상덕이가 털 보석점에는 보석이 종 N개 있다
    각 보석은 무게 M[i] 와 가격 V[i]를 가지고 있다
    도둑은 가방K개를 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 C[i]이다
    가방에는 최대 한 개의 보석만 넣을 수 있다
    훔칠 수 있는 보석의 최대 가격을 구하시오

    1초
    1<= N, K <= 300,000
    0<= M[i], V[i] <= 1,000,000
    1<= C[i] <= 100,000,000

*/
/*
    그리디인지 파악하기 힘들었겠는데

    그럴듯한 그리디로 구현해보자

    가방을 오름차순으로 정렬 한 뒤
    현재 가방에 담을 수 있는 보석 중 가장 큰 가치를 지닌 보석을 담는다

    무게가 작은 순으로, 무게가 같다면 가치가 높은 순으로 우선순위 부여
    
    가방 [2, 10]
    보석 [1:65, 2:99, 5:23]

    현재 item이 다음 item 중 가치가 큰 item을 보존, 가치가 작은 item은 제거
    다음 item이 담을 수 없는 무게일때까지 반복하여, 현재 가방에 담을 수 있는 최고의 가치의 물건을 선택

    => 만약 가방에 담을 수 있는 item이 없다면? 가치가 작은 item은 제거해버렸잖아
    예) 
    가방 [2, 2, 3]
    보석 [1:22, 2:44, 3:33]

    첫번째 가방에 2:44가 담길것이며, 1:22는 제거될것
    두번째 가방에 담을 것이 없다

    가치가 작은 item을 제거하는게 아닌 유지시킬 방법을 생각해보자
    -> 보관해 뒀다가 다시 offer => 개당 O(lg N)의 시간복잡도. 최대 N개 
    => 시간복잡도가 안될것 같은데
*/
/*
    제출
    내 그리디
    https://jaimemin.tistory.com/760 참고

    1. 시간초과(7%)
    - 가방, 보석이 모두 무게순 오름차순이니, 이전 가방에서 추가된 보석은 현재 가방에도 당연히 추가될 수 있다
    - 때문에 queue, 와 jIdx를 그대로 유지할 수 있다

    2. 틀렸습니다(7%)
    - sum을 long 타입으로 설정

    3. 시간초과(14%)

    4. 성공
    - 근데 3번제출이랑 달라진게 없는거 같은데
    - 3번 제출과 차이점을 찾으려고 한참 고민했는데, 백준 채점문제인지, 같은코드가 실패할때도 있고 성공할때도 있다
    - 성공했던 코드를 다시 제출하니 시간초과가 뜨고, 실패했던 코드를 다시 제출하니 성공이 뜨기도 한다
    - 시간복잡도가 간당간당한듯
    - 다른 코드들과 큰 차이가 없는데, 속도 차이가 나는 이유는 뭘까?
*/
import java.util.PriorityQueue
fun main(args: Array<String>){
    Solution1202().solve()
}
class Solution1202 {
    // 내 그리디 풀이
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, k) = br.readLine().split(' ').map{it.toInt()}
        val jewels: Array<Jewel?> = Array(n){null}
        repeat(n){
            jewels[it] = Jewel(br.readLine().split(' ').map{it.toInt()})
        }
        jewels.sortWith(compareBy(
            {it!!.weight}, {-it!!.value}
        ))// 무게순 오름차순. 무게가 같다면 가치가 높은게 우선

        val bags: Array<Int> = Array(k){0}
        repeat(k){
            bags[it] = br.readLine().toInt()
        }
        bags.sort()

        var sum: Long = 0
        val maxHeap = PriorityQueue<Int>(compareBy(
            {-it}
        ))
        var j = 0
        //내부의 while문은 for문과 관계없이 딱 N번 반복하니 O(NK)가 아니고 O(N+K)
        //while문 내부의 offer으로 인해 while문 시간복잡도 O(NlogN)
        //for문 내부의 poll로 인해 for문 시간복잡도 O(klogN)
        // N과K의 범위가 같기 때문에 총 O(NlogN)
        for(b in 0 until bags.size){
            //현재 가방에 담을 수 있는 보석을 모두 최대힙에 담는다
            //담을 수 있는 보석을 모두 담는다. 가방의 무게를 넘은 구간은 확인하지 않는다
            //한번 담은 보석은 다음 가방에도 담을 수 있는 보석이니 유지
            while(j<n && jewels[j]!!.weight <= bags[b]){
                maxHeap.offer(jewels[j++]!!.value)
            }
            // 담은 보석중 가치가 제일 큰 보석의 정보를 가져온다
            if(!maxHeap.isEmpty()){
                sum+=maxHeap.poll()
            }
        }
        //
        bw.write("$sum\n")
        
    
        bw.flush()
        bw.close()
        br.close()
    }
    private data class Jewel(
        val weight: Int,
        val value: Int
    ){
        constructor(list: List<Int>): this(list[0], list[1])
    }
}