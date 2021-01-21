fun main(args : Array<String>){
    val year = readLine()!!.toInt()
    val a = if( year % 400 == 0 )
        true
    else if( year % 4 == 0 && year % 100 != 0)
        true
    else 
        false
    print(if(a) "1" else "0" )
} 