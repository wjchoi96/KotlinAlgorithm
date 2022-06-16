/*
    https://www.crocus.co.kr/1000 에서 제시한 Parametric Search 연습 문제
    gold2
    난이도가 좀 높아서 문제 유형을 확인했는데 
    dp, 그리디, 이분탐색 유형

    난이도가 높고 어려운 유형이니 부담 내려놓고 편하게 풀다가 정 모르겠으면 답을 보고 이해하는 쪽으로 해보자

    숫자구슬
    https://www.acmicpc.net/problem/2613

    N개의 숫자 구슬이 막대에 꿰어져 일자로 놓여 있다
    구슬을 막대에서 빼낼 수 없고, 바꿀 수 없다

    숫자 구슬을 M개의 그룹으로 나누었을때 각 그룹의 합 중 최댓값이 최소가 되도록 하려 한다
    예)
    {5, 4, 2, 6, 9, 3, 8, 7}
    세개의 그룹으로 나눈다
    1번)
    {5, 4, 2}, {6, 9}, {3, 8, 7}
    => 11, 15, 18 이 되어 최댓값이 18이 된다

    2번)
    {5, 4, 2, 6}, {9, 3}, {8, 7}
    => 17, 12, 15 가 되어 최댓값이 17이 된다
    => 1번의 최댓값 보다 작은 17을 찾을 수 있다

    그룹에 포함된 숫자 구슬의 개수는 0개 보다 커야 한다

    그룹의 합 중 최댓값이 최소가 되도록 M개의 그룹으로 나누었을 때,
    그 최댓값과 각 그룹을 구성하는 개수를 출력하시오

    1초
    1<= N <= 300
    1<= M <= N
    구슬의 숫자는 100 이하 자연수

*/
/*
    Parametric Search 로써의 결정문제
    그룹의 합이 될 수 있는 최소~최대 의 범위를 이분탐색하며, 해당 합이 나오도록 3개의 그룹 구성이 가능한지 체크
    구슬의 값 중 최소값 ~ 모든 구슬의 합(사실 3개의 그룹으로 써 나올 수 있는 최대값이여야 하지만, 까다로우니 합으로 대체)

    그룹의 합이 주어졌을때, 해당 합이 나오도록 그룹을 구성하는 코드는 dp로?
    투포인터는 안되나?
    투포인터로 해당 합을 도출해 내고, 도출해 냈을때 그룹이 몇개가 나오는지, 나온 그룹들 중 합이 현재 값이 최대인지?
    
*/


fun main(args: Array<String>){
    Solution2613().solve()
}
class Solution2613 {
    private var n = 0
    private var m = 0
    private lateinit var arr: Array<Int>
    private var max = 0 // 최대 100
    private var sum = 0 // 최대 300*100
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        br.readLine().split(' ').map{it.toInt()}.apply{
            n = this[0]
            m = this[1]
        }
        arr = Array(n){0}
        br.readLine().split(' ').map{it.toInt()}.forEachIndexed { i,v ->
            arr[i] = v
            max = Math.max(max, v)
            sum += v
        }

        getMaxGroupSum()
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun getMaxGroupSum(){
        var start = max
        var end = sum
        while(start<end){
            val mid = (start+end)/2
            print("start[$start] mid[$mid] end[$end] ")
            val canGroup = canMakeGroupSum(mid)
            println("canGroup => $canGroup")
            when(canGroup){
                true -> end=mid-1 // 가능하다면, mid를 낮춰 최소값을 구하도록 한다
                false -> start=mid+1 // 불가능하다면, mid를 높여 가능한 값을 찾는다
            }
        }

    }
    //m개의 그룹으로 나누면서, 그룹의 합 중 최댓값이 value가 나오도록 할 수 있는가
    private fun canMakeGroupSum(value: Int): Boolean{
        var start = 0
        var end = start+1
        var group = arr[start]+arr[end]
        var prevGroup = 0
        var nextGroup = sum-group
        while(start<end && end<n){
            when{
                group<value -> {
                    group+=arr[++end]
                    nextGroup-=arr[end-1] // end 이후 그룹에서 end값을 빼준다
                } // 합이 value보다 작다면 end 를 늘려 갚을 키운다
                group==value -> {
                    //유효성이 일치하지 않는다면, start를 올린다
                    if(end+1-start == n-2){ // 3개의 그룹을 만드려면 2개는 남기고 현재 그룹이 만들어 져야 한다
                        group-=arr[start++]
                    }else { // 나머지 그룹의 합을 체크
                        if(group<prevGroup){
                            return false
                        }else if(group<nextGroup){
                            group+=arr[++end]
                            nextGroup-=arr[end-1]
                        }else{
                            return true
                        }
                    }
                } // 합이 value와 같다면, 유효성을 체크한다 -> 3개의 그룹이 가능한지, 나머지 구슬을 합쳐서 현재 value를 넘는지
                group>value -> {
                    group-=arr[start++]
                    prevGroup+=arr[start-1] // start 이전 그룹에 start 값을 추가해준다
                } // 합이 value보다 작다면 start를 늘려 값을 줄인다
            }
        }
        return false
    }

    
}