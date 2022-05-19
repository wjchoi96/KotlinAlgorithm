/*
    바킹독님 알고리즘 강의 중 정렬단원 Counting sort 구현 연습 문제
    silver 5

    수 정렬하기5

    길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다
    수의 개수 N(1 ≤ N ≤ 1,000,000)
    수는 절댓값이 1,000,000보다 작거나 같은 정수이며, 같은 수가 여러 번 중복될 수도 있다

    10초
*/
/*
    제출
    1. 틀렸습니다(2%)
    - 0 카운팅 처리 + 양수 출력할때 continue 처리 누락

    2. 성공
*/
/*
    개선 
    1. 단순히 그냥 max를 더해주고, 0 until max*2+1 을 순회하면 비내림차순 정렬이 되는구나
    - 단순한걸 놓쳤네

    제출
    1. 성공
*/

fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val max = 1000000
    val n = br.readLine().toInt()
    val freg = Array(max*2+1){0} // 음수는 양수로 전환, 양수는 +max 처리 해준다
    repeat(n){
        freg[br.readLine().toInt()+max]++
    }
    /*
        0 은 0
        1~max : 본래 음수들, -처리해서 출력
        max+1 ~ max*2 : 본래 양수들, -max 처리해서 출력
    */
    for(i in 0 until max*2+1){
        if(freg[i] == 0) continue
        for(u in 0 until freg[i]){
            bw.write("${i-max}\n")
        }
    }
    
    bw.flush()
    bw.close()
    br.close()
}
