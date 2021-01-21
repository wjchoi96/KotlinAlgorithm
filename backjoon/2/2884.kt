fun main(args : Array<String>){
    val input = readLine()!!.toString()
    val inputArr = input.split(" ")
    val hour = inputArr[0].toInt()
    val min = inputArr[inputArr.size - 1].toInt()
    var resultHour = 0
    var resultMin = 0
    if( min >= 45 ){
        resultHour = hour
        resultMin = min - 45
    }else{
        resultMin = 60 - ( 45 - min ) 
        // process hour
        resultHour = hour - 1
        if( resultHour < 0 )
            resultHour = 24 - ( resultHour * -1 )
    }
    println("$resultHour $resultMin")
}