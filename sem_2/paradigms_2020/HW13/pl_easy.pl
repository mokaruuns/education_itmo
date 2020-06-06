fori().

while(I, N, MAX, D) :-
    not(0 is mod(N, I)),
    I2 is I + 1,
    fori(I2, N, MAX, D).

while(I, N, MAX, [H | T]) :-
    0 is mod(N, I),
    %print(I), nl,
    I is H,
    N2 is div(N, I),
    while(I, N2, MAX , T).


fori(I, N, MAX, [H | _]):-
    I * I > MAX,
    N > 1,
    N is H.
    %print(N), nl

fori(I, N, MAX, _):-
    I * I > MAX,
    N = 1.

fori(I, N, MAX, D):-
    I2 is I * I,
    (<(I2, MAX); =(I2, MAX)),
    while(I, N, MAX, D).

prime_divisors(N, Divisors):-
    fori(2, N, N, Divisors).


prime(N):-
    prime_divisors(N, [N]).

composite(N):-
    not(prime_divisors(N, [N])).
