// sliver2
// 16-2

/*
    회의 I 에 대해 시작시간, 끝나는 시간 부여
    각 회의가 겹치지 않도록 회의를 최대한 많이 배정

    회의가 끝나고, 바로 다음 회의 시작 가능
    회의는 시작시간과 끝나는 시간이 같을 수 있다

    n : 회의의 수
    1<= n <= 10000

    회의 시작, 끝나는 시간은 0<= <=2^31 -1 => int 범위 내

    start, end 를 지닌 회의의 배열

    1. 회의 시작시간 기준으로 정렬 
    2. 시작시간이 제일 빠른 첫번째 회의 결정
    3. 첫번째 회의가 끝나는시간에 가장 가깝고, 가장 짧은 두번째 회의 결정
    => 마지막 idx 까지 반복

    1. 회의가 끝나는 시간 순으로 정렬
    2. 0번쨰 회의 시작 => 가장 빨리 끝나는 회의이기 때문
    3. 0번째 회의가 끝난 시간과 같거나 큰 회의 시작시간을 가지는 다음 회의 시작
    
    정렬
    sortWith, sortedWith, compareBy 숙달해야겠다
    https://notepad96.tistory.com/entry/Kotlin-8
    https://codechacha.com/ko/kotlin-sorting-list/
 */

import java.io.*
import java.util.StringTokenizer
private lateinit var studyArr : Array<Array<Int>>
private val start = 0
private val end = 1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st : StringTokenizer

    val size = br.readLine().toInt()
    studyArr = Array(size) { Array(2){ -1 } }
    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        studyArr[i][start] = st.nextToken().toInt()
        studyArr[i][end] = st.nextToken().toInt()
    }

    // studyArr.sortBy { it[end] } // 회의 끝나는 시간 순으로 정렬
    // 시작하는 순으로 정렬하면 최적해가 나오지 않는다 -> 회의가 더 빨리끝나지만 더 일찍시작하는 회의를 채택하기 때문
    // 끝나는 시간이 같다면, 시작하는 순서가 먼저인 순으로 정렬
    studyArr.sortWith(compareBy( {it[end]}, {it[start]} )) // sortWith, sortedWith, compareBy 숙달해야겠다
    // end 기준으로 정렬하고, 같다면 start 기준
    
    var endTime : Int = studyArr[0][end]
    bw.write("[${studyArr[0][start]}, ${studyArr[0][end]}]\n")
    var sum = 1
    for(i in 1 until size){
        if(studyArr[i][start] >= endTime){ // 회의가 끝난 시간에 가장 가까운 다음 회의 시작
            bw.write("[${studyArr[i][start]}, ${studyArr[i][end]}]\n")
            sum += 1
            endTime = studyArr[i][end] 
        }
    }

    bw.write("$sum\n")


    br.close()
    bw.flush()
    bw.close()
}
