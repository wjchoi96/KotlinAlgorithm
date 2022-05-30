package src.backjoon.algorithmtype.queue
/*
    바킹독님 알고리즘 강의 중 큐 단원 구현
*/

private class BarkingDogQueue {
    private val MX = 1000005
    private var queue: Array<Int?> = Array(MX){null}
    private var rear = 0
    private var front = 0

    fun offer(x : Int){
        queue[rear++] = x
    }
    
    fun poll(): Int?{
        return queue[front++]
    }

    fun front(): Int?{
        return queue[front]
    }

    fun back(): Int?{
        return queue[rear-1]
    }
}

fun main(args: Array<String>){
    val queue = BarkingDogQueue()
    queue.offer(10)
    queue.offer(20)
    queue.offer(30)
    println("${queue.front()}")// 10
    println("${queue.back()}")// 30
    queue.poll()
    queue.poll()
    queue.offer(15)
    queue.offer(25)
    println("${queue.front()}")// 30
    println("${queue.back()}")// 25
}

