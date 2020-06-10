
while(I, N, MAX, R, R1) :-
    not(0 is mod(N, I)),
    I2 is I + 1,
    fori(I2, N, MAX, R, R1), !.

while(I, N, MAX, R, R1) :-
    0 is mod(N, I),
    N2 is div(N, I),
    while(I, N2, MAX, R, R2),
    append(R2, [I], R1),!.

fori(I, N, MAX, R, R1):-
    I * I > MAX,
    N > 1,
    append(R, [N], R1), !.

fori(I, N, MAX, _, _):-
    I * I > MAX,
    N = 1, !.

fori(I, N, MAX, R, R1):-
    I2 is I * I,
    (<(I2, MAX); =(I2, MAX)),
    while(I, N, MAX, R, R1), !.

prime_divisors(N, R):-
    number(N),
    fori(2, N, N, [], R1),
   	reverse(R1, R), !.

mul_list(1, []).
mul_list(M, [H | T]):-
    mul_list(M1, T),
    M is H * M1.

prime_divisors(N, R):-
    var(N),
    mul_list(N, R),
    prime_divisors(N, R),
    !.

prime(N):-
    prime_divisors(N, [N]),
    !.

composite(N):-
    not(prime_divisors(N, [N])), !.

unique_prime_divisors(1, V):-
    V = [], !.


remove_duplicates([],[]).
remove_duplicates([H | T], List) :-
     member(H, T),
     remove_duplicates(T, List).
remove_duplicates([H | T], [H | T1]) :-
     not (member(H, T)),
     remove_duplicates(T, T1).

unique_prime_divisors(N, R):-
    var(R),
    prime_divisors(N, R1),
    remove_duplicates(R1, R), !.