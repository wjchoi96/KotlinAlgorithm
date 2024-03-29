/*
    바킹독님 알고리즘 강의 중 그리디 알고리즘 단원

    Greedy알고리즘
    - 지금 가장 최적인 답을 근시안적으로 택하는 알고리즘
    - 관찰을 통해 탐색 범위를 줄이는 알고리즘

    : 현재까지 배운 알고리즘 중에 그리디의 정의에 잘 맞는 알고리즘이 존재
    => 머지소트에서 정렬된 두 list를 O(n+m)에 합치는 알고리즘
    - o(n+m)에 수행할 수 있었던 이유는, 배열을 합쳐나가는 상황에 현재 칸에 들어가야 하는 원소를 정할 때 n+m개의 모든 원소를 보는 대신
    가장 앞에 있는 2개의 원소만 확인하면 된다는 점이였다
    - 즉 원래대로라면 O((n+m)^2)가 걸렸을텐데, 관찰을 통해 O(n+m)에 수행할 수 있었다

    이상적인 풀이 흐름
    1. 관찰을 통해 탐색 범위를 줄이는 방법을 고안
    - 직관적으로 보이는 풀이의 시간복잡도로는 해결 할 수 없을때, 시간복잡도를 낮출수 없을까 고민
    2. 탐색 범위를 줄여도 올바른 결과를 낸다는 사실을 수학적으로 증명
    - 그럴싸해보이는 방법이 생각나면, 그 방법이 올바른 결과를 낸다는 사실을 수학적으로 완벽하게 증명한 후에 구현    
    3. 구현

    현실적인 풀이 흐름
    1. 관찰을 통해 탐색 범위를 줄이는 방법을 고안
    2. 탐색범위를 줄여도 올바른 결과를 낸다는 강한 믿음을 가진다
    3. 믿음을 가지고 구현

    절망적인 풀이 흐름
    1. 관찰을 통해 탐색 범위를 줄이는 잘못된 방법을 고안
    2. 강한 믿음을 가진다
    3. 계속 틀린다
    - 방법이 틀린건지 구현이 틀린건지 시간은 없고 미치는 상황

    !!실제 코딩테스트에서의 전략
    1. 거의 똑같은 문제를 풀어봤거나, 간단한 문제여서 나의 그리디 풀이를 확신한다
    - 빠르게 구현해보고 틀리면 빠르게 손절

    2. 100%확신은 없지만 맞는 것 같은 그리디 풀이를 찾았다
    - 일단 넘어가고, 다른거 할거 다 하고 구현해볼것


    연습문제
    1. 백준 11047(solve)

    2. 백준 1931(solve)

    3. 백준 2217(solve)

    4. 백준 1026(solve)

    잘못된 그리디
    1. 백준 12865(solve)
    - dp문제
    - 그리로 풀 수 없는 문제인데 그리디를 질러보는 문제가있는데, 그러기 쉬운 문제
    - 구현은 dp 패키지에 진행

*/