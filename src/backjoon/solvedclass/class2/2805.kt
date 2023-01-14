/*
    백준 2805
    solved.ac class2** 문제
    https://www.acmicpc.net/problem/2805

    sliver 2

    이분탐색

    나무 M미터가 필요
    나무 한 줄에 대한 벌목 수행

    절단기의 높이 H를 지정
    한 줄에 연속해있는 나무를 모두 절단
    - H보다 높은 나무는 H의 윗부분이 잘림
    - H보다 낮은 나무는 잘리지 않음
    - 잘리 나무를 들고 복귀

    예) [20, 15, 10, 17]의 줄에 H를 15로 지정한다면
    - 5, 2의 나무를 획득하여 총 7M의 나무를 얻음

    환경을 위해 나무를 꼭 필요한 만큼만 집으로 가져가려함
    적어도 M미터의 나무를 가져가기위해 절단기의 높이(H)의 최댓값을 구하라
    -> m에 가까운 범위? m을 넘으면 안됨?
*/
/*
    나무의 수 N
    1 <= N <= 1,000,000

    필요한 나무의 길이 M
    1 <= M <= 2,000,000,000

    나무의 높이
    합은 항상 M보다 크거나 같음
    0 <= 높이 <= 1,000,000,000
*/
/*
    H = mid
    value = H로 나무를 잘랐을때 얻을 수 있는 나무의 길이의 합
    
    when() {
        value < m -> 나무의 길이가 부족하니, H를 낮춰 더 많은 나무를 획득하게 함
        value == m -> 필요한 만큼의 나무를 획득 -> return
        value > m -> 나무의 길이가 넘치니, H를 높여 더 적은 나무를 획득하게 함
    }

    case3에서 최대값을 구했는데, H를 높여서 반복 조건이 깨진 경우라면
    -> start를 리턴

    높이가 mid일때 얼마나 나무를 얻을 수 있나

*/
/*
    제출 
    1. 틀렸습니다(4%)
    - value == mid 일때만 일치하도록 코드가 작성되었음 
    - 무한 Loop에 빠질 수 있음
    - mid-1, mid+1을 end와 start에 설정해줌

    2. 틀렸습니다(4%)
    - m을 넘기 않도록 설정

    3. 틀렸습니다(4%)
    - m에 최대한 가까운으로 변경
    - https://st-lab.tistory.com/270 참고 시작
        - Upper Bound방식이라 함
    - 우선 value를 long타입으로 변경

    4. 틀렸습니다(4%)
    - res = start -1 로 수정

    5. 틀렸습니다(39%)
    - 기본값 0 처리

    6. 틀렸습니다(39%)
    - start를 1로 변경
    - 나무의 min보다 작은 값으로 잘라야 할 수 있음

    7. 성공


*/

fun main() {
    Solution2805().solve()
}

class Solution2805 {
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        val trees = br.readLine().split(' ').map{ it.toInt() }.sorted().toTypedArray()

        var start = 1
        var end = trees[n-1]
        while(start <= end) {
            val mid = (start+end)/2
            val value: Long = sliceTree(mid, trees)
            println("start[$start], mid[$mid], end[$end] = value[$value]")
            when {
                value < m -> end = mid-1
                value == m.toLong() -> {
                    start = mid + 1
                    println("break")
                    break
                }
                value > m -> start = mid+1
            }
        }
        println("finish pointer => start[$start], end[$end]")
        bw.write("${start-1}\n")
        bw.flush()
        bw.close()
        br.close()
    }

    private fun sliceTree(height: Int, trees: Array<Int>): Long {
        var value: Long = 0
        trees.filter{ it > height }.map{ it - height }.forEach {
            value += it.toLong()
        }
        return value
    }

    // 블로그 토대 코드 -> while의 조건식과 end를 변경하는 조건식만 다름
    fun solve2() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        val trees = br.readLine().split(' ').map{ it.toInt() }.sorted().toTypedArray()

        var start = 1
        var end = trees[n-1]
        while(start < end) {
            val mid = (start+end)/2
            val value: Long = sliceTree(mid, trees)
            println("start[$start], mid[$mid], end[$end] = value[$value]")
            when {
                value < m -> end = mid
                else -> start = mid+1
            }
        }
        println("finish pointer => start[$start], end[$end]")
        bw.write("${start-1}\n")
        bw.flush()
        bw.close()
        br.close()
    }

}


// class Solution2805 {
    // fun solve() {
    //     val bw = System.out.bufferedWriter()
    //     val br = System.`in`.bufferedReader()

    //     val (n, m) = br.readLine().split(' ').map{ it.toInt() }
    //     val trees = br.readLine().split(' ').map{ it.toInt() }.sorted().toTypedArray()

    //     var start = 1
    //     var end = trees[n-1]
    //     while(start < end) {
    //         val mid = (start+end)/2
    //         val value: Long = sliceTree(mid, trees)
    //         println("start[$start], mid[$mid], end[$end] = value[$value]")
    //         when {
    //             value < m -> end = mid
    //             else -> start = mid+1
    //         }
    //     }
    //     println("finish pointer => start[$start], end[$end]")
    //     bw.write("${start-1}\n")
    //     bw.flush()
    //     bw.close()
    //     br.close()
    // }

//     private fun sliceTree(height: Int, trees: Array<Int>): Long {
//         var value: Long = 0
//         trees.filter{ it > height }.map{ it - height }.forEach {
//             value += it.toLong()
//         }
//         return value
//     }

// }

/*
반례

5 19
4 42 40 26 46
36


4 10
20 15 10 17
13

2 3
3 3
0

4 3
2 2 2 2
1


3 4
3 3 5
2

*/