//bronze1
//12-1


import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val arr : Array<Int> = Array(size){0}
    for(i in 0 until size){
        arr[i] = br.readLine().toInt()
    }
    
    for(i in arr){
        bw.write("$i\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
} 

private fun bubbleSort(arr : Array<Int>){
    for(i in 0 until arr.size){
        var swapCount = 0
        for(j in arr.size-1 downTo i+1){
            if(arr[j-1] > arr[j]){
                swap(arr, j-1, j)
                swapCount++
            }
        }
        if(swapCount == 0) break
    }
}

private fun swap(arr : Array<Int>, idx1 : Int, idx2 : Int){
    val t = arr[idx1]
    arr[idx1] = arr[idx2]
    arr[idx2] = t
}

