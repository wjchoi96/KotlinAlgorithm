/*
    덱 유형 문제
    sliver 4

    회전하는큐

    덱 콜렉션을 사용하는 방식을 학습하자

    n개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다
    이 큐에서 몇개의 원소를 뽑아내려고 한다

    3가지 연산 가능
    1. 첫번째 원소를 뽑아낸다
    [a1,a2...ak] -> [a2,a3...ak]
    2. 왼쪽으로 한칸 이동
    [a1,a2...ak] -> [a2,a3...ak,a1]
    3. 오른쪽으로 한칸 이동
    [a1,a2...ak] -> [ak,a1,a2...ak-1]

    큐에 처음에 포함되어있던 수 n이 주어진다
    뽑아내려고 하난 원소의 위치가 주어진다(가장 처음 큐에서의 위치)
    이때 그 원소를 주어진 순서대로 뽑아내는데 드는 2,3번 연산의 최소값을 구하라
*/
/*
    1<= M <= N <=50
*/
/*
    1~N 으로 이루어진 큐의 데이터

    현재 뽑아야 하는 숫자가 어느쪽으로 데이터를 회전시켰을때 더 가까울지 판단
    사실상 원형이라고 생각

    제거할 idx
    size-idx, idx를 비교

    1,2,3,4,5,6

    에서 3을 제거
    removeIdx = 2
    start 에서 2칸
    end 에서 3칸(size-1-removeIdx)

    start 에 더 가까우면 pollFirst
    end에 더 가까우면 pollLast
*/
/*
    제출
    1. 성공
*/
/*
    개선
    1. 한쪽으로만 회전하여 횟수를 구하고, 해당 횟수를 전체count에서 뺀 값과 비교하여 더 적은 값을 출력
    => 한쪽으로 회전하여 값을 제거했다는건, 반대로 회전했다면 size-count 횟수일거라는 계산인가?
    => 다만 최악의 경우 시간복잡도가 많이 늘어나는 구현 방법. 코드는 깔끔하긴 하다
    https://www.acmicpc.net/source/22262909

    => 현재 방법은 매 회전마다 indexOf를 수행한다
    indexOf의 시간복잡도는 O(n)
    
    => 위의 방법의 최악의 시간복잡도는 O(n)

    더 개선된 방법인것 같으니 구현 시도

    제출
    1. 성공

*/

import java.util.Deque
import java.util.LinkedList
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val (n, _) = br.readLine().split(' ').map{it.toInt()}
    val deque: Deque<Int> = LinkedList()
    repeat(n){
        deque.offerLast(it+1) // 뒤로 넣는다
    }
    val values = br.readLine().split(' ').map{it.toInt()}
    var count = 0
    values.forEach {
        var rotateCount = 0
        while(deque.peekFirst() != it){ // 찾는값이 나올때까지 한쪽으로 회전
            deque.offerFirst(deque.pollLast())
            rotateCount++
        }
        count += if(deque.size-rotateCount>rotateCount)rotateCount else deque.size-rotateCount // 더 적은값을 넣어준다
        deque.pollFirst()
    }
    bw.write("${count}\n")

    br.close()
    bw.flush()
    bw.close()
}

/*
    매 회저마다 indexOf를 수행
    indexOf의 시간복잡도는 O(n)이니 바람직한 상황이 아니다
*/
private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val (n, _) = br.readLine().split(' ').map{it.toInt()}
    val deque: Deque<Int> = LinkedList()
    repeat(n){
        deque.offerLast(it+1) // 뒤로 넣는다
    }
    val values = br.readLine().split(' ').map{it.toInt()}
    var size = n
    var count = 0
    printDeque(deque, count)
    values.forEach {
        var removeIdx = deque.indexOf(it)
        while(removeIdx != 0){
            if(removeIdx <= size-1-removeIdx){ // pollFirst
                deque.offerLast(deque.pollFirst())
                count++
                printDeque(deque, count)
            }else{ // pollLast
                deque.offerFirst(deque.pollLast())
                count++
                printDeque(deque, count)
            }
            removeIdx = deque.indexOf(it)
        }
        deque.pollFirst()
        size--
    }
    bw.write("${count}\n")

    br.close()
    bw.flush()
    bw.close()
}
private fun printDeque(deque: Deque<Int>, count: Int){
    print("[ ")
    for(i in deque){
        print("${i} ")
    }
    print("] [$count]\n")
}