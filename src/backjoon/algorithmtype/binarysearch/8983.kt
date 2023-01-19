/*
    백준 8983
    백준 이분탐색 유형 문제
    https://www.acmicpc.net/problem/8983

    gold 4

    N마리의 동물이 서식
    일직선 상에 위치한 M개의 사대에서 사격이 가능
    일직선 상을 x-축, 사대의 추기 x1, x2, .. xm은 x-좌표값

    동물이 사는 위치는 (a1, b1), (a2, b2)..(an, bn)과 같이 x,y-좌표값

    총의 사정거리 L
    - 한 사대에서 거리가 L보다 작거나 같은 위치의 동물을 사냥 가능
    - 사대(xi)와 동물의 위치(aj, bj)의 거리는 |xi-aj| + bj

    사대와 동물들의 위치가 주어졌을때, 잡을 수 있는 동물의 수를 출력

    1 ≤ M ≤ 100,000 - 사대
    1 ≤ N ≤ 100,000 - 동물의 수
    1 ≤ L ≤ 1,000,000,000 - 사정거리
    모든 좌표 값은 1,000,000,000보다 작거나 같은 양의 정수
    사대의 위치가 겹치는 경우는 없으며, 동물들의 위치가 겹치는 경우도 없다
*/
/*
    거리가 L일때, 잡을 수 있는 동물들의 수
    
    사대에서 잡을 수 있는 동물들을 확인하려면, 최악의 경우 모든 사대를 확인
    -> O(M * N) = 10,000,000,000

    블로그를 참고
    https://lotuslee.tistory.com/77
    https://data-make.tistory.com/593

    동물의 위치에서, 자신을 쏠 수 있는 사대를 탐색
    -> 이분탐색
    
    min -> min 사대
    max -> max 사대

    쏠 수 있다면 count++
    거리가 멀다면 동물의 x축에 가까운 사대로 이동
    거리가 가깝다면 쏠수 있음
    -> 쏠수있다, 없다로 결정한다면 쏠수 없는 경우엔 모든 사대를 체크하게 될것
    -> 가장 가까운 사대를 이분탐색 후, 해당 사대에서 동물을 쏠 수 있는지 없는지 체크
    
*/
/*
    제출
    
    1. 틀렸습니다(11%) -> 9점 
    - M ≤ 20, N ≤ 20, X ≤ 20 를 통과 못한 상태
    - animals를 x축 기준으로 정렬
    - start를 유지시켜서, 이전 x축 결과를 start로 진행

    2. 틀렸습니다(11%) -> 9점 
    - O(nlogm) 인데 왜 시간초과가 나지;
    - 클래스 구조를 벗겨봄

    3. 틀렸습니다(11%) -> 9점 
    - 최대한 가까운 사대를 찾는건데, lowerBound를 돌려서 문제 발생
    - 사대보다 작은 값중에 제일 가까운게 있는 경우를 고려해야함
        반례
        2 1 3
        3 6
        4 2
        => 1이 나와야 하는데 0이나옴
        => binSearch를 통해 3사대가 나와야하는데, 6사대가 나옴
        1 1 3
        3
        4 2
    => 위의 반례와 다르게 1이 나오는것을 확인 가능
    이분 탐색시 3가지 요소를 고려
    1. dist가 l범위 안에 존재 -> return
    2. dist가 l범위 밖에 존재
    2_1. animal이 사대 왼쪽에 존재 -> 사대를 낮춰야함 -> end = mid
    2_2. animal이 사대 오른쪽에 존재 -> 사대를 올려야함 -> start = mid + 1

    4. 성공
*/

fun main(){
    Solution8983().solve()
}
class Solution8983 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (m, n, l) = br.readLine().split(' ').map{ it.toInt() }
        val sands = br.readLine().split(' ').map{ it.toInt() }.sorted().toTypedArray()
        val animals = Array(n){ 0 to 0 }
        repeat(n) { i ->
            animals[i] = br.readLine().split(' ').map{ it.toInt() }.let { 
                it[0] to it[1] 
            }
        }

        var count = 0
        for(animal in animals) {
            val idx = binSearch(0, m-1, sands, animal, l)
            sands[idx].let {
                val dist = getDist(it to 0, animal)
                println("finish => idx[$idx], sand[$it], animal[$animal], dist[${dist}], it[${dist <= l}]")
                if (dist <= l) { count++ }
            }
        }
        bw.write("$count\n")
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun binSearch(startIdx: Int, endIdx: Int, sands: Array<Int>, value: Pair<Int, Int>, l: Int): Int {
        var start = startIdx
        var end = endIdx
        while(start < end){
            val mid = (start + end) / 2
            val dist = getDist(sands[mid] to 0, value)
            println("start[$start], mid[$mid], end[$end] dist[$dist]")
            when {
                dist <= l -> return mid
                else -> {
                    when {
                        sands[mid] < value.first -> start = mid + 1
                        else -> end = mid
                    }
                }
            }
        }
        println("finish bound => start[$start], end[$end]")
        return start
    }

    private fun getDist(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
        return Math.abs(a.first - b.first) + Math.abs(a.second - b.second)
    }
}

/*
반례
38 38 38957
21945 32795 39916 5989 46965 18836 18650 39921 24115 5984 41360 11992 32109 22649 15217 32515 27533 17031 4754 43278 20673 31155 13283 45464 20145 41993 3795 15391 10888 5542 31495 46346 5242 9433 21437 24290 42271 49862
34880 36872
2251 37414
15118 38858
47074 38849
46298 38909
31368 38831
28968 37523
48626 37721
14276 38017
47922 38001
43677 38558
46932 38924
19515 38327
7214 37732
34582 37170
10311 38380
28612 37878
2324 37487
41263 38860
7916 37440
42942 38621
48485 37580
19733 38546
14781 38521
48173 37749
16512 38438
44086 38150
16251 38178
45056 38549
33059 38693
19049 38744
46483 38821
13370 38870
19352 38442
38421 37463
29209 37281
24258 38926
27022 38446
=> 24
*/