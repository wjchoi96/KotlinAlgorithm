/*
    카카오 인턴 2020 기출문제 3번

    level 3

    보석쇼핑

    진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매


    4종류의 보석(RUBY, DIA, EMERALD, SAPPHIRE) 8개가 진열된 예시
    진열대 번호	1	2	3	4	5	6	7	8
    보석 이름	D	R	R	D	D	E	S	D
    3번부터 7번까지 5개의 보석을 구매하면 모든 종류의 보석을 적어도 하나 이상씩 포함하게 된다
    => 3, 4, 6, 7번의 보석만 구매하는 것은 중간에 특정 구간(5번)이 빠지게 되므로 맞지 않는다

    조건
    1. 연속되어야 한다
    2. 중복된 보석이 있어도 된다
    3. 모든 보석이 다 모이면 종료
    3-1. or 모든 보석을 다 못모으고 list의 끝에 도착하면 종료(이땐 결과처리 하지않는다)

    만약 짧은 구간이 여러개라면 시작 진열대 번호가 가장 작은 구간을 출력

    1<= gems <= 100,000
    1번 진열대부터 진열대 번호 순서대로 보석이름이 저장
*/

/*
    gems Array<String> : 보석 이름을 item
    gMap HashMap<String, Int> : 보석의 종류를 key, 수집한 개수를 value
    보석의 종류 개수

    1. 연속되어야한다
    2. 중복된 보석 가능
    3. 모든 보석이 다모이면 종료
    - 안모았던 보석을 수집하면 gMap에서 체크?
    - 안모았던 보석을 수집하면 수집Cnt++? => 총 보석 종류 개수와 비교
    4. 이전에 minLength가 있다면, 해당 길이보다 길어지면 즉시 종료

    백트래킹?
    브루트 포스?

*/
/*
    제출

    1. 51.1 / 100.0
    정확성 100%
    효율성 [2, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15]실패
    => 지금 idx부터 모든 보석을 다 사도 category 를 다 못사는 경우 처리

    2. 
    정확성 100%
    효율성 [2, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15]실패
    => 효율성은 투포인터알고리즘을 사용해야 하는듯 하다

*/

fun main(args : Array<String>){
    val gems = arrayOf(
        // "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"
        // "AA", "AB", "AC", "AA", "AC"
        // "XYZ", "XYZ", "XYZ"
        "ZZZ", "YYY", "NNNN", "YYY", "BBB"
    )
    Kakao003().solution(gems)
}

private class Kakao003 {
    private val gMap = HashMap<String, Int>()
    private lateinit var purchase : Array<Boolean>
    private var catCount = 0
    private var min : Int = Int.MAX_VALUE
    fun solution(gems: Array<String>): IntArray {
        for(i in 0 until gems.size){
            val gem = gems[i]
            gMap[gem] = gMap.getOrDefault(gem, gMap.size) // 각 보석을 key, idx를 value로 가진다
        } 
        catCount = gMap.count()
        print("category count : $catCount\n")
        gMap.forEach{ k, v ->
            print("gem[$k] : $v\n")
        }

        var resArr : IntArray = intArrayOf()
        fun dfs(i : Int, pCount : Int, pCatCount : Int, gems : Array<String>, start : Int) {
            if(gems.size-i < catCount-pCatCount){ // 현재 idx서부터 모든 보석을 구매해도 catCount를 못채우는경우
                // 10개의 보석 진열대
                // 5종류
                // size = 10, i= [0..9]

                //1종류 모았을때 짤리는 idx?
                // 10-7 < 4
                return
            }
            if(pCount >= min){ // 같은 최소값이라면 앞쪽 진열대에서 시작한게 출력되어야하기때문에 같은 값도 제거
                // print("cut node[$i] => pCount >= min : ${pCount >= min}\n")
                return 
            }
            if(pCatCount == catCount){
                min = pCount // min 보다 커지면 중간에 중단하기 때문에 무조건 현재값이 최소값
                resArr = intArrayOf(start, start+min-1)
                print("[${resArr[0]}, ${resArr[1]}] => $pCount\n")
                return 
            }
            if(i == gems.size){
                // print("cut node[$i] => gems.size : ${gems.size}\n")
                return 
            }
            val gem = gems[i]
            val pIdx = gMap[gem]!!
            // print("node[$i] : gem[$gem], isPurchaseCat : ${purchase[pIdx]}\n")
            val purchaseCatCount =  if(purchase[pIdx] == false){
                purchase[pIdx] = true
                pCatCount + 1
            }else{
                pCatCount
            }
            dfs(i+1, pCount+1, purchaseCatCount, gems, start) // 현재 진열대를 구매했다
        }

        for(start in 0 until gems.size){
            purchase = Array(catCount){false}
            dfs(start, 0, 0, gems, start+1) 
        }
        
        return resArr
    }

    // dfs 실제로 배열이 필요없다
    /*
        i : 검색 시작 idx
        pCatCount(purchase category count) : 현재 수집한 보석의 종류 수
        pCount(purchase count) : 구매한 전체 개수
        purchase : gMap을 통해 해당 보석의idx를 가져온 후, purchase배열에 접근하면 해당 보석을 구매했는지 알 수 있다

        1번진열대부터 시작이니, +1 해줘야한다
    */
}
