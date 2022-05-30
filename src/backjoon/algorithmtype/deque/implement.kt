/*
    바킹독님 알고리즘 강의 중 덱 구현
*/


private class BarkingDogDeque {
    private val MX = 1000005
    private val deque = Array<Int?>(2*MX+1){null}
    private var head = MX
    private var tail = MX

    /*
        앞,뒤는 보편적인 앞,뒤의 정의를 따른다
        front: 앞쪽의 값
        back: 뒤쪽의값
        
        head: front의 값을 가리킨다
        tail: back+1의 값을 가리킨다
    */
    val front: Int?
        get() = deque[head]
    val back: Int?
        get() = deque[tail-1]

    fun pushFront(x : Int){
        deque[--head] = x
    }
    fun pushBack(x : Int){
        deque[tail++] = x
    }
    fun popFront(): Int?{
        return if(front == null){
            null
        }else{
            deque[head++]
        }
    }
    fun popBack(): Int?{
        return if(back == null){
            null
        }else{
            deque[--tail]
        }
    }
}

fun main(args: Array<String>){
    val deque = BarkingDogDeque()
    deque.pushBack(30) // 30
    println("${deque.front}")  // 30
    println("${deque.back}") // 30
    deque.pushFront(25)  // 25 30 
    deque.pushBack(12) // 25 30 12
    println("${deque.back}")  // 12
    deque.pushBack(62) // 25 30 12 62
    deque.popFront()  // 30 12 62
    println("${deque.front}")  // 30
    deque.popFront() // 12 62
    println("${deque.back}") //  62
    /*
        30
        30
        12
        30
        62
    */
}
