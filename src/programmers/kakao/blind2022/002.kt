/*
    카카오 블라인드 2022 002
    level 2
    https://school.programmers.co.kr/learn/courses/30/lessons/92335

    양의 정수n을 k진수로 변환 후, 변환 된 수 안에 아래 조건에 맞는 소수의 개수 출력
    - 0P0 처럼 소수 양쪽에 0이 존재
    - P0 처럼 소수 오른쪽에만 0이 존재, 왼쪽에는 아무것도 없음
    - 0P 처럼 소수 왼쪽에만 0이 존재, 오른쪽에는 아무것도 없음
    - P처럼 소수 양쪽에 아무것도 없는 경우. 단 각 자리수에 0이 포함되지 않는 소수
*/
/*
    n을 k진수로 변경한 수 m
    m을 0으로 split한 뒤, 결과들 중 소수가 몇개 있나 검색

    1. 진법 변환 알고리즘
    2. 소수 판별 알고리즘
*/
/*
    진법 변환 후 숫자가 Int 타입을 넘어설 수 있다는 것을 누락시켜서 2개의 테케를 실패했었다
*/

fun main(args: Array<String>){
    KakaoBlind2022002().solution(885733, 3)
}

class KakaoBlind2022002 {
    fun solution(n: Int, k: Int): Int {
        val m = conversion(n, k)
            .split('0')
            .map{ it.trim() }
            .filter{ !it.isEmpty() }
        println(m.toList())
        var count = 0
        m.forEach {
            if(isPrime(it.toLong())){
                println("prime => $it")
                count++
            }
        }
        println(count)
        return count
    }

    private fun conversion(n: Int, radix: Int): String{
        val sb = StringBuffer()
        var num = n
        while(num>0){
            sb.append(num%radix)
            num /= radix
        }
        val res = sb.reverse().toString()
        println(res)
        return res
    }

    private fun isPrime(n: Long): Boolean {
        println("check $n")
        if(n == 1L) return false
        var i = 2L
        while(i*i<=n){
            if(n%i==0L) return false
            i++
        }
        return true
    }
}