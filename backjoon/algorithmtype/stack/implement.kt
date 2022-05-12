/*
    바킹독님 알고리즘 강의 중 stack 구현
*/



private class BarkingDogStack(){
    private val MX = 1000005
    private val stack : Array<Int?> = Array(MX){null}
    private var pos = 0
    fun push(x : Int){
        stack[pos++] = x
    }
    
    fun pop() : Int? {
        return stack[--pos]
    }
    
    fun peek() : Int? {
        return stack[pos-1]
    }
}

fun main(args : Array<String>){
    val stack = BarkingDogStack()
    stack.push(5)
    stack.push(4)
    stack.push(3)
    println("${stack.peek()}") // 3
    stack.pop()
    stack.pop()
    println("${stack.peek()}") // 5
    stack.push(10)
    stack.push(12)
    println("${stack.peek()}") // 12
    stack.pop()
    println("${stack.peek()}") // 10
}
