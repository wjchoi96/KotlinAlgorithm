/*
    큐 유형 문제
    sliver 1

    트럭

    하나의 차선으로 된 다리. 이 다리를 n개의 트럭이 지나가려 한다
    순서는 바꿀 수 없다
    무게는 서로 같지 않을 수 있다

    다리위에는 단지 w대의 트럭만 동시에 올라갈 수 있다
    다리의 길이는 w단위길이이며 
    각 트럭들은 하나의 단위시간에 하나의 단위길이만큼 이동 가능하다

    => 1초에 1m, 총 Wm의 다리길이 이런식인것같다

    동시에 다리 위에 올라가 있는 트럭들의 무게의 합은 다리의 최대 하중인 L보다 작거나 같아야 한다
    다리위에 완전히 올라가지 못한 트럭의 무게는 다리 위의 트럭들의 무게의 합을 계산할때 포함하지 않는다고 가정

    모든 트럭이 다리를 건너는 최단시간을 구하시오
*/
/*
    1<= n <= 1000 : 트럭의 수
    1<= w <= 100 : 다리의 길이
    10<= L <= 1000 : 다리의 최대하중
*/
/*
    1. 트럭배열 Arr
    2. 다리Queue
    3. queue에 offer를 하며, 현재 queue의 무게를 갱신한다
    - offer 이전에 현재 트럭이 offer 되었을때 최대하중을 넘는지 체크 후 offer
    4. 현재 트럭이 다리에 진입하면 다리가 무너질때는 0을 offer 한다
    5. queue의 size를 체크해서 다리의 길이와 같아지면 poll을 한다
    - 트럭 하나가 다리를 다 건넌것
    
    6. 모든 트럭이 poll 될때까지 offer을 한 횟수를 구한다

    마지막 트럭이 빠져나온것을 어떻게 알지?
    트럭이 빠져나올때마다 카운팅, 전체 트럭수와 일치하면 모두 다 다리를 건넌것

    트럭이 다리를 건너는 순간 0을 offer하는것이 아닌, 새로운 트럭을 offer할수 있다면 그렇게 할 수 있게 알고리즘 수정

    현재 item을 offer했을때 트럭이 다리를 건너가게 된다면? => 이걸 체크할 방법
*/
/*
    제출
    1. 성공
    - 근데 코드가 약간 맘에 안듬

    2. 
*/

import java.util.LinkedList
import java.util.Queue
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter() 

    val queue : Queue<Int> = LinkedList()
    val (n, w, l) = br.readLine().split(' ').map{it.toInt()}
    val trucks: Array<Int> = br.readLine().split(' ').map{it.toInt()}.toTypedArray()

    var passCount = 0
    var currentWeight = 0
    var truckIdx = 0
    var offerCount = 0
    while(passCount != n){
        // 트럭이 남아있고, 현재 트럭이 offer되었을때 최대하중을 넘지 않는다면
        if(truckIdx < n && currentWeight + trucks[truckIdx] <= l){
            currentWeight += trucks[truckIdx]
            queue.offer(trucks[truckIdx++])
        }else{
            if(truckIdx < n && queue.size == w && currentWeight - (queue.firstOrNull()?:0) + trucks[truckIdx] <= l){
                currentWeight += trucks[truckIdx]
                queue.offer(trucks[truckIdx++])
            }else{
                queue.offer(0)
            }
        }
        offerCount++
        println("offer: ${queue.last()}")
        if(queue.size > w){
            val poll = queue.poll()
            if(poll != 0) {
                currentWeight-=poll
                passCount++
            }
            println("poll: $poll")
        }
    }
    bw.write("$offerCount\n")
    

    br.close()
    bw.flush()
    bw.close()
}


// 코드는 맘에 좀 안들지만 성공
private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter() 

    val queue : Queue<Int> = LinkedList()
    val (n, w, l) = br.readLine().split(' ').map{it.toInt()}
    val trucks: Array<Int> = br.readLine().split(' ').map{it.toInt()}.toTypedArray()

    var passCount = 0
    var currentWeight = 0
    var truckIdx = 0
    var offerCount = 0
    while(passCount != n){
        // 트럭이 남아있고, 현재 트럭이 offer되었을때 최대하중을 넘지 않는다면
        if(truckIdx < n && currentWeight + trucks[truckIdx] <= l){
            currentWeight += trucks[truckIdx]
            queue.offer(trucks[truckIdx++])
        }else{
            if(truckIdx < n && queue.size == w && currentWeight - (queue.firstOrNull()?:0) + trucks[truckIdx] <= l){
                currentWeight += trucks[truckIdx]
                queue.offer(trucks[truckIdx++])
            }else{
                queue.offer(0)
            }
        }
        offerCount++
        println("offer: ${queue.last()}")
        if(queue.size > w){
            val poll = queue.poll()
            if(poll != 0) {
                currentWeight-=poll
                passCount++
            }
            println("poll: $poll")
        }
    }
    bw.write("$offerCount\n")
    

    br.close()
    bw.flush()
    bw.close()
}