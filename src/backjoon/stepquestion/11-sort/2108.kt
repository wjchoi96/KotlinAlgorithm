// 12-4
// sliver 3
import java.io.*
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val t : Array<Int> = Array(8001){0} // -4000 ~ 4000
    var max = -5000
    var min = 10000

    var sum = 0.0
    for(i in 0 until size){
        val value = br.readLine().toInt()
        t[value+4000]++
        sum += value

        if(max<value){
            max = value
        }
        if(min>value){
            min = value
        }
    }
    sum /= size

    bw.write("\n")
    //1
    bw.write("${getRoundOff(sum)}\n")
    
    //2,3
    var count = 0
    var midValue = 10000 // 중앙값

    var maxCountValueTemp = 0 // 최빈값구할때 사용 -> max 빈도 저장용
    var maxCountValue = 10000 // 최빈값
    var flag = false // 이전의 동일한 최빈값이 1번만 등장했을경우 true, 아닐경우 false
    for(i in min+4000 until max+4001){
        if(t[i]>0){
            /*
                중앙값 찾기 : 누적횟수가 전체 길이의 절반에 못 미친다면
            */
            if(count < (size+1) / 2){
                count += t[i] // i값의 빈도수를 count 에 누적
                midValue = i-4000
            }

            // 최빈값
            if(maxCountValueTemp < t[i]){
                maxCountValueTemp = t[i]
                maxCountValue = i-4000
                flag = true
            }else if(maxCountValueTemp == t[i] && flag){ // 이전 최빈값 최대값과 동일하면서, 한번만 중복되는 경우
                maxCountValue = i-4000
                flag = false
            }
        }  
    }  
    //2
    bw.write("$midValue\n")
    //3
    bw.write("$maxCountValue\n")


    //4
    bw.write("${max-min}\n")



    bw.flush()
    bw.close()
    br.close()
}

private fun getRoundOff(value : Double) : Int {
    return if(value >= 0){
        if(value*10%10 <= 4){
            value.toInt()
        }else{
            (value+1).toInt()
        }
    }else{
        if(-value*10%10 <= 4){
            value.toInt()
        }else{
            (value-1).toInt()
        }
    }
}

/*
    1. 산술평균 : n개의 수들의 합을 n으로 나눈것
    2. 중앙값 : n개의 수들을 오름차순으로 나열했을때 중앙값
    3. 최빈값 : n개의 수들 중 가장 많이 나타나는 값
    4. 범위 : n 개의 수들 중 최대값, 최소값

    1. 그냥 다 더하고 나누면된다 -> loop 한번
    2. 중앙값 => 정렬 해서 중앙값 가져오면 된다
    3. 도수분포표 작성? -> 카운팅 정렬 -> 음수 처리를 어케하지
    0~8000
    0 -> 0
    4. 정렬 후 0번재, n번째 값
 */