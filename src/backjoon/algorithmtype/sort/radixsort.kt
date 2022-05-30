/*
    바킹독님 알고리즘 강의 중 Radix Sort 구현

    Radix sort의 구현
    - 구현같은 경우는 맹점이 하나 존재한다
    - 10개의 list가 필요한데, 배열을 사용하여 10개의 list를 구현한다면, n개의 원소가 하나의 list에 다 몰릴수도 있기때문에 size가 n인 배열을 k개 생성해야만 한다
    - 이는 공간의 낭비가 너무나도 심한 방법
    - 이를 보완하기 위해선 동적배열 혹은 LinkedList를 사용해야한다
    - 그런데 동적배열이든 LinkedList의 경우는 라이브러리가 없다면 구현이 까다롭고, 보통 라이브러리가 접근이 가능한 상황이라면 sort 함수를 쓰고말지? 라는 생각도 든다
    - 코딩테스트에서는 소트 기능 자체를 구현할 일은 특수한 상황을 제외하곤 없다고 단정지을 수 있고, 그 중 Radix Sort는 절대 업다고 할 수 있다
    - 따라서 LinkedList를 사용해서 구현을 진행해 보도록 하자

    - 음수는 어떻게 하지?
    
*/
import java.util.LinkedList
private val arr = arrayOf(
    61, 10, 100, 121, 801, 251, 700, 901
)
// 최대 자리수가 100이니 d = 3
private val d = 3
//pow 는 실수형을 반환하는 함수이기때문에 실수 오차로 인해 꼬여버릴 수 있다
private val dArr: Array<Int> = Array(d){0}
private val p: Array<LinkedList<Int>> = Array(10){LinkedList()}

fun main(args: Array<String>){
    dArr[0] = 1
    repeat(dArr.size-1){dArr[it+1] = dArr[it]*10}
    println("${dArr.toList()}")
    
    radixSort()
    println("${arr.toList()}")
}
private fun radixSort(){
    for(digit in 0 until d){
        for(i in 0 until arr.size){
            p[getDigit(arr[i], dArr[digit])].offer(arr[i])
        }
        
        var pointer = 0
        for(i in 0 until p.size){
            while(!p[i].isEmpty()){
                arr[pointer++] = p[i].poll()
            }
        }
        println("[${dArr[digit]}] => ${arr.toList()}")
    }
}
/*
    num: 101
    101/100 = 1%10 = 1
    101/10 = 10%10 = 0
    101/1 = 101%10 = 1
*/
private fun getDigit(num: Int, digit: Int): Int{
    return (num/digit)%10
}