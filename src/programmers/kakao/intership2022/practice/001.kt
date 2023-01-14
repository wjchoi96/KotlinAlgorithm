/*
    카카오 인턴 2022 001 
    level 2
    https://school.programmers.co.kr/learn/courses/30/lessons/118667

    투포인터

    길이가 같은 두개의 큐

    하나의 큐에서 pop
    나머지 큐에 push

    이 반복작업을 통해 두 큐의 합을 같게 하려 함
    최소 작업의 수를 구하라

    어떤 방법으로도 원소의 합을 같게 할 수 없다면 -1을 리턴
*/
/*
    두 배열의 합/2 가 목표

    두 배열을 1자로 나열한 뒤
    투포인터를 사용해 두 배열의 합/2가 되는 구간을 구한다

*/

fun main(args: Array<String>){
    val queue1 = intArrayOf(
        // 3, 2, 7, 2
        1, 2, 1, 2
        // 1, 1
    )
    val queue2 = intArrayOf(
        // 4, 6, 5, 1
        1, 10, 1, 2
        // 1, 5
    )
    val res = Kakao2022001Practice().solution(queue1, queue2)
    println(res)
}

private class Kakao2022001Practice {
    fun solution(queue1: IntArray, queue2: IntArray): Int {

        val expact: Long = (queue1.sum().toLong() + queue2.sum().toLong())/2
        println("expact => $expact")
        val queue = queue1+queue2
        var start = 0
        var end = queue1.size-1 // queue1을 기준으로 시작
        var sum: Long = queue1.sum().toLong()
        var count = 0

        while(sum!=expact){
            if(start == end) {
                count = -1
                break
            }
            count++
            if(sum<expact && end != queue.size-1){
                sum += queue[++end] // pop from 2, push at 1
            }else {
                sum -= queue[start++] // pop from 1, push at 2
            }
            println("$count => $start, $end, $sum")
        }

        return count
    }
}