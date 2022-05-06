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
    => 실제list를 사용 안하는 dfs
    정확성 100%
    효율성 [2, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15]실패
    => 효율성은 투포인터알고리즘을 사용해야 하는듯 하다
    => Kakao003UseDfs

    3. 성공
    => 투포인터 알고리즘을 학습 후 구현

*/

fun main(args : Array<String>){
    val gems = arrayOf(
        "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"
        // "AA", "AB", "AC", "AA", "AC"
        // "XYZ", "XYZ", "XYZ"
        // "ZZZ", "YYY", "NNNN", "YYY", "BBB"
    )
    Kakao003().solution(gems)
}

/*
    투포인터 사용 방식으로 다시 풀어보자
    start 1938 solve 2017

    진열대list
    구간 내 결과 처리

    보석의 종류의 개수 
    => gems를 입력받아 hashMap으로 정리한다
    => gMap : HashMap<String, Int> => 보석, 개수
    => gMap의 size가 보석의 종류의 개수

    start = 0
    end = 0
    sum = 0
    => start~end범위 내에 보석의 종류수
    => 내가 현재 구입한 보석목록 이 필요하다
    => buyMap : HashMap<String, Int> => 보석, 개수
    => sum => buyMap의 size가 될것

    진열대idx로 보석의 카테고리를 알아내는 방법
    idx를 넣었을때, 보석의 종류를 리턴하는 map
    catMap : HashMap<Int, String> => idx, 보석 
    => catMap도 gems를 통해 입력받는다

    end 증가 조건
    - 조건을 만족 못할때 and
    - end != size 

    start 증가조건
    - 조건을 만족할때 or
    - end == size

    종료조건
    - start == n and end == n

    자료구조의 종류
    1. 각 보석의 개수를 저장하는 gCatMap : HashMap<String, Int>  => 어차피 카테고리 개수 구하기용이라 toSet().size 를 통해 대체
    2. 진열대idx와 보석을 대칭시켜주는 gidxMap : HashMap<Int, String>
    3. 현재 구매한 보석의 개수 gBuyMap : HashMap<String, Int> 

*/
private class Kakao003 {
    private val gIdxMap = HashMap<Int, String>()
    private val gBuyMap = HashMap<String, Int>()
    private var shopCount = 0
    private var gemsCatCount = 0


    /*
        buyGemsCount 구하는 법
        [0, 0] => 0개
        [0, 1] => 1개
        [0, 4] => 4개 
        => end - start
    */
    private var start = 0
    private var end = 0
    private val buyGemsCount : Int
        get() = end - start 
    private val buyCatCount : Int
        get() = gBuyMap.size

    fun solution(gems: Array<String>): IntArray {
        shopCount = gems.size
        gemsCatCount = gems.toSet().size // set 은 중복 허용을 안하기때문에 보석 종류수를 구할 수 있다
        gems.forEachIndexed{ i, v ->
            gIdxMap[i] = v // 진열대 idx 와 보석을 대칭
        }

        var minBuyGemsCount = Int.MAX_VALUE
        var resStart = 0
        var resEnd = 0
        while(start < shopCount){
            print("v [$start, $end] => gems : $buyGemsCount, cats : $buyCatCount\n")
            if(buyCatCount >= gemsCatCount || end == shopCount){
                if(buyCatCount == gemsCatCount){ 
                    print("catch [$start, $end] => gems : $buyGemsCount, cats : $buyCatCount\n")
                    if(buyGemsCount < minBuyGemsCount){ // 여기서 체크해야하는것은 보석의 종류수 비교가아닌, 구매한 진열대 개수이다
                        minBuyGemsCount = buyGemsCount
                        resStart = start+1 // 진열대는 1번부터 시작하지만, idx는 0번부터 시작하기 때문
                        resEnd = end //end+1-1 => 현재end가 가리키고 있는 값은 구매에 포함되지않았기때문에-1을 해줘야한다. 하지만 start와 마찬가지의 이유로 +1을 해줘야 하기때문에 그대로 유지
                        print("finally catch [$start, $end] => gems : $buyGemsCount, cats : $buyCatCount\n")
                    }
                }
                // start++ 시 gBuyMap 에서 start가 가리키고 있던 진열대의 보석 구매를 취소
                gBuyMap[gIdxMap[start]!!] = gBuyMap.get(gIdxMap[start])!! - 1 
                // 해당 작업을 마치고 해당 보석에 대응하는 구매 count 가 0이하라면 아예 제거
                if(gBuyMap.getOrDefault(gIdxMap[start]!!, 0) <= 0){
                    gBuyMap.remove(gIdxMap[start])
                }
                start++
            }else if(buyCatCount < gemsCatCount){
                // end++ 과 연계되는 작업 실시
                gBuyMap[gIdxMap[end]!!] = gBuyMap.getOrDefault(gIdxMap[end++]!!, 0) + 1
            }
        }
        print("[$resStart, $resEnd]\n")
        return intArrayOf(resStart, resEnd)
    }
}


/*
    정확성 100%
    효율성 [2, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15]실패
*/
private class Kakao003UseDfs {
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

