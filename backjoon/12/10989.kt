import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val arr : Array<Int> = Array(size){0}
    var max = 0
    for(i in 0 until size){
        val value = br.readLine().toInt()
        if(value > max){
            max = value
        }
        arr[i] = value
    }
    
    countingSort(arr, max)

    for(i in arr){
        bw.write("$i\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
} 

/*
    카운팅 정렬 = 도수 정렬
    1. 도수분포표 작성
    2. 누적도수분포표 작성
    3. 목적 배열 만들기
    4. 배열 복사
 */

 private fun countingSort(arr : Array<Int>, maxValue : Int){
    val f : Array<Int> = Array(maxValue+1){0} // 누적 도수 분표표
    val b : Array<Int> = Array(arr.size){0} // 작업용 목적 배열

    // 1
    for(i in 0 until arr.size){
        f[arr[i]]++
    }

    // 2
    for(i in 1 until maxValue+1){
        f[i] += f[i-1]
    }

    //3
    for(i in arr.size-1 downTo 0){
        b[--f[arr[i]]] = arr[i]
    }

    //4
    for(i in 0 until arr.size){
        arr[i] = b[i]
    }

 }