/*
    카카오 2022 인턴 코테 2번

    길이가 같은 두개의 큐가 존재

    하나의 큐에서 pop
    pop된 원소를 다른 큐에 insert 하는 작업을 진행

    이를 통해 각 큐의 원소 합이 같도록 만드려고 한다
    이때 필요한 작업의 최소 횟수를 구하라

    큐를 배열로 표현
    원소가 배열 앞쪽에 있을수록 먼저 집어넣은 원소

    pop => arr[0] 제거
    inster => arr의 마지막에 추가

    두 큐에 담긴 원소의 합은 30
    각 큐의 합을 15로 만들려한다

    큐의 원소 합을 같게 만들 수 없는 경우 -1 리턴
*/
/*
    1<= n <= 300,000
    1<= item <= 10^9
    long type 사용 고려
*/

/*
    각 큐의 합을 가지고 있는 sum1, sum2 를 구한다
    더 큰 큐에서 pop -> 더 작은 큐에 insert
    각 sum 갱신

    반복?

    queue1 = [3, 2, 7, 2] => 14
    queue2 = [4, 6, 5, 1] => 16

    queue1 = [3, 2, 7, 2, 4] => 18
    queue2 = [6, 5, 1] => 12

    queue1 = [2, 7, 2, 4] => 15
    queue2 = [6, 5, 1, 3] => 15



    각 queue의 합이 홀수면 안되는거같은데
    길이가 꽤 긴데.. 일단 list로 해보자
    => add, remove만 많이하니까 linkedList


    => 2번

    안되는걸 어떻게 알까?
    [1, 3]
    [1, 5]

    한쪽의 sum보다 큰item이 있다면 안되는데

    item1개 빼고 한쪽에 싹다 몰았는데
    상대가 더 크면 안되는거

    1. 안되는유형인데 못찾았다
    2. 되는 유형인데 시간초과다
    - sum1, sum2를 합하고 2로 나눈게 최종 목표가 되어야한

    queue1 = [3, 2, 7, 2] => 14
    queue2 = [4, 6, 5, 1] => 16

    => 15가 되어야한다

    15보다 큰 큐에서
    자기가 15이하가 될때가지 pop해서 상대 큐에 넣는다

    둘다큰경우는? 존재할수가없다

*/
/*
    tc11, 25, 26, 27, 28 : 시간초과

    tc 11, 28 : 시간초과
*/
/*
    10개, 값이 홀수
    [1,1,1,1,8] = 12
    [1,1,1,1,2] = 6

    18 => 9

    list 2개 합쳐서 투포인터?

    start = 0
    end = queue1.size

    에서 시작해서 구간합 구하는거
    
    0과 start 사이의 거리가 count에 더해져야하고
    본래queue size보다 end가 크다면 그만큼 적용해줘야한다

    투포인터였네
*/

import java.util.LinkedList
import java.util.Queue
fun main(args : Array<String>){
    Kakao2022002().solution(
        intArrayOf(
            3, 2, 7, 2
        ),
        intArrayOf(
            4, 6, 5, 1
        )
    )
}

private class Kakao2022002 {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var sum1 : Long = 0
        var sum2 : Long = 0
        var fSize = queue1.size
        var sSize = queue2.size
        if(fSize>sSize){
            for(i in 0 until sSize){
                sum1 += queue1[i]
                sum2 += queue2[i]
            }
            for(i in sSize until fSize){
                sum1 += queue1[i]
            }
        }else{
            for(i in 0 until fSize){
                sum1 += queue1[i]
                sum2 += queue2[i]
            }
            for(i in fSize until sSize){
                sum2 += queue2[i]
            }
        }
        if((sum1+sum2)%2 == 1.toLong()){
            return -1
        }
        val value = (sum1+sum2)/2
        val list = queue1 + queue2
        val n = list.size
        println("${queue1.toList()}")
        println("${queue2.toList()}")
        println("${list.toList()}")

