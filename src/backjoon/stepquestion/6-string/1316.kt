// silver 5
// 7-10

import java.util.*
import java.io.*

fun main(args : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    var count = 0
    for(i in 0 until size){
        val result = wordCheker(br.readLine(), bw) 
        if(result)
            count++
        bw.write("$result\n")
        bw.write("\n")
    }

    bw.write("$count\n")

    bw.flush()
    br.close()
    bw.close()

}

/*
 그룹단어 판별기
 그룹단어란 : 단어에 존재하는 모든 문자가 연속해서 나타나는것
 예) abcd -> o / abca -> x
 예) abcc -> o aabc -> o / abcb -> x / abca -> x

 1. 단어를 순회한다

 1. 첫번째 단어 접근
 2. 현재 idx 이전의 idx 들의 단어를 순회하며 같은 값이 있는지 체크 -> 있다면 false
 3. 현재 자리의 단어와 다음 자리의 단어를 체크
 4. 두개가 같다면 그 다음 자리의 단어를 체크
 5. 다른 단어를 만났다면 해당 idx 로 이동해서 1번부터 재실행
 6. word 의 끝까지 다른 단어를 못만난다면 true 리턴

 1차 디버깅 : 연속된 두자리를 인식못한다 => aa, bb 등
 */
private fun wordCheker(word : String, bw : BufferedWriter) : Boolean {
    var buffer = -1
    for((idx, i) in word.withIndex()){
        // 5번 수행
        if(0<=buffer && buffer < word.length && idx != buffer) // buffer 까지 건너뛴다
            continue
        buffer = -1
        // 2번 수행

        for(rIdx in idx - 1 downTo 0){ // idx-1 포함, 0 포함
            bw.write("2번($rIdx) => i($idx) : $i, word[rIdx] : ${word[rIdx]}\n")
            if(word[rIdx] == i){
                return false
            }
        }

        // 3,4
        /*
        aca
        a, c -> buffer = 1
        c
         */
        for(nIdx in idx + 1 until word.length){ // idx+1 포함 word.length 미포함
            bw.write("3,4번($nIdx) => i($idx) : $i, word[rIdx] : ${word[nIdx]}\n")
            if(word[nIdx] == i){
                if(nIdx == word.length-1)
                    return true
                else
                    continue
            }else{
                buffer = nIdx // 5번을 수행하기 위한 저장
                break
            }
        }
    }

    return true
}