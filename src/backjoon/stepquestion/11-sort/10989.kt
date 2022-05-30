import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    doCountingSort(br, bw)

    // val size = br.readLine().toInt()
    // val arr : Array<Int> = Array(size){0}
    // var max = 0
    // for(i in 0 until size){
    //     val value = br.readLine().toInt()
    //     if(value > max){
    //         max = value
    //     }
    //     arr[i] = value
    // }
    
    // countingSort2(arr, max, bw)
    
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

private fun doCountingSort(br : BufferedReader, bw : BufferedWriter){
    val f : Array<Int> = Array(10001){0} // 도수 분포표

    // data 받으면서 도수분포표 작성
    for(i in 0 until br.readLine().toInt()){
        f[br.readLine().toInt()]++
    }

    for(i in 1 until f.size){
        while(f[i]>0){
            bw.write("$i\n")
            f[i]--
        }
    }

}

// 메모리초과
private fun countingSort2(arr : Array<Int>, maxValue : Int, bw : BufferedWriter){
    val f : Array<Int> = Array(maxValue+1){0} // 도수 분포표
    // 1
    for(i in 0 until arr.size){
        f[arr[i]]++
    }

    //3, 4통합해서 출력
    for(i in 1 until f.size){ // 범위가 1<= <=10000
        // f[i]가 0이 될때까지 출력
        while(f[i]>0){
            bw.write("$i\n")
            f[i]--
        }
    }

}

// 메모리초과
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