        var start = 0
        var end = queue1.size
        var min = Int.MAX_VALUE
        while(true){
            if(end == n && start==n){
                break
            }
            print("[$start to ${end}] : $sum1\n")
            if(sum1>=value || end==n){
                if(sum1 == value.toLong()){
                    val popCount = start // start가 증가되었다는건 그만큼 pop() 을 진행한것
                    val offerCount = end-queue1.size // end가 증가되었다는건 그만큼 offer() 을 한것
                    println("pop : $popCount, offer : $offerCount, total : ${popCount+offerCount}")
                    min = Math.min(min, popCount+offerCount)
                }
                sum1 -= list[start++] //start가 가리키던 값을 sum에서 제거하고 ++
            } else if(sum1 < value){
                sum1 += list[end++] // end를 증가시키고 해당 값을 sum에 적용
            }
        }

        println("$min")
        return if(min == Int.MAX_VALUE)
            -1
        else   
            min
    }
}
/*
    [3, 2, 7, 2], [4, 6, 5, 1]
    value = 15
    14

    3,2,7,8,4 
    18
    2,7,8,4
    15
*/

/*
    tc11, 25, 26, 27, 28 : 시간초과

    tc 11, 28 : 시간초과
    o(n)인데..
*/

private class Kakao2022002Try1 {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var sum1 : Long = 0
        var sum2 : Long = 0
        var fSize = queue1.size
        var sSize = queue2.size

        
        if(fSize>sSize){
            for(i in 0 until sSize){
                sum1 += queue1[i]
                sum2 += queue2[i]
            }
            for(i in sSize until fSize){
                sum1 += queue1[i]
            }
        }else{
            for(i in 0 until fSize){
                sum1 += queue1[i]
                sum2 += queue2[i]
            }
            for(i in fSize until sSize){
                sum2 += queue2[i]
            }
        }

        
        for(i in 0 until fSize){
            sum1 += queue1[i]
        }
        for(i in 0 until sSize){
            sum2 += queue2[i]
        }

        val queueF : Queue<Int> = LinkedList<Int>(queue1.toList())
        val queueS : Queue<Int> = LinkedList<Int>(queue2.toList())

        var count = 0
        val value = (sum1+sum2)/2
        while(sum1 != value && sum2 != value){
            if(fSize == 1 && sum1>sum2){
                count = -1
                break
            }else if(sSize == 1 && sum1<sum2){
                count = -1
                break
            }
            if(sum1>sum2){
                val item = queueF.poll()
                sum1 -= item
                sum2 += item
                fSize -= 1
                sSize += 1
                queueS.offer(item)
            }else{
                val item = queueS.poll()
                sum2 -= item
                sum1 += item
                sSize -= 1
                fSize += 1
                queueF.offer(item)
            }
            count++
        }

        return count
    }
}

// 더안되네 ㅋㅋ
private class Kakao2022002Try2Fail {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var sum1 : Long = 0
        var sum2 : Long = 0
        var fSize = queue1.size
        var sSize = queue2.size

        
        if(fSize>sSize){
            for(i in 0 until sSize){
                sum1 += queue1[i]
                sum2 += queue2[i]
            }
            for(i in sSize until fSize){
                sum1 += queue1[i]
            }
        }else{
            for(i in 0 until fSize){
                sum1 += queue1[i]
                sum2 += queue2[i]
            }
            for(i in fSize until sSize){
                sum2 += queue2[i]
            }
        }

        val queueF : Queue<Int> = LinkedList<Int>(queue1.toList())
        val queueS : Queue<Int> = LinkedList<Int>(queue2.toList())

        var count = 0
        var value = (sum1 + sum2)/2
        loop@while(sum1 != value || sum2 != value){
            if(fSize > sSize){
                while(sum1>value){
                    if(fSize == 1){
                        count =-1
                        break@loop
                    }
                    val item = queueF.poll()
                    sum1 -= item
                    fSize -= 1
                    queueS.offer(item)
                    sum2 += item
                    sSize += 1
                    count++
                }
            }else{
                while(sum2>value){
                    if(sSize == 1){
                        count =-1
                        break@loop
                    }
                    val item = queueS.poll()
                    sum2 -= item
                    sSize -= 1
                    queueF.offer(item)
                    sum1 += item
                    fSize += 1
                    count++
                }
            }
        }
        return if(sum1 == value && sum2 == value){
            count
        }else{
            -1
        }
    }
}