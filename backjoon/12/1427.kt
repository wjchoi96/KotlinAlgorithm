// sliver 5
// 12-5
import java.io.*
fun main(args : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val value = br.readLine()
    val arr = getIntArray(value)
    
    revereseBubbleSort(arr)
    for(i in arr){
        bw.write("$i")
    }
    bw.write("\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun revereseBubbleSort(arr : Array<Int>){
    for(i in 0 until arr.size){
        for(j in arr.size-1 downTo i+1){
            if(arr[j-1]<arr[j]){
                swap(arr, j-1, j)
            }
        }
    }
}

private fun swap(arr : Array<Int>, idx1 : Int, idx2 : Int){
    val t = arr[idx1]
    arr[idx1] = arr[idx2]
    arr[idx2] = t
}

private fun getIntArray(str : String) : Array<Int>{
    val arr = Array<Int>(str.length){0}
    var value = str.toInt()

    var count = 0
    while(value != 0){
        arr[count++] = value%10
        value /= 10
    }

    return arr
}