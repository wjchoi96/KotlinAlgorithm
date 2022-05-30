fun main(arge : Array<String>) {
    val x = readLine()!!.toInt()
    for( i in 1 until 10 )
        println("$x * $i = ${x*i}")
}