import java.util.*
fun main(arge : Array<String>) = with(Scanner(System.`in`)) {
    for(i in 0 until nextInt()){ // until 은 마지막 숫자 포함안함 / .. 은 마지막 숫자 포함
        println(nextInt() + nextInt())
    }
} 