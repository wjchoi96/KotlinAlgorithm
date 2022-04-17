//sliver4
//18-6
/*
    1. 첫번째 원소를 뽑아낸다 => front++ 된다
    2. 왼쪽으로 한 칸 이동시킨다 => a1, ..., ak가 a2, ..., ak, a1 => offer(poll)
    3. 오른쪽으로 한 칸 이동시킨다 => a1, ..., ak가 ak, a1, ..., ak-1 => offerFirst(pollLast)

    z의 크기 N, 뽑아낼 수의 개수 M
    1 <= M <= N < = 50

    2와 3을 수행하는 최솟값을 구하라

    현재 item 에서 찾는 item까지 앞으로 가는게 빠른지, 뒤로가는게 빠른지 확인하는 방법?

    현재 item 에서 찾는 item까지 앞으로 가는 횟수 

    find 3
    오른쪽으로 가는 횟수
    currentItem > searchItme : [6,1,2,3,4,5]
    [1,2,3,4,5,6]
    [2,3,4,5,6,1]
    [3,4,5,6,1,2] => 3번

    currentItem < searchItme : [1,2,3,4,5,6]
    [2,3,4,5,6,1]
    [3,4,5,6,1,2] => 2번

    if(currentItem < searchItem){ 
        searchItem - currentItem 
    }else{
        size - currentItem + searchItem
    }

    현재 item 에서 찾는 item까지 뒤로 가는 횟수

    find 3
    오른쪽으로 가는 횟수

    currentItem > searchItme : [6,1,2,3,4,5]
    [5,6,1,2,3,4]
    [4,5,6,1,2,3]
    [3,4,5,6,1,2] => 3번

    currentItem < searchItme : [1,2,3,4,5,6]
    [6,1,2,3,4,5]
    [5,6,1,2,3,4]
    [4,5,6,1,2,3]
    [3,4,5,6,1,2] => 4번

    if(currentItem < searchItem){ 
        backItem
    }else{
        searchItem - currentItem 
    }

    1. 뽑고자 하는 원소가 덱의 중앙에서 끝쪽에 가까운 쪽 방향으로 이동(연산)하여 뽑고자 하는 원소가 첫 번째 위치에 갈 때까지 반복
    2. 원소를 뽑음

    searchItem의 idx를 찾는다
    halfIdx 의 앞, 뒤에 있는지 판단하여 2,3번 연산 중 적절한것을 수행
 */

import java.io.*
import java.util.*
private lateinit var queue : LinkedList<Int>
private var count = 0
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val k = st.nextToken().toInt() // 뽑으려는 item 개수

    queue = LinkedList<Int>()
    for(i in 1 until size+1){
        queue.offer(i)
    }
    printQueue()

    st = StringTokenizer(br.readLine())
    val searchArr = Array(k){st.nextToken().toInt()} // 뽑을 숫자를 담은 배열

    for(i in 0 until k){
        val targetIdx = queue.indexOf(searchArr[i])
        val halfIdx = if(size%2==0){
            size/2-1
        }else{
            size/2
        }
        print("target : $targetIdx, halfIdx : $halfIdx\n")
        if(targetIdx <= halfIdx){
            for(j in 0 until targetIdx){
                val temp = queue.pollFirst()
                queue.offerLast(temp)
                printQueue()
                count++
            }
        }else{
            for(j in 0 until size-targetIdx){
                val temp = queue.pollLast()
                queue.offerFirst(temp)
                printQueue()
                count++
            }
        }
        print("poll first : ${queue.pollFirst()}\n")
    }

    bw.write("$count\n")

    bw.flush()
    bw.close()
    br.close()
}
private fun printQueue(){
    print("[ ")
    for(i in queue){
        print("${i} ")
    }
    print("]\n")
}
private fun moveQueueLeft(){
    count++
    queue.offerLast(queue.pollFirst())   
}
private fun moveQueueRight(){
    count++
    queue.offerFirst(queue.pollLast())
}