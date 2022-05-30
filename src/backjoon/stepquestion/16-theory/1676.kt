// sliver 4
// 16-11
//1676

/*
    N! 에서 뒤에서부터 처음 0이 아닌 숫자가 나올때까지 0의 개수를 구하라
    0 <= n <= 500
    500! 은 어마어마하게 큰 숫자임을 간과하고, 숫자로 받아서 처리하는 코드를 작성
    => BigInteger 였나? 그거 사용하기에도 너무 큰거같음

    끝자리에 0이 오려면 2*5 밖에 경우의 수가 없다
    2가 5보다 작은 수이니 점점 증가하는 숫자의 곱을 구하는 팩토리얼 특성상
    5를 소인수로 포함하고 있는 숫자 이전에 2를 포함하고 있는 숫자는 반드시 존재한다
    고로 5의 변화에만 집중하면 될것같다

    fac을 구하는것이아닌 facZero 함수와 dp를 새로 생성
    facZero : 팩토리얼이 진행되는 숫자를 소인수 분해해서 5가 들어있는 횟수를 계속 더해주는 함수

    => 굳이 팩토리얼을 진행하거나 dp를 사용할 필요없이 n!팩토리얼의 5의 개수를 구혀면 된다
    즉, 기본적으로 5의 개수에 따라 값이 변화한다고 보면 된다
    10 => 10안에 5가 몇개가 있냐 -> 2개
    15 => 3개
    단순히 5로 나누는게 아닌, 5로 나눠가면서 누적합을 구해야한다

    즉, 기본적으로 5의 개수에 따라 값이 변화한다고 보면 된다
    5의 n승에 따라 카운트 값을 한 개 더 추가해주면 되지 않겠는가. 
    한마디로 단순히 5로 나눌 것이 아니라 반복문을 통해 5로 나누면서 누적합을 해주어야 한다.
*/


import java.io.*
lateinit var facZeroDp : Array<Int>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    

    var n = br.readLine().toInt()

    facZeroDp = Array(n+1){-1}
    facZeroDp[0] = 0

    bw.write("${facZero(n)}\n")

    // 이코드는 잘 이해가 안가네
    // 최적화 코드
    var count = 0
    while(n>=5){
        count += n/5
        n /= 5
    }
    bw.write("$count\n")



    bw.flush()
    bw.close()
    br.close()
}


private fun facZero(n : Int) : Int {
    if(facZeroDp[n] >= 0){
        return facZeroDp[n]
    }
    facZeroDp[n] = facZero(n-1) + get5SoinsuCount(n) // n/5가 아닌 소인수분해
    return facZeroDp[n]
}

private fun get5SoinsuCount(n : Int) : Int{
    var value = n
    var count = 0
    while(value%5==0){
        count++
        value/=5
    }
    print("5Soinsu count[$n] => $count\n")
    return count
}

//  500! 은 어마어마하게 큰 숫자임을 간과하고, 숫자로 받아서 처리하는 코드를 작성
// => BigInteger 였나? 그거 사용하기에도 너무 큰거같음
lateinit var facDp : Array<Long>
private fun getFacZeroCount1(n : Int){
    facDp = Array(n+1){0}
    facDp[1] = 1
    facDp[2] = 2

    var res : Long = fac(n)
    print("$res\n")
    var count = 0
    do{
        // print("$res\n")
        if(res%10.toLong()==0.toLong()){
            count++
        }else{
            break
        }
        res /= 10
    }while(true)
    print("$count\n")
}
private fun fac(n : Int) : Long {
    if(facDp[n] != 0.toLong()){
        return facDp[n]
    }
    facDp[n] = fac(n-1)*n
    return facDp[n]
}