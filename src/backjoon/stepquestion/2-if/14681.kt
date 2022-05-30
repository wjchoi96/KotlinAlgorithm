fun main(args : Array<String>){
    val x = readLine()!!.toInt()
    val y = readLine()!!.toInt()
    val recPoint = if( x > 0 && y > 0 )
        1
    else if( x < 0 && y > 0 )
        2
    else if( x < 0 && y < 0 )
        3
    else
        4
    println("$recPoint")
}