/*
    큐 유형 문제
    sliver 4

    요세푸스 문제

    1번부터 n번까지 사람의 원을 이루며 앉아있다
    k(<= n)정수가 주어진다

    순서대로 K번째 사람을 제거

    한사람이 제거되면, 남은사람들로 이루어진 원을 따라 반복
    N명의 사람이 모두 제거될때까지 계속된다

    원에서 사람들이 제거되는 순서를 (N,K)- 요세푸스 순열 이라고 한다
    (7, 3)- 요세푸스 순열은 <3,6,2,7,5,1,4> 이다

    (N, K)- 요세푸스 순열을 구하시오
*/
/*
    1<= K <= N <=5000
    2초
*/
/*
    제출
    1. 성공
*/

import java.util.LinkedList
import java.util.Queue
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   
    val queue : Queue<Int> = LinkedList()
    val (n, k) = br.readLine().split(' ').map{it.toInt()}
    repeat(n) { queue.offer(it+1) }

    val sb = StringBuilder().apply{append("<")}
    while(!queue.isEmpty() && queue.size != 1){
        repeat(k-1){
            queue.offer(queue.poll())
        }
        sb.append("${queue.poll()}, ")
    }
    sb.append("${queue.poll()}>")
    bw.write("${sb}\n")
    br.close()
    bw.flush()
    bw.close()
}
