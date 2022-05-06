/*
    카카오 2019 인턴 기출문제 5번
    level 3

    징검다리 건너기


*/
/*
    제출

    1. 정확도 100%, 효율성 개박살
    - 효율성은 이분탐색을 추천하네?
    - 이분탐색은 O(nlogm)
    - 이 외에 O(n) 풀이 방법도 있다고 한다
*/

fun main(args : Array<String>){
    val s = intArrayOf(
        2, 4, 5, 3, 2, 1, 4, 2, 5, 1
    )
    Kakao2019005().solution(s, 3)
}

private class Kakao2019005 {
    fun solution(stones: IntArray, k: Int): Int {
        
        var count = 0
        loop@while(true){
            var jumpCount = 0
            for(i in 0 until stones.size){
                if(stones[i] == 0){
                    jumpCount++
                    if(jumpCount>=k){
                        break@loop
                    }
                    print("user[$count] jump[$jumpCount] $i\n")
                    continue
                }
                // 연속으로 건너는게 아니면 초기화
                if(jumpCount != 0){
                    jumpCount = 0
                }
                stones[i]--
            }
            print("${stones.toList()}\n")
            count++
            print("c : $count\n")
        }
        print("$count\n")

        return 0
    }
}