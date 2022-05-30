import java.util.*
fun main(args : Array<String>) = with(Scanner(System.`in`)) {
    var sum = 0
    for( i in 1 until nextInt() + 1)
        sum += i
    print("$sum")
}