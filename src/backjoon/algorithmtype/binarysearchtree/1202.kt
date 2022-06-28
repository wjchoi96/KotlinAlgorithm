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
    바킹독님 해설 슬쩍 보려다가 본 그리드 접근법
    => 이건 bst lowwerBound 사용해서 구현해야겠다

    가격이 높은 보석순으로 정렬
    현재 보석을 담을 수 있는 가장 작은 베낭에 보석을 담는다

    귀류법1. 
    가장 가격이 높은 보석x를 해당 보석을 담을 수 있는 가방중 최대무게가 A보다 큰 B가방에 담는게 더 이득인가?
    => 만약 A에 다른 보석y가 있다면, 가방 A와 가방B의 내용물 바꿔도 제한 조건에 아무런 문제가 없다

    예) 
    가방 [2, 2, 3]
    보석 [2:44, 3:33, 1:22]
    2:44를 가방[2]에 담는다
    3:33을 가방[3]에 담는다
    1:22를 가방[1]에 담는다

    귀류법1에 대한 반례
    2:44를 가방[3]에 넣는다
    3:33를 담을 가방이 없다
    1:22를 가방[2]에 담는다

    보석을 가치가 높은 순으로, 가치가 같다면 무게가 작은 순으로 정렬
    가방을 TreeMap에 key로 저장. 동일한 무게가 추가된다면 value++
*/
/*
    제출
    1. 성공
*/

import java.util.TreeMap
fun main(args: Array<String>){
    Solution1202UseBst().solve()
}
class Solution1202UseBst {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
    
        val (n, k) = br.readLine().split(' ').map{it.toInt()}
        val jewels: Array<Jewel?> = Array(n){null}
        repeat(n){
            jewels[it] = Jewel(br.readLine().split(' ').map{it.toInt()})
        }
        jewels.sortWith(compareBy(
            {-it!!.value}, {it!!.weight}
        ))// 가치가 높은 순, 가치가 같다면 무게가 적은 순

        val bags: TreeMap<Int, Int> = TreeMap()
        repeat(k){
            val v = br.readLine().toInt()
            bags[v] = bags.getOrDefault(v, 0)+1
        }

        var sum: Long = 0
        //O(NlogN)
        for(j in 0 until n){
            //보석의 무게를 담을 수 있는 가장 작은 베낭 -> 보석의 무게보다 큰 키중에 제일 작은 것
            val w = jewels[j]!!.weight
            val key = if(bags.containsKey(w)){
                w
            }else {
                bags.higherKey(w) //w보다 큰 key중 제일 작은 key
            }
            if(key == null) continue // 담을 가방이 없다
            when(bags.getOrDefault(key, 0)) {
                0 -> bags.remove(key) // 일반적으로는 없어야 하는 경우의 수
                1 -> bags.remove(key)
                else -> bags[key] = bags[key]!!-1
            }
            sum+=jewels[j]!!.value
        }
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