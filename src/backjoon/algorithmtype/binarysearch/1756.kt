/*
    네이버 카페 문제 50선 중  이분탐색 유형 연습문재
    gold 5

    피자 굽기
    https://www.acmicpc.net/problem/1756

    N개의 피자 반죽을 오븐에 넣고 구우려고 한다
    피자 반죽의 지름은 제각각이며, 오븐의 모양도 오묘하다
    오븐은 깊은 관처럼 생겼는데, 관의 지름이 깊이에 따라 들쭉날쭉하게 변한다

    n개의 피자가 오븐에 모두 들어가고 나면, 맨 위의 피자가 얼마나 깊이 들어가 있는지 궁금하다
    => 가장 마지막에 넣은 피자의 깊이를 출력

    오븐의 최상단이 1이고, 최하단 가장 깊은 곳이 D가 된다
    피자가 모두 오븐에 들어가지 않는다면 0을 출력

    2초
    오븐의 깊이 D
    반죽의 개수 N
    1<= D, N <= 300,000

    오븐의 최상단부터 깊이에 따른 오븐의 지름이 차례대로 주어진다
    피자 반숙이 완성대는 순서대로 각각 지름이 주어진다

    지름은 10억 이하의 자연수
    1,000,000,000
*/
/*
    오븐의 깊이가 300,000 이니 O(nlgn)정도가 필요할듯 하다

    반죽을 순차적으로
    1. 오븐에 넣을 수 있는 한 가장 깊이 넣는다(start - end 까지 선형탐색)
    -> 근데 가능한 깊은 곳에 넣어야 하는데
    2. 넣은 곳의 위치를 기억하고, end로 설정한다
    ..
    반죽이 남아있는데 start==end이 되거나, 반죽을 못 넣는 사태가 발생하면 0 출력
    반죽을 모두 넣었다면 end 출력
*/
/*
    제출
    1. 시간초과(3%)
    - makePizza2 
    - O(n^2) 이라 안될 것 같았다

    2. 성공
    - makePizza
    - 검색하여 oven list 처리를 통해 이분탐색을 진행하는 방법을 완전 보고 베낀건 아니고 조금 참고해서 생각해 냈다

*/
/*
    n과 d의 범위가 같으니 같은 문자로 표현
    makePizza 를 O(nlgn)으로 만들어야 한다

    https://gusdnr69.tistory.com/105 참고

    정렬이 안되어있어서 이분탐색이 불가능하다고 판단했는데
    [5 6 4 3 2 6 2 3]
    을 반죽 3의 입장에서 본다면
    6은 5와 동일하게 들어갈 수 있는 반죽이고
    2 이후의 오븐들은 어차피 못들어가는 오븐이니
    [5 5 4 3 2 2 2 2] 로 판단 할 수 있다    

    배열을 이렇게 변경해주는게 근데 o(n) 아닌가
    => 이걸 배열을 입력받을때 진행해준다
    => https://skygood95.tistory.com/89 를 읽다가 떠올림
*/

fun main(args: Array<String>){
    Solution1756().solve()
}
class Solution1756 {
    private var d = 0
    private var n = 0
    private lateinit var oven: Array<Int>
    private lateinit var breads: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        br.readLine().split(' ').map{it.toInt()}.apply {
            d=this[0]; n=this[1]
        }
        oven = Array<Int>(d){0}
        br.readLine().split(' ').map{it.toInt()}.forEachIndexed { i,v ->
            oven[i] = v
            if(i != 0 && oven[i]>oven[i-1]){
                oven[i] = oven[i-1]
            }
        }
        breads = br.readLine().split(' ').map{it.toInt()}.toTypedArray()

        println("${oven.toList()}")

        bw.write("${makePizze()+1}\n") // 1-indexed 형태로 출력
    
        bw.flush()
        bw.close()
        br.close()
    }

    // oven 배열이 정렬되어버렸으니 이분탐색
    private fun makePizze(): Int {
        var pushRes = 0
        var end = d-1
        for(i in 0 until n){
            pushRes = pushOven(breads[i], end)
            if(pushRes < 0) break
            end = pushRes-1
        }
        return pushRes
    }
    private fun pushOven(bread: Int, size: Int): Int {
        print("push try bread[$bread] size[$size] ")
        var start = -1 // 끝까지 못넣는 경우 -1을 내보내기 위함
        var end = size
        while(start<end){
            val mid = (start+end+1)/2
            // println("start[$start] mid[$mid] end[$end]")
            when{
                oven[mid]>=bread -> start=mid // 넣을 수 있다-> 더 좁은 범위(깊은곳)로 이동
                else -> end=mid-1 // 못넣는다면 더 넓은 범위(높은곳)로 이동
            }
        }
        println("push[$start]")
        return start
    }

    ////O(n) 내부에 최악의 경우 O(d)가 될 수 있는 로직이 존재하니 O(dn) => O(n^2)
    private fun makePizza2(): Int{
        var start = 0
        var end = d-1
        var maxOvenWidth = breads[0]
        for(i in 0 until n){
            if(start>end) break
            val item = breads[i]
            print("bread[$item] ")
            if(item>maxOvenWidth) return 0 // 못넣는다
            var pushIdx = -1
            // 최악의 경우O(d)
            for(o in start..end){
                pushIdx = o
                if(item>oven[o]){ // 못 넣는 곳을 탐색
                    pushIdx = o-1 // 이전 idx를 pushIdx로 설정
                    break
                }
                maxOvenWidth = Math.max(maxOvenWidth, oven[o])
            }
            println("push : $pushIdx ")
            if(!(pushIdx in start..end)) return 0
            end = pushIdx-1
        }
        return end+1+1 // 1-indexed 로 결과 출력 + end는 이전에 넣은 idx-1을 기억중
    }
